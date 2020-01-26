package com.robertx22.mine_and_slash.packets.particles;

import com.robertx22.mine_and_slash.uncommon.enumclasses.RGB;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.registries.ForgeRegistries;

@Storable
public class ParticlePacketData {

    private ParticlePacketData() {

    }

    public static ParticlePacketData empty() {
        return new ParticlePacketData();
    }

    @Store
    public BlockPos pos;

    @Store
    public ParticleEnum type;

    @Store
    public float radius = 1;

    @Store
    public int amount = 1;

    @Store
    public RGB color;

    @Store
    public String particleID;

    public IParticleData getParticleType() {
        return (IParticleData) ForgeRegistries.PARTICLE_TYPES.getValue(new ResourceLocation(particleID));
    }

    public ParticlePacketData type(ParticleType type) {
        this.particleID = type.getRegistryName().toString();
        return this;
    }

    public ParticlePacketData amount(int num) {
        this.amount = num;
        return this;
    }

    public ParticlePacketData radius(double rad) {
        this.radius = (float) rad;
        return this;
    }

    public ParticlePacketData radius(float rad) {
        this.radius = rad;
        return this;
    }

    public ParticlePacketData color(RGB color) {
        this.color = color;
        return this;
    }

    public ParticlePacketData(BlockPos pos, ParticleEnum type) {
        this.pos = pos;
        this.type = type;
    }
}
