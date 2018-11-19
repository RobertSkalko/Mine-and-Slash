package com.robertx22.saveclasses.gearitem;

import java.io.Serializable;

import com.robertx22.database.lists.Rarities;
import com.robertx22.database.lists.StatMods;
import com.robertx22.generation.StatGen;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.gear_bases.ITooltipString;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.stats.Trait;
import com.robertx22.uncommon.enumclasses.StatTypes;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.TextFormatting;

@Storable
public class StatModData implements Serializable, ITooltipString {

	private static final long serialVersionUID = -274938432076951259L;

	public StatModData() {

	}

	public static StatModData NewRandom(GearItemData gear, StatMod mod) {

		StatModData data = new StatModData();

		data.baseModName = mod.GUID();
		data.type = mod.Type();
		data.percent = StatGen.GenPercent(gear.GetRarity());

		return data;
	}

	public static StatModData NewStatusEffect(int percent, StatMod mod) {

		StatModData data = new StatModData();

		data.baseModName = mod.GUID();
		data.type = mod.Type();
		data.percent = percent;

		return data;
	}

	public static StatModData Load(StatMod mod, int percent) {

		StatModData data = new StatModData();

		data.baseModName = mod.GUID();
		data.type = mod.Type();
		data.percent = percent;

		return data;
	}

	@Store
	public StatTypes type;

	@Store
	public int percent;

	@Store
	public String baseModName;

	public StatMod GetBaseMod() {
		return StatMods.All.get(baseModName);
	}

	public int GetActualVal(int level) {

		StatMod mod = GetBaseMod();

		Stat stat = mod.GetBaseStat();

		int val = mod.GetValueByPercent(percent);

		if (stat.ScalesToLevel() && mod.Type().equals(StatTypes.Flat)) {
			val *= level;
		}

		return val;

	}

	public static String STAT_PREFIX = " * ";

	public String NameText(boolean IsSet) {
		StatMod mod = GetBaseMod();
		Stat basestat = mod.GetBaseStat();

		String str = basestat.Name();

		if (mod.Type().equals(StatTypes.Percent) && basestat.IsPercent()) {
			str += " Percent";
		}

		if (IsSet) {
			return TextFormatting.RED + STAT_PREFIX + str + ": ";
		} else {
			return TextFormatting.RED + str + ": ";
		}
	}

	public String TraitText() {
		StatMod mod = GetBaseMod();
		Stat basestat = mod.GetBaseStat();
		return TextFormatting.GREEN + " * " + basestat.Name();
	}

	public String NameAndValueText(GearItemData gear, boolean IsSet) {

		int val = this.GetActualVal(gear.level);

		String minusplus = val > 0 ? "+" : "";

		return NameText(IsSet) + minusplus + val;
	}

	@Override
	public String GetTooltipString(int level, GearItemData gear, boolean IsNotSet) {
		StatMod mod = GetBaseMod();

		Stat basestat = mod.GetBaseStat();

		String text = "";

		if (!(basestat instanceof Trait)) {

			text = NameAndValueText(gear, IsNotSet);

			if (mod.Type() == StatTypes.Flat) {

				if (basestat.IsPercent()) {
					text += "%";
				}

			} else if (mod.Type() == StatTypes.Percent) {
				text += "%";
			} else {
				text += "% Multi";
			}

			if (GuiScreen.isShiftKeyDown() && IsNotSet) {

				StatModData min = StatModData.Load(this.GetBaseMod(),
						Rarities.Items.get(gear.Rarity).StatPercents().Min);
				StatModData max = StatModData.Load(this.GetBaseMod(),
						Rarities.Items.get(gear.Rarity).StatPercents().Max);

				text += TextFormatting.BLUE + " (" + min.GetActualVal(gear.level) + " - " + max.GetActualVal(gear.level)
						+ ")";
			}

		} else {

			text = TraitText();

			if (GuiScreen.isShiftKeyDown()) {

				Trait trait = (Trait) basestat;
				text += ": " + TextFormatting.GRAY + trait.Description();

			}
		}

		return text;
	}

}