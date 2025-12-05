package com.besson.tutorial.datagen;

import com.besson.tutorial.TutorialMod;
import com.besson.tutorial.block.ModBlocks;
import com.besson.tutorial.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModItemModelsProvider extends ItemModelProvider {
    public ModItemModelsProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TutorialMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.ICE_ETHER.get());
        basicItem(ModItems.RAW_ICE_ETHER.get());
//        basicItem(ModItems.CARDBOARD.get());

        basicItem(ModItems.CORN.get());
        basicItem(ModItems.STRAWBERRY.get());
        basicItem(ModItems.CHEESE.get());

        basicItem(ModItems.ANTHRACITE.get());
        basicItem(ModItems.ANTHRACITE2.get());

        basicItem(ModItems.PROSPECTOR.get());

        buttonItem(ModBlocks.ICE_ETHER_BUTTON, ModBlocks.ICE_ETHER_BLOCK);
        fenceItem(ModBlocks.ICE_ETHER_FENCE, ModBlocks.ICE_ETHER_BLOCK);
        wallItem(ModBlocks.ICE_ETHER_WALL, ModBlocks.ICE_ETHER_BLOCK);

        basicItem(ModBlocks.ICE_ETHER_DOOR.asItem());

        handheldItem(ModItems.FIRE_ETHER_SWORD.get());
        handheldItem(ModItems.FIRE_ETHER_SHOVEL.get());
        handheldItem(ModItems.FIRE_ETHER_AXE.get());
        handheldItem(ModItems.FIRE_ETHER_PICKAXE.get());
        handheldItem(ModItems.FIRE_ETHER_HOE.get());

        handheldItem(ModItems.PICKAXE_AXE_ITEM.get());

        basicItem(ModItems.ICE_ETHER_HELMET.get());
        basicItem(ModItems.ICE_ETHER_CHESTPLATE.get());
        basicItem(ModItems.ICE_ETHER_LEGGINGS.get());
        basicItem(ModItems.ICE_ETHER_BOOTS.get());
    }

    private void buttonItem(DeferredBlock<?> block, DeferredBlock<Block> base) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture", ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID,
                        "block/" + base.getId().getPath()));
    }
    private void fenceItem(DeferredBlock<?> block, DeferredBlock<Block> base) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture", ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID,
                        "block/" + base.getId().getPath()));
    }
    private void wallItem(DeferredBlock<?> block, DeferredBlock<Block> base) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall", ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID,
                        "block/" + base.getId().getPath()));
    }
}
