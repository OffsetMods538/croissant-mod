package top.offsetmonkey538.croissantmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import top.offsetmonkey538.croissantmod.datagen.language.ModEnglishLanguageProvider;
import top.offsetmonkey538.croissantmod.datagen.language.ModEstonianLanguageProvider;

public class CroissantModDatagen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        final FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModModelGenerator::new);
        pack.addProvider(ModEnglishLanguageProvider::new);
        pack.addProvider(ModEstonianLanguageProvider::new);
        pack.addProvider(ModRecipeProvider::new);
    }
}
