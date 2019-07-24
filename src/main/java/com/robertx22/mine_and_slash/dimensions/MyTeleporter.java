package com.robertx22.mine_and_slash.dimensions;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;

public class MyTeleporter extends Teleporter {
    private final BlockPos targetPos;
    DimensionType type = null;

    public MyTeleporter(World world, BlockPos pos, PlayerEntity player) {
        super((ServerWorld) world);
        this.targetPos = pos;
    }

    public MyTeleporter(World world, DimensionType type, BlockPos pos,
                        PlayerEntity player) {
        super((ServerWorld) world);
        this.targetPos = pos;
        this.type = type;
    }

    @Override
    public boolean func_222268_a(Entity entity, float yaw) {

        entity.moveToBlockPosAndAngles(targetPos, yaw, entity.rotationPitch);

        return true;

    }

}