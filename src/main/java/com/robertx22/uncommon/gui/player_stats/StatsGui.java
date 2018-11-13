package com.robertx22.uncommon.gui.player_stats;

import java.awt.Color;
import java.io.IOException;

import org.lwjgl.opengl.GL11;

import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.Trait;
import com.robertx22.stats.UsableStat;
import com.robertx22.uncommon.datasaving.UnitSaving;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class StatsGui extends GuiScreen {

	private GuiButton mButtonClose;

	Unit unit;
	Minecraft mc;

	public StatsGui() {

	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {

		this.drawDefaultBackground();
		displayStats();
		super.drawScreen(mouseX, mouseY, partialTicks);

	}

	@Override
	public void initGui() {

		mc = Minecraft.getMinecraft();

		if (mc != null && mc.player != null) {
			super.initGui();

			unit = UnitSaving.Load(mc.player);

			this.buttonList.add(mButtonClose = new GuiButton(0, this.width / 2 - 100,
					this.height - (this.height / 4) + 10, "Close"));
		}
	}

	private void displayStats() {

		if (unit != null) {

			int x = mc.currentScreen.width / 2;
			int y = 0;

			for (StatData data : unit.MyStats.values()) {
				String str = this.DisplayStat(data);

				if (data.GetStat() instanceof UsableStat) {
					UsableStat usable = (UsableStat) data.GetStat();

					str += " (" + usable.GetUsableValue(unit.level, (int) data.Value) + "%)";

				}

				if (data.GetStat() instanceof Trait) {
				} else {

					GL11.glColor4f(1, 1, 1, 1);
					mc.fontRenderer.drawString(str, x - mc.fontRenderer.getStringWidth(str) / 2, y,
							Color.BLACK.getRGB());

					y += mc.fontRenderer.FONT_HEIGHT;

				}
			}
		}
	}

	private String DisplayStat(StatData data) {

		return data.GetStat().Name() + " " + data.Value;

	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button == mButtonClose) {
			mc.player.closeScreen();
		}
	}

	@Override
	public boolean doesGuiPauseGame() {
		return true;
	}
}