package com.robertx22.unique_items;

import java.util.HashMap;
import java.util.List;

import com.robertx22.customitems.gearitems.bases.BaseArmorItem;
import com.robertx22.database.IGUID;
import com.robertx22.db_lists.CreativeTabList;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.utilityclasses.ITiered;
import com.robertx22.uncommon.utilityclasses.IWeighted;
import com.robertx22.uncommon.utilityclasses.RegisterItemUtils;
import com.robertx22.uncommon.utilityclasses.RegisterUtils;

import baubles.api.IBauble;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public interface IUnique extends IWeighted, ITiered, IGUID {

	public static HashMap<String, Item> ITEMS = new HashMap<String, Item>();

	@Override
	public default int Weight() {
		return this.UncommonWeight;
	}

	String name();

	String description();

	List<StatMod> uniqueStats();

	String slot();

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		for (Item item : ITEMS.values()) {
			item.setCreativeTab(CreativeTabList.UniqueItems);
			item.setMaxStackSize(1);

			if (item instanceof IBauble) {
				item.setMaxDamage(0);
			} else {
				item.setMaxDamage(BaseArmorItem.MAX_GEAR_DURABILITY);
			}
			RegisterItemUtils.RegisterItemName(item, "uniques/" + ((IUnique) item).GUID());

			event.getRegistry().register(item);
		}
	}

	@SubscribeEvent
	public static void onModelRegistry(ModelRegistryEvent event) {
		for (Item item : ITEMS.values()) {
			RegisterUtils.registerRender(item);
		}
	}

}
