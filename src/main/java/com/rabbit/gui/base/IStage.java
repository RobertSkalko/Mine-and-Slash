package com.rabbit.gui.base;

import java.util.Stack;

import com.rabbit.gui.show.IShow;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface IStage {

	public void close();

	/**
	 * Puts the given show on stage
	 *
	 * @param show
	 *            - show to display
	 */
	public void display(IShow show);

	/**
	 * Displays previously opened show <br>
	 * If current show is the only opened show this stage will be closed <br>
	 * If history is empty nothing will happen
	 */
	public void displayPrevious();

	/**
	 * Returns currently displayed show
	 *
	 * @return currently display show
	 */
	public IShow getShow();

	/**
	 * @return This stage history
	 */
	public Stack<IShow> getShowHistory();

	/**
	 * Shortcut for #reinitShow(false), provided for backward compatibility
	 */
	public void reinitShow();

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
	public void reinitShow(boolean forceInit);

	/**
	 * Setter for show field, if you want to display show use
	 * {@link #display(IShow)} instead
	 *
	 * @param show
	 *            - new show
	 * @return current instance of Stage
	 */
	public IStage setShow(IShow show);
}
