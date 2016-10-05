package apps.xenione.com.demoloader.presentation.view.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import apps.xenione.com.demoloader.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Eugeni on 05/10/2016.
 */
public class FieldView extends FrameLayout {

    @Bind(R.id.title_field_view)
    TextView field;

    @Bind(R.id.value_field_view)
    TextView value;

    private String title;

    public FieldView(Context context) {
        this(context, null);
    }

    public FieldView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FieldView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FieldView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs, defStyleAttr, defStyleRes);
    }


    public void init(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        inflate(getContext(), R.layout.field_view, this);
        ButterKnife.bind(this, this);

        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.FieldView,
                defStyleAttr, defStyleRes);
        try {
            title = a.getString(R.styleable.FieldView_titleText);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        field.setText(title);
    }

    public String getText() {
        return value.getText().toString();
    }
}
