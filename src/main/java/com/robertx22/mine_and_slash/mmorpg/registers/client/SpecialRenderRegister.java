package com.robertx22.mine_and_slash.mmorpg.registers.client;

import com.robertx22.mine_and_slash.blocks.beacon.CosmeticBeaconRenderer;
import com.robertx22.mine_and_slash.dimensions.blocks.MapPortalRenderer;
import com.robertx22.mine_and_slash.mmorpg.registers.common.TileEntityRegister;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class SpecialRenderRegister {

    public static void register(FMLClientSetupEvent event) {

        ClientRegistry.bindTileEntityRenderer(TileEntityRegister.MAP_PORTAL.get(), MapPortalRenderer::new);
        ClientRegistry.bindTileEntityRenderer(TileEntityRegister.BEACON.get(), CosmeticBeaconRenderer::new);

    }

}
