package com.robertx22.mine_and_slash.database.spells.synergies;

import com.robertx22.mine_and_slash.database.spells.synergies.cleric.RighteousFuryAoeSynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.druid.ThornArmorThornsSynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ocean_mystic.BlizzardFrostSynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ocean_mystic.FrostballExtraDmgSynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ocean_mystic.FrostballFrostEssenceGenSynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ocean_mystic.WhirlpoolFrostDmgSynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.shaman.LightningTotemStaticSynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.shaman.ThunderSpearLightningStrikeSynergy;

public class Synergies {

    public static FrostballExtraDmgSynergy FROSTBALL_EXTRA_DMG = new FrostballExtraDmgSynergy();
    public static FrostballFrostEssenceGenSynergy FROSTBALL_FROST_ESSENCE_GEN = new FrostballFrostEssenceGenSynergy();
    public static WhirlpoolFrostDmgSynergy WHIRLPOOL_FROST_DMG = new WhirlpoolFrostDmgSynergy();
    public static BlizzardFrostSynergy BLIZZARD_FROST = new BlizzardFrostSynergy();

    public static ThunderSpearLightningStrikeSynergy THUNDER_SPEAR_LIGHTNING = new ThunderSpearLightningStrikeSynergy();
    public static LightningTotemStaticSynergy LIGHTNING_TOTEM_STATIC = new LightningTotemStaticSynergy();

    public static ThornArmorThornsSynergy THORN_ARMOR_THORNS = new ThornArmorThornsSynergy();

    public static RighteousFuryAoeSynergy RIGHTEOUS_FURY_AOE = new RighteousFuryAoeSynergy();
}
