package com.robertx22.mine_and_slash.database.chaos_stats;

import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.stats.Trait;
import com.robertx22.mine_and_slash.database.stats.types.traits.bad_ones.Crippled;
import com.robertx22.mine_and_slash.db_lists.bases.IhasRequirements;
import com.robertx22.mine_and_slash.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;

public class ChaosStat implements ISlashRegistryEntry<ChaosStat>, IhasRequirements {

    public static ChaosStat empty() {
        return new ChaosStat(Requirements.EMPTY, new Crippled());
    }

    Requirements requirements;
    Trait chaosTrait;
    String guid;

    public ChaosStat(Requirements requirements, Trait chaosTrait) {
        this.requirements = requirements;
        this.chaosTrait = chaosTrait;
        this.guid = this.chaosTrait.GUID();
    }

    public Trait getChaosTrait() {
        return chaosTrait;
    }

    @Override
    public int Weight() {
        return chaosTrait.Weight();
    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.CHAOS_STAT;
    }

    @Override
    public String GUID() {
        return guid;
    }

    @Override
    public Requirements requirements() {
        return requirements;
    }
}
