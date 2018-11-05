package com.robertx22.saveclasses.gearitem;

import java.io.Serializable;

import com.robertx22.database.lists.StatMods;
import com.robertx22.generation.StatGen;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.gear_bases.ITooltipString;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.stats.Trait;
import com.robertx22.uncommon.enumclasses.StatTypes;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.TextFormatting;

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

	public static StatModData Load(StatMod mod, int percent, int level) {

		StatModData data = new StatModData();

		data.baseModName = mod.GUID();
		data.type = mod.Type();
		data.percent = percent;

		return data;
	}

	public StatTypes type;
	public int percent;
	public String baseModName;

	public StatMod GetBaseMod() {
		return StatMods.All().get(baseModName);
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

	public String NameText() {
		StatMod mod = GetBaseMod();
		Stat basestat = mod.GetBaseStat();
		return TextFormatting.RED + " * " + basestat.Name() + ": ";
	}

	public String TraitText() {
		StatMod mod = GetBaseMod();
		Stat basestat = mod.GetBaseStat();
		return TextFormatting.GREEN + " * " + basestat.Name();
	}

	public String NameAndValueText(GearItemData gear) {

		int val = this.GetActualVal(gear.level);

		String minusplus = val > 0 ? "+" : "";

		return NameText() + minusplus + val;
	}

	@Override
	public String GetTooltipString(GearItemData gear) {
		StatMod mod = GetBaseMod();

		Stat basestat = mod.GetBaseStat();

		String text = "";

		if (!(basestat instanceof Trait)) {

			text = NameAndValueText(gear);

			if (mod.Type() == StatTypes.Flat) {

				if (basestat.IsPercent()) {
					text += "%";
				}

			} else if (mod.Type() == StatTypes.Percent) {
				text += "%";
			} else {
				text += "% Multi";
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