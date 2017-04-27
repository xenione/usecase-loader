package apps.xenione.com.demoloader.presentation.notes.notedetail;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import apps.xenione.com.demoloader.R;
import apps.xenione.com.demoloader.presentation.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;


public class NoteDetailFragment extends BaseFragment implements NoteDetailContract.View {

    public static final String TAG = "UserListFragment";

    private static final String UUID_ARG = "uuid_args";

    public static Fragment newInstance(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt(UUID_ARG, id);
        Fragment fr = new NoteDetailFragment();
        fr.setArguments(bundle);
        return fr;
    }

    @BindView(R.id.user_detail_info)
    TextView mInfo;

    @Inject
    NoteDetailContract.Presenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getNoteComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_detail, container, false);
        ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private String getUUID() {
        return (String) getArguments().get(UUID_ARG);
    }

    private void initViews() {
      //  mPresenter.setView(this);
      //  mPresenter.fetchUsers(getUUID());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
     //   mPresenter.setView(null);
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        // TODO show loading
    }

    @Override
    public void showUsers(NoteModelView note) {
        mInfo.setText(note.getInfo());
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }


}