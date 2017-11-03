package id.putraprima.installretrofit.services;

import id.putraprima.installretrofit.models.ChuckNorrisQuote;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by putraprima on 03/11/17.
 */

public interface ChuckService {
    @GET("jokes/random")
    Call<ChuckNorrisQuote> getQuote();
}
