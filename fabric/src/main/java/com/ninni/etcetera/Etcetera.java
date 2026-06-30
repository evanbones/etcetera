package com.ninni.etcetera;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.levelgen.GenerationStep;

import static com.ninni.etcetera.registry.EtceteraItems.*;

public class Etcetera implements ModInitializer {

    @Override
    public void onInitialize() {
        CommonClass.init();
        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheNether(),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "nether_bismuth_ore"))
        );
        registerCreativeTabs();
    }

    private void registerCreativeTabs() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(entries -> {
            entries.addAfter(Items.SMOOTH_QUARTZ_SLAB, BISMUTH_BLOCK, BISMUTH_BARS);
            entries.addAfter(Items.SMOOTH_STONE_SLAB, LEVELED_STONE, CRUMBLING_STONE, WAXED_CRUMBLING_STONE, LEVELED_STONE_STAIRS, LEVELED_STONE_SLAB);
            entries.addAfter(Items.MUD_BRICK_WALL, RUBBER_BLOCK, RUBBER_BUTTON);
        });

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries -> {
            entries.addAfter(Items.SOUL_LANTERN, LIGHT_BULB, TINTED_LIGHT_BULB);
            entries.addAfter(Items.END_ROD, SQUID_LAMP);
            entries.addAfter(Items.SEA_LANTERN, IRIDESCENT_LANTERN);
            entries.addAfter(Items.JUKEBOX, DRUM);
            entries.addAfter(Items.SCAFFOLDING, FRAME);
            entries.addAfter(Items.DECORATED_POT, TERRACOTTA_VASE);
            entries.addAfter(Items.GLOW_ITEM_FRAME, ITEM_STAND, GLOW_ITEM_STAND);
            entries.addAfter(Items.ENDER_CHEST, PRICKLY_CAN);
            entries.addAfter(Items.SUSPICIOUS_GRAVEL, CRUMBLING_STONE, WAXED_CRUMBLING_STONE);
            entries.addAfter(Items.BELL, DREAM_CATCHER);
            entries.addAfter(Items.CAULDRON, COPPER_TAP);
            entries.addBefore(Items.SKELETON_SKULL, RUBBER_CHICKEN);
        });

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.REDSTONE_BLOCKS).register(entries -> {
            entries.addAfter(Items.COMPARATOR, REDSTONE_WIRES, REDSTONE_WIRE_TORCH, REDSTONE_WIRE_REPEATER, REDSTONE_WIRE_COMPARATOR);
            entries.addAfter(Items.HEAVY_WEIGHTED_PRESSURE_PLATE, DRUM);
            entries.addAfter(Items.WHITE_WOOL, DICE);
            entries.addAfter(Items.BARREL, PRICKLY_CAN);
            entries.addAfter(Items.STONE_BUTTON, RUBBER_BUTTON);
        });

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS).register(entries -> {
            entries.addAfter(Items.CAVE_SPIDER_SPAWN_EGG, CHAPPLE_SPAWN_EGG);
            entries.addAfter(Items.WARDEN_SPAWN_EGG, WEAVER_SPAWN_EGG);
        });

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(entries -> {
            entries.addAfter(Items.EGG, EGGPLE, GOLDEN_EGGPLE);
            entries.addAfter(Items.TURTLE_HELMET,
                    TIDAL_HELMET,
                    SILKEN_SLACKS,
                    ADVENTURERS_BOOTS,
                    WHITE_HAT,
                    TRADER_HOOD,
                    WHITE_SWEATER,
                    TRADER_ROBE
            );
            entries.addAfter(Items.TRIDENT, HAMMER);
            entries.addBefore(Items.TOTEM_OF_UNDYING, GOLDEN_GOLEM);
        });

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
            entries.addAfter(Items.BRUSH, HAMMER, CHISEL, WRENCH, HANDBELL);
            entries.addAfter(Items.BAMBOO_CHEST_RAFT, TURTLE_RAFT);
            entries.addAfter(Items.NAME_TAG, ITEM_LABEL);
            entries.addBefore(Items.MUSIC_DISC_OTHERSIDE, MUSIC_DISC_SQUALL);
        });

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> {
            entries.addAfter(Items.GRAVEL, GRAVEL_PATH);
            entries.addAfter(Items.SAND, SAND_PATH);
            entries.addAfter(Items.RED_SAND, RED_SAND_PATH);
            entries.addAfter(Items.SNOW, SNOW_PATH);
            entries.addAfter(Items.NETHER_GOLD_ORE, NETHER_BISMUTH_ORE);
            entries.addAfter(Items.RAW_GOLD_BLOCK, RAW_BISMUTH_BLOCK);
            entries.addAfter(Items.PINK_PETALS, BOUQUET);
            entries.addAfter(Items.BEETROOT_SEEDS, COTTON_SEEDS);
        });

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COLORED_BLOCKS).register(entries -> {
            entries.addBefore(Items.WHITE_CONCRETE, IRIDESCENT_CONCRETE);
            entries.addBefore(Items.WHITE_WOOL, IRIDESCENT_WOOL);
            entries.addBefore(Items.WHITE_GLAZED_TERRACOTTA, IRIDESCENT_GLAZED_TERRACOTTA);
            entries.addAfter(Items.TERRACOTTA, IRIDESCENT_TERRACOTTA);
            entries.addAfter(Items.GLASS, IRIDESCENT_GLASS);
            entries.addAfter(Items.GLASS_PANE, IRIDESCENT_GLASS_PANE);
            entries.addAfter(Items.PINK_BED,
                    WHITE_SWEATER, LIGHT_GRAY_SWEATER, GRAY_SWEATER, BLACK_SWEATER, BROWN_SWEATER, RED_SWEATER,
                    ORANGE_SWEATER, YELLOW_SWEATER, LIME_SWEATER, GREEN_SWEATER, CYAN_SWEATER, LIGHT_BLUE_SWEATER,
                    BLUE_SWEATER, PURPLE_SWEATER, MAGENTA_SWEATER, PINK_SWEATER, WHITE_HAT, LIGHT_GRAY_HAT,
                    GRAY_HAT, BLACK_HAT, BROWN_HAT, RED_HAT, ORANGE_HAT, YELLOW_HAT, LIME_HAT, GREEN_HAT,
                    CYAN_HAT, LIGHT_BLUE_HAT, BLUE_HAT, PURPLE_HAT, MAGENTA_HAT, PINK_HAT
            );
        });

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(entries -> {
            entries.addAfter(Items.NETHERITE_INGOT, RUBBER);
            entries.addAfter(Items.EGG, EGGPLE, GOLDEN_EGGPLE);
            entries.addAfter(Items.RAW_GOLD, RAW_BISMUTH);
            entries.addAfter(Items.GOLD_INGOT, BISMUTH_INGOT);
            entries.addAfter(Items.WHEAT, COTTON_FLOWER);
        });
    }
}