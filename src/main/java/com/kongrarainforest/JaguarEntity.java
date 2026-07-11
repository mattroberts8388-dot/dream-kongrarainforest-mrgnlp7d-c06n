package com.kongrarainforest;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class JaguarEntity extends AnimalEntity {

    public JaguarEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createJaguarAttributes() {
        return AnimalEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 24.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.32)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 24.0);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.2, true));
        this.goalSelector.add(2, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 10.0f));
        this.goalSelector.add(4, new LookAroundGoal(this));
        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, ToucanEntity.class, true));
    }

    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return KongraRainforestModEntities.JAGUAR.create(world);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_CAT_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(net.minecraft.entity.damage.DamageSource source) {
        return SoundEvents.ENTITY_CAT_HISS;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_CAT_DEATH;
    }
}