package com.sea.academy.list.base.rest;

import com.sea.academy.list.util.Validator;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DefaultRestAdapter {

    private final String baseUrl;
    private OkHttpClient.Builder okBuilder;
    private Retrofit.Builder adapterBuilder;

    public DefaultRestAdapter(String baseUrl) {

        Validator.checkNull(baseUrl);

        String auxBaseUrl = baseUrl;

        if(!baseUrl.endsWith("/")) {
            auxBaseUrl = baseUrl + "/";
        }

        this.baseUrl = auxBaseUrl;

        createDefaultAdapter();
    }

    public <S> S createService(Class<S> serviceClass) {

        Validator.checkNull(serviceClass);

        return adapterBuilder
                .client(okBuilder.build())
                .build()
                .create(serviceClass);

    }

    protected Retrofit.Builder getAdapterBuilder() {
        return adapterBuilder;
    }

    protected void setAdapterBuilder(Retrofit.Builder adapterBuilder) {

        Validator.checkNull(adapterBuilder);

        this.adapterBuilder = adapterBuilder;
    }

    protected OkHttpClient.Builder getOkBuilder() {
        return okBuilder;
    }

    protected void setOkBuilder(OkHttpClient.Builder okBuilder) {

        Validator.checkNull(okBuilder);

        this.okBuilder = okBuilder;
    }

    protected void configureFromOkClient(OkHttpClient okClient) {

        Validator.checkNull(okClient);

        okBuilder = okClient.newBuilder();
    }

    private void createDefaultAdapter() {
        okBuilder = new OkHttpClient.Builder();

        // TODO EXTRACT TO DEBUG, YOU MUST REMOVE LOGS FOR SECURITY RESOSNS
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okBuilder.addInterceptor(interceptor);

        adapterBuilder = new Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create());
    }

}