package com.robertx22.mine_and_slash.database.bosses.base;

import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.capability.entity.BossCap;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.LivingEntity;

@Storable
public class BossData {

    @Store
    public String boss;

    @Store
    public boolean hp_10 = false;
    @Store
    public boolean hp_25 = false;
    @Store
    public boolean hp_50 = false;
    @Store
    public boolean hp_75 = false;

    public enum HealthTreshhold {
        T_10,
        T_25,
        T_50,
        T_75;
    }

    public Boss getBoss() {
        return SlashRegistry.Bosses().get(boss);
    }

    public void onHealthChanged(LivingEntity en, BossCap.IBossData bossData) {

        Boss boss = getBoss();

        int perc = getHealthPercent(en);

        if (!hp_75 && perc < 75) {
            hp_75 = true;
            boss.onHealthTreshholdTriggered(en, HealthTreshhold.T_75);
            return;
        }
        if (!hp_50 && perc < 50) {
            hp_50 = true;
            boss.onHealthTreshholdTriggered(en, HealthTreshhold.T_50);
            return;
        }
        if (!hp_25 && perc < 25) {
            hp_25 = true;
            boss.onHealthTreshholdTriggered(en, HealthTreshhold.T_25);
            return;
        }
        if (!hp_10 && perc < 10) {
            hp_10 = true;
            boss.onHealthTreshholdTriggered(en, HealthTreshhold.T_10);
            return;
        }

    }

    private int getHealthPercent(LivingEntity en) {
        return (int) ((en.getHealth() / en.getMaxHealth()) * 100);
    }

}
