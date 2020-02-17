package com.robertx22.mine_and_slash.database.items.unique_items.chest.plate;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.PlateChest;
import com.robertx22.mine_and_slash.database.items.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellToAttackDMGFlat;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class ChestThunder implements IUnique {

    public ChestThunder() {

    }

    static StatReq req = new StatReq(LvlPointStat.INTELLIGENCE, StatReq.Size.NORMAL);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int Tier() {
        return 6;
    }

    @Override
    public String GUID() {
        return "chestthunder0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ElementalResistFlat(Elements.Thunder), new ArmorFlat().size(StatMod.Size.HIGH),
            new ElementalSpellDamageFlat(Elements.Thunder),
            new ElementalSpellToAttackDMGFlat(Elements.Thunder)
        );
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new HealthFlat());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Armor of the Thunderstorm";
    }

    @Override
    public String locDescForLangFile() {
        return "Those who dared to follow had long since died.";
    }

    @Override
    public GearItemSlot getGearSlot() {
        return PlateChest.INSTANCE;
    }
}