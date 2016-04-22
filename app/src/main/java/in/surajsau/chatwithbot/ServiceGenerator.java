package in.surajsau.chatwithbot;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by suraj on 22/4/16.
 */
public class ServiceGenerator {

    public static final String BASE_URL = "http://api.program-o.com/";

    private static Retrofit.Builder builder = new Retrofit.Builder()
                                        .baseUrl(BASE_URL)
                                        .addConverterFactory(JacksonConverterFactory.create())
                                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

    public static <Res> Res createService(Class<Res> serviceClass) {
        Retrofit retrofit = builder.build();
        return retrofit.create(serviceClass);
    }
}
