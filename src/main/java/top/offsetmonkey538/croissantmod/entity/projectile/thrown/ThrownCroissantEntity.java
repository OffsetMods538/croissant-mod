package top.offsetmonkey538.croissantmod.entity.projectile.thrown;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import top.offsetmonkey538.croissantmod.item.AbstractCroissantItem;

public class ThrownCroissantEntity extends PersistentProjectileEntity implements FlyingItemEntity {
    private static final TrackedData<ItemStack> ITEM = DataTracker.registerData(ThrownCroissantEntity.class, TrackedDataHandlerRegistry.ITEM_STACK);

    private static final String ITEM_NBT_KEY = "CroissantItem";
    private static final String IS_RETURNING_NBT_KEY = "IsReturning";
    private static final String ENTITIES_HIT_NBT_KEY = "EntitiesHit";

    private boolean isReturning = false;
    private int entitiesHit = 0;

    public ThrownCroissantEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public ThrownCroissantEntity(EntityType<? extends PersistentProjectileEntity> type, World world, LivingEntity owner) {
        super(type, owner, world);
        this.setOwner(owner);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ITEM, ItemStack.EMPTY);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);

        nbt.put(ITEM_NBT_KEY, this.getStack().writeNbt(new NbtCompound()));
        nbt.putBoolean(IS_RETURNING_NBT_KEY, this.isReturning);
        nbt.putInt(ENTITIES_HIT_NBT_KEY, this.entitiesHit);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);

        if (nbt.contains(ITEM_NBT_KEY, NbtElement.COMPOUND_TYPE)) this.setStack(ItemStack.fromNbt(nbt.getCompound(ITEM_NBT_KEY)));
        if (nbt.contains(IS_RETURNING_NBT_KEY, NbtElement.BYTE_TYPE)) this.isReturning = nbt.getBoolean(IS_RETURNING_NBT_KEY);
        if (nbt.contains(ENTITIES_HIT_NBT_KEY, NbtElement.INT_TYPE)) this.entitiesHit = nbt.getInt(ENTITIES_HIT_NBT_KEY);
    }

    @Override
    public void tick() {
        if (!(this.getStack().getItem() instanceof AbstractCroissantItem)) return;

        final AbstractCroissantItem item = (AbstractCroissantItem) (this.getStack().getItem());
        final Entity owner = this.getOwner();


        if (!isReturning && !this.inGround && age >= item.getProjectileDurationTicks()) this.isReturning = true;
        if (owner == null || !owner.isAlive()) {
            if (this.pickupType == PickupPermission.ALLOWED) this.dropStack(this.getStack());
            this.discard();
            return;
        }
        if (!isReturning || world.isClient) {
            super.tick();
            return;
        }

        final Vec3d direction = owner.getEyePos().subtract(this.getPos()).normalize();

        final Vec3d desiredVelocity = direction.multiply(item.getProjectileSpeed());
        final Vec3d steeringForce = desiredVelocity.subtract(this.getVelocity());

        this.setVelocity(this.getVelocity().add(steeringForce));


        super.tick();
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        final Entity entity = entityHitResult.getEntity();
        final AbstractCroissantItem item = (AbstractCroissantItem) (this.getStack().getItem());

        if (this.isOwner(entity)) return;
        if (entitiesHit++ >= item.getProjectileMaxEntitiesHit()) isReturning = true;
        entity.damage(this.getDamageSources().thrown(this, this.getOwner()), item.getProjectileDamage());

        item.onProjectileHitEntity(entityHitResult, this.getOwner(), this);
    }

    @Override
    public void onPlayerCollision(PlayerEntity player) {
        if (this.world.isClient || age < 10 || !this.isOwner(player)) return;

        if (this.tryPickup(player)) {
            player.sendPickup(this, 1);
            this.discard();
        }
    }

    @Override
    public boolean shouldRender(double distance) {
        return distance < 512 * Entity.getRenderDistanceMultiplier();
    }

    @Override
    protected void age() {
        if (pickupType != PickupPermission.ALLOWED) super.age();
    }

    @Override
    protected ItemStack asItemStack() {
        return this.getStack();
    }

    @Override
    public ItemStack getStack() {
        return this.getDataTracker().get(ITEM);
    }

    public void setStack(ItemStack itemStack) {
        this.getDataTracker().set(ITEM, itemStack);
    }

    public boolean isInGround() {
        return this.inGround;
    }
}
