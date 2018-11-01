package com.robertx22.database.lists;

import java.util.HashMap;

import com.robertx22.database.status.effects.BaseStatusEffect;
import com.robertx22.database.status.effects.HealthSE;

public class StatusEffects {
	public static HashMap<String, BaseStatusEffect> All = new HashMap<String, BaseStatusEffect>() {
		{
			{
				put(new HealthSE().GUID(), new HealthSE());

			}
		}
	};

}
