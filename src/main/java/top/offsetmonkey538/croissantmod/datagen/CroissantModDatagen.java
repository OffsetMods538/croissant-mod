package top.offsetmonkey538.croissantmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import top.offsetmonkey538.croissantmod.datagen.language.EnglishLanguageProvider;
import top.offsetmonkey538.croissantmod.datagen.language.EstonianLanguageProvider;

public class CroissantModDatagen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        final FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModelGenerator::new);
        pack.addProvider(EnglishLanguageProvider::new);
        pack.addProvider(EstonianLanguageProvider::new);
        pack.addProvider(RecipeProvider::new);
    }
}
