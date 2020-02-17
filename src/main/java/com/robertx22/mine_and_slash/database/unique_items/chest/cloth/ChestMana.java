package com.robertx22.mine_and_slash.database.unique_items.chest.cloth;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.cloth.ClothChest;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.EnergyRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicShieldFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.conversions.EnergyToManaConvFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.WeaponDamageFlat;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class ChestMana implements IUnique {

    public ChestMana() {

    }

    static StatReq req = new StatReq(LvlPointStat.WISDOM, StatReq.Size.NORMAL);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int Tier() {
        return 11;

    }

    @Override
    public GearItemSlot getGearSlot() {
        return ClothChest.INSTANCE;
    }

    @Override
    public String GUID() {
        return "chestmana0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new EnergyToManaConvFlat(), new ManaFlat(), new EnergyRegenFlat(), new WeaponDamageFlat(WeaponTypes.Staff));
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new MagicShieldFlat());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Shirt of the Arcane";
    }

    @Override
    public String locDescForLangFile() {
        return "The process of multiplying energy has just begun!";
    }
}