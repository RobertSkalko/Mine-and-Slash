package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueNecklace;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueRing;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueSword;
import com.robertx22.mine_and_slash.database.unique_items.jewelry.necklace.BirthingMiracleNecklace;
import com.robertx22.mine_and_slash.database.unique_items.jewelry.necklace.SkullOfSpiritsNecklace;
import com.robertx22.mine_and_slash.database.unique_items.jewelry.ring.GreedsPersistenceRing;
import com.robertx22.mine_and_slash.database.unique_items.weapons.sword.WaterElementalSword;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class UniqueGearItemRegister {

    public static DeferredRegister<Item> REG = new DeferredRegister<>(ForgeRegistries.ITEMS, Ref.MODID);

    public static RegistryObject<Item> WATER_ELEMENTAL_SWORD = of(() -> new BaseUniqueSword(), new WaterElementalSword());
    public static RegistryObject<Item> BIRTHING_MIRACLE_NECKLACE = of(() -> new BaseUniqueNecklace(), new BirthingMiracleNecklace());
    public static RegistryObject<Item> SKULL_OF_SPIRITS_NECKLACE = of(() -> new BaseUniqueNecklace(), new SkullOfSpiritsNecklace());
    public static RegistryObject<Item> GREEDS_PERSISTENCE_RING = of(() -> new BaseUniqueRing(), new GreedsPersistenceRing());

    static RegistryObject<Item> of(Supplier<Item> c, IUnique uniq) {
        RegistryObject<Item> wrap = REG.register(uniq.getGeneratedResourceID(), c);
        return wrap;
    }
}
