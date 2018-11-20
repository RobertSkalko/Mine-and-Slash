package com.robertx22.onevent.player;

import com.robertx22.database.gearitemslots.Boots;
import com.robertx22.database.gearitemslots.Bracelet;
import com.robertx22.database.gearitemslots.Chest;
import com.robertx22.database.gearitemslots.Helmet;
import com.robertx22.database.gearitemslots.Necklace;
import com.robertx22.database.gearitemslots.Pants;
import com.robertx22.database.gearitemslots.Ring;
import com.robertx22.database.gearitemslots.Sword;
import com.robertx22.generation.GearGen;
import com.robertx22.generation.SpellItemGen;
import com.robertx22.generation.blueprints.GearBlueprint;
import com.robertx22.generation.blueprints.SpellBlueprint;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.Unit;
import com.robertx22.spells.projectile.firebolt.SpellFireBolt;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;
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

		print.SetSpecificType(new Ring().Name());
		player.inventory.addItemStackToInventory(GearGen.CreateStack(print));
		print.SetSpecificType(new Ring().Name());
		player.inventory.addItemStackToInventory(GearGen.CreateStack(print));
		print.SetSpecificType(new Necklace().Name());
		player.inventory.addItemStackToInventory(GearGen.CreateStack(print));
		print.SetSpecificType(new Bracelet().Name());
		player.inventory.addItemStackToInventory(GearGen.CreateStack(print));

		SpellBlueprint spell = new SpellBlueprint(1);
		spell.SetSpecificType(new SpellFireBolt().GUID());
		spell.LevelRange = false;
		spell.SetSpecificRarity(0);

		player.inventory.addItemStackToInventory(SpellItemGen.Create(spell));

	}

	@SubscribeEvent
	public static void onLogin(PlayerLoggedInEvent event) {

		if (event.player.world.isRemote) {
			return;
		}

		try {
			EntityPlayer player = event.player;

			if (player.hasCapability(EntityData.Data, null)) {

				Unit unit = UnitSaving.Load(player);
				UnitData data = Load.Unit(player);

				if (unit == null) {
					UnitSaving.Save(player, new Unit(player));
					GiveStarterItems(player);
				} else {
					data.recalculateStats(player);
				}

				player.getCapability(EntityData.Data, null).syncToClient(player);

			} else {
				player.sendMessage(
						new TextComponentString("Error, player has no capability!" + Ref.MODID + " mod is broken!"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
