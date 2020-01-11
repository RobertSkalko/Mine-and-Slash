package com.robertx22.mine_and_slash.a_libraries.curios;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import top.theillusivec4.curios.api.CuriosAPI;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CurioClientSetup {
    public static final String CURIOS = "curios";

    public static void setup(final FMLClientSetupEvent event) {

        send(CuriosAPI.IMC.REGISTER_ICON, new Tuple<>(RefCurio.BRACELET, new ResourceLocation(Ref.MODID, "items/slots/bracelet")));
        send(CuriosAPI.IMC.REGISTER_ICON, new Tuple<>(RefCurio.SALVAGE_BAG, new ResourceLocation(Ref.MODID, "items/slots/salvage_bag")));

    }

    private static void send(String id, Object msg) {
        InterModComms.sendTo(CURIOS, id, () -> msg);
    }

    @SubscribeEvent
    public static void stitchTextures(TextureStitchEvent.Pre evt) {

        evt.addSprite(new ResourceLocation(Ref.MODID, "items/slots/bracelet"));
        evt.addSprite(new ResourceLocation(Ref.MODID, "items/slots/salvage_bag"));

    }

}
