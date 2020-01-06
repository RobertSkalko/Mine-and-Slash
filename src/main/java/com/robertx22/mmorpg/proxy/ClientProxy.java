package com.robertx22.mmorpg.proxy;

import com.robertx22.dimensions.blocks.RenderTileMapPortal;
import com.robertx22.dimensions.blocks.TileMapPortal;
import com.robertx22.items.gearitems.offhands.NormalShield;
import com.robertx22.items.gearitems.offhands.ShieldRenderer;
import com.robertx22.mmorpg.Keybinds;
import com.robertx22.mmorpg.Main;
import com.robertx22.mmorpg.Ref;
import com.robertx22.network.DamageNumberPackage;
import com.robertx22.network.EntityUnitPackage;
import com.robertx22.network.MessagePackage;
import com.robertx22.network.ParticlePackage;
import com.robertx22.network.PlayerUnitPackage;
import com.robertx22.network.WorldPackage;
import com.robertx22.uncommon.gui.mobs.HealthBarRenderer;
import com.robertx22.uncommon.gui.mobs.ToggleKeyBind;
import com.robertx22.uncommon.gui.player_overlays.BarsGUI;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ClientProxy implements IProxy {
	// functionality

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		// DEBUG
		System.out.println("on Client side");

		MinecraftForge.EVENT_BUS.register(new ToggleKeyBind());
		MinecraftForge.EVENT_BUS.register(new HealthBarRenderer());

		MinecraftForge.EVENT_BUS.register(new PlayerUnitPackage.Handler());
		MinecraftForge.EVENT_BUS.register(new EntityUnitPackage.Handler());
		MinecraftForge.EVENT_BUS.register(new DamageNumberPackage.Handler());
		MinecraftForge.EVENT_BUS.register(new ParticlePackage.Handler());
		MinecraftForge.EVENT_BUS.register(new WorldPackage.Handler());
		MinecraftForge.EVENT_BUS.register(new MessagePackage.Handler());

		ClientRegistry.bindTileEntitySpecialRenderer(TileMapPortal.class, new RenderTileMapPortal());

	}

	@Override
	public void init(FMLInitializationEvent event) {
		// DEBUG
		System.out.println("on Client side");

		Keybinds.register();

	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		// DEBUG
		System.out.println("on Client side");

		MinecraftForge.EVENT_BUS.register(new BarsGUI(Minecraft.getMinecraft()));

	}

	@Override
	public EntityPlayer getPlayerEntityFromContext(MessageContext ctx) {
		return (ctx.side.isClient() ? Minecraft.getMinecraft().player : Main.proxy.getPlayerEntityFromContext(ctx));
	}

	@Override
	public void serverStarting(FMLServerStartingEvent event) {
		// This will never get called on client side

	}

	public void RegisterModEntity(Item item, Class<? extends Entity> theclass, int id) {

		EntityRegistry.registerModEntity(new ResourceLocation(Ref.MODID, theclass.getName()), theclass,
				Ref.MODID + ":" + theclass.getName(), id, Main.instance, 64, 10, true);

		RenderingRegistry.registerEntityRenderingHandler(theclass,
				renderManager -> new RenderSnowball<>(renderManager, item, Minecraft.getMinecraft().getRenderItem()));

	}

	@Override
	public String translate(String str) {
		return I18n.format(str);
	}

	@Override
	public void setShieldRenderer(NormalShield normalShield) {
		normalShield.setTileEntityItemStackRenderer(new ShieldRenderer());
	}

}
