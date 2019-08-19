package com.robertx22.mine_and_slash.config.compatible_items.auto_gen;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashSet;

public class AvgMat {

    public static class ArmorValues {

        public float dur = 0;
        public float ench = 0;
        public float tough = 0;
        public float reduct = 0;

    }

    private static void add(HashSet<IArmorMaterial> list, IArmorMaterial mat) {

        if (mat.getClass().isEnum() || !list.stream()
                .anyMatch(x -> x.getClass().equals(mat.getClass()))) {

            list.add(mat);

        }

    }

    public static ArmorValues GetAvgArmorValues() {
        ArmorValues val = new ArmorValues();

        EquipmentSlotType slot = EquipmentSlotType.CHEST;

        HashSet<IArmorMaterial> mats = new HashSet<>();

        ForgeRegistries.ITEMS.getValues()
                .stream()
                .filter(x -> x instanceof ArmorItem)
                .forEach(x -> add(mats, ((ArmorItem) x).getArmorMaterial()));

        val.dur = (float) (mats.stream()
                .mapToDouble(x -> x.getDurability(slot))
                .sum() / mats.size());

        val.ench = (float) (mats.stream()
                .mapToDouble(x -> x.getEnchantability())
                .sum() / mats.size());

        val.tough = (float) (mats.stream().mapToDouble(x -> x.getToughness()).sum() / mats
                .size());

        val.reduct = (float) (mats.stream()
                .mapToDouble(x -> x.getDamageReductionAmount(slot))
                .sum() / mats.size());

        return val;

    }

}
