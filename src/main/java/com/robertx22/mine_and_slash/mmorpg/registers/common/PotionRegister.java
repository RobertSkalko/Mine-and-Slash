package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.all.*;
import net.minecraft.potion.Effect;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PotionRegister {

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Effect> event) {

        IForgeRegistry<Effect> reg = event.getRegistry();

        reg.register(AoeRegenPotion.INSTANCE);
        reg.register(CleansePoisonPotion.INSTANCE);
        reg.register(EnergyRegenPotion.INSTANCE);
        reg.register(HealthRegenPotion.INSTANCE);
        reg.register(ManaRegenPotion.INSTANCE);
        reg.register(TeleportProtection.INSTANCE);

        for (BonusDmgPotion pot : BonusDmgPotion.INSTANCE.generateAllPossibleStatVariations()) {
            reg.register(pot);
            BonusDmgPotion.MAP.put(pot.element, pot);
        }

    }

}
