package apps.xenione.com.demoloader.data;


public class Note {

    int mId;

    String mTitle;

    String mShortDescription;

    String mBody;

    String mAuthor;

    long mPublishDate;

    boolean mFavorite;

    private Note(Builder builder) {
        mId = builder.mId;
        mTitle = builder.mTitle;
        mShortDescription = builder.mShortDescription;
        mBody = builder.mBody;
        mAuthor = builder.mAuthor;
        mPublishDate = builder.mPublishDate;
        mFavorite = false;
    }

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getShortDescription() {
        return mShortDescription;
    }

    public String getBody() {
        return mBody;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public long getPublishDate() {
        return mPublishDate;
    }

    public boolean isFavorite() {
        return mFavorite;
    }


    public static class Builder {

        private int mId;

        private String mTitle;

        private String mShortDescription;

        private String mBody;

        private String mAuthor;

        private long mPublishDate;

        private boolean mFavorite;


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

        public Builder withPublshDate(long publishDate) {
            this.mPublishDate = publishDate;
            return this;
        }

        public Note build() {
            return new Note(this);
        }
    }
}
