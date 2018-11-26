package com.robertx22.unique_items.bases;

import java.util.HashMap;
import java.util.List;

import com.robertx22.database.IGUID;
import com.robertx22.db_lists.CreativeTabList;
import com.robertx22.mmorpg.Ref;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.utilityclasses.ITiered;
import com.robertx22.uncommon.utilityclasses.IWeighted;
import com.robertx22.uncommon.utilityclasses.RegisterUtils;

import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public interface BaseUniqueItem extends IWeighted, ITiered, IGUID {

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
			item.setRegistryName(Ref.MODID, ((BaseUniqueItem) item).GUID());
			item.setUnlocalizedName(Ref.MODID + ":" + ((BaseUniqueItem) item).GUID());
			item.setCreativeTab(CreativeTabList.UniqueItems);

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
