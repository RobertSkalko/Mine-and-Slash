package com.robertx22.mine_and_slash.database.unique_items.staffs;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.Staff;
import com.robertx22.mine_and_slash.database.spells.spell_classes.nature.PoisonBallSpell;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.misc.PlusAbiliyLevelFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthRegenFlat;
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

public class StaffNature implements IUnique {

    public StaffNature() {

    }

    static StatReq req = new StatReq(LvlPointStat.INTELLIGENCE, StatReq.Size.MEDIUM, LvlPointStat.VITALITY, StatReq.Size.SMALL);

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
        return "uniquestaffnature0";
    }

    @Override
    public GearItemSlot getGearSlot() {
        return Staff.INSTANCE;
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(
            new ElementalPeneFlat(Elements.Nature),
            new LifeOnHitFlat(),
            new HealthRegenFlat(),
            new PlusAbiliyLevelFlat(PoisonBallSpell.getInstance())
        );
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(
            new ElementalAttackDamageFlat(Elements.Nature));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Worldbreaker Staff";
    }

    @Override
    public String locDescForLangFile() {
        return "Won't break? Smash harder!";
    }
}
