package com.robertx22.mine_and_slash.vanilla_mc.items.misc;

import com.robertx22.mine_and_slash.database.base.CreativeTabs;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.localization.Chats;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class ItemIncreaseRarityNearestEntity extends Item {

    public ItemIncreaseRarityNearestEntity() {

        super(new Properties().group(CreativeTabs.MyModTab)
            .maxStackSize(64));

    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player,
                                                    Hand hand) {

        if (!world.isRemote) {
            try {

                AxisAlignedBB box = new AxisAlignedBB(player.getPosition()).grow(2);

                for (LivingEntity en : world.getEntitiesWithinAABB(LivingEntity.class, box)) {

                    if (en.isEntityEqual(player) == false && en instanceof PlayerEntity == false) {

                        UnitData data = Load.Unit(en);

                        if (data.increaseRarity(en)) {

                            player.getHeldItem(hand)
                                .shrink(1);

                            data.trySync(en);

                            return new ActionResult<ItemStack>(ActionResultType.PASS, player
                                .getHeldItem(hand));
                        } else {
                            player.sendMessage(Chats.No_targets_found.locName());
                        }
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ActionResult<ItemStack>(ActionResultType.PASS, player.getHeldItem(hand));
    }

}