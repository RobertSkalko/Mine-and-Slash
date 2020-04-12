package com.robertx22.mine_and_slash.database.unique_items.necklaces;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.Necklace;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean.HeartOfIceSpell;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.misc.PlusAbiliyLevelFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.HealthPercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.MagicShieldPercent;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class NecklaceWater implements IUnique {

    public NecklaceWater() {

    }

    static StatReq req = new StatReq(
        LvlPointStat.VITALITY, StatReq.Size.MEDIUM, LvlPointStat.INTELLIGENCE, StatReq.Size.MEDIUM);

    @Override
    public GearItemSlot getGearSlot() {
        return Necklace.INSTANCE;
    }

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int getTier() {
        return 2;
    }

    @Override
    public String GUID() {
        return "necklacewater0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(
            new HealthPercent(),
            new MagicShieldPercent(),
            new ArmorFlat().size(StatMod.Size.HALF_MORE),
            new ElementalResistFlat(Elements.Water).size(StatMod.Size.HALF_MORE),
            new PlusAbiliyLevelFlat(HeartOfIceSpell.getInstance())
        );
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalSpellDamageFlat(Elements.Water));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Amulet of the Glacier";
    }

    @Override
    public String locDescForLangFile() {
        return "Crystal clear and yet so incredibly tough.";
    }
}
