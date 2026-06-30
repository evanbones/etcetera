package com.ninni.etcetera.registry;

import com.ninni.etcetera.Constants;
import com.ninni.etcetera.item.*;
import com.ninni.etcetera.platform.services.RegistrationProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;

import static com.ninni.etcetera.registry.EtceteraVanillaIntegration.CHISELLING_MANAGER;
import static com.ninni.etcetera.registry.EtceteraVanillaIntegration.HAMMERING_MANAGER;

@SuppressWarnings("unused")
public class EtceteraItems {
    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registries.ITEM, Constants.MOD_ID);

    public static final Item ETCETERA = register("etcetera", new Item(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

    public static final Item RAW_BISMUTH_BLOCK = register("raw_bismuth_block", new BlockItem(EtceteraBlocks.RAW_BISMUTH_BLOCK, new Item.Properties()));
    public static final Item BISMUTH_BLOCK = register("bismuth_block", new BlockItem(EtceteraBlocks.BISMUTH_BLOCK, new Item.Properties()));
    public static final Item BISMUTH_BARS = register("bismuth_bars", new BlockItem(EtceteraBlocks.BISMUTH_BARS, new Item.Properties()));
    public static final Item NETHER_BISMUTH_ORE = register("nether_bismuth_ore", new BlockItem(EtceteraBlocks.NETHER_BISMUTH_ORE, new Item.Properties()));
    public static final Item RAW_BISMUTH = register("raw_bismuth", new Item(new Item.Properties()));
    public static final Item BISMUTH_INGOT = register("bismuth_ingot", new Item(new Item.Properties()));
    public static final Item IRIDESCENT_GLASS = register("iridescent_glass", new BlockItem(EtceteraBlocks.IRIDESCENT_GLASS, new Item.Properties()));
    public static final Item IRIDESCENT_GLASS_PANE = register("iridescent_glass_pane", new BlockItem(EtceteraBlocks.IRIDESCENT_GLASS_PANE, new Item.Properties()));
    public static final Item IRIDESCENT_TERRACOTTA = register("iridescent_terracotta", new BlockItem(EtceteraBlocks.IRIDESCENT_TERRACOTTA, new Item.Properties()));
    public static final Item IRIDESCENT_GLAZED_TERRACOTTA = register("iridescent_glazed_terracotta", new BlockItem(EtceteraBlocks.IRIDESCENT_GLAZED_TERRACOTTA, new Item.Properties()));
    public static final Item IRIDESCENT_CONCRETE = register("iridescent_concrete", new BlockItem(EtceteraBlocks.IRIDESCENT_CONCRETE, new Item.Properties()));
    public static final Item IRIDESCENT_WOOL = register("iridescent_wool", new BlockItem(EtceteraBlocks.IRIDESCENT_WOOL, new Item.Properties()));
    public static final Item IRIDESCENT_LANTERN = register("iridescent_lantern", new BlockItem(EtceteraBlocks.IRIDESCENT_LANTERN, new Item.Properties()));

    public static final Item CHISEL = register("chisel", new TransformingItem(EtceteraToolMaterials.BISMUTH, CHISELLING_MANAGER::getMap, new Item.Properties().stacksTo(1).durability(145), EtceteraSoundEvents.ITEM_CHISEL_USE, EtceteraTags.CHISELLABLE));
    public static final Item WRENCH = register("wrench", new WrenchItem(EtceteraToolMaterials.BISMUTH, new Item.Properties().stacksTo(1).durability(145)));
    public static final Item HAMMER = register("hammer", new HammerItem(EtceteraToolMaterials.BISMUTH, (int) 7.5, -3.6F, HAMMERING_MANAGER::getMap, new Item.Properties().stacksTo(1).durability(80), EtceteraSoundEvents.ITEM_HAMMER_USE, EtceteraTags.HAMMERABLE));
    public static final Item HANDBELL = register("handbell", new HandbellItem(new Item.Properties().stacksTo(1)));

    public static final Item ITEM_LABEL = register("item_label", new Item(new Item.Properties()));

    public static final Item DRUM = register("drum", new BlockItem(EtceteraBlocks.DRUM, new Item.Properties()));

    public static final Item DICE = register("dice", new BlockItem(EtceteraBlocks.DICE, new Item.Properties()));

    public static final Item FRAME = register("frame", new BlockItem(EtceteraBlocks.FRAME, new Item.Properties()));

    public static final Item PRICKLY_CAN = register("prickly_can", new BlockItem(EtceteraBlocks.PRICKLY_CAN, new Item.Properties()));

    public static final Item DREAM_CATCHER = register("dream_catcher", new DreamCatcherItem(EtceteraBlocks.DREAM_CATCHER, new Item.Properties()));

    public static final Item BOUQUET = register("bouquet", new BlockItem(EtceteraBlocks.BOUQUET, new Item.Properties().stacksTo(16)));
    public static final Item TERRACOTTA_VASE = register("terracotta_vase", new BlockItem(EtceteraBlocks.TERRACOTTA_VASE, new Item.Properties()));

    public static final Item ITEM_STAND = register("item_stand", new BlockItem(EtceteraBlocks.ITEM_STAND, new Item.Properties()));
    public static final Item GLOW_ITEM_STAND = register("glow_item_stand", new BlockItem(EtceteraBlocks.GLOW_ITEM_STAND, new Item.Properties()));

    public static final Item SQUID_LAMP = register("squid_lamp", new StandingAndWallBlockItem(EtceteraBlocks.SQUID_LAMP, EtceteraBlocks.WALL_SQUID_LAMP, new Item.Properties(), Direction.DOWN));
    public static final Item TIDAL_HELMET = register("tidal_helmet", new ArmorItem(EtceteraArmorMaterials.TIDAL, ArmorItem.Type.HELMET, new Item.Properties().rarity(Rarity.RARE)));
    public static final Item TURTLE_RAFT = register("turtle_raft", new TurtleRaftItem(new Item.Properties().stacksTo(1)));

    public static final Item MUSIC_DISC_SQUALL = register("music_disc_squall", new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "squall")))));

    public static final Item ADVENTURERS_BOOTS = register("adventurers_boots", new ArmorItem(EtceteraArmorMaterials.ADVENTURER, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final Item SAND_PATH = register("sand_path", new BlockItem(EtceteraBlocks.SAND_PATH, new Item.Properties()));
    public static final Item RED_SAND_PATH = register("red_sand_path", new BlockItem(EtceteraBlocks.RED_SAND_PATH, new Item.Properties()));
    public static final Item GRAVEL_PATH = register("gravel_path", new BlockItem(EtceteraBlocks.GRAVEL_PATH, new Item.Properties()));
    public static final Item SNOW_PATH = register("snow_path", new BlockItem(EtceteraBlocks.SNOW_PATH, new Item.Properties()));

    public static final Item CRUMBLING_STONE = register("crumbling_stone", new BlockItem(EtceteraBlocks.CRUMBLING_STONE, new Item.Properties()));
    public static final Item WAXED_CRUMBLING_STONE = register("waxed_crumbling_stone", new BlockItem(EtceteraBlocks.WAXED_CRUMBLING_STONE, new Item.Properties()));
    public static final Item LEVELED_STONE = register("leveled_stone", new BlockItem(EtceteraBlocks.LEVELED_STONE, new Item.Properties()));
    public static final Item LEVELED_STONE_STAIRS = register("leveled_stone_stairs", new BlockItem(EtceteraBlocks.LEVELED_STONE_STAIRS, new Item.Properties()));
    public static final Item LEVELED_STONE_SLAB = register("leveled_stone_slab", new BlockItem(EtceteraBlocks.LEVELED_STONE_SLAB, new Item.Properties()));

    public static final Item LIGHT_BULB = register("light_bulb", new BlockItem(EtceteraBlocks.LIGHT_BULB, new Item.Properties()));
    public static final Item TINTED_LIGHT_BULB = register("tinted_light_bulb", new BlockItem(EtceteraBlocks.TINTED_LIGHT_BULB, new Item.Properties()));

    public static final Item GOLDEN_GOLEM = register("golden_golem", new GoldenGolemItem(new Item.Properties().stacksTo(1)));

    public static final Item WEAVER_SPAWN_EGG = register("weaver_spawn_egg", new SpawnEggItem(EtceteraEntityType.WEAVER, 0x191919, 0xFF1B00, new Item.Properties().stacksTo(64)));
    public static final Item SILKEN_SLACKS = register("silken_slacks", new ArmorItem(EtceteraArmorMaterials.SILK, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final Item CHAPPLE_SPAWN_EGG = register("chapple_spawn_egg", new SpawnEggItem(EtceteraEntityType.CHAPPLE, 0xE41826, 0x548630, new Item.Properties().stacksTo(64)));
    public static final Item EGGPLE = register("eggple", new EggpleItem(false, new Item.Properties().stacksTo(16)));
    public static final Item GOLDEN_EGGPLE = register("golden_eggple", new EggpleItem(true, new Item.Properties().rarity(Rarity.RARE).stacksTo(16)));

    public static final Item COPPER_TAP = register("copper_tap", new BlockItem(EtceteraBlocks.COPPER_TAP, new Item.Properties()));

    public static final Item RUBBER = register("rubber", new Item(new Item.Properties()));
    public static final Item RUBBER_CHICKEN = register("rubber_chicken", new RubberChickenItem(new Item.Properties().stacksTo(16)));
    public static final Item RUBBER_BLOCK = register("rubber_block", new BlockItem(EtceteraBlocks.RUBBER_BLOCK, new Item.Properties()));
    public static final Item RUBBER_BUTTON = register("rubber_button", new BlockItem(EtceteraBlocks.RUBBER_BUTTON, new Item.Properties()));
    public static final Item REDSTONE_WIRES = register("redstone_wires", new BlockItem(EtceteraBlocks.REDSTONE_WIRES, new Item.Properties()));
    public static final Item REDSTONE_WIRE_TORCH = register("redstone_wire_torch", new StandingAndWallBlockItem(EtceteraBlocks.REDSTONE_WIRE_TORCH, EtceteraBlocks.REDSTONE_WIRE_WALL_TORCH, new Item.Properties(), Direction.DOWN));
    public static final Item REDSTONE_WIRE_COMPARATOR = register("redstone_wire_comparator", new BlockItem(EtceteraBlocks.REDSTONE_WIRE_COMPARATOR, new Item.Properties()));
    public static final Item REDSTONE_WIRE_REPEATER = register("redstone_wire_repeater", new BlockItem(EtceteraBlocks.REDSTONE_WIRE_REPEATER, new Item.Properties()));

    public static final Item COTTON_SEEDS = register("cotton_seeds", new ItemNameBlockItem(EtceteraBlocks.COTTON, new Item.Properties()));
    public static final Item COTTON_FLOWER = register("cotton_flower", new Item(new Item.Properties()));
    public static final Item WHITE_SWEATER = register("white_sweater", new SweaterItem(new Item.Properties().stacksTo(1), true));
    public static final Item LIGHT_GRAY_SWEATER = register("light_gray_sweater", new SweaterItem(new Item.Properties().stacksTo(1), true));
    public static final Item GRAY_SWEATER = register("gray_sweater", new SweaterItem(new Item.Properties().stacksTo(1), true));
    public static final Item BLACK_SWEATER = register("black_sweater", new SweaterItem(new Item.Properties().stacksTo(1), true));
    public static final Item BROWN_SWEATER = register("brown_sweater", new SweaterItem(new Item.Properties().stacksTo(1), true));
    public static final Item RED_SWEATER = register("red_sweater", new SweaterItem(new Item.Properties().stacksTo(1), true));
    public static final Item ORANGE_SWEATER = register("orange_sweater", new SweaterItem(new Item.Properties().stacksTo(1), true));
    public static final Item YELLOW_SWEATER = register("yellow_sweater", new SweaterItem(new Item.Properties().stacksTo(1), true));
    public static final Item LIME_SWEATER = register("lime_sweater", new SweaterItem(new Item.Properties().stacksTo(1), true));
    public static final Item GREEN_SWEATER = register("green_sweater", new SweaterItem(new Item.Properties().stacksTo(1), true));
    public static final Item CYAN_SWEATER = register("cyan_sweater", new SweaterItem(new Item.Properties().stacksTo(1), true));
    public static final Item LIGHT_BLUE_SWEATER = register("light_blue_sweater", new SweaterItem(new Item.Properties().stacksTo(1), true));
    public static final Item BLUE_SWEATER = register("blue_sweater", new SweaterItem(new Item.Properties().stacksTo(1), true));
    public static final Item PURPLE_SWEATER = register("purple_sweater", new SweaterItem(new Item.Properties().stacksTo(1), true));
    public static final Item MAGENTA_SWEATER = register("magenta_sweater", new SweaterItem(new Item.Properties().stacksTo(1), true));
    public static final Item PINK_SWEATER = register("pink_sweater", new SweaterItem(new Item.Properties().stacksTo(1), true));
    public static final Item TRADER_ROBE = register("trader_robe", new SweaterItem(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON), true));
    public static final Item WHITE_HAT = register("white_hat", new SweaterItem(new Item.Properties().stacksTo(1), false));
    public static final Item LIGHT_GRAY_HAT = register("light_gray_hat", new SweaterItem(new Item.Properties().stacksTo(1), false));
    public static final Item GRAY_HAT = register("gray_hat", new SweaterItem(new Item.Properties().stacksTo(1), false));
    public static final Item BLACK_HAT = register("black_hat", new SweaterItem(new Item.Properties().stacksTo(1), false));
    public static final Item BROWN_HAT = register("brown_hat", new SweaterItem(new Item.Properties().stacksTo(1), false));
    public static final Item RED_HAT = register("red_hat", new SweaterItem(new Item.Properties().stacksTo(1), false));
    public static final Item ORANGE_HAT = register("orange_hat", new SweaterItem(new Item.Properties().stacksTo(1), false));
    public static final Item YELLOW_HAT = register("yellow_hat", new SweaterItem(new Item.Properties().stacksTo(1), false));
    public static final Item LIME_HAT = register("lime_hat", new SweaterItem(new Item.Properties().stacksTo(1), false));
    public static final Item GREEN_HAT = register("green_hat", new SweaterItem(new Item.Properties().stacksTo(1), false));
    public static final Item CYAN_HAT = register("cyan_hat", new SweaterItem(new Item.Properties().stacksTo(1), false));
    public static final Item LIGHT_BLUE_HAT = register("light_blue_hat", new SweaterItem(new Item.Properties().stacksTo(1), false));
    public static final Item BLUE_HAT = register("blue_hat", new SweaterItem(new Item.Properties().stacksTo(1), false));
    public static final Item PURPLE_HAT = register("purple_hat", new SweaterItem(new Item.Properties().stacksTo(1), false));
    public static final Item MAGENTA_HAT = register("magenta_hat", new SweaterItem(new Item.Properties().stacksTo(1), false));
    public static final Item PINK_HAT = register("pink_hat", new SweaterItem(new Item.Properties().stacksTo(1), false));
    public static final Item TRADER_HOOD = register("trader_hood", new SweaterItem(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON), false));

    private static <T extends Item> T register(String id, T item) {
        ITEMS.register(id, () -> item);
        return item;
    }

    public static void init() {
    }
}