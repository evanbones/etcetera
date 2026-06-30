package com.ninni.etcetera.mixin.client;

import com.ninni.etcetera.client.gui.HandbellItemRenderer;
import com.ninni.etcetera.item.HandbellItem;
import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemModelShaper.class)
public abstract class ItemModelsMixin {
    @Shadow
    public abstract ModelManager getModelManager();

    @Inject(
            method = "getItemModel(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/client/resources/model/BakedModel;",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onGetModel(ItemStack stack, CallbackInfoReturnable<BakedModel> cir) {
        if (stack.getItem() instanceof HandbellItem) {
            cir.setReturnValue(this.getModelManager().getModel(HandbellItemRenderer.INVENTORY_IN_HAND_MODEL_ID));
        }
    }
}