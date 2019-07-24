package com.robertx22.mine_and_slash.database.unique_items.necklaces;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.less.LessHealthRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellToAttackDMGFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalTransferFlat;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueNecklace;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class NecklaceFire extends BaseUniqueNecklace {

    public NecklaceFire() {

    }

    @Override
    public int Tier() {
        return 10;
    }

    @Override
    public String GUID() {
        return "necklacefire0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ElementalSpellDamageFlat(Elements.Fire), new ElementalSpellToAttackDMGFlat(Elements.Fire), new ElementalTransferFlat(Elements.Water, Elements.Fire), new ElementalResistFlat(Elements.Water), new LessHealthRegenFlat());

    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Burning Man Amulet";
    }

    @Override
    public String locDescForLangFile() {
        return "I will take down my enemies with me, in flames.";
    }
}
