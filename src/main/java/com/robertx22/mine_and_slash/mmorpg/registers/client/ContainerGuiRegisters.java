package com.robertx22.mine_and_slash.mmorpg.registers.client;

import com.robertx22.mine_and_slash.blocks.gear_factory_station.ContainerGearFactory;
import com.robertx22.mine_and_slash.blocks.gear_factory_station.GuiGearFactory;
import com.robertx22.mine_and_slash.blocks.item_modify_station.ContainerGearModify;
import com.robertx22.mine_and_slash.blocks.item_modify_station.GuiGearModify;
import com.robertx22.mine_and_slash.blocks.map_device.ContainerMapDevice;
import com.robertx22.mine_and_slash.blocks.map_device.GuiMapDevice;
import com.robertx22.mine_and_slash.blocks.repair_station.ContainerGearRepair;
import com.robertx22.mine_and_slash.blocks.repair_station.GuiGearRepair;
import com.robertx22.mine_and_slash.blocks.salvage_station.ContainerGearSalvage;
import com.robertx22.mine_and_slash.blocks.salvage_station.GuiGearSalvage;
import com.robertx22.mine_and_slash.items.bags.currency_bag.ContainerCurrencyBag;
import com.robertx22.mine_and_slash.items.bags.currency_bag.GuiCurrencyBag;
import com.robertx22.mine_and_slash.items.bags.loot_bag.ContainerLootBag;
import com.robertx22.mine_and_slash.items.bags.loot_bag.GuiLootBag;
import com.robertx22.mine_and_slash.items.bags.map_bag.ContainerMapBag;
import com.robertx22.mine_and_slash.items.bags.map_bag.GuiMapBag;
import com.robertx22.mine_and_slash.items.bags.master_bag.ContainerMasterBag;
import com.robertx22.mine_and_slash.items.bags.master_bag.GuiMasterBag;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ContainerTypeRegisters;
import net.minecraft.client.gui.ScreenManager;

public class ContainerGuiRegisters {

    public static void reg() {

        // BLACKED "OBSOLTE" PARTS ARE NEEDED OR IT ERROS !!!!
        ScreenManager.<ContainerGearFactory, GuiGearFactory>registerFactory(ContainerTypeRegisters.GEAR_FACTORY, GuiGearFactory::new);
        ScreenManager.<ContainerGearModify, GuiGearModify>registerFactory(ContainerTypeRegisters.GEAR_MODIFY, GuiGearModify::new);

        ScreenManager.<ContainerMapDevice, GuiMapDevice>registerFactory(ContainerTypeRegisters.MAP_DEVICE, GuiMapDevice::new);

        ScreenManager.<ContainerGearRepair, GuiGearRepair>registerFactory(ContainerTypeRegisters.GEAR_REPAIR, GuiGearRepair::new);
        ScreenManager.<ContainerGearSalvage, GuiGearSalvage>registerFactory(ContainerTypeRegisters.GEAR_SALVAGE, GuiGearSalvage::new);

        ScreenManager.<ContainerLootBag, GuiLootBag>registerFactory(ContainerTypeRegisters.LOOT_BAG, GuiLootBag::new);
        ScreenManager.<ContainerMapBag, GuiMapBag>registerFactory(ContainerTypeRegisters.MAP_BAG, GuiMapBag::new);
        ScreenManager.<ContainerCurrencyBag, GuiCurrencyBag>registerFactory(ContainerTypeRegisters.CURRENCY_BAG, GuiCurrencyBag::new);
        ScreenManager.<ContainerMasterBag, GuiMasterBag>registerFactory(ContainerTypeRegisters.MASTER_BAG, GuiMasterBag::new);

    }

}
