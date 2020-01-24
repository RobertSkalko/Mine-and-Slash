package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.items.profession.alchemy.bases.BaseBuffPotion;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.all.*;
import com.robertx22.mine_and_slash.potion_effects.cleric.RighteousFuryEffect;
import com.robertx22.mine_and_slash.potion_effects.druid.MinorThornsEffect;
import com.robertx22.mine_and_slash.potion_effects.druid.PetrifyEffect;
import com.robertx22.mine_and_slash.potion_effects.ember_mage.BlazingInfernoEffect;
import com.robertx22.mine_and_slash.potion_effects.ocean_mystic.FrostEffect;
import com.robertx22.mine_and_slash.potion_effects.ocean_mystic.FrostEssenceEffect;
import com.robertx22.mine_and_slash.potion_effects.ocean_mystic.ShiverEffect;
import net.minecraft.potion.Effect;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
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
        reg.register(BleedPotion.INSTANCE);

        reg.register(FrostEffect.INSTANCE);
        reg.register(BlazingInfernoEffect.INSTANCE);
        reg.register(MinorThornsEffect.INSTANCE);
        reg.register(RighteousFuryEffect.INSTANCE);
        reg.register(PetrifyEffect.INSTANCE);
        reg.register(ShiverEffect.INSTANCE);
        reg.register(FrostEssenceEffect.INSTANCE);

        for (net.minecraft.item.Item item : ForgeRegistries.ITEMS) {
            if (item instanceof BaseBuffPotion) {
                BaseBuffPotion pot = (BaseBuffPotion) item;
                reg.register(pot.createEffect().setRegistryName(pot.GUID()));
            }

        }

    }

}
