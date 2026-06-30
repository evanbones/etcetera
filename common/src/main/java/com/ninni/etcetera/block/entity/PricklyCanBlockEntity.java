package com.ninni.etcetera.block.entity;
import net.minecraft.core.HolderLookup;

import com.ninni.etcetera.block.PricklyCanBlock;
import com.ninni.etcetera.client.gui.screen.PricklyCanScreenHandler;
import com.ninni.etcetera.registry.EtceteraBlockEntityType;
import com.ninni.etcetera.registry.EtceteraSoundEvents;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.core.NonNullList;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class PricklyCanBlockEntity extends RandomizableContainerBlockEntity {
    private NonNullList<ItemStack> inventory;
    private final ContainerOpenersCounter stateDefinition;
    private Component customName;

    public PricklyCanBlockEntity(BlockPos pos, BlockState state) {
        super(EtceteraBlockEntityType.PRICKLY_CAN, pos, state);
        this.inventory = NonNullList.withSize(27, ItemStack.EMPTY);
        this.stateDefinition = new ContainerOpenersCounter() {
            @Override
            protected void onOpen(Level world, BlockPos pos, BlockState state) {
                PricklyCanBlockEntity.this.playSound(EtceteraSoundEvents.BLOCK_PRICKLY_CAN_OPEN);
                PricklyCanBlockEntity.this.setOpen(state, true);
            }

            @Override
            protected void onClose(Level world, BlockPos pos, BlockState state) {
                PricklyCanBlockEntity.this.playSound(EtceteraSoundEvents.BLOCK_PRICKLY_CAN_CLOSE);
                PricklyCanBlockEntity.this.setOpen(state, false);
            }

            @Override
            protected void openerCountChanged(Level world, BlockPos pos, BlockState state, int oldViewerCount, int newViewerCount) {
            }

            @Override
            protected boolean isOwnContainer(Player player) {
                if (player.containerMenu instanceof PricklyCanScreenHandler) {
                    Container inventory = ((PricklyCanScreenHandler)player.containerMenu).getInventory();
                    return inventory == PricklyCanBlockEntity.this;
                } else return false;
            }
        };
    }

    @Override
    protected void saveAdditional(CompoundTag nbt, HolderLookup.Provider provider) {
        super.saveAdditional(nbt, provider);
        if (!this.trySaveLootTable(nbt)) {
            ContainerHelper.saveAllItems(nbt, this.inventory, provider);
        }
        if (this.customName != null) {
            nbt.putString("CustomName", Component.Serializer.toJson(this.customName, provider));
        }
    }

    @Override
    public void loadAdditional(CompoundTag nbt, HolderLookup.Provider provider) {
        super.loadAdditional(nbt, provider);
        this.inventory = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(nbt)) {
            ContainerHelper.loadAllItems(nbt, this.inventory, provider);
        }
        if (nbt.contains("CustomName", 8)) {
            this.customName = Component.Serializer.fromJson(nbt.getString("CustomName"), provider);
        }
    }

    @Override
    public int getContainerSize() {
        return 27;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> list) {
        this.inventory = list;
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("etcetera.container.prickly_can");
    }

    @Override
    protected AbstractContainerMenu createMenu(int syncId, Inventory playerInventory) {
        return PricklyCanScreenHandler.createGeneric9x3(syncId, playerInventory, this);
    }

    @Override
    public void startOpen(Player player) {
        if (!this.isRemoved() && !player.isSpectator()) {
            this.stateDefinition.incrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    @Override
    public void stopOpen(Player player) {
        if (!this.isRemoved() && !player.isSpectator()) {
            this.stateDefinition.decrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }

    }

    public void tick() {
        if (!this.isRemoved()) {
            this.stateDefinition.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
        }

    }

    void setOpen(BlockState state, boolean open) {
        this.level.setBlock(this.getBlockPos(), state.setValue(PricklyCanBlock.OPEN, open), 3);
    }

    void playSound(SoundEvent soundEvent) {
        this.level.playSound(null, this.worldPosition.getX(), this.worldPosition.getY(), this.worldPosition.getZ(), soundEvent, SoundSource.BLOCKS, 1F, this.level.random.nextFloat() * 0.1F + 0.9F);
    }

    @Override
    public Component getName() {
        return this.customName != null ? this.customName : this.getDefaultName();
    }

    @Override
    public Component getCustomName() {
        return this.customName;
    }

    public void setCustomName(Component name) {
        this.customName = name;
    }
}