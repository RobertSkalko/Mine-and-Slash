package com.robertx22.onevent.combatevent;

import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.capability.bases.CommonStatUtils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class OnMobSpawn {

    @SubscribeEvent
    public static void onMobSpawn(EntityJoinWorldEvent event) {

	if (!(event.getEntity() instanceof EntityLivingBase)) {
	    return;
	}

	EntityLivingBase entity = (EntityLivingBase) event.getEntity();

	if (entity.world.isRemote) {
	    return;
	}

	if (entity.hasCapability(EntityData.Data, null) == false) {
	    return;
	}

	try {
	    IWorldData data = event.getWorld().getCapability(WorldData.Data, null);

	    if (!(entity instanceof EntityPlayer)) {
		if (event.getWorld().hasCapability(WorldData.Data, null)) {

		    UnitData endata = entity.getCapability(EntityData.Data, null);
		    Unit check = endata.getUnit();

		    if (check == null) {

			Unit unit = Unit.Mob(entity, data);

			endata.forceSetUnit(unit);

		    }

		}
	    } else {
		UnitData endata = entity.getCapability(EntityData.Data, null);
		if (endata != null && endata.getUnit() != null) {
		    CommonStatUtils.addMapAffixes(data, entity, endata.getUnit(), endata);
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

}
