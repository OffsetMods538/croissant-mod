package top.offsetmonkey538.croissantmod.init;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static top.offsetmonkey538.croissantmod.CroissantMod.id;

public final class ModItems {
    private ModItems() {

    }



    private static <T extends Item> T register(T item, String name) {
        return Registry.register(Registries.ITEM, id(name), item);
    }

    public static void register() {
        // Registers items by loading the class
    }
}
