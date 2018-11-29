package com.robertx22.database.rarities;

import com.robertx22.saveclasses.gearitem.gear_bases.Rarity;

public interface MobRarity extends Rarity {

    public float DamageMultiplier();

    public float HealthMultiplier();

    public float StatMultiplier();

    public int MaxMobEffects();

    public float LootMultiplier();

    public float ExpOnKill();

}
