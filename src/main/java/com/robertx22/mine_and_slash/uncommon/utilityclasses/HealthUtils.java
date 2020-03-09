package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import com.robertx22.mine_and_slash.database.stats.types.resources.Health;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import net.minecraft.entity.LivingEntity;

public class HealthUtils {

    public static float setCurrentHealth(LivingEntity entity,
                                         UnitData data) {

        float maxhp = data.getUnit()
            .getMaxEffectiveHealth();
        float maxMChp = entity.getMaxHealth();

        float current = data.getUnit()
            .getCurrentEffectiveHealth(entity, data);

        return (float) (maxMChp / maxhp * current);

    }

    public static float DamageToMinecraftHealth(float dmg, LivingEntity entity,
                                                UnitData data) {

        try {
            Unit unit = data.getUnit();

            float maxhp = unit.getCreateStat(Health.GUID).val;
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

            float maxHp = unit.healthData().val;
            float maxVanillaHp = entity.getMaxHealth();

            return (float) (dmg / maxVanillaHp * maxHp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;

    }

}
