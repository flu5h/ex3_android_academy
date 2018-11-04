package e.mipro.business_card.DTO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import e.mipro.business_card.data.NewsItem;

public class NewsDTO {
    private List<MultimediaDTO> multimedia;

    @SerializedName("section")
    private String section;

    @SerializedName("title")
    private String title;

    @SerializedName("abstract")
    private String abstract1;

    @SerializedName("published_date")
    private String publishedDate;

    @SerializedName("url")
    private String url;

    public String getUrl() {
        return url;
    }

    public String getAbstract1() {
        return abstract1;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getSection() {
        return section;
    }

    public String getTitle() {
        return title;
    }

    public List<MultimediaDTO> getMultimedia() {
        return multimedia;
    }
    public String getImg() {
        return multimedia.get(0).getUrl();
    }

    public NewsItem convertToItems() {
        NewsItem item=new NewsItem(this.getTitle(),this.getUrl(),this.getSection(),this.getPublishedDate(),this.getAbstract1(),this.getAbstract1());

        return item;
    }
}
