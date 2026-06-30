package com.ninni.etcetera.block.entity;

import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import com.ninni.etcetera.registry.EtceteraBlockEntityType;
import com.ninni.etcetera.registry.EtceteraStatusEffects;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.Level;

import java.util.List;

public class DreamCatcherBlockEntity extends BlockEntity {

    public DreamCatcherBlockEntity(BlockPos pos, BlockState state) {
        super(EtceteraBlockEntityType.DREAM_CATCHER.get(), pos, state);
    }

    public static void tick(Level world, BlockPos pos, BlockState state, DreamCatcherBlockEntity blockEntity) {
        if (state.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF) == DoubleBlockHalf.LOWER && world.getDayTime() > 13000 && world.getDayTime() < 24020) {
            List<Player> list = world.getEntitiesOfClass(Player.class, new AABB(pos).inflate(8.0D, 5.0D, 8.0D), player -> !player.isCreative());
            for (Player player : list) {
                if (world.getDayTime() > 24000) {
                    player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 20 * 15, 2, false, true));
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 20 * 90, 0, false, true));
                }
                player.addEffect(new MobEffectInstance(EtceteraStatusEffects.DROWSY, 10, 0, true, true));
            }
        }
    }

}