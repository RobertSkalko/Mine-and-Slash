package com.robertx22.db_lists;

import java.util.HashMap;

import com.robertx22.database.map_affixes.BaseMapAffix;
import com.robertx22.database.map_affixes.beneficial.BonusHealthAffix;
import com.robertx22.database.map_affixes.beneficial.BonusLifestealAffix;
import com.robertx22.database.map_affixes.beneficial.ele_dmg.BonusFireDamageAffix;
import com.robertx22.database.map_affixes.beneficial.ele_dmg.BonusNatureDamageAffix;
import com.robertx22.database.map_affixes.beneficial.ele_dmg.BonusThunderDamageAffix;
import com.robertx22.database.map_affixes.beneficial.ele_dmg.BonusWaterDamageAffix;
import com.robertx22.database.map_affixes.beneficial.ele_res.BonusFireResistAffix;
import com.robertx22.database.map_affixes.beneficial.ele_res.BonusNatureResistAffix;
import com.robertx22.database.map_affixes.beneficial.ele_res.BonusThunderResistAffix;
import com.robertx22.database.map_affixes.beneficial.ele_res.BonusWaterResistAffix;
import com.robertx22.database.map_affixes.detrimental.LessEnergyRegenAffix;
import com.robertx22.database.map_affixes.detrimental.LessHealthAffix;
import com.robertx22.database.map_affixes.detrimental.LessHealthRegenAffix;
import com.robertx22.database.map_affixes.detrimental.LessLifeOnHitAffix;
import com.robertx22.database.map_affixes.detrimental.LessLifestealAffix;
import com.robertx22.database.map_affixes.detrimental.LessManaOnHitAffix;
import com.robertx22.database.map_affixes.detrimental.LessManaRegenAffix;

public class MapAffixes {
	public static HashMap<String, BaseMapAffix> All = new HashMap<String, BaseMapAffix>() {
		{
			{

				put(new BonusHealthAffix().GUID(), new BonusHealthAffix());
				put(new BonusLifestealAffix().GUID(), new BonusLifestealAffix());

				put(new BonusFireDamageAffix().GUID(), new BonusFireDamageAffix());
				put(new BonusNatureDamageAffix().GUID(), new BonusNatureDamageAffix());
				put(new BonusThunderDamageAffix().GUID(), new BonusThunderDamageAffix());
				put(new BonusWaterDamageAffix().GUID(), new BonusWaterDamageAffix());

				put(new BonusFireResistAffix().GUID(), new BonusFireResistAffix());
				put(new BonusNatureResistAffix().GUID(), new BonusNatureResistAffix());
				put(new BonusThunderResistAffix().GUID(), new BonusThunderResistAffix());
				put(new BonusWaterResistAffix().GUID(), new BonusWaterResistAffix());

				put(new LessEnergyRegenAffix().GUID(), new LessEnergyRegenAffix());
				put(new LessManaRegenAffix().GUID(), new LessManaRegenAffix());
				put(new LessHealthRegenAffix().GUID(), new LessHealthRegenAffix());
				put(new LessLifeOnHitAffix().GUID(), new LessLifeOnHitAffix());
				put(new LessLifestealAffix().GUID(), new LessLifestealAffix());
				put(new LessHealthAffix().GUID(), new LessHealthAffix());
				put(new LessManaOnHitAffix().GUID(), new LessManaOnHitAffix());

			}
		}
	};
}
