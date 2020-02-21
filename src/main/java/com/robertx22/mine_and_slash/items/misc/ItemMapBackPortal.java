package com.robertx22.mine_and_slash.items.misc;

import com.robertx22.mine_and_slash.db_lists.CreativeTabs;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerMapCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.localization.Chats;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RegisterItemUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.registries.ObjectHolder;

public class ItemMapBackPortal extends Item {

    @ObjectHolder(Ref.MODID + ":map_back_portal")
    public static final Item ITEM = null;

    public ItemMapBackPortal() {

        super(new Properties().group(CreativeTabs.MyModTab));

        RegisterItemUtils.RegisterItemName(this, "map_back_portal");

    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player,
                                                    Hand hand) {

        if (!world.isRemote) {
            try {

                if (WorldUtils.isMapWorld(world)) {

                    if (!player.getHeldItem(hand).hasTag()) {
                        player.getHeldItem(hand).setTag(new CompoundNBT());
                    }

                    PlayerMapCap.IPlayerMapData data = Load.playerMapData((PlayerEntity) player);
                    data.teleportPlayerBack(player);

                    player.getHeldItem(hand).shrink(1);

                    return new ActionResult<ItemStack>(ActionResultType.PASS, player.getHeldItem(hand));

                } else {
                    player.sendMessage(Chats.You_are_not_inside_a_map_world.locName());

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ActionResult<ItemStack>(ActionResultType.PASS, player.getHeldItem(hand));
    }

}
