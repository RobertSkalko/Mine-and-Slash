package com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs;

import com.google.common.base.Preconditions;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.level_based_numbers.LevelBased;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.saveclasses.spells.calc.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;

import java.util.HashMap;

// this class should be easy to serialize as a config
// synergies add to these values.
public class PreCalcSpellConfigs {

    public PreCalcSpellConfigs() {
    }

    private HashMap<SC, LevelBased> map = new HashMap<>();

    public int maxSpellLevel = 12;

    public SpellCalcData getCalc(PlayerSpellCap.ISpellsCap cap, IAbility ability) {
        return SpellCalcData.scaleWithAttack(
            get(SC.ATTACK_SCALE_VALUE).get(cap, ability),
            get(SC.BASE_VALUE).get(cap, ability)
        );
    }

    public void set(SC sc, float min, float max) {

        if (map.containsKey(sc)) {
            try {
                throw new RuntimeException("Trying to set an already set value!!!");
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }

        map.put(sc, new LevelBased(min, max));
    }

    public void setMaxLevel(int lvl) {
        this.maxSpellLevel = lvl;
    }

    public LevelBased get(SC sc) {

        if (!map.containsKey(sc)) {

            try {
                throw new RuntimeException("Trying to get non existent value!!!");
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }

        LevelBased thing = map.get(sc);

        if (thing.isEmpty()) {
            try {
                throw new RuntimeException("Getting empty value!!!");
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }

        return thing;

    }

    /*
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


     */

    private boolean modifiedBySynergies = false;

    public void modifyBySynergies(BaseSpell spell, PlayerSpellCap.ISpellsCap cap) {

        Preconditions.checkArgument(
            modifiedBySynergies == false,
            "Cannot modify same spell calc config instance twice with synergies!,Make sure you're returning new config instances on each method call!!!");

        spell.getAllocatedSynergies(cap)
            .forEach(x -> {
                PreCalcSpellConfigs sc = x.getConfigsAffectingSpell();

                sc.map.entrySet()
                    .forEach(e -> {

                        this.map.g

                    });

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
