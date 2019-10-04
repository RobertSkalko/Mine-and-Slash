package com.robertx22.mine_and_slash.database.items.currency.infusions;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.DodgeRatingFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.MajorArmorFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ListUtils;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

import java.util.Arrays;
import java.util.List;

public class DefenseInfusionItem extends BaseInfusionItem {

    public DefenseInfusionItem() {
        super(name);

    }

    private static final String name = "defense_infusion";

    @ObjectHolder(Ref.MODID + ":" + name)
    public static final Item ITEM = null;

    @Override
    public List<StatMod> weaponInfusions() {
        return Arrays.asList(new DodgeRatingFlat(), new MajorArmorFlat());
    }

    @Override
    public List<StatMod> armorInfusions() {

        return ListUtils.newList(new ElementalResistFlat(Elements.Physical).allSingleElementVariations(), new ArmorFlat(), new DodgeRatingFlat());
    }

    @Override
    public List<StatMod> jewerlyInfusions() {
        return armorInfusions();
    }

    @Override
    public String GUID() {
        return name;
    }

    @Override
    public String locDescForLangFile() {
        return "Infuses an item with Defense Modifiers";
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Defense Infusion";
    }

    @Override
    public int instabilityAddAmount() {
        return 5;
    }
}
