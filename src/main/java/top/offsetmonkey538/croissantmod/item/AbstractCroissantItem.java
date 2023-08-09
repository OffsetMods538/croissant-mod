package top.offsetmonkey538.croissantmod.item;

import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import top.offsetmonkey538.croissantmod.entity.projectile.thrown.ThrownCroissantEntity;
import top.offsetmonkey538.croissantmod.init.ModEntities;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCroissantItem extends Item {
    protected List<Pair<StatusEffectInstance, Float>> statusEffects = new ArrayList<>();

    protected AbstractCroissantItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!user.isSneaking()) return super.use(world, user, hand);

        final ItemStack itemStack = user.getStackInHand(hand);

        user.getItemCooldownManager().set(this, this.getItemCooldownDurationTicks());

        if (!world.isClient) {
            ThrownCroissantEntity croissantEntity = new ThrownCroissantEntity(ModEntities.CROISSANT, world, user);

            croissantEntity.setStack(itemStack.copyWithCount(1));
            croissantEntity.setNoGravity(true);
            croissantEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0, 1, 1f);

            world.spawnEntity(croissantEntity);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) itemStack.decrement(1);

        return TypedActionResult.success(itemStack);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (this.isFood()) afterEat(world, user);
        return super.finishUsing(stack, world, user);
    }

    protected void addStatusEffect(StatusEffectInstance effect, float chance) {
        statusEffects.add(Pair.of(effect, chance));
    }

    public void afterEat(World world, LivingEntity user) {

    }

    public abstract double getProjectileSpeed();

    public abstract float getProjectileDamage();

    public abstract int getProjectileDurationTicks();
    public abstract int getProjectileMaxEntitiesHit();

    public abstract int getItemCooldownDurationTicks();

    public void onProjectileHitEntity(EntityHitResult entityHitResult, Entity user, ThrownCroissantEntity croissantEntity) {
        final Entity entity = entityHitResult.getEntity();
        final World world = entity.getWorld();

        if (statusEffects == null || !(entity instanceof LivingEntity livingEntity)) return;


        for (Pair<StatusEffectInstance, Float> pair : this.statusEffects) {
            if (world.isClient() || pair.getFirst() == null || world.random.nextFloat() >= pair.getSecond()) continue;
            final StatusEffectInstance instance = new StatusEffectInstance(pair.getFirst());

            if (user == null) livingEntity.addStatusEffect(instance);
            else livingEntity.addStatusEffect(instance, user);
        }
    }
}
