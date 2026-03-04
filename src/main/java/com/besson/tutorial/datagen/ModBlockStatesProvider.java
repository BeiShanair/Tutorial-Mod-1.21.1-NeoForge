package com.besson.tutorial.datagen;

import com.besson.tutorial.TutorialMod;
import com.besson.tutorial.block.ModBlocks;
import com.besson.tutorial.block.custom.StrawberryCrop;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Function;

public class ModBlockStatesProvider extends BlockStateProvider {
    public ModBlockStatesProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TutorialMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithItem(ModBlocks.ICE_ETHER_BLOCK.get(), cubeAll(ModBlocks.ICE_ETHER_BLOCK.get()));
        simpleBlockWithItem(ModBlocks.RAW_ICE_ETHER_BLOCK.get(), cubeAll(ModBlocks.RAW_ICE_ETHER_BLOCK.get()));
        simpleBlockWithItem(ModBlocks.ICE_ETHER_ORE.get(), cubeAll(ModBlocks.ICE_ETHER_ORE.get()));

        stairsBlock(ModBlocks.ICE_ETHER_STAIRS.get(), blockTexture(ModBlocks.ICE_ETHER_BLOCK.get()));
        slabBlock(ModBlocks.ICE_ETHER_SLAB.get(), blockTexture(ModBlocks.ICE_ETHER_BLOCK.get()), blockTexture(ModBlocks.ICE_ETHER_BLOCK.get()));
        buttonBlock(ModBlocks.ICE_ETHER_BUTTON.get(), blockTexture(ModBlocks.ICE_ETHER_BLOCK.get()));
        pressurePlateBlock(ModBlocks.ICE_ETHER_PRESSURE_PLATE.get(), blockTexture(ModBlocks.ICE_ETHER_BLOCK.get()));
        fenceBlock(ModBlocks.ICE_ETHER_FENCE.get(), blockTexture(ModBlocks.ICE_ETHER_BLOCK.get()));
        fenceGateBlock(ModBlocks.ICE_ETHER_FENCE_GATE.get(), blockTexture(ModBlocks.ICE_ETHER_BLOCK.get()));
        wallBlock(ModBlocks.ICE_ETHER_WALL.get(), blockTexture(ModBlocks.ICE_ETHER_BLOCK.get()));

        doorBlockWithRenderType(ModBlocks.ICE_ETHER_DOOR.get(), modLoc("block/ice_ether_door_bottom"), modLoc("block/ice_ether_door_top"), "cutout");
        trapdoorBlockWithRenderType(ModBlocks.ICE_ETHER_TRAPDOOR.get(), modLoc("block/ice_ether_trapdoor"), true, "cutout");

        blockItem(ModBlocks.ICE_ETHER_STAIRS);
        blockItem(ModBlocks.ICE_ETHER_SLAB);
        blockItem(ModBlocks.ICE_ETHER_PRESSURE_PLATE);
        blockItem(ModBlocks.ICE_ETHER_FENCE_GATE);
        blockItem(ModBlocks.ICE_ETHER_TRAPDOOR, "_bottom");

        crop(ModBlocks.STRAWBERRY_CROP.get(), "strawberry_crop_stage", StrawberryCrop.AGE);
    }

    public void crop(CropBlock block, String name, IntegerProperty property) {
        Function<BlockState, ConfiguredModel[]> function = state ->
                cropStates(state, name, property);

        getVariantBuilder(block).forAllStates(function);
    }

    public void crossCrop(CropBlock block, String name, IntegerProperty property) {
        Function<BlockState, ConfiguredModel[]> function = state ->
                crossStates(state, name, property);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] cropStates(BlockState state, String modelName, IntegerProperty property) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(property),
                ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "block/" + modelName + state.getValue(property))).renderType("cutout"));

        return models;
    }

    private ConfiguredModel[] crossStates(BlockState state, String modelName, IntegerProperty property) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().cross(modelName + state.getValue(property),
                ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "block/" + modelName + state.getValue(property))).renderType("cutout"));

        return models;
    }

    private void blockItem(DeferredBlock<?> block) {
        simpleBlockItem(block.get(), new ModelFile.UncheckedModelFile(TutorialMod.MOD_ID + ":block/" + block.getId().getPath()));
    }
    private void blockItem(DeferredBlock<?> block, String append) {
        simpleBlockItem(block.get(), new ModelFile.UncheckedModelFile(TutorialMod.MOD_ID + ":block/" + block.getId().getPath() + append));
    }
}
