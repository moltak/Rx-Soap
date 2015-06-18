package net.moltak.soap.parser;

import net.moltak.soap.deserializer.SoapDeserializer;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import java.lang.reflect.Field;
import java.text.ParseException;

/**
 * Created by moltak on 15. 6. 12..
 */
public class AnnotationSoapParser<E> implements SoapParser {
    @SuppressWarnings("unchecked")
    public E parsing(Object reslt, Class outputClass) throws IllegalAccessException, InstantiationException, ParseException {
        if (reslt instanceof SoapObject) {
            if (outputClass != null) {
                Object obj = outputClass.newInstance();
                SoapObject soapObject
                        = (SoapObject)((SoapObject) ((SoapObject) reslt).getProperty(1)).getProperty(0);

                Field[] fields = outputClass.getDeclaredFields();
                if(fields.length > 0 && fields[0].getType().isArray()) {
                    return (E)SoapDeserializer.fromSoapObject(obj, outputClass, soapObject);
                } else {
                    return (E)SoapDeserializer.fromSoapObject(obj, outputClass, (SoapObject) soapObject.getProperty(0));
                }
            }
        } else if (reslt instanceof SoapPrimitive) {
            if (outputClass != null) {
                if (outputClass.equals(String.class)) {
                    return (E) reslt.toString();
                } else if ((outputClass.equals(Integer.class)) || (outputClass.equals(int.class))) {
                    return (E) Integer.valueOf(reslt.toString());
                } else if ((outputClass.equals(Double.class)) || (outputClass.equals(double.class))) {
                    return (E) Double.valueOf(reslt.toString());
                } else if ((outputClass.equals(Float.class)) || (outputClass.equals(float.class))) {
                    return (E) Float.valueOf(reslt.toString());
                } else if ((outputClass.equals(Boolean.class)) || (outputClass.equals(boolean.class))) {
                    return (E) Boolean.valueOf(reslt.toString());
                }
            }
        }

        throw new ParseException("Maybe it's not soap protocol results.", 0);
    }
}
