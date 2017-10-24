package com.xbc.douban.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by xiaobocui on 2017/7/17.
 */

public class RetrofitManager {
    private static final RetrofitManager ourInstance = new RetrofitManager();

    private MovieService movieService;
    private Retrofit retrofit;
    private OkHttpClient okHttpClient;

    public static RetrofitManager getInstance() {
        return ourInstance;
    }

    private RetrofitManager() {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor())
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        String param = "";
                        if (request.body() != null) {
                            Buffer buffer = new Buffer();
                            request.body().writeTo(buffer);
                            Charset charset = Charset.forName("utf-8");
                            MediaType contentType = request.body().contentType();
                            if (contentType != null) {
                                charset = contentType.charset(charset);
                            }
                            param = buffer.readString(charset);
                        }
//                        request = request.newBuilder()
//                                .addHeader("what", "the fuck")
//                                .build();
                        Log.e("request", "url=[" + request.url().toString() + "],param=[" + param + "],header=[" + request.headers().toString().trim() + "]");

                        Response response = chain.proceed(request);
                        //response.newBuilder().addHeader("Content-Type", "application/json; charset=UTF-8").build();
                        String resp = response.body().string();
                        response = response.newBuilder()
                                .body(ResponseBody.create(response.body().contentType(), resp))
                                .build();
//                        if (resp!=null) {
//                            throw new FileNotFoundException("hello world ~~");
//                        }
                        Log.e("response", ">>>url=[" + request.url().toString() + "],header=[" + request.headers().toString().trim() + "]\n>>>" + resp);
                        return response;
                    }
                })
                .build();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)

                .baseUrl("https://api.douban.com/v2/")
                .addConverterFactory(MyConverterFactory.create(new GsonBuilder().create()))
                //.addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build();
    }

    public MovieService getMovieService() {
        if (movieService == null) {
            movieService = retrofit.create(MovieService.class);
        }
        return movieService;
    }

    private static class MyConverterFactory extends Converter.Factory {

        public static MyConverterFactory create() {
            return create(new Gson());
        }


        @SuppressWarnings("ConstantConditions") // Guarding public API nullability.
        public static MyConverterFactory create(@NonNull Gson gson) {
            if (gson == null) throw new NullPointerException("gson == null");
            return new MyConverterFactory(gson);
        }

        private final Gson gson;

        private MyConverterFactory(Gson gson) {
            this.gson = gson;
        }

        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                                Retrofit retrofit) {
            TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
            return new GsonResponseBodyConverter<>(gson, adapter);
        }

        @Override
        public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                              Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
            TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
            return new GsonRequestBodyConverter<>(gson, adapter);
        }

        @Nullable
        @Override
        public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {

            return super.stringConverter(type, annotations, retrofit);
        }
    }


}

class GsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    GsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public RequestBody convert(T value) throws IOException {

        Buffer buffer = new Buffer();
        Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
        JsonWriter jsonWriter = gson.newJsonWriter(writer);
        adapter.write(jsonWriter, value);
        jsonWriter.close();
        RequestBody requestBody = RequestBody.create(MEDIA_TYPE, buffer.readByteString());
        Log.e("xbc", buffer.readByteString().toString());
        return requestBody;
    }
}

class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String resp = value.string();

        // Log.e("xbc", "convert(ResponseBody):"+resp);
        ResponseData respData = gson.fromJson(resp, ResponseData.class);
        try {
            if (respData.code != 0) {
                throw new NullPointerException("hello world");
            } else {
                return adapter.fromJson(resp);
            }
        } finally {
            value.close();
        }
//        JsonReader jsonReader = gson.newJsonReader(value.charStream());
//        try {
//            return adapter.read(jsonReader);
//        } finally {
//            value.close();
//        }
    }
}

class ResponseData<T> {
    public int code;
    public String msg;
    public T data;
}



