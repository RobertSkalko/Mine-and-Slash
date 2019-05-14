package com.robertx22.items.infusions;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.database.gearitemslots.bases.GearItemSlot.GearSlotType;
import com.robertx22.database.stats.StatMod;
import com.robertx22.items.currency.CurrencyItem;
import com.robertx22.items.currency.ICurrencyItemEffect;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.InfusionData;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

import net.minecraft.item.ItemStack;

public abstract class BaseInfusionItem extends CurrencyItem implements ICurrencyItemEffect {

    public BaseInfusionItem(String name) {
	super(name);

    }

    public void createInfusion(GearItemData gear) {

	gear.infusion = new InfusionData();

	List<StatMod> possible = new ArrayList();

	if (gear.GetBaseGearType().slotType().equals(GearSlotType.Armor)) {
	    possible = this.armorInfusions();
	} else if (gear.GetBaseGearType().slotType().equals(GearSlotType.Weapon)) {
	    possible = this.weaponInfusions();
	} else if (gear.GetBaseGearType().slotType().equals(GearSlotType.Jewerly)) {
	    possible = this.jewerlyInfusions();
	} else {
	    possible = this.jewerlyInfusions();
	}

	StatMod random = (StatMod) RandomUtils.WeightedRandom(ListUtils.CollectionToList(possible));

	gear.infusion.Mods = new ArrayList();

	gear.infusion.Mods.add(StatModData.Load(random, 0));

    }

    public abstract List<StatMod> weaponInfusions();

    public abstract List<StatMod> armorInfusions();

    public abstract List<StatMod> jewerlyInfusions();

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {
	GearItemData gear = Gear.Load(stack);

	this.createInfusion(gear);

	Gear.Save(stack, gear);
	return stack;
    }

    @Override
    public boolean canItemBeModified(ItemStack stack, ItemStack Currency) {
	GearItemData gear = Gear.Load(stack);

	return gear != null && !gear.isRuned();
    }

    @Override
    public int Tier() {
	return 5;
    }

    @Override
    public int Rank() {
	return 4;

    }

}