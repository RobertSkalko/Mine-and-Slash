package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ConsumableRegister {

    public static final String RESTORE_HEALTH_ID = Ref.MODID + ":consumable/restore_health_potion";

    public static final String RESTORE_MANA_ID = Ref.MODID + ":consumable/restore_mana_item";
    public static final String RESTORE_ENERGY_ID = Ref.MODID + ":consumable/restore_energy_item";
    public static final String GIVE_MANA_REGEN_BUFF_ID = Ref.MODID + ":consumable/give_mana_regen_buff";

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> reg = event.getRegistry();

        //  reg.registerForgeConfigs(new RestoreEnergyItem());
        //reg.registerForgeConfigs(new RestoreManaItem());
        //reg.registerForgeConfigs(new GiveManaRegenBuffItem());
    }

}
