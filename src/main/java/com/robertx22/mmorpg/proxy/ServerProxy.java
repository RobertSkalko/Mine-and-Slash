package com.robertx22.mmorpg.proxy;

import com.robertx22.mmorpg.Main;
import com.robertx22.mmorpg.Ref;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ServerProxy implements IProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {

	// DEBUG
	System.out.println("on Server side");

    }

    @Override
    public void init(FMLInitializationEvent event) {

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

    }

    @Override
    public void serverStarting(FMLServerStartingEvent event) {

    }

    @Override
    public EntityPlayer getPlayerEntityFromContext(MessageContext ctx) {
	return ctx.getServerHandler().player;
    }

    public void regArrow(Item item, Class<? extends Entity> theclass, int id) {
	EntityRegistry.registerModEntity(new ResourceLocation(Ref.MODID, theclass.getName()), theclass,
		Ref.MODID + ":" + theclass.getName(), id, Main.instance, 64, 10, true);
    }

    @Override
    public String translate(String str) {
	return "TRANSLATION PROXY ERROR";
    }

    @Override
    public void RegisterModEntity(Item item, Class<? extends Entity> theclass, int id) {

	EntityRegistry.registerModEntity(new ResourceLocation(Ref.MODID, theclass.getName()), theclass,
		Ref.MODID + ":" + theclass.getName(), id, Main.instance, 64, 10, true);

    }
}