package rashjz.info.util;


import java.util.Date;
import java.util.Locale;
import com.vaadin.data.util.converter.Converter;

@SuppressWarnings("serial")
public class DateToSqlTimestampConverter implements Converter<Date, java.sql.Timestamp> {

    @Override
    public java.sql.Timestamp convertToModel(Date value, Class<? extends java.sql.Timestamp> targetType, Locale locale) throws ConversionException {
        if (targetType != getModelType()) {
            throw new ConversionException("Converter only supports " + getModelType().getName() + " (targetType was " + targetType.getName() + ")");
        }

        if (null == value)
            return null;

        return new java.sql.Timestamp(value.getTime());
    }

    @Override
    public Date convertToPresentation(java.sql.Timestamp value, Class<? extends Date> targetType, Locale locale) throws ConversionException {
        if (targetType != getPresentationType()) {
            throw new ConversionException("Converter only supports " + getPresentationType().getName() + " (targetType was " + targetType.getName() + ")");
        }

        if (null == value)
            return null;

        return new Date(value.getTime());
    }

    @Override
    public Class<java.sql.Timestamp> getModelType() {
        return java.sql.Timestamp.class;
    }

    @Override
    public Class<Date> getPresentationType() {
        return Date.class;
    }
}