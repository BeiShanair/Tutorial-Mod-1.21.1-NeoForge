package com.besson.tutorial.entity;

import com.besson.tutorial.TutorialMod;
import com.besson.tutorial.entity.custom.SeatEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, TutorialMod.MOD_ID);
    
    public static final Supplier<EntityType<SeatEntity>> SEAT = ENTITY_TYPES.register("seat",
            () -> EntityType.Builder.<SeatEntity>of(
                    (entityType, level) -> new SeatEntity(level), MobCategory.MISC)
                    .sized(0.0f, 0.0f)
                    .build("seat"));
    
    public static void register(IEventBus bus) {
        ENTITY_TYPES.register(bus);
    }
}
