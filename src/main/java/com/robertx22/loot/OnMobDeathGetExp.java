package com.robertx22.loot;

import com.robertx22.capability.EntityData;
import com.robertx22.constants.Mob;
import com.robertx22.constants.Tag;
import com.robertx22.player.PlayerData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OnMobDeathGetExp {

    @SubscribeEvent
    public void mobOnDeathgetExp(LivingDeathEvent event) {

        if (event.getEntityLiving().world.isRemote) {
            return;
        }
        if (!(event.getEntityLiving() instanceof EntityMob)) {
            return;
        }
        if (event.getSource() == null) {
            return;
        }
        if (event.getSource().getTrueSource() == null) {
            return;
        }

        if (event.getSource().getTrueSource() instanceof EntityPlayer) {

            Entity mob = event.getEntity();

            EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();

            EntityData.IData mobdata = mob.getCapability(EntityData.Data, null);

            NBTTagCompound mobnbt = mobdata.getNBT();

            int exp = mobnbt.getInteger(Tag.LEVEL) * (Mob.rarityXPMulti[mobnbt.getInteger(Tag.RARITY_NUMBER)]);

            PlayerData.giveExp(player, exp);

        }

    }
}