package net.moltak.soap.mock.model;

import net.moltak.soap.model.BaseRequest;
import net.moltak.soap.utility.ParameterBaker;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created by moltak on 15. 6. 17..
 */
public class GetListRequest implements BaseRequest {

    private final String name;

    public GetListRequest(String name) {
        this.name = name;
    }

    @Override
    public String getRequestName() {
        return "get_list";
    }

    @Override
    public Map<String, String> getParams() throws IllegalAccessException, InvocationTargetException {
        return ParameterBaker.bake(this);
    }

    public String getName() {
        return name;
    }
}
