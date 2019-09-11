package com.robertx22.mine_and_slash.items.profession.alchemy.potion_buffs;

import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.mine_and_slash.items.profession.alchemy.bases.BaseBuffPotion;
import com.robertx22.mine_and_slash.items.profession.alchemy.bases.BasePotion;
import com.robertx22.mine_and_slash.new_content_test.professions.data.Professions;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.BaseRecipe;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;

import java.util.Arrays;
import java.util.List;

public class HpRegenBuffPotion extends BaseBuffPotion {

    public HpRegenBuffPotion(Professions.Levels lvl) {
        super(lvl);
    }

    @Override
    public List<StatModData> mods() {
        return Arrays.asList(StatModData.Load(new HealthRegenFlat(), 100));
    }

    @Override
    public BaseRecipe getRecipe() {
        return null;
    }

    @Override
    public BasePotion newInstance(Professions.Levels lvl) {
        return new HpRegenBuffPotion(lvl);
    }

    @Override
    public String GUID() {
        return "alchemy/buff/hp_regen/potion_lvl_" + level.number;
    }

    @Override
    public String locNameForLangFile() {
        return level.color + level.name + " " + "Health Regen Buff Potion ";
    }
}
