package com.robertx22.customitems.currency;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Nullable;

import com.robertx22.db_lists.CreativeTabList;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.utilityclasses.ITiered;
import com.robertx22.uncommon.utilityclasses.IWeighted;
import com.robertx22.uncommon.utilityclasses.RegisterItemUtils;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class CurrencyItem extends Item implements IWeighted, ITiered {

    public static HashSet<CurrencyItem> ITEMS = new HashSet<CurrencyItem>();

    public abstract String GUID();

    public CurrencyItem(String name) {
	setMaxDamage(0);
	maxStackSize = 64;
	setCreativeTab(CreativeTabList.CurrencyTab);
	RegisterItemUtils.RegisterItemName(this, name);

	ITEMS.add(this);

    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

	tooltip.add(CLOC.tooltip(GUID()));
	tooltip.add(CLOC.lore(GUID()));

    }

    @Override
    public int Weight() {
	return this.UncommonWeight;
    }

}
