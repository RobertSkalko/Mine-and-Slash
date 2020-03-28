package com.robertx22.mine_and_slash.database.unique_items.shields;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.offhand.Shield;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.CoreStatFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.BlockStrengthFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Strength;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Vitality;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.List;

public class ShieldEleResist implements IUnique {

    public ShieldEleResist() {
        super();
    }

    @Override
    public String locDescForLangFile() {
        return "Fear no elements.";
    }

    static StatReq req = new StatReq(LvlPointStat.STAMINA, StatReq.Size.NORMAL);

    @Override
    public GearItemSlot getGearSlot() {
        return Shield.INSTANCE;
    }

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(
            new ElementalResistFlat(Elements.Elemental), new CoreStatFlat(Vitality.INSTANCE).size(StatMod.Size.HALF_MORE),
            new CoreStatFlat(Strength.INSTANCE).size(StatMod.Size.HALF_MORE)
        );
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new BlockStrengthFlat());
    }

    @Override
    public String locNameForLangFile() {
        return TextFormatting.YELLOW + "Shield of Resistance";
    }

    @Override
    public String GUID() {
        return "shieldresist0";
    }

    @Override
    public int Tier() {
        return 5;
    }
}

