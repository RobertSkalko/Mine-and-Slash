package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueSword;
import com.robertx22.mine_and_slash.database.unique_items.weapons.sword.WaterElementalSword;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class UniqueGearItemRegister {

    public static DeferredRegister<Item> REG = new DeferredRegister<>(ForgeRegistries.ITEMS, Ref.MODID);

    public static RegistryObject<Item> WATER_ELEMENTAL_SWORD = unique(() -> new BaseUniqueSword(), new WaterElementalSword());

    static RegistryObject<Item> unique(Supplier<Item> c, IUnique uniq) {
        RegistryObject<Item> wrap = REG.register(uniq.getGeneratedResourceID(), c);

        return wrap;
    }
}
