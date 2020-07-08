package com.robertx22.mine_and_slash.uncommon.datasaving;

import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.capability.world.AntiMobFarmCap;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nonnull;

public class Load {

    public static boolean hasUnit(ICapabilityProvider provider) { // tterag said this is correct
        if (provider != null) {
            return provider.getCapability(EntityCap.Data)
                .isPresent();
        }
        return false;
    }

    @Nonnull
    public static PlayerSpellCap.ISpellsCap spells(LivingEntity provider) {

        if (provider instanceof PlayerEntity) {
            return provider.getCapability(PlayerSpellCap.Data)
                .orElse(new PlayerSpellCap.DefaultImpl());
        } else {
            return new PlayerSpellCap.DefaultImpl();

        }
    }

    public static UnitData Unit(ICapabilityProvider provider) {

        if (provider != null) {

            return provider.getCapability(EntityCap.Data)
                .orElse(new EntityCap.DefaultImpl());

        }
        return null;
    }

    public static AntiMobFarmCap.IAntiMobFarmData antiMobFarm(World provider) {

        if (provider != null) {
            return provider.getCapability(AntiMobFarmCap.Data)
                .orElse(new AntiMobFarmCap.DefaultImpl());
        }
        return null;
    }

}
