package com.robertx22.database;

import java.util.HashMap;

import com.robertx22.constants.Rarity;
import com.robertx22.database.rarities.general.*;

public class Rarities {

	public static HashMap<Integer, Rarity> General = new HashMap<Integer, Rarity>() {{	   
		put(0,new Common());		  
	    
	}};	
	

	
}
