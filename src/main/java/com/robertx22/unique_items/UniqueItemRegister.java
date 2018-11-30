package com.robertx22.unique_items;

import com.robertx22.unique_items.axes.AxeFire;
import com.robertx22.unique_items.axes.AxeWaterFire;
import com.robertx22.unique_items.boots.BootsFire;
import com.robertx22.unique_items.boots.BootsNature;
import com.robertx22.unique_items.boots.BootsThunder;
import com.robertx22.unique_items.boots.BootsWater;
import com.robertx22.unique_items.bracelets.BraceletThunder;
import com.robertx22.unique_items.bracelets.BraceletWater;
import com.robertx22.unique_items.chest.ChestFire;
import com.robertx22.unique_items.chest.ChestWater;
import com.robertx22.unique_items.hammers.HammerPhysical;
import com.robertx22.unique_items.hammers.HammerThunder;
import com.robertx22.unique_items.helmet.HelmetMana;
import com.robertx22.unique_items.helmet.HelmetWater;
import com.robertx22.unique_items.necklaces.NecklaceFire;
import com.robertx22.unique_items.necklaces.NecklaceNature;
import com.robertx22.unique_items.necklaces.NecklaceWater;
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

	// pants
	new PantsThunder();
	new PantsWater();

	// helmet
	new HelmetWater();
	new HelmetMana();

	// chest
	new ChestFire();
	new ChestWater();

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

	// rings
	new RingDodge();
	new RingWaterFire();
	new RingEnergy();
	new RingCrit();

	// bracelets
	new BraceletThunder();
	new BraceletWater();

	// necklaces
	new NecklaceNature();
	new NecklaceWater();
	new NecklaceFire();

	// staffs
	new StaffFire();
	new StaffWater();
	new StaffThunder();
	new StaffNature();
	new StaffLifesteal();

    }

}
