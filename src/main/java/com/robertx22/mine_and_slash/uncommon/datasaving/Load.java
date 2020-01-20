package com.robertx22.mine_and_slash.uncommon.datasaving;

import com.robertx22.mine_and_slash.uncommon.capability.*;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nonnull;

public class Load {

    public static boolean hasUnit(
            ICapabilityProvider provider) { // tterag said this is correct
        if (provider != null) {
            return provider.getCapability(EntityCap.Data).isPresent();
        }
        return false;
    }

    public static PlayerStatsPointsCap.IPlayerStatPointsData statPoints(
            PlayerEntity provider) {

        if (provider != null) {

            return provider.getCapability(PlayerStatsPointsCap.Data)
                    .orElse(new PlayerStatsPointsCap.DefaultImpl());

        }
        return null;
    }

    @Nonnull
    public static PlayerTalentsCap.IPlayerTalentsData talents(PlayerEntity provider) {

        return provider.getCapability(PlayerTalentsCap.Data)
                .orElse(new PlayerTalentsCap.DefaultImpl());
    }

    @Nonnull
    public static PlayerSpellCap.ISpellsCap spells(PlayerEntity provider) {

        return provider.getCapability(PlayerSpellCap.Data)
                .orElse(new PlayerSpellCap.DefaultImpl());
    }

    public static WeaponSpeedCap.IWeaponSpeedCap weaponSpeed(PlayerEntity provider) {

        if (provider != null) {

            return provider.getCapability(WeaponSpeedCap.Data)
                    .orElse(new WeaponSpeedCap.DefaultImpl());

        }
        return null;
    }

    public static QuestsCap.IQuestsData quests(PlayerEntity provider) {

        if (provider != null) {
            return provider.getCapability(QuestsCap.Data)
                    .orElse(new QuestsCap.DefaultImpl());

        }
        return null;
    }

    public static ProfessionsCap.IProfessionsData professions(PlayerEntity provider) {

        if (provider != null) {

            return provider.getCapability(ProfessionsCap.Data)
                    .orElse(new ProfessionsCap.DefaultImpl());

        }
        return null;
    }

    public static PlayerCapBackupCap.IPlayerCapBackupData playersCapBackup(World world) {

        if (world != null) {

            return world.getCapability(PlayerCapBackupCap.Data)
                    .orElse(new PlayerCapBackupCap.DefaultImpl());

        }
        return null;
    }

    public static UnitData Unit(ICapabilityProvider provider) {

        if (provider != null) {

            return provider.getCapability(EntityCap.Data)
                    .orElse(new EntityCap.DefaultImpl());

        }
        return null;
    }

    public static PlayerMapCap.IPlayerMapData playerMapData(PlayerEntity provider) {

        if (provider != null) {
            return provider.getCapability(PlayerMapCap.Data)
                    .orElse(new PlayerMapCap.DefaultImpl());
        }
        return null;
    }

}
