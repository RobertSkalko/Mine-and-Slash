package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import com.robertx22.mine_and_slash.database.stats.stat_types.resources.Health;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import net.minecraft.entity.LivingEntity;

public class HealthUtils {

    public static float DamageToMinecraftHealth(float dmg, LivingEntity entity,
                                                UnitData data) {

        try {
            Unit unit = data.getUnit();

            float maxhp = unit.getStat(Health.GUID).Value;
            float maxMChp = entity.getMaxHealth();

            return (float) (maxMChp / maxhp * dmg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public static float vanillaHealthToActualHealth(float dmg, LivingEntity entity,
                                                    UnitData data) {

        try {
            Unit unit = data.getUnit();

            float maxHp = unit.healthData().Value;
            float maxVanillaHp = entity.getMaxHealth();

            return (float) (dmg / maxVanillaHp * maxHp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;

    }

}
