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
import com.robertx22.spells.projectile.firebolt.SpellFireBolt;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

@Mod.EventBusSubscriber
public class OnLogin {

    public static void GiveStarterItems(EntityPlayer player) {

	GearBlueprint print = new GearBlueprint(1);
	print.SetSpecificType(new Sword().GUID());
	print.LevelRange = false;
	print.SetSpecificRarity(0);

	player.inventory.addItemStackToInventory(GearGen.CreateStack(print));
	print.SetSpecificType(new Boots().GUID());
	player.inventory.addItemStackToInventory(GearGen.CreateStack(print));
	print.SetSpecificType(new Chest().GUID());
	player.inventory.addItemStackToInventory(GearGen.CreateStack(print));
	print.SetSpecificType(new Helmet().GUID());
	player.inventory.addItemStackToInventory(GearGen.CreateStack(print));
	print.SetSpecificType(new Pants().GUID());
	player.inventory.addItemStackToInventory(GearGen.CreateStack(print));

	print.SetSpecificType(new Ring().GUID());
	player.inventory.addItemStackToInventory(GearGen.CreateStack(print));
	print.SetSpecificType(new Ring().GUID());
	player.inventory.addItemStackToInventory(GearGen.CreateStack(print));
	print.SetSpecificType(new Necklace().GUID());
	player.inventory.addItemStackToInventory(GearGen.CreateStack(print));
	print.SetSpecificType(new Bracelet().GUID());
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

		UnitData data = Load.Unit(player);

		data.onLogin(player);

		player.getCapability(EntityData.Data, null).syncToClient(player);

	    } else {
		player.sendMessage(
			new TextComponentString("Error, player has no capability!" + Ref.NAME + " mod is broken!"));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

}
