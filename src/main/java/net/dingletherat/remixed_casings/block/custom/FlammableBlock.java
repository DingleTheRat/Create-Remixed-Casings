package net.dingletherat.remixed_casings.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class FlammableBlock extends Block {
    public int flammability = 20;
    public int fireSpreadSpeed = 5;

    public FlammableBlock(Properties properties) {
        super(properties);
    }

    public FlammableBlock(Properties properties, int flammability, int fireSpreadSpeed) {
        super(properties);
        this.flammability = flammability;
        this.fireSpreadSpeed = fireSpreadSpeed;
    }
    
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos position, Direction direction) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos position, Direction direction) {
        return flammability;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos position, Direction direction) {
        return fireSpreadSpeed;
    }
}
