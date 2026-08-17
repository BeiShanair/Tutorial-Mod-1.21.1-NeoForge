package com.besson.tutorial.worldgen;

import com.besson.tutorial.TutorialMod;
import com.besson.tutorial.block.ModBlocks;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

public class ModPlacementFeatures {
    public static final ResourceKey<PlacedFeature> ICE_ETHER_TREE_PLACED_KEY = createKey("ice_ether_tree_placed");
    public static final ResourceKey<PlacedFeature> SIMPLE_FLOWER_PLACED_KEY = createKey("simple_flower_placed");
    
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> holderGetter = context.lookup(Registries.CONFIGURED_FEATURE);
        PlacementUtils.register(context, ICE_ETHER_TREE_PLACED_KEY, holderGetter.getOrThrow(ModConfiguredFeatures.ICE_ETHER_TREE_KEY),
                VegetationPlacements.treePlacement(
                        PlacementUtils.countExtra(2, 0.1f, 2),
                        ModBlocks.ICE_ETHER_TREE_SAPLING.get()));
        
        PlacementUtils.register(context, SIMPLE_FLOWER_PLACED_KEY, holderGetter.getOrThrow(ModConfiguredFeatures.SIMPLE_FLOWER_KEY),
                RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
    }
    
    public static ResourceKey<PlacedFeature> createKey(String key) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, key));
    }
}
