package com.robertx22.mine_and_slash.database.bosses.impl;

import com.robertx22.mine_and_slash.database.bosses.base.Boss;
import com.robertx22.mine_and_slash.database.bosses.base.BossData;
import com.robertx22.mine_and_slash.database.bosses.base.TickAction;
import com.robertx22.mine_and_slash.database.spells.spell_classes.druid.RegenerateSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.druid.ThornArmorSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.druid.ThornBushSpell;
import com.robertx22.mine_and_slash.database.stats.types.resources.HealthRegen;
import com.robertx22.mine_and_slash.db_lists.initializers.Synergies;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.text.ITextComponent;

public class DruidBoss extends Boss {

    private DruidBoss() {
        this.synergies.add(Synergies.REGEN_AOE);
        this.synergies.add(Synergies.REGEN_THORNS);

        this.tickActions.add(new TickAction(200, x -> {
            RegenerateSpell.getInstance()
                .cast(x, 0);
            return x;
        }));
        this.tickActions.add(new TickAction(400, x -> {
            ThornBushSpell.getInstance()
                .cast(x, 0);
            return x;
        }));
    }

    public static DruidBoss getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public IParticleData getParticle() {
        return ParticleTypes.WITCH;
    }

    @Override
    public ITextComponent getName() {
        return Words.Druid.locName();
    }

    @Override
    public void onHealthTreshholdTriggered(LivingEntity en, BossData.HealthTreshhold treshhold) {

        if (treshhold == BossData.HealthTreshhold.T_75) {
            ThornArmorSpell.getInstance()
                .cast(en, 0);
        }

        if (treshhold == BossData.HealthTreshhold.T_25) {
            ThornArmorSpell.getInstance()
                .cast(en, 0);
        }
    }

    @Override
    public void applyStats(EntityCap.UnitData data) {
        super.applyStats(data);
        data.getUnit()
            .getCreateStat(HealthRegen.getInstance())
            .addMulti(-100);
    }

    @Override
    public String GUID() {
        return "druid";
    }

    private static class SingletonHolder {
        private static final DruidBoss INSTANCE = new DruidBoss();
    }
}
