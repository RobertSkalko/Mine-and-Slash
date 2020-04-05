package com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs;

import com.google.common.base.Preconditions;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.level_based_numbers.LevelBased;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;

// this class should be easy to serialize as a config
// all values must be ZERO, because synergies add to these values. If i use them, must change the value
public class PreCalcSpellConfigs {

    public PreCalcSpellConfigs() {
    }

    public int maxSpellLevel = 12;

    public LevelBased spellBaseValue = LevelBased.empty();
    public LevelBased spellAttackScalingValue = LevelBased.empty();

    public LevelBased manaCost = LevelBased.empty();
    public LevelBased radius = LevelBased.empty();
    public LevelBased projectileCount = LevelBased.empty()
        .min(1);
    public LevelBased castTimeTicks = LevelBased.empty();
    public LevelBased shootSpeed = LevelBased.empty()
        .min(0.05F);
    public LevelBased summonedEntities = LevelBased.empty()
        .min(1);
    public LevelBased duration = LevelBased.empty()
        .min(1);
    public LevelBased cooldownTicks = LevelBased.empty();
    public LevelBased timesToCast = LevelBased.empty()
        .min(1); //most spells its casted once at end of cast time, others are casted during the whole cast duration

    private boolean modifiedBySynergies = false;

    public void modifyBySynergies(BaseSpell spell, PlayerSpellCap.ISpellsCap cap) {

        Preconditions.checkArgument(
            modifiedBySynergies == false,
            "Cannot modify same spell calc config instance twice with synergies!,Make sure you're returning new config instances on each method call!!!");

        spell.getAllocatedSynergies(cap)
            .forEach(x -> {
                PreCalcSpellConfigs sc = x.getConfigsAffectingSpell();

                spellBaseValue.modifyBy(sc.spellBaseValue);
                spellAttackScalingValue.modifyBy(sc.spellAttackScalingValue);

                manaCost.modifyBy(sc.manaCost);
                radius.modifyBy(sc.radius);
                projectileCount.modifyBy(sc.projectileCount);
                castTimeTicks.modifyBy(sc.castTimeTicks);
                shootSpeed.modifyBy(sc.shootSpeed);
                summonedEntities.modifyBy(sc.summonedEntities);
                duration.modifyBy(sc.duration);
                cooldownTicks.modifyBy(sc.cooldownTicks);
                timesToCast.modifyBy(sc.timesToCast);

            });

        this.modifiedBySynergies = true;

    }

}
