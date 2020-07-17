package com.robertx22.mine_and_slash.mmorpg.registers.client;

import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ParticleRegister;
import com.robertx22.mine_and_slash.vanilla_mc.particles.DripEleParticle;
import com.robertx22.mine_and_slash.vanilla_mc.particles.MyBubbleParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.BubbleParticle;
import net.minecraft.client.particle.ParticleManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ParticleFactoryRegister {

    @SubscribeEvent
    public static void onParticleFactoryRegisterEvent(ParticleFactoryRegisterEvent event) {

        ParticleManager man = Minecraft.getInstance().particles;

        man.registerFactory(ParticleRegister.DRIP, DripEleParticle.DrippingElementalFactory::new);
        man.registerFactory(ParticleRegister.THUNDER, BubbleParticle.Factory::new);
        man.registerFactory(ParticleRegister.BUBBLE, MyBubbleParticle.Factory::new);

        MMORPG.devToolsLog("Registered Particles");
    }

}
