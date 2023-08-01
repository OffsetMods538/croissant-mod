package top.offsetmonkey538.croissantmod.mixin.block;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.block.BlockState;
import net.minecraft.block.MagmaBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import top.offsetmonkey538.croissantmod.init.ModStatusEffects;

@Mixin(MagmaBlock.class)
public abstract class MagmaBlockMixin {

    @ModifyExpressionValue(
            method = "onSteppedOn",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/enchantment/EnchantmentHelper;hasFrostWalker(Lnet/minecraft/entity/LivingEntity;)Z"
            )
    )
    private boolean makeMagmaNotDamageEntityWithMagmaOrFrostWalker(boolean original, World world, BlockPos pos, BlockState state, Entity entity) {
        if (!(entity instanceof LivingEntity livingEntity)) return original;
        return original || livingEntity.hasStatusEffect(ModStatusEffects.MAGMA_WALKER) || livingEntity.hasStatusEffect(ModStatusEffects.FROST_WALKER);
    }
}
