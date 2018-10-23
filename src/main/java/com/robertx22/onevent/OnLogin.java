package com.robertx22.onevent;

import java.util.UUID;

import com.robertx22.database.lists.Stats;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.Stat;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.datasaving.UnitSaving;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
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

				EntityPlayer entity = event.player;

				if (entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH)
						.getModifier(UUID.fromString("0bee127e-d6e1-11e8-9f8b-f2801f1b9fd1")) == null) {

					entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH)
							.applyModifier(new AttributeModifier(
									UUID.fromString("0bee127e-d6e1-11e8-9f8b-f2801f1b9fd1"), Ref.MODID + ":hpmod",
									Integer.MAX_VALUE/* entity.getMaxHealth() * 100 */, 2));
				}

				entity.setHealth(entity.getMaxHealth());

				UnitSaving.Save(player, unit);

			}
		}

	}

}
