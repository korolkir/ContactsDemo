package com.example.korolkir.contactsdemo.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.korolkir.contactsdemo.R;
import com.example.korolkir.contactsdemo.model.Post;
import com.example.korolkir.contactsdemo.presenter.PostsPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * Created by korolkir on 04.09.16.
 */
public class PostPageFragment extends Fragment implements View.OnClickListener {

    private static final int TITLE_LENGTH = 10;
    private PostsPresenter presenter;

    @BindViews({R.id.id_1, R.id.id_2, R.id.id_3, R.id.id_4, R.id.id_5, R.id.id_6})
    List<TextView> viewList;
    @BindViews({R.id.title_1, R.id.title_2, R.id.title_3, R.id.title_4, R.id.title_5, R.id.title_6})
    List<TextView> titleList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.item_layout, container, false);
        ButterKnife.bind(this, rootView);
        Bundle values = getArguments();
        if (!values.isEmpty()) {
            setValues(values);
            setOnClickListeners(values.size() / 2);
        }
        return rootView;
    }

    private void setValues(Bundle values) {
        for (int index = 0; index < values.size() / 2; index++) {
            viewList.get(index).setText(values.getString(String.valueOf(index)) + "\n\n" +
                    values.getString(String.valueOf(index) + PostItemsPagerAdapter.TITLE_KEY).substring(0, TITLE_LENGTH)); //textview elipsize
        }
    }

    private void setOnClickListeners(int number) {
        for (int i = 0; i < number; i++) {
            viewList.get(i).setOnClickListener(this);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onClick(View v) {
        TextView view = (TextView) v;
        String postId = view.getText().toString().substring(view.getLayout()
                .getLineStart(0), view.getLayout().getLineEnd(0));
        presenter.onPostItemClicked(Integer.parseInt(postId.replaceAll("\\s+", "")));
    }

    public void attachPresenter(PostsPresenter presenter) {
        this.presenter = presenter;
    }

    public static PostPageFragment newInstance(ArrayList<Post> postList) {
        Bundle args = new Bundle();
        args.putParcelableArrayList("posts", postList );
        PostPageFragment fragment = new PostPageFragment();
        fragment.setArguments(args);
        return fragment;
    }
}


