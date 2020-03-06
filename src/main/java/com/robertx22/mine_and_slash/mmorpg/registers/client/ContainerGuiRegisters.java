package com.robertx22.mine_and_slash.mmorpg.registers.client;

import com.robertx22.mine_and_slash.blocks.item_modify_station.ContainerGearModify;
import com.robertx22.mine_and_slash.blocks.item_modify_station.GuiGearModify;
import com.robertx22.mine_and_slash.blocks.map_device.ContainerMapDevice;
import com.robertx22.mine_and_slash.blocks.map_device.GuiMapDevice;
import com.robertx22.mine_and_slash.blocks.repair_station.ContainerGearRepair;
import com.robertx22.mine_and_slash.blocks.repair_station.GuiGearRepair;
import com.robertx22.mine_and_slash.blocks.salvage_station.ContainerGearSalvage;
import com.robertx22.mine_and_slash.blocks.salvage_station.GuiGearSalvage;
import com.robertx22.mine_and_slash.gui.professions.ProfessionGui;
import com.robertx22.mine_and_slash.items.bags.currency_bag.ContainerCurrencyBag;
import com.robertx22.mine_and_slash.items.bags.currency_bag.GuiCurrencyBag;
import com.robertx22.mine_and_slash.items.bags.loot_bag.ContainerLootBag;
import com.robertx22.mine_and_slash.items.bags.loot_bag.GuiLootBag;
import com.robertx22.mine_and_slash.items.bags.map_bag.ContainerMapBag;
import com.robertx22.mine_and_slash.items.bags.map_bag.GuiMapBag;
import com.robertx22.mine_and_slash.items.bags.master_bag.ContainerMasterBag;
import com.robertx22.mine_and_slash.items.bags.master_bag.GuiMasterBag;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModContainers;
import com.robertx22.mine_and_slash.new_content.chests.MapChestScreen;
import net.minecraft.client.gui.ScreenManager;

public class ContainerGuiRegisters {

    public static void reg() {

        // BLACKED "OBSOLTE" PARTS ARE NEEDED OR IT ERROS !!!!
        ScreenManager.<ContainerGearModify, GuiGearModify>registerFactory(ModContainers.GEAR_MODIFY.get(), GuiGearModify::new);

        ScreenManager.<ContainerMapDevice, GuiMapDevice>registerFactory(ModContainers.MAP_DEVICE.get(), GuiMapDevice::new);

        ScreenManager.<ContainerGearRepair, GuiGearRepair>registerFactory(ModContainers.GEAR_REPAIR.get(), GuiGearRepair::new);
        ScreenManager.<ContainerGearSalvage, GuiGearSalvage>registerFactory(ModContainers.GEAR_SALVAGE.get(), GuiGearSalvage::new);

        ScreenManager.<ContainerLootBag, GuiLootBag>registerFactory(ModContainers.LOOT_BAG, GuiLootBag::new);
        ScreenManager.<ContainerMapBag, GuiMapBag>registerFactory(ModContainers.MAP_BAG, GuiMapBag::new);
        ScreenManager.<ContainerCurrencyBag, GuiCurrencyBag>registerFactory(ModContainers.CURRENCY_BAG, GuiCurrencyBag::new);
        ScreenManager.<ContainerMasterBag, GuiMasterBag>registerFactory(ModContainers.MASTER_BAG, GuiMasterBag::new);

        ScreenManager.registerFactory(ModContainers.PROFESSION_RECIPE_CONTAINER, ProfessionGui::new);
        ScreenManager.registerFactory(ModContainers.MAP_CHEST.get(), MapChestScreen::new);

    }

}
