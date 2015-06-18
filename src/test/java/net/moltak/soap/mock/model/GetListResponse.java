package net.moltak.soap.mock.model;

import net.moltak.soap.annotations.JSoapResField;

/**
 * Created by moltak on 15. 6. 17..
 */
public class GetListResponse {
    @JSoapResField(name = "Table")
    private SoapList[] soapLists;

    public SoapList[] getSoapLists() {
        return soapLists;
    }

    public static class SoapList {
        @JSoapResField(name = "id")
        String id;

        @JSoapResField(name = "name")
        String name;

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
