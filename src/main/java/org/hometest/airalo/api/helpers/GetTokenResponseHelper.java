package org.hometest.airalo.api.helpers;

import java.util.HashMap;
import java.util.List;

public class GetTokenResponseHelper {
    public List<HashMap<String, Object>> getData() {
        return data;
    }

    public void setData(List<HashMap<String, Object>> data) {
        this.data = data;
    }

    private List<HashMap<String, Object>> data;
}
