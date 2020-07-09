package com.robertx22.mine_and_slash.mmorpg.registers.client;

import com.robertx22.mine_and_slash.blocks.item_modify_station.ContainerGearModify;
import com.robertx22.mine_and_slash.blocks.item_modify_station.GuiGearModify;
import com.robertx22.mine_and_slash.blocks.repair_station.ContainerGearRepair;
import com.robertx22.mine_and_slash.blocks.repair_station.GuiGearRepair;
import com.robertx22.mine_and_slash.blocks.salvage_station.ContainerGearSalvage;
import com.robertx22.mine_and_slash.blocks.salvage_station.GuiGearSalvage;
import com.robertx22.mine_and_slash.items.bags.currency_bag.ContainerCurrencyBag;
import com.robertx22.mine_and_slash.items.bags.currency_bag.GuiCurrencyBag;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModContainers;
import net.minecraft.client.gui.ScreenManager;

public class ContainerGuiRegisters {

    public static void reg() {

        // BLACKED "OBSOLTE" PARTS ARE NEEDED OR IT ERROS !!!!
        ScreenManager.<ContainerGearModify, GuiGearModify>registerFactory(ModContainers.GEAR_MODIFY.get(), GuiGearModify::new);

        ScreenManager.<ContainerGearRepair, GuiGearRepair>registerFactory(ModContainers.GEAR_REPAIR.get(), GuiGearRepair::new);
        ScreenManager.<ContainerGearSalvage, GuiGearSalvage>registerFactory(ModContainers.GEAR_SALVAGE.get(), GuiGearSalvage::new);
        ScreenManager.<ContainerCurrencyBag, GuiCurrencyBag>registerFactory(ModContainers.CURRENCY_BAG, GuiCurrencyBag::new);

    }

}
