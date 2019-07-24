package com.robertx22.mine_and_slash.database.stats.stat_types.generated;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.stat_effects.offense.AllEleDmgEffectIfElement;
import com.robertx22.mine_and_slash.database.stats.stat_types.ElementalStat;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public class AllElementalDamage extends ElementalStat implements IStatEffects {

    public AllElementalDamage(Elements element) {
        super(element);
    }

    @Override
    public Stat newGeneratedInstance(Elements element) {
        return new AllElementalDamage(element);
    }

    @Override
    public String GUID() {
        return "All " + this.Element().name() + " Damage";
    }

    @Override
    public String locDescForLangFile() {
        return "Increases All DMG of that element, both spells and attacks";
    }

    @Override
    public boolean ScalesToLevel() {
        return false;
    }

    @Override
    public String locDescLangFileGUID() {
        return Ref.MODID + ".stat_desc." + "all_ele_dmg";
    }

    @Override
    public boolean IsPercent() {
        return true;
    }

    @Override
    public List<IStatEffect> GetEffects() {
        return Arrays.asList(new AllEleDmgEffectIfElement());
    }

    @Override
    public String locNameForLangFile() {
        return "All " + this.Element().name() + " Damage";
    }

}

