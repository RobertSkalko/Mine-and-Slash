package com.rabbit.gui.layout.argument;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.objecthunter.exp4j.Expression;

/**
 * Represents layout argument which needs some argument For example: width and
 * height of the screen
 */
@SideOnly(Side.CLIENT)
public class LayoutCalculatableArgument<T> implements ILayoutArgument {

	private String fieldName;

	private Expression expression;

	public LayoutCalculatableArgument(String fieldName, Expression exp) {
		this.fieldName = fieldName;
		this.expression = exp;
	}

	@Override
	public String fieldName() {
		return this.fieldName;
	}

	public int get(Pair<String, Double>... args) {
		for (Pair<String, Double> p : args) {
			this.expression.setVariable(p.getKey(), p.getValue());
		}
		return (int) this.expression.evaluate();
	}

}
