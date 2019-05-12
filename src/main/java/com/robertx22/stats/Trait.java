package com.robertx22.stats;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.database.MinMax;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.enumclasses.Elements;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.TextFormatting;

public abstract class Trait extends Stat implements IAffectsOtherStats, ITrait {

    @Override
    public void TryAffectOtherStats(UnitData unit) {
	if (this.condition(unit)) {
	    for (StatModData mod : getStatsMods()) {
		mod.useOnPlayer(unit);
	    }
	}

    }

    // override if it has a condition
    public boolean condition(UnitData unit) {
	return true;
    }

    @Override
    public boolean ScalesToLevel() {
	return false;
    }

    @Override
    public Elements Element() {
	return null;
    }

    @Override
    public boolean IsPercent() {
	return false;
    }

    @Override
    public int CalcVal(StatData data, UnitData Source) {

	if (data.Flat > 0) {
	    data.Value = 1;

	    return 1;

	} else {
	    data.Value = 0;
	    return 0;
	}

    }

    public String TraitText(StatModData data) {
	StatMod mod = data.GetBaseMod();
	Stat basestat = mod.GetBaseStat();
	return TextFormatting.GREEN + STAT_PREFIX + basestat.localizedString();
    }

    @Override
    public List<String> getTooltipList(MinMax minmax, StatModData data, int level, boolean IsNotSet) {
	List<String> list = new ArrayList<String>();
	StatMod mod = data.GetBaseMod();
	Stat basestat = mod.GetBaseStat();
	String text = "";

	text = TraitText(data);
	Trait trait = (Trait) basestat;

	if (GuiScreen.isShiftKeyDown()) {
	    text += " " + TextFormatting.GRAY + trait.Description();
	}

	list.add(text);

	if (GuiScreen.isShiftKeyDown()) {

	    for (StatModData motdata : trait.getStatsMods()) {
		list.addAll(motdata.GetTooltipString(new MinMax(trait.percent(), trait.percent()), level, IsNotSet));
	    }

	}

	return list;
    }

}
