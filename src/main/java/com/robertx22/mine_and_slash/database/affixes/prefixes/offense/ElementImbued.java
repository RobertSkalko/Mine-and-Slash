package com.robertx22.mine_and_slash.database.affixes.prefixes.offense;

import com.robertx22.mine_and_slash.database.affixes.ElementalPrefix;
import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellToAttackDMGFlat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class ElementImbued extends ElementalPrefix {

    public ElementImbued(Elements element) {
        super(new Requirements(SlotRequirement.jewerlyOnly(), LevelRequirement.fromLowLevel()), element);
    }

    @Override
    public String GUID() {
        return element.guidName + "_imbued";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new ElementalSpellToAttackDMGFlat(element));
    }

    @Override
    public String locNameForLangFile() {
        return element.dmgName + " Imbued";
    }

    @Override
    public Prefix newGeneratedInstance(Elements element) {
        return new ElementImbued(element);
    }
}

