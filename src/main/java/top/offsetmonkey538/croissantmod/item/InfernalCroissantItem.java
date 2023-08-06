package top.offsetmonkey538.croissantmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;
import net.minecraft.util.hit.EntityHitResult;
import top.offsetmonkey538.croissantmod.init.ModStatusEffects;

public class InfernalCroissantItem extends AbstractCroissantItem {
    public InfernalCroissantItem() {
        super(new FabricItemSettings().food(
                new FoodComponent.Builder().hunger(8).saturationModifier(1.1f)
                        .statusEffect(
                                new StatusEffectInstance(ModStatusEffects.MAGMA_WALKER, 300), 1f
                        ).build()
        ));
    }

    @Override
    public void onProjectileHitEntity(EntityHitResult entityHitResult, Entity user) {
        super.onProjectileHitEntity(entityHitResult, user);

        entityHitResult.getEntity().setFireTicks(600);
    }

    @Override
    public double getProjectileSpeed() {
        return 0.5;
    }

    @Override
    public float getProjectileDamage() {
        return 2f;
    }

    @Override
    public int getProjectileDurationTicks() {
        return 20;
    }

    @Override
    public int getProjectileMaxEntitiesHit() {
        return 3;
    }

    @Override
    public int getItemCooldownDurationTicks() {
        return 15;
    }
}
