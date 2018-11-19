package com.libraries.rabbit.gui.utils;

import java.awt.Color;

import net.minecraft.client.renderer.GlStateManager;

public class ColourHelper {
	/**
	 * Evaluates rgb from given color and bind it to GL
	 *
	 * @param color
	 *            - awt color
	 */
	public static int AWTColor2RGBInt(Color color) {
		return ColourHelper.RGB(color.getRed(), color.getGreen(), color.getBlue());
	}

	/**
	 * Blends given int colours
	 *
	 * @param colours
	 *            an amount of colours
	 * @return the mix int colour value or an IllegalArgumentException if colours is
	 *         empty
	 */
	public static int blend(final int... colours) {
		if (colours.length < 1) {
			throw new IllegalArgumentException();
		}

		final int[] alphas = new int[colours.length];
		final int[] reds = new int[colours.length];
		final int[] greens = new int[colours.length];
		final int[] blues = new int[colours.length];

		for (int i = 0; i < colours.length; i++) {
			alphas[i] = ((colours[i] >> 24) & 0xff);
			reds[i] = ((colours[i] & 0xff0000) >> 16);
			greens[i] = ((colours[i] & 0xff00) >> 8);
			blues[i] = (colours[i] & 0xff);
		}

		float a, r, g, b;
		a = r = g = b = 0;
		final float ratio = 1.0F / colours.length;

		for (final int alpha : alphas) {
			a += alpha * ratio;
		}

		for (final int red : reds) {
			r += red * ratio;
		}

		for (final int green : greens) {
			g += green * ratio;
		}

		for (final int blue : blues) {
			b += blue * ratio;
		}

		return (((int) a) << 24) | (((int) r) << 16) | (((int) g) << 8) | ((int) b);
	}

	/**
	 * Blend colour with given grey scale
	 *
	 * @param colour
	 *            colour in int form
	 * @param greyScale
	 *            grayScale as float
	 * @return the toned colour
	 */
	public static int blendWithGreyScale(final int colour, final float greyScale) {
		return ColourHelper.blend(colour, ColourHelper.RGB(greyScale, greyScale, greyScale));
	}

	/**
	 * Gives a colour based of {@link System#currentTimeMillis()} and given params
	 *
	 * @param freqR
	 *            strength of the reds
	 * @param freqG
	 *            strength of the greens
	 * @param freqB
	 *            strength of the blues
	 * @param phaseR
	 *            phase shift red
	 * @param phaseG
	 *            phase shift green
	 * @param phaseB
	 *            phase shift blue
	 * @param center
	 *            center value
	 * @param width
	 *            width of colour range
	 * @param length
	 *            change rate
	 * @return an int colour
	 */
	public static int getRainbowColour(final float freqR, final float freqG, final float freqB, final float phaseR,
			final float phaseG, final float phaseB, final float center, final float width, final float length) {
		final long i = Math.abs((int) System.currentTimeMillis()) / (int) length;
		final double r = (Math.sin((freqR * i) + phaseR) * width) + center;
		final double g = (Math.sin((freqG * i) + phaseG) * width) + center;
		final double b = (Math.sin((freqB * i) + phaseB) * width) + center;
		return ColourHelper.RGB((float) r, (float) g, (float) b);
	}

	/**
	 * Short had for parsing array of params
	 *
	 * @param params
	 *            all parameters for
	 *            {@link #getRainbowColour(float, float, float, float, float, float, float, float, float)}
	 * @return an int rainbow colour
	 */
	public static int getRainbowColour(final float[] params) {
		return ColourHelper.getRainbowColour(params[0], params[1], params[2], params[3], params[4], params[5],
				params[6], params[7], params[8]);
	}

