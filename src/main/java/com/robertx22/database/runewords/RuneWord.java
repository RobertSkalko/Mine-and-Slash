package com.robertx22.database.runewords;

import java.util.List;

import com.robertx22.customitems.runes.base.BaseRuneItem;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.utilityclasses.IWeighted;

public abstract class RuneWord implements IWeighted {

    public abstract List<StatMod> mods();

    public abstract String GUID();

    public abstract String unlocName();

    public String locName() {
	return CLOC.word(unlocName());
    }

    public abstract List<BaseRuneItem> runes();

    public int size() {
	return runes().size();
    }

    @Override
    public int Weight() {
	return 1000;
    }

    public String getRuneWordCombo() {

	String text = "";

	for (BaseRuneItem item : runes()) {
	    text += item.name().toUpperCase();
	}
	return text;
    }

    public String getRuneWordComboString() {

	String text = "";

	for (BaseRuneItem item : runes()) {
	    text += item.name().toUpperCase() + " + ";
	}
	text = text.substring(0, text.length() - 3);

	return text;
    }

    public boolean runesMatch(String word) {
	return this.getRuneWordCombo().equals(word);
    }

}
