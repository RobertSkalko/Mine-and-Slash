package com.robertx22.mine_and_slash.items.consumables;

import com.robertx22.mine_and_slash.items.consumables.bases.BaseConsumabletem;
import com.robertx22.mine_and_slash.items.consumables.bases.IAmount;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ConsumableRegister;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class RestoreEnergyItem extends BaseConsumabletem implements IAmount {

    @Override
    public ITextComponent tooltip() {

        ITextComponent comp = new StringTextComponent("Restores " + amount() + " Energy");

        return comp;

    }

    @Override
    public void onFinish(ItemStack stack, World world, LivingEntity player,
                         EntityCap.UnitData unitdata) {

        unitdata.restoreEnergy(amount());

    }

    @Override
    public String GUID() {
        return ConsumableRegister.RESTORE_ENERGY_ID;
    }

    @Override
    public float amount() {
        return 20;
    }
}
