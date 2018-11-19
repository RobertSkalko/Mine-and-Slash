package com.robertx22.mmorpg.proxy;

import com.robertx22.mmorpg.Keybinds;
import com.robertx22.mmorpg.Main;
import com.robertx22.mmorpg.Ref;
import com.robertx22.network.DamageNumberPackage;
import com.robertx22.network.EntityPackage;
import com.robertx22.network.ParticlePackage;
import com.robertx22.network.UnitPackage;
import com.robertx22.network.WorldPackage;
import com.robertx22.spells.aoe_projectile.AcidExplosion.EntityAcidExplosion;
import com.robertx22.spells.aoe_projectile.FlameExplosion.EntityFlameExplosion;
import com.robertx22.spells.aoe_projectile.FrostExplosion.EntityFrostExplosion;
import com.robertx22.spells.aoe_projectile.LightningExplosion.EntityLightningExplosion;
import com.robertx22.spells.projectile.acidbolt.EntityAcidBolt;
import com.robertx22.spells.projectile.firebolt.EntityFireBolt;
import com.robertx22.spells.projectile.frostbolt.EntityFrostBolt;
import com.robertx22.spells.projectile.thunderbolt.EntityThunderBolt;
import com.robertx22.uncommon.gui.mobs.HealthBarRenderer;
import com.robertx22.uncommon.gui.mobs.ToggleKeyBind;
import com.robertx22.uncommon.gui.player_overlays.BarsGUI;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
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

		MinecraftForge.EVENT_BUS.register(new UnitPackage.Handler());
		MinecraftForge.EVENT_BUS.register(new EntityPackage.Handler());
		MinecraftForge.EVENT_BUS.register(new DamageNumberPackage.Handler());
		MinecraftForge.EVENT_BUS.register(new ParticlePackage.Handler());
		MinecraftForge.EVENT_BUS.register(new WorldPackage.Handler());

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

	@Override
	public void RegisterEntityRenders() {
		RegisterModEntityClient(Items.SNOWBALL, EntityFrostBolt.class, 0);
		RegisterModEntityClient(Items.MAGMA_CREAM, EntityFireBolt.class, 1);
		RegisterModEntityClient(Items.SLIME_BALL, EntityAcidBolt.class, 2);
		RegisterModEntityClient(Items.GLOWSTONE_DUST, EntityThunderBolt.class, 3);

		RegisterModEntityClient(Items.SNOWBALL, EntityFrostExplosion.class, 4);
		RegisterModEntityClient(Items.MAGMA_CREAM, EntityFlameExplosion.class, 5);
		RegisterModEntityClient(Items.SLIME_BALL, EntityAcidExplosion.class, 6);
		RegisterModEntityClient(Items.GLOWSTONE_DUST, EntityLightningExplosion.class, 7);

	}

	private static void RegisterModEntityClient(Item item, Class<? extends Entity> theclass, int id) {

		EntityRegistry.registerModEntity(new ResourceLocation(Ref.MODID, theclass.getName()), theclass,
				Ref.MODID + ":" + theclass.getName(), id, Main.instance, 64, 10, true);

		RenderingRegistry.registerEntityRenderingHandler(theclass,
				renderManager -> new RenderSnowball<>(renderManager, item, Minecraft.getMinecraft().getRenderItem()));

	}

}
