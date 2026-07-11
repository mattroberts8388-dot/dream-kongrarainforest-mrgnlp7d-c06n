package com.kongrarainforest;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;

public class KongraRainforestMod implements ModInitializer {
    public static final String MOD_ID = "kongrarainforest";

    // Tracks how long each player has been exposed to rain (in ticks).
    private static final java.util.Map<java.util.UUID, Integer> rainExposure = new java.util.HashMap<>();

    // How long (ticks) before rain begins to damage. 200 ticks = 10 seconds.
    private static final int RAIN_GRACE_TICKS = 200;
    // Damage tick interval once past grace.
    private static final int DAMAGE_INTERVAL = 40;

    @Override
    public void onInitialize() {
        KongraRainforestModItems.registerItems();
        KongraRainforestModEntities.registerEntities();
        KongraRainforestModEntities.registerSpawns();

        ServerTickEvents.END_WORLD_TICK.register(this::onWorldTick);
    }

    private void onWorldTick(ServerWorld world) {
        if (!world.isRaining()) {
            return;
        }
        for (PlayerEntity player : world.getPlayers()) {
            if (player.isCreative() || player.isSpectator()) {
                continue;
            }
            if (isExposedToRain(world, player)) {
                int exposure = rainExposure.getOrDefault(player.getUuid(), 0) + 1;
                rainExposure.put(player.getUuid(), exposure);

                if (exposure > RAIN_GRACE_TICKS && exposure % DAMAGE_INTERVAL == 0) {
                    boolean fullArmor = hasFullKongraArmor(player);
                    if (fullArmor) {
                        // Armor protects fully but gives slight warning effect only.
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 100, 0, false, false));
                    } else {
                        player.damage(world.getDamageSources().magic(), 1.0f);
                    }
                }
            } else {
                rainExposure.put(player.getUuid(), 0);
            }
        }
    }

    private boolean isExposedToRain(ServerWorld world, PlayerEntity player) {
        BlockPos pos = player.getBlockPos();
        int topY = world.getTopY(Heightmap.Type.MOTION_BLOCKING, pos.getX(), pos.getZ());
        boolean skyOpen = topY <= pos.getY();
        boolean inWater = player.isSubmergedInWater();
        return skyOpen && !inWater && world.getBiome(pos).value().hasPrecipitation();
    }

    public static boolean hasFullKongraArmor(PlayerEntity player) {
        for (int slot = 0; slot < 4; slot++) {
            var stack = player.getInventory().getArmorStack(slot);
            if (!(stack.getItem() instanceof ArmorItem armorItem)) {
                return false;
            }
            if (armorItem.getMaterial() != KongraArmorMaterial.INSTANCE) {
                return false;
            }
        }
        return true;
    }
}