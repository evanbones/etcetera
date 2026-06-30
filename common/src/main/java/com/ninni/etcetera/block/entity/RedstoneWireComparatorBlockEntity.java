package com.ninni.etcetera.block.entity;
import net.minecraft.core.HolderLookup;

import com.ninni.etcetera.registry.EtceteraBlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;

public class RedstoneWireComparatorBlockEntity extends BlockEntity {
    private int outputSignal;

    public RedstoneWireComparatorBlockEntity(BlockPos pos, BlockState state) {
        super(EtceteraBlockEntityType.REDSTONE_WIRE_COMPARATOR, pos, state);
    }

    @Override
    protected void saveAdditional(CompoundTag nbt, HolderLookup.Provider provider) {
        super.saveAdditional(nbt, provider);
        nbt.putInt("OutputSignal", this.outputSignal);
    }

    @Override
    public void loadAdditional(CompoundTag nbt, HolderLookup.Provider provider) {
        super.loadAdditional(nbt, provider);
        this.outputSignal = nbt.getInt("OutputSignal");
    }

    public int getOutputSignal() {
        return this.outputSignal;
    }

    public void setOutputSignal(int outputSignal) {
        this.outputSignal = outputSignal;
    }
}
