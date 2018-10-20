package com.robertx22.database.lists;

import java.util.HashMap;

import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.spells.projectile.firebolt.SpellFireBolt;
import com.robertx22.spells.projectile.frostbolt.SpellFrostBolt;

public class Spells {
	public static HashMap<String, BaseSpell> All = new HashMap<String, BaseSpell>() {
		{
			{
				put(new SpellFrostBolt().GUID(), new SpellFrostBolt());
				put(new SpellFireBolt().GUID(), new SpellFireBolt());
			}
		}
	};

}
