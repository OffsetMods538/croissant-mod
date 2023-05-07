package top.offsetmonkey538.croissantmod.init;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import top.offsetmonkey538.croissantmod.item.CroissantItem;

import static top.offsetmonkey538.croissantmod.CroissantMod.id;

public final class ModItems {
    private ModItems() {

    }

    public static final Item CROISSANT = register(new CroissantItem(), "croissant");

    private static <T extends Item> T register(T item, String name) {
        return Registry.register(Registries.ITEM, id(name), item);
    }

    public static void register() {
        // Registers items by loading the class
    }

    public static final class FoodComponents {
        public static final FoodComponent CROISSANT = new FoodComponent.Builder().hunger(8).saturationModifier(1.1f).build();
    }
}
