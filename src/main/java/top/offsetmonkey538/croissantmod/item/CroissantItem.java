package top.offsetmonkey538.croissantmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.FoodComponent;

public class CroissantItem extends AbstractCroissantItem {

    public CroissantItem() {
        super(new FabricItemSettings().food(
                new FoodComponent.Builder().hunger(8).saturationModifier(1.1f).build()
        ));
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
