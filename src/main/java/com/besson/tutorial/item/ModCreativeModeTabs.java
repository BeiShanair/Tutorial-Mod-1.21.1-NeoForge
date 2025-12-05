package com.besson.tutorial.item;

import com.besson.tutorial.TutorialMod;
import com.besson.tutorial.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TutorialMod.MOD_ID);

    public static final Supplier<CreativeModeTab> TUTORIAL_TAB =
            CREATIVE_MODE_TABS.register("tutorial_tab", () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.ICE_ETHER.get()))
                    .title(Component.translatable("itemGroup.tutorial_tab"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.ICE_ETHER);
                        output.accept(ModItems.RAW_ICE_ETHER);
                        output.accept(ModBlocks.ICE_ETHER_BLOCK);
                        output.accept(ModBlocks.RAW_ICE_ETHER_BLOCK);
                        output.accept(ModBlocks.ICE_ETHER_ORE);

                        output.accept(ModItems.CORN);
                        output.accept(ModItems.STRAWBERRY);
                        output.accept(ModItems.CHEESE);

                        output.accept(ModItems.ANTHRACITE);
                        output.accept(ModItems.ANTHRACITE2);

                        output.accept(ModItems.PROSPECTOR);

                        output.accept(ModBlocks.ICE_ETHER_STAIRS);
                        output.accept(ModBlocks.ICE_ETHER_SLAB);
                        output.accept(ModBlocks.ICE_ETHER_BUTTON);
                        output.accept(ModBlocks.ICE_ETHER_PRESSURE_PLATE);
                        output.accept(ModBlocks.ICE_ETHER_FENCE);
                        output.accept(ModBlocks.ICE_ETHER_FENCE_GATE);
                        output.accept(ModBlocks.ICE_ETHER_WALL);
                        output.accept(ModBlocks.ICE_ETHER_DOOR);
                        output.accept(ModBlocks.ICE_ETHER_TRAPDOOR);

                        output.accept(ModItems.FIRE_ETHER_SWORD);
                        output.accept(ModItems.FIRE_ETHER_SHOVEL);
                        output.accept(ModItems.FIRE_ETHER_AXE);
                        output.accept(ModItems.FIRE_ETHER_PICKAXE);
                        output.accept(ModItems.FIRE_ETHER_HOE);
                    }).build());

    public static final Supplier<CreativeModeTab> MATERIAL =
            CREATIVE_MODE_TABS.register("material", () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.CARDBOARD.get()))
                    .title(Component.translatable("itemGroup.material"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.CARDBOARD);
                    }).withTabsBefore(ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "tutorial_tab"))
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
