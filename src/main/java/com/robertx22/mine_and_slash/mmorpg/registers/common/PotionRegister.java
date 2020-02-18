package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.items.profession.alchemy.bases.BaseBuffPotion;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.all.BleedPotion;
import com.robertx22.mine_and_slash.potion_effects.all.CleansePoisonPotion;
import com.robertx22.mine_and_slash.potion_effects.all.TeleportProtection;
import com.robertx22.mine_and_slash.potion_effects.cleric.DivineShieldEffect;
import com.robertx22.mine_and_slash.potion_effects.cleric.RighteousFuryEffect;
import com.robertx22.mine_and_slash.potion_effects.druid.*;
import com.robertx22.mine_and_slash.potion_effects.ember_mage.BlazingInfernoEffect;
import com.robertx22.mine_and_slash.potion_effects.ember_mage.BurnEffect;
import com.robertx22.mine_and_slash.potion_effects.ocean_mystic.FrostEffect;
import com.robertx22.mine_and_slash.potion_effects.ocean_mystic.FrostEssenceEffect;
import com.robertx22.mine_and_slash.potion_effects.ocean_mystic.ShiverEffect;
import com.robertx22.mine_and_slash.potion_effects.ranger.ImbueEffect;
import com.robertx22.mine_and_slash.potion_effects.shaman.StaticEffect;
import com.robertx22.mine_and_slash.potion_effects.shaman.ThunderEssenceEffect;
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

        reg.register(CleansePoisonPotion.INSTANCE);
        reg.register(RegenerateEffect.INSTANCE);
        reg.register(TeleportProtection.INSTANCE);
        reg.register(BleedPotion.INSTANCE);

        reg.register(FrostEffect.INSTANCE);
        reg.register(BlazingInfernoEffect.INSTANCE);
        reg.register(MinorThornsEffect.INSTANCE);
        reg.register(RighteousFuryEffect.INSTANCE);
        reg.register(PetrifyEffect.INSTANCE);
        reg.register(ShiverEffect.INSTANCE);
        reg.register(FrostEssenceEffect.INSTANCE);
        reg.register(ThornArmorEffect.INSTANCE);
        reg.register(StaticEffect.INSTANCE);
        reg.register(BurnEffect.INSTANCE);
        reg.register(ThunderEssenceEffect.INSTANCE);
        reg.register(MajorThornsEffect.INSTANCE);
        reg.register(PoisonedWeaponsEffect.getInstance());
        reg.register(DivineShieldEffect.getInstance());
        reg.register(ImbueEffect.getInstance());

        for (net.minecraft.item.Item item : ForgeRegistries.ITEMS) {
            if (item instanceof BaseBuffPotion) {
                BaseBuffPotion pot = (BaseBuffPotion) item;
                reg.register(pot.createEffect()
                    .setRegistryName(pot.GUID()));
            }

        }

    }

}
