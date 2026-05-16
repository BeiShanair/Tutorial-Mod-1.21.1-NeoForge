package com.besson.tutorial.villager;

import com.besson.tutorial.TutorialMod;
import com.besson.tutorial.block.ModBlocks;
import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES = 
            DeferredRegister.create(BuiltInRegistries.POINT_OF_INTEREST_TYPE, TutorialMod.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = 
            DeferredRegister.create(BuiltInRegistries.VILLAGER_PROFESSION, TutorialMod.MOD_ID);
    
    public static final Holder<PoiType> ICE_ETHER_POI = POI_TYPES.register("ice_ether_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.ICE_ETHER_BLOCK.get().getStateDefinition().getPossibleStates()), 1, 1));
    public static final Holder<VillagerProfession> ICE_ETHER_MASTER = VILLAGER_PROFESSIONS.register("ice_ether_master",
            () -> new VillagerProfession("ice_ether_master",
                    p -> p.value() == ICE_ETHER_POI.value(), p -> p.value() == ICE_ETHER_POI.value(),
                    ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_ARMORER));
    
    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}
