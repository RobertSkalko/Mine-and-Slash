package com.robertx22.mine_and_slash.database.unique_items.chest.plate;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.PlateChest;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.CoreStatFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Stamina;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class ChestNature implements IUnique {

    public ChestNature() {

    }

    static StatReq req = new StatReq(LvlPointStat.VITALITY, StatReq.Size.NORMAL);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int Tier() {
        return 11;

    }

    @Override
    public String GUID() {
        return "chestnature0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new HealthRegenFlat().size(StatMod.Size.HALF_MORE), new ElementalResistFlat(Elements.Nature), new CoreStatFlat(Stamina.INSTANCE)

        );
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new HealthFlat().size(StatMod.Size.HALF_MORE));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Wooden Plate";
    }

    @Override
    public String locDescForLangFile() {
        return "Do not try move me.";
    }

    @Override
    public GearItemSlot getGearSlot() {
        return PlateChest.INSTANCE;
    }
}