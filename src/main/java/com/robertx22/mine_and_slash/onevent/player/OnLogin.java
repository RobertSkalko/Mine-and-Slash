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
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.loot.blueprints.GearBlueprint;
import com.robertx22.mine_and_slash.loot.blueprints.MapBlueprint;
import com.robertx22.mine_and_slash.loot.gens.util.GearCreationUtils;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.mmorpg.registers.common.BlockRegister;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ConfigRegister;
import com.robertx22.mine_and_slash.packets.RegistryPacket;
import com.robertx22.mine_and_slash.saveclasses.gearitem.GearItemEnum;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.localization.Chats;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
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

            ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();

            ConfigRegister.CONFIGS.values().forEach(x -> x.sendToClient(player));

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
                player.sendMessage(
                        new StringTextComponent("Error, player has no capability!" + Ref.MOD_NAME + " mod is broken!"));
            }

            SlashRegistry.getAllRegistries().forEach(x -> {
                if (x.getType().getEmpty() != null) {
                    try {
                        MMORPG.sendToClient(new RegistryPacket(x.getType()), player);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void giveGear(GearItemSlot type, PlayerEntity player) {
        GearBlueprint print = new GearBlueprint(1);
        print.gearItemSlot.set(type);
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

        player.inventory.addItemStackToInventory(new ItemStack(ItemOre.ItemOres.get(0)));

        if (MMORPG.RUN_DEV_TOOLS) {
            // TESTING MAPS
            MapBlueprint map = new MapBlueprint(1, 1);
            player.inventory.addItemStackToInventory(map.createStack());

            ItemStack mapdevice = new ItemStack(BlockRegister.ITEMBLOCK_MAP_DEVICE);
            mapdevice.setCount(64);
            player.inventory.addItemStackToInventory(mapdevice);
        }
    }

}
