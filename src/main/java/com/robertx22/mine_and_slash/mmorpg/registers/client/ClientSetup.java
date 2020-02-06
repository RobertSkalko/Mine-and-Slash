package com.robertx22.mine_and_slash.mmorpg.registers.client;

import com.robertx22.mine_and_slash.a_libraries.TestNameRender;
import com.robertx22.mine_and_slash.a_libraries.curios.CurioClientSetup;
import com.robertx22.mine_and_slash.a_libraries.neat_mob_overlay.HealthBarRenderer;
import com.robertx22.mine_and_slash.config.forge.ClientContainer;
import com.robertx22.mine_and_slash.gui.bar_overlays.PlayerBarsOverlayScreen;
import com.robertx22.mine_and_slash.gui.gear_overlay.GearOverlayGUI;
import com.robertx22.mine_and_slash.gui.mob_bar.MobBarScreen;
import com.robertx22.mine_and_slash.gui.spell_cast_bar.SpellCastBarOverlay;
import com.robertx22.mine_and_slash.gui.spell_hotbar.SpellHotbarOverlay;
import com.robertx22.mine_and_slash.mmorpg.registers.common.BlockRegister;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {

    public static void setup(final FMLClientSetupEvent event) {

        Minecraft.getInstance().getRenderManager().renderers.values().forEach(x -> {
            if (x instanceof LivingRenderer) {
                ((LivingRenderer) x).addLayer(new TestNameRender((IEntityRenderer) x)); // TODO
            }
        });

        RenderTypeLookup.setRenderLayer(BlockRegister.BLOCK_GEAR_MODIFY, RenderType.cutout());//cutout
        RenderTypeLookup.setRenderLayer(BlockRegister.BLOCK_GEAR_SALVAGE, RenderType.cutout());//cutout
        RenderTypeLookup.setRenderLayer(BlockRegister.ALCHEMY_BLOCK, RenderType.cutout());//cutout
        RenderTypeLookup.setRenderLayer(BlockRegister.BLOCK_GEAR_REPAIR, RenderType.cutout());//cutout

        RenderTypeLookup.setRenderLayer(BlockRegister.MAGMA_FLOWER_BLOCK, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockRegister.THORN_BUSH_BLOCK, RenderType.cutout());

        SpecialRenderRegister.register(event);
        CurioClientSetup.setup(event);

        MinecraftForge.EVENT_BUS.register(new PlayerBarsOverlayScreen(Minecraft.getInstance()));
        MinecraftForge.EVENT_BUS.register(new MobBarScreen(Minecraft.getInstance()));
        MinecraftForge.EVENT_BUS.register(new SpellCastBarOverlay());
        MinecraftForge.EVENT_BUS.register(new SpellHotbarOverlay());

        if (ClientContainer.INSTANCE.SHOW_UNMET_GEAR_REQUIREMENTS_GUI.get()) {
            MinecraftForge.EVENT_BUS.register(new GearOverlayGUI(Minecraft.getInstance()));
        }

        MinecraftForge.EVENT_BUS.register(new HealthBarRenderer());
        KeybindsRegister.register();
        ContainerGuiRegisters.reg();
        RenderRegister.regRenders();
    }
}
