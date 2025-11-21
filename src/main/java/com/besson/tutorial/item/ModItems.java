package com.besson.tutorial.item;

import com.besson.tutorial.TutorialMod;
import com.besson.tutorial.item.custom.ModFuelItem;
import com.besson.tutorial.item.custom.ProspectorItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(TutorialMod.MOD_ID);

    public static final DeferredItem<Item> ICE_ETHER =
            ITEMS.register("ice_ether", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_ICE_ETHER =
            ITEMS.register("raw_ice_ether", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CARDBOARD =
            ITEMS.register("material/cardboard", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CORN =
            ITEMS.register("corn", () -> new Item(new Item.Properties().food(ModFoods.CORN)));
    public static final DeferredItem<Item> STRAWBERRY =
            ITEMS.register("strawberry", () -> new Item(new Item.Properties().food(ModFoods.STRAWBERRY)));
    public static final DeferredItem<Item> CHEESE =
            ITEMS.register("cheese", () -> new Item(new Item.Properties().food(ModFoods.CHEESE)));

    public static final DeferredItem<Item> ANTHRACITE =
            ITEMS.register("anthracite", () -> new ModFuelItem(new Item.Properties(), 1600));
    public static final DeferredItem<Item> ANTHRACITE2 =
            ITEMS.register("anthracite2", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> PROSPECTOR =
            ITEMS.register("prospector", () -> new ProspectorItem(new Item.Properties().durability(127)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
