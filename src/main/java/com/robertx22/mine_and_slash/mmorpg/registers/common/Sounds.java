package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Sounds {

    static final String DASH_ID = "dash";
    static final String SPLASH_ID = "splash";
    static final String FREEZE_ID = "freeze";

    @ObjectHolder(Ref.MODID + ":" + DASH_ID)
    public static SoundEvent DASH;

    @ObjectHolder(Ref.MODID + ":" + SPLASH_ID)
    public static SoundEvent SPLASH;

    @ObjectHolder(Ref.MODID + ":" + FREEZE_ID)
    public static SoundEvent FREEZE;

    @SubscribeEvent
    public static void register(RegistryEvent.Register<SoundEvent> event) {
        reg(event, DASH_ID);
        reg(event, SPLASH_ID);
        reg(event, FREEZE_ID);
    }

    private static void reg(RegistryEvent.Register<SoundEvent> event, String id) {
        ResourceLocation loc = new ResourceLocation(Ref.MODID, id);
        event.getRegistry().register(new SoundEvent(loc).setRegistryName(loc));

    }

}
