package com.rabbit.gui.base;

import java.io.IOException;
import java.util.Stack;

import org.lwjgl.input.Keyboard;

import com.rabbit.gui.component.IGui;
import com.rabbit.gui.show.IShow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Stage extends GuiScreen implements IStage {

	/**
	 * Currently displayed show
	 */
	protected IShow show;

	/**
	 * Contains all opened shows (including current)
	 */
	private Stack<IShow> showHistory = new Stack<>();

	/**
	 * Will create an empty show <br>
	 * Note: If you try to render empty stage the crash may occur
	 */
	public Stage() {
	}

	/**
	 * Creates stage and places the given show on it
	 *
	 * @param show
	 *            - displayed show
	 */
	public Stage(IShow show) {
		display(show);
	}

	@Override
	public void close() {
		Minecraft.getMinecraft().setIngameFocus();
	}

	/**
	 * Puts the given show on stage
	 *
	 * @param show
	 *            - show to display
	 */
	@Override
	public void display(IShow show) {
		setShow(show);
		show.setStage(this);
		this.reinitShow(true);
		pushHistory(this.show);
	}

	/**
	 * Displays previously opened show <br>
	 * If current show is the only opened show this stage will be closed <br>
	 * If history is empty nothing will happen
	 */
	@Override
	public void displayPrevious() {
		if (getShowHistory().size() != 0) {
			if (getShowHistory().size() == 1) {
				close();
			} else {
				getShowHistory().pop(); // remove current
				display(getShowHistory().pop()); // remove and open
													// previous
			}
		}
	}

	/**
	 * Wrapper for vanilla method
	 */
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	/**
	 * Wrapper for vanilla method
	 */
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		show.onDraw(mouseX, mouseY, partialTicks);
	}

	/**
	 * Returns currently displayed show
	 *
	 * @return currently display show
	 */
	@Override
	public IShow getShow() {
		return show;
	}

	/**
	 * @return This stage history
	 */
	@Override
	public Stack<IShow> getShowHistory() {
		return showHistory;
	}

	/**
	 * Wrapper for vanilla method
	 *
	 * @throws IOException
	 */
	@Override
	public void handleMouseInput() throws IOException {
		super.handleMouseInput();
		show.onMouseInput();
	}

	/**
	 * Wrapper for vanilla method
	 */
	@Override
	public final void initGui() {
		this.reinitShow();
	}

	/**
	 * Wrapper for vanilla method
	 */
	@Override
	protected void keyTyped(char typedChar, int typedKeyIndex) {
		show.onKeyTyped(typedChar, typedKeyIndex);
		if (typedKeyIndex == Keyboard.KEY_ESCAPE) {
			Minecraft.getMinecraft().setIngameFocus();
		}
	}

	/**
	 * Wrapper for vanilla method
	 */
	@Override
	public void mouseClicked(int clickX, int clickY, int mouseIndex) {
		show.onMouseClicked(clickX, clickY, mouseIndex, false);
	}

	/**
	 * Wrapper for vanilla method
	 */
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int type) {
		super.mouseReleased(mouseX, mouseY, type);
		if ((type == 0) || (type == 1)) {
			show.onMouseRelease(mouseX, mouseY);
		}
	}

	/**
	 * Wrapper for vanilla method
	 */
	@Override
	public void onGuiClosed() {
		show.onClose();
	}

	/**
	 * Updated stage's history and adds the given show <br>
	 * If given show already in the history it will be moved to the start
	 *
	 * @param show
	 *            - show which must be placed in history
	 */
	private void pushHistory(IShow show) {
		if (showHistory.contains(show)) {
			showHistory.remove(show);
		}
		showHistory.push(show);
	}

	/**
	 * Shortcut for #reinitShow(false), provided for backward compatibility
	 */
	@Override
	public void reinitShow() {
		this.reinitShow(false);
	}

	/**
	 * Reinitialized currently opened shows, updates it's resolution and re-setups
	 * it. <br>
	 * If <code>forceInit</code> is <code>true</code> show#onInit() will be called
	 * even if it's been already initialized
	 *
	 * @param forceInit
	 *            - if <code>true</code> show#onInit() will be called event if it's
	 *            been already initialized
	 */
	@Override
	public void reinitShow(boolean forceInit) {
		show.setSize(width, height);
		if (show instanceof WidgetContainer) {
			((WidgetContainer) show).getComponentsList().clear();
		}
		if (!show.hasBeenInitialized() || forceInit) {
			show.onInit();
		}
		show.setup();
		if (show instanceof WidgetContainer) {
			((WidgetContainer) show).getComponentsList().forEach(IGui::setup);
		}
	}

	/**
	 * Setter for show field, if you want to display show use
	 * {@link #display(IShow)} instead
	 *
	 * @param show
	 *            - new show
	 * @return current instance of Stage
	 */
	@Override
	public Stage setShow(IShow show) {
		this.show = show;
		return this;
	}

	/**
	 * Wrapper for vanilla method
	 */
	@Override
	public void updateScreen() {
		show.onUpdate();
	}
}
