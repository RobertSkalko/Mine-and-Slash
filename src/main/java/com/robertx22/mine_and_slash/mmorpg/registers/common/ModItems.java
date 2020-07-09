package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.database.currency.*;
import com.robertx22.mine_and_slash.database.currency.base.CurrencyItem;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.HealthNecklace;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.ManaRing;
import com.robertx22.mine_and_slash.database.gearitemslots.offhand.ArmorShield;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.PlateBoots;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.PlateChest;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.PlateHelmet;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.PlatePants;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.Axe;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.Staff;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.Sword;
import com.robertx22.mine_and_slash.items.gearitems.armor.plate.PlateBootsItem;
import com.robertx22.mine_and_slash.items.gearitems.armor.plate.PlateChestItem;
import com.robertx22.mine_and_slash.items.gearitems.armor.plate.PlateHelmetItem;
import com.robertx22.mine_and_slash.items.gearitems.armor.plate.PlatePantsItem;
import com.robertx22.mine_and_slash.items.gearitems.baubles.ItemNecklace;
import com.robertx22.mine_and_slash.items.gearitems.baubles.ItemRing;
import com.robertx22.mine_and_slash.items.gearitems.offhands.NormalShield;
import com.robertx22.mine_and_slash.items.gearitems.weapons.ItemAxe;
import com.robertx22.mine_and_slash.items.gearitems.weapons.ItemStaff;
import com.robertx22.mine_and_slash.items.gearitems.weapons.ItemSword;
import com.robertx22.mine_and_slash.items.misc.IdentifyTomeItem;
import com.robertx22.mine_and_slash.items.reset_potions.*;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModItems {

    public static DeferredRegister<Item> REG = new DeferredRegister<>(ForgeRegistries.ITEMS, Ref.MODID);

    public static RegistryObject<Item> PLATE_BOOTS = of(() -> new PlateBootsItem(0), PlateBoots.INSTANCE);
    public static RegistryObject<Item> PLATE_HELMET = of(() -> new PlateHelmetItem(0), PlateHelmet.INSTANCE);
    public static RegistryObject<Item> PLATE_CHEST = of(() -> new PlateChestItem(0), PlateChest.INSTANCE);
    public static RegistryObject<Item> PLATE_PANTS = of(() -> new PlatePantsItem(0), PlatePants.INSTANCE);

    public static RegistryObject<Item> SWORD = of(() -> new ItemSword(0), Sword.INSTANCE);
    public static RegistryObject<Item> AXE = of(() -> new ItemAxe(0), Axe.INSTANCE);
    public static RegistryObject<Item> STAFF = of(() -> new ItemStaff(0), Staff.INSTANCE);
    public static RegistryObject<Item> ARMOR_SHIELD = of(() -> new NormalShield(ArmorShield.INSTANCE), ArmorShield.INSTANCE);

    public static RegistryObject<Item> HEALTH_NECKLACE = of(() -> new ItemNecklace(0), HealthNecklace.INSTANCE);
    public static RegistryObject<Item> MANA_RING = of(() -> new ItemRing(0), ManaRing.INSTANCE);

    // todo register all gears like this.

    public static List<RegistryObject<CurrencyItem>> currencies = new ArrayList<>();

    public static RegistryObject<CurrencyItem> ORB_OF_TRANSMUTATION = of(() -> new OrbOfTransmutationItem());
    public static RegistryObject<CurrencyItem> GEM_OF_UNIQUE_HEAVEN = of(() -> new GemOfUniqueHeaven());

    public static RegistryObject<CurrencyItem> ORB_OF_DISORDER = of(() -> new OrbOfDisorder());

    public static RegistryObject<CurrencyItem> ORB_OF_TURBULENCE = of(() -> new OrbOfTurbulence());

    public static RegistryObject<CurrencyItem> STONE_OF_HOPE = of(() -> new StoneOfHopeItem());
    public static RegistryObject<CurrencyItem> LEAF_OF_CHANGE = of(() -> new LeafOfChangeItem());
    public static RegistryObject<CurrencyItem> ORB_OF_BLESSING = of(() -> new OrbOfBlessingItem());
    public static RegistryObject<CurrencyItem> ORB_OF_UNIQUE_BLESSING = of(() -> new OrbOfUniqueBlessingItem());

    public static RegistryObject<ResetStatsPotionItem> RESET_STATS = item(() -> new ResetStatsPotionItem());
    public static RegistryObject<ResetSpellsPotionItem> RESET_SPELLS = item(() -> new ResetSpellsPotionItem());
    public static RegistryObject<ResetTalentsPotionItem> RESET_TALENTS = item(() -> new ResetTalentsPotionItem());

    public static RegistryObject<AddRemoveSpellPotionItem> ADD_RESET_SPELLS = item(() -> new AddRemoveSpellPotionItem());
    public static RegistryObject<AddRemoveTalentPotionItem> ADD_RESET_TALENTS = item(() -> new AddRemoveTalentPotionItem());

    public static RegistryObject<IdentifyTomeItem> IDENTIFY_TOME = item(() -> new IdentifyTomeItem(), "identify_tome");
    public static RegistryObject<Item> SKILL_GEM = item(() -> new Item(new Item.Properties().maxStackSize(1)
        .maxDamage(0)), "skill_gem");

    static <T extends Item & IGUID> RegistryObject<T> item(Supplier<T> c) {

        RegistryObject<T> wrap = REG.register(c.get()
            .GUID(), c);

        return wrap;

    }

    static <T extends Item> RegistryObject<T> item(Supplier<T> c, String id) {
        RegistryObject<T> wrap = REG.register(id, c);
        return wrap;
    }

    static RegistryObject<Item> of(Supplier<Item> c, GearItemSlot slot) {
        RegistryObject<Item> wrap = REG.register(slot.slotTypeFamily()
            .name() + "/" + slot.GUID(), c);
        return wrap;

    }

    static RegistryObject<CurrencyItem> of(Supplier<CurrencyItem> c) {

        RegistryObject<CurrencyItem> wrap = REG.register(c.get()
            .GUID(), c);

        currencies.add(wrap);

        return wrap;

    }

}
