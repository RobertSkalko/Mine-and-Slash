package com.robertx22.mine_and_slash.items.profession.alchemy.potion_buffs;

import com.robertx22.mine_and_slash.database.stats.mods.generated.LootTypeBonusFlat;
import com.robertx22.mine_and_slash.items.profession.alchemy.bases.BaseBuffPotion;
import com.robertx22.mine_and_slash.items.profession.alchemy.bases.BasePotion;
import com.robertx22.mine_and_slash.items.profession.alchemy.single_use.InstantEnergyPotionItem;
import com.robertx22.mine_and_slash.professions.blocks.bases.Professions;
import com.robertx22.mine_and_slash.professions.recipe.BaseRecipe;
import com.robertx22.mine_and_slash.professions.recipe.SimpleRecipe;
import com.robertx22.mine_and_slash.professions.recipe.builders.SimpleRecipeBuilders;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import net.minecraft.item.Items;

import java.util.Arrays;
import java.util.List;

public class LootBonusBuffPotion extends BaseBuffPotion {

    public LootBonusBuffPotion(Professions.Levels lvl) {
        super(lvl);
    }

    @Override
    public List<StatModData> mods() {
        return Arrays.asList(StatModData.Load(new LootTypeBonusFlat(LootType.All), (int) (80 * level.effectMultiplier)));
    }

    @Override
    public BaseRecipe getRecipe() {
        SimpleRecipeBuilders.SimpleRecipeMatBuilder mats = SimpleRecipe.Builder.create(GUID(), Professions.ALCHEMY)
                .addMaterial(new InstantEnergyPotionItem(level).getFromForgeRegistry(), 1)
                .addMaterial(Items.YELLOW_DYE, 3 * this.level.materialCostMulti)
                .addMaterial(Items.GOLD_NUGGET, 3 * this.level.materialCostMulti)
                .addMaterial(Items.GOLDEN_CARROT, 1 * this.level.materialCostMulti);

        if (level.number >= Professions.Levels.FIFTY.number) {
            mats.addMaterial(Items.GOLDEN_APPLE, 1 * level.materialCostMulti);
        }

        return mats.buildMaterials()
                .setOutput(this)
                .levelReq(level.number)
                .expGained(15)
                .build();

    }

    @Override
    public BasePotion newInstance(Professions.Levels lvl) {
        return new LootBonusBuffPotion(lvl);
    }

    @Override
    public String GUID() {
        return "alchemy/buff/loot_bonus/potion_lvl_" + level.number;
    }

    @Override
    public String locNameForLangFile() {
        return level.color + level.name + " " + "Loot Bonus Buff Potion ";
    }
}
