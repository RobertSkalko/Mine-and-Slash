package com.robertx22.db_lists;

import java.util.HashMap;

import com.robertx22.database.status.effects.MobArmorSE;
import com.robertx22.database.status.effects.MobElementResistSE;
import com.robertx22.database.status.effects.MobFireDMGSE;
import com.robertx22.database.status.effects.MobHealthSE;
import com.robertx22.database.status.effects.MobLifestealSE;
import com.robertx22.database.status.effects.MobNatureDMGSE;
import com.robertx22.database.status.effects.MobThunderDMGSE;
import com.robertx22.database.status.effects.MobWaterDMGSE;
import com.robertx22.database.status.effects.bases.BaseStatusEffect;

public class StatusEffects {
	public static HashMap<String, BaseStatusEffect> All = new HashMap<String, BaseStatusEffect>() {
		{
			{
				put(new MobHealthSE().GUID(), new MobHealthSE());
				put(new MobElementResistSE().GUID(), new MobElementResistSE());
				put(new MobArmorSE().GUID(), new MobArmorSE());

				put(new MobNatureDMGSE().GUID(), new MobNatureDMGSE());
				put(new MobWaterDMGSE().GUID(), new MobWaterDMGSE());
				put(new MobThunderDMGSE().GUID(), new MobThunderDMGSE());
				put(new MobFireDMGSE().GUID(), new MobFireDMGSE());
				put(new MobLifestealSE().GUID(), new MobLifestealSE());

			}
		}
	};

}
