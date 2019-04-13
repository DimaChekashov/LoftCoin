package foxsay.ru.loftcoin.data.api;

import foxsay.ru.loftcoin.data.api.model.RateResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface Api {

    String CONVERT = "USD";

    @GET("cryptocurrency/listings/latest")
    @Headers("X-CMC_PRO_API_KEY: e77ac188-4739-4915-9d6e-81621dc44faf")
    Observable<RateResponse> rates(@Query("convert") String convert);

}
