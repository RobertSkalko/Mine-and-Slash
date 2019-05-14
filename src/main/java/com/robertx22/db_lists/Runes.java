package com.robertx22.db_lists;

import java.util.HashMap;

import com.robertx22.items.runes.AnoItem;
import com.robertx22.items.runes.BerItem;
import com.robertx22.items.runes.CenItem;
import com.robertx22.items.runes.DosItem;
import com.robertx22.items.runes.GohItem;
import com.robertx22.items.runes.ItaItem;
import com.robertx22.items.runes.MosItem;
import com.robertx22.items.runes.RahItem;
import com.robertx22.items.runes.VohItem;
import com.robertx22.items.runes.XahItem;
import com.robertx22.items.runes.base.BaseRuneItem;

public class Runes {
    public static HashMap<String, BaseRuneItem> All = new HashMap<String, BaseRuneItem>() {
	{
	    {
		put(new CenItem(0).name(), new CenItem(0));
		put(new MosItem(0).name(), new MosItem(0));
		put(new ItaItem(0).name(), new ItaItem(0));
		put(new BerItem(0).name(), new BerItem(0));
		put(new DosItem(0).name(), new DosItem(0));
		put(new GohItem(0).name(), new GohItem(0));
		put(new RahItem(0).name(), new RahItem(0));
		put(new VohItem(0).name(), new VohItem(0));
		put(new XahItem(0).name(), new XahItem(0));
		put(new AnoItem(0).name(), new AnoItem(0));

	    }
	}
    };

}
