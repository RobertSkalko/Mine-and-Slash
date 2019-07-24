package com.robertx22.mine_and_slash.items.misc;

import com.robertx22.mine_and_slash.db_lists.CreativeTabs;
import com.robertx22.mine_and_slash.items.BaseItem;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RegisterItemUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.Tooltip;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nullable;
import java.util.List;

public class ItemPlayerLevelUp extends BaseItem {

    @ObjectHolder(Ref.MODID + ":player_levelup")
    public static final Item ITEM = null;

    public ItemPlayerLevelUp() {

        super(new Properties().group(CreativeTabs.MyModTab));

        RegisterItemUtils.RegisterItemName(this, "player_levelup");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity player,
                                                    Hand handIn) {

        if (!worldIn.isRemote) {
            try {

                int req = tokensRequired(Load.Unit(player).getLevel());

                if (hasEnoughTokens(player.getHeldItem(handIn), req)) {

                    if (Load.Unit(player).LevelUp((ServerPlayerEntity) player)) {

                        return new ActionResult<ItemStack>(ActionResultType.PASS, EmptyOrDecrease(player
                                .getHeldItem(handIn), req));

                    }
                } else {
                    player.sendMessage(new StringTextComponent("You need a total of " + req + " tokens to level up."));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ActionResult<ItemStack>(ActionResultType.PASS, player.getHeldItem(handIn));
    }

    private boolean hasEnoughTokens(ItemStack stack, int tokensreq) {
        return stack.getCount() >= tokensreq;
    }

    private int tokensRequired(int level) {

        int req = level / 10;

        if (req < 1) {
            req = 1;
        }
        if (req >= 64) {
            return 64;
        } else {
            return req;
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn,
                               List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        Tooltip.add(CLOC.tooltip("player_levelup"), tooltip);

    }

}
