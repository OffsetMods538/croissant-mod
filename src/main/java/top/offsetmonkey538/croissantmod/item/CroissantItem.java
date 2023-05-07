package top.offsetmonkey538.croissantmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import top.offsetmonkey538.croissantmod.init.ModItems;

public class CroissantItem extends Item {

    public CroissantItem() {
        super(new FabricItemSettings().food(ModItems.FoodComponents.CROISSANT));
    }
}
