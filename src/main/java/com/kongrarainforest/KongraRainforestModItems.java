package com.kongrarainforest;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class KongraRainforestModItems {

    public static final FoodComponent JUNGLE_BERRY_FOOD = new FoodComponent.Builder()
            .hunger(4).saturationModifier(1.2f).alwaysEdible()
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 0), 1.0f)
            .build();

    public static final Item RAINFOREST_ESSENCE = new Item(new FabricItemSettings());
    public static final Item COBRA_SCALE = new Item(new FabricItemSettings());
    public static final Item GORILLA_HIDE = new Item(new FabricItemSettings());
    public static final Item KONGRA_ALLOY = new Item(new FabricItemSettings());
    public static final Item JUNGLE_BERRY = new Item(new FabricItemSettings().food(JUNGLE_BERRY_FOOD));

    public static final Item KONGRA_HELMET = new ArmorItem(KongraArmorMaterial.INSTANCE, ArmorItem.Type.HELMET, new FabricItemSettings().fireproof());
    public static final Item KONGRA_CHESTPLATE = new ArmorItem(KongraArmorMaterial.INSTANCE, ArmorItem.Type.CHESTPLATE, new FabricItemSettings().fireproof());
    public static final Item KONGRA_LEGGINGS = new ArmorItem(KongraArmorMaterial.INSTANCE, ArmorItem.Type.LEGGINGS, new FabricItemSettings().fireproof());
    public static final Item KONGRA_BOOTS = new ArmorItem(KongraArmorMaterial.INSTANCE, ArmorItem.Type.BOOTS, new FabricItemSettings().fireproof());

    public static Item KONGRA_SPAWN_EGG;
    public static Item TOUCAN_SPAWN_EGG;
    public static Item JAGUAR_SPAWN_EGG;
    public static Item TREE_FROG_SPAWN_EGG;

    public static void registerItems() {
        register("rainforest_essence", RAINFOREST_ESSENCE);
        register("cobra_scale", COBRA_SCALE);
        register("gorilla_hide", GORILLA_HIDE);
        register("kongra_alloy", KONGRA_ALLOY);
        register("jungle_berry", JUNGLE_BERRY);

        register("kongra_helmet", KONGRA_HELMET);
        register("kongra_chestplate", KONGRA_CHESTPLATE);
        register("kongra_leggings", KONGRA_LEGGINGS);
        register("kongra_boots", KONGRA_BOOTS);

        KONGRA_SPAWN_EGG = register("kongra_spawn_egg",
                new SpawnEggItem(KongraRainforestModEntities.KONGRA, 0x001a00, 0x39ff14, new FabricItemSettings()));
        TOUCAN_SPAWN_EGG = register("toucan_spawn_egg",
                new SpawnEggItem(KongraRainforestModEntities.TOUCAN, 0x000000, 0xff9900, new FabricItemSettings()));
        JAGUAR_SPAWN_EGG = register("jaguar_spawn_egg",
                new SpawnEggItem(KongraRainforestModEntities.JAGUAR, 0xc8a020, 0x1a1a1a, new FabricItemSettings()));
        TREE_FROG_SPAWN_EGG = register("tree_frog_spawn_egg",
                new SpawnEggItem(KongraRainforestModEntities.TREE_FROG, 0x39ff14, 0x664422, new FabricItemSettings()));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(RAINFOREST_ESSENCE);
            entries.add(COBRA_SCALE);
            entries.add(GORILLA_HIDE);
            entries.add(KONGRA_ALLOY);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(JUNGLE_BERRY);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(KONGRA_HELMET);
            entries.add(KONGRA_CHESTPLATE);
            entries.add(KONGRA_LEGGINGS);
            entries.add(KONGRA_BOOTS);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries -> {
            entries.add(KONGRA_SPAWN_EGG);
            entries.add(TOUCAN_SPAWN_EGG);
            entries.add(JAGUAR_SPAWN_EGG);
            entries.add(TREE_FROG_SPAWN_EGG);
        });
    }

    private static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(KongraRainforestMod.MOD_ID, name), item);
    }
}