package com.example.korolkir.contactsdemo.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.korolkir.contactsdemo.R;
import com.example.korolkir.contactsdemo.model.Post;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by korolkir on 04.09.16.
 */
public class PostPageFragment extends Fragment {

    private static final int TITLE_LENGHT = 10;
    private List<TextView> viewList;

    @BindView(R.id.item_1)
    TextView item1;
    @BindView(R.id.item_2)
    TextView item2;
    @BindView(R.id.item_3)
    TextView item3;
    @BindView(R.id.item_4)
    TextView item4;
    @BindView(R.id.item_5)
    TextView item5;
    @BindView(R.id.item_6)
    TextView item6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.item_layout, container, false);
        ButterKnife.bind(this,rootView);
        viewList = new ArrayList<>();
        viewList.add(item1);
        viewList.add(item2);
        viewList.add(item3);
        viewList.add(item4);
        viewList.add(item5);
        viewList.add(item6);
        Bundle values = getArguments();
        if(!values.isEmpty()) {
            setValues(values);
        }
        return rootView;
    }

    private void setValues(Bundle values) {
        for (int index = 0; index < values.size()/2; index++) {
            viewList.get(index).setText(values.getString(String.valueOf(index))+ "\n\n" +
            values.getString(String.valueOf(index) + PostItemsPagerAdapter.TITLE_KEY).substring(0,0 + TITLE_LENGHT));
        }
    }
}


