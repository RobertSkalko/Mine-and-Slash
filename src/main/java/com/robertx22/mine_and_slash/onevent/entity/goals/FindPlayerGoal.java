package com.robertx22.mine_and_slash.onevent.entity.goals;

import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

public class FindPlayerGoal extends Goal {
    private final MobEntity mob;
    private final float distance;
    public final EntityPredicate predicate = (new EntityPredicate()).setDistance(8.0D)
        .setSkipAttackChecks()
        .allowInvulnerable()
        .allowFriendlyFire()
        .setLineOfSiteRequired()
        .setUseInvisibilityCheck();

    public FindPlayerGoal(MobEntity mob, float distance) {
        this.mob = mob;
        this.distance = distance * distance;
        this.setMutexFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean shouldExecute() {
        return true;
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
        this.mob.getNavigator()
            .clearPath();
        List<MobEntity> list = this.mob.world.getTargettableEntitiesWithinAABB(MobEntity.class, this.predicate, this.mob, this.mob.getBoundingBox()
            .grow(8.0D, 8.0D, 8.0D));
        Iterator var2 = list.iterator();

        while (var2.hasNext()) {
            MobEntity themob = (MobEntity) var2.next();
            themob.setAttackTarget(this.mob.getAttackTarget());
        }

    }

    @Override
    public void resetTask() {
        super.resetTask();
        LivingEntity en = this.mob.getAttackTarget();
        if (en != null) {
            List<MobEntity> mb = this.mob.world.getTargettableEntitiesWithinAABB(MobEntity.class, this.predicate, this.mob, this.mob.getBoundingBox()
                .grow(8.0D, 8.0D, 8.0D));
            Iterator var3 = mb.iterator();

            while (var3.hasNext()) {
                MobEntity themob = (MobEntity) var3.next();
                themob.setAttackTarget(en);
                themob.setAggroed(true);
            }

            this.mob.setAggroed(true);
        }

    }

    @Override
    public void tick() {
        LivingEntity en = this.mob.getAttackTarget();
        if (en != null) {
            if (this.mob.getDistanceSq(en) > (double) this.distance) {
                this.mob.getLookController()
                    .setLookPositionWithEntity(en, 30.0F, 30.0F);
                if (RandomUtils.roll(50)) {
                    this.mob.playAmbientSound();
                }
            } else {
                this.mob.setAggroed(true);
            }

            super.tick();
        }
    }
}