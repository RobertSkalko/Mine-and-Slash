package com.robertx22.mine_and_slash.items.profession.alchemy.single_use;

import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.items.profession.alchemy.bases.BaseInstantPotion;
import com.robertx22.mine_and_slash.items.profession.alchemy.bases.BasePotion;
import com.robertx22.mine_and_slash.professions.blocks.bases.Professions;
import com.robertx22.mine_and_slash.professions.recipe.BaseRecipe;
import com.robertx22.mine_and_slash.professions.recipe.SimpleRecipe;
import com.robertx22.mine_and_slash.professions.recipe.builders.SimpleRecipeBuilders;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class ResetTalentsPotionItem extends BaseInstantPotion {

    public ResetTalentsPotionItem() {
        super(Professions.Levels.ONE);
    }

    @Override
    public ITextComponent tooltip() {
        ITextComponent comp = new StringTextComponent("Resets all your talent points");
        return comp;

    }

    @Override
    public void onFinish(ItemStack stack, World world, LivingEntity player,
                         EntityCap.UnitData unitdata) {

        if (player instanceof PlayerEntity) {
            Load.talents((PlayerEntity) player).reset();
        }
    }

    @Override
    public String GUID() {
        return "alchemy/instant/misc/reset_talents";
    }

    @Override
    public String locNameForLangFile() {
        return level.color + level.name + " " + "Potion of Reset Talents";
    }

    @Override
    public BaseRecipe getRecipe() {

        SimpleRecipeBuilders.SimpleRecipeMatBuilder mats = SimpleRecipe.Builder.create(GUID(), Professions.ALCHEMY)
                .addMaterial(Items.GLASS_BOTTLE, 1)
                .addMaterial(Items.DIAMOND, 3)
                .addMaterial(Items.GOLDEN_APPLE, 1)
                .addMaterial(ItemOre.ItemOres.get(IRarity.Common), 10);

        return mats.buildMaterials().setOutput(this).levelReq(5).expGained(20).build();

    }

    @Override
    public BasePotion newInstance(Professions.Levels lvl) {
        return new ResetTalentsPotionItem();
    }

    @Override
    public float amount() {
        return 0;
    }
}
