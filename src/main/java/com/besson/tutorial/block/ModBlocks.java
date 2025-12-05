package com.besson.tutorial.block;

import com.besson.tutorial.TutorialMod;
import com.besson.tutorial.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(TutorialMod.MOD_ID);

    public static final DeferredBlock<Block> ICE_ETHER_BLOCK =
            registerBlocks("ice_ether_block", () -> new Block(BlockBehaviour.Properties.of().strength(1.5F, 6.0F)
                    .requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> RAW_ICE_ETHER_BLOCK =
            registerBlocks("raw_ice_ether_block", () -> new Block(BlockBehaviour.Properties.of().strength(1.5F, 3.0F)));
    public static final DeferredBlock<Block> ICE_ETHER_ORE =
            registerBlocks("ice_ether_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredBlock<StairBlock> ICE_ETHER_STAIRS =
            registerBlocks("ice_ether_stairs",
                    () -> new StairBlock(ICE_ETHER_BLOCK.get().defaultBlockState(), Block.Properties.of().strength(3.0F, 3.0F)));
    public static final DeferredBlock<SlabBlock> ICE_ETHER_SLAB =
            registerBlocks("ice_ether_slab",
                    () -> new SlabBlock(BlockBehaviour.Properties.of().strength(3.0F, 3.0F)));
    public static final DeferredBlock<ButtonBlock> ICE_ETHER_BUTTON =
            registerBlocks("ice_ether_button",
                    () -> new ButtonBlock(BlockSetType.STONE, 40, BlockBehaviour.Properties.of().strength(1.0F, 2.0F)));
    public static final DeferredBlock<PressurePlateBlock> ICE_ETHER_PRESSURE_PLATE =
            registerBlocks("ice_ether_pressure_plate",
                    () -> new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.of().strength(1.0F, 2.0F)));
    public static final DeferredBlock<FenceGateBlock> ICE_ETHER_FENCE_GATE =
            registerBlocks("ice_ether_fence_gate",
                    () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.of().strength(2.0F, 3.0F)));
    public static final DeferredBlock<FenceBlock> ICE_ETHER_FENCE =
            registerBlocks("ice_ether_fence",
                    () -> new FenceBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F)));
    public static final DeferredBlock<WallBlock> ICE_ETHER_WALL =
            registerBlocks("ice_ether_wall",
                    () -> new WallBlock(BlockBehaviour.Properties.of().strength(3.0F, 2.0F)));
    public static final DeferredBlock<DoorBlock> ICE_ETHER_DOOR =
            registerBlocks("ice_ether_door",
                    () -> new DoorBlock(BlockSetType.IRON, BlockBehaviour.Properties.of().strength(3.0F, 3.0F).noOcclusion()));
    public static final DeferredBlock<TrapDoorBlock> ICE_ETHER_TRAPDOOR =
            registerBlocks("ice_ether_trapdoor",
                    () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(2.0F, 1.5F).noOcclusion()));

    private static <T extends Block> void registerBlockItems(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static  <T extends Block> DeferredBlock<T> registerBlocks(String name, Supplier<T> block) {
        DeferredBlock<T> blocks = BLOCKS.register(name, block);
        registerBlockItems(name, blocks);
        return blocks;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
