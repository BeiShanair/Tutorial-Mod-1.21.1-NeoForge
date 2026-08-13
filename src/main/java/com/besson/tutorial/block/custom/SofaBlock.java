package com.besson.tutorial.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class SofaBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<Type> TYPE = EnumProperty.create("type", Type.class);
    public SofaBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(TYPE, Type.SINGLE));
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        return getRelatedBlockState(state, level, pos, state.getValue(FACING));
    }

    private BlockState getRelatedBlockState(BlockState state, LevelAccessor level, BlockPos pos, Direction value) {
        boolean left = isRelatedBlock(level, pos, value, true);
        boolean right = isRelatedBlock(level, pos, value, false);
        if (left && right) {
            return state.setValue(TYPE, Type.MIDDLE);
        } else if (left) {
            return state.setValue(TYPE, Type.RIGHT);
        } else if (right) {
            return state.setValue(TYPE, Type.LEFT);
        }
        return state.setValue(TYPE, Type.SINGLE);
    }

    private boolean isRelatedBlock(LevelAccessor level, BlockPos pos, Direction value, boolean counterClockwise) {
        Direction rotate = counterClockwise ? value.getCounterClockWise() : value.getClockWise();
        BlockState state = level.getBlockState(pos.relative(rotate));
        if (state.getBlock() == this) {
            Direction direction = state.getValue(FACING);
            return direction.equals(value);
        }
        return false;
    }


    public enum Type implements StringRepresentable {
        SINGLE("single"),
        LEFT("left"),
        MIDDLE("middle"),
        RIGHT("right");

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
