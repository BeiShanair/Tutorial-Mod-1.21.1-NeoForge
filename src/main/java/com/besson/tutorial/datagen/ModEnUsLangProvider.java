package com.besson.tutorial.datagen;

import com.besson.tutorial.TutorialMod;
import com.besson.tutorial.block.ModBlocks;
import com.besson.tutorial.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModEnUsLangProvider extends LanguageProvider {
    public ModEnUsLangProvider(PackOutput output) {
        super(output, TutorialMod.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(ModItems.ICE_ETHER.get(), "Ice Ether");
        add(ModItems.RAW_ICE_ETHER.get(), "Raw Ice Ether");
        add(ModItems.CARDBOARD.get(), "CardBoard");

        add(ModBlocks.ICE_ETHER_BLOCK.get(), "Ice Ether Block");
        add(ModBlocks.RAW_ICE_ETHER_BLOCK.get(), "Raw Ice Ether Block");
        add(ModBlocks.ICE_ETHER_ORE.get(), "Ice Ether Ore");

        add(ModItems.CORN.get(), "Corn");
        add(ModItems.STRAWBERRY.get(), "Strawberry");
        add(ModItems.CHEESE.get(), "Cheese");

        add(ModItems.ANTHRACITE.get(), "Anthracite");
        add(ModItems.ANTHRACITE2.get(), "Anthracite 2");

        add(ModItems.PROSPECTOR.get(), "Prospector");

        add(ModBlocks.ICE_ETHER_STAIRS.get(), "Ice Ether Stairs");
        add(ModBlocks.ICE_ETHER_SLAB.get(), "Ice Ether Slabs");
        add(ModBlocks.ICE_ETHER_BUTTON.get(), "Ice Ether Button");
        add(ModBlocks.ICE_ETHER_PRESSURE_PLATE.get(), "Ice Ether Pressure Plate");
        add(ModBlocks.ICE_ETHER_FENCE.get(), "Ice Ether Fence");
        add(ModBlocks.ICE_ETHER_FENCE_GATE.get(), "Ice Ether Fence Gate");
        add(ModBlocks.ICE_ETHER_WALL.get(), "Ice Ether Wall");
        add(ModBlocks.ICE_ETHER_DOOR.get(), "Ice Ether Door");
        add(ModBlocks.ICE_ETHER_TRAPDOOR.get(), "Ice Ether Trapdoor");

        add("itemGroup.tutorial_tab", "Tutorial Tab");
        add("itemGroup.material", "Material");
    }
}
