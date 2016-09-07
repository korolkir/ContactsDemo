package com.example.korolkir.contactsdemo.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.korolkir.contactsdemo.R;
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

    @BindViews({R.id.item_1, R.id.item_2, R.id.item_3, R.id.item_4, R.id.item_5, R.id.item_6 })
    List<TextView> viewList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.item_layout, container, false);
        ButterKnife.bind(this,rootView);
        Bundle values = getArguments();
        if(!values.isEmpty()) {
            setValues(values);
            setOnClickListeners(values.size()/2);
        }
        return rootView;
    }

    private void setValues(Bundle values) {
        for (int index = 0; index < values.size()/2; index++) {
            viewList.get(index).setText(values.getString(String.valueOf(index))+ "\n\n" +
            values.getString(String.valueOf(index) + PostItemsPagerAdapter.TITLE_KEY).substring(0,0 + TITLE_LENGTH));
        }
    }

    private void setOnClickListeners(int number) {
        for(int i = 0; i < number; i++) {
            viewList.get(i).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        TextView view = (TextView) v;
        String postId = view.getText().toString().substring(view.getLayout()
                .getLineStart(0),view.getLayout().getLineEnd(0));
        presenter.onPostItemClicked(Integer.parseInt(postId.replaceAll("\\s+", "")));
    }

    public void attachPresenter(PostsPresenter presenter) {
        this.presenter = presenter;
    }
}


