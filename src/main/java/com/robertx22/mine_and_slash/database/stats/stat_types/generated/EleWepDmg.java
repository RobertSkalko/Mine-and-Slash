package com.robertx22.mine_and_slash.database.stats.stat_types.generated;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.stat_effects.offense.EleWepDmgEffect;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class EleWepDmg extends Stat implements IStatEffects {

    public static HashMap<WeaponTypes, EleWepDmg> MAP = new HashMap<>();

    private WeaponTypes weaponType;

    private EleWepDmg(WeaponTypes type) {
        this.weaponType = type;
    }

    @Override
    public String locDescForLangFile() {
        return "Increases elemental damage of x weapon";
    }

    public WeaponTypes weaponType() {
        return this.weaponType;
    }

    @Override
    public String GUID() {
        return "ele_" + this.weaponType.name() + "_damage";
    }

    @Override
    public boolean IsPercent() {
        return true;
    }

    @Override
    public boolean ScalesToLevel() {
        return false;
    }

    @Override
    public Elements Element() {
        return null;
    }

    @Override
    public List<IStatEffect> GetEffects() {
        return Arrays.asList(EleWepDmgEffect.INSTANCE);
    }

    @Override
    public String locDescLangFileGUID() {
        return Ref.MODID + ".stat_desc." + "ele_wep_damage";
    }

    @Override
    public String locNameForLangFile() {
        return "Elemental " + this.weaponType().name() + " Damage";
    }

    public static void register() {
        for (WeaponTypes x : WeaponTypes.getAll()) {
            EleWepDmg stat = new EleWepDmg(x);
            MAP.put(x, stat);
            stat.registerToSlashRegistry();
        }
    }

}
