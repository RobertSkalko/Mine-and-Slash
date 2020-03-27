package com.robertx22.mine_and_slash.mobs.entity;

import com.robertx22.mine_and_slash.uncommon.utilityclasses.PlayerUtils;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.TargetGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.server.ServerWorld;

public class NearestPlayerOpGoal extends TargetGoal {

    int searchDistance = 300;

    public NearestPlayerOpGoal(MobEntity mob) {
        super(mob, false /*check sight*/);
    }

    @Override
    public boolean shouldExecute() {

        if (this.target == null && this.goalOwner.ticksExisted % 50 == 0) {
            findNearestTarget();
            return this.target != null;
        } else {
            return false;
        }
    }

    protected void findNearestTarget() {
        PlayerEntity player = PlayerUtils.nearestPlayer((ServerWorld) this.goalOwner.world, this.goalOwner);
        if (player != null && player.getDistance(goalOwner) < searchDistance) {
            this.target = player;
        }
    }
}
