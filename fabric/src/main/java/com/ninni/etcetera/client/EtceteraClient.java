package com.ninni.etcetera.client;

import com.ninni.etcetera.client.gui.HandbellItemRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class EtceteraClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        FabricClientIntegration.clientInit();

        ModelLoadingPlugin.register(pluginContext -> {
            pluginContext.addModels(HandbellItemRenderer.INVENTORY_IN_HAND_MODEL_ID.id());
        });

        HudRenderCallback.EVENT.register((guiGraphics, deltaTracker) ->
                new TidalHelmetHud().render(guiGraphics, deltaTracker.getGameTimeDeltaTicks())
        );
    }
}