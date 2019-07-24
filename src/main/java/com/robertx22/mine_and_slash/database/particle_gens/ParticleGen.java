package com.robertx22.mine_and_slash.database.particle_gens;

import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.client.Minecraft;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.Random;

public abstract class ParticleGen {

    public Random rand = new Random();

    public abstract void Summon(double x, double y, double z, double radius, int amount,
                                Elements.RGB color);

    public Vec3d getCenter(BlockPos pos) {

        return new Vec3d(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
    }

    public abstract String Name();

    public void spawnRedstone(Elements.RGB color, double xpos, double ypos, double zpos) {

        RedstoneParticleData data = new RedstoneParticleData(color.getR(), color.getG(), color
                .getB(), 1F);
        Minecraft.getInstance().world.addParticle(data, true, xpos, ypos, zpos, 1, 1, 1);
    }

}
