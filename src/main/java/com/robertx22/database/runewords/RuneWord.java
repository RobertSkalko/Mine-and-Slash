package com.robertx22.database.runewords;

import java.util.List;

import com.robertx22.customitems.runes.base.BaseRuneItem;
import com.robertx22.stats.StatMod;

public abstract class RuneWord {

    public abstract List<StatMod> mods();

    public abstract String name();

    public abstract List<BaseRuneItem> runes();

    public int size() {
	return runes().size();
    }

    public String getRuneWordCombo() {

	String text = "";

	for (BaseRuneItem item : runes()) {
	    text += item.name().toUpperCase();
	}
	return text;
    }

    public boolean runesMatch(String word) {
	return this.getRuneWordCombo().equals(word);
    }

}
