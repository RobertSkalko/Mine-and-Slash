package com.robertx22.saveclasses.gearitem;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.database.MinMax;
import com.robertx22.db_lists.StatMods;
import com.robertx22.generation.StatGen;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.gear_bases.ITooltipString;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.stats.Trait;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.enumclasses.StatTypes;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.TextFormatting;

@Storable
public class StatModData implements ITooltipString {

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

    public void useOnPlayer(UnitData unit) {
	String guid = this.GetBaseMod().GetBaseStat().Guid();
	if (unit.getUnit().MyStats.containsKey(guid)) {
	    unit.getUnit().MyStats.get(guid).Add(this, unit.getLevel());
	}
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

    public static String STAT_PREFIX = " ● ";

    public String NameText(boolean IsSet) {
	StatMod mod = GetBaseMod();
	Stat basestat = mod.GetBaseStat();

	String str = basestat.localizedString();

	if (mod.Type().equals(StatTypes.Percent) && basestat.IsPercent()) {
	    str += " " + CLOC.word("percent");
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
	return TextFormatting.GREEN + STAT_PREFIX + basestat.localizedString();
    }

    public String NameAndValueText(int level, boolean IsSet) {

	int val = this.GetActualVal(level);

	String minusplus = val > 0 ? "+" : "";

	return NameText(IsSet) + minusplus + val;
    }

    @Override
    public List<String> GetTooltipString(MinMax minmax, int level, boolean IsNotSet) {

	List<String> list = new ArrayList<String>();
	StatMod mod = GetBaseMod();
	Stat basestat = mod.GetBaseStat();
	String text = "";

	if (!(basestat instanceof Trait)) {

	    text = NameAndValueText(level, IsNotSet);

	    if (mod.Type() == StatTypes.Flat) {

		if (basestat.IsPercent()) {
		    text += "%";
		}

	    } else if (mod.Type() == StatTypes.Percent) {
		text += "%";
	    } else {
		text += "% " + CLOC.word("multi");
	    }

	    if (GuiScreen.isShiftKeyDown() && IsNotSet) {

		StatModData min = StatModData.Load(this.GetBaseMod(), minmax.Min);
		StatModData max = StatModData.Load(this.GetBaseMod(), minmax.Max);

		text += TextFormatting.BLUE + " (" + min.GetActualVal(level) + " - " + max.GetActualVal(level) + ")";

	    }
	    list.add(text);

	    return list;

	} else {

	    text = TraitText();
	    Trait trait = (Trait) basestat;

	    if (GuiScreen.isShiftKeyDown()) {
		text += " " + TextFormatting.GRAY + trait.Description();
	    }

	    list.add(text);

	    if (GuiScreen.isShiftKeyDown()) {

		for (StatModData motdata : trait.getStatsMods()) {
		    list.addAll(
			    motdata.GetTooltipString(new MinMax(trait.percent(), trait.percent()), level, IsNotSet));
		}

	    }

	}

	return list;
    }

}