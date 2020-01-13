package com.robertx22.mine_and_slash.particles;

import com.robertx22.mine_and_slash.mmorpg.registers.common.ParticleRegister;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;

public class EleParticleData extends ParticleType<EleParticleData> implements IParticleData {

    Elements element;

    public EleParticleData(Elements element) {
        super(true, ParticleDeserializer.INSTANCE);
        this.element = element;
    }

    @Override
    public ParticleType<?> getType() {
        return ParticleRegister.ele_particle;
    }

    @Override
    public void write(PacketBuffer buf) {
        buf.writeString(element.name());
    }

    @Override
    public String getParameters() {
        return "";
    }
}
