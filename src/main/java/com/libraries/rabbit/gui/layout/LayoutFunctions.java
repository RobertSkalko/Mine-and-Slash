package com.libraries.rabbit.gui.layout;

import com.libraries.rabbit.gui.base.Stage;
import com.libraries.rabbit.gui.show.IShow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.objecthunter.exp4j.function.Function;

@SideOnly(Side.CLIENT)
public class LayoutFunctions {

	public static final Function width = new Function("width", 0) {
		@Override
		public double apply(double... args) {
			IShow currentShow = LayoutFunctions.getCurrentlyOpenedShow();
			return currentShow != null ? currentShow.getWidth() : 0;
		}
	};

	public static final Function height = new Function("height", 0) {
		@Override
		public double apply(double... args) {
			IShow currentShow = LayoutFunctions.getCurrentlyOpenedShow();
			return currentShow != null ? currentShow.getHeight() : 0;
		}
	};

	public static IShow getCurrentlyOpenedShow() {
		GuiScreen currentScreen = Minecraft.getMinecraft().currentScreen;
		if ((currentScreen != null) && (currentScreen instanceof Stage)) {
			return ((Stage) currentScreen).getShow();
		}
		return null;
	}
}
