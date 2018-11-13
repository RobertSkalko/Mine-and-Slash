package com.rabbit.gui.render;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.lwjgl.opengl.GL20;

import com.rabbit.gui.RabbitGui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;

public class ShaderProgram {
	private int program;

	public ShaderProgram(String domain, String vertShaderFilename, String fragShaderFilename) {
		try {
			init(domain, vertShaderFilename, fragShaderFilename);
			if (program > 0) {
				GL20.glUseProgram(program);
				GL20.glUniform1i(GL20.glGetUniformLocation(program, "texture"), 0);
				GL20.glUseProgram(0);
			}
		} catch (Exception e) {
			RabbitGui.logger.error("Could not initialize shader program!", e);
			program = 0;
		}
	}

	public int getProgram() {
		return program;
	}

	private void init(String domain, String vertShaderFilename, String fragShaderFilename) {
		if (!OpenGlHelper.shadersSupported) {
			program = 0;
			return;
		}
		program = GL20.glCreateProgram();

		int vertShader = loadAndCompileShader(domain, vertShaderFilename, 35633);
		int fragShader = loadAndCompileShader(domain, fragShaderFilename, 35632);
		if (vertShader != 0) {
			GL20.glAttachShader(program, vertShader);
		}
		if (fragShader != 0) {
			GL20.glAttachShader(program, fragShader);
		}
		GL20.glLinkProgram(program);
		if (GL20.glGetProgrami(program, 35714) == 0) {
			RabbitGui.logger.error("Could not link shader: {}",
					new Object[] { GL20.glGetProgramInfoLog(program, 1024) });
			GL20.glDeleteProgram(program);
			program = 0;
			return;
		}
		GL20.glValidateProgram(program);
		if (GL20.glGetProgrami(program, 35715) == 0) {
			RabbitGui.logger.error("Could not validate shader: {}",
					new Object[] { GL20.glGetProgramInfoLog(program, 1024) });
			GL20.glDeleteProgram(program);
			program = 0;
		}
	}

	private int loadAndCompileShader(String domain, String filename, int shaderType) {
		if (filename == null) {
			return 0;
		}
		int handle = GL20.glCreateShader(shaderType);
		if (handle == 0) {
			RabbitGui.logger.error("Could not create shader of type {} for {}: {}",
					new Object[] { Integer.valueOf(shaderType), filename, GL20.glGetProgramInfoLog(program, 1024) });
			return 0;
		}
		String code = loadFile(new ResourceLocation(domain, filename));
		if (code == null) {
			GL20.glDeleteShader(handle);
			return 0;
		}
		GL20.glShaderSource(handle, code);
		GL20.glCompileShader(handle);
		if (GL20.glGetShaderi(handle, 35713) == 0) {
			RabbitGui.logger.error("Could not compile shader {}: {}",
					new Object[] { filename, GL20.glGetShaderInfoLog(program, 1024) });
			GL20.glDeleteShader(handle);
			return 0;
		}
		return handle;
	}

	private String loadFile(ResourceLocation resourceLocation) {
		try {
			StringBuilder code = new StringBuilder();
			InputStream inputStream = Minecraft.getMinecraft().getResourceManager().getResource(resourceLocation)
					.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String line;
			while ((line = reader.readLine()) != null) {
				code.append(line);
				code.append('\n');
			}
			reader.close();

			return code.toString();
		} catch (Exception e) {
			RabbitGui.logger.error("Could not load shader file!", e);
		}
		return null;
	}
}
