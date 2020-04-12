package com.robertx22.mine_and_slash.database.unique_items.staffs;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.Staff;
import com.robertx22.mine_and_slash.database.spells.spell_classes.fire.FireballSpell;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.misc.PlusAbiliyLevelFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.LifeOnHitFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalAttackDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalPeneFlat;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class StaffFire implements IUnique {

    public StaffFire() {

    }

    static StatReq req = new StatReq(LvlPointStat.INTELLIGENCE, StatReq.Size.MEDIUM, LvlPointStat.STRENGTH, StatReq.Size.SMALL);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int getTier() {
        return 2;
    }

    @Override
    public String GUID() {
        return "uniquestafffire0";
    }

    @Override
    public GearItemSlot getGearSlot() {
        return Staff.INSTANCE;
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(
            new CriticalDamageFlat(),
            new ElementalPeneFlat(Elements.Fire),
            new LifeOnHitFlat(),
            new PlusAbiliyLevelFlat(FireballSpell.getInstance())
        );
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalAttackDamageFlat(Elements.Fire));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Staff of Volcanoes";
    }

    @Override
    public String locDescForLangFile() {
        return "Leave only ashes behind.";
    }
}
