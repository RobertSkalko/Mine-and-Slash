package com.robertx22.mine_and_slash.items.gearitems.bases;

import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public abstract class BaseBaublesItem extends Item implements IAutoLocName {

    public int rarity = 0;

    public BaseBaublesItem(int rar) {

        super(new Properties().maxStackSize(1));
        this.rarity = rar;
    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Gear_Items;
    }

    @Override
    public String locNameLangFileGUID() {
        return formatString(this.getRegistryName().toString());
    }

    @Override
    public String GUID() {
        return "";
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player,
                                                    Hand hand) {

        return new ActionResult<ItemStack>(ActionResultType.SUCCESS, player.getHeldItem(hand));
    }

}
