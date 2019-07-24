package com.robertx22.mine_and_slash.onevent.player;

import com.robertx22.mine_and_slash.database.gearitemslots.*;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.loot.blueprints.GearBlueprint;
import com.robertx22.mine_and_slash.loot.blueprints.MapBlueprint;
import com.robertx22.mine_and_slash.loot.blueprints.SpellBlueprint;
import com.robertx22.mine_and_slash.loot.gens.GearLootGen;
import com.robertx22.mine_and_slash.loot.gens.MapLootGen;
import com.robertx22.mine_and_slash.loot.gens.SpellLootGen;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.mmorpg.registers.common.BlockRegister;
import com.robertx22.mine_and_slash.spells.self.SpellInstantHeal;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.localization.Chats;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class OnLogin {

    @SubscribeEvent
    public static void onLogin(PlayerLoggedInEvent event) {

        if (event.getPlayer().world.isRemote) {
            return;
        }

        try {

            PlayerEntity player = event.getPlayer();

            if (MMORPG.RUN_DEV_TOOLS) {
                player.sendMessage(Chats.Dev_tools_enabled_contact_the_author.locName()
                        .setStyle(new Style().setColor(Styles.RED)));

            }

            if (Load.hasUnit(player)) {

                UnitData data = Load.Unit(player);

                data.onLogin(player);

                data.syncToClient(player);

                Load.playerMapData(player).teleportPlayerBack(player);

            } else {
                player.sendMessage(new StringTextComponent("Error, player has no capability!" + Ref.MOD_NAME + " mod is broken!"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void GiveStarterItems(PlayerEntity player) {

        GearBlueprint print = new GearBlueprint(1);
        print.SetSpecificType(new Sword().GUID());
        print.LevelRange = false;
        print.setSpecificRarity(0);

        player.inventory.addItemStackToInventory(GearLootGen.CreateStack(print));
        print.SetSpecificType(new Boots().GUID());
        player.inventory.addItemStackToInventory(GearLootGen.CreateStack(print));
        print.SetSpecificType(new Chest().GUID());
        player.inventory.addItemStackToInventory(GearLootGen.CreateStack(print));
        print.SetSpecificType(new Helmet().GUID());
        player.inventory.addItemStackToInventory(GearLootGen.CreateStack(print));
        print.SetSpecificType(new Pants().GUID());
        player.inventory.addItemStackToInventory(GearLootGen.CreateStack(print));

        print.SetSpecificType(new Ring().GUID());
        player.inventory.addItemStackToInventory(GearLootGen.CreateStack(print));
        print.SetSpecificType(new Ring().GUID());
        player.inventory.addItemStackToInventory(GearLootGen.CreateStack(print));
        print.SetSpecificType(new Necklace().GUID());
        player.inventory.addItemStackToInventory(GearLootGen.CreateStack(print));
        print.SetSpecificType(new Bracelet().GUID());
        player.inventory.addItemStackToInventory(GearLootGen.CreateStack(print));

        SpellBlueprint spell = new SpellBlueprint(1);
        spell.SetSpecificType(new SpellInstantHeal().GUID());
        spell.LevelRange = false;
        spell.setSpecificRarity(0);

        player.inventory.addItemStackToInventory(new ItemStack(ItemOre.ItemOres.get(0)));

        player.inventory.addItemStackToInventory(SpellLootGen.Create(spell));

        if (MMORPG.RUN_DEV_TOOLS) {
            // TESTING MAPS
            MapBlueprint map = new MapBlueprint(1, 1);
            player.inventory.addItemStackToInventory(MapLootGen.Create(map));
            player.inventory.addItemStackToInventory(MapLootGen.Create(map));
            player.inventory.addItemStackToInventory(MapLootGen.Create(map));
            player.inventory.addItemStackToInventory(MapLootGen.Create(map));
            player.inventory.addItemStackToInventory(MapLootGen.Create(map));
            player.inventory.addItemStackToInventory(MapLootGen.Create(map));

            ItemStack seeds = new ItemStack(Items.WHEAT_SEEDS);
            seeds.setCount(64);
            player.inventory.addItemStackToInventory(seeds);

            ItemStack mapdevice = new ItemStack(BlockRegister.ITEMBLOCK_MAP_DEVICE);
            mapdevice.setCount(64);
            player.inventory.addItemStackToInventory(mapdevice);
        }
    }

}
