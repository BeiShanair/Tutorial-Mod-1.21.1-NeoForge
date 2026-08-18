package com.besson.tutorial.entity;

import com.besson.tutorial.TutorialMod;
import com.besson.tutorial.entity.custom.ModBoat;
import com.besson.tutorial.entity.custom.ModChestBoat;
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
    
    public static final Supplier<EntityType<ModBoat>> MOD_BOAT = ENTITY_TYPES.register("mod_boat",
            () -> EntityType.Builder.<ModBoat>of(ModBoat::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f).build("mod_boat"));
    public static final Supplier<EntityType<ModChestBoat>> MOD_CHEST_BOAT = ENTITY_TYPES.register("mod_chest_boat",
            () -> EntityType.Builder.<ModChestBoat>of(ModChestBoat::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f).build("mod_chest_boat"));
    
    public static void register(IEventBus bus) {
        ENTITY_TYPES.register(bus);
    }
}
