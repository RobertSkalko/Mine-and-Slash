package com.robertx22.mine_and_slash.database.requirements.bases;

import com.robertx22.mine_and_slash.onevent.data_gen.ISerializablePart;

public abstract class BaseRequirement<T> implements ISerializablePart<T> {

    public abstract boolean meetsRequierment(GearRequestedFor requested);

}
