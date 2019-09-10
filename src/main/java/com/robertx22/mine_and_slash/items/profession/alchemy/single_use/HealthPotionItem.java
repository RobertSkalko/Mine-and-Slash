package com.robertx22.mine_and_slash.items.profession.alchemy.single_use;

import com.robertx22.mine_and_slash.items.consumables.bases.BaseInstantPotion;
import com.robertx22.mine_and_slash.items.consumables.bases.IAmount;
import com.robertx22.mine_and_slash.new_content_test.professions.data.Professions;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.BaseRecipe;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.SimpleRecipe;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.effectdatas.HealData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.HashMap;

public class HealthPotionItem extends BaseInstantPotion implements IAmount, IHasRecipe, ILvlRecipeGen {

    public static HashMap<Professions.Levels, HealthPotionItem> ITEMS = new HashMap<>();

    public HealthPotionItem(Professions.Levels lvl) {
        this.level = lvl;
    }

    float lvl_1_amount = 50;
    Professions.Levels level = Professions.Levels.ONE;

    @Override
    public ITextComponent tooltip() {
        ITextComponent comp = new StringTextComponent(TextFormatting.RED + "Restores " + amount() + " Health");
        return comp;

    }

    @Override
    public void onFinish(ItemStack stack, World world, LivingEntity player,
                         EntityCap.UnitData unitdata) {

        unitdata.heal(new HealData(player, unitdata, (int) amount()));

    }

    @Override
    public String GUID() {
        return "alchemy/instant/health/potion_lvl_" + level.number;
    }

    @Override
    public float amount() {
        return StatModData.calculateStatGrowth(lvl_1_amount, level.number);
    }

    @Override
    public String locNameForLangFile() {
        return level.color + level.name + " " + "Instant Health Potion";
    }

    @Override
    public BaseRecipe getRecipe() {
        return SimpleRecipe.Builder.create("health_pot_lvl_" + this.level.number, Professions.ALCHEMY)
                .addMaterial(Items.APPLE, 2 * this.level.materialCostMulti)
                .addMaterial(Items.GLISTERING_MELON_SLICE, 1 * level.materialCostMulti)
                .buildMaterials()
                .setOutput(this)
                .build();

    }

    @Override
    public BaseInstantPotion newInstance(Professions.Levels lvl) {
        return new HealthPotionItem(lvl);
    }
}
