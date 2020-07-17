package com.robertx22.mine_and_slash.vanilla_mc.items.bags;

import com.robertx22.mine_and_slash.database.base.CreativeTabs;
import com.robertx22.mine_and_slash.vanilla_mc.items.ItemSingle;
import com.robertx22.mine_and_slash.uncommon.item_filters.bases.ItemFilterGroup;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public abstract class BaseBagItem extends Item {

    public abstract ItemFilterGroup filterGroup();

    public abstract INamedContainerProvider getNamedContainer(ItemStack stack);

    public int size = 9 * 2;

    public BaseBagItem(String name) {
        super(new ItemSingle().group(CreativeTabs.MyModTab));

    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip,
                               ITooltipFlag flagIn) {

        tooltip.add(TooltipUtils.color(TextFormatting.GREEN, Words.PicksUpItemsAuto.locName()));
        tooltip.add(TooltipUtils.color(TextFormatting.YELLOW, Words.HoldToPreventPickup.locName()));

    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, @Nonnull Hand hand) {
        if (!world.isRemote) {
            if (hand == Hand.MAIN_HAND && player.getHeldItem(hand)
                .getItem() instanceof BaseBagItem) {
                player.openContainer(getNamedContainer(player.getHeldItem(hand)));
            }
        }
        return new ActionResult<ItemStack>(ActionResultType.SUCCESS, player.getHeldItem(hand));
    }

    public BaseInventory getInventory(ItemStack bag, ItemStack stack) {
        if (stack.getCount() > 0 && filterGroup().anyMatchesFilter(stack)) {
            return newInventory(bag);
        }
        return null;

    }

    @Override
    public net.minecraftforge.common.capabilities.ICapabilityProvider initCapabilities(ItemStack stack,
                                                                                       @Nullable CompoundNBT nbt) {
        // MOVE OVER ITEM FROM OLD CAPABILITY SYSTEM
        if (nbt != null && nbt.contains("Parent")) {
            nbt.put("Items", nbt.get("Parent"));
            stack.setTag(nbt);
        }
        return null;
    }

    public BaseInventory newInventory(ItemStack bag) {
        return new BaseInventory(bag);
    }

}