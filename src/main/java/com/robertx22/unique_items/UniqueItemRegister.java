package com.robertx22.unique_items;

import com.robertx22.unique_items.bracelets.BraceletThunder;
import com.robertx22.unique_items.bracelets.BraceletWater;
import com.robertx22.unique_items.necklaces.NecklaceNature;
import com.robertx22.unique_items.rings.RingWaterFire;
import com.robertx22.unique_items.rings.RingDodge;
import com.robertx22.unique_items.staffs.StaffFire;
import com.robertx22.unique_items.staffs.StaffLifesteal;
import com.robertx22.unique_items.staffs.StaffNature;
import com.robertx22.unique_items.staffs.StaffThunder;
import com.robertx22.unique_items.staffs.StaffWater;

public class UniqueItemRegister {

    public static void registerAll() {

	// rings
	new RingDodge();
	new RingWaterFire();

	// bracelets
	new BraceletThunder();
	new BraceletWater();

	// necklaces
	new NecklaceNature();

	// staffs
	new StaffFire();
	new StaffWater();
	new StaffThunder();
	new StaffNature();
	new StaffLifesteal();

    }

}
