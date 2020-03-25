package com.robertx22.mine_and_slash.onevent;

import com.robertx22.mine_and_slash.items.gearitems.offhands.NormalShield;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class StitchTextures {

    @SubscribeEvent
    public static void onStitch(TextureStitchEvent.Pre event) {
        if (event.getMap()
            .getTextureLocation()
            .equals(AtlasTexture.LOCATION_BLOCKS_TEXTURE)) {
            for (int i = 0; i <= IRarity.Highest; i++) {
                event.addSprite(NormalShield.getResource(i));
            }
        }
    }

}
