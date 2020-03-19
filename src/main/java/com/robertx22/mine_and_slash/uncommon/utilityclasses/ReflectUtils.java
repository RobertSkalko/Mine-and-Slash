package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import net.minecraftforge.event.entity.living.LivingAttackEvent;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ReflectUtils {

    public static Field getField(Object obj, String name) {
        try {
            return obj.getClass()
                .getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void setAttackEventValue(LivingAttackEvent event, float val) {
        setValue(event, ReflectUtils.getField(event, "amount"), val);
    }

    public static void setValue(Object obj, Field field, float val) {

        try {

            field.setAccessible(true);
            // Remove final modifier
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            // Get and set field value

            System.out.println(field.get(obj));

            field.set(obj, val);

            System.out.println(field.get(obj));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
