package com.robertx22.mine_and_slash.onevent.my_events;

import com.robertx22.mine_and_slash.a_libraries.curios.MyCurioUtils;
import com.robertx22.mine_and_slash.api.MineAndSlashEvents;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class CollectGearEvent {

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void onGiveExp(MineAndSlashEvents.CollectGearStacksEvent event) {

        getEquipsExcludingWeapon(event.getEntityLiving()).forEach(x -> event.add(x));
        addHeldItems(event);

    }

    public static List<ItemStack> getEquipsExcludingWeapon(LivingEntity entity) {

        List<ItemStack> list = new ArrayList<ItemStack>();

        for (ItemStack stack : entity.getArmorInventoryList()) {
            if (stack != null) {
                list.add(stack);
            }
        }

        if (entity instanceof PlayerEntity) {
            list.addAll(MyCurioUtils.getAllSlots((PlayerEntity) entity));
        }
        return list;

    }

    public static void addHeldItems(MineAndSlashEvents.CollectGearStacksEvent event) {

        ItemStack weapon = event.getEntityLiving().getHeldItemMainhand();
        if (event.isStackValidGear(weapon)) {
            GearItemData wep = Gear.Load(weapon);
            if (wep != null && wep.GetBaseGearType() != null && wep.GetBaseGearType()
                    .slotType()
                    .equals(GearItemSlot.GearSlotType.Weapon)) {
                event.add(wep);
            }

        }

        ItemStack offhand = event.getEntityLiving().getHeldItemOffhand();
        if (event.isStackValidGear(offhand)) {

            GearItemData off = Gear.Load(offhand);
            if (off != null && off.GetBaseGearType() != null && off.GetBaseGearType()
                    .slotType()
                    .equals(GearItemSlot.GearSlotType.OffHand)) {
                event.add(off);
            } else if (off != null && off.GetBaseGearType().slotType().equals(GearItemSlot.GearSlotType.Weapon)) {
                event.getEntityLiving().sendMessage(new StringTextComponent("You can't wear a weapon in offhand."));
            }

        }
    }

}
