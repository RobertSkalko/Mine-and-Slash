package com.libraries.rabbit.gui.layout;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.apache.commons.lang3.reflect.FieldUtils;

import com.libraries.rabbit.gui.component.IGui;
import com.libraries.rabbit.gui.layout.argument.ILayoutArgument;
import com.libraries.rabbit.gui.layout.argument.LayoutArgument;
import com.libraries.rabbit.gui.layout.argument.LayoutCalculatableArgument;
import com.libraries.rabbit.gui.show.IShow;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayoutComponentWrapper {
	private static IGui instantiateType(Class type) {
		try {
			Constructor constr = type.getDeclaredConstructor();
			if (constr != null) {
				constr.setAccessible(true);
			}
			return (IGui) constr.newInstance();
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException("Can't instantiate " + type.getName() + " with zero-arg constructor");
		}
	}

	private Class type;

	private Set<ILayoutArgument> args;

	public LayoutComponentWrapper(Class type, Set<ILayoutArgument> args) {
		this.type = type;
		this.args = args;
	}

	public IGui create() throws Exception {
		return this.create(null);
	}

	public IGui create(IShow show) throws Exception {
		IGui com;

		com = LayoutComponentWrapper.instantiateType(type);
		for (ILayoutArgument arg : args) {
			Object value = null;
			if (arg instanceof LayoutArgument) {
				value = ((LayoutArgument) arg).get();
			} else if ((arg instanceof LayoutCalculatableArgument) && (show != null)) {
				value = ((LayoutCalculatableArgument) arg).get();
			}

			FieldUtils.writeField(com, arg.fieldName(), value, true);
		}
		return com;
	}

	public Set<ILayoutArgument> getArguments() {
		return args;
	}

	public Class getType() {
		return type;
	}
}
