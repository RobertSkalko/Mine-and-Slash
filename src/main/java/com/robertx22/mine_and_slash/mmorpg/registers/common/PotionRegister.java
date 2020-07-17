package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.vanilla_mc.potion_effects.divine.BraveryEffect;
import com.robertx22.mine_and_slash.vanilla_mc.potion_effects.divine.JudgementEffect;
import com.robertx22.mine_and_slash.vanilla_mc.potion_effects.divine.TrickeryEffect;
import com.robertx22.mine_and_slash.vanilla_mc.potion_effects.divine.WizardryEffect;
import com.robertx22.mine_and_slash.vanilla_mc.potion_effects.druid.*;
import com.robertx22.mine_and_slash.vanilla_mc.potion_effects.ember_mage.BurnEffect;
import com.robertx22.mine_and_slash.vanilla_mc.potion_effects.ocean_mystic.ColdEssenceEffect;
import com.robertx22.mine_and_slash.vanilla_mc.potion_effects.ocean_mystic.FrostEffect;
import com.robertx22.mine_and_slash.vanilla_mc.potion_effects.ocean_mystic.ShiverEffect;
import com.robertx22.mine_and_slash.vanilla_mc.potion_effects.ranger.HunterInstinctEffect;
import com.robertx22.mine_and_slash.vanilla_mc.potion_effects.ranger.ImbueEffect;
import com.robertx22.mine_and_slash.vanilla_mc.potion_effects.ranger.WoundsEffect;
import com.robertx22.mine_and_slash.vanilla_mc.potion_effects.shaman.StaticEffect;
import com.robertx22.mine_and_slash.vanilla_mc.potion_effects.shaman.ThunderEssenceEffect;
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

        reg.register(RegenerateEffect.INSTANCE);

        reg.register(BraveryEffect.INSTANCE);
        reg.register(WizardryEffect.INSTANCE);
        reg.register(TrickeryEffect.INSTANCE);

        reg.register(FrostEffect.INSTANCE);
        reg.register(PoisonEffect.INSTANCE);
        reg.register(PetrifyEffect.INSTANCE);
        reg.register(ShiverEffect.INSTANCE);
        reg.register(ColdEssenceEffect.INSTANCE);
        reg.register(JudgementEffect.INSTANCE);
        reg.register(ThornArmorEffect.INSTANCE);
        reg.register(StaticEffect.INSTANCE);
        reg.register(BurnEffect.INSTANCE);
        reg.register(ThunderEssenceEffect.INSTANCE);
        reg.register(PoisonedWeaponsEffect.getInstance());
        reg.register(ImbueEffect.getInstance());
        reg.register(HunterInstinctEffect.getInstance());
        reg.register(WoundsEffect.getInstance());

    }

}
