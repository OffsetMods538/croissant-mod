package top.offsetmonkey538.croissantmod.entity.effect;

import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class FrostWalkerStatusEffect extends StatusEffect {
    public FrostWalkerStatusEffect(StatusEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        FrostWalkerEnchantment.freezeWater(entity, entity.getWorld(), entity.getBlockPos(), amplifier);
    }
}