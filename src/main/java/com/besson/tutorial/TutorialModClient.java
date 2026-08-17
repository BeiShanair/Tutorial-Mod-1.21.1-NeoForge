package com.besson.tutorial;

import com.besson.tutorial.blockentity.ModBlockEntities;
import com.besson.tutorial.entity.ModEntities;
import com.besson.tutorial.fluid.BaseFluidType;
import com.besson.tutorial.fluid.ModFluidTypes;
import com.besson.tutorial.fluid.ModFluids;
import com.besson.tutorial.renderer.SeatEntityRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = TutorialMod.MOD_ID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = TutorialMod.MOD_ID, value = Dist.CLIENT)
public class TutorialModClient {
    public TutorialModClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        TutorialMod.LOGGER.info("HELLO FROM CLIENT SETUP");
        TutorialMod.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_SEWAGE.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.STILL_SEWAGE.get(), RenderType.translucent());
        });
    }
    
    @SubscribeEvent
    public static void onClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerFluidType(((BaseFluidType) ModFluidTypes.SEWAGE_FLUID_TYPE.get()).getClientExtensions(),
                ModFluidTypes.SEWAGE_FLUID_TYPE.get());
    }
    
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.SEAT.get(), SeatEntityRenderer::new);
        
        event.registerBlockEntityRenderer(ModBlockEntities.MOD_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
    }
}
