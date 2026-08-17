package com.besson.tutorial.fluid;

import com.besson.tutorial.TutorialMod;
import com.besson.tutorial.block.ModBlocks;
import com.besson.tutorial.item.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(BuiltInRegistries.FLUID, TutorialMod.MOD_ID);
    
    public static final Supplier<FlowingFluid> STILL_SEWAGE = FLUIDS.register("sewage",
            SewageFluid.Source::new);
    public static final Supplier<FlowingFluid> FLOWING_SEWAGE = FLUIDS.register("flowing_sewage",
            SewageFluid.Flowing::new);
    public static final BaseFlowingFluid.Properties SEWAGE_PROPERTIES = new BaseFlowingFluid.Properties(
            ModFluidTypes.SEWAGE_FLUID_TYPE, STILL_SEWAGE, FLOWING_SEWAGE)
            .slopeFindDistance(2)
            .levelDecreasePerBlock(1)
            .block(ModBlocks.SEWAGE_BLOCK)
            .bucket(ModItems.SEWAGE_BUCKET);
    
    
    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
