package com.besson.tutorial.fluid;

import com.besson.tutorial.TutorialMod;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.joml.Vector3f;

import java.util.function.Supplier;

public class ModFluidTypes {
    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, TutorialMod.MOD_ID);
    
    public static final ResourceLocation WATER_STILL_RL = ResourceLocation.parse("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = ResourceLocation.parse("block/water_flow");
    public static final ResourceLocation WATER_OVERLAY_RL = ResourceLocation.parse("block/water_overlay");
    
    public static final Supplier<FluidType> SEWAGE_FLUID_TYPE = FLUID_TYPES.register("sewage",
            () -> new BaseFluidType(FluidType.Properties.create().density(15).viscosity(5),
                    WATER_STILL_RL, WATER_FLOWING_RL, WATER_OVERLAY_RL,
                    0xFF2F4F4F,
                    new Vector3f(47f / 255f, 79f / 255f, 79f / 255f)));
    
    
    
    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
