package com.robertx22.mine_and_slash.database.unique_items.chest.leather;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.leather.LeatherChest;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.DodgeRatingFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.misc.PlusAllSkillLevelsInSchoolFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.WeaponDamageFlat;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class ChestDodge implements IUnique {

    public ChestDodge() {

    }

    static StatReq req = new StatReq(LvlPointStat.DEXTERITY, StatReq.Size.NORMAL);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int getTier() {
        return 4;

    }

    @Override
    public GearItemSlot getGearSlot() {
        return LeatherChest.INSTANCE;
    }

    @Override
    public String GUID() {
        return "chestdodge0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(
            new ArmorFlat(),
            new WeaponDamageFlat(WeaponTypes.Bow),
            new ElementalResistFlat(Elements.Nature),
            new PlusAllSkillLevelsInSchoolFlat(Masteries.HUNTING)
        );
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new HealthFlat(), new DodgeRatingFlat().size(StatMod.Size.DOUBLE));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Thief's Chestplate";
    }

    @Override
    public String locDescForLangFile() {
        return "Come on, hit me!";
    }
}