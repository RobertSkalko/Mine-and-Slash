package com.robertx22.mine_and_slash.mmorpg.registers.client;

import com.robertx22.mine_and_slash.a_libraries.curios.CurioClientSetup;
import com.robertx22.mine_and_slash.gui.overlays.bar_overlays.types.VanillaOverlay;
import com.robertx22.mine_and_slash.gui.overlays.mob_bar.MobBarScreen;
import com.robertx22.mine_and_slash.gui.overlays.spell_cast_bar.SpellCastBarOverlay;
import com.robertx22.mine_and_slash.gui.overlays.spell_hotbar.SpellHotbarOverlay;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {

    public static void setup(final FMLClientSetupEvent event) {

        RenderTypeLookup.setRenderLayer(ModBlocks.GEAR_MODIFY.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.GEAR_SALVAGE.get(), RenderType.getCutout());//cutout
        RenderTypeLookup.setRenderLayer(ModBlocks.GEAR_REPAIR.get(), RenderType.getCutout());//cutout

        RenderTypeLookup.setRenderLayer(ModBlocks.MAGMA_FLOWER.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.THORN_BUSH.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.HOLY_FLOWER.get(), RenderType.getCutout());

        CurioClientSetup.setup(event);

        MinecraftForge.EVENT_BUS.register(new VanillaOverlay(Minecraft.getInstance()));

        MinecraftForge.EVENT_BUS.register(new MobBarScreen(Minecraft.getInstance()));
        MinecraftForge.EVENT_BUS.register(new SpellCastBarOverlay());
        MinecraftForge.EVENT_BUS.register(new SpellHotbarOverlay());

        KeybindsRegister.register();
        ContainerGuiRegisters.reg();
        RenderRegister.regRenders();
    }
}
