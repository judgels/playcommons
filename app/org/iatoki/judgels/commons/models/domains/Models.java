package org.iatoki.judgels.commons.models.domains;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public final class Models {

    private Models() {
        // prevent instantiation
    }

    public static String getModelSlug(Class<?> clazz) {
        return StringUtils.uncapitalize(clazz.getSimpleName());
    }

    public static String getFieldSlug(Field field) {
        if (field.isAnnotationPresent(Id.class)) {
            return "general.id";
        } else {
            return getModelSlug(field.getDeclaringClass()) + ".field." + field.getName();
        }
    }

    public static <M> M newModel(Class<M> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Cannot instantiate model " + clazz.getSimpleName());
        }
    }

    public static List<Field> getFields(Class<?> clazz) {
        if (clazz.equals(Object.class)) {
            return ImmutableList.of();
        }

        ImmutableList.Builder<Field> fields = ImmutableList.builder();
        fields.addAll(getFields(clazz.getSuperclass()));
        fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        return fields.build();
    }
}
