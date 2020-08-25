package com.uae.emiratescar.utils;

import com.uae.emiratescar.network.APIService;
import com.uae.emiratescar.network.RequestController;

public class ApiUtils {

    private ApiUtils() {
    }

    public static APIService getAPIService() {

        return RequestController.getClient(Constant.base_url).create(APIService.class);
    }
}
