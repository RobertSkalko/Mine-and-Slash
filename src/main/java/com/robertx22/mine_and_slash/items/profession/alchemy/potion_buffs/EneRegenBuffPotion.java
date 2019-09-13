package com.robertx22.mine_and_slash.items.profession.alchemy.potion_buffs;

import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.mine_and_slash.items.profession.alchemy.bases.BaseBuffPotion;
import com.robertx22.mine_and_slash.items.profession.alchemy.bases.BasePotion;
import com.robertx22.mine_and_slash.items.profession.alchemy.single_use.InstantEnergyPotionItem;
import com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases.Professions;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.BaseRecipe;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.SimpleRecipe;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.builders.SimpleRecipeBuilders;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import net.minecraft.item.Items;

import java.util.Arrays;
import java.util.List;

public class EneRegenBuffPotion extends BaseBuffPotion {

    public EneRegenBuffPotion(Professions.Levels lvl) {
        super(lvl);
    }

    @Override
    public List<StatModData> mods() {
        return Arrays.asList(StatModData.Load(new EnergyRegenFlat(), (int) (80 * level.effectMultiplier)));
    }

    @Override
    public BaseRecipe getRecipe() {
        SimpleRecipeBuilders.SimpleRecipeMatBuilder mats = SimpleRecipe.Builder.create(GUID(), Professions.ALCHEMY)
                .addMaterial(new InstantEnergyPotionItem(level).getFromForgeRegistry(), 1)
                .addMaterial(Items.EMERALD, 5 * this.level.materialCostMulti)
                .addMaterial(Items.SUGAR_CANE, 5 * level.materialCostMulti);

        if (level.number >= Professions.Levels.FIFTY.number) {
            mats.addMaterial(Items.ENDER_EYE, 2 * level.materialCostMulti);
        }

        return mats.buildMaterials()
                .setOutput(this)
                .levelReq(level.number)
                .expGained(15)
                .build();

    }

    @Override
    public BasePotion newInstance(Professions.Levels lvl) {
        return new EneRegenBuffPotion(lvl);
    }

    @Override
    public String GUID() {
        return "alchemy/buff/ene_regen/potion_lvl_" + level.number;
    }

    @Override
    public String locNameForLangFile() {
        return level.color + level.name + " " + "Energy Regen Buff Potion ";
    }
}