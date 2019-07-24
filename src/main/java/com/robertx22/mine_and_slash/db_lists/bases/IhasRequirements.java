package com.robertx22.mine_and_slash.db_lists.bases;

import com.robertx22.mine_and_slash.database.requirements.GearRequestedFor;
import com.robertx22.mine_and_slash.database.requirements.Requirements;

public interface IhasRequirements {

    public abstract Requirements requirements();

    public default boolean meetsRequirements(GearRequestedFor requested) {

        return requirements().satisfiesAllRequirements(requested);

    }

}
