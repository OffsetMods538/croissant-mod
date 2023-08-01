package top.offsetmonkey538.croissantmod.init;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import top.offsetmonkey538.croissantmod.block.MeltingObsidianBlock;

import static top.offsetmonkey538.croissantmod.CroissantMod.id;

public final class ModBlocks {
    private ModBlocks() {

    }

    public static final MeltingObsidianBlock MELTING_OBSIDIAN = register(new MeltingObsidianBlock(FabricBlockSettings.copyOf(Blocks.OBSIDIAN).ticksRandomly()), "melting_obsidian");

    private static <T extends Block> T register(T block, String name) {
        return Registry.register(Registries.BLOCK, id(name), block);
    }

    public static void register() {
        // Registers blocks by loading the class
    }
}
