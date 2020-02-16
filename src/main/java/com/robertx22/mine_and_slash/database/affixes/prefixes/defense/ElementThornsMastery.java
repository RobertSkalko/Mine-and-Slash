package com.robertx22.mine_and_slash.database.affixes.prefixes.defense;

import com.robertx22.mine_and_slash.database.affixes.ElementalPrefix;
import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.items.unique_items.shields.ShieldElemental;
import com.robertx22.mine_and_slash.database.requirements.ExactUniquesRequierement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.generated.BlockReflectFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ElementThornsMastery extends ElementalPrefix {

    public ElementThornsMastery(Elements element) {
        super(new Requirements(new ExactUniquesRequierement(new ShieldElemental(element))), element);
    }

    @Override
    public Prefix newGeneratedInstance(Elements element) {
        return new ElementThornsMastery(element);
    }

    @Override
    public String GUID() {
        return "bonus_" + element.guidName + "_reflect_prefix";
    }

    @Override
    public int getRarityRank() {
        return IRarity.Epic;
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new BlockReflectFlat(element), new ElementalResistFlat(element));
    }

    @Override
    public String locNameForLangFile() {
        return element.dmgName + " Thorns Mastery";
    }

    @Override
    public List<Prefix> generateAllPossibleStatVariations() {
        List<Prefix> list = new ArrayList<>();
        new ShieldElemental(element).generateAllPossibleStatVariations()
                .forEach(x -> list.add(newGeneratedInstance(((ShieldElemental) x).element)));
        return list;

    }
}
