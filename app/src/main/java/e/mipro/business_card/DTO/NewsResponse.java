package e.mipro.business_card.DTO;

import java.util.List;

import androidx.annotation.Nullable;

public class NewsResponse {
    private List<NewsDTO> results;

    @Nullable
    public List<NewsDTO> getData() {
        return results;
    }
}
