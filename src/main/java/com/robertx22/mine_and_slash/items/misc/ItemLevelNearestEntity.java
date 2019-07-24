package com.robertx22.mine_and_slash.items.misc;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.db_lists.CreativeTabs;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.localization.Chats;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RegisterItemUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.registries.ObjectHolder;

public class ItemLevelNearestEntity extends Item {

    @ObjectHolder(Ref.MODID + ":level_nearest_entity")
    public static final Item ITEM = null;

    public ItemLevelNearestEntity() {
        super(new Properties().group(CreativeTabs.MyModTab).maxStackSize(64));

        RegisterItemUtils.RegisterItemName(this, "level_nearest_entity");

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

                        if (data.getLevel() + 1 <= ModConfig.INSTANCE.Server.MAXIMUM_PLAYER_LEVEL
                                .get()) {
                            data.setLevel(data.getLevel() + 1, en);

                            player.getHeldItem(hand).shrink(1);

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