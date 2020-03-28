package com.robertx22.mine_and_slash.database.unique_items.necklaces;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.Necklace;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.CoreStatFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicShieldFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicShieldRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Wisdom;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class NecklaceMagicShield implements IUnique {

    public static NecklaceMagicShield INSTANCE = new NecklaceMagicShield();

    private NecklaceMagicShield() {

    }

    static StatReq req = new StatReq(
        LvlPointStat.INTELLIGENCE, StatReq.Size.MEDIUM, LvlPointStat.WISDOM, StatReq.Size.SMALL);

    @Override
    public GearItemSlot getGearSlot() {
        return Necklace.INSTANCE;
    }

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int Tier() {
        return 2;
    }

    @Override
    public String GUID() {
        return "necklace_magic_shield0";
    }

    @Override
    public int getRarityRank() {
        return IRarity.Epic;
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(
            new ElementalResistFlat(Elements.Elemental), new MagicShieldRegenFlat(),
            new CoreStatFlat(Wisdom.INSTANCE).size(StatMod.Size.HALF_MORE)
        );
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new MagicShieldFlat());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Amulet of Mental Fortitude";
    }

    @Override
    public String locDescForLangFile() {
        return "Preserve, prevail.";
    }
}
