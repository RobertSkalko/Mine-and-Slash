package com.robertx22.mine_and_slash.items.misc;

import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class ExpItem extends Item {

    public ExpItem() {
        super(new Properties().maxStackSize(1)
            .maxDamage(0));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {

        if (!world.isRemote) {
            try {

                ItemStack stack = player.getHeldItem(hand);

                int exp = stack.getTag()
                    .getInt("exp");

                Load.Unit(player)
                    .GiveExp(player, exp);

                stack.shrink(1);

                SoundUtils.ding(world, player.getPosition());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ActionResult<ItemStack>(ActionResultType.PASS, player.getHeldItem(hand));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        if (stack.hasTag() && stack.getTag()
            .contains("exp")) {
            int exp = stack.getTag()
                .getInt("exp");

            tooltip.add(Words.Exp.locName()
                .appendText(": " + exp)
                .setStyle(new Style().setColor(TextFormatting.YELLOW)));

            tooltip.add(Words.ClickToUse.locName()
                .setStyle(new Style().setColor(TextFormatting.BLUE)));

        }
    }

}
