package com.robertx22.mine_and_slash.potion_effects.alchemy_pot_buffs;

import com.robertx22.mine_and_slash.potion_effects.IDefaultStatPotion;
import com.robertx22.mine_and_slash.professions.blocks.bases.Professions;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.util.Arrays;
import java.util.List;

public abstract class BaseEffect extends Effect implements IAutoLocName, IDefaultStatPotion {

    public String guid;
    public String name;
    public int level;
    public List<StatModData> mods;

    public BaseEffect(String guid, String name, int level, List<StatModData> mods) {
        super(EffectType.BENEFICIAL, 0);
        this.guid = guid;
        this.name = name;
        this.level = level;
        this.mods = mods;
    }

    public BaseEffect(String guid, String name, int level, StatModData mod) {
        this(guid, name, level, Arrays.asList(mod));
    }

    public abstract Professions proffesion();

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Potions;
    }

    @Override
    public String locNameLangFileGUID() {
        return this.getRegistryName().toString();
    }

    @Override
    public String locNameForLangFile() {
        return name;
    }

    @Override
    public String GUID() {
        return guid;
    }

    @Override
    public List<StatModData> statsMods() {
        return mods;
    }

    @Override
    public int getLevel() {
        return this.level;
    }
}
