package com.besson.tutorial.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class ModPillarBlock extends Block {
    public static final EnumProperty<Type> TYPE = EnumProperty.create("type2", Type.class);
    public ModPillarBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(TYPE, Type.SINGLE));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TYPE);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        boolean top = level.getBlockState(pos.above()).is(this);
        boolean bottom = level.getBlockState(pos.below()).is(this);
        if (top && bottom) {
            return state.setValue(TYPE, Type.MIDDLE);
        } else if (top) {
            return state.setValue(TYPE, Type.BOTTOM);
        } else if (bottom) {
            return state.setValue(TYPE, Type.TOP);
        } else {
            return state.setValue(TYPE, Type.SINGLE);
        }
    }

    public enum Type implements StringRepresentable {
        SINGLE("single"),
        TOP("top"),
        MIDDLE("middle"),
        BOTTOM("bottom");

        private final String name;
        
        Type(String name) {
            this.name = name;
        }
        
        @Override
        public String getSerializedName() {
            return name;
        }
    }
}
