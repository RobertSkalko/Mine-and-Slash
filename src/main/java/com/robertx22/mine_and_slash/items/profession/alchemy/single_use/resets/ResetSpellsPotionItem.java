package com.robertx22.mine_and_slash.items.profession.alchemy.single_use.resets;

import com.robertx22.mine_and_slash.advacements.PlayerLevelTrigger;
import com.robertx22.mine_and_slash.database.currency.base.IShapedRecipe;
import com.robertx22.mine_and_slash.items.SimpleMatItem;
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

public class ResetSpellsPotionItem extends BaseInstantPotion implements IShapedRecipe {

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
            Load.spells((PlayerEntity) player)
                .reset();
        }
    }

    @Override
    public String GUID() {
        return "alchemy/instant/misc/reset_spells";
    }

    @Override
    public String locNameForLangFile() {
        return level.color + level.name + " " + "Potion of Spells Reset";
    }

    @Override
    public ShapedRecipeBuilder getRecipe() {
        return shaped(ModItems.RESET_SPELLS.get())
            .key('#', SimpleMatItem.GOLDEN_ORB)
            .key('t', ModItems.CRYSTAL_OF_LEGEND.get())
            .key('v', Items.GOLD_INGOT)
            .key('b', Items.GLASS_BOTTLE)
            .key('c', Items.COAL)
            .patternLine("#v#")
            .patternLine("vtv")
            .patternLine("cbc")
            .addCriterion("player_level", new PlayerLevelTrigger.Instance(5));
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

