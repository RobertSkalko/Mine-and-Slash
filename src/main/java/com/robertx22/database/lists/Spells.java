package com.robertx22.database.lists;

import java.util.HashMap;

import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.spells.projectile.acidbolt.SpellAcidBolt;
import com.robertx22.spells.projectile.firebolt.SpellFireBolt;
import com.robertx22.spells.projectile.frostbolt.SpellFrostBolt;
import com.robertx22.spells.projectile.thunderbolt.SpellThunderBolt;

public class Spells {
	public static HashMap<String, BaseSpell> All = new HashMap<String, BaseSpell>() {
		{
			{
				put(new SpellFrostBolt().GUID(), new SpellFrostBolt());
				put(new SpellFireBolt().GUID(), new SpellFireBolt());
				put(new SpellAcidBolt().GUID(), new SpellAcidBolt());
				put(new SpellThunderBolt().GUID(), new SpellThunderBolt());
			}
		}
	};

}
