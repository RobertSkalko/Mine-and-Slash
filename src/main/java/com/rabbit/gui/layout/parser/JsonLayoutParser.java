package com.rabbit.gui.layout.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.reflect.FieldUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.rabbit.gui.component.IGui;
import com.rabbit.gui.layout.LayoutComponent;
import com.rabbit.gui.layout.LayoutComponentWrapper;
import com.rabbit.gui.layout.LayoutFunctions;
import com.rabbit.gui.layout.argument.ILayoutArgument;
import com.rabbit.gui.layout.argument.LayoutArgument;
import com.rabbit.gui.layout.argument.LayoutCalculatableArgument;
import com.rabbit.gui.show.LayoutShow;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

/**
 * Generates show from json file
 */
@SideOnly(Side.CLIENT)
public class JsonLayoutParser implements LayoutParser {

	private LayoutShow from(JsonElement json) {
		JsonObject layout = json.getAsJsonObject();
		parseTitle(layout);
		List<LayoutComponentWrapper> components = wrapComponents(parseComponents(layout));
		return new LayoutShow(components);
	}

	@Override
	public LayoutShow from(URI path) {
		File file = new File(path);
		JsonElement json;

		try {
			json = new JsonParser().parse(new FileReader(file));
		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Error while parsing " + file.getAbsolutePath());
		}

		return this.from(json);
	}

	private Map<String, Class<?>> getAllowedFields(Class<? extends IGui> type) {
		LayoutComponent config = type.getAnnotation(LayoutComponent.class);
		Validate.notNull(config, "Provided type can't be accessed throught layout");
		Map<String, Class<?>> allowedFields = Maps.newHashMap();
		for (Field field : FieldUtils.getAllFields(type)) {
			if (field.isAnnotationPresent(LayoutComponent.class)) {
				allowedFields.put(field.getName(), field.getType());
			}
		}
		return allowedFields;
	}

	private ILayoutArgument getArgument(String fieldName, Class<?> type, JsonElement element) {
		ILayoutArgument result = null;
		boolean isSimple = ClassUtils.isPrimitiveOrWrapper(type) || type.isAssignableFrom(String.class);
		if (isSimple) {
			Object value = getPrimitiveValue(type, element);
			if (value instanceof Expression) {
				result = new LayoutCalculatableArgument(fieldName, (Expression) value);
			} else {
				result = new LayoutArgument(fieldName, value);
			}
		} else {
			result = new LayoutArgument(fieldName, getComplicatedValue(type, element));
		}
		return result;
	}

	private List<ILayoutArgument> getArguments(Map<String, JsonElement> fields, Map<String, Class<?>> allowedFields) {
		List<ILayoutArgument> result = Lists.newArrayList();
		for (Entry<String, JsonElement> entry : fields.entrySet()) {
			Class<?> fieldType = allowedFields.get(entry.getKey());
			Validate.notNull(fieldType, entry.getKey() + " not found in component");
			result.add(getArgument(entry.getKey(), fieldType, entry.getValue()));
		}
		return result;
	}

	private Object getComplicatedValue(Class<?> type, JsonElement element) {
		return new Gson().fromJson(element, type);
	}

	private Object getPrimitiveValue(Class<?> type, JsonElement element) {
		Object result = null;
		if (type.isAssignableFrom(String.class)) {
			result = element.getAsString();
		} else if (type.isAssignableFrom(Integer.TYPE)) {
			try {
				result = Integer.parseInt(element.getAsString());
			} catch (NumberFormatException ex) {
				result = new ExpressionBuilder(element.getAsString())
						.functions(LayoutFunctions.width, LayoutFunctions.height).build();
			}
		} else if (type.isAssignableFrom(Boolean.TYPE)) {
			result = element.getAsBoolean();
		}
		return result;
	}

	private JsonArray parseComponents(JsonObject layout) {
		return layout.has("components") ? layout.getAsJsonArray("components") : new JsonArray();
	}

	private String parseTitle(JsonObject layout) {
		return layout.has("title") ? layout.get("title").getAsString() : "";
	}

	private LayoutComponentWrapper wrap(JsonObject component) {
		try {
			Map<String, JsonElement> layoutFields = component.entrySet().stream()
					.collect(Collectors.toMap(Entry::getKey, Entry::getValue));
			Class<? extends IGui> componentType = (Class<? extends IGui>) Class
					.forName(layoutFields.remove("type").getAsString());
			Map<String, Class<?>> allowedFields = getAllowedFields(componentType);
			List<ILayoutArgument> layoutArgument = getArguments(layoutFields, allowedFields);
			return new LayoutComponentWrapper(componentType, new HashSet(layoutArgument));
		} catch (ClassNotFoundException | ClassCastException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Can't resolve component type");
		}
	}

	private List<LayoutComponentWrapper> wrapComponents(JsonArray components) {
		return Lists.newArrayList(components.iterator()).stream().map(element -> wrap(element.getAsJsonObject()))
				.collect(Collectors.toList());
	}

}
