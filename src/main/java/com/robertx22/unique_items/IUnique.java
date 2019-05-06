package com.robertx22.unique_items;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.robertx22.customitems.gearitems.bases.BaseArmorItem;
import com.robertx22.customitems.gearitems.bases.IGearItem;
import com.robertx22.database.IGUID;
import com.robertx22.db_lists.CreativeTabList;
import com.robertx22.mmorpg.Ref;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.utilityclasses.ITiered;
import com.robertx22.uncommon.utilityclasses.IWeighted;
import com.robertx22.uncommon.utilityclasses.RegisterUtils;

import baubles.api.IBauble;
import net.minecraft.item.Item;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public interface IUnique extends IWeighted, ITiered, IGUID, IGearItem {

    public static HashMap<String, Item> ITEMS = new HashMap<String, Item>();

    static List<IUnique> all = new ArrayList();

    public static List<IUnique> getAll() {
        if (all.isEmpty()) {
            for (Item item : ITEMS.values()) {
                IUnique uniq = (IUnique) item;
                all.add(uniq);
            }

        }
        return all;
    }

    @Override
    public default int Weight() {
        return this.UncommonWeight;
    }

    public default String locName() {
        return CLOC.uniqueName(this.GUID());
    }

    public default String locDesc() {
        return CLOC.uniqueDesc(this.GUID());
    }

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
	    IUnique uniq = (IUnique) item;

	    item.setRegistryName("uniques/" + uniq.slot().toLowerCase() + "/" + uniq.GUID());

	    item.setUnlocalizedName(Ref.MODID + ".unique." + uniq.GUID());// i kinda fked up here

	    // System.out.println(item.getUnlocalizedName());

	    event.getRegistry().register(item);
	}
    }

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
	for (Item item : ITEMS.values()) {
	    RegisterUtils.registerRender(item);
	}
    }

    public static List<IUnique> getAllUniquesOfTier(int tier, Collection<Item> coll) {
        List<IUnique> list = new ArrayList<IUnique>();

        for (Item item : coll) {
            IUnique baseu = (IUnique) item;

            if (tier == baseu.Tier()) {
                list.add((IUnique) item);
            }
        }
        return list;
    }

    public static List<IUnique> getAllPossibleUniqueDrops(int tier,
                                                          Collection<Item> coll) {
        List<IUnique> list = new ArrayList<IUnique>();

        for (Item item : coll) {
            IUnique baseu = (IUnique) item;

            if (tier >= baseu.Tier()) {
                list.add((IUnique) item);
            }
        }
        return list;
    }

    public static List<IUnique> filterUniquesByType(String type, List<IUnique> coll) {

        List<IUnique> list = new ArrayList<IUnique>();

        for (IUnique item : coll) {
            if (item.slot().equals(type) || type.equals("random") || type.equals("")) {
                list.add((IUnique) item);
            }
        }

        return list;
    }

}