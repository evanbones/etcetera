package com.ninni.etcetera.client;

import com.ninni.etcetera.Constants;
import com.ninni.etcetera.block.RedstoneWiresBlock;
import com.ninni.etcetera.client.gui.HandbellItemRenderer;
import com.ninni.etcetera.client.gui.screen.PricklyCanScreen;
import com.ninni.etcetera.client.model.*;
import com.ninni.etcetera.client.particles.GoldenParticle;
import com.ninni.etcetera.client.particles.RubberParticle;
import com.ninni.etcetera.client.render.block.entity.ItemStandBlockEntityRenderer;
import com.ninni.etcetera.client.render.entity.*;
import com.ninni.etcetera.registry.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.*;
import net.minecraft.ChatFormatting;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.block.Block;

import static com.ninni.etcetera.registry.EtceteraBlocks.*;

public class EtceteraClient implements ClientModInitializer {

    private static void itemTooltipCallback() {
        ItemTooltipCallback.EVENT.register((stack, context, type, lines) -> {
            int color = 0x959595;
            Integer darkAqua = ChatFormatting.DARK_AQUA.getColor();
            Integer darkPurple = ChatFormatting.DARK_PURPLE.getColor();

            switch (stack.getRarity()) {
                case COMMON -> color = 0x959595;
                case UNCOMMON -> color = 0xbb7d2b;
                case RARE -> color = darkAqua != null ? darkAqua : 0x55FFFF;
                case EPIC -> color = darkPurple != null ? darkPurple : 0xAA00AA;
            }
            Style style = Style.EMPTY.withColor(color).withItalic(true);

            CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
            if (customData != null) {
                CompoundTag tag = customData.copyTag();
                for (int row = 1; row < 5; row++) {
                    if (tag.contains("Label" + row)) {
                        lines.add(row, Component.literal(tag.getString("Label" + row)).setStyle(style));
                    }
                }
            }
        });
    }

    private static void registerParticles() {
        ParticleFactoryRegistry instance = ParticleFactoryRegistry.getInstance();
        instance.register(EtceteraParticleTypes.GOLDEN_HEART, GoldenParticle.Factory::new);
        instance.register(EtceteraParticleTypes.GOLDEN_SHEEN, GoldenParticle.Factory::new);

        instance.register(EtceteraParticleTypes.DRIPPING_RUBBER, sprites -> (type, world, x, y, z, xSpeed, ySpeed, zSpeed) -> {
            TextureSheetParticle drippingRubber = RubberParticle.createDrippingRubber(world, x, y, z, xSpeed, ySpeed, zSpeed);
            drippingRubber.pickSprite(sprites);
            return drippingRubber;
        });
        instance.register(EtceteraParticleTypes.FALLING_RUBBER, sprites -> (type, world, x, y, z, xSpeed, ySpeed, zSpeed) -> {
            TextureSheetParticle fallingRubber = RubberParticle.createFallingRubber(world, x, y, z, xSpeed, ySpeed, zSpeed);
            fallingRubber.pickSprite(sprites);
            return fallingRubber;
        });
        instance.register(EtceteraParticleTypes.LANDING_RUBBER, sprites -> (type, world, x, y, z, xSpeed, ySpeed, zSpeed) -> {
            TextureSheetParticle landingRubber = RubberParticle.createLandingRubber(world, x, y, z, xSpeed, ySpeed, zSpeed);
            landingRubber.pickSprite(sprites);
            return landingRubber;
        });
    }

