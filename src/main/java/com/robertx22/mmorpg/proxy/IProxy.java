package com.robertx22.mmorpg.proxy;

import com.robertx22.customitems.gearitems.MyEntityArrow;
import com.robertx22.spells.aoe_bomb_proj.SpellAcidBomb;
import com.robertx22.spells.aoe_bomb_proj.SpellFireBomb;
import com.robertx22.spells.aoe_bomb_proj.SpellIceBomb;
import com.robertx22.spells.aoe_bomb_proj.SpellThunderBomb;
import com.robertx22.spells.aoe_projectile.AcidExplosion.EntityAcidExplosion;
import com.robertx22.spells.aoe_projectile.FlameExplosion.EntityFlameExplosion;
import com.robertx22.spells.aoe_projectile.FrostExplosion.EntityFrostExplosion;
import com.robertx22.spells.aoe_projectile.LightningExplosion.EntityLightningExplosion;
import com.robertx22.spells.bases.projectile.EntityStaffProjectile;
import com.robertx22.spells.projectile.acidbolt.EntityAcidBolt;
import com.robertx22.spells.projectile.firebolt.EntityFireBolt;
import com.robertx22.spells.projectile.frostbolt.EntityFrostBolt;
import com.robertx22.spells.projectile.thunderbolt.EntityThunderBolt;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public interface IProxy {

    default void s() {

    }

    default void registerRenders() {

	int i = 0;

	RegisterModEntity(Items.SNOWBALL, EntityFrostBolt.class, i++);
	RegisterModEntity(Items.MAGMA_CREAM, EntityFireBolt.class, i++);
	RegisterModEntity(Items.SLIME_BALL, EntityAcidBolt.class, i++);
	RegisterModEntity(Items.GLOWSTONE_DUST, EntityThunderBolt.class, i++);

	RegisterModEntity(Items.SNOWBALL, EntityFrostExplosion.class, i++);
	RegisterModEntity(Items.MAGMA_CREAM, EntityFlameExplosion.class, i++);
	RegisterModEntity(Items.SLIME_BALL, EntityAcidExplosion.class, i++);
	RegisterModEntity(Items.GLOWSTONE_DUST, EntityLightningExplosion.class, i++);

	RegisterModEntity(Items.ENDER_PEARL, EntityStaffProjectile.class, i++);

	regArrow(Items.ARROW, MyEntityArrow.class, i++);
	//
	RegisterModEntity(Items.MAGMA_CREAM, SpellFireBomb.EntityFireBomb.class, i++);
	RegisterModEntity(Items.SLIME_BALL, SpellAcidBomb.EntityAcidBomb.class, i++);
	RegisterModEntity(Items.GLOWSTONE_DUST, SpellThunderBomb.EntityThunderBomb.class, i++);
	RegisterModEntity(Items.SNOWBALL, SpellIceBomb.EntityIceBomb.class, i++);

    }

    void regArrow(Item item, Class<? extends Entity> theclass, int id);

    void RegisterModEntity(Item item, Class<? extends Entity> theclass, int id);

    String translate(String str);

    /**
     * Fml life cycle event for Pre-Initialization. Historically (before registry
     * events) this was where blocks, items, etc. were registered. There are still
     * things like entities and networking which should still be registered here.
     *
     * @param event the event
     */
    void preInit(FMLPreInitializationEvent event);

    /**
     * Fml life cycle event for Initialization. This phase is good for registering
     * event listeners, for registering things that depend on things in pre-init
     * from other mods (like recipes, advancements and such.)
     *
     * @param event the event
     */
    void init(FMLInitializationEvent event);

    /**
     * Fml life cycle event Post Initialization. This phase is useful For doing
     * inter-mod stuff like checking which mods are loaded or if you want a complete
     * view of things across mods like having a list of all registered items to aid
     * random item generation.
     *
     * @param event the event
     */
    void postInit(FMLPostInitializationEvent event);

    /**
     * Fml life cycle event. Server commands should be registered here.
     *
     * @param event the event
     */
    void serverStarting(FMLServerStartingEvent event);

    /*
     * Thanks to CoolAlias for this tip!
     */
    /**
     * Returns a side-appropriate EntityPlayer for use during message handling.
     *
     * @param parContext the context
     * @return the player entity from context
     */
    EntityPlayer getPlayerEntityFromContext(MessageContext parContext);

    // void RegisterEntityRenders();

}