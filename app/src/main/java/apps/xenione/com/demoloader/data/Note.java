package apps.xenione.com.demoloader.data;


public class Note {

    private int mId;

    private String mTitle;

    private String mShortDescription;

    private String mBody;

    private String mAuthor;

    private long mPublishDate;

    private boolean mFavorite;

    private Note(Builder builder) {
        mId = builder.mId;
        mTitle = builder.mTitle;
        mShortDescription = builder.mShortDescription;
        mBody = builder.mBody;
        mAuthor = builder.mAuthor;
        mPublishDate = builder.mPublishDate;
        mFavorite = builder.mFavorite;
    }

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    void setTitle(String title) {
        mTitle = title;
    }

    public String getShortDescription() {
        return mShortDescription;
    }

    void setShortDescription(String shortDescription) {
        mShortDescription = shortDescription;
    }

    public String getBody() {
        return mBody;
    }

    void setBody(String body) {
        mBody = body;
    }

    public String getAuthor() {
        return mAuthor;
    }

    void setAuthor(String author) {
        mAuthor = author;
    }

    public long getPublishDate() {
        return mPublishDate;
    }

    void setPublishDate(int date) {
        mPublishDate = date;
    }

    public boolean isFavorite() {
        return mFavorite;
    }

    void setFavorite(boolean favorite) {
        mFavorite = favorite;
    }

    public static class Builder {

        private int mId;

        private String mTitle;

        private String mShortDescription;

        private String mBody;

        private String mAuthor;

        private long mPublishDate;

        private boolean mFavorite = false;


        public Builder withId(int id) {
            this.mId = id;
            return this;
        }

        public Builder withTitle(String title) {
            this.mTitle = title;
            return this;
        }

        public Builder withAuthor(String author) {
            this.mAuthor = author;
            return this;
        }

        public Builder withShortDescription(String shortDescription) {
            this.mShortDescription = shortDescription;
            return this;
        }

        public Builder withBody(String body) {
            this.mBody = body;
            return this;
        }

        public Builder withPublishDate(long publishDate) {
            this.mPublishDate = publishDate;
            return this;
        }

        public Builder withFavorite(boolean favorite) {
            this.mFavorite = favorite;
            return this;
        }

        public Note build() {
            return new Note(this);
        }
    }
}
