package com.ninni.etcetera;

import com.ninni.etcetera.client.ClientConfigSetup;
import com.ninni.etcetera.client.TidalHelmetHud;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import static com.ninni.etcetera.registry.EtceteraItems.*;

@Mod(Constants.MOD_ID)
public class Etcetera {
    public static IEventBus MOD_EVENT_BUS;

    public Etcetera(IEventBus modEventBus, ModContainer modContainer) {
        MOD_EVENT_BUS = modEventBus;
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::buildCreativeTabs);

        if (FMLEnvironment.dist.isClient()) {
            ClientConfigSetup.register(modContainer);
            modEventBus.addListener(this::registerGuiLayers);
        }
    }

    private void registerGuiLayers(RegisterGuiLayersEvent event) {
        event.registerAbove(
                VanillaGuiLayers.FOOD_LEVEL,
                ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "tidal_eye"),
                (graphics, deltaTracker) -> new TidalHelmetHud().render(graphics, deltaTracker.getGameTimeDeltaTicks())
        );
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        CommonClass.init();
    }

    private void buildCreativeTabs(BuildCreativeModeTabContentsEvent event) {
        var tabKey = event.getTabKey();
        if (tabKey == CreativeModeTabs.BUILDING_BLOCKS) {
            addAfter(event, Items.SMOOTH_QUARTZ_SLAB, BISMUTH_BLOCK, BISMUTH_BARS);
            addAfter(event, Items.SMOOTH_STONE_SLAB, LEVELED_STONE, CRUMBLING_STONE, WAXED_CRUMBLING_STONE, LEVELED_STONE_STAIRS, LEVELED_STONE_SLAB);
            addAfter(event, Items.MUD_BRICK_WALL, RUBBER_BLOCK, RUBBER_BUTTON);
        } else if (tabKey == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            addAfter(event, Items.SOUL_LANTERN, LIGHT_BULB, TINTED_LIGHT_BULB);
            addAfter(event, Items.END_ROD, SQUID_LAMP);
            addAfter(event, Items.SEA_LANTERN, IRIDESCENT_LANTERN);
            addAfter(event, Items.JUKEBOX, DRUM);
            addAfter(event, Items.SCAFFOLDING, FRAME);
            addAfter(event, Items.DECORATED_POT, TERRACOTTA_VASE);
            addAfter(event, Items.GLOW_ITEM_FRAME, ITEM_STAND, GLOW_ITEM_STAND);
            addAfter(event, Items.ENDER_CHEST, PRICKLY_CAN);
            addAfter(event, Items.SUSPICIOUS_GRAVEL, CRUMBLING_STONE, WAXED_CRUMBLING_STONE);
            addAfter(event, Items.BELL, DREAM_CATCHER);
            addAfter(event, Items.CAULDRON, COPPER_TAP);
            addBefore(event, Items.SKELETON_SKULL, RUBBER_CHICKEN);
        } else if (tabKey == CreativeModeTabs.REDSTONE_BLOCKS) {
            addAfter(event, Items.COMPARATOR, REDSTONE_WIRES, REDSTONE_WIRE_TORCH, REDSTONE_WIRE_REPEATER, REDSTONE_WIRE_COMPARATOR);
            addAfter(event, Items.HEAVY_WEIGHTED_PRESSURE_PLATE, DRUM);
            addAfter(event, Items.WHITE_WOOL, DICE);
            addAfter(event, Items.BARREL, PRICKLY_CAN);
            addAfter(event, Items.STONE_BUTTON, RUBBER_BUTTON);
        } else if (tabKey == CreativeModeTabs.SPAWN_EGGS) {
            addAfter(event, Items.CAVE_SPIDER_SPAWN_EGG, CHAPPLE_SPAWN_EGG);
            addAfter(event, Items.WARDEN_SPAWN_EGG, WEAVER_SPAWN_EGG);
        } else if (tabKey == CreativeModeTabs.COMBAT) {
            addAfter(event, Items.EGG, EGGPLE, GOLDEN_EGGPLE);
            addAfter(event, Items.TURTLE_HELMET,
                    TIDAL_HELMET,
                    SILKEN_SLACKS,
                    ADVENTURERS_BOOTS,
                    WHITE_HAT,
                    TRADER_HOOD,
                    WHITE_SWEATER,
                    TRADER_ROBE
            );
            addAfter(event, Items.TRIDENT, HAMMER);
            addBefore(event, Items.TOTEM_OF_UNDYING, GOLDEN_GOLEM);
        } else if (tabKey == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            addAfter(event, Items.BRUSH, HAMMER, CHISEL, WRENCH, HANDBELL);
            addAfter(event, Items.BAMBOO_CHEST_RAFT, TURTLE_RAFT);
            addAfter(event, Items.NAME_TAG, ITEM_LABEL);
            addBefore(event, Items.MUSIC_DISC_OTHERSIDE, MUSIC_DISC_SQUALL);
        } else if (tabKey == CreativeModeTabs.NATURAL_BLOCKS) {
            addAfter(event, Items.GRAVEL, GRAVEL_PATH);
            addAfter(event, Items.SAND, SAND_PATH);
            addAfter(event, Items.RED_SAND, RED_SAND_PATH);
            addAfter(event, Items.SNOW, SNOW_PATH);
            addAfter(event, Items.NETHER_GOLD_ORE, NETHER_BISMUTH_ORE);
            addAfter(event, Items.RAW_GOLD_BLOCK, RAW_BISMUTH_BLOCK);
            addAfter(event, Items.PINK_PETALS, BOUQUET);
            addAfter(event, Items.BEETROOT_SEEDS, COTTON_SEEDS);
        } else if (tabKey == CreativeModeTabs.COLORED_BLOCKS) {
            addBefore(event, Items.WHITE_CONCRETE, IRIDESCENT_CONCRETE);
            addBefore(event, Items.WHITE_WOOL, IRIDESCENT_WOOL);
            addBefore(event, Items.WHITE_GLAZED_TERRACOTTA, IRIDESCENT_GLAZED_TERRACOTTA);
            addAfter(event, Items.TERRACOTTA, IRIDESCENT_TERRACOTTA);
            addAfter(event, Items.GLASS, IRIDESCENT_GLASS);
            addAfter(event, Items.GLASS_PANE, IRIDESCENT_GLASS_PANE);
            addAfter(event, Items.PINK_BED,
                    WHITE_SWEATER, LIGHT_GRAY_SWEATER, GRAY_SWEATER, BLACK_SWEATER, BROWN_SWEATER, RED_SWEATER,
                    ORANGE_SWEATER, YELLOW_SWEATER, LIME_SWEATER, GREEN_SWEATER, CYAN_SWEATER, LIGHT_BLUE_SWEATER,
                    BLUE_SWEATER, PURPLE_SWEATER, MAGENTA_SWEATER, PINK_SWEATER, WHITE_HAT, LIGHT_GRAY_HAT,
                    GRAY_HAT, BLACK_HAT, BROWN_HAT, RED_HAT, ORANGE_HAT, YELLOW_HAT, LIME_HAT, GREEN_HAT,
                    CYAN_HAT, LIGHT_BLUE_HAT, BLUE_HAT, PURPLE_HAT, MAGENTA_HAT, PINK_HAT
            );
        } else if (tabKey == CreativeModeTabs.INGREDIENTS) {
            addAfter(event, Items.NETHERITE_INGOT, RUBBER);
            addAfter(event, Items.EGG, EGGPLE, GOLDEN_EGGPLE);
            addAfter(event, Items.RAW_GOLD, RAW_BISMUTH);
            addAfter(event, Items.GOLD_INGOT, BISMUTH_INGOT);
            addAfter(event, Items.WHEAT, COTTON_FLOWER);
        }
    }

    private void addAfter(BuildCreativeModeTabContentsEvent event, Item anchor, Item... items) {
        ItemStack anchorStack = new ItemStack(anchor);
        for (Item item : items) {
            ItemStack newStack = new ItemStack(item);
            event.insertAfter(anchorStack, newStack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            anchorStack = newStack;
        }
    }

    private void addBefore(BuildCreativeModeTabContentsEvent event, Item anchor, Item... items) {
        ItemStack anchorStack = new ItemStack(anchor);
        for (int i = items.length - 1; i >= 0; i--) {
            ItemStack newStack = new ItemStack(items[i]);
            event.insertBefore(anchorStack, newStack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            anchorStack = newStack;
        }
    }
}