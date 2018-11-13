package com.robertx22.uncommon.gui.player_stats;

import java.util.ArrayList;
import java.util.List;

import com.rabbit.gui.component.list.ScrollableDisplayList;
import com.rabbit.gui.component.list.entries.ListEntry;
import com.rabbit.gui.component.list.entries.StringEntry;
import com.rabbit.gui.show.Show;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.Trait;
import com.robertx22.stats.UsableStat;
import com.robertx22.uncommon.datasaving.UnitSaving;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class StatsGui extends Show {

	private GuiButton mButtonClose;

	Unit unit;
	Minecraft mc;

	public StatsGui() {

	}

	@Override
	public void setup() {
		super.setup();

		mc = Minecraft.getMinecraft();

		if (mc != null && mc.player != null) {

			unit = UnitSaving.Load(mc.player);

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
			this.registerComponent(scroll);
		}

	}

	private ScrollableDisplayList displayStats() {

		if (unit != null && mc.currentScreen != null) {

			List<ListEntry> list = new ArrayList();

			for (StatData data : unit.MyStats.values()) {
				String str = this.DisplayStat(data);

				if (data.GetStat() instanceof UsableStat) {
					UsableStat usable = (UsableStat) data.GetStat();

					str += " (" + String.format("%.1f", usable.GetUsableValue(unit.level, (int) data.Value)) + "%)";

				}

				if (data.GetStat() instanceof Trait) {
				} else {

					StringEntry entry = new StringEntry(str);

					list.add(entry);

				}
			}

			int width = this.width / 2;
			int height = this.height - this.height / 5;

			ScrollableDisplayList scroll = new ScrollableDisplayList(this.width / 4, this.height / 10, width, height,
					15, list);

			return scroll;
		}
		return null;

	}

	private String DisplayStat(StatData data) {

		return data.GetStat().Name() + " " + String.format("%.1f", data.Value);

	}

}