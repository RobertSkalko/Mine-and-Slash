package com.robertx22.saveclasses.rune;

import com.robertx22.customitems.currency.CurrencyItem;
import com.robertx22.customitems.ores.ItemOre;
import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.rarities.RuneRarity;
import com.robertx22.db_lists.Rarities;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.ISalvagable;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Storable
public class RuneItemData implements ISalvagable {

    public RuneItemData() {

    }

    public RuneItemData(int level, String name, int rarity, StatModData weapon, StatModData armor,
	    StatModData jewerly) {

	this.name = name;
	this.level = level;
	this.rarity = rarity;
	this.weapon = weapon;
	this.armor = armor;
	this.jewerly = jewerly;
    }

    @Store
    public String name;

    @Store
    public int level = 1;

    @Store
    public StatModData weapon;
    @Store
    public StatModData armor;
    @Store
    public StatModData jewerly;

    public StatModData getModFor(GearItemData gear) {

	GearItemSlot slot = gear.GetBaseGearType();

	if (slot.slotType().equals(GearItemSlot.GearSlotType.Armor)) {
	    return armor;
	} else if (slot.slotType().equals(GearItemSlot.GearSlotType.Jewerly)) {
	    return jewerly;
	}

	return weapon;

    }

    @Store
    public int rarity = 0;

    @Override
    public int getSalvagedRarity() {
	return this.rarity;
    }

    public RuneRarity GetRarity() {

	return Rarities.Runes.get(rarity);

    }

    @Override
    public ItemStack getSalvageResult(float salvageBonus) {

	int min = tryIncreaseAmount(salvageBonus, 1);
	int max = tryIncreaseAmount(salvageBonus, 2);

	ItemStack stack = ItemStack.EMPTY;

	if (RandomUtils.roll(this.GetRarity().specialItemChance())) {

	    Item item = (Item) RandomUtils
		    .WeightedRandom(ListUtils.SameTierOrLess(ListUtils.CollectionToList(CurrencyItem.ITEMS), 10));

	    stack = new ItemStack(item);
	} else {

	    int amount = RandomUtils.RandomRange(min, max);

	    ItemOre ore = (ItemOre) ItemOre.ItemOres.get(rarity);

	    stack = new ItemStack(ore);
	    stack.setCount(amount);

	}

	return stack;
    }

}
