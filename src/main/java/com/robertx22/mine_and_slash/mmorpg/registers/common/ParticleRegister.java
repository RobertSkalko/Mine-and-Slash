package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.particles.DripEleParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

public class ParticleRegister {

    @ObjectHolder(Ref.MODID + ":ele_particle")
    public static final BasicParticleType ele_particle = null;

    @SubscribeEvent
    public static void onParticleFactoryRegisterEvent(
            ParticleFactoryRegisterEvent event) {

        Minecraft.getInstance().particles.registerFactory(ele_particle, new DripEleParticle.DrippingElementalFactory());

        MMORPG.devToolsLog("Registered Particles");
    }

    @SubscribeEvent
    public static void register(RegistryEvent.Register<ParticleType<?>> event) {

        register("ele_particle", new BasicParticleType(false));

    }

    private static <T extends ParticleType<?>> T register(String name, T particleType) {
        particleType.setRegistryName(name);
        ForgeRegistries.PARTICLE_TYPES.register(particleType);
        return particleType;
    }
}
