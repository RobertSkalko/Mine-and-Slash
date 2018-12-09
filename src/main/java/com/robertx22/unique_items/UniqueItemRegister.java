package com.robertx22.unique_items;

import com.robertx22.unique_items.axes.AxeFire;
import com.robertx22.unique_items.axes.AxeThunder;
import com.robertx22.unique_items.axes.AxeWaterFire;
import com.robertx22.unique_items.boots.BootsFire;
import com.robertx22.unique_items.boots.BootsNature;
import com.robertx22.unique_items.boots.BootsThunder;
import com.robertx22.unique_items.boots.BootsWater;
import com.robertx22.unique_items.bracelets.BraceletFire;
import com.robertx22.unique_items.bracelets.BraceletNature;
import com.robertx22.unique_items.bracelets.BraceletThunder;
import com.robertx22.unique_items.bracelets.BraceletThunderNature;
import com.robertx22.unique_items.bracelets.BraceletWater;
import com.robertx22.unique_items.charms.CharmFire;
import com.robertx22.unique_items.charms.CharmNature;
import com.robertx22.unique_items.charms.CharmThunder;
import com.robertx22.unique_items.charms.CharmWater;
import com.robertx22.unique_items.chest.ChestDodge;
import com.robertx22.unique_items.chest.ChestFire;
import com.robertx22.unique_items.chest.ChestMana;
import com.robertx22.unique_items.chest.ChestNature;
import com.robertx22.unique_items.chest.ChestThunder;
import com.robertx22.unique_items.chest.ChestWater;
import com.robertx22.unique_items.hammers.HammerPhysical;
import com.robertx22.unique_items.hammers.HammerThunder;
import com.robertx22.unique_items.helmet.HelmetFire;
import com.robertx22.unique_items.helmet.HelmetMana;
import com.robertx22.unique_items.helmet.HelmetNature;
import com.robertx22.unique_items.helmet.HelmetThunder;
import com.robertx22.unique_items.helmet.HelmetWater;
import com.robertx22.unique_items.necklaces.NecklaceEnergy;
import com.robertx22.unique_items.necklaces.NecklaceFire;
import com.robertx22.unique_items.necklaces.NecklaceNature;
import com.robertx22.unique_items.necklaces.NecklaceThunder;
import com.robertx22.unique_items.necklaces.NecklaceWater;
import com.robertx22.unique_items.pants.PantsFire;
import com.robertx22.unique_items.pants.PantsNature;
import com.robertx22.unique_items.pants.PantsThunder;
import com.robertx22.unique_items.pants.PantsWater;
import com.robertx22.unique_items.rings.RingCrit;
import com.robertx22.unique_items.rings.RingDodge;
import com.robertx22.unique_items.rings.RingEnergy;
import com.robertx22.unique_items.rings.RingWaterFire;
import com.robertx22.unique_items.staffs.StaffFire;
import com.robertx22.unique_items.staffs.StaffLifesteal;
import com.robertx22.unique_items.staffs.StaffNature;
import com.robertx22.unique_items.staffs.StaffThunder;
import com.robertx22.unique_items.staffs.StaffWater;
import com.robertx22.unique_items.swords.SwordNature;
import com.robertx22.unique_items.swords.SwordPhysical;
import com.robertx22.unique_items.swords.SwordWater;

public class UniqueItemRegister {

    public static void registerAll() {

	// charms

	new CharmThunder();
	new CharmWater();
	new CharmFire();
	new CharmNature();

	// pants
	new PantsThunder();
	new PantsWater();
	new PantsNature();
	new PantsFire();

	// helmet
	new HelmetWater();
	new HelmetMana();
	new HelmetFire();
	new HelmetThunder();
	new HelmetNature();

	// chest
	new ChestFire();
	new ChestWater();
	new ChestDodge();
	new ChestNature();
	new ChestThunder();
	new ChestMana();

	// boots
	new BootsNature();
	new BootsWater();
	new BootsFire();
	new BootsThunder();

	// hammers
	new HammerThunder();
	new HammerPhysical();

	// swords
	new SwordNature();
	new SwordWater();
	new SwordPhysical();

	// axes
	new AxeWaterFire();
	new AxeFire();
	new AxeThunder();

	// rings
	new RingDodge();
	new RingWaterFire();
	new RingEnergy();
	new RingCrit();

	// bracelets
	new BraceletThunder();
	new BraceletWater();
	new BraceletThunderNature();
	new BraceletFire();
	new BraceletNature();

	// necklaces
	new NecklaceNature();
	new NecklaceWater();
	new NecklaceFire();
	new NecklaceThunder();
	new NecklaceEnergy();

	// staffs
	new StaffFire();
	new StaffWater();
	new StaffThunder();
	new StaffNature();
	new StaffLifesteal();

	// localization helper

	/*
	 * 
	 * String s = ""; for (Item item : IUnique.ITEMS.values()) { IUnique uniq =
	 * (IUnique) item;
	 * 
	 * s += "\n" + "mmorpg.unique.name." + uniq.GUID() + "=" + uniq.locString(); }
	 * System.out.println(s);
	 * 
	 * String ss = ""; for (Item item : IUnique.ITEMS.values()) { IUnique uniq =
	 * (IUnique) item;
	 * 
	 * ss += "\n" + "mmorpg.unique.tooltip." + uniq.GUID() + "=" +
	 * uniq.description(); } System.out.println(ss);
	 * 
	 */
    }

}
