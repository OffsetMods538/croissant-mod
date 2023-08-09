package top.offsetmonkey538.croissantmod.datagen.language;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import top.offsetmonkey538.croissantmod.init.ModItems;
import top.offsetmonkey538.croissantmod.init.ModStatusEffects;

public class ModEstonianLanguageProvider extends FabricLanguageProvider {
    public ModEstonianLanguageProvider(FabricDataOutput dataOutput) {
        super(dataOutput, "et_ee");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.CROISSANT, "Sarvesai");
        translationBuilder.add(ModItems.ICE_CROISSANT, "Jääst Sarvesai");
        translationBuilder.add(ModItems.INFERNAL_CROISSANT, "Põrgu Sarvesai");
        translationBuilder.add(ModItems.TNT_CROISSANT, "Dünamiidi Sarvesai");

        translationBuilder.add(ModStatusEffects.FROST_WALKER, "Härmatiskõndija");
        translationBuilder.add(ModStatusEffects.MAGMA_WALKER, "Magmakõndija");
    }
}
