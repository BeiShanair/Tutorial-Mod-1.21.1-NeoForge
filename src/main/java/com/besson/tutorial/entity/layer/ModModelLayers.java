package com.besson.tutorial.entity.layer;

import com.besson.tutorial.TutorialMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static final ModelLayerLocation ICE_ETHER_BOAT_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "boat/ice_ether"), "main");
    public static final ModelLayerLocation ICE_ETHER_CHEST_BOAT_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "chest_boat/ice_ether"), "main");
}
