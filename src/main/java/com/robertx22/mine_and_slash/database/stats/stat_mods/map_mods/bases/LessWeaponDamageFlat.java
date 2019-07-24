package com.robertx22.mine_and_slash.database.stats.stat_mods.map_mods.bases;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.generated.WeaponDamage;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.List;

public class LessWeaponDamageFlat extends StatMod implements IGenerated<StatMod> {

    @Override
    public float Min() {
        return -20;
    }

    @Override
    public float Max() {
        return -50;
    }

    public WeaponTypes weaponType;

    public LessWeaponDamageFlat() {

    }

    public LessWeaponDamageFlat(WeaponTypes type) {
        this.weaponType = type;
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
        return "Less" + weaponType.name() + "DamageMap";
    }

    @Override
    public List<StatMod> generateAllPossibleStatVariations() {
        List<StatMod> list = new ArrayList<>();
        WeaponTypes.getAll().forEach(x -> list.add(new LessWeaponDamageFlat(x)));
        return list;

    }

}
