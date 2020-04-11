package com.robertx22.mine_and_slash.database.stats.types.spell_calc;

import com.robertx22.mine_and_slash.database.stats.IAfterStatCalc;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityData;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PlusAbilityLevelStat extends Stat implements IAfterStatCalc, IGenerated<PlusAbilityLevelStat> {

    private IAbility ability;

    public PlusAbilityLevelStat(IAbility ability) {
        this.ability = ability;
    }

    public IAbility getAbility() {
        return ability;
    }

    @Override
    public boolean IsPercent() {
        return false;
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
    public String locDescForLangFile() {
        return "Increase level of that ability.";
    }

    @Override
    public String locNameForLangFile() {
        return "To " + CLOC.translate(ability.getLocName())
            + " Skill Level";
    }

    @Override
    public String locDescLangFileGUID() {
        return Ref.MODID + ".stat_desc." + "plus_skill_level_to_spell";
    }

    @Override
    public String GUID() {
        return "plus_" + ability.GUID() + "_" + ability.getAbilityType()
            .name()
            .toLowerCase(Locale.ROOT) + "_lvl";
    }

    @Override
    public List<PlusAbilityLevelStat> generateAllPossibleStatVariations() {
        List<PlusAbilityLevelStat> list = new ArrayList<>();
        IAbility.getAll()
            .forEach(x -> list.add(new PlusAbilityLevelStat(x)));
        return list;
    }

    @Override
    public void doAfterStatCalc(StatData data, EntityCap.UnitData unit, PlayerSpellCap.ISpellsCap spells) {
        int lvls = (int) data
            .getAverageValue();

        if (spells.getAbilitiesData()
            .getAbilityMap()
            .containsKey(ability.GUID())) {
            AbilityData abilityData = spells.getAbilitiesData()
                .getAbilityMap()
                .get(ability.GUID());
            abilityData.setBonusLvl((int) (abilityData.getBonusLvls() + lvls));
        }

    }
}

