package com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.level_based_numbers.LevelBased;

public class PreCalcSpellConfigs {

    public PreCalcSpellConfigs(float manaCost, LevelBased baseValue) {
        this.manaCost = manaCost;
        this.spellBaseValue = baseValue;
    }

    public LevelBased spellBaseValue;
    public LevelBased spellAttackScalingValue = new LevelBased(0, 0);

    public float manaCost;

    public LevelBased projectileCount = new LevelBased(1, 1);
    public LevelBased castTimeTicks = new LevelBased(10, 10);
    public LevelBased shootSpeed = new LevelBased(1, 1.5F);
    public LevelBased summonedEntities = new LevelBased(1, 1);
    public LevelBased duration = new LevelBased(20, 30);
    public LevelBased cooldownTicks = new LevelBased(30, 25);
    public LevelBased timesToCast = new LevelBased(1, 1); //most spells its casted once at end of cast time, others are casted during the whole cast duration

    public PreCalcSpellConfigs scaleWithAttack(LevelBased val) {
        this.spellAttackScalingValue = val;
        return this;
    }
}
