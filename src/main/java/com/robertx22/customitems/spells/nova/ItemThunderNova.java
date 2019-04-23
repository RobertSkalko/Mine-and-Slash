package com.robertx22.customitems.spells.nova;

import com.robertx22.customitems.gearitems.bases.BaseSpellItem;
import com.robertx22.mmorpg.Ref;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.spells.nova.SpellThunderNova;
import com.robertx22.uncommon.utilityclasses.RegisterUtils;

import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class ItemThunderNova extends BaseSpellItem {

    public ItemThunderNova() {
	super();

    }

    @GameRegistry.ObjectHolder(Ref.MODID + ":spell_thunder_nova")
    public static final Item ITEM = null;

    @Override
    public BaseSpell Spell() {
	return new SpellThunderNova();
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new ItemThunderNova());
    }

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
	RegisterUtils.registerRender(ITEM);
    }

    @Override
    public String GUID() {
	return Ref.MODID + ":spell_thunder_nova";
    }

}
