package com.robertx22.mmorpg.proxy;

import com.robertx22.customitems.gearitems.MyEntityArrow;
import com.robertx22.mmorpg.Main;
import com.robertx22.mmorpg.Ref;
import com.robertx22.spells.EntityStaffProjectileNormal;
import com.robertx22.spells.aoe_projectile.AcidExplosion.EntityAcidExplosion;
import com.robertx22.spells.aoe_projectile.FlameExplosion.EntityFlameExplosion;
import com.robertx22.spells.aoe_projectile.FrostExplosion.EntityFrostExplosion;
import com.robertx22.spells.aoe_projectile.LightningExplosion.EntityLightningExplosion;
import com.robertx22.spells.projectile.acidbolt.EntityAcidBolt;
import com.robertx22.spells.projectile.firebolt.EntityFireBolt;
import com.robertx22.spells.projectile.frostbolt.EntityFrostBolt;
import com.robertx22.spells.projectile.thunderbolt.EntityThunderBolt;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
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

    @Override
    public void RegisterEntityRenders() {

	RegisterModEntityServer(Items.SNOWBALL, EntityFrostBolt.class, 0);
	RegisterModEntityServer(Items.MAGMA_CREAM, EntityFireBolt.class, 1);
	RegisterModEntityServer(Items.SLIME_BALL, EntityAcidBolt.class, 2);
	RegisterModEntityServer(Items.GLOWSTONE_DUST, EntityThunderBolt.class, 3);

	RegisterModEntityServer(Items.SNOWBALL, EntityFrostExplosion.class, 4);
	RegisterModEntityServer(Items.MAGMA_CREAM, EntityFlameExplosion.class, 5);
	RegisterModEntityServer(Items.SLIME_BALL, EntityAcidExplosion.class, 6);
	RegisterModEntityServer(Items.GLOWSTONE_DUST, EntityLightningExplosion.class, 7);
	RegisterModEntityServer(Items.ENDER_PEARL, EntityStaffProjectileNormal.class, 8);

	RegisterModEntityServer(Items.ARROW, MyEntityArrow.class, 9);

    }

    private static void RegisterModEntityServer(Item item, Class<? extends Entity> theclass, int id) {

	EntityRegistry.registerModEntity(new ResourceLocation(Ref.MODID, theclass.getName()), theclass,
		Ref.MODID + ":" + theclass.getName(), id, Main.instance, 64, 10, true);

    }

    @Override
    public String translate(String str) {
	return "TRANSLATION PROXY ERROR";
    }
}