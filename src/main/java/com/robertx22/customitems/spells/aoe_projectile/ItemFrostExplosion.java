package com.robertx22.customitems.spells.aoe_projectile;

import com.robertx22.customitems.gearitems.bases.BaseSpellItem;
import com.robertx22.mmorpg.Ref;
import com.robertx22.spells.aoe_projectile.SpellFrostExplosion;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.uncommon.utilityclasses.RegisterUtils;

import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class ItemFrostExplosion extends BaseSpellItem {

    public ItemFrostExplosion() {
	super();
    }

    @GameRegistry.ObjectHolder(Ref.MODID + ":spell_frostexplosion")
    public static final Item ITEM = null;

    @Override
    public BaseSpell Spell() {
	return new SpellFrostExplosion();
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new ItemFrostExplosion());
    }

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
	RegisterUtils.registerRender(ITEM);
    }

    @Override
    public String GUID() {
	return Ref.MODID + ":spell_frostexplosion";
    }

}
