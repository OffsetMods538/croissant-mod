package top.offsetmonkey538.croissantmod.init;

import net.minecraft.block.Blocks;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import top.offsetmonkey538.croissantmod.entity.effect.WalkerStatusEffect;

import static top.offsetmonkey538.croissantmod.CroissantMod.id;

public final class ModStatusEffects {
    private ModStatusEffects() {

    }

    public static final WalkerStatusEffect FROST_WALKER = register(new WalkerStatusEffect(StatusEffectCategory.BENEFICIAL, 8303100, Blocks.WATER, Blocks.FROSTED_ICE), "frost_walker");
    public static final WalkerStatusEffect MAGMA_WALKER = register(new WalkerStatusEffect(StatusEffectCategory.BENEFICIAL, 13783309, Blocks.LAVA, ModBlocks.MELTING_OBSIDIAN), "magma_walker");

    private static <T extends StatusEffect> T register(T statusEffect, String name) {
        return Registry.register(Registries.STATUS_EFFECT, id(name), statusEffect);
    }

    public static void register() {
        // Registers status effects by loading the class
    }
}
