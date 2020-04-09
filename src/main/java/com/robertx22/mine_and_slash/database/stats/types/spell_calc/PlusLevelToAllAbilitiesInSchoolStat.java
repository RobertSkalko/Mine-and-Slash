package com.robertx22.mine_and_slash.database.stats.types.spell_calc;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.List;

public class PlusLevelToAllAbilitiesInSchoolStat extends Stat implements IGenerated<PlusLevelToAllAbilitiesInSchoolStat> {

    private SpellSchools school;

    public PlusLevelToAllAbilitiesInSchoolStat(SpellSchools school) {
        this.school = school;
    }

    public SpellSchools getSchool() {
        return school;
    }

    @Override
    public boolean IsPercent() {
        return false;
    }

    @Override
    public StatScaling getScaling() {
        return StatScaling.NONE;
    }

    @Override
    public Elements getElement() {
        return null;
    }

    @Override
    public String locDescForLangFile() {
        return "Increase all allocated spell levels of that school of magic, but can't go over a certain amount.";
    }

    @Override
    public String locNameForLangFile() {
        return "To " + school.locName.locNameForLangFile() + " Skill Levels";
    }

    @Override
    public String locDescLangFileGUID() {
        return Ref.MODID + ".stat_desc." + "plus_all_skill_levels";
    }

    @Override
    public String GUID() {
        return "plus_" + school.id + "_skill_lvls";
    }

    @Override
    public List<PlusLevelToAllAbilitiesInSchoolStat> generateAllPossibleStatVariations() {
        List<PlusLevelToAllAbilitiesInSchoolStat> list = new ArrayList<>();

        for (SpellSchools value : SpellSchools.values()) {
            list.add(new PlusLevelToAllAbilitiesInSchoolStat(value));
        }

        return list;
    }

    public static void calcBonusAbilityLevelStats(PlayerSpellCap.ISpellsCap spells, Unit unit) {

        // i really don't like how this looks...
        new PlusLevelToAllAbilitiesInSchoolStat(SpellSchools.OCEAN_MYSTIC).generateAllPossibleStatVariations()
            .forEach(x -> {
                if (unit.hasStat(x)) {
                    int lvls = (int) unit.peekAtStat(x)
                        .getAverageValue();
                    if (lvls > 0) {
                        spells.getAbilitiesData()
                            .addBonusAbilityLevelsTo(x.getSchool(), lvls);
                    }

                }
            });
    }
}
