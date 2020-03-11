package com.robertx22.mine_and_slash.database.gearitemslots.bases;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.AllTraitMods;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.CoreStatFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.DodgeRatingFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.SpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicShieldFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.*;
import com.robertx22.mine_and_slash.database.unique_items.ISpecificStatReq;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public abstract class GearItemSlot implements IWeighted, IAutoLocName, ISlashRegistryEntry<GearItemSlot>,
    ISpecificStatReq {

    public static StatReq.Size doubleArmorStatSize = StatReq.Size.SMALL;

    public static StatReq clothArmorReq = new StatReq(
        LvlPointStat.WISDOM, doubleArmorStatSize, LvlPointStat.INTELLIGENCE, doubleArmorStatSize);
    public static StatReq leatherArmorReq = new StatReq(
        LvlPointStat.DEXTERITY, doubleArmorStatSize, LvlPointStat.STAMINA, doubleArmorStatSize);
    public static StatReq plateArmorReq = new StatReq(
        LvlPointStat.VITALITY, doubleArmorStatSize, LvlPointStat.STRENGTH, doubleArmorStatSize);

    public static StatReq noReq = StatReq.nothing();

    public enum PlayStyle {
        MAGE, THIEF, WARRIOR, NONE
    }

    public abstract PlayStyle getPlayStyle();

    public abstract Item getBaseUniqueItem();

    public boolean isMeleeWeapon() {
        return false;
    }

    public abstract EquipmentSlotType getVanillaSlotType();

    public enum GearSlotType {
        Weapon,
        Armor,
        Jewerly,
        OffHand
    }

    public List<PosStats> clothPrimary() {
        return Arrays.asList(new PosStats(new MagicShieldFlat()));
    }

    public List<PosStats> leatherPrimary() {
        return Arrays.asList(new PosStats(new DodgeRatingFlat(), new HealthFlat().size(StatMod.Size.LOW)));
    }

    public List<PosStats> eleDmgs() {
        List<PosStats> list = (List<PosStats>) new ElementalSpellDamageFlat(
            Elements.Nature).allSingleElementVariations()
            .stream()
            .map(x -> new PosStats((StatMod) x))
            .collect(Collectors.toList());

        list.add(new PosStats(new SpellDamageFlat()));

        return list;

    }

    public int cooldownTicks() {
        return 20;
    }

    public boolean isGearOfThisType(Item item) {
        return false;
    }

    public abstract String resourceID();

    public static List<StatMod> leatherArmorStats() {
        return Arrays.asList(new ArmorFlat(), new CoreStatFlat(Dexterity.INSTANCE), new CoreStatFlat(Stamina.INSTANCE));
    }

    public static List<StatMod> plateArmorStats() {
        return Arrays.asList(new ArmorFlat(), new CoreStatFlat(Vitality.INSTANCE), new CoreStatFlat(Strength.INSTANCE));
    }

    public static List<StatMod> clothArmorStats() {
        return Arrays.asList(new ArmorFlat().size(StatMod.Size.LOW), new ManaFlat().size(StatMod.Size.HIGH), new CoreStatFlat(Intelligence.INSTANCE),
            new CoreStatFlat(Wisdom.INSTANCE)
        );
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
    public int Tier() {
        return 0;
    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.GEAR_TYPE;
    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Gear_Slots;
    }

    public abstract GearSlotType slotType();

    public List<StatMod> coreStatMods() {
        return new CoreStatFlat(Vitality.INSTANCE).generateAllPossibleStatVariations();
    }

    @Override
    public String locNameLangFileGUID() {
        return Ref.MODID + ".gear_type." + formattedGUID();
    }

    public abstract List<PosStats> getPossiblePrimaryStats();

    public abstract List<StatMod> getPossibleSecondaryStats();

    public Item getDefaultItem() {
        return Items.AIR;
    }

    public abstract HashMap<Integer, Item> getItemsForRaritiesMap();

    public int Weight() {
        return 1000;
    }

    public ItemStack getStackForRarity(int rarityNum) {

        return new ItemStack(getItemForRarity(rarityNum));

    }

    public Item getItemForRarity(int rarityNum) {

        if (getItemsForRaritiesMap().containsKey(rarityNum)) {
            return getItemsForRaritiesMap().get(rarityNum);
        }

        return getDefaultItem();

    }

    public List<StatMod> ChaosStats() {

        List<StatMod> list = new ArrayList<StatMod>();

        for (StatMod mod : SlashRegistry.StatMods()
            .getAll()
            .values()) {
            if (mod instanceof AllTraitMods) {
                list.add(mod);
            }
        }

        return list;
    }

}
