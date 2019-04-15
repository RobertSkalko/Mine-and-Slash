package com.robertx22.db_lists;

import java.util.HashMap;

import com.robertx22.customitems.runes.CenItem;
import com.robertx22.customitems.runes.MosItem;
import com.robertx22.customitems.runes.ZohItem;
import com.robertx22.customitems.runes.base.BaseRuneItem;

public class Runes {
    public static HashMap<String, BaseRuneItem> All = new HashMap<String, BaseRuneItem>() {
	{
	    {
		put(new CenItem(0).name(), new CenItem(0));
		put(new MosItem(0).name(), new MosItem(0));
		put(new ZohItem(0).name(), new ZohItem(0));

	    }
	}
    };
}
