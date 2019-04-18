package com.robertx22.customitems.misc;

import com.robertx22.customitems.currency.ICurrencyItemEffect;
import com.robertx22.database.runewords.RuneWord;
import com.robertx22.db_lists.CreativeTabList;
import com.robertx22.db_lists.RuneWords;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.utilityclasses.RegisterItemUtils;
import com.robertx22.uncommon.utilityclasses.RegisterUtils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class ItemAwakenRuneWord extends Item implements ICurrencyItemEffect {

    @GameRegistry.ObjectHolder(Ref.MODID + ":awaken_runeword")
    public static final Item ITEM = null;

    public ItemAwakenRuneWord() {

	RegisterItemUtils.RegisterItemName(this, "awaken_runeword");
	this.setMaxStackSize(64);
	this.setMaxDamage(0);
	this.setCreativeTab(CreativeTabList.MyModTab);

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	event.getRegistry().register(new ItemAwakenRuneWord());
    }

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
	RegisterUtils.registerRender(ITEM);
    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack currency) {

	if (currency.getItem() instanceof ItemAwakenRuneWord) {
	    GearItemData gear = Gear.Load(stack);
	    if (gear != null) {
		gear.runes.AwakenRuneWord();
	    }
	}

	return null;
    }

    public void setWord(ItemStack stack, RuneWord word) {
	NBTTagCompound nbt = stack.getTagCompound();
	if (nbt == null) {
	    nbt = new NBTTagCompound();
	}
	nbt.setString("runeword", word.name());

    }

    public String getWord(ItemStack stack) {

	try {
	    return stack.getTagCompound().getString("runeword");
	} catch (Exception e) {

	    e.printStackTrace();
	}
	return "";

    }

    @Override
    public boolean canItemBeModified(ItemStack item, ItemStack currency) {

	if (currency.getItem() instanceof ItemAwakenRuneWord) {
	    GearItemData gear = Gear.Load(item);

	    if (gear != null) {

		String wordtext = this.getWord(currency);

		if (RuneWords.All.containsKey(wordtext)) {

		    if (gear.isRuned() && gear.runes.canAwakenRuneWord(RuneWords.All.get(wordtext))) {
			return true;
		    }
		}
	    }
	}

	return false;
    }

}