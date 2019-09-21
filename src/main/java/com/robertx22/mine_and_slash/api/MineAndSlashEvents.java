package com.robertx22.mine_and_slash.api;

import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RepairUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;

import java.util.List;

public class MineAndSlashEvents {

    public static class GiveExpEvent extends LivingEvent {

        public int experience = 0;
        public UnitData playerCapability;
        public LivingEntity entityKilled;
        public PlayerEntity player;

        public GiveExpEvent(LivingEntity killed, PlayerEntity player, UnitData playerdata,
                            int exp) {
            super(player);
            this.entityKilled = killed;
            this.player = player;
            this.playerCapability = playerdata;
            this.experience = exp;
            this.setResult(Result.ALLOW);

        }

    }

    // called right before stats are calculated.
    public static class OnStatCalculation extends LivingEvent {

        public UnitData data;
        public LivingEntity entityKilled;

        public OnStatCalculation(LivingEntity entity, UnitData data) {
            super(entity);
            this.data = data;
        }
    }

    // called before stat calculation to gather all the gears. Add it here if you say use a different jewerly mod that isn't compatible with one i use
    public static class CollectGearStacksEvent extends LivingEvent {

        public List<GearItemData> gears;

        public CollectGearStacksEvent(LivingEntity entity, List<GearItemData> gears) {
            super(entity);
            this.gears = gears;
        }

        public void add(GearItemData data) {
            if (data != null) {
                gears.add(data);
            }
        }

        public void add(ItemStack stack) {
            if (isStackValidGear(stack)) {
                GearItemData data = Gear.Load(stack);
                if (data != null) {
                    gears.add(data);
                }
            }
        }

        public boolean isStackValidGear(ItemStack stack) {

            if (stack == null) {
                return false;
            }

            if (stack.isDamageable()) {
                return RepairUtils.isItemBroken(stack) == false;
            }

            return true;

        }

    }

}