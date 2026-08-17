package com.besson.tutorial.block;

import com.besson.tutorial.TutorialMod;
import com.besson.tutorial.block.custom.*;
import com.besson.tutorial.fluid.ModFluids;
import com.besson.tutorial.item.ModItems;
import com.besson.tutorial.sound.ModSounds;
import com.besson.tutorial.worldgen.tree.ModTreeGrower;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(TutorialMod.MOD_ID);

    public static final DeferredBlock<Block> ICE_ETHER_BLOCK =
            registerBlocks("ice_ether_block", () -> new Block(BlockBehaviour.Properties.of().strength(1.5F, 6.0F)
                    .requiresCorrectToolForDrops().sound(ModSounds.BLOCK_SOUNDS).lightLevel(state -> 5)));
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

    public static final DeferredBlock<StrawberryCrop> STRAWBERRY_CROP =
            BLOCKS.register("strawberry_crop", () -> new StrawberryCrop(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));
    public static final DeferredBlock<CornCrop> CORN_CROP =
            BLOCKS.register("corn_crop", () -> new CornCrop(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));
    public static final DeferredBlock<Block> ORANGE_NIGHTSTAND =
            registerBlocks("orange_nightstand", () -> new Block(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).noOcclusion()));
    public static final DeferredBlock<SimpleOrangeClock> SIMPLE_ORANGE_CLOCK =
            registerBlocks("simple_orange_clock", () -> new SimpleOrangeClock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).noOcclusion()));
    
    public static final DeferredBlock<SofaBlock> SOFA = 
            registerBlocks("sofa", () -> new SofaBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).noOcclusion()));
    
    public static final DeferredBlock<LampBlock> LAMP_BLOCK =
            registerBlocks("lamp_block", () -> new LampBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F)
                    .noOcclusion().lightLevel(state -> state.getValue(LampBlock.LIT) ? 15 : 0)));
    
    public static final DeferredBlock<ModBedBlock> BED =
            registerBlocks("bed", () -> new ModBedBlock(DyeColor.BLACK, BlockBehaviour.Properties.of().strength(2.0F, 3.0F).noOcclusion()));
   
    public static final DeferredBlock<ModPillarBlock> PILLAR =
            registerBlocks("pillar", () -> new ModPillarBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).noOcclusion()));
    
    public static final DeferredBlock<ModFenceBlock> FENCE =
            registerBlocks("fence", () -> new ModFenceBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).noOcclusion()));
    
    public static final DeferredBlock<SewageFluidBlock> SEWAGE_BLOCK = BLOCKS.register("sewage",
            () -> new SewageFluidBlock(ModFluids.STILL_SEWAGE.get(), BlockBehaviour.Properties.of()
                    .replaceable().strength(100f).liquid()
                    .pushReaction(PushReaction.DESTROY).noLootTable()));
    
    public static final DeferredBlock<ModRotatedPillarBlock> ICE_ETHER_LOG =
            registerBlocks("ice_ether_log", () -> new ModRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<ModRotatedPillarBlock> ICE_ETHER_WOOD =
            registerBlocks("ice_ether_wood", () -> new ModRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));
    public static final DeferredBlock<ModRotatedPillarBlock> STRIPPED_ICE_ETHER_LOG =
            registerBlocks("stripped_ice_ether_log", () -> new ModRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));
    public static final DeferredBlock<ModRotatedPillarBlock> STRIPPED_ICE_ETHER_WOOD =
            registerBlocks("stripped_ice_ether_wood", () -> new ModRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));
    public static final DeferredBlock<ModLeavesBlock> ICE_ETHER_LEAVES =
            registerBlocks("ice_ether_leaves", () -> new ModLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
    public static final DeferredBlock<ModPlankBlock> ICE_ETHER_PLANKS =
            registerBlocks("ice_ether_planks", () -> new ModPlankBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    
    public static final DeferredBlock<SaplingBlock> ICE_ETHER_TREE_SAPLING =
            registerBlocks("ice_ether_tree_sapling", () -> new SaplingBlock(ModTreeGrower.ICE_ETHER_TREE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    
    public static final DeferredBlock<Block> SIMPLE_FLOWER =
            registerBlocks("simple_flower", () -> new FlowerBlock(MobEffects.NIGHT_VISION, 4f, BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)));
    public static final DeferredBlock<Block> POTTED_SIMPLE_FLOWER = BLOCKS.register("potted_simple_flower",
            () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SIMPLE_FLOWER, BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_DANDELION)));
    
    public static final WoodType ICE_ETHER_WOOD_TYPE = WoodType.register(new WoodType(TutorialMod.MOD_ID + ":ice_ether", BlockSetType.OAK));
    public static final DeferredBlock<Block> ICE_ETHER_SIGN = BLOCKS.register("ice_ether_sign",
            () -> new ModStandingSignBlock(ICE_ETHER_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN)));
    public static final DeferredBlock<Block> ICE_ETHER_HANGING_SIGN = BLOCKS.register("ice_ether_hanging_sign",
            () -> new ModHangingSignBlock(ICE_ETHER_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN)));
    public static final DeferredBlock<Block> ICE_ETHER_WALL_SIGN = BLOCKS.register("ice_ether_wall_sign",
            () -> new ModWallSignBlock(ICE_ETHER_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN)));
    public static final DeferredBlock<Block> ICE_ETHER_WALL_HANGING_SIGN = BLOCKS.register("ice_ether_wall_hanging_sign",
            () -> new ModWallHangingSignBlock(ICE_ETHER_WOOD_TYPE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN)));
    
    
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
