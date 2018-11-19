package com.libraries.rabbit.gui.component.display.entity;

import org.lwjgl.input.Mouse;

import com.libraries.rabbit.gui.component.GuiWidget;
import com.libraries.rabbit.gui.layout.LayoutComponent;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityComponent extends GuiWidget {

	EntityLivingBase entity;
	boolean dragging;
	boolean canRotate;

	@LayoutComponent
	float rotation = 0;

	@LayoutComponent
	float zoom = 1;

	public EntityComponent(int x, int y, int width, int height, EntityLivingBase entity) {
		this(x, y, width, height, entity, 0, 1);
	}

	public EntityComponent(int x, int y, int width, int height, EntityLivingBase entity, int rotation) {
		this(x, y, width, height, entity, rotation, 1);
	}

	public EntityComponent(int x, int y, int width, int height, EntityLivingBase entity, int rotation, float zoom) {
		this(x, y, width, height, entity, rotation, zoom, true);
	}

	public EntityComponent(int x, int y, int width, int height, EntityLivingBase entity, int rotation, float zoom,
			boolean canRotate) {
		super(x, y, width, height);
		width = (int) (entity.width * 30 * zoom);
		height = (int) (entity.height * 30 * zoom);
		this.entity = entity;
		this.rotation = rotation;
		this.zoom = zoom;
		this.canRotate = canRotate;
	}

	public EntityLivingBase getEntity() {
		return entity;
	}

	public float getRotation() {
		return rotation;
	}

	public float getZoom() {
		return zoom;
	}

	@Override
	public void onDraw(int mouseX, int mouseY, float partialTicks) {
		super.onDraw(mouseX, mouseY, partialTicks);

		// entity.onEntityUpdate();

		GlStateManager.enableColorMaterial();
		GlStateManager.color(1, 1, 1, 1);
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(x, y, 100.0F);
			GlStateManager.scale(30.0f * zoom, 30.0f * zoom, 30.0f * zoom);
			GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
			GlStateManager.translate(-entity.width / 2, -entity.height, 0.0F);

			RenderHelper.enableStandardItemLighting();
			entity.renderYawOffset = rotation;
			entity.rotationYawHead = entity.renderYawOffset;
			RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
			rendermanager.setRenderShadow(false);
			rendermanager.renderEntity(entity, 0.0D, 0.0D, 0.0D, 0.0f, 1.0F, false);
			rendermanager.setRenderShadow(true);
		}
		GlStateManager.popMatrix();
		RenderHelper.disableStandardItemLighting();
		GlStateManager.disableRescaleNormal();
		GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		GlStateManager.disableTexture2D();
		GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
	}

	@Override
	public boolean onMouseClicked(int posX, int posY, int mouseButtonIndex, boolean overlap) {
		super.onMouseClicked(posX, posY, mouseButtonIndex, overlap);
		boolean clicked = !overlap;
		dragging = clicked;
		return clicked;
	}

	/**
	 * Called then mouse moved or scrolled
	 */
	@Override
	public void onMouseInput() {
		super.onMouseInput();
		if (canRotate && dragging) {
			rotation += Mouse.getEventDX() * .5;
		}
	}

	@Override
	public void onMouseRelease(int mouseX, int mouseY) {
		super.onMouseRelease(mouseX, mouseY);
		dragging = false;
	}

	public EntityComponent setEntity(EntityLivingBase entity) {
		this.entity = entity;
		return this;
	}

	public EntityComponent setRotation(float rotation) {
		this.rotation = rotation;
		return this;
	}

	public EntityComponent setZoom(float zoom) {
		this.zoom = zoom;
		return this;
	}
}
