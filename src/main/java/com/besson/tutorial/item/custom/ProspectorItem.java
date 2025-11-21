package com.besson.tutorial.item.custom;

import com.besson.tutorial.tag.ModBlockTags;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class ProspectorItem extends Item {
    public ProspectorItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        Level level = context.getLevel();

        if (!level.isClientSide()) {
            boolean found = false;
            if (!Screen.hasShiftDown()) {
                for (int i = 0; i <= pos.getY() + 64; i++) {
                    for (int j = 0; j < 5; j++) {
                        for (int k = 0; k < 5; k++) {
                            BlockPos pos1 = pos.below(i).north(j).east(k);
                            BlockState blockState = level.getBlockState(pos1);
                            String name = blockState.getBlock().getName().getString();

                            if (isCorrectBlock(blockState)) {
                                player.sendSystemMessage(Component.literal("Found " + name + " !"));
                                found = true;
                                break;
                            }
                        }
                    }
                }
                if (!found) {
                    player.sendSystemMessage(Component.literal("No ore found!"));
                }
            } else {
                for (int i = 0; i <= pos.getY() + 64; i++) {
                    BlockPos pos1 = pos.below(i);
                    BlockState blockState = level.getBlockState(pos1);
                    String name = blockState.getBlock().getName().getString();

                    if (isCorrectBlock(blockState)) {
                        player.sendSystemMessage(Component.literal("Found " + name + " !"));
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    player.sendSystemMessage(Component.literal("No ore found!"));
                }
            }
            context.getItemInHand().hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
            return InteractionResult.SUCCESS;
        }

        return super.useOn(context);
    }

    private boolean isCorrectBlock(BlockState blockState) {
        return blockState.is(ModBlockTags.ORE_TAGS);
    }
}
