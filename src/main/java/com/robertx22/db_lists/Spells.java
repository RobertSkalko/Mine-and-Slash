package com.robertx22.db_lists;

import java.util.HashMap;

import com.robertx22.spells.aoe_projectile.AcidExplosion.SpellAcidExplosion;
import com.robertx22.spells.aoe_projectile.FlameExplosion.SpellFlameExplosion;
import com.robertx22.spells.aoe_projectile.FrostExplosion.SpellFrostExplosion;
import com.robertx22.spells.aoe_projectile.LightningExplosion.SpellLightningExplosion;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.spells.projectile.acidbolt.SpellAcidBolt;
import com.robertx22.spells.projectile.firebolt.SpellFireBolt;
import com.robertx22.spells.projectile.frostbolt.SpellFrostBolt;
import com.robertx22.spells.projectile.thunderbolt.SpellThunderBolt;
import com.robertx22.spells.self.SpellInstantHeal;

public class Spells {
    public static HashMap<String, BaseSpell> All = new HashMap<String, BaseSpell>() {
	{
	    {
		put(new SpellFrostBolt().GUID(), new SpellFrostBolt());
		put(new SpellFireBolt().GUID(), new SpellFireBolt());
		put(new SpellAcidBolt().GUID(), new SpellAcidBolt());
		put(new SpellThunderBolt().GUID(), new SpellThunderBolt());

		put(new SpellFrostExplosion().GUID(), new SpellFrostExplosion());
		put(new SpellFlameExplosion().GUID(), new SpellFlameExplosion());
		put(new SpellLightningExplosion().GUID(), new SpellLightningExplosion());
		put(new SpellAcidExplosion().GUID(), new SpellAcidExplosion());

		put(new SpellInstantHeal().GUID(), new SpellInstantHeal());

	    }
	}
    };

}
