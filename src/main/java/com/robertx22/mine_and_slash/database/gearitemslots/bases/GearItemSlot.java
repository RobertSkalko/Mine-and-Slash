package com.robertx22.mine_and_slash.database.gearitemslots.bases;

import com.robertx22.mine_and_slash.database.items.unique_items.ISpecificStatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.AllTraitMods;
import com.robertx22.mine_and_slash.database.stats.mods.flat.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.DodgeRatingFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.*;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.SpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicShieldFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
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
        return Arrays.asList(new PosStats(new DodgeRatingFlat().multi(0.75F), new HealthFlat().multi(1.8F)));
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
        return Arrays.asList(new ArmorFlat(), new StaminaFlat(), new DexterityFlat());
    }

    public static List<StatMod> plateArmorStats() {
        return Arrays.asList(new ArmorFlat().multi(1.25F), new VitalityFlat(), new StrengthFlat());
    }

    public static List<StatMod> clothArmorStats() {
        return Arrays.asList(
                new ArmorFlat().multi(0.75F), new ManaFlat().multi(1.75F), new IntelligenceFlat(), new WisdomFlat());
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
        return Arrays.asList(new StrengthFlat(), new VitalityFlat(), new IntelligenceFlat(), new WisdomFlat(),
                             new StaminaFlat(), new DexterityFlat()
        );
    }

    @Override
    public String locNameLangFileGUID() {
        return Ref.MODID + ".gear_type." + formattedGUID();
    }

    public abstract List<PosStats> PrimaryStats();

    public abstract List<StatMod> PossibleSecondaryStats();

    public Item DefaultItem() {
        return Items.AIR;
    }

    public abstract HashMap<Integer, Item> ItemsForRarities();

    public int Weight() {
        return 1000;
    }

    public ItemStack GetStackForRarity(int rarityNum) {

        if (ItemsForRarities().containsKey(rarityNum)) {
            return new ItemStack(ItemsForRarities().get(rarityNum));
        }

        return new ItemStack(DefaultItem());

    }

    public Item GetItemForRarity(int rarityNum) {

        if (ItemsForRarities().containsKey(rarityNum)) {
            return ItemsForRarities().get(rarityNum);
        }

        return DefaultItem();

    }

    public List<StatMod> ChaosStats() {

        List<StatMod> list = new ArrayList<StatMod>();

        for (StatMod mod : SlashRegistry.StatMods().getAll().values()) {
            if (mod instanceof AllTraitMods) {
                list.add(mod);
            }
        }

        return list;
    }

}
