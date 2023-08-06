package top.offsetmonkey538.croissantmod.init;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import top.offsetmonkey538.croissantmod.item.CroissantItem;
import top.offsetmonkey538.croissantmod.item.IceCroissantItem;
import top.offsetmonkey538.croissantmod.item.InfernalCroissantItem;

import static top.offsetmonkey538.croissantmod.CroissantMod.id;

public final class ModItems {
    private ModItems() {

    }

    public static final Item CROISSANT          = register(new CroissantItem(),         "croissant");
    public static final Item ICE_CROISSANT      = register(new IceCroissantItem(),      "ice_croissant");
    public static final Item INFERNAL_CROISSANT = register(new InfernalCroissantItem(), "infernal_croissant");

    private static <T extends Item> T register(T item, String name) {
        return Registry.register(Registries.ITEM, id(name), item);
    }

    @SuppressWarnings("UnstableApiUsage")
    public static void register() {
        // Registers items by loading the class

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.addAfter(Items.BREAD, CROISSANT);
            entries.addAfter(CROISSANT, ICE_CROISSANT);
            entries.addAfter(ICE_CROISSANT, INFERNAL_CROISSANT);
        });
    }
}
