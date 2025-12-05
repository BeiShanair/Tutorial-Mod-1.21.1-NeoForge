package com.besson.tutorial.datagen;

import com.besson.tutorial.block.ModBlocks;
import com.besson.tutorial.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class ModBlockLootTablesProvider extends BlockLootSubProvider {
    public ModBlockLootTablesProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.ICE_ETHER_BLOCK.get());
        dropSelf(ModBlocks.RAW_ICE_ETHER_BLOCK.get());
        add(ModBlocks.ICE_ETHER_ORE.get(),
                block -> createCopperOreLikeDrops(ModBlocks.ICE_ETHER_ORE.get(), ModItems.RAW_ICE_ETHER.get(), 2.0F, 6.0F));

        dropSelf(ModBlocks.ICE_ETHER_STAIRS.get());
        add(ModBlocks.ICE_ETHER_SLAB.get(), block -> createSlabItemTable(ModBlocks.ICE_ETHER_SLAB.get()));
        dropSelf(ModBlocks.ICE_ETHER_BUTTON.get());
        dropSelf(ModBlocks.ICE_ETHER_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.ICE_ETHER_FENCE.get());
        dropSelf(ModBlocks.ICE_ETHER_FENCE_GATE.get());
        dropSelf(ModBlocks.ICE_ETHER_WALL.get());
        add(ModBlocks.ICE_ETHER_DOOR.get(), block -> createDoorTable(ModBlocks.ICE_ETHER_DOOR.get()));
        dropSelf(ModBlocks.ICE_ETHER_TRAPDOOR.get());
    }

    protected LootTable.Builder createCopperOreLikeDrops(Block block, Item item, float min, float max) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                block,
                (LootPoolEntryContainer.Builder<?>)this.applyExplosionDecay(
                        block,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))
                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
