package apps.xenione.com.demoloader.Domain;

/**
 * Created by Eugeni on 25/09/2016.
 */
public class Note {

    private String mTitle;

    private String mDescription;

    long mCreateDate;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Note(String title, String description) {
        mTitle = title;
        mDescription = description;
        mCreateDate = System.currentTimeMillis();
    }
}
