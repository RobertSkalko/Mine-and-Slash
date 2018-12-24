package com.robertx22.customitems.infusions;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.database.gearitemslots.bases.GearItemSlot.GearSlotType;
import com.robertx22.db_lists.CreativeTabList;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.InfusionData;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import com.robertx22.uncommon.utilityclasses.RegisterItemUtils;

import net.minecraft.item.Item;

public abstract class BaseInfusionItem extends Item {

    public void createInfusion(GearItemData gear) {

	gear.infusion = new InfusionData();

	List<StatMod> possible = new ArrayList();

	if (gear.GetBaseGearType().slotType().equals(GearSlotType.Armor)) {
	    possible = this.armorInfusions();
	} else if (gear.GetBaseGearType().slotType().equals(GearSlotType.Weapon)) {
	    possible = this.weaponInfusions();
	} else if (gear.GetBaseGearType().slotType().equals(GearSlotType.Jewerly)) {
	    possible = this.jewerlyInfusions();
	}

	StatMod random = (StatMod) RandomUtils.WeightedRandom(ListUtils.CollectionToList(possible));

	gear.infusion.Mods = new ArrayList();

	gear.infusion.Mods.add(StatModData.Load(random, 0));

    }

    public abstract List<StatMod> weaponInfusions();

    public abstract List<StatMod> armorInfusions();

    public abstract List<StatMod> jewerlyInfusions();

    public BaseInfusionItem(String name) {
	RegisterItemUtils.RegisterItemName(this, name);
	setMaxDamage(0);
	maxStackSize = 64;
	setCreativeTab(CreativeTabList.CurrencyTab);
    }

}