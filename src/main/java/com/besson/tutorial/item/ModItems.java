package com.besson.tutorial.item;

import com.besson.tutorial.TutorialMod;
import com.besson.tutorial.item.custom.CustomArmorItem;
import com.besson.tutorial.item.custom.ModFuelItem;
import com.besson.tutorial.item.custom.PickaxeAxeItem;
import com.besson.tutorial.item.custom.ProspectorItem;
import net.minecraft.world.item.*;
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

    public static final DeferredItem<Item> FIRE_ETHER =
            ITEMS.register("fire_ether", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FIRE_ETHER_SWORD = ITEMS.register("fire_ether_sword",
            () -> new SwordItem(ModToolTiers.FIRE_ETHER, new Item.Properties().attributes(SwordItem.createAttributes(ModToolTiers.FIRE_ETHER, 2, 3))));
    public static final DeferredItem<Item> FIRE_ETHER_PICKAXE = ITEMS.register("fire_ether_pickaxe",
            () -> new PickaxeItem(ModToolTiers.FIRE_ETHER, new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.FIRE_ETHER, 1, 2))));
    public static final DeferredItem<Item> FIRE_ETHER_SHOVEL = ITEMS.register("fire_ether_shovel",
            () -> new ShovelItem(ModToolTiers.FIRE_ETHER, new Item.Properties().attributes(ShovelItem.createAttributes(ModToolTiers.FIRE_ETHER, 2, 3))));
    public static final DeferredItem<Item> FIRE_ETHER_AXE = ITEMS.register("fire_ether_axe",
            () -> new AxeItem(ModToolTiers.FIRE_ETHER, new Item.Properties().attributes(AxeItem.createAttributes(ModToolTiers.FIRE_ETHER, 2, 3))));
    public static final DeferredItem<Item> FIRE_ETHER_HOE = ITEMS.register("fire_ether_hoe",
            () -> new HoeItem(ModToolTiers.FIRE_ETHER, new Item.Properties().attributes(HoeItem.createAttributes(ModToolTiers.FIRE_ETHER, 2, 3))));

    public static final DeferredItem<Item> PICKAXE_AXE_ITEM = ITEMS.register("pickaxe_axe_item",
            () -> new PickaxeAxeItem(ModToolTiers.FIRE_ETHER, new Item.Properties().attributes(AxeItem.createAttributes(ModToolTiers.FIRE_ETHER, 6.0F, -2.8F)).fireResistant()));

    public static final DeferredItem<Item> ICE_ETHER_HELMET = ITEMS.register("ice_ether_helmet",
            () -> new CustomArmorItem(ModArmorMaterials.ICE_ETHER, ArmorItem.Type.HELMET,
            new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(37))));
    public static final DeferredItem<Item> ICE_ETHER_CHESTPLATE = ITEMS.register("ice_ether_chestplate",
            () -> new ArmorItem(ModArmorMaterials.ICE_ETHER, ArmorItem.Type.CHESTPLATE,
            new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
    public static final DeferredItem<Item> ICE_ETHER_LEGGINGS = ITEMS.register("ice_ether_leggings",
            () -> new ArmorItem(ModArmorMaterials.ICE_ETHER, ArmorItem.Type.LEGGINGS,
            new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
    public static final DeferredItem<Item> ICE_ETHER_BOOTS = ITEMS.register("ice_ether_boots",
            () -> new ArmorItem(ModArmorMaterials.ICE_ETHER, ArmorItem.Type.BOOTS,
            new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(37))));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
