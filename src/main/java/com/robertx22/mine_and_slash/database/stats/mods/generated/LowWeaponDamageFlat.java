package com.robertx22.mine_and_slash.database.stats.mods.generated;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.mods.BaseWeaponMod;
import com.robertx22.mine_and_slash.database.stats.types.generated.WeaponDamage;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class LowWeaponDamageFlat extends BaseWeaponMod {

    public LowWeaponDamageFlat(WeaponTypes type) {
        super(type);

    }

    @Override
    public BaseWeaponMod newGeneratedInstance(WeaponTypes type) {
        return new LowWeaponDamageFlat(type);
    }

    @Override
    public float Min() {
        return 5;
    }

    @Override
    public float Max() {
        return 15;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return new WeaponDamage(weaponType);
    }

    @Override
    public String GUID() {
        return "low_" + weaponType.id + "_damage_flat";
    }

}
