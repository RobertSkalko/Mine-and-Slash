package com.robertx22.onevent;

import com.robertx22.database.lists.Stats;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.Stat;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.datasaving.UnitSaving;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

@Mod.EventBusSubscriber
public class OnLogin {

	@SubscribeEvent
	public static void onLogin(PlayerLoggedInEvent event) {

		if (event.player.world.isRemote) {
			return;
		}

		EntityPlayer player = event.player;

		if (player.hasCapability(EntityData.Data, null)) {

			if (UnitSaving.Load(player) == null) {
				UnitSaving.Save(player, new Unit());
			} else {

				Unit unit = UnitSaving.Load(player);

				for (Stat stat : Stats.All.values()) {
					if (!unit.Stats.containsKey(stat.Name())) {
						unit.Stats.put(stat.Name(), stat);
						player.sendMessage(
								new TextComponentString("New Stat: '" + stat.Name() + "' has been added to the game!"));
					}
				}

				UnitSaving.Save(player, unit);

			}
		}

	}

}
