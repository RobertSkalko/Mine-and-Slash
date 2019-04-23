package com.robertx22.db_lists;

import java.util.HashMap;

import com.robertx22.spells.aoe_bomb_proj.SpellAcidBomb;
import com.robertx22.spells.aoe_bomb_proj.SpellFireBomb;
import com.robertx22.spells.aoe_bomb_proj.SpellIceBomb;
import com.robertx22.spells.aoe_bomb_proj.SpellThunderBomb;
import com.robertx22.spells.aoe_projectile.SpellAcidExplosion;
import com.robertx22.spells.aoe_projectile.SpellFlameExplosion;
import com.robertx22.spells.aoe_projectile.SpellFrostExplosion;
import com.robertx22.spells.aoe_projectile.SpellLightningExplosion;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.spells.nova.SpellFireNova;
import com.robertx22.spells.nova.SpellFrostNova;
import com.robertx22.spells.nova.SpellPoisonNova;
import com.robertx22.spells.nova.SpellThunderNova;
import com.robertx22.spells.projectile.SpellAcidBolt;
import com.robertx22.spells.projectile.SpellFireBolt;
import com.robertx22.spells.projectile.SpellFrostBolt;
import com.robertx22.spells.projectile.SpellThunderBolt;
import com.robertx22.spells.self.SpellInstantHeal;
import com.robertx22.spells.self.SpellSelfRegen;

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
		put(new SpellSelfRegen().GUID(), new SpellSelfRegen());

		put(new SpellFireBomb().GUID(), new SpellFireBomb());
		put(new SpellThunderBomb().GUID(), new SpellThunderBomb());
		put(new SpellAcidBomb().GUID(), new SpellAcidBomb());
		put(new SpellIceBomb().GUID(), new SpellIceBomb());

		put(new SpellFrostNova().GUID(), new SpellFrostNova());
		put(new SpellFireNova().GUID(), new SpellFireNova());
		put(new SpellThunderNova().GUID(), new SpellThunderNova());
		put(new SpellPoisonNova().GUID(), new SpellPoisonNova());

	    }
	}
    };

}
