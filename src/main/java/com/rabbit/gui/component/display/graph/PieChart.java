package com.rabbit.gui.component.display.graph;

import java.awt.Color;
import java.util.stream.DoubleStream;

import com.rabbit.gui.component.GuiWidget;
import com.rabbit.gui.render.Renderer;
import com.rabbit.gui.render.TextAlignment;
import com.rabbit.gui.render.TextRenderer;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PieChart extends GuiWidget {

	/**
	 * Contains colors which will be used in diagram, by default it's filled with
	 * six common colors from java.awt.Color class
	 */
	protected Color[] colors = { Color.BLUE, Color.RED, Color.ORANGE, Color.MAGENTA, Color.GREEN, Color.pink };
	/**
	 * Width and height of the diagram
	 */
	protected int size;
	/**
	 * Each data value represents piece of diagram
	 */
	protected double[] data = new double[0];
	/**
	 * Contains display angle for each data value, usually calculated in constructor
	 */
	protected double[] angles = new double[0];
	/**
	 * Contains titles per each value, titles length may be differ from value length
	 */
	protected String[] titles = new String[0];

	public PieChart(int x, int y, int size, double[] data) {
		this(x, y, size, data, new String[0]);
	}

	public PieChart(int x, int y, int size, double[] data, String[] titles) {
		super(x, y, size, size);
		this.size = size;
		this.data = data;
		this.titles = titles;
		initialCalculate();
	}

	/**
	 * Display angle for each data value
	 */
	public double[] getAngles() {
		return angles;
	}

	public double[] getData() {
		return data;
	}

	public String[] getTitles() {
		return titles;
	}

	protected void initialCalculate() {
		angles = new double[data.length];
		double total = DoubleStream.of(data).sum();

		for (int i = 0; i < data.length; i++) {
			angles[i] = (data[i] / total) * 360;
		}
	}

	@Override
	public void onDraw(int mouseX, int mouseY, float partialTicks) {
		super.onDraw(mouseX, mouseY, partialTicks);
		double prevAngle = 0;
		for (int i = 0; i < data.length; i++) {
			Color color = colors[i % colors.length];
			Renderer.drawFilledArc(x + (width / 2), y + (height / 2), size / 2, prevAngle, angles[i] + prevAngle,
					color.getRGB());

			if ((i < titles.length) && (angles[i] > 0)) { // if title
															// exist and
															// slice has
															// been
															// drawn
				double textAngle = Math.toRadians(prevAngle + (angles[i] / 2));
				int textX = (int) (x + (width / 2) + ((Math.sin(textAngle) * size) / 4));
				int textY = (int) (y + (height / 2) + ((Math.cos(textAngle) * size) / 4));
				textY -= 5;
				TextRenderer.renderString(textX, textY, titles[i], TextAlignment.CENTER);
			}
			prevAngle += angles[i];
		}
	}

	public PieChart setColors(Color[] colors) {
		this.colors = colors;
		return this;
	}

	/**
	 * Updates data and recalculates angles
	 */
	public PieChart setData(double[] data) {
		this.data = data;
		initialCalculate();
		return this;
	}

	public PieChart setTitles(String[] titles) {
		this.titles = titles;
		return this;
	}
}
