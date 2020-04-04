package com.robertx22.mine_and_slash.saveclasses.spells;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;

public interface IAbility extends IGUID {
    public enum Type {
        SPELL, SYNERGY
    }

    public Type getAbilityType();

    public int getMaxSpellLevelNormal();

    public int getMaxSpellLevelBuffed();

    public AbilityPlace getAbilityPlace();

    public IAbility getRequiredAbility();

    public SpellSchools getSchool();

    public default int getSchoolPointsNeeded() {

        AbilityPlace place = getAbilityPlace();

        if (place.y == 0) {
            return 0;
        }

        return SpellSchools.MAXIMUM_POINTS / AbilityPlace.MAX_Y * place.y;

    }

}
