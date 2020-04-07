package com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs;

public enum SC {
    MANA_COST(0, true),
    RADIUS(0, true),
    PROJECTILE_COUNT(1, true),
    CAST_TIME_TICKS(0, true),
    COOLDOWN_SECONDS(0, false),
    COOLDOWN_TICKS(0, false),
    SHOOT_SPEED(0.05F, true),
    SUMMONED_ENTITIES(1, true),
    CHANCE(0, true),
    DURATION_TICKS(0, true),
    TICK_RATE(0, true),
    TIMES_TO_CAST(1, false),
    BASE_VALUE(0, true),
    AMOUNT(0, true),
    ATTACK_SCALE_VALUE(0, true);

    public float min;
    public boolean errorIfOverridden;

    SC(float min, boolean errorIfOverridden) {
        this.min = min;
    }
}
