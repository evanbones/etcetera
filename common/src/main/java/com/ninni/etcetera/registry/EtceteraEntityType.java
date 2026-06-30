package com.ninni.etcetera.registry;

import com.ninni.etcetera.Constants;
import com.ninni.etcetera.entity.*;
import com.ninni.etcetera.platform.Services;
import com.ninni.etcetera.platform.services.RegistrationProvider;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;

public class EtceteraEntityType {
    public static final RegistrationProvider<EntityType<?>> ENTITY_TYPES = RegistrationProvider.get(Registries.ENTITY_TYPE, Constants.MOD_ID);

    public static final EntityType<TurtleRaftEntity> TURTLE_RAFT = register(
            "turtle_raft",
            EntityType.Builder.<TurtleRaftEntity>of(TurtleRaftEntity::new, MobCategory.MISC)
                    .sized(0.8f, 0.5625f)
                    .clientTrackingRange(10)
    );

    public static final EntityType<ChappleEntity> CHAPPLE = register(
            "chapple",
            EntityType.Builder.of(ChappleEntity::new, MobCategory.CREATURE)
                    .sized(0.4f, 0.7f)
                    .clientTrackingRange(10)
    );

    public static final EntityType<WeaverEntity> WEAVER = register(
            "weaver",
            EntityType.Builder.of(WeaverEntity::new, MobCategory.MONSTER)
                    .eyeHeight(0.65f)
                    .sized(1.6f, 1.4f)
                    .clientTrackingRange(10)
    );

    public static final EntityType<GoldenGolemEntity> GOLDEN_GOLEM = register(
            "golden_golem",
            EntityType.Builder.of(GoldenGolemEntity::new, MobCategory.MISC)
                    .sized(0.35f, 0.6f)
                    .clientTrackingRange(10)
                    .updateInterval(2)
    );

    public static final EntityType<EggpleEntity> EGGPLE = register(
            "eggple",
            EntityType.Builder.<EggpleEntity>of(EggpleEntity::new, MobCategory.MISC)
                    .sized(0.25f, 0.25f)
                    .clientTrackingRange(4)
    );

    public static final EntityType<CobwebProjectileEntity> COBWEB = register(
            "cobweb",
            EntityType.Builder.<CobwebProjectileEntity>of(CobwebProjectileEntity::new, MobCategory.MISC)
                    .sized(0.3f, 0.3f)
                    .clientTrackingRange(4)
    );

    public static final EntityType<GoldenGolemItemEntity> THROWN_GOLDEN_GOLEM = register(
            "thrown_golden_golem",
            EntityType.Builder.<GoldenGolemItemEntity>of(GoldenGolemItemEntity::new, MobCategory.MISC)
                    .sized(0.25f, 0.25f)
                    .clientTrackingRange(4)
    );

    public static final EntityType<RubberChickenEntity> RUBBER_CHICKEN = register(
            "rubber_chicken",
            EntityType.Builder.<RubberChickenEntity>of(RubberChickenEntity::new, MobCategory.MISC)
                    .sized(0.4F, 0.4F)
                    .clientTrackingRange(4)
    );

    static {
        Services.PLATFORM.registerEntityAttributes(CHAPPLE, Chicken::createAttributes);
        Services.PLATFORM.registerEntityAttributes(WEAVER, WeaverEntity::createAttributes);
        Services.PLATFORM.registerEntityAttributes(GOLDEN_GOLEM, GoldenGolemEntity::createAttributes);
        Services.PLATFORM.registerEntityAttributes(RUBBER_CHICKEN, LivingEntity::createLivingAttributes);

        Services.PLATFORM.registerSpawnPlacement(WEAVER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        Services.PLATFORM.registerSpawnPlacement(CHAPPLE, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.WORLD_SURFACE_WG, Animal::checkAnimalSpawnRules);
        Services.PLATFORM.registerSpawnPlacement(GOLDEN_GOLEM, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
    }

    private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> entityType) {
        EntityType<T> built = entityType.build(id);
        ENTITY_TYPES.register(id, () -> built);
        return built;
    }

    public static void init() {
    }
}