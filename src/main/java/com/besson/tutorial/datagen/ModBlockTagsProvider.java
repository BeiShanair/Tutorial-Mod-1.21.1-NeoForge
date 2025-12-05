package com.besson.tutorial.datagen;

import com.besson.tutorial.TutorialMod;
import com.besson.tutorial.block.ModBlocks;
import com.besson.tutorial.tag.ModBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TutorialMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.ICE_ETHER_BLOCK.get())
                .add(ModBlocks.ICE_ETHER_ORE.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.ICE_ETHER_ORE.get());

        tag(ModBlockTags.ORE_TAGS)
                .add(ModBlocks.ICE_ETHER_ORE.get())
                .addTag(BlockTags.COAL_ORES)
                .addTag(BlockTags.IRON_ORES)
                .addTag(BlockTags.GOLD_ORES)
                .addTag(BlockTags.DIAMOND_ORES)
                .addTag(BlockTags.EMERALD_ORES)
                .addTag(BlockTags.LAPIS_ORES)
                .addTag(BlockTags.REDSTONE_ORES);

        tag(BlockTags.FENCES)
                .add(ModBlocks.ICE_ETHER_FENCE.get());
        tag(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.ICE_ETHER_FENCE.get());
        tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.ICE_ETHER_FENCE_GATE.get());
        tag(BlockTags.WALLS)
                .add(ModBlocks.ICE_ETHER_WALL.get());
    }
}
