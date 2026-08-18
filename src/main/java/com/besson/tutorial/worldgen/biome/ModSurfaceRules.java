package com.besson.tutorial.worldgen.biome;

import com.besson.tutorial.block.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class ModSurfaceRules {
    public static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    public static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    public static final SurfaceRules.RuleSource DIAMOND_BLOCK = makeStateRule(Blocks.DIAMOND_BLOCK);
    public static final SurfaceRules.RuleSource ICE_ETHER_BLOCK = makeStateRule(ModBlocks.ICE_ETHER_BLOCK.get());
    
    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);
        
        SurfaceRules.RuleSource grass = SurfaceRules.ifTrue(
                SurfaceRules.abovePreliminarySurface(),
                SurfaceRules.ifTrue(isAtOrAboveWaterLevel, GRASS_BLOCK));
        
        SurfaceRules.RuleSource diamond = SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiomes.DIAMOND_BIOME),
                SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR, DIAMOND_BLOCK));
        
        SurfaceRules.RuleSource dirt = SurfaceRules.ifTrue(
                SurfaceRules.DEEP_UNDER_FLOOR,
                DIRT);
        
        SurfaceRules.RuleSource iceEther = SurfaceRules.ifTrue(SurfaceRules.UNDER_CEILING, ICE_ETHER_BLOCK);
        
        return SurfaceRules.sequence(
                grass,
                diamond,
                dirt,
                iceEther
        );
    }
    
    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
