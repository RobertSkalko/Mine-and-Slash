package com.robertx22.mine_and_slash.saveclasses.spells;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public interface IAbility extends IGUID {
    public enum Type {
        SPELL, SYNERGY, EFFECT
    }

    PreCalcSpellConfigs getPreCalcConfig();

    ResourceLocation getIconLoc();

    public Type getAbilityType();

    public default float getLevelPowerMulti(PlayerSpellCap.ISpellsCap cap) {
        return cap.getLevelOf(this) / getMaxSpellLevelBuffed();
    }

    public int getMaxSpellLevelNormal();

    public int getMaxSpellLevelBuffed();

    public AbilityPlace getAbilityPlace();

    @Nullable
    public BaseSpell getSpell();

    @Nullable
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
