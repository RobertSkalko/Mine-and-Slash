package com.robertx22.mine_and_slash.items.profession.alchemy.potion_buffs;

import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaRegenFlat;
import com.robertx22.mine_and_slash.items.profession.alchemy.bases.BaseBuffPotion;
import com.robertx22.mine_and_slash.items.profession.alchemy.bases.BasePotion;
import com.robertx22.mine_and_slash.items.profession.alchemy.single_use.InstantManaPotionItem;
import com.robertx22.mine_and_slash.professions.blocks.bases.Professions;
import com.robertx22.mine_and_slash.professions.recipe.BaseRecipe;
import com.robertx22.mine_and_slash.professions.recipe.SimpleRecipe;
import com.robertx22.mine_and_slash.professions.recipe.builders.SimpleRecipeBuilders;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import net.minecraft.item.Items;

import java.util.Arrays;
import java.util.List;

public class ManaRegenBuffPotion extends BaseBuffPotion {

    public ManaRegenBuffPotion(Professions.Levels lvl) {
        super(lvl);
    }

    @Override
    public List<StatModData> mods() {
        return Arrays.asList(StatModData.Load(new ManaRegenFlat(), (int) (80 * level.effectMultiplier)));
    }

    @Override
    public BaseRecipe getRecipe() {
        SimpleRecipeBuilders.SimpleRecipeMatBuilder mats = SimpleRecipe.Builder.create(GUID(), Professions.ALCHEMY)
                .addMaterial(new InstantManaPotionItem(level).getFromForgeRegistry(), 1)
                .addMaterial(Items.LAPIS_LAZULI, 5 * this.level.materialCostMulti)
                .addMaterial(Items.ENDER_PEARL, 3 * level.materialCostMulti);

        if (level.number >= Professions.Levels.FIFTY.number) {
            mats.addMaterial(Items.NETHER_WART, 2 * level.materialCostMulti);
        }

        return mats.buildMaterials()
                .setOutput(this)
                .levelReq(level.number)
                .expGained(15)
                .build();

    }

    @Override
    public BasePotion newInstance(Professions.Levels lvl) {
        return new ManaRegenBuffPotion(lvl);
    }

    @Override
    public String GUID() {
        return "alchemy/buff/mana_regen/potion_lvl_" + level.number;
    }

    @Override
    public String locNameForLangFile() {
        return level.color + level.name + " " + "Mana Regen Buff Potion ";
    }
}