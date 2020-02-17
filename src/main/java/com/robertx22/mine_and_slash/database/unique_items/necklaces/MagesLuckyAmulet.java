package com.robertx22.mine_and_slash.database.unique_items.necklaces;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.Necklace;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicShieldFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.LootTypeBonusFlat;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class MagesLuckyAmulet implements IUnique {

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
        return Arrays.asList(new ElementalResistFlat(Elements.Elemental).size(StatMod.Size.HIGH), new ManaFlat().size(StatMod.Size.HIGH),
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

    @Override
    public GearItemSlot getGearSlot() {
        return Necklace.INSTANCE;
    }
}