	/**
	 * Create settings for rainbow colour
	 *
	 * @param colourCode
	 *            a string representation of the rainbow settings
	 * @return an array containing parameters for
	 *         {@link #getRainbowColour(float, float, float, float, float, float, float, float, float)}
	 */
	public static float[] getRainbowSettings(final String colourCode) {
		final String[] splitted = colourCode.split(";");
		final float[] result = { 0.3F, 0.3F, 0.3F, 0, 2, 4, 128, 127, 50 };
		for (int i = 1; i < splitted.length; i++) {
			try {
				result[i - 1] = Float.parseFloat(splitted[i]);
			} catch (final NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * Evaluates rgb from given color and bind it to GL
	 *
	 * @param color
	 *            - awt color
	 */
	public static void glColorAWT(Color color) {
		ColourHelper.glColorRGB(color.getRGB());
	}

	/**
	 * Evaluates red, green, blue and alpha from given color and binds them to GL
	 *
	 * @param rgb
	 *            - rgb color
	 */
	public static void glColorRGB(int rgb) {
		float alpha = ((rgb >> 24) & 255) / 255.0F;
		float red = ((rgb >> 16) & 255) / 255.0F;
		float green = ((rgb >> 8) & 255) / 255.0F;
		float blue = (rgb & 255) / 255.0F;
		GlStateManager.color(red, green, blue, alpha);
	}

	/**
	 * Convert to integer RGBA value Uses 1.0F as A value
	 *
	 * @param red
	 *            float red
	 * @param green
	 *            float green
	 * @param blue
	 *            float blue
	 *
	 * @return single integer representation of the given floats
	 */
	public static int RGB(final float red, final float green, final float blue) {
		return ColourHelper.RGBA((int) red * 255, (int) green * 255, (int) blue * 255, 255);
	}

	/**
	 * Convert to integer RGBA value
	 *
	 * @param red
	 *            float red
	 * @param green
	 *            float green
	 * @param blue
	 *            float blue
	 * @param alpha
	 *            float alpha
	 *
	 * @return single integer representation of the given floats
	 */
	public static int RGB(final float red, final float green, final float blue, final float alpha) {
		return ColourHelper.RGBA((int) red * 255, (int) green * 255, (int) blue * 255, (int) alpha * 255);
	}

	/**
	 * Convert to integer RGBA value Uses 255 as A value
	 *
	 * @param r
	 *            integer red
	 * @param g
	 *            integer green
	 * @param b
	 *            integer blue
	 *
	 * @return single integer representation of the given ints
	 */
	public static int RGB(final int r, final int g, final int b) {
		return ColourHelper.RGBA(r, g, b, 255);
	}

	/**
	 * Convert an #RRGGBB value to a int colour
	 *
	 * @param colour
	 *            the #RRGGBB value
	 * @return the int colour value or an {@link java.lang.IllegalArgumentException}
	 *         if a mal formed input is given
	 */
	public static int RGB(final String colour) {
		if (!colour.startsWith("#") || !(colour.length() == 7)) {
			throw new IllegalArgumentException("Use #RRGGBB format");
		}
		return ColourHelper.RGB(Integer.parseInt(colour.substring(1, 3), 16),
				Integer.parseInt(colour.substring(3, 5), 16), Integer.parseInt(colour.substring(5, 7), 16));
	}

	/**
	 * Convert to integer RGBA value
	 *
	 * @param r
	 *            integer red
	 * @param g
	 *            integer green
	 * @param b
	 *            integer blue
	 * @param a
	 *            integer alpha
	 *
	 * @return single integer representation of the given ints
	 */
	public static int RGBA(final int r, final int g, final int b, final int a) {
		return (a << 24) | ((r & 255) << 16) | ((g & 255) << 8) | (b & 255);
	}

	/**
	 * Tone a int colour bigger then 1 will tone up, less then 1 will tone down
	 *
	 * @param colour
	 *            colour in int form
	 * @param scale
	 *            scale as float
	 * @return the toned colour
	 */
	public static int tone(final int colour, final float scale) {
		final float r = (colour >> 16) & 255;
		final float g = (colour >> 8) & 255;
		final float b = colour & 255;
		return ColourHelper.RGB(r * scale, g * scale, b * scale);
	}
}
