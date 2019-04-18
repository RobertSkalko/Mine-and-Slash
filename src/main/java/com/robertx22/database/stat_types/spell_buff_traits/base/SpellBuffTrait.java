package com.robertx22.database.stat_types.spell_buff_traits.base;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.database.MinMax;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.stats.IStatEffects;
import com.robertx22.stats.ITrait;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.enumclasses.Elements;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.TextFormatting;

public abstract class SpellBuffTrait extends Stat implements IStatEffects, ITrait {

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
    public String Description() {
	return CLOC.tooltip(this.unlocString());
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
	SpellBuffTrait trait = (SpellBuffTrait) basestat;

	list.add(text);

	if (GuiScreen.isShiftKeyDown()) {
	    list.add(" " + TextFormatting.GRAY + trait.Description());
	}

	return list;
    }
}
