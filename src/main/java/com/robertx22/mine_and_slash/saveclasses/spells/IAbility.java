package com.robertx22.mine_and_slash.saveclasses.spells;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public interface IAbility extends IGUID, ITooltipList {
    public enum Type {
        SPELL, SYNERGY, EFFECT
    }

    public static IAbility fromId(String id) {
        IAbility ability = null;

        if (SlashRegistry.Synergies()
            .isRegistered(id)) {
            ability = SlashRegistry.Synergies()
                .get(id);
        } else if (SlashRegistry.Spells()
            .isRegistered(id)) {
            ability = SlashRegistry.Spells()
                .get(id);
        } else if (SlashRegistry.PotionEffects()
            .isRegistered(id)) {
            ability = SlashRegistry.PotionEffects()
                .get(id);
        }

        return ability;
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
            return 1;
        }

        return SpellSchools.MAXIMUM_POINTS / AbilityPlace.MAX_Y * place.y;

    }

}
