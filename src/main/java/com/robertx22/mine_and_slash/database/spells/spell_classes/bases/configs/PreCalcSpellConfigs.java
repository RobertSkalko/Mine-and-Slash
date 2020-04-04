package com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.level_based_numbers.LevelBased;

// this class should be easy to serialize as a config
// make all of these null so it reminds me if i forget to initialize any variable from the Setup object.
public class PreCalcSpellConfigs {

    public PreCalcSpellConfigs(SetupPreCalcSpellConfigs setup) {

        this.spellAttackScalingValue = setup.attackScaleValue();
        this.spellBaseValue = setup.baseValue();

        this.manaCost = setup.manaCost();
        this.castTimeTicks = setup.castTimeTicks();
        this.timesToCast = setup.timesToCast();
        this.cooldownTicks = setup.cooldownTicks();
        this.summonedEntities = setup.summonedEntities();
        this.projectileCount = setup.projectileCount();
        this.duration = setup.durationTicks();
        this.shootSpeed = setup.shootSpeed();

    }

    public LevelBased spellBaseValue;
    public LevelBased spellAttackScalingValue;

    public LevelBased manaCost;
    public LevelBased projectileCount;
    public LevelBased castTimeTicks;
    public LevelBased shootSpeed;
    public LevelBased summonedEntities;
    public LevelBased duration;
    public LevelBased cooldownTicks;
    public LevelBased timesToCast; //most spells its casted once at end of cast time, others are casted during the whole cast duration

}
