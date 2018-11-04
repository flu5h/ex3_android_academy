package e.mipro.business_card.data;

public class NewsItem {

  private final String title;
  private final String imageUrl;
  private final String category;
  private final String publishDate;
  private final String previewText;
  private final String fullText;

  public NewsItem(String title, String imageUrl, String category, String publishDate, String previewText, String fullText) {
    this.title = title;
    this.imageUrl = imageUrl;
    this.category = category;
    this.publishDate = publishDate;
    this.previewText = previewText;
    this.fullText = fullText;
  }

  public String getTitle() {
    return title;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public String getCategory() {
    return category;
  }

  public String getPublishDate() {
    return publishDate;
  }

  public String getPreviewText() {
    return previewText;
  }

  public String getFullText() {
    return fullText;

  }
  public void setTitle (String s){

  }
  public void setFullText (String s){

  }
  public void setPublishDate (String s){

  }


}
