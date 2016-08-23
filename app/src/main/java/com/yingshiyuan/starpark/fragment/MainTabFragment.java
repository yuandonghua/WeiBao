package com.yingshiyuan.starpark.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yingshiyuan.starpark.R;

/**
 * Created by Administrator on 2016/6/22 0022.
 */

public class MainTabFragment extends Fragment {
private int position;
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static MainTabFragment newInstance(int sectionNumber) {
        MainTabFragment fragment = new MainTabFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public MainTabFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        position = getArguments().getInt(ARG_SECTION_NUMBER);
        View rootView = null;
        switch (position) {
            //进入蜂巢界面
            case 1:
                rootView = HoneycombPage.getInstance().getView();
                if (rootView == null) {
                    rootView = inflater.inflate(R.layout.honeycomb_layout, container, false);
                     HoneycombPage.getInstance().start(getActivity(), rootView);
                }

                break;
            case 2:
                rootView = WorkerOrderPage.getInstance().getView();
                if (rootView == null) {
                    rootView = inflater.inflate(R.layout.workerorder_layout, container, false);
                    WorkerOrderPage.getInstance().start(getActivity(), rootView);
                }
                break;
            case 3:
                rootView = MePage.getInstance().getView();
                if (rootView == null) {
                    rootView = inflater.inflate(R.layout.me_layout, container, false);
                    MePage.getInstance().start(getActivity(), rootView);
                }
                break;
        }


        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        switch (position) {
            //进入蜂巢界面
            case 1:

                break;
            case 2:

                break;
            case 3:
                View rootView = MePage.getInstance().getView();
                if (rootView != null) {
                    MePage.getInstance().refresh();
                }
                break;
        }
    }


}
