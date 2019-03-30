package foxsay.ru.loftcoin.data.api;

import foxsay.ru.loftcoin.data.api.model.RateResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface Api {

    String CONVERT = "USD,EUR,RUB";

    @GET("cryptocurrency/listings/latest")
    @Headers("X-CMC_PRO_API_KEY: 36f0739b-5474-45d4-a129-b50cce60aca9")
    Call<RateResponse> rates(@Query("convert") String convert);

}
