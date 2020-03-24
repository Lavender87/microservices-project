package com.gupao.microservicesproject.httputil;


import com.squareup.okhttp.*;

import java.io.IOException;
import java.util.Arrays;

public class OkHttpUtils {

    public static void testGraphql(){
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();

        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
//        RequestBody body = RequestBody.create(mediaType, "{\"query\":\"query \\n{\\n    countBooks\\n}\",\"variables\":{}}");

//        RequestBody body = RequestBody.create(mediaType, "{\"query\":\"query { findOneAuthor(id:1){ id firstName lastName}}\",\"variables\":{}}");
        RequestBody body = RequestBody.create(mediaType, "{\"query\":\"query { findAuthorsByFilter( filter:[{firstName:\\\"Jim\\\",lastName:\\\"Green\\\",gender:MAN}],first:1){firstName   lastName}}\",\"variables\":{}}");

        Request request = new Request.Builder()
                .url("http://localhost:8082/graphql")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.body());
            System.out.println(response.message());
            System.out.println(response.body().string());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
