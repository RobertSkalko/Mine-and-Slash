package com.robertx22.mine_and_slash.database.runewords;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.items.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.List;

public abstract class RuneWord implements IGUID, IWeighted, IAutoLocName, ISlashRegistryEntry<RuneWord> {

    public abstract List<StatMod> mods();

    public abstract String GUID();

    @Override
    public int Tier() {
        return 0;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Uncommon;
    }

    @Override
    public Rarity getRarity() {
        return Rarities.Runes.get(getRarityRank());
    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.RUNEWORD;
    }

    @Override
    public String locNameLangFileGUID() {
        return Ref.MODID + ".word." + formattedGUID();
    }

    public abstract List<BaseRuneItem> runes();

    public int size() {
        return runes().size();
    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Rune_Words;
    }

    @Override
    public int Weight() {
        return this.getRarity().Weight();
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
