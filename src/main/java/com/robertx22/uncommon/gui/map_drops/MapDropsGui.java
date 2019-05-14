package com.robertx22.uncommon.gui.map_drops;

import java.util.ArrayList;
import java.util.List;

import com.libraries.rabbit.gui.component.list.ScrollableDisplayList;
import com.libraries.rabbit.gui.component.list.entries.ListEntry;
import com.libraries.rabbit.gui.component.list.entries.StringEntry;
import com.libraries.rabbit.gui.show.Show;
import com.robertx22.items.unique_items.IUnique;
import com.robertx22.saveclasses.MapItemData;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextFormatting;

public class MapDropsGui extends Show {

    MapItemData map;
    Minecraft mc;
    IWorldData world;

    public MapDropsGui() {

    }

    @Override
    public void setup() {
	super.setup();

	mc = Minecraft.getMinecraft();

	if (mc != null && mc.player != null) {

	    IWorldData data = Load.World(mc.player.world);

	    if (data != null) {
		map = data.getMap();
		world = data;
	    }

	}

    }

    ScrollableDisplayList scroll = null;

    boolean setup = false;

    @Override
    public void onDraw(int mouseX, int mouseY, float partialTicks) {

	super.onDraw(mouseX, mouseY, partialTicks);

	scroll = displayStats();

	if (scroll != null && setup == false) {
	    scroll.setup();
	    setup = true;
	    scroll.setVisibleBackground(true);

	    this.registerComponent(scroll);
	}

    }

    private ScrollableDisplayList displayStats() {

	if (map != null && mc.currentScreen != null && world != null) {

	    List<ListEntry> list = new ArrayList<ListEntry>();

	    if (world.isMapWorld()) {

		List<IUnique> uniques = IUnique.getAllPossibleUniqueDrops(world.getTier(), IUnique.ITEMS.values());

		boolean has = false;
		List<String> names = new ArrayList<String>();

		for (int i = 0; i < 50; i++) {
		    names.clear();
		    has = false;

		    for (IUnique uniq : uniques) {
			if (uniq.Tier() == i) {
			    has = true;
			    names.add(uniq.locName());
			}
		    }
		    if (has) {
			StringToListEntry(TextFormatting.GREEN + CLOC.word("tier") + " " + i + ":", list);
		    }

		    for (String name : names) {
			StringToListEntry(TextFormatting.YELLOW + name, list);
		    }

		}

	    } else {
		StringToListEntry(CLOC.word("not_mapworld"), list);
	    }
	    int width = this.width / 2;
	    int height = this.height - this.height / 5;

	    ScrollableDisplayList scroll = new ScrollableDisplayList(this.width / 4, this.height / 10, width, height,
		    15, list);

	    return scroll;
	}
	return null;

    }

    private void StringToListEntry(String str, List<ListEntry> list) {
	if (str != null) {
	    StringEntry entry = new StringEntry(str);

	    list.add(entry);

	}
    }

}