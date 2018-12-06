package com.robertx22.database.stat_types.traits.bases;

import com.robertx22.stats.Trait;

public abstract class ConditionalTrait extends Trait {

    public abstract String descPrefix();

    @Override
    public String Description() {
	return this.descPrefix();
    }

}
