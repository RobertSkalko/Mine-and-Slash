package com.robertx22.stats;

import com.robertx22.saveclasses.Unit;

public interface IAffectsOtherStats {

	/**
	 * This is done after stats are calculated, as a last stat changing method
	 */
	public abstract void TryAffectOtherStats(Unit unit);
}
