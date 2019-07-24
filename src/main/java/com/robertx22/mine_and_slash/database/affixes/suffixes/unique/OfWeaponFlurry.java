package com.robertx22.mine_and_slash.database.affixes.suffixes.unique;

import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.affixes.WeaponSuffix;
import com.robertx22.mine_and_slash.database.gearitemslots.Bracelet;
import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.requirements.UniqueTierRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.corestats.StaminaFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.WeaponDamageFlat;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class OfWeaponFlurry extends WeaponSuffix {

    public OfWeaponFlurry(WeaponTypes type) {
        super(type);
    }

    @Override
    public String GUID() {
        return "of_" + this.type.name() + "_flurry";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new WeaponDamageFlat(this.type), new StaminaFlat());
    }

    @Override
    public int getRarityRank() {
        return IRarity.Mythic;
    }

    @Override
    public Requirements requirements() {
        return new Requirements(new SlotRequirement(new Bracelet()), LevelRequirement.fromLVL50(), new UniqueTierRequirement(10));
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
