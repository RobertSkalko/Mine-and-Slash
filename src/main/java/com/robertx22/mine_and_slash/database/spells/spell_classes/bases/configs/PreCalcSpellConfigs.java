package com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.level_based_numbers.LevelBased;

// this class should be easy to serialize as a config
public class PreCalcSpellConfigs {

    public PreCalcSpellConfigs() {
    }

    public int maxSpellLevel = 12;

    public LevelBased spellBaseValue = new LevelBased(0, 0);
    public LevelBased spellAttackScalingValue = new LevelBased(0, 0);

    public LevelBased manaCost = new LevelBased(0, 0);
    public LevelBased radius = new LevelBased(1, 1);
    public LevelBased projectileCount = new LevelBased(1, 1);
    public LevelBased castTimeTicks = new LevelBased(0, 0);
    public LevelBased shootSpeed = new LevelBased(1, 1);
    public LevelBased summonedEntities = new LevelBased(1, 1);
    public LevelBased duration = new LevelBased(0, 0);
    public LevelBased cooldownTicks = new LevelBased(0, 0);
    public LevelBased timesToCast = new LevelBased(1, 1); //most spells its casted once at end of cast time, others are casted during the whole cast duration

}
