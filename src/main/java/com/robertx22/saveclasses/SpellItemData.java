package com.robertx22.saveclasses;

import com.robertx22.customitems.currency.CurrencyItem;
import com.robertx22.customitems.ores.ItemOre;
import com.robertx22.database.rarities.SpellRarity;
import com.robertx22.db_lists.Rarities;
import com.robertx22.db_lists.Spells;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Storable
public class SpellItemData implements ISalvagable {

    public SpellItemData() {

    }

    @Override
    public int getSalvagedRarity() {
	return this.rarity;
    }

    public static int MIN_MANA_COST_PERCENT = 50;
    public static int MAX_MANA_COST_PERCENT = 100;

    @Store
    public int level = 1;
    @Store
    public String spellGUID;
    @Store
    public int rarity;
    @Store
    public int manaCostPercent = 100;
    @Store
    public int scalingEffectPercent = 100;
    @Store
    public int baseEffectPercent = 100;

    public int GetManaCost() {
	return this.GetSpell().ManaCost() * this.manaCostPercent / 100;
    }

    public int GetBaseValue() {
	return 1 + GetSpell().BaseValue() * level * baseEffectPercent / 100;
    }

    public float GetScalingValue() {
	return (GetSpell().ScalingValue().Multi * scalingEffectPercent / 100);
    }

    private int MinScaling() {
	return (int) (GetSpell().ScalingValue().Multi * getRarity().SpellScalingPercents().Min);
    }

    private int MaxScaling() {
	return (int) (GetSpell().ScalingValue().Multi * getRarity().SpellScalingPercents().Max);
    }

    private int MinBase() {
	return (int) (1 + GetSpell().BaseValue() * level * getRarity().SpellBasePercents().Min / 100);
    }

    private int MaxBase() {
	return (int) (1 + GetSpell().BaseValue() * level * getRarity().SpellBasePercents().Max / 100);
    }

    private int MinMana() {
	return this.GetSpell().ManaCost() * MIN_MANA_COST_PERCENT / 100;
    }

    private int MaxMana() {
	return this.GetSpell().ManaCost() * MAX_MANA_COST_PERCENT / 100;
    }

    public SpellRarity getRarity() {
	return Rarities.Spells.get(this.rarity);
    }

    public String GetScalingDesc(boolean moreInfo) {

	String text = CLOC.word("scaling_value") + ": " + GetSpell().ScalingValue().GetStat().localizedString() + " "
		+ CLOC.word("by") + " : " + (int) (GetScalingValue() * 100) + "%";

	if (moreInfo) {
	    text += "" + " (" + MinScaling() + "-" + MaxScaling() + ")";
	}

	return text;

    }

    public String GetBaseDesc(boolean moreInfo) {

	String text = CLOC.word("base_value") + ": " + this.GetBaseValue();

	if (moreInfo) {

	    text += "" + " (" + MinBase() + "-" + MaxBase() + ")";
	}

	return text;
    }

    public String GetManaDesc(boolean moreInfo) {

	String text = CLOC.word("mana_cost") + ": " + this.GetManaCost();

	if (moreInfo) {

	    text += "" + " (" + MinMana() + "-" + MaxMana() + ")";
	}
	return text;
    }

    public int GetDamage(Unit unit) {

	BaseSpell spell = GetSpell();

	int basedmg = spell.BaseValue() * baseEffectPercent / 100 * level;
	int scalingdmg = spell.ScalingValue().GetValue(unit) * scalingEffectPercent / 100;

	int total = basedmg + scalingdmg;

	int finalrandom = RandomUtils.RandomRange(1 + total / 2, 2 + total + total / 2);

	return finalrandom;

    }

    public BaseSpell GetSpell() {
	return Spells.All.get(spellGUID);
    }

    @Override
    public ItemStack getSalvageResult(float salvageBonus) {

	int min = tryIncreaseAmount(salvageBonus, 1);
	int max = tryIncreaseAmount(salvageBonus, 3);

	ItemStack stack = ItemStack.EMPTY;

	if (RandomUtils.roll(this.getRarity().specialItemChance())) {

	    Item item = (Item) RandomUtils
		    .WeightedRandom(ListUtils.SameTierOrLess(ListUtils.CollectionToList(CurrencyItem.ITEMS), 0));

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
