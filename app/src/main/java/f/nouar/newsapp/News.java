package f.nouar.newsapp;

class News {

    public String mSection;
    private String mTitle;
    private String mText;
    private String mAuthor;

    private String mUrl;


    public News(String mTitle, String mText, String mSection, String mAuthor,  String mUrl) {
        this.mTitle = mTitle;
        this.mText = mText;
        this.mSection = mSection;
        this.mAuthor = mAuthor;

        this.mUrl = mUrl;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmText() {
        return mText;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }

    public String getmSection() {
        return mSection;
    }

    public void setmSection(String mSection) {
        this.mSection = mSection;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }



    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}