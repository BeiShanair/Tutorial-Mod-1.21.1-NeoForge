package com.besson.tutorial.worldgen.tree;

import com.besson.tutorial.TutorialMod;
import com.besson.tutorial.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrower {
    public static final TreeGrower ICE_ETHER_TREE = new TreeGrower(TutorialMod.MOD_ID + ":ice_ether_tree",
            Optional.empty(), Optional.of(ModConfiguredFeatures.ICE_ETHER_TREE_KEY), Optional.empty());
}
