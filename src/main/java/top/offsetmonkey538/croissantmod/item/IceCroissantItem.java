package top.offsetmonkey538.croissantmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import top.offsetmonkey538.croissantmod.init.ModStatusEffects;

public class IceCroissantItem extends AbstractCroissantItem {

    public IceCroissantItem() {
        super(new FabricItemSettings().food(
                new FoodComponent.Builder().hunger(8).saturationModifier(1.1f)
                        .statusEffect(
                                new StatusEffectInstance(ModStatusEffects.FROST_WALKER, 300), 1f
                        ).build()
        ));

        this.addStatusEffect(
                new StatusEffectInstance(StatusEffects.SLOWNESS, 150), 1f
        );
    }

    @Override
    public double getProjectileSpeed() {
        return 0.5;
    }

    @Override
    public float getProjectileDamage() {
        return 3f;
    }

    @Override
    public int getProjectileDurationTicks() {
        return 20;
    }

    @Override
    public int getItemCooldownDurationTicks() {
        return 15;
    }
}
