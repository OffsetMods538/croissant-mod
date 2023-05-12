package top.offsetmonkey538.croissantmod.init;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import top.offsetmonkey538.croissantmod.entity.effect.FrostWalkerStatusEffect;

import static top.offsetmonkey538.croissantmod.CroissantMod.id;

public final class ModStatusEffects {
    private ModStatusEffects() {

    }

    public static final FrostWalkerStatusEffect FROST_WALKER = register(new FrostWalkerStatusEffect(StatusEffectCategory.BENEFICIAL, 8303100), "frost_walker");

    private static <T extends StatusEffect> T register(T statusEffect, String name) {
        return Registry.register(Registries.STATUS_EFFECT, id(name), statusEffect);
    }

    public static void register() {
        // Registers status effects by loading the class
    }
}
