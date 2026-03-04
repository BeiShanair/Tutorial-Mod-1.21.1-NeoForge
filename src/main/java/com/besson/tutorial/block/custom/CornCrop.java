package com.besson.tutorial.block.custom;

import com.besson.tutorial.item.ModItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CornCrop extends CropBlock {
    public static final MapCodec<CornCrop> CODEC = simpleCodec(CornCrop::new);
    public static final int FIRST_STAGE_AGE = 7;
    public static final int SECOND_STAGE_AGE = 1;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 8);

    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 10.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 12.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 14.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0)
    };

    public CornCrop(Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<? extends CropBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_AGE[this.getAge(state)];
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public int getMaxAge() {
        return FIRST_STAGE_AGE + SECOND_STAGE_AGE;
    }

    @Override
    protected IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.CORN;
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState below = level.getBlockState(pos.below());
        return super.canSurvive(state, level, pos) ||
                below.is(this) && below.getValue(AGE) == FIRST_STAGE_AGE;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.getRawBrightness(pos, 0) >= 9) {
            int age = this.getAge(state);
            if (age < this.getMaxAge()) {
                float f = getGrowthSpeed(this.defaultBlockState(), level, pos);
                if (random.nextInt((int) (25.0F / f) + 1) == 0) {
                    if (age == FIRST_STAGE_AGE) {
                        BlockState above = level.getBlockState(pos.above());
                        if (above.isAir()) {
                            level.setBlock(pos.above(), this.getStateForAge(age + 1), 3);
                        }
                    } else {
                        level.setBlock(pos, this.getStateForAge(age + 1), 3);
                    }
                }
            }
        }
    }

    @Override
    public void growCrops(Level level, BlockPos pos, BlockState state) {
        int nextAge = this.getAge(state) + this.getBonemealAgeIncrease(level);
        int maxAge = this.getMaxAge();
        if (nextAge > maxAge) {
            nextAge = maxAge;
        }

        BlockState above = level.getBlockState(pos.above());
        if (this.getAge(state) == FIRST_STAGE_AGE && above.isAir()) {
            level.setBlock(pos.above(), this.getStateForAge(nextAge), 3);
        } else {
            level.setBlock(pos, this.getStateForAge(nextAge - 1), 3);
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            if (this.getAge(state) == this.getMaxAge()) {
                level.removeBlock(pos, false);
                level.setBlock(pos.below(), this.getStateForAge(0), 3);
                popResource(level, pos, ModItems.CORN.get().getDefaultInstance());
                popExperience((ServerLevel) level, pos, 5);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }
}
