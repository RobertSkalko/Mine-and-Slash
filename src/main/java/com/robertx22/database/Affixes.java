package com.robertx22.database;

import java.util.HashMap;

import com.robertx22.classes.BaseAffix;
import com.robertx22.database.prefixes.*;

public class Affixes {

	public static HashMap<String, BaseAffix> All = new HashMap<String, BaseAffix>() {
		{
		 put("Flaming", new Flaming() );

		}
	};

}
