package com.robertx22.mine_and_slash.database.stats.mods.generated;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.mods.BaseWeaponMod;
import com.robertx22.mine_and_slash.database.stats.types.generated.WeaponDamage;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class WeaponDamageFlat extends BaseWeaponMod {

    public WeaponDamageFlat(WeaponTypes type) {
        super(type);

    }

    @Override
    public BaseWeaponMod newGeneratedInstance(WeaponTypes type) {
        return new WeaponDamageFlat(type);
    }

    @Override
    public float Min() {
        return 10;
    }

    @Override
    public float Max() {
        return 25;
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
        return weaponType.id + "_damage_flat";
    }

}
