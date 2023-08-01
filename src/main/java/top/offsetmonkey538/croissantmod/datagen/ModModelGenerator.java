package top.offsetmonkey538.croissantmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateVariant;
import net.minecraft.data.client.BlockStateVariantMap;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureMap;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.data.client.VariantsBlockStateSupplier;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import top.offsetmonkey538.croissantmod.init.ModBlocks;
import top.offsetmonkey538.croissantmod.init.ModItems;

public class ModModelGenerator extends FabricModelProvider {
    public ModModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(ModBlocks.MELTING_OBSIDIAN).coordinate(BlockStateVariantMap.create(Properties.AGE_3).register(0, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier("minecraft:block/obsidian"))).register(1, BlockStateVariant.create().put(VariantSettings.MODEL, blockStateModelGenerator.createSubModel(ModBlocks.MELTING_OBSIDIAN, "_1", Models.CUBE_ALL, TextureMap::all))).register(2, BlockStateVariant.create().put(VariantSettings.MODEL, blockStateModelGenerator.createSubModel(ModBlocks.MELTING_OBSIDIAN, "_2", Models.CUBE_ALL, TextureMap::all))).register(3, BlockStateVariant.create().put(VariantSettings.MODEL, blockStateModelGenerator.createSubModel(ModBlocks.MELTING_OBSIDIAN, "_3", Models.CUBE_ALL, TextureMap::all)))));
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.CROISSANT, Models.GENERATED);
        itemModelGenerator.register(ModItems.ICE_CROISSANT, Models.GENERATED);
        itemModelGenerator.register(ModItems.INFERNAL_CROISSANT, Models.GENERATED);
    }
}
