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
    private boolean isReturning = false;

    public ThrownCroissantEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public ThrownCroissantEntity(EntityType<? extends PersistentProjectileEntity> type, World world, LivingEntity owner) {
        super(type, owner, world);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ITEM, ItemStack.EMPTY);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);

        nbt.put("CroissantItem", this.getStack().writeNbt(new NbtCompound()));
        nbt.putBoolean("IsReturning", this.isReturning);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);

        if (nbt.contains("CroissantItem", NbtElement.COMPOUND_TYPE)) this.setStack(ItemStack.fromNbt(nbt.getCompound("CroissantItem")));
        this.isReturning = nbt.getBoolean("IsReturning");
    }

    @Override
    public void tick() {
        if (!(this.getStack().getItem() instanceof AbstractCroissantItem)) return;

        final AbstractCroissantItem item = (AbstractCroissantItem) (this.getStack().getItem());
        final Entity owner = this.getOwner();


        if (!isReturning && !this.inGround && age >= item.getProjectileDurationTicks()) this.isReturning = true;
        if (!isReturning || world.isClient || owner == null || !owner.isAlive()) {
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
        entity.damage(this.getDamageSources().thrown(this, this.getOwner()), item.getProjectileDamage());

        item.onProjectileHitEntity(entityHitResult, this.getOwner());

        isReturning = true;
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
        return distance < 512 * ThrownCroissantEntity.getRenderDistanceMultiplier();
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
