package com.robertx22.mine_and_slash.database.stats.stat_types.generated;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.stat_effects.BlockReflectEffect;
import com.robertx22.mine_and_slash.database.stats.stat_types.ElementalStat;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlockReflect extends ElementalStat implements IStatEffects {

    public BlockReflect(Elements element) {
        super(element);
    }

    @Override
    public Stat newGeneratedInstance(Elements element) {
        return new BlockReflect(element);
    }

    @Override
    public boolean IsPercent() {
        return false;
    }

    @Override
    public boolean ScalesToLevel() {
        return true;
    }

    @Override
    public String locDescForLangFile() {
        return "When blocking an attack, damage the enemy";
    }

    @Override
    public String locDescLangFileGUID() {
        return Ref.MODID + ".stat_desc." + "block_reflect";
    }

    @Override
    public String locNameForLangFile() {
        return element.dmgName + " Thorns";
    }

    @Override
    public String GUID() {
        return element.name().toLowerCase() + "_thorns_reflect";
    }

    // include none, the physical damage
    @Override
    public List<Stat> generateAllPossibleStatVariations() {
        List<Elements> elements = Elements.getAllSingleElements();
        elements.add(Elements.Physical);
        List<Stat> list = new ArrayList<>();
        elements.forEach(x -> list.add(newGeneratedInstance(x)));
        return list;

    }

    @Override
    public List<IStatEffect> GetEffects() {
        return Arrays.asList(new BlockReflectEffect(element));
    }
}
