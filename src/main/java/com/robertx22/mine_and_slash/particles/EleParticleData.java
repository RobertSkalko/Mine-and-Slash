package com.robertx22.mine_and_slash.particles;

import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;

public class EleParticleData extends ParticleType<EleParticleData> implements IParticleData {

    Elements element;
    ParticleType<?> type;

    public EleParticleData(ParticleType<?> type, Elements element) {
        super(true, ParticleDeserializer.INSTANCE);
        this.element = element;
        this.type = type;
    }

    @Override
    public ParticleType<?> getType() {
        return type;
    }

    @Override
    public void write(PacketBuffer buf) {
        buf.writeString(element.name());
        buf.writeString(type.getRegistryName().toString());
    }

    @Override
    public String getParameters() {
        return "";
    }
}
