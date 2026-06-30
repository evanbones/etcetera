package com.ninni.etcetera.registry;

import com.ninni.etcetera.Constants;
import com.ninni.etcetera.block.entity.*;
import com.ninni.etcetera.platform.Services;
import com.ninni.etcetera.platform.services.RegistrationProvider;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class EtceteraBlockEntityType {
    public static final RegistrationProvider<BlockEntityType<?>> BLOCK_ENTITY_TYPES = RegistrationProvider.get(Registries.BLOCK_ENTITY_TYPE, Constants.MOD_ID);

    private static <T extends BlockEntity> BlockEntityType<T> register(String id, BlockEntityType<T> type) {
        BLOCK_ENTITY_TYPES.register(id, () -> type);
        return type;
    }

    public static final BlockEntityType<ItemStandBlockEntity> ITEM_STAND = register(
            "item_stand",
            Services.PLATFORM.createBlockEntityType(
                    ItemStandBlockEntity::new,
                    EtceteraBlocks.ITEM_STAND,
                    EtceteraBlocks.GLOW_ITEM_STAND
            )
    );

    public static final BlockEntityType<TintedLightBulbBlockEntity> TINTED_LIGHT_BULB = register(
            "tinted_light_bulb",
            Services.PLATFORM.createBlockEntityType(
                    TintedLightBulbBlockEntity::new,
                    EtceteraBlocks.TINTED_LIGHT_BULB
            )
    );

    public static final BlockEntityType<PricklyCanBlockEntity> PRICKLY_CAN = register(
            "prickly_can",
            Services.PLATFORM.createBlockEntityType(
                    PricklyCanBlockEntity::new,
                    EtceteraBlocks.PRICKLY_CAN
            )
    );

    public static final BlockEntityType<DreamCatcherBlockEntity> DREAM_CATCHER = register(
            "dream_catcher",
            Services.PLATFORM.createBlockEntityType(
                    DreamCatcherBlockEntity::new,
                    EtceteraBlocks.DREAM_CATCHER
            )
    );

    public static final BlockEntityType<RedstoneWireComparatorBlockEntity> REDSTONE_WIRE_COMPARATOR = register(
            "redstone_wire_comparator",
            Services.PLATFORM.createBlockEntityType(
                    RedstoneWireComparatorBlockEntity::new,
                    EtceteraBlocks.REDSTONE_WIRE_COMPARATOR
            )
    );

    public static void init() {
    }
}