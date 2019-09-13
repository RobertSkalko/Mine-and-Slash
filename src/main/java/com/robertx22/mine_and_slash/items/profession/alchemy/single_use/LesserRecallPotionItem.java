package com.robertx22.mine_and_slash.items.profession.alchemy.single_use;

import com.robertx22.mine_and_slash.items.profession.alchemy.bases.BaseInstantPotion;
import com.robertx22.mine_and_slash.items.profession.alchemy.bases.IAmount;
import com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases.Professions;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.BaseRecipe;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.SimpleRecipe;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class LesserRecallPotionItem extends BaseInstantPotion implements IAmount {

    public LesserRecallPotionItem() {
        super(Professions.Levels.TWENTY_FIVE);
    }

    @Override
    public ITextComponent tooltip() {
        return null;
    }

    @Override

    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn,
                               List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        tooltip.add(new StringTextComponent(TextFormatting.BLUE + "Telepots you back to your Bed."));
        super.addInformation(stack, worldIn, tooltip, flagIn);

    }

    public int useDurationInTicks() {
        return 150;
    }

    @Override
    public void onFinish(ItemStack stack, World world, LivingEntity player,
                         EntityCap.UnitData unitdata) {

        PlayerEntity p = (PlayerEntity) player;
        BlockPos x = p.getBedLocation(p.world.getDimension().getType());
        player.setPositionAndUpdate(x.getX(), x.getY(), x.getZ());

    }

    @Override
    public String GUID() {
        return "alchemy/instant/misc/lesser_recall_potion";
    }

    @Override
    public float amount() {
        return 0;
    }

    @Override
    public String locNameForLangFile() {
        return level.color + level.name + " " + "Recall Potion";
    }

    @Override
    public BaseRecipe getRecipe() {
        return SimpleRecipe.Builder.create(GUID(), Professions.ALCHEMY)
                .addMaterial(Items.GLASS_BOTTLE, 1)
                .addMaterial(Items.ENDER_EYE, 1)
                .addMaterial(Items.NETHER_WART, 5)
                .buildMaterials()
                .setOutput(this)
                .levelReq(level.number)
                .expGained(3)
                .build();

    }

    @Override
    public BaseInstantPotion newInstance(Professions.Levels lvl) {
        return new LesserRecallPotionItem();
    }
}
