package com.robertx22.mine_and_slash.database.unique_items.necklaces;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.Necklace;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.EnergyRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicShieldFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.AllElementalDamageMulti;
import com.robertx22.mine_and_slash.database.stats.mods.multi.offence.PhysicalDamageMulti;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class DoomdsayNecklace implements IUnique {

    static StatReq req = new StatReq(
        new StatReq(LvlPointStat.STRENGTH, StatReq.Size.TINY),
        new StatReq(LvlPointStat.STAMINA, StatReq.Size.TINY),
        new StatReq(LvlPointStat.WISDOM, StatReq.Size.TINY)
    );

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int getTier() {
        return 5;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
    }

    @Override
    public String GUID() {
        return "necklace_doomsday";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(
            new ManaRegenFlat().size(StatMod.Size.HALF_LESS),
            new EnergyRegenFlat().size(StatMod.Size.ONE_LESS),

            new AllElementalDamageMulti(Elements.Elemental).size(StatMod.Size.HALF_MORE),
            new PhysicalDamageMulti()
        );
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(
            new MagicShieldFlat().size(StatMod.Size.ONE_LESS),
            new HealthFlat().size(StatMod.Size.ONE_LESS));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Doomsday Pendant";
    }

    @Override
    public String locDescForLangFile() {
        return "I feel my creation was a mistake.";
    }

    @Override
    public GearItemSlot getGearSlot() {
        return Necklace.INSTANCE;
    }
}

