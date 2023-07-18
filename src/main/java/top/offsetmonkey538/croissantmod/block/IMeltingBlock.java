package top.offsetmonkey538.croissantmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public interface IMeltingBlock {

    default void scheduledTick(int maxAge, IntProperty age, Block thisBlock, BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if ((random.nextInt(3) != 0 || !this.canMelt(thisBlock, world, pos, 4)) && !this.increaseAge(maxAge, age, state, world, pos)) {
            world.scheduleBlockTick(pos, thisBlock, MathHelper.nextInt(random, 20, 40));
            return;
        }

        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for (Direction direction : Direction.values()) {
            mutable.set(pos, direction);
            final BlockState blockState = world.getBlockState(mutable);

            if (!blockState.isOf(thisBlock) || this.increaseAge(maxAge, age, blockState, world, mutable)) continue;

            world.scheduleBlockTick(mutable, thisBlock, MathHelper.nextInt(random, 20, 40));
        }
    }

    default boolean canMelt(Block thisBlock, BlockView world, BlockPos pos, int maxNeighbors) {
        int neighborCount = 0;

        BlockPos.Mutable mutable = new BlockPos.Mutable();
        for (Direction direction : Direction.values()) {
            mutable.set(pos, direction);
            if (!world.getBlockState(mutable).isOf(thisBlock) || ++neighborCount < maxNeighbors) continue;
            return false;
        }
        return true;
    }

    default boolean increaseAge(int maxAge, IntProperty ageProperty, BlockState state, World world, BlockPos pos) {
        int age = state.get(ageProperty);
        if (age < maxAge) {
            world.setBlockState(pos, state.with(ageProperty, age + 1), Block.NOTIFY_LISTENERS);
            return false;
        }
        this.melt(state, world, pos);
        return true;
    }

    void melt(BlockState state, World world, BlockPos pos);
}
