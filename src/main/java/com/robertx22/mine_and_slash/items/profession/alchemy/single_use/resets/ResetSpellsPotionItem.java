package com.robertx22.mine_and_slash.items.profession.alchemy.single_use.resets;

import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.items.profession.alchemy.bases.BaseInstantPotion;
import com.robertx22.mine_and_slash.items.profession.alchemy.bases.BasePotion;
import com.robertx22.mine_and_slash.professions.blocks.bases.Professions;
import com.robertx22.mine_and_slash.professions.recipe.BaseRecipe;
import com.robertx22.mine_and_slash.professions.recipe.SimpleRecipe;
import com.robertx22.mine_and_slash.professions.recipe.builders.SimpleRecipeBuilders;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class ResetSpellsPotionItem extends BaseInstantPotion {

    public ResetSpellsPotionItem() {
        super(Professions.Levels.TEN);
    }

    @Override
    public ITextComponent tooltip() {
        ITextComponent comp = new StringTextComponent("Resets all your spell points");
        return comp;

    }

    @Override
    public void onFinish(ItemStack stack, World world, LivingEntity player, EntityCap.UnitData unitdata) {
        if (player instanceof PlayerEntity) {
            Load.spells((PlayerEntity) player).reset();
        }
    }

    @Override
    public String GUID() {
        return "alchemy/instant/misc/reset_spells";
    }

    @Override
    public String locNameForLangFile() {
        return level.color + level.name + " " + "Potion of Reset Spells";
    }

    @Override
    public BaseRecipe getRecipe() {

        SimpleRecipeBuilders.SimpleRecipeMatBuilder mats = SimpleRecipe.Builder.create(GUID(), Professions.ALCHEMY)
                .addMaterial(Items.GLASS_BOTTLE, 1)
                .addMaterial(Items.GOLD_INGOT, 3)
                .addMaterial(Items.DIAMOND, 1)
                .addMaterial(ItemOre.ItemOres.get(IRarity.Common), 10);

        return mats.buildMaterials().setOutput(this).levelReq(10).expGained(20).build();

    }

    @Override
    public BasePotion newInstance(Professions.Levels lvl) {
        return new ResetSpellsPotionItem();
    }

    @Override
    public float amount() {
        return 0;
    }
}

