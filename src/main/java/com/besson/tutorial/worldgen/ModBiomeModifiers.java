package com.besson.tutorial.worldgen;

import com.besson.tutorial.TutorialMod;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_ICE_ETHER_TREE = registerKey("add_ice_ether_tree");
    public static final ResourceKey<BiomeModifier> ADD_SIMPLE_FLOWER = registerKey("add_simple_flower");
    public static final ResourceKey<BiomeModifier> ADD_ICE_ETHER_ORE = registerKey("add_ice_ether_ore");
    public static final ResourceKey<BiomeModifier> ADD_NETHER_ICE_ETHER_ORE = registerKey("add_nether_ice_ether_ore");
    public static final ResourceKey<BiomeModifier> ADD_END_ICE_ETHER_ORE = registerKey("add_end_ice_ether_ore");
    
    
    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        HolderGetter<PlacedFeature> placedFeature = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        
        context.register(ADD_ICE_ETHER_TREE, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.PLAINS), biomes.getOrThrow(Biomes.FOREST)),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacementFeatures.ICE_ETHER_TREE_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
        
        context.register(ADD_SIMPLE_FLOWER, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_PLAINS),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacementFeatures.SIMPLE_FLOWER_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
        
        context.register(ADD_ICE_ETHER_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacementFeatures.ICE_ETHER_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_NETHER_ICE_ETHER_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_NETHER),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacementFeatures.NETHER_ICE_ETHER_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_END_ICE_ETHER_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_END),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacementFeatures.END_ICE_ETHER_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
    }
    
    public static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, name));
    }
}
