package com.robertx22.mine_and_slash.items.infusions;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.offense.CriticalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.offense.CriticalHitFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellDamagePercent;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellToAttackDMGFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.offense.PhysicalDamagePercent;
import com.robertx22.mine_and_slash.items.currency.CurrencyItem;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ListUtils;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

import java.util.List;

public class AttackInfusionItem extends BaseInfusionItem {

    public AttackInfusionItem() {
        super(name);

    }

    private static final String name = "attack_infusion";

    @ObjectHolder(Ref.MODID + ":" + name)
    public static final Item ITEM = null;

    @Override
    public List<StatMod> weaponInfusions() {
        return ListUtils.newList(new ElementalSpellToAttackDMGFlat(Elements.Physical).allSingleElementVariations(), new CriticalHitFlat(), new CriticalDamageFlat(), new PhysicalDamagePercent());
    }

    @Override
    public List<StatMod> armorInfusions() {
        return ListUtils.newList(new ElementalSpellDamagePercent(Elements.Physical).allSingleElementVariations(), new PhysicalDamagePercent());
    }

    @Override
    public List<StatMod> jewerlyInfusions() {
        return new ElementalResistFlat(Elements.Physical).allSingleElementVariations();
    }

    @Override
    public String GUID() {
        return name;
    }

    @Override
    public String locNameForLangFile() {
        return CurrencyItem.nameColor + "Attack Infusion";
    }

    @Override
    public String locDescForLangFile() {
        return "Infuses an item with Attack Modifiers";
    }
}
