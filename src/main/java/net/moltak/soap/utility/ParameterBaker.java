package net.moltak.soap.utility;

import net.moltak.soap.model.BaseRequest;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by moltak on 15. 2. 5..
 */
public class ParameterBaker {
    public static Map<String, String> bake(BaseRequest baseRequest)
            throws InvocationTargetException, IllegalAccessException {
        
        Map<String, String> map = new HashMap<>();
        Field[]fields = baseRequest.getClass().getDeclaredFields();
        for(Field i : fields) {
            if("request_name".equals(i.getName())) continue;

            String getFieldValueFunction = runGetter(baseRequest, i.getName());
            if(!getFieldValueFunction.isEmpty()) map.put(i.getName().toUpperCase(), getFieldValueFunction);
        }
        return map;
    }

    private static String runGetter(BaseRequest baseRequest, String fieldName)
            throws InvocationTargetException, IllegalAccessException {

        for(Method i : baseRequest.getClass().getMethods()) {
            if (!i.getName().startsWith("get")) continue;
            if (i.getName().length() != (fieldName.length() + 3)) continue;

            if (i.getName().toLowerCase().endsWith(fieldName.toLowerCase())) {
                return (String)i.invoke(baseRequest);
            }
        }

        // for jacoco field.
        if ("$jacocoData".equals(fieldName)) return "";
        throw new IllegalAccessException(String.format("There is no %s field!", fieldName));
    }
}
