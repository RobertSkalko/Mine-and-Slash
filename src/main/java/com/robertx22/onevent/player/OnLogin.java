package com.robertx22.onevent.player;

import com.robertx22.database.gearitemslots.Boots;
import com.robertx22.database.gearitemslots.Chest;
import com.robertx22.database.gearitemslots.Helmet;
import com.robertx22.database.gearitemslots.Pants;
import com.robertx22.database.gearitemslots.Sword;
import com.robertx22.database.lists.Stats;
import com.robertx22.generation.GearGen;
import com.robertx22.generation.blueprints.GearBlueprint;
import com.robertx22.mmorpg.Ref;
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

	private static void GiveStarterItems(EntityPlayer player) {

		GearBlueprint print = new GearBlueprint(1);
		print.SetSpecificType(new Sword().Name());
		print.LevelRange = false;
		print.SetSpecificRarity(0);

		player.inventory.addItemStackToInventory(GearGen.CreateStack(print));
		print.SetSpecificType(new Boots().Name());
		player.inventory.addItemStackToInventory(GearGen.CreateStack(print));
		print.SetSpecificType(new Chest().Name());
		player.inventory.addItemStackToInventory(GearGen.CreateStack(print));
		print.SetSpecificType(new Helmet().Name());
		player.inventory.addItemStackToInventory(GearGen.CreateStack(print));
		print.SetSpecificType(new Pants().Name());
		player.inventory.addItemStackToInventory(GearGen.CreateStack(print));

	}

	private static void CheckForNewStats(EntityPlayer player) {
		Unit unit = UnitSaving.Load(player);

		for (Stat stat : Stats.All.values()) {
			if (!unit.Stats.containsKey(stat.Name())) {
				player.sendMessage(
						new TextComponentString("New Stat: '" + stat.Name() + "' has been added to the game!"));
			}
			unit.Stats.put(stat.Name(), stat);
		}

		unit.ReloadStatsAndSave(player);

	}

	@SubscribeEvent
	public static void onLogin(PlayerLoggedInEvent event) {

		if (event.player.world.isRemote) {
			return;
		}

		EntityPlayer player = event.player;

		if (player.hasCapability(EntityData.Data, null)) {

			if (UnitSaving.Load(player) == null) {
				UnitSaving.Save(player, new Unit());
				GiveStarterItems(player);
			} else {
				CheckForNewStats(player);

			}
		} else {
			player.sendMessage(
					new TextComponentString("Error, player has no capability!" + Ref.MODID + " mod is broken!"));
		}

	}

}
