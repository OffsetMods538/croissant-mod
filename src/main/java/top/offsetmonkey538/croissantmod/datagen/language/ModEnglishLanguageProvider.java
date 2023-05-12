package top.offsetmonkey538.croissantmod.datagen.language;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import top.offsetmonkey538.croissantmod.init.ModItems;
import top.offsetmonkey538.croissantmod.init.ModStatusEffects;

public class ModEnglishLanguageProvider extends FabricLanguageProvider {
    public ModEnglishLanguageProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.CROISSANT, "Croissant");
        translationBuilder.add(ModItems.ICE_CROISSANT, "Ice Croissant");

        translationBuilder.add(ModStatusEffects.FROST_WALKER, "Frost Walker");
    }
}
