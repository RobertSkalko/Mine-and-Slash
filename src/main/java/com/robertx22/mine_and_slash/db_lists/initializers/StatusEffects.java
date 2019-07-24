package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.status_effects.*;
import com.robertx22.mine_and_slash.database.status_effects.bases.BaseStatusEffect;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;

import java.util.ArrayList;
import java.util.List;

public class StatusEffects implements ISlashRegistryInit {

    @Override
    public void registerAll() {

        List<BaseStatusEffect> All = new ArrayList<BaseStatusEffect>() {
            {
                {
                    add(new MobHealthSE());
                    add(new MobElementResistSE());
                    add(new MobArmorSE());

                    add(new MobNatureDMGSE());
                    add(new MobWaterDMGSE());
                    add(new MobThunderDMGSE());
                    add(new MobFireDMGSE());
                    add(new MobLifestealSE());

                }
            }
        };

        All.forEach(x -> SlashRegistry.getRegistry(SlashRegistryType.STATUS_EFFECT)
                .register(x));

    }
}
