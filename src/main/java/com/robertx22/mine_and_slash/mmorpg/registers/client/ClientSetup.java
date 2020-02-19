package com.robertx22.mine_and_slash.mmorpg.registers.client;

import com.robertx22.mine_and_slash.a_libraries.curios.CurioClientSetup;
import com.robertx22.mine_and_slash.a_libraries.neat_mob_overlay.HealthBarRenderer;
import com.robertx22.mine_and_slash.config.forge.ClientContainer;
import com.robertx22.mine_and_slash.gui.bar_overlays.PlayerBarsOverlayScreen;
import com.robertx22.mine_and_slash.gui.gear_overlay.GearOverlayGUI;
import com.robertx22.mine_and_slash.gui.map_overlay.MapInfoOverlay;
import com.robertx22.mine_and_slash.gui.mob_bar.MobBarScreen;
import com.robertx22.mine_and_slash.gui.spell_cast_bar.SpellCastBarOverlay;
import com.robertx22.mine_and_slash.gui.spell_hotbar.SpellHotbarOverlay;
import com.robertx22.mine_and_slash.mmorpg.registers.common.BlockRegister;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {

    public static void setup(final FMLClientSetupEvent event) {

        RenderTypeLookup.setRenderLayer(BlockRegister.BLOCK_GEAR_MODIFY, RenderType.getCutout());//cutout
        RenderTypeLookup.setRenderLayer(BlockRegister.BLOCK_GEAR_SALVAGE, RenderType.getCutout());//cutout
        RenderTypeLookup.setRenderLayer(BlockRegister.ALCHEMY_BLOCK, RenderType.getCutout());//cutout
        RenderTypeLookup.setRenderLayer(BlockRegister.BLOCK_GEAR_REPAIR, RenderType.getCutout());//cutout

        RenderTypeLookup.setRenderLayer(BlockRegister.MAGMA_FLOWER_BLOCK, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockRegister.THORN_BUSH_BLOCK, RenderType.getCutout());

        SpecialRenderRegister.register(event);
        CurioClientSetup.setup(event);

        MinecraftForge.EVENT_BUS.register(new PlayerBarsOverlayScreen(Minecraft.getInstance()));
        MinecraftForge.EVENT_BUS.register(new MobBarScreen(Minecraft.getInstance()));
        MinecraftForge.EVENT_BUS.register(new SpellCastBarOverlay());
        MinecraftForge.EVENT_BUS.register(new SpellHotbarOverlay());
        MinecraftForge.EVENT_BUS.register(new MapInfoOverlay());

        if (ClientContainer.INSTANCE.SHOW_UNMET_GEAR_REQUIREMENTS_GUI.get()) {
            MinecraftForge.EVENT_BUS.register(new GearOverlayGUI(Minecraft.getInstance()));
        }

        MinecraftForge.EVENT_BUS.register(new HealthBarRenderer());
        KeybindsRegister.register();
        ContainerGuiRegisters.reg();
        RenderRegister.regRenders();
    }
}
