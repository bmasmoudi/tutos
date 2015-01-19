package com.bmasmoudi.tutos.model.enums.transformer;

import org.apache.commons.collections.Transformer;

/**
 * Validateur de la valeur d'un propriété dans le profil.
 * 
 * <p>
 * Validation basée sur les enum apportée par le JDK5.
 */
public class WDIEnumTransformer<T extends Enum<T>> implements Transformer {

	private final Class<T> enumClass;

	public static <T extends Enum<T>> WDIEnumTransformer<T> forEnum(final Class<T> enumClass) {
		return new WDIEnumTransformer<T>(enumClass);
	}

	private WDIEnumTransformer(final Class<T> enumClass) {
		this.enumClass = enumClass;
	}

	public T transform(final Object input) {
		return getMember(enumClass, (String) input);
	}

	public T getMember(final Class<T> enumType, final String value) {
		for (Enum<T> enumValue : enumType.getEnumConstants()) {
			if (enumValue.name().equals(value)) {
				return (T) enumValue;
			}
		}
		return null;
	}

}
