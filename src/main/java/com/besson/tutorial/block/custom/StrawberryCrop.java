package com.besson.tutorial.block.custom;

import com.besson.tutorial.item.ModItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class StrawberryCrop extends CropBlock {
    public static final MapCodec<StrawberryCrop> CODEC = simpleCodec(StrawberryCrop::new);
    public static final int MAX_AGE = 5;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_5;

    public StrawberryCrop(Properties properties) {
        super(properties);
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public MapCodec<? extends CropBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.STRAWBERRY_SEEDS;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return super.mayPlaceOn(state, level, pos) || state.is(BlockTags.DIRT);
    }
}
