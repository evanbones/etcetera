package com.ninni.etcetera.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.ninni.etcetera.client.gui.HandbellItemRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

    @ModifyVariable(method = "render", at = @At("HEAD"), argsOnly = true)
    private BakedModel createModel(BakedModel value, ItemStack stack, ItemDisplayContext displayContext, boolean leftHanded, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay) {
        ModelManager models = Minecraft.getInstance().getModelManager();
        return HandbellItemRenderer.isInventory(stack, displayContext) ? models.getModel(HandbellItemRenderer.INVENTORY_MODEL_ID) : value;
    }
}