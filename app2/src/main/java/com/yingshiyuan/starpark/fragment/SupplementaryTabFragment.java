package com.yingshiyuan.starpark.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yingshiyuan.starpark.R;
/**
 *@description:补充公司资料的Fragment
 *@author:袁东华
 *created at 2016/7/29 0029 下午 2:03
 */
public class SupplementaryTabFragment extends Fragment {
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
    public static SupplementaryTabFragment newInstance(int sectionNumber) {
        SupplementaryTabFragment fragment = new SupplementaryTabFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public SupplementaryTabFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        position = getArguments().getInt(ARG_SECTION_NUMBER);
        View rootView = null;
        switch (position) {
            //进入法人界面
            case 1:
                rootView = JuridicalPersonPage.getInstance().getView();
                if (rootView == null) {
                    rootView = inflater.inflate(R.layout.fragment_juridical_person, container, false);
                    JuridicalPersonPage.getInstance().start(getActivity(), rootView);
                }

                break;
            //进入股东界面
            case 2:
                rootView = ShareHolderPage.getInstance().getView();
                if (rootView == null) {
                    rootView = inflater.inflate(R.layout.fragment_shareholder, container, false);
                    ShareHolderPage.getInstance().start(getActivity(), rootView);
                }
                break;
            //进入职工界面
            case 3:
                rootView = WorkerPage.getInstance().getView();
                if (rootView == null) {
                    rootView = inflater.inflate(R.layout.fragment_shareholder, container, false);
                    WorkerPage.getInstance().start(getActivity(), rootView);
                }
                break;
        }


        return rootView;
    }





}
