package top.offsetmonkey538.croissantmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.FoodComponent;

public class CroissantItem extends Item {

    public CroissantItem() {
        super(new FabricItemSettings().food(
                new FoodComponent.Builder().hunger(8).saturationModifier(1.1f).build()
        ));
    }
}
