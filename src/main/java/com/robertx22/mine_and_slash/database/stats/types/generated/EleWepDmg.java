package com.robertx22.mine_and_slash.database.stats.types.generated;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.offense.EleWepDmgEffect;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;
import com.robertx22.mine_and_slash.uncommon.wrappers.MapWrapper;

import java.util.List;

public class EleWepDmg extends Stat implements IStatEffects, IGenerated<EleWepDmg> {

    public static MapWrapper<WeaponTypes, EleWepDmg> MAP = new MapWrapper();

    @Override
    public List<EleWepDmg> generateAllPossibleStatVariations() {
        for (WeaponTypes x : WeaponTypes.getAll()) {
            EleWepDmg stat = new EleWepDmg(x);
            MAP.put(x, stat);
        }
        return MAP.getList();

    }

    private WeaponTypes weaponType;

    private EleWepDmg(WeaponTypes type) {
        this.weaponType = type;
    }

    @Override
    public String locDescForLangFile() {
        return "Increases elemental damage of x weapon";
    }

    @Override
    public String getIconPath() {
        return "ele_wep_dmg/" + weaponType.id;
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
    public StatScaling getScaling() {
        return StatScaling.NONE;
    }

    @Override
    public Elements getElement() {
        return null;
    }

    @Override
    public IStatEffect getEffect() {
        return EleWepDmgEffect.INSTANCE;
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
