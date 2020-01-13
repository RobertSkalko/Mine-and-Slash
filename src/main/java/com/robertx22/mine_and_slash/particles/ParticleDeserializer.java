package com.robertx22.mine_and_slash.particles;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData.IDeserializer;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class ParticleDeserializer {

    public static final IDeserializer<EleParticleData> INSTANCE = new IDeserializer<EleParticleData>() {
        @Override
        public EleParticleData deserialize(ParticleType<EleParticleData> data,
                                           StringReader reader) throws CommandSyntaxException {
            reader.expect(' ');
            String eleName = reader.readString();
            Elements element = Elements.valueOf(eleName);
            reader.expect(' ');
            ResourceLocation key = new ResourceLocation(reader.readString());
            ParticleType<?> type = ForgeRegistries.PARTICLE_TYPES.getValue(key);

            return new EleParticleData(type, element);
        }

        @Override
        public EleParticleData read(ParticleType<EleParticleData> data,
                                    PacketBuffer buf) {
            String eleName = buf.readString();
            Elements element = Elements.valueOf(eleName);

            ResourceLocation key = new ResourceLocation(buf.readString());
            ParticleType<?> type = ForgeRegistries.PARTICLE_TYPES.getValue(key);

            return new EleParticleData(type, element);
        }
    };
}
