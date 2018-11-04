package e.mipro.business_card.NET;


import androidx.annotation.NonNull;
import e.mipro.business_card.DTO.NewsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EndPoint {
    @NonNull
    @GET("{category}.json")
    Call<NewsResponse> search(@Path("category") String category);

}