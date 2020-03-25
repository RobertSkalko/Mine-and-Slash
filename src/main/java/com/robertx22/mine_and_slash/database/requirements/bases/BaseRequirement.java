package com.robertx22.mine_and_slash.database.requirements.bases;

import com.robertx22.mine_and_slash.onevent.data_gen.ISerializablePart;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;

public abstract class BaseRequirement<T> implements ISerializablePart<T>, ITooltipList {

    public abstract boolean meetsRequierment(GearRequestedFor requested);

}
