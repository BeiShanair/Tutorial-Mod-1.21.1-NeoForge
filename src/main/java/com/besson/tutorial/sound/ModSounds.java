package com.besson.tutorial.sound;

import com.besson.tutorial.TutorialMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.util.DeferredSoundType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = 
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, TutorialMod.MOD_ID);
    
    public static final Supplier<SoundEvent> FOUND_ORE = registerSound("found_ore");
    public static final Supplier<SoundEvent> BLOCK_BREAK = registerSound("block_break");
    public static final Supplier<SoundEvent> BLOCK_PLACE = registerSound("block_place");
    public static final Supplier<SoundEvent> BLOCK_HIT = registerSound("block_hit");
    public static final Supplier<SoundEvent> BLOCK_FALL = registerSound("block_fall");
    public static final Supplier<SoundEvent> BLOCK_STEP = registerSound("block_step");
    
    public static final DeferredSoundType BLOCK_SOUNDS = new DeferredSoundType(1f, 1f,
            BLOCK_BREAK, BLOCK_STEP, BLOCK_PLACE, BLOCK_HIT, BLOCK_FALL);
    
    public static final Supplier<SoundEvent> A_MOMENT_APART = registerSound("a_moment_apart");
    public static final ResourceKey<JukeboxSong> A_MOMENT_APART_SONG = creatSong("a_moment_apart");
    
    private static ResourceKey<JukeboxSong> creatSong(String name) {
        return ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, name));
    }
    private static Supplier<SoundEvent> registerSound(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }
    
    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
