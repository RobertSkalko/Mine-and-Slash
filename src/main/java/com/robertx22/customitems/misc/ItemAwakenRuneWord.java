package com.robertx22.customitems.misc;

import java.util.List;

import javax.annotation.Nullable;

import com.robertx22.customitems.currency.ICurrencyItemEffect;
import com.robertx22.database.runewords.RuneWord;
import com.robertx22.db_lists.RuneWords;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.utilityclasses.RegisterItemUtils;
import com.robertx22.uncommon.utilityclasses.RegisterUtils;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class ItemAwakenRuneWord extends Item implements ICurrencyItemEffect {

    @GameRegistry.ObjectHolder(Ref.MODID + ":awaken_runeword")
    public static final Item ITEM = null;

    public ItemAwakenRuneWord() {

	RegisterItemUtils.RegisterItemName(this, "awaken_runeword");
	this.setMaxStackSize(64);
	this.setMaxDamage(0);
	// this.setCreativeTab(CreativeTabList.MyModTab);

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

	if (stack != null && RuneWords.All.containsKey(this.getWord(stack))) {
	    tooltip.add("");
	    tooltip.add(CLOC.word("runeword") + ":");

	    String word = this.getWord(stack);

	    RuneWord runeword = RuneWords.All.get(word);

	    tooltip.add(TextFormatting.GOLD + runeword.locName());

	    tooltip.add(TextFormatting.GREEN + runeword.getRuneWordComboString());

	    tooltip.add(TextFormatting.AQUA + "Runes: " + runeword.size());

	    tooltip.add("");
	}
	tooltip.add(CLOC.tooltip("place_in_modify"));
	tooltip.add(CLOC.tooltip("unlocks_runeword_combo"));
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
		gear.runes.AwakenRuneWord(this.getWord(currency));
		Gear.Save(stack, gear);
	    }
	}

	return stack;
    }

    public void setWord(ItemStack stack, RuneWord word) {
	NBTTagCompound nbt = stack.getTagCompound();
	if (nbt == null) {
	    nbt = new NBTTagCompound();
	}
	nbt.setString("runeword", word.GUID());
	stack.setTagCompound(nbt);

    }

    public String getWord(ItemStack stack) {

	if (stack != null && stack.hasTagCompound() && stack.getTagCompound().hasKey("runeword")) {
	    return stack.getTagCompound().getString("runeword");
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