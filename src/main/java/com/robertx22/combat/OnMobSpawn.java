package com.robertx22.combat;

import com.robertx22.capability.EntityData;
import com.robertx22.constants.*;
import com.robertx22.mmorpg.ModConfig;
import com.robertx22.utilityclasses.GeneralUtils;
import com.robertx22.utilityclasses.RandomUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OnMobSpawn {

    @SubscribeEvent
    public void onMobSpawn(LivingSpawnEvent.CheckSpawn event) {

        if (Minecraft.getMinecraft().player == null) {
            return;
        }
        if (!(event.getEntityLiving() instanceof EntityMob)) {
            return;
        }
        if (event.getEntityLiving().world.isRemote) {
            return;
        }

        if (event.getEntityLiving().hasCapability(EntityData.Data, null) && !event.getEntityLiving()
                .getCapability(EntityData.Data, null).getNBT().getBoolean(Tag.ENTITY_INFO)) {

            EntityLiving mob = (EntityLiving) event.getEntityLiving();

            EntityData.IData data = mob.getCapability(EntityData.Data, null);

            NBTTagCompound nbt = GeneralUtils.getdefaultEntityNBT();

            int rarity = RandomUtils.rollArray(Chances.MOB_SPAWN_RARITY);

            int lvl = Mob.getRandomLevel(event.getWorld().playerEntities.get(0));

            int hp = (int) (mob.getMaxHealth() * lvl * Mob.rarityHPMulti[rarity] * ModConfig.Cheats.DIFFICULTY);
            int dmg = (int) (10 * lvl * Mob.rarityDMGMulti[rarity] / 4 * ModConfig.Cheats.DIFFICULTY);

            nbt.setInteger(Stats.HEALTH.name, hp);
            nbt.setInteger(Tag.LEVEL, lvl);
            nbt.setInteger(Tag.RARITY_NUMBER, rarity);
            nbt.setString(Tag.RARITY, Mob.rarityNames[rarity]);
            nbt.setBoolean(Tag.ENTITY_INFO, true);
            nbt.setInteger(Tag.DAMAGE, dmg);

            mob.setCustomNameTag(Gear.rarityChatColors[rarity] + "" + " " + "LvL " + lvl + " " + Mob
                    .rarityNames[rarity]
                    + " "
                    + mob.getName());

            mob.setAlwaysRenderNameTag(true);

            data.setNBT(nbt);

            event.setResult(Event.Result.ALLOW);
        }

    }

}
