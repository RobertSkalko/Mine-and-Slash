package com.robertx22.mine_and_slash.database.gearitemslots.bases;

import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.database.gearitemslots.offhand.TowerShield;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.Crossbow;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.HunterBow;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.mechanics.NormalWeaponMechanic;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.mechanics.WeaponMechanic;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.melee.GemstoneSword;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.melee.PrimitiveAxe;
import com.robertx22.mine_and_slash.database.stats.types.offense.AttackSpeed;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.StatRequirement;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import top.theillusivec4.curios.api.CuriosAPI;

import java.util.HashMap;
import java.util.List;

public abstract class BaseGearType implements IAutoLocName, ISlashRegistryEntry<BaseGearType> {

    public float attacksPerSecond = 1;

    public abstract List<StatModifier> implicitStats();

    public abstract List<StatModifier> baseStats();

    public WeaponTypes weaponType() {
        return WeaponTypes.None;
    }

    public abstract List<SlotTag> getTags();

    public abstract Item getItem();

    public abstract StatRequirement getStatRequirements();

    public abstract EquipmentSlotType getVanillaSlotType();

    public int Weight() {
        return 1000;
    }

    public static class Constants {
        public static float SWORD_ATK_SPEED = 0.75F;
        public static float WAND_ATK_SPEED = 1;
        public static float AXE_ATK_SPEED = 1.25F;
    }

    public final float getAttacksPerSecondCalculated(EntityCap.UnitData data) {

        float multi = data.getUnit()
            .peekAtStat(AttackSpeed.getInstance())
            .getMultiplier();

        float f = multi * attacksPerSecond;

        return f;
    }

    public final float getAttacksPerSecondForTooltip(GearItemData gear) {
        // only show bonus atk speed from this item

        float speed = attacksPerSecond;

        for (ExactStatData x : gear.GetAllStats(false, false)) {
            if (x.getStat() instanceof AttackSpeed) {
                speed *= 1F + x.getAverageValue() / 100F;
            }
        }

        return speed;
    }

    public final boolean hasUniqueItemVersions() {
        return !SlashRegistry.UniqueGears()
            .getFilterWrapped(x -> x.getBaseGearType()
                .GUID()
                .equals(GUID())).list.isEmpty();
    }

    public enum PlayStyle {
        INT, DEX, STR, NONE;

        public boolean isINT() {
            return this == INT;
        }

        public boolean isDEX() {
            return this == DEX;
        }

        public boolean isSTR() {
            return this == STR;
        }

    }

    public final boolean isWeapon() {
        return this.family()
            .equals(SlotFamily.Weapon);
    }

    public final boolean isMeleeWeapon() {
        return this.getTags()
            .contains(SlotTag.MeleeWeapon);
    }

    public boolean isShield() {
        return getTags().contains(SlotTag.Shield);
    }

    public enum SlotTag {
        Sword(SlotFamily.Weapon),
        Axe(SlotFamily.Weapon),
        Bow(SlotFamily.Weapon),
        Wand(SlotFamily.Weapon),
        Crossbow(SlotFamily.Weapon),

        Boots(SlotFamily.Armor),
        Helmet(SlotFamily.Armor),
        Pants(SlotFamily.Armor),
        Chest(SlotFamily.Armor),

        Necklace(SlotFamily.Jewelry),
        Ring(SlotFamily.Jewelry),

        Cloth(SlotFamily.NONE),
        Plate(SlotFamily.NONE),
        Leather(SlotFamily.NONE),
        Shield(SlotFamily.NONE),

        MageWeapon(SlotFamily.NONE), MeleeWeapon(SlotFamily.NONE), RangedWeapon(SlotFamily.NONE);

        public SlotFamily family = null;

        SlotTag(SlotFamily family) {
            this.family = family;
        }
    }

    public enum SlotFamily {
        Weapon,
        Armor,
        Jewelry,
        OffHand,
        NONE;

        public boolean isJewelry() {
            return this == Jewelry;
        }

        public boolean isArmor() {
            return this == Armor;
        }

        public boolean isWeapon() {
            return this == Weapon;
        }

        public boolean isOffhand() {
            return this == OffHand;
        }

    }

    public final SlotFamily family() {
        return getTags().stream()
            .filter(x -> x.family != null)
            .findFirst()
            .get().family;
    }

    public final WeaponMechanic getWeaponMechanic() {
        return new NormalWeaponMechanic();
    }

    private static HashMap<String, HashMap<Item, Boolean>> CACHED = new HashMap<>();

    // has to use ugly stuff like this cus datapacks.
    public static boolean isGearOfThisType(BaseGearType slot, Item item) {

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
                .equals(GemstoneSword.INSTANCE.GUID())) {
                bool = item instanceof SwordItem;
            } else if (slot.GUID()
                .equals(HunterBow.INSTANCE.GUID())) {
                bool = item instanceof BowItem;
            } else if (slot.GUID()
                .equals(PrimitiveAxe.INSTANCE.GUID())) {
                bool = item instanceof AxeItem;
            } else if (slot.GUID()
                .equals(TowerShield.INSTANCE.GUID())) {
                bool = item instanceof ShieldItem;
            } else if (slot.GUID()
                .equals(Crossbow.INSTANCE.GUID())) {
                bool = item instanceof CrossbowItem;
            } else if (slot.family()
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

    @Override
    public String locNameLangFileGUID() {
        return Ref.MODID + ".gear_type." + formattedGUID();
    }

    public final boolean isMageWeapon() {
        return getTags().contains(SlotTag.MageWeapon);
    }

}
