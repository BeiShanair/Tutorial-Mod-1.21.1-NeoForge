package com.besson.tutorial.worldgen;

import com.besson.tutorial.TutorialMod;
import com.besson.tutorial.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> ICE_ETHER_TREE_KEY = createKey("ice_ether_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SIMPLE_FLOWER_KEY = createKey("simple_flower");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ICE_ETHER_ORE_KEY = createKey("ice_ether_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_ICE_ETHER_ORE_KEY = createKey("nether_ice_ether_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_ICE_ETHER_ORE_KEY = createKey("end_ice_ether_ore");
    
    
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        FeatureUtils.register(context, ICE_ETHER_TREE_KEY, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.ICE_ETHER_LOG.get()),
                        new StraightTrunkPlacer(4, 2, 1),
                        BlockStateProvider.simple(ModBlocks.ICE_ETHER_LEAVES.get()),
                        new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), 3),
                        new TwoLayersFeatureSize(1, 0, 1)
                ).build());
        
        FeatureUtils.register(context, SIMPLE_FLOWER_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(32, 4, 3,
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.SIMPLE_FLOWER.get())))));

        RuleTest stoneReplace = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplace = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherReplace = new TagMatchTest(BlockTags.BASE_STONE_NETHER);
        RuleTest endReplace = new BlockMatchTest(Blocks.END_STONE);

        List<OreConfiguration.TargetBlockState> overworldTargets = List.of(
                OreConfiguration.target(stoneReplace, ModBlocks.ICE_ETHER_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplace, ModBlocks.ICE_ETHER_ORE.get().defaultBlockState()));
        List<OreConfiguration.TargetBlockState> netherTargets = List.of(
                OreConfiguration.target(netherReplace, ModBlocks.ICE_ETHER_ORE.get().defaultBlockState()));
        List<OreConfiguration.TargetBlockState> endTargets = List.of(
                OreConfiguration.target(endReplace, ModBlocks.ICE_ETHER_ORE.get().defaultBlockState()));
        
        FeatureUtils.register(context, ICE_ETHER_ORE_KEY, Feature.ORE,
                new OreConfiguration(overworldTargets, 9));
        FeatureUtils.register(context, NETHER_ICE_ETHER_ORE_KEY, Feature.ORE,
                new OreConfiguration(netherTargets, 9));
        FeatureUtils.register(context, END_ICE_ETHER_ORE_KEY, Feature.ORE,
                new OreConfiguration(endTargets, 9));
    }
    
    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, name));
    }
}
