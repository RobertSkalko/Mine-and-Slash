package com.robertx22.mine_and_slash.config.base_player_stat;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.types.defense.Armor;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalHit;
import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.SpellDamage;
import com.robertx22.mine_and_slash.database.stats.types.resources.*;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IApplyableStats;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;

import java.util.HashMap;

public class BasePlayerStatContainer implements ISlashRegistryInit, IApplyableStats {

    public static BasePlayerStatContainer INSTANCE = new BasePlayerStatContainer();

    public static BasePlayerStatContainer defaultStats() {

        BasePlayerStatContainer c = new BasePlayerStatContainer();

        //base ones

        c.base(Health.INSTANCE, 100);
        c.base(HealthRegen.INSTANCE, 2);
        c.base(MagicShieldRegen.INSTANCE, 2);
        c.base(PhysicalDamage.INSTANCE, 3);
        c.base(Armor.INSTANCE, 10);
        c.base(CriticalHit.INSTANCE, 1);
        c.base(CriticalDamage.INSTANCE, 0);
        c.base(SpellDamage.INSTANCE, 3);

        //scaling ones

        c.scale(Energy.INSTANCE, 125);
        c.scale(Mana.INSTANCE, 50);

        c.scale(EnergyRegen.INSTANCE, 7);
        c.scale(ManaRegen.INSTANCE, 3);

        c.scale(Health.INSTANCE, 5);
        c.scale(HealthRegen.INSTANCE, 1);
        c.scale(MagicShieldRegen.INSTANCE, 1);
        c.scale(PhysicalDamage.INSTANCE, 0.3D);
        c.scale(Armor.INSTANCE, 5);
        c.scale(SpellDamage.INSTANCE, 0.25D);

        return c;

    }

    public HashMap<String, Double> BASE_PLAYER_STATS = new HashMap<>();
    public HashMap<String, Double> PLAYER_STATS_THAT_SCALE_TO_LEVEL = new HashMap<>();

    public void scale(Stat stat, double val) {
        PLAYER_STATS_THAT_SCALE_TO_LEVEL.put(stat.GUID(), val);
    }

    public void base(Stat stat, double val) {
        BASE_PLAYER_STATS.put(stat.GUID(), val);
    }

    @Override
    public void registerAll() {
        INSTANCE = this;
    }

    @Override
    public void applyStats(EntityCap.UnitData data) {

        this.BASE_PLAYER_STATS.entrySet().forEach(x -> {
            data.getUnit().getCreateStat(SlashRegistry.Stats().get(x.getKey())).val += x.getValue();
        });

        this.PLAYER_STATS_THAT_SCALE_TO_LEVEL.entrySet().forEach(x -> {
            data.getUnit()
                    .getCreateStat(SlashRegistry.Stats().get(x.getKey()))
                    .addFlat(x.getValue().floatValue(), data.getLevel());
        });

    }

}
