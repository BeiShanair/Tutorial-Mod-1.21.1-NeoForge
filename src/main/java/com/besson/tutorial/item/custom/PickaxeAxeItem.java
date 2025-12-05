package com.besson.tutorial.item.custom;

import com.besson.tutorial.tag.ModBlockTags;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.state.BlockState;

public class PickaxeAxeItem extends AxeItem {

    public PickaxeAxeItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return state.is(ModBlockTags.PICKAXE_AXE_MINEABLE) ? 12.0F : 1.0F;
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return state.is(ModBlockTags.PICKAXE_AXE_MINEABLE);
    }
}
