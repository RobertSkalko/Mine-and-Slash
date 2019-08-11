package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.items.gearitems.bases.IWeapon;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.List;

public class EnchantRegisters {

    public static EnchantmentType WEAPON_ENCHANT = EnchantmentType.create(Ref.MODID + ":weapon", x -> x instanceof IWeapon || EnchantmentType.WEAPON
            .canEnchantItem(x));

    public static List<Enchantment> blacklist = Arrays.asList(Enchantments.SMITE, Enchantments.SHARPNESS, Enchantments.BANE_OF_ARTHROPODS, Enchantments.SWEEPING);

    public static void register() {
        
        System.out.println("Mine and Slash: Overriding Weapon EnchantmentType so any instance of IWeapon is also included. This shouldn't break anything unless another mod wants to do something similar. Then the last one that overrides it wins. The purpose is to allow my weapons which don't inherit from SwordItem to have sword enchants like looting.");
        System.out.println("If you don't want me to use this dirty hack, please contribute a PR to forge. I lack experience with tags to do anything with them.");
        for (Enchantment enchant : ForgeRegistries.ENCHANTMENTS) {
            try {
                if (blacklist.contains(enchant) == false) {
                    if (enchant.type.equals(EnchantmentType.WEAPON)) {
                        enchant.type = WEAPON_ENCHANT;
                        System.out.println("Overrided: " + enchant.getRegistryName()
                                .toString());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
