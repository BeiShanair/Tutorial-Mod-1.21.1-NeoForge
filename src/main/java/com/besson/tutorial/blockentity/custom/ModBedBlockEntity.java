package com.besson.tutorial.blockentity.custom;

import com.besson.tutorial.blockentity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class ModBedBlockEntity extends BlockEntity {
    private DyeColor color;
    public ModBedBlockEntity(BlockPos pos, BlockState blockState, DyeColor color) {
        super(ModBlockEntities.BED.get(), pos, blockState);
        this.color = color;
    }
    
    public ModBedBlockEntity(BlockPos pos, BlockState blockState) {
        this(pos, blockState, DyeColor.BLACK);
    }

    public DyeColor getColor() {
        return color;
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.color = DyeColor.byName(tag.getString("color"), DyeColor.BLACK);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putString("color", this.color.getName());
    }
}
