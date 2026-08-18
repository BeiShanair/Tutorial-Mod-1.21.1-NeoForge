package com.besson.tutorial.renderer;

import com.besson.tutorial.TutorialMod;
import com.besson.tutorial.entity.custom.ModBoat;
import com.besson.tutorial.entity.custom.ModChestBoat;
import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;

import java.util.Map;
import java.util.stream.Stream;

public class ModBoatRenderer extends BoatRenderer {
    private final Map<ModBoat.Type, Pair<ResourceLocation, ListModel<Boat>>> boatResource;
    public ModBoatRenderer(EntityRendererProvider.Context context, boolean chestBoat) {
        super(context, chestBoat);
        this.boatResource = Stream.of(ModBoat.Type.values()).collect(ImmutableMap.toImmutableMap(type -> type,
                type -> Pair.of(ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, getTextureLocation(type, chestBoat)),
                        this.creatBoatModel(context, type, chestBoat))));
    }
    
    private static String getTextureLocation(ModBoat.Type boatType, boolean chest) {
        return chest ? "textures/entity/chest_boat/" + boatType.getName() + ".png" : "textures/entity/boat/" + boatType.getName() + ".png";
    }
    
    private ListModel<Boat> creatBoatModel(EntityRendererProvider.Context context, ModBoat.Type boatType, boolean chest) {
        ModelLayerLocation modelLayerLocation = chest ? createChestBoat(boatType) : createBoat(boatType);
        ModelPart modelPart = context.bakeLayer(modelLayerLocation);
        return chest ? new ChestBoatModel(modelPart) : new BoatModel(modelPart);
    }
    
    public static ModelLayerLocation createBoat(ModBoat.Type boatType) {
        return creatLocation("boat/" + boatType.getName(), "main");
    }
    
    public static ModelLayerLocation createChestBoat(ModBoat.Type boatType) {
        return creatLocation("chest_boat/" + boatType.getName(), "main");
    }
    
    private static ModelLayerLocation creatLocation(String path, String model) {
        return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, path), model);
    }
    
    public Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(Boat boat) {
        if (boat instanceof ModBoat modBoat) {
            return this.boatResource.get(modBoat.getModVariants());
        } else if (boat instanceof ModChestBoat modChestBoat) {
            return this.boatResource.get(modChestBoat.getModVariants());
        } else {
            return null;
        }
    }
}
