package br.com.vrbsm.challenge.rest;

import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import br.com.vrbsm.challenge.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RestGenerator {

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static HttpLoggingInterceptor loggin = new HttpLoggingInterceptor();

    public static <S> S createService(Class<S> serviceClass) {


        httpClient.connectTimeout(30, TimeUnit.SECONDS);
        loggin.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(loggin);

        OkHttpClient client = httpClient.build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.REST_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setDateFormat("EEE MMM dd kk:mm:ss z yyyy").create()))
                .client((client))
                .build();

        return retrofit.create(serviceClass);
    }

}


