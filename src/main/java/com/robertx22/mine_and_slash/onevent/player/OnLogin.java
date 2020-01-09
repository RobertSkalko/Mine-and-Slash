package com.robertx22.mine_and_slash.onevent.player;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.Bracelet;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.Necklace;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.Ring;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.PlateBoots;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.PlateChest;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.PlateHelmet;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.PlatePants;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.Sword;
import com.robertx22.mine_and_slash.database.spells.self.SpellInstantHeal;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.loot.blueprints.GearBlueprint;
import com.robertx22.mine_and_slash.loot.blueprints.MapBlueprint;
import com.robertx22.mine_and_slash.loot.blueprints.SpellBlueprint;
import com.robertx22.mine_and_slash.loot.gens.MapLootGen;
import com.robertx22.mine_and_slash.loot.gens.SpellLootGen;
import com.robertx22.mine_and_slash.loot.gens.util.GearCreationUtils;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.mmorpg.registers.common.BlockRegister;
import com.robertx22.mine_and_slash.saveclasses.gearitem.GearItemEnum;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.localization.Chats;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnLogin {

    @SubscribeEvent
    public static void onLogin(PlayerEvent.PlayerLoggedInEvent event) {

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

    private static void giveGear(GearItemSlot type, PlayerEntity player) {
        GearBlueprint print = new GearBlueprint(1);
        print.SetSpecificType(type);
        print.level.LevelRange = false;
        print.rarity.setSpecificRarity(0);
        player.inventory.addItemStackToInventory(GearCreationUtils.CreateStack(print, GearItemEnum.NORMAL));

    }

    public static void GiveStarterItems(PlayerEntity player) {

        giveGear(PlatePants.INSTANCE, player);
        giveGear(PlateChest.INSTANCE, player);
        giveGear(PlateHelmet.INSTANCE, player);
        giveGear(PlateBoots.INSTANCE, player);

        giveGear(Sword.INSTANCE, player);

        giveGear(Ring.INSTANCE, player);
        giveGear(Ring.INSTANCE, player);
        giveGear(Necklace.INSTANCE, player);
        giveGear(Bracelet.INSTANCE, player);

        SpellBlueprint spell = new SpellBlueprint(1);
        spell.SetSpecificType(new SpellInstantHeal().GUID());
        spell.level.LevelRange = false;
        spell.rarity.setSpecificRarity(0);

        player.inventory.addItemStackToInventory(new ItemStack(ItemOre.ItemOres.get(0)));

        player.inventory.addItemStackToInventory(SpellLootGen.Create(spell));

        if (MMORPG.RUN_DEV_TOOLS) {
            // TESTING MAPS
            MapBlueprint map = new MapBlueprint(1, 1);
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
