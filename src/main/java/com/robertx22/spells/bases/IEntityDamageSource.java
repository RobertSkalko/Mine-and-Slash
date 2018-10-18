package com.robertx22.spells.bases;

import com.robertx22.saveclasses.DamageData;

public interface IEntityDamageSource {

	public abstract void SetData(DamageData data);

	public abstract DamageData GetData();

}
