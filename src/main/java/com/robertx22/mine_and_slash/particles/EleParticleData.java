package com.robertx22.mine_and_slash.particles;

import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;

public class EleParticleData extends ParticleType<EleParticleData> implements IParticleData {

    public EleParticleData(boolean alwaysShow,
                           IDeserializer<EleParticleData> serializer) {
        super(alwaysShow, serializer);
    }

    @Override
    public ParticleType<?> getType() {
        return null;
    }

    @Override
    public void write(PacketBuffer packetBuffer) {

    }

    @Override
    public String getParameters() {
        return "";
    }
}
