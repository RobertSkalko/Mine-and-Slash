package com.robertx22.mine_and_slash.items.profession.alchemy.potion_buffs;

import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthRegenFlat;
import com.robertx22.mine_and_slash.items.profession.alchemy.bases.BaseBuffPotion;
import com.robertx22.mine_and_slash.items.profession.alchemy.bases.BasePotion;
import com.robertx22.mine_and_slash.items.profession.alchemy.single_use.InstantHealthPotionItem;
import com.robertx22.mine_and_slash.professions.blocks.bases.Professions;
import com.robertx22.mine_and_slash.professions.recipe.BaseRecipe;
import com.robertx22.mine_and_slash.professions.recipe.SimpleRecipe;
import com.robertx22.mine_and_slash.professions.recipe.builders.SimpleRecipeBuilders;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import net.minecraft.item.Items;

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
        SimpleRecipeBuilders.SimpleRecipeMatBuilder mats = SimpleRecipe.Builder.create(GUID(), Professions.ALCHEMY)
                .addMaterial(new InstantHealthPotionItem(level).getFromForgeRegistry(), 1)
                .addMaterial(Items.APPLE, 3 * this.level.materialCostMulti)
                .addMaterial(Items.GLISTERING_MELON_SLICE, 5 * level.materialCostMulti);

        if (level.number >= Professions.Levels.FIFTY.number) {
            mats.addMaterial(Items.GOLDEN_CARROT, 1 * level.materialCostMulti);
        }

        return mats.buildMaterials()
                .setOutput(this)
                .levelReq(level.number)
                .expGained(15)
                .build();

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
