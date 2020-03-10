package com.robertx22.mine_and_slash.items.profession.alchemy.single_use.resets;

import com.robertx22.mine_and_slash.advacements.PlayerLevelTrigger;
import com.robertx22.mine_and_slash.database.currency.base.IShapedRecipe;
import com.robertx22.mine_and_slash.items.profession.alchemy.bases.BaseInstantPotion;
import com.robertx22.mine_and_slash.items.profession.alchemy.bases.BasePotion;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModItems;
import com.robertx22.mine_and_slash.professions.blocks.bases.Professions;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class AddRemoveSpellPotionItem extends BaseInstantPotion implements IShapedRecipe {

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
            Load.spells((PlayerEntity) player)
                .addResetPoints(1);
        }
    }

    @Override
    public String GUID() {
        return "alchemy/instant/misc/remove_spell";
    }

    @Override
    public String locNameForLangFile() {
        return level.color + " " + "Potion of Single Spell Reset";
    }

    @Override
    public ShapedRecipeBuilder getRecipe() {
        return shaped(ModItems.ADD_RESET_SPELLS.get())
            .key('t', ModItems.ORB_OF_TRANSMUTATION.get())
            .key('v', Items.GOLD_NUGGET)
            .key('b', Items.GLASS_BOTTLE)
            .key('c', Items.COAL)
            .patternLine("cvc")
            .patternLine("vtv")
            .patternLine("cbc")
            .addCriterion("player_level", new PlayerLevelTrigger.Instance(5));
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
