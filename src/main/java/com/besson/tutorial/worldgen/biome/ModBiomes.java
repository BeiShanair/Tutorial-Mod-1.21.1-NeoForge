package com.besson.tutorial.worldgen.biome;

import com.besson.tutorial.TutorialMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModBiomes {
    public static final ResourceKey<Biome> DIAMOND_BIOME = ResourceKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "diamond_biome"));
    
    public static void globalOverWorldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }
    
    private static Biome diamondBiome(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        
        BiomeDefaultFeatures.farmAnimals(spawnSettings);
        BiomeDefaultFeatures.commonSpawns(spawnSettings);
        
        BiomeGenerationSettings.Builder generationSettings =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE),
                        context.lookup(Registries.CONFIGURED_CARVER));
        
        globalOverWorldGeneration(generationSettings);
        BiomeDefaultFeatures.addMossyStoneBlock(generationSettings);
        BiomeDefaultFeatures.addDefaultOres(generationSettings);
        BiomeDefaultFeatures.addExtraGold(generationSettings);
        
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_PLAINS);
        BiomeDefaultFeatures.addForestFlowers(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);
        
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.5f)
                .temperature(0.7f)
                .generationSettings(generationSettings.build())
                .mobSpawnSettings(spawnSettings.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0xe82e3b)
                        .waterFogColor(0xbf1b26)
                        .skyColor(0x78a7ff)
                        .grassColorOverride(0x7c9b45)
                        .foliageColorOverride(0x7c9b45)
                        .fogColor(0xc0d8ff)
                        .build()).build();
    }
    
    public static void bootstrap(BootstrapContext<Biome> context) {
        context.register(DIAMOND_BIOME, diamondBiome(context));
    }
}
