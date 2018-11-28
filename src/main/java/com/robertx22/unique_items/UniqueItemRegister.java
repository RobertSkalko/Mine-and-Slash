package com.robertx22.unique_items;

import com.robertx22.unique_items.bracelets.BraceletThunder;
import com.robertx22.unique_items.necklaces.NecklaceNature;
import com.robertx22.unique_items.rings.RingFrostFire;
import com.robertx22.unique_items.rings.RingOfDodge;
import com.robertx22.unique_items.staffs.StaffFire;
import com.robertx22.unique_items.staffs.StaffNature;
import com.robertx22.unique_items.staffs.StaffThunder;
import com.robertx22.unique_items.staffs.StaffWater;

public class UniqueItemRegister {

	public static void registerAll() {

		// rings
		new RingOfDodge();
		new RingFrostFire();

		// bracelets
		new BraceletThunder();

		// necklacews
		new NecklaceNature();

		// staffs
		new StaffFire();
		new StaffWater();
		new StaffThunder();
		new StaffNature();

	}

}
