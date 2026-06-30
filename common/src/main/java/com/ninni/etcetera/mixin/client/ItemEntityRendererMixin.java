package com.ninni.etcetera.mixin.client;

import com.ninni.etcetera.client.gui.HandbellItemRenderer;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemDisplayContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemEntityRenderer.class)
public abstract class ItemEntityRendererMixin {

    @ModifyVariable(
            method = "render(Lnet/minecraft/world/entity/item/ItemEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            at = @At(value = "STORE")
    )
    private BakedModel onRender(BakedModel originalModel, ItemEntity entity) {
        BakedModel customModel = HandbellItemRenderer.modifyRenderItem(entity.getItem(), ItemDisplayContext.GROUND);
        return customModel != null ? customModel : originalModel;
    }
}