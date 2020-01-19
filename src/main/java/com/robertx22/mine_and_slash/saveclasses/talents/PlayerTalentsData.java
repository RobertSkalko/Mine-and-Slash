package com.robertx22.mine_and_slash.saveclasses.talents;

import com.robertx22.mine_and_slash.database.talent_tree.Perk;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryContainer;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IApplyableStats;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import info.loenwind.autosave.annotations.Storable;

@Storable
public class PlayerTalentsData extends BasePerksData<Perk> implements IApplyableStats {

    @Override
    public SlashRegistryContainer getRegistryContainer() {
        return SlashRegistry.Perks();
    }

    @Override
    public void applyStats(EntityCap.UnitData data) {
        for (Perk talent : getAllCurrentTalents()) {
            if (talent.effect != null) {

                if (talent.effect instanceof IApplyableStats) {
                    IApplyableStats apply = (IApplyableStats) talent.effect;
                    apply.applyStats(data);
                }

            }
        }
    }
}
