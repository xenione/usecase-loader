package apps.xenione.com.demoloader.presentation.view;

import android.content.Context;
import android.widget.Toast;

public class Notifier {

    private Context mContext;

    public Notifier(Context context) {
        mContext = context;
    }

    public void notify(String error) {
        Toast.makeText(mContext, error, Toast.LENGTH_LONG).show();
    }
}
