package top.offsetmonkey538.croissantmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.TntBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.ExplosionBehavior;
import top.offsetmonkey538.croissantmod.entity.projectile.thrown.ThrownCroissantEntity;
import top.offsetmonkey538.croissantmod.init.ModStatusEffects;

public class TntCroissantItem extends AbstractCroissantItem {

    public TntCroissantItem() {
        super(new FabricItemSettings().food(
                new FoodComponent.Builder().hunger(8).saturationModifier(1.1f).build()
        ));
    }

    @Override
    public void afterEat(World world, LivingEntity user) {
        if (world.isClient()) return;
        world.createExplosion(user, user.getDamageSources().explosion(user, user), new ExplosionBehavior(), user.getPos(), 2, false, World.ExplosionSourceType.TNT);
    }

    @Override
    public void onProjectileHitEntity(EntityHitResult entityHitResult, Entity user, ThrownCroissantEntity croissantEntity) {
        super.onProjectileHitEntity(entityHitResult, user, croissantEntity);

        final World world = user.getWorld();
        final Entity victim = entityHitResult.getEntity();

        if (world.isClient()) return;
        world.createExplosion(user, user.getDamageSources().explosion(user, user), new ExplosionBehavior(), victim.getPos(), 2, false, World.ExplosionSourceType.TNT);

        croissantEntity.discard();
    }

    @Override
    public double getProjectileSpeed() {
        return 0.5;
    }

    @Override
    public float getProjectileDamage() {
        return 0f;
    }

    @Override
    public int getProjectileDurationTicks() {
        return 20;
    }

    @Override
    public int getProjectileMaxEntitiesHit() {
        return 1;
    }

    @Override
    public int getItemCooldownDurationTicks() {
        return 15;
    }
}