    private static void registerModelPredicates() {
        ItemProperties.register(EtceteraItems.GOLDEN_GOLEM, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "broken"), (stack, world, entity, seed) -> {
            CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
            if (customData != null) {
                CompoundTag tag = customData.copyTag();
                if (tag.contains("Broken") && tag.getBoolean("Broken")) {
                    return 1.0f;
                }
            }
            return 0.0f;
        });
    }

    private static void registerBlockEntityRenderer() {
        BlockEntityRenderers.register(EtceteraBlockEntityType.ITEM_STAND, ItemStandBlockEntityRenderer::new);
    }

    private static void registerBlockRenderLayers() {
        ColorProviderRegistry<Block, BlockColor> blockColor = ColorProviderRegistry.BLOCK;
        blockColor.register((state, world, pos, tintIndex) -> RedstoneWiresBlock.getWireColor(state.getValue(RedstoneWiresBlock.POWER)), EtceteraBlocks.REDSTONE_WIRES);

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.translucent(),
                IRIDESCENT_GLASS,
                IRIDESCENT_GLASS_PANE,
                LIGHT_BULB,
                TINTED_LIGHT_BULB,
                FOOTSTEPS
        );
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(),
                REDSTONE_WIRES,
                REDSTONE_WIRE_TORCH,
                REDSTONE_WIRE_COMPARATOR,
                REDSTONE_WIRE_REPEATER,
                REDSTONE_WIRE_WALL_TORCH,
                BISMUTH_BARS,
                BOUQUET,
                COTTON,
                POTTED_BOUQUET,
                ITEM_STAND,
                GLOW_ITEM_STAND,
                FRAME,
                DREAM_CATCHER,
                PRICKLY_CAN,
                COPPER_TAP
        );
    }

    private static void registerArmor() {
        CottonArmorRenderer cottonRenderer = new CottonArmorRenderer();

        ArmorRenderer.register(cottonRenderer::render, EtceteraItems.WHITE_SWEATER);
        ArmorRenderer.register(cottonRenderer::render, EtceteraItems.LIGHT_GRAY_SWEATER);
        ArmorRenderer.register(cottonRenderer::render, EtceteraItems.GRAY_SWEATER);
        ArmorRenderer.register(cottonRenderer::render, EtceteraItems.BLACK_SWEATER);
        ArmorRenderer.register(cottonRenderer::render, EtceteraItems.BROWN_SWEATER);
        ArmorRenderer.register(cottonRenderer::render, EtceteraItems.RED_SWEATER);
        ArmorRenderer.register(cottonRenderer::render, EtceteraItems.ORANGE_SWEATER);
        ArmorRenderer.register(cottonRenderer::render, EtceteraItems.YELLOW_SWEATER);
        ArmorRenderer.register(cottonRenderer::render, EtceteraItems.LIME_SWEATER);
        ArmorRenderer.register(cottonRenderer::render, EtceteraItems.GREEN_SWEATER);
        ArmorRenderer.register(cottonRenderer::render, EtceteraItems.CYAN_SWEATER);
        ArmorRenderer.register(cottonRenderer::render, EtceteraItems.LIGHT_BLUE_SWEATER);
        ArmorRenderer.register(cottonRenderer::render, EtceteraItems.BLUE_SWEATER);
        ArmorRenderer.register(cottonRenderer::render, EtceteraItems.PURPLE_SWEATER);
        ArmorRenderer.register(cottonRenderer::render, EtceteraItems.MAGENTA_SWEATER);
        ArmorRenderer.register(cottonRenderer::render, EtceteraItems.PINK_SWEATER);
        ArmorRenderer.register(cottonRenderer::render, EtceteraItems.TRADER_ROBE);
        ArmorRenderer.register(cottonRenderer::render, EtceteraItems.WHITE_HAT);
        ArmorRenderer.register(cottonRenderer::render, EtceteraItems.LIGHT_GRAY_HAT);
        ArmorRenderer.register(cottonRenderer::render, EtceteraItems.GRAY_HAT);
        ArmorRenderer.register(cottonRenderer::render, EtceteraItems.BLACK_HAT);
        ArmorRenderer.register(cottonRenderer::render, EtceteraItems.BROWN_HAT);
        ArmorRenderer.register(cottonRenderer::render, EtceteraItems.RED_HAT);
        ArmorRenderer.register(cottonRenderer::render, EtceteraItems.ORANGE_HAT);
        ArmorRenderer.register(cottonRenderer::render, EtceteraItems.YELLOW_HAT);
        ArmorRenderer.register(cottonRenderer::render, EtceteraItems.LIME_HAT);
        ArmorRenderer.register(cottonRenderer::render, EtceteraItems.GREEN_HAT);
        ArmorRenderer.register(cottonRenderer::render, EtceteraItems.CYAN_HAT);
        ArmorRenderer.register(cottonRenderer::render, EtceteraItems.LIGHT_BLUE_HAT);
        ArmorRenderer.register(cottonRenderer::render, EtceteraItems.BLUE_HAT);
        ArmorRenderer.register(cottonRenderer::render, EtceteraItems.PURPLE_HAT);
        ArmorRenderer.register(cottonRenderer::render, EtceteraItems.MAGENTA_HAT);
        ArmorRenderer.register(cottonRenderer::render, EtceteraItems.PINK_HAT);
        ArmorRenderer.register(cottonRenderer::render, EtceteraItems.TRADER_HOOD);

        ArmorRenderer.register(new TidalArmorRenderer()::render, EtceteraItems.TIDAL_HELMET);
        ArmorRenderer.register(new SilkArmorRenderer()::render, EtceteraItems.SILKEN_SLACKS);
        ArmorRenderer.register(new AdventurerArmorRenderer()::render, EtceteraItems.ADVENTURERS_BOOTS);
    }

    private static void registerScreens() {
        MenuScreens.register(EtceteraScreenHandlerType.PRICKLY_CAN, PricklyCanScreen::new);
    }

    private static void registerEntityModelLayers() {
        EntityModelLayerRegistry.registerModelLayer(EtceteraEntityModelLayers.RUBBER_CHICKEN, RubberChickenModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(EtceteraEntityModelLayers.GOLDEN_GOLEM, GoldenGolemModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(EtceteraEntityModelLayers.COBWEB, CobwebProjectileModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(EtceteraEntityModelLayers.TURTLE_RAFT, TurtleRaftModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(EtceteraEntityModelLayers.WEAVER, WeaverModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(EtceteraEntityModelLayers.CHAPPLE, ChappleModel::getTexturedModelData);

        EntityRendererRegistry.register(EtceteraEntityType.TURTLE_RAFT, TurtleRaftRenderer::new);
        EntityRendererRegistry.register(EtceteraEntityType.CHAPPLE, ChappleRenderer::new);
        EntityRendererRegistry.register(EtceteraEntityType.EGGPLE, ThrownItemRenderer::new);
        EntityRendererRegistry.register(EtceteraEntityType.WEAVER, WeaverRenderer::new);
        EntityRendererRegistry.register(EtceteraEntityType.COBWEB, CobwebProjectileEntityRenderer::new);
        EntityRendererRegistry.register(EtceteraEntityType.GOLDEN_GOLEM, GoldenGolemRenderer::new);
        EntityRendererRegistry.register(EtceteraEntityType.THROWN_GOLDEN_GOLEM, ThrownItemRenderer::new);
        EntityRendererRegistry.register(EtceteraEntityType.RUBBER_CHICKEN, RubberChickenRenderer::new);
    }

    private static void registerColorProviders() {
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            if (tintIndex > 0) return -1;
            DyedItemColor dyedColor = stack.get(DataComponents.DYED_COLOR);
            return dyedColor != null ? dyedColor.rgb() : 0x3fa442;
        }, EtceteraItems.TURTLE_RAFT);
    }

    @Override
    public void onInitializeClient() {
        registerBlockEntityRenderer();
        registerArmor();
        registerBlockRenderLayers();
        registerScreens();
        registerEntityModelLayers();
        registerModelPredicates();
        registerColorProviders();
        registerParticles();
        itemTooltipCallback();

        ModelLoadingPlugin.register(pluginContext -> {
            pluginContext.addModels(HandbellItemRenderer.INVENTORY_IN_HAND_MODEL_ID.id());
        });

        HudRenderCallback.EVENT.register((guiGraphics, deltaTracker) ->
                new TidalHelmetHud().render(guiGraphics, deltaTracker.getGameTimeDeltaTicks())
        );
    }
}