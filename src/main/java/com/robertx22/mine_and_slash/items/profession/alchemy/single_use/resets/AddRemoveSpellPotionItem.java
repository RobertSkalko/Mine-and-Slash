package com.robertx22.mine_and_slash.items.profession.alchemy.single_use.resets;

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

public class AddRemoveSpellPotionItem extends BaseInstantPotion {

    public AddRemoveSpellPotionItem() {
        super(Professions.Levels.ONE);
    }

    @Override
    public ITextComponent tooltip() {
        ITextComponent comp = new StringTextComponent(
                "Gives you 1 remove spell point. Use by right clicking on spell screen.");
        return comp;

    }

    @Override
    public void onFinish(ItemStack stack, World world, LivingEntity player, EntityCap.UnitData unitdata) {

        if (player instanceof PlayerEntity) {
            Load.spells((PlayerEntity) player).addResetPoints(1);
        }
    }

    @Override
    public String GUID() {
        return "alchemy/instant/misc/remove_spell";
    }

    @Override
    public String locNameForLangFile() {
        return level.color + " " + "Potion of Single Spell Removal";
    }

    @Override
    public BaseRecipe getRecipe() {

        SimpleRecipeBuilders.SimpleRecipeMatBuilder mats = SimpleRecipe.Builder.create(GUID(), Professions.ALCHEMY)
                .addMaterial(Items.GLASS_BOTTLE, 1)
                .addMaterial(Items.COAL, 3)
                .addMaterial(ItemOre.ItemOres.get(IRarity.Common), 3);

        return mats.buildMaterials().setOutput(this).levelReq(1).expGained(5).build();

    }

    @Override
    public BasePotion newInstance(Professions.Levels lvl) {
        return new AddRemoveSpellPotionItem();
    }

    @Override
    public float amount() {
        return 0;
    }
}
