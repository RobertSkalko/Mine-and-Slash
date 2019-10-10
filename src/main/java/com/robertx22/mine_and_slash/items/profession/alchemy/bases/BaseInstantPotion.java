package com.robertx22.mine_and_slash.items.profession.alchemy.bases;

import com.robertx22.mine_and_slash.professions.blocks.bases.Professions;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BaseInstantPotion extends BasePotion implements IAmount {

    public BaseInstantPotion(Professions.Levels lvl) {
        super(lvl);
    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Alchemy;
    }

    @Override
    public String locNameLangFileGUID() {
        return this.getRegistryName().toString();
    }

    public abstract ITextComponent tooltip();

    public int useDurationInTicks() {
        return 30;
    }

    public abstract void onFinish(ItemStack stack, World world, LivingEntity player,
                                  EntityCap.UnitData unitdata);

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn,
                               List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        if (tooltip() != null) {
            tooltip.add(Styles.GREENCOMP().appendSibling(tooltip()));
        }

        tooltip.add(TooltipUtils.level(this.level.number));

    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return useDurationInTicks();
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, LivingEntity player) {

        onFinish(stack, world, player, Load.Unit(player));
        stack.shrink(1);

        if (player instanceof PlayerEntity) {
            PlayerEntity p = (PlayerEntity) player;
            p.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
        }

        return stack;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity player,
                                                    Hand handIn) {
        ItemStack itemstack = player.getHeldItem(handIn);

        if (Load.Unit(player).getLevel() >= this.level.number) {
            player.setActiveHand(handIn);
            return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
        } else {
            return new ActionResult<>(ActionResultType.FAIL, itemstack);
        }
    }

}
