package com.robertx22.dimensions;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class MyTeleporter extends Teleporter {
    private final BlockPos targetPos;

    public MyTeleporter(World world, BlockPos pos, EntityPlayer player, int dimension) {
	super((WorldServer) world);
	this.targetPos = pos;
    }

    @Override
    public void placeEntity(World world, Entity entity, float yaw) {
	entity.moveToBlockPosAndAngles(targetPos, yaw, entity.rotationPitch);
    }

}