package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.items.gearitems.bases.IWeapon;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EnchantRegisters {

    public static EnchantmentType WEAPON_ENCHANT = EnchantmentType.create(Ref.MODID + ":weapon", x -> x instanceof IWeapon || EnchantmentType.WEAPON
            .canEnchantItem(x));

    public static List<Enchantment> blacklist = Arrays.asList(Enchantments.SMITE, Enchantments.SHARPNESS, Enchantments.BANE_OF_ARTHROPODS, Enchantments.SWEEPING);

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Enchantment> event) {

        for (Enchantment enchant : ForgeRegistries.ENCHANTMENTS) {

            if (blacklist.contains(enchant) == false) {
                if (enchant.type.equals(EnchantmentType.WEAPON)) {
                    enchant.type = WEAPON_ENCHANT;
                    event.getRegistry().register(enchant);
                }
            }
        }
    }

}
