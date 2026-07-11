package com.kongrarainforest;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class KongraRainforestModEntities {

    public static final EntityType<KongraEntity> KONGRA = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(KongraRainforestMod.MOD_ID, "kongra"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, KongraEntity::new)
                    .dimensions(EntityDimensions.fixed(1.4f, 2.6f)).build());

    public static final EntityType<ToucanEntity> TOUCAN = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(KongraRainforestMod.MOD_ID, "toucan"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ToucanEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.7f)).build());

    public static final EntityType<JaguarEntity> JAGUAR = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(KongraRainforestMod.MOD_ID, "jaguar"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, JaguarEntity::new)
                    .dimensions(EntityDimensions.fixed(0.9f, 0.9f)).build());

    public static final EntityType<TreeFrogEntity> TREE_FROG = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(KongraRainforestMod.MOD_ID, "tree_frog"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, TreeFrogEntity::new)
                    .dimensions(EntityDimensions.fixed(0.4f, 0.4f)).build());

    public static void registerEntities() {
        FabricDefaultAttributeRegistry.register(KONGRA, KongraEntity.createKongraAttributes());
        FabricDefaultAttributeRegistry.register(TOUCAN, ToucanEntity.createToucanAttributes());
        FabricDefaultAttributeRegistry.register(JAGUAR, JaguarEntity.createJaguarAttributes());
        FabricDefaultAttributeRegistry.register(TREE_FROG, TreeFrogEntity.createTreeFrogAttributes());
    }

    public static void registerSpawns() {
        BiomeModifications.addSpawnEntry(BiomeSelectors.tag(ConventionalBiomeTags.JUNGLE),
                SpawnGroup.CREATURE, TOUCAN, 12, 1, 3);
        BiomeModifications.addSpawnEntry(BiomeSelectors.tag(ConventionalBiomeTags.JUNGLE),
                SpawnGroup.CREATURE, JAGUAR, 6, 1, 2);
        BiomeModifications.addSpawnEntry(BiomeSelectors.tag(ConventionalBiomeTags.JUNGLE),
                SpawnGroup.CREATURE, TREE_FROG, 10, 1, 4);
        BiomeModifications.addSpawnEntry(BiomeSelectors.tag(ConventionalBiomeTags.JUNGLE),
                SpawnGroup.MONSTER, KONGRA, 1, 1, 1);
    }
}