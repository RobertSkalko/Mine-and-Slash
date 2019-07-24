package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import com.robertx22.mine_and_slash.database.stats.stat_types.resources.Health;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.LivingEntity;

public class HealthUtils {

    public static float DamageToMinecraftHealth(float dmg, LivingEntity entity) {

        try {
            UnitData data = Load.Unit(entity);
            Unit unit = data.getUnit();

            float maxhp = unit.getStat(new Health()).Value;
            float maxMChp = entity.getMaxHealth();

            return (float) (maxMChp / maxhp * dmg);
        } catch (Exception e) {
        }
        return 0;

    }

}
