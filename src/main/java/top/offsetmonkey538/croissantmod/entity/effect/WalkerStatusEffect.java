package top.offsetmonkey538.croissantmod.entity.effect;

import net.minecraft.block.*;
import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class WalkerStatusEffect extends StatusEffect {
    private final Block walkOn;
    private final Block convertTo;

    public WalkerStatusEffect(StatusEffectCategory statusEffectCategory, int color, Block walkOn, Block convertTo) {
        super(statusEffectCategory, color);

        this.walkOn = walkOn;
        this.convertTo = convertTo;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        // Compatibility with my better frost walker mod
        // by using the vanilla code for freezing water.
        if (this.walkOn == Blocks.WATER && this.convertTo == Blocks.FROSTED_ICE) {
            FrostWalkerEnchantment.freezeWater(entity, entity.getWorld(), entity.getBlockPos(), amplifier);
            return;
        }

        if (!entity.isOnGround()) return;

        final World world  = entity.getWorld();
        final BlockPos blockPos = entity.getBlockPos();
        final BlockState blockState = this.convertTo.getDefaultState();


        final int area = Math.min(16, 2 + amplifier);

        for (BlockPos iteratedPos : BlockPos.iterate(blockPos.add(-area, -1, -area), blockPos.add(area, -1, area))) {
            final BlockState iteratedBlock = world.getBlockState(iteratedPos);

            if (!iteratedPos.isWithinDistance(entity.getPos(), area) || !iteratedBlock.isOf(this.walkOn) || !blockState.canPlaceAt(world, iteratedPos) || !world.canPlace(blockState, iteratedPos, ShapeContext.absent())) continue;


            world.setBlockState(iteratedPos, blockState);
            world.scheduleBlockTick(iteratedPos, convertTo, MathHelper.nextInt(entity.getRandom(), 60, 120));
        }
    }
}