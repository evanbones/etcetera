package com.ninni.etcetera.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LayeredCauldronBlock.class)
public abstract class LayeredCauldronBlockMixin extends AbstractCauldronBlock {
    public LayeredCauldronBlockMixin(Properties properties, CauldronInteraction.InteractionMap behaviorMap) {
        super(properties, behaviorMap);
    }

    @Override
    protected @NotNull ItemInteractionResult useItemOn(ItemStack stack, @NotNull BlockState state, Level world, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        CustomData customData = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
        CompoundTag tag = customData.copyTag();

        if (world.getBlockState(pos).is(Blocks.WATER_CAULDRON) && (tag.contains("Label1") || tag.contains("Label2") || tag.contains("Label") || tag.contains("Label4"))) {
            ItemStack itemStack2 = stack.copy();

            tag.remove("Label1");
            tag.remove("Label2");
            tag.remove("Label3");
            tag.remove("Label4");

            itemStack2.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
            player.setItemInHand(hand, itemStack2);
            LayeredCauldronBlock.lowerFillLevel(world.getBlockState(pos), world, pos);

            return ItemInteractionResult.sidedSuccess(world.isClientSide);
        }
        return super.useItemOn(stack, state, world, pos, player, hand, hit);
    }
}