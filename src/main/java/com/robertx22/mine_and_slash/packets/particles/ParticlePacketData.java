package com.robertx22.mine_and_slash.packets.particles;

import com.robertx22.mine_and_slash.uncommon.enumclasses.RGB;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.math.BlockPos;

@Storable
public class ParticlePacketData {

    public ParticlePacketData() {

    }

    @Store
    public BlockPos pos;

    @Store
    public ParticleEnum type;

    @Store
    public float radius = 1;

    @Store
    public RGB color;

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
