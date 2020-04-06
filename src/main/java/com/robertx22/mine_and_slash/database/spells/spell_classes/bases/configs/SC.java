package com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs;

public enum SC {
    MANA_COST(0),
    RADIUS(0),
    PROJECTILE_COUNT(1),
    CAST_TIME_TICKS(0),
    COOLDOWN_SECONDS(0),
    SHOOT_SPEED(0.05F),
    SUMMONED_ENTITIES(1),
    CHANCE(0),
    DURATION_TICKS(0),
    TIMES_TO_CAST(1),
    BASE_VALUE(0),
    ATTACK_SCALE_VALUE(0);

    public float min;

    SC(float min) {
        this.min = min;
    }
}
