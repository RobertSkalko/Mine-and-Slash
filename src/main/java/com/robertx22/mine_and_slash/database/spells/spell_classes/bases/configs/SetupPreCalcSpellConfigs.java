package com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.level_based_numbers.LevelBased;

public abstract class SetupPreCalcSpellConfigs {

    public abstract LevelBased manaCost();

    public abstract LevelBased baseValue();

    public LevelBased attackScaleValue() {
        return new LevelBased(0, 0);
    }

    public LevelBased timesToCast() {
        return new LevelBased(1, 1);
    }

    public LevelBased projectileCount() {
        return new LevelBased(1, 1);
    }

    public LevelBased summonedEntities() {
        return new LevelBased(1, 1);
    }

    public LevelBased shootSpeed() {
        return new LevelBased(1, 1.5F);
    }

    public abstract LevelBased castTimeTicks();

    public LevelBased durationTicks() {
        return new LevelBased(30, 45);
    }

    public abstract LevelBased cooldownTicks();

}
