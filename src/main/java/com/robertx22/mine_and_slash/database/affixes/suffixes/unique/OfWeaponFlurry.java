package com.robertx22.mine_and_slash.database.affixes.suffixes.unique;

import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.affixes.WeaponSuffix;
import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.requirements.UniqueTierRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.CoreStatFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.WeaponDamageFlat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Stamina;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class OfWeaponFlurry extends WeaponSuffix {

    public OfWeaponFlurry(WeaponTypes type) {
        super(
            new Requirements(
                SlotRequirement.bracelet(), LevelRequirement.fromLVL50(), new UniqueTierRequirement(10)), type);
    }

    @Override
    public String GUID() {
        return "of_" + this.type.id + "_flurry";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new WeaponDamageFlat(this.type), new CoreStatFlat(Stamina.INSTANCE));
    }

    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
    }

    @Override
    public String locNameForLangFile() {
        return "Of " + type.name() + " Flurry";
    }

    @Override
    public Suffix newGeneratedInstance(WeaponTypes type) {
        return new OfWeaponFlurry(type);
    }
}
