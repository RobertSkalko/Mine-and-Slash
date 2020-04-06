package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.spells.synergies.druid.*;
import com.robertx22.mine_and_slash.database.spells.synergies.ember_mage.FireballBurnConsumeSynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ember_mage.MagmaFlowerEnhancedSynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ember_mage.MagmaFlowerHealSynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ember_mage.VolcanoBurnSynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ocean_mystic.*;
import com.robertx22.mine_and_slash.database.spells.synergies.ranger.ImbueCritAddHunterSynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ranger.RecoilAddHunterSynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ranger.RecoilAddWoundsSynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ranger.TripleShotConsumeHunterSynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.shaman.LightningTotemStaticSynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.shaman.ThunderDashEnergySynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.shaman.ThunderSpearLightningStrikeSynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.shaman.ThunderSpearThunderEssenceSynergy;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;

public class Synergies implements ISlashRegistryInit {

    public static HeartOfIceFrostSynergy HEART_OF_ICE_FROST = new HeartOfIceFrostSynergy();
    public static FrostballExtraDmgSynergy FROSTBALL_EXTRA_DMG = new FrostballExtraDmgSynergy();
    public static FrostballFrostEssenceGenSynergy FROSTBALL_FROST_ESSENCE_GEN = new FrostballFrostEssenceGenSynergy();
    public static WhirlpoolFrostDmgSynergy WHIRLPOOL_FROST_DMG = new WhirlpoolFrostDmgSynergy();
    public static WhirlpoolShiverSynergy WHIRLPOOL_SHIVER = new WhirlpoolShiverSynergy();
    public static HeartOfIceMagicShieldSynergy HEART_OF_ICE_MAGIC_SHIELD = new HeartOfIceMagicShieldSynergy();

    public static ThunderSpearLightningStrikeSynergy THUNDER_SPEAR_LIGHTNING = new ThunderSpearLightningStrikeSynergy();
    public static LightningTotemStaticSynergy LIGHTNING_TOTEM_STATIC = new LightningTotemStaticSynergy();
    public static ThunderSpearThunderEssenceSynergy THUNDER_SPEAR_ESSENCE = new ThunderSpearThunderEssenceSynergy();
    public static ThunderDashEnergySynergy THUNDER_DASH_ENERGY = new ThunderDashEnergySynergy();

    public static ThornArmorThornsSynergy THORN_ARMOR_THORNS = new ThornArmorThornsSynergy();
    public static ThornBushMajorThornsSynergy THORN_BUSH_MAJOR_TORNS = new ThornBushMajorThornsSynergy();
    public static RegenerateThornsSynergy REGEN_THORNS = new RegenerateThornsSynergy();
    public static RegenerateAoeSynergy REGEN_AOE = new RegenerateAoeSynergy();
    public static PoisonedWeaponsThornsSynergy POISONED_WEAPONS_THORNS = new PoisonedWeaponsThornsSynergy();

    public static MagmaFlowerEnhancedSynergy MAGMA_FLOWER_ENHANCED = new MagmaFlowerEnhancedSynergy();
    public static FireballBurnConsumeSynergy FIREBALL_BURN_CONSUME = new FireballBurnConsumeSynergy();
    public static VolcanoBurnSynergy VOLCANO_BURN = new VolcanoBurnSynergy();
    public static MagmaFlowerHealSynergy MAGMA_FLOWER_HEAL = new MagmaFlowerHealSynergy();

    public static TripleShotConsumeHunterSynergy TRIPLE_SHOT_HUNTER = new TripleShotConsumeHunterSynergy();
    public static ImbueCritAddHunterSynergy IMBUE_CRIT_HUNTER = new ImbueCritAddHunterSynergy();
    public static RecoilAddHunterSynergy RECOIL_ADD_HUNTER = new RecoilAddHunterSynergy();
    public static RecoilAddWoundsSynergy RECOIL_ADD_WOUNDS = new RecoilAddWoundsSynergy();

    @Override
    public void registerAll() {

        HEART_OF_ICE_FROST.registerToSlashRegistry();
        FROSTBALL_EXTRA_DMG.registerToSlashRegistry();
        FROSTBALL_FROST_ESSENCE_GEN.registerToSlashRegistry();
        WHIRLPOOL_FROST_DMG.registerToSlashRegistry();
        WHIRLPOOL_SHIVER.registerToSlashRegistry();
        HEART_OF_ICE_MAGIC_SHIELD.registerToSlashRegistry();

        THUNDER_SPEAR_LIGHTNING.registerToSlashRegistry();
        LIGHTNING_TOTEM_STATIC.registerToSlashRegistry();
        THUNDER_SPEAR_ESSENCE.registerToSlashRegistry();
        THUNDER_DASH_ENERGY.registerToSlashRegistry();

        THORN_ARMOR_THORNS.registerToSlashRegistry();
        THORN_BUSH_MAJOR_TORNS.registerToSlashRegistry();
        REGEN_THORNS.registerToSlashRegistry();
        REGEN_AOE.registerToSlashRegistry();
        POISONED_WEAPONS_THORNS.registerToSlashRegistry();

        MAGMA_FLOWER_ENHANCED.registerToSlashRegistry();
        FIREBALL_BURN_CONSUME.registerToSlashRegistry();
        VOLCANO_BURN.registerToSlashRegistry();
        MAGMA_FLOWER_HEAL.registerToSlashRegistry();

        TRIPLE_SHOT_HUNTER.registerToSlashRegistry();
        IMBUE_CRIT_HUNTER.registerToSlashRegistry();
        RECOIL_ADD_HUNTER.registerToSlashRegistry();
        RECOIL_ADD_WOUNDS.registerToSlashRegistry();

    }
}
