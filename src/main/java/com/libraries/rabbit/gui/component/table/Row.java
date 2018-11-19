package com.libraries.rabbit.gui.component.table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.libraries.rabbit.gui.component.WidgetList;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Row<T> implements WidgetList<T> {

	protected final String name;
	protected final List<T> content;

	public Row(String name, List<T> content) {
		this.name = name;
		this.content = content;
	}

	public Row(String name, T... content) {
		this(name, new ArrayList(Arrays.asList(content)));
	}

	@Override
	public Row<T> add(T object) {
		this.content.add(object);
		return this;
	}

	@Override
	public WidgetList<T> addAll(Collection<T> values) {
		values.forEach(this::add);
		return this;
	}

	@Override
	public Row<T> addAll(T... objects) {
		this.content.addAll(Arrays.asList(objects));
		return this;
	}

	@Override
	public Row<T> clear() {
		this.content.clear();
		return this;
	}

	@Override
	public List<T> getContent() {
		return this.content;
	}

	public String getName() {
		return this.name;
	}

	public List<String> getStringContent() {
		List<String> strings = new ArrayList<>();
		this.getContent().forEach(element -> strings.add(String.valueOf(element)));
		return strings;
	}

	@Override
	public Row<T> remove(T object) {
		this.content.remove(object);
		return this;
	}
}
