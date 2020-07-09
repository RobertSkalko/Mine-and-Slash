package com.robertx22.mine_and_slash.database.gearitemslots.bases;

import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.database.gearitemslots.offhand.Shield;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.*;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.mechanics.NormalWeaponMechanic;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.mechanics.WeaponMechanic;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import top.theillusivec4.curios.api.CuriosAPI;

import java.util.HashMap;
import java.util.List;

public abstract class GearItemSlot implements IWeighted, IAutoLocName, ISlashRegistryEntry<GearItemSlot> {

    public enum PlayStyle {
        MAGE, THIEF, WARRIOR, NONE
    }

    public boolean isWeapon() {
        return this.slotTypeFamily()
            .equals(SlotFamily.Weapon);
    }

    public abstract List<StatModifier> ImplicitStats();

    public abstract List<StatModifier> BaseStats();

    public WeaponTypes weaponType() {
        return WeaponTypes.None;
    }

    public abstract PlayStyle getPlayStyle();

    public boolean isMeleeWeapon() {
        return false;
    }

    public abstract EquipmentSlotType getVanillaSlotType();

    public enum SlotTag {
        Sword, Axe, Bow, Staff,
        Boots, Helmet, Pants, Chest,
        Cloth, Plate, Leather,
        Shield, Necklace, Ring,
        MageWeapon
    }

    public enum SlotFamily {
        Weapon,
        Armor,
        Jewelry,
        OffHand
    }

    public WeaponMechanic getWeaponMechanic() {
        return new NormalWeaponMechanic();
    }

    private static HashMap<String, HashMap<Item, Boolean>> CACHED = new HashMap<>();

    // has to use ugly stuff like this cus datapacks.
    public static boolean isGearOfThisType(GearItemSlot slot, Item item) {

        String id = slot.GUID();

        if (!CACHED.containsKey(id)) {
            CACHED.put(id, new HashMap<>());
        }
        if (CACHED.get(id)
            .containsKey(item)) {
            return CACHED.get(id)
                .get(item);
        }

        boolean bool = false;

        try {
            if (item instanceof ArmorItem) {
                if (slot.getVanillaSlotType() != null) {
                    if (slot.getVanillaSlotType()
                        .equals(EquipmentSlotType.FEET)) {

                        bool = ((ArmorItem) item).getEquipmentSlot()
                            .equals(EquipmentSlotType.FEET);

                    } else if (slot.getVanillaSlotType()
                        .equals(EquipmentSlotType.CHEST)) {

                        bool = ((ArmorItem) item).getEquipmentSlot()
                            .equals(EquipmentSlotType.CHEST);

                    } else if (slot.getVanillaSlotType()
                        .equals(EquipmentSlotType.HEAD)) {

                        bool = ((ArmorItem) item).getEquipmentSlot()
                            .equals(EquipmentSlotType.HEAD);

                    } else if (slot.getVanillaSlotType()
                        .equals(EquipmentSlotType.LEGS)) {

                        bool = ((ArmorItem) item).getEquipmentSlot()
                            .equals(EquipmentSlotType.LEGS);
                    }
                }
            } else if (slot.GUID()
                .equals(Sword.INSTANCE.GUID())) {
                bool = item instanceof SwordItem;
            } else if (slot.GUID()
                .equals(Trident.INSTANCE.GUID())) {
                bool = item instanceof TridentItem;
            } else if (slot.GUID()
                .equals(Bow.INSTANCE.GUID())) {
                bool = item instanceof BowItem;
            } else if (slot.GUID()
                .equals(Axe.INSTANCE.GUID())) {
                bool = item instanceof AxeItem;
            } else if (slot.GUID()
                .equals(Shield.INSTANCE.GUID())) {
                bool = item instanceof ShieldItem;
            } else if (slot.GUID()
                .equals(CrossBow.INSTANCE.GUID())) {
                bool = item instanceof CrossbowItem;
            } else if (slot.slotTypeFamily()
                .equals(SlotFamily.Jewelry)) {
                bool = CuriosAPI.getCurioTags(item)
                    .stream()
                    .anyMatch(x -> x.toString()
                        .contains(slot.GUID()));
            }

            CACHED.get(slot.GUID())
                .put(item, bool);

            return bool;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

    public abstract String resourceID();

    @Override
    public int getRarityRank() {
        return 0;
    }

    @Override
    public Rarity getRarity() {
        return Rarities.Gears.get(getRarityRank());
    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.GEAR_TYPE;
    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Gear_Slots;
    }

    public abstract SlotFamily slotTypeFamily();

    public abstract List<SlotTag> getTags();

    @Override
    public String locNameLangFileGUID() {
        return Ref.MODID + ".gear_type." + formattedGUID();
    }

    public abstract Item getItem();

    public int Weight() {
        return 1000;
    }

    public final boolean isMageWeapon() {
        return getTags().contains(SlotTag.MageWeapon);
    }

}
