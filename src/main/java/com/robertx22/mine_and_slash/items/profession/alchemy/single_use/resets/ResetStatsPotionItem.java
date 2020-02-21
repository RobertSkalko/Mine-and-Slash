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

public class ResetStatsPotionItem extends BaseInstantPotion {

    public ResetStatsPotionItem() {
        super(Professions.Levels.TEN);
    }

    @Override
    public ITextComponent tooltip() {
        ITextComponent comp = new StringTextComponent("Removes all allocated Stat Points");
        return comp;

    }

    @Override
    public void onFinish(ItemStack stack, World world, LivingEntity player, EntityCap.UnitData unitdata) {

        if (player instanceof PlayerEntity) {
            Load.statPoints((PlayerEntity) player).resetStats();
        }
    }

    @Override
    public String GUID() {
        return "alchemy/instant/misc/regret" + level.number;
    }

    @Override
    public String locNameForLangFile() {
        return level.color + level.name + " " + "Potion of Stat Regret";
    }

    @Override
    public BaseRecipe getRecipe() {

        SimpleRecipeBuilders.SimpleRecipeMatBuilder mats = SimpleRecipe.Builder.create(GUID(), Professions.ALCHEMY)
                .addMaterial(Items.GLASS_BOTTLE, 1)
                .addMaterial(Items.GOLDEN_CARROT, 2)
                .addMaterial(Items.NETHER_WART, 25)
                .addMaterial(ItemOre.ItemOres.get(IRarity.Mythic), 5);

        return mats.buildMaterials().setOutput(this).levelReq(level.number).expGained(10).build();

    }

    @Override
    public BasePotion newInstance(Professions.Levels lvl) {
        return new ResetStatsPotionItem();
    }

    @Override
    public float amount() {
        return 0;
    }
}
