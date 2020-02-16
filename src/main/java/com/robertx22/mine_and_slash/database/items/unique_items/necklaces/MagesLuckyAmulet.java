package com.robertx22.mine_and_slash.database.items.unique_items.necklaces;

import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueNecklace;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HighManaFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicShieldFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.HighElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.LootTypeBonusFlat;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class MagesLuckyAmulet extends BaseUniqueNecklace {

    static StatReq req = new StatReq(LvlPointStat.WISDOM, StatReq.Size.NORMAL);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int Tier() {
        return 18;
    }

    @Override
    public String GUID() {
        return "necklacegambler0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new HighElementalResistFlat(Elements.Elemental), new HighManaFlat(),
                             new LootTypeBonusFlat(LootType.UniqueItem)
        );
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new MagicShieldFlat());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Mage's Lucky Amulet";
    }

    @Override
    public String locDescForLangFile() {
        return "One more bet!";
    }
}
