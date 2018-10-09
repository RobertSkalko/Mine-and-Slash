package com.robertx22.utilityclasses;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

import java.util.Arrays;
import java.util.Objects;

import static com.robertx22.mmorpg.Ref.MODID;
import static java.lang.String.format;

public final class Utils {

    public static ItemStack checkNBT(ItemStack stack) {
        if (stack.getTagCompound() == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
        return stack;
    }

    public static ItemStack setUnbreakable(ItemStack stack) {
        checkNBT(stack);
        stack.getTagCompound().setBoolean("unbreakable", true);
        return stack;
    }

    public static String setName(String name) {
        return format("%s.%s", MODID, name);
    }

    public static NonNullList<ItemStack> getItemStacks(ItemStack... items) {
        NonNullList<ItemStack> list = NonNullList.create();
        list.addAll(Arrays.asList(items));
        return list;
    }

    public static ResourceLocation setRL(String path) {
        return new ResourceLocation(MODID, path);
    }

    public static String setLocation(String path) {
        return format("%s:%s", MODID, path);
    }

    public static boolean isNotEmpty(ItemStack stack) {
        return !stack.isEmpty();
    }

    public static boolean isEmpty(ItemStack stack) {
        return stack.isEmpty();
    }

    public static boolean isNotNull(Object object) {
        return object != null;
    }

    public static boolean areNotNull(Object object1, Object object2) {
        return object1 != null && object2 != null;
    }

    public static boolean isNotNullNorEmpty(String object) {
        return isNotNull(object) && !Objects.equals(object, "");
    }

    public static boolean isNull(Object object) {
        return object == null;
    }

    public static boolean isNullOrEmpty(String object) {
        return isNull(object) || Objects.equals(object, "");
    }

    public static boolean areSame(ItemStack a, Item b) {
        return a.getItem() == b;
    }

    public static boolean isArmorEmpty(ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
        return (helmet.isEmpty() && chestplate.isEmpty() && leggings.isEmpty() && boots.isEmpty());
    }
}