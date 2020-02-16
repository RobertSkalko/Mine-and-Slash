package com.robertx22.mine_and_slash.database.stats.mods;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseWeaponMod extends StatMod implements IGenerated<StatMod> {

    public WeaponTypes weaponType;

    public BaseWeaponMod(WeaponTypes type) {
        this.weaponType = type;
    }

    public abstract BaseWeaponMod newGeneratedInstance(WeaponTypes type);

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public List<StatMod> generateAllPossibleStatVariations() {
        List<StatMod> list = new ArrayList<>();
        WeaponTypes.getAll().forEach(x -> list.add(newGeneratedInstance(x)));
        return list;

    }

}
