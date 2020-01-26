package com.robertx22.mine_and_slash.items.profession.alchemy.single_use;

import com.robertx22.mine_and_slash.database.stats.types.resources.Health;
import com.robertx22.mine_and_slash.items.profession.alchemy.bases.BaseInstantPotion;
import com.robertx22.mine_and_slash.professions.blocks.bases.Professions;
import com.robertx22.mine_and_slash.professions.recipe.BaseRecipe;
import com.robertx22.mine_and_slash.professions.recipe.SimpleRecipe;
import com.robertx22.mine_and_slash.professions.recipe.builders.SimpleRecipeBuilders;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class InstantHealthPotionItem extends BaseInstantPotion {

    public InstantHealthPotionItem(Professions.Levels lvl) {

        super(lvl);
    }

    float lvl_1_amount = 50;

    @Override
    public ITextComponent tooltip() {
        ITextComponent comp = new StringTextComponent(TextFormatting.RED + "Restores " + amount() + " Health");
        return comp;

    }

    @Override
    public void onFinish(ItemStack stack, World world, LivingEntity player, EntityCap.UnitData unitdata) {

        unitdata.getResources()
                .modify(new ResourcesData.Context(unitdata, player, ResourcesData.Type.HEALTH, amount(),
                                                  ResourcesData.Use.RESTORE
                ));

    }

    @Override
    public String GUID() {
        return "alchemy/instant/health/potion_lvl_" + level.number;
    }

    @Override
    public float amount() {
        return Health.INSTANCE.calculateScalingStatGrowth(lvl_1_amount * level.effectMultiplier, level.number);
    }

    @Override
    public String locNameForLangFile() {
        return level.color + level.name + " " + "Instant Health Potion";
    }

    @Override
    public BaseRecipe getRecipe() {
        SimpleRecipeBuilders.SimpleRecipeMatBuilder mats = SimpleRecipe.Builder.create(GUID(), Professions.ALCHEMY)
                .addMaterial(Items.GLASS_BOTTLE, 1)
                .addMaterial(Items.APPLE, 2 * this.level.materialCostMulti)
                .addMaterial(Items.GLISTERING_MELON_SLICE, 1 * level.materialCostMulti);

        if (level.number >= Professions.Levels.FIFTY.number) {
            mats.addMaterial(Items.BEETROOT, 5 * level.materialCostMulti);
        }

        return mats.buildMaterials().setOutput(this, 3).levelReq(level.number).expGained(10).build();

    }

    @Override
    public BaseInstantPotion newInstance(Professions.Levels lvl) {
        return new InstantHealthPotionItem(lvl);
    }
}
