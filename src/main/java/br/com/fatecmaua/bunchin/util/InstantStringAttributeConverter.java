package br.com.fatecmaua.bunchin.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.time.Instant;

@Converter(autoApply = false)
public class InstantStringAttributeConverter implements AttributeConverter<Instant, String> {
    @Override
    public String convertToDatabaseColumn(Instant attribute) {
        return attribute != null ? attribute.toString() : null;
    }

    @Override
    public Instant convertToEntityAttribute(String dbData) {
        return dbData != null ? Instant.parse(dbData) : null;
    }
}
