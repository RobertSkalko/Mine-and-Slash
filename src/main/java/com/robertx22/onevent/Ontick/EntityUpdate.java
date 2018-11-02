package com.robertx22.onevent.ontick;

import java.util.List;

import com.robertx22.mmorpg.Main;
import com.robertx22.network.EntityPackage;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.datasaving.UnitSaving;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;

public class EntityUpdate {
	public EntityUpdate(EntityPlayerMP player, List<EntityLivingBase> entities) {
		this.player = player;
		this.entities = entities;
	}

	public EntityPlayerMP player;
	public List<EntityLivingBase> entities;
	public int current = 0;

	public boolean isFinished() {
		return current >= entities.size();
	}

	public void update() {

		if (!isFinished()) {

			String json = entities.get(current).getCapability(EntityData.Data, null).getNBT()
					.getString(UnitSaving.DataLocation);

			if (json != null && !json.isEmpty()) {
				EntityPackage mobpacket = new EntityPackage(json);
				Main.Network.sendTo(mobpacket, (EntityPlayerMP) player);
				current++;
			}
		}

	}

}