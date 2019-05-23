package com.libraries.rabbit.gui.utils;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.imageio.ImageIO;

import com.libraries.rabbit.gui.RabbitGui;

import net.minecraft.client.Minecraft;

public final class ImageCacheHelper {
	private static final File cacheFolder;
	private static final MessageDigest digestor;

	static {
		cacheFolder = new File(Minecraft.getMinecraft().gameDir, "/Rabbit/cache");
		try {
			digestor = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		if (!ImageCacheHelper.cacheFolder.exists()) {
			ImageCacheHelper.cacheFolder.mkdirs();
		}
	}

	public static BufferedImage fetchImage(final String url) throws MalformedURLException {
		return ImageCacheHelper.fetchImage(new URL(url));
	}

	public static BufferedImage fetchImage(final URL url) {
		final String externalUrl = url.toExternalForm();
		final File cachedImage = ImageCacheHelper.getCachedImage(externalUrl);
		HttpURLConnection conn = null;
		InputStream stream = null;
		FileOutputStream fos = null;
		if (!cachedImage.exists()) {
			RabbitGui.logger.info("Downloading image " + cachedImage.getName());
			try {
				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestProperty("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
				conn.connect();
				final int response = conn.getResponseCode();
				if ((response < 200) || (response > 207)) {
					RabbitGui.logger
							.warn("Received HTTP response code " + response + ". Attempting to read image anyways.");
				}
				stream = new BufferedInputStream(conn.getInputStream());
				final ByteArrayOutputStream baos = new ByteArrayOutputStream();
				final byte[] buff = new byte[1024];
				int read;
				while ((read = stream.read(buff)) != -1) {
					baos.write(buff, 0, read);
				}
				baos.close();
				if (!cachedImage.createNewFile()) {
					throw new IOException("Failed to create cache image!");
				}
				fos = new FileOutputStream(cachedImage, false);
				fos.write(baos.toByteArray());
				fos.flush();
			} catch (IOException e) {
				RabbitGui.logger.error("Failed to fetch image from URL " + externalUrl, e);
			} finally {
				if (conn != null) {
					conn.disconnect();
				}
				ImageCacheHelper.tryToClose(stream);
				ImageCacheHelper.tryToClose(fos);
			}
		} else {
			RabbitGui.logger.info("Using cached image " + cachedImage.getName());
		}
		BufferedImage image;
		try {
			image = ImageIO.read(cachedImage);
		} catch (IOException e) {
			RabbitGui.logger.error("Failed to read image from path " + cachedImage, e);
			return null;
		}
		return image;
	}

	public static BufferedImage getBufferedCachedImage(final String url) {
		ImageCacheHelper.digestor.reset();
		ImageCacheHelper.digestor.update(url.getBytes(Charset.forName("UTF-8")));
		final String fileName = String.format("%040x", new BigInteger(1, ImageCacheHelper.digestor.digest()));
		try {
			return ImageIO.read(new File(ImageCacheHelper.cacheFolder, fileName));
		} catch (IOException e) {
			RabbitGui.logger.error("Failed to fetch image from URL: " + url, e);
			return null;
		}
	}

	public static File getCachedImage(final String url) {
		ImageCacheHelper.digestor.reset();
		ImageCacheHelper.digestor.update(url.getBytes(Charset.forName("UTF-8")));
		final String fileName = String.format("%040x", new BigInteger(1, ImageCacheHelper.digestor.digest()));
		return new File(ImageCacheHelper.cacheFolder, fileName);
	}

	public static BufferedImage resizeImage(final BufferedImage image) {
		return ImageCacheHelper.resizeImage(image, 128, 128);
	}

	public static BufferedImage resizeImage(final BufferedImage image, final int width, final int height) {
		final BufferedImage resized = new BufferedImage(width, height, 1);
		final Graphics2D graphics = resized.createGraphics();
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.drawImage(image, 0, 0, width, height, null);
		graphics.dispose();
		return resized;
	}

	public static void tryToClose(final Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				RabbitGui.logger.error("Failed to close stream " + closeable, e);
			}
		}
	}
}