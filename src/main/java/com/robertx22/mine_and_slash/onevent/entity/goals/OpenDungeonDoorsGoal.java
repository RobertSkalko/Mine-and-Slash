package com.robertx22.mine_and_slash.onevent.entity.goals;

import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.OpenDoorGoal;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.math.BlockPos;

public class OpenDungeonDoorsGoal extends OpenDoorGoal {
    public OpenDungeonDoorsGoal(MobEntity mob) {
        super(mob, false);
    }

    @Override
    protected void toggleDoor(boolean close) {
        BlockState state = this.entity.world.getBlockState(this.doorPosition);
        if (state.getBlock() instanceof DoorBlock) {
            ((DoorBlock) state.getBlock()).toggleDoor(this.entity.world, this.doorPosition, true);
        }

    }

    @Override
    public boolean shouldExecute() {
        GroundPathNavigator navigator = (GroundPathNavigator) this.entity.getNavigator();
        Path path = navigator.getPath();
        if (path != null && !path.isFinished()) {
            for (int i = 0; i < Math.min(path.getCurrentPathIndex() + 2, path.getCurrentPathLength()); ++i) {
                PathPoint point = path.getPathPointFromIndex(i);
                this.doorPosition = new BlockPos(point.x, point.y + 1, point.z);
                if (this.entity.getDistanceSq((double) this.doorPosition.getX(), this.entity.getPosY(), (double) this.doorPosition.getZ()) <= 2.25D) {
                    this.doorInteract = canOpenDoor(this.entity.world, this.doorPosition);
                    if (this.doorInteract) {
                        return true;
                    }
                }
            }

            this.doorPosition = (new BlockPos(this.entity)).up();
            this.doorInteract = canOpenDoor(this.entity.world, this.doorPosition);
            return this.doorInteract;
        } else {
            return false;
        }

    }

}
