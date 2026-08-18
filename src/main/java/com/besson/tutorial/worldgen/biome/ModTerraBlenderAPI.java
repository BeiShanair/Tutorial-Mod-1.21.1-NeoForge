package com.besson.tutorial.worldgen.biome;

import com.besson.tutorial.TutorialMod;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class ModTerraBlenderAPI {
    public static void registerRegions() {
        Regions.register(new ModOverworldRegion(
                ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "overworld"), 5));
    }
}
