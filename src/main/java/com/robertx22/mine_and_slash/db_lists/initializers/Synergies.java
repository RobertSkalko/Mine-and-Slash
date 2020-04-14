package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.spells.synergies.fire.FireballBurnConsumeSynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.fire.MagmaFlowerEnhancedSynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.fire.MagmaFlowerHealSynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.fire.VolcanoBurnSynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.hunting.ImbueAddHunterSynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.hunting.RecoilAddHunterSynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.hunting.RecoilAddWoundsSynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.nature.*;
import com.robertx22.mine_and_slash.database.spells.synergies.ocean.*;
import com.robertx22.mine_and_slash.database.spells.synergies.storm.LightningTotemStaticSynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.storm.ThunderDashEnergySynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.storm.ThunderSpearLightningStrikeSynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.storm.ThunderSpearThunderEssenceSynergy;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;

public class Synergies implements ISlashRegistryInit {

    private static HeartOfIceFrostSynergy HEART_OF_ICE_FROST = new HeartOfIceFrostSynergy();
    private static FrostballExtraDmgSynergy FROSTBALL_EXTRA_DMG = new FrostballExtraDmgSynergy();
    private static FrostballFrostEssenceGenSynergy FROSTBALL_FROST_ESSENCE_GEN = new FrostballFrostEssenceGenSynergy();
    private static WhirlpoolShiverSynergy WHIRLPOOL_SHIVER = new WhirlpoolShiverSynergy();
    private static HeartOfIceMagicShieldSynergy HEART_OF_ICE_MAGIC_SHIELD = new HeartOfIceMagicShieldSynergy();

    private static ThunderSpearLightningStrikeSynergy THUNDER_SPEAR_LIGHTNING = new ThunderSpearLightningStrikeSynergy();
    private static LightningTotemStaticSynergy LIGHTNING_TOTEM_STATIC = new LightningTotemStaticSynergy();
    private static ThunderSpearThunderEssenceSynergy THUNDER_SPEAR_ESSENCE = new ThunderSpearThunderEssenceSynergy();
    private static ThunderDashEnergySynergy THUNDER_DASH_ENERGY = new ThunderDashEnergySynergy();

    private static ThornArmorThornsSynergy THORN_ARMOR_THORNS = new ThornArmorThornsSynergy();
    private static ThornBushMajorThornsSynergy THORN_BUSH_MAJOR_TORNS = new ThornBushMajorThornsSynergy();
    private static RegenerateThornsSynergy REGEN_THORNS = new RegenerateThornsSynergy();
    private static RegenerateAoeSynergy REGEN_AOE = new RegenerateAoeSynergy();
    private static PoisonedWeaponsThornsSynergy POISONED_WEAPONS_THORNS = new PoisonedWeaponsThornsSynergy();

    private static MagmaFlowerEnhancedSynergy MAGMA_FLOWER_ENHANCED = new MagmaFlowerEnhancedSynergy();
    private static FireballBurnConsumeSynergy FIREBALL_BURN_CONSUME = new FireballBurnConsumeSynergy();
    private static VolcanoBurnSynergy VOLCANO_BURN = new VolcanoBurnSynergy();
    private static MagmaFlowerHealSynergy MAGMA_FLOWER_HEAL = new MagmaFlowerHealSynergy();

    private static ImbueAddHunterSynergy IMBUE_CRIT_HUNTER = new ImbueAddHunterSynergy();
    private static RecoilAddHunterSynergy RECOIL_ADD_HUNTER = new RecoilAddHunterSynergy();
    private static RecoilAddWoundsSynergy RECOIL_ADD_WOUNDS = new RecoilAddWoundsSynergy();

    @Override
    public void registerAll() {

        HEART_OF_ICE_FROST.registerToSlashRegistry();
        FROSTBALL_EXTRA_DMG.registerToSlashRegistry();
        FROSTBALL_FROST_ESSENCE_GEN.registerToSlashRegistry();
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

        IMBUE_CRIT_HUNTER.registerToSlashRegistry();
        RECOIL_ADD_HUNTER.registerToSlashRegistry();
        RECOIL_ADD_WOUNDS.registerToSlashRegistry();

        new TidalWaveFrostSynergy().registerToSlashRegistry();

    }
}
