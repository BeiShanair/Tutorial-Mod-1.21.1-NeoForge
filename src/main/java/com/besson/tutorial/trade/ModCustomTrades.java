package com.besson.tutorial.trade;

import com.besson.tutorial.TutorialMod;
import com.besson.tutorial.item.ModItems;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

import java.util.List;

@EventBusSubscriber(modid = TutorialMod.MOD_ID)
public class ModCustomTrades {
    @SubscribeEvent
    public static void addTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.FARMER) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            trades.get(1).add((trader, random) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 2),
                    new ItemStack(ModItems.STRAWBERRY.get(), 15),
                    10, 2, 0.5F
            ));
            trades.get(2).add((trader, random) -> new MerchantOffer(
                    new ItemCost(ModItems.CORN.get(), 15),
                    new ItemStack(Items.EMERALD, 5),
                    10, 10, 0.5F
            ));
        }

        if (event.getType() == VillagerProfession.ARMORER) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            trades.get(2).add((trader, random) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 10),
                    new ItemStack(ModItems.ICE_ETHER_HELMET.get()),
                    5, 10, 0.2F
            ));
        }
    }
}
