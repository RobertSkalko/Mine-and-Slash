package com.robertx22.mine_and_slash.onevent.entity.goals;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;

public class NearestPlayerGoal extends NearestAttackableTargetGoal<PlayerEntity> {

    public NearestPlayerGoal(MobEntity mob) {
        super(mob, PlayerEntity.class, 100, true, true, x -> x instanceof PlayerEntity);
    }

    @Override
    protected AxisAlignedBB getTargetableArea(double f) {
        return this.goalOwner.getBoundingBox()
            .grow(f + 25, 6, f + 25)
            .grow(15);
    }

    @Override
    public boolean shouldExecute() {
        this.findNearestTarget();
        return this.nearestTarget != null;
    }

}
