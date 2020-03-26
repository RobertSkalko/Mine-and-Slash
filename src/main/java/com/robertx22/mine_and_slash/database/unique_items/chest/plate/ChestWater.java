package com.robertx22.mine_and_slash.database.unique_items.chest.plate;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.PlateChest;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.CoreStatFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalPeneFlat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Stamina;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class ChestWater implements IUnique {

    public ChestWater() {

    }

    static StatReq req = new StatReq(LvlPointStat.INTELLIGENCE, StatReq.Size.NORMAL);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int Tier() {
        return 7;
    }

    @Override
    public String GUID() {
        return "chestwater0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ElementalPeneFlat(Elements.Water).size(StatMod.Size.HIGH), new ArmorFlat().size(StatMod.Size.HIGH), new CoreStatFlat(Stamina.INSTANCE)

        );
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new HealthFlat());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Armor of the Glacier";
    }

    @Override
    public String locDescForLangFile() {
        return "I am a Fortress of pure Ice.";
    }

    @Override
    public GearItemSlot getGearSlot() {
        return PlateChest.INSTANCE;
    }
}