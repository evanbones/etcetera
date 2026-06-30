package com.ninni.etcetera.registry;

import com.ninni.etcetera.Constants;
import com.ninni.etcetera.platform.services.RegistrationProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;

import java.util.Comparator;
import java.util.function.Predicate;

import static com.ninni.etcetera.registry.EtceteraItems.*;

public class EtceteraCreativeModeTab {
    public static final RegistrationProvider<CreativeModeTab> CREATIVE_MODE_TABS = RegistrationProvider.get(Registries.CREATIVE_MODE_TAB, Constants.MOD_ID);

    public static final CreativeModeTab ITEM_GROUP = register("item_group", CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .icon(() -> new ItemStack(ETCETERA))
            .title(Component.translatable("etcetera.item_group"))
            .displayItems((featureFlagSet, output) -> {
                output.accept(RAW_BISMUTH_BLOCK);
                output.accept(BISMUTH_BLOCK);
                output.accept(BISMUTH_BARS);
                output.accept(NETHER_BISMUTH_ORE);
                output.accept(RAW_BISMUTH);
                output.accept(BISMUTH_INGOT);
                output.accept(IRIDESCENT_GLASS);
                output.accept(IRIDESCENT_GLASS_PANE);
                output.accept(IRIDESCENT_TERRACOTTA);
                output.accept(IRIDESCENT_GLAZED_TERRACOTTA);
                output.accept(IRIDESCENT_CONCRETE);
                output.accept(IRIDESCENT_WOOL);
                output.accept(IRIDESCENT_LANTERN);

                output.accept(CHISEL);
                output.accept(WRENCH);
                output.accept(HAMMER);
                output.accept(HANDBELL);

                output.accept(ITEM_LABEL);

                output.accept(DRUM);

                output.accept(DICE);

                output.accept(FRAME);

                output.accept(PRICKLY_CAN);

                output.accept(DREAM_CATCHER);

                output.accept(BOUQUET);
                output.accept(TERRACOTTA_VASE);

                output.accept(ITEM_STAND);
                output.accept(GLOW_ITEM_STAND);

                featureFlagSet.holders().lookup(Registries.PAINTING_VARIANT).ifPresent((wrapper) -> addEtcPaintings(output, wrapper, (registryEntry) -> registryEntry.is(EtceteraTags.ETCETERA_PAINTING_VARIANTS)));

                output.accept(SQUID_LAMP);
                output.accept(TIDAL_HELMET);
                output.accept(TURTLE_RAFT);

                output.accept(MUSIC_DISC_SQUALL);

                output.accept(ADVENTURERS_BOOTS);

                output.accept(GRAVEL_PATH);
                output.accept(SAND_PATH);
                output.accept(RED_SAND_PATH);
                output.accept(SNOW_PATH);

                output.accept(CRUMBLING_STONE);
                output.accept(WAXED_CRUMBLING_STONE);
                output.accept(LEVELED_STONE);
                output.accept(LEVELED_STONE_STAIRS);
                output.accept(LEVELED_STONE_SLAB);

                output.accept(LIGHT_BULB);
                output.accept(TINTED_LIGHT_BULB);

                output.accept(GOLDEN_GOLEM);

                output.accept(WEAVER_SPAWN_EGG);
                output.accept(SILKEN_SLACKS);

                output.accept(CHAPPLE_SPAWN_EGG);
                output.accept(EGGPLE);
                output.accept(GOLDEN_EGGPLE);

                output.accept(COPPER_TAP);

                output.accept(RUBBER);
                output.accept(RUBBER_BLOCK);
                output.accept(RUBBER_BUTTON);
                output.accept(RUBBER_CHICKEN);
                output.accept(REDSTONE_WIRES);
                output.accept(REDSTONE_WIRE_TORCH);
                output.accept(REDSTONE_WIRE_COMPARATOR);
                output.accept(REDSTONE_WIRE_REPEATER);

                output.accept(COTTON_SEEDS);
                output.accept(COTTON_FLOWER);
                output.accept(WHITE_SWEATER);
                output.accept(LIGHT_GRAY_SWEATER);
                output.accept(GRAY_SWEATER);
                output.accept(BLACK_SWEATER);
                output.accept(BROWN_SWEATER);
                output.accept(RED_SWEATER);
                output.accept(ORANGE_SWEATER);
                output.accept(YELLOW_SWEATER);
                output.accept(LIME_SWEATER);
                output.accept(GREEN_SWEATER);
                output.accept(CYAN_SWEATER);
                output.accept(LIGHT_BLUE_SWEATER);
                output.accept(BLUE_SWEATER);
                output.accept(PURPLE_SWEATER);
                output.accept(MAGENTA_SWEATER);
                output.accept(PINK_SWEATER);
                output.accept(TRADER_ROBE);
                output.accept(WHITE_HAT);
                output.accept(LIGHT_GRAY_HAT);
                output.accept(GRAY_HAT);
                output.accept(BLACK_HAT);
                output.accept(BROWN_HAT);
                output.accept(RED_HAT);
                output.accept(ORANGE_HAT);
                output.accept(YELLOW_HAT);
                output.accept(LIME_HAT);
                output.accept(GREEN_HAT);
                output.accept(CYAN_HAT);
                output.accept(LIGHT_BLUE_HAT);
                output.accept(BLUE_HAT);
                output.accept(PURPLE_HAT);
                output.accept(MAGENTA_HAT);
                output.accept(PINK_HAT);
                output.accept(TRADER_HOOD);
            }).build()
    );

    private static void addEtcPaintings(CreativeModeTab.Output entries, HolderLookup.RegistryLookup<PaintingVariant> registryLookup, Predicate<Holder<PaintingVariant>> predicate) {
        Comparator<Holder<PaintingVariant>> paintingComparator = Comparator.comparing(
                (Holder<PaintingVariant> holder) -> holder.value().width() * holder.value().height()
        ).thenComparing(
                (Holder<PaintingVariant> holder) -> holder.value().width()
        );

        registryLookup.listElements()
                .filter(predicate)
                .sorted(paintingComparator)
                .forEach((variant) -> {
                    ItemStack itemStack = new ItemStack(Items.PAINTING);
                    CustomData.update(DataComponents.ENTITY_DATA, itemStack, (tag) -> {
                        variant.unwrapKey().ifPresent(key -> tag.putString("variant", key.location().toString()));
                    });
                    entries.accept(itemStack);
                });
    }

    private static CreativeModeTab register(String id, CreativeModeTab tab) {
        CREATIVE_MODE_TABS.register(id, () -> tab);
        return tab;
    }

    public static void init() {
    }
}