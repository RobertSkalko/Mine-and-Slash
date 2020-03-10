package com.robertx22.mine_and_slash.items.profession.alchemy.single_use;

import com.robertx22.mine_and_slash.items.profession.alchemy.bases.BaseInstantPotion;
import com.robertx22.mine_and_slash.items.profession.alchemy.bases.IAmount;
import com.robertx22.mine_and_slash.items.profession.alchemy.bases.IHasRecipe;
import com.robertx22.mine_and_slash.professions.blocks.bases.Professions;
import com.robertx22.mine_and_slash.professions.recipe.BaseRecipe;
import com.robertx22.mine_and_slash.professions.recipe.SimpleRecipe;
import com.robertx22.mine_and_slash.professions.recipe.builders.SimpleRecipeBuilders;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.StatUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class InstantManaPotionItem extends BaseInstantPotion implements IAmount, IHasRecipe {

    public InstantManaPotionItem(Professions.Levels lvl) {
        super(lvl);
    }

    float lvl_1_amount = 50;

    @Override
    public ITextComponent tooltip() {
        ITextComponent comp = new StringTextComponent("Restores " + amount() + " Mana");
        return comp;

    }

    @Override
    public void onFinish(ItemStack stack, World world, LivingEntity player,
                         EntityCap.UnitData unitdata) {

        ResourcesData.Context ctx = new ResourcesData.Context(unitdata, player, ResourcesData.Type.MANA, amount(), ResourcesData.Use.RESTORE);
        unitdata.modifyResource(ctx);

    }

    @Override
    public String GUID() {
        return "alchemy/instant/mana/potion_lvl_" + level.number;
    }

    @Override
    public float amount() {
        return StatUtils.roundNumber(level.effectMultiplier * lvl_1_amount);
    }

    @Override
    public String locNameForLangFile() {
        return level.color + level.name + " " + "Instant Mana Potion";
    }

    @Override
    public BaseRecipe getRecipe() {

        SimpleRecipeBuilders.SimpleRecipeMatBuilder mats = SimpleRecipe.Builder.create(GUID(), Professions.ALCHEMY)
            .addMaterial(Items.GLASS_BOTTLE, 1)
            .addMaterial(Items.COAL, 5 * this.level.materialCostMulti)
            .addMaterial(Items.LAPIS_LAZULI, 5 * level.materialCostMulti);

        if (level.number >= Professions.Levels.FIFTY.number) {
            mats.addMaterial(Items.ENDER_PEARL, 2 * level.materialCostMulti);
        }

        return mats.buildMaterials()
            .setOutput(this, 3)
            .levelReq(level.number)
            .expGained(10)
            .build();

    }

    @Override
    public BaseInstantPotion newInstance(Professions.Levels lvl) {
        return new InstantManaPotionItem(lvl);
    }
}
