package com.robertx22.customitems.runes.base;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

import com.robertx22.customitems.currency.ICurrencyItemEffect;
import com.robertx22.database.rarities.RuneRarity;
import com.robertx22.database.stat_mods.flat.elemental.pene.FirePeneFlat;
import com.robertx22.database.stat_mods.flat.elemental.pene.NaturePeneFlat;
import com.robertx22.database.stat_mods.flat.elemental.pene.ThunderPeneFlat;
import com.robertx22.database.stat_mods.flat.elemental.pene.WaterPeneFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.FireResistFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.NatureResistFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.ThunderResistFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.WaterResistFlat;
import com.robertx22.database.stat_mods.flat.elemental.spell_dmg.SpellFireDamageFlat;
import com.robertx22.database.stat_mods.flat.elemental.spell_dmg.SpellNatureDamageFlat;
import com.robertx22.database.stat_mods.flat.elemental.spell_dmg.SpellThunderDamageFlat;
import com.robertx22.database.stat_mods.flat.elemental.spell_dmg.SpellWaterDamageFlat;
import com.robertx22.database.stat_mods.multi.elemental.damage.SpellFireDamageMulti;
import com.robertx22.database.stat_mods.multi.elemental.damage.SpellNatureDamageMulti;
import com.robertx22.database.stat_mods.multi.elemental.damage.SpellThunderDamageMulti;
import com.robertx22.database.stat_mods.multi.elemental.damage.SpellWaterDamageMulti;
import com.robertx22.database.stat_mods.percent.pene.FirePenePercent;
import com.robertx22.database.stat_mods.percent.pene.NaturePenePercent;
import com.robertx22.database.stat_mods.percent.pene.ThunderPenePercent;
import com.robertx22.database.stat_mods.percent.pene.WaterPenePercent;
import com.robertx22.database.stat_mods.percent.spell_ele_dmg.SpellFireDamagePercent;
import com.robertx22.database.stat_mods.percent.spell_ele_dmg.SpellNatureDamagePercent;
import com.robertx22.database.stat_mods.percent.spell_ele_dmg.SpellThunderDamagePercent;
import com.robertx22.database.stat_mods.percent.spell_ele_dmg.SpellWaterDamagePercent;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.rune.RuneItemData;
import com.robertx22.saveclasses.rune.RunesData;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.datasaving.Rune;
import com.robertx22.uncommon.utilityclasses.IWeighted;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BaseRuneItem extends Item implements IWeighted, ICurrencyItemEffect {

    public int rarity;

    @Override
    public int Weight() {
	return 1000;
    }

    public abstract BaseRuneItem byRarity(int rar);

    public BaseRuneItem(int rarity) {
	this.setMaxDamage(0);
	this.setMaxStackSize(1);
	this.rarity = rarity;

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack currency) {
	GearItemData gear = Gear.Load(stack);

	RuneItemData rune = Rune.Load(currency);

	gear.runes.insert(rune, gear);

	Gear.Save(stack, gear);

	return stack;

    }

    @Override
    public boolean canItemBeModified(ItemStack stack, ItemStack currency) {

	GearItemData gear = Gear.Load(stack);

	if (gear != null && gear.isRuned()) {
	    if (gear.runes == null) {
		gear.runes = new RunesData();
		Gear.Save(stack, gear);
	    }

	    RuneItemData rune = Rune.Load(currency);

	    if (rune != null) {
		return gear.runes.canFit(gear, rune);
	    }
	}

	return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

	RuneItemData rune = Rune.Load(stack);

	if (rune != null) {

	    tooltip.add(TextFormatting.YELLOW + CLOC.word("level") + ": " + rune.level);

	    RuneRarity rar = rune.GetRarity();

	    if (rune.armor != null) {
		tooltip.add(CLOC.tooltip("stats_on_armor") + ":");
		for (String str : rune.armor.GetTooltipString(rar.StatPercents(), rune.level, true)) {
		    tooltip.add(str);
		}
		tooltip.add("");
	    }
	    if (rune.weapon != null) {

		tooltip.add(CLOC.tooltip("stats_on_weapon") + ":");
		for (String str : rune.weapon.GetTooltipString(rar.StatPercents(), rune.level, true)) {
		    tooltip.add(str);
		}
	    }
	    if (rune.jewerly != null) {

		tooltip.add("");
		tooltip.add(CLOC.tooltip("stats_on_jewerly") + ":");
		for (String str : rune.jewerly.GetTooltipString(rar.StatPercents(), rune.level, true)) {
		    tooltip.add(str);
		}
		tooltip.add("");
	    }

	    tooltip.add(CLOC.word("rarity") + ": " + rune.GetRarity().Color() + rune.GetRarity().locName());

	}

    }

    public abstract String name();

    public abstract List<StatMod> weaponStat();

    public abstract List<StatMod> armorStat();

    public abstract List<StatMod> jewerlyStat();

    public List<StatMod> spellDamageFlats() {
	return Arrays.asList(new SpellFireDamageFlat(), new SpellWaterDamageFlat(), new SpellThunderDamageFlat(),
		new SpellNatureDamageFlat());
    }

    public List<StatMod> spellDamageMultis() {
	return Arrays.asList(new SpellFireDamageMulti(), new SpellWaterDamageMulti(), new SpellThunderDamageMulti(),
		new SpellNatureDamageMulti());
    }

    public List<StatMod> resistFlats() {
	return Arrays.asList(new FireResistFlat(), new WaterResistFlat(), new ThunderResistFlat(),
		new NatureResistFlat());
    }

    public List<StatMod> peneFlats() {
	return Arrays.asList(new FirePeneFlat(), new WaterPeneFlat(), new ThunderPeneFlat(), new NaturePeneFlat());

    }

    public List<StatMod> penePercents() {
	return Arrays.asList(new FirePenePercent(), new WaterPenePercent(), new ThunderPenePercent(),
		new NaturePenePercent());
    }

    public List<StatMod> spellDamagePercents() {
	return Arrays.asList(new SpellFireDamagePercent(), new SpellWaterDamagePercent(),
		new SpellThunderDamagePercent(), new SpellNatureDamagePercent());
    }

}
