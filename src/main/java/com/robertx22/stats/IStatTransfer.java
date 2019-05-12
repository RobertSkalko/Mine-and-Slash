package com.robertx22.stats;

import java.util.List;

import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;

public interface IStatTransfer {

    public abstract void transferStats(Unit unitcopy, Unit unit, StatData data);

    public abstract List<TransferMethod> Transfer();

}
