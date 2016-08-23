package com.yingshiyuan.starpark.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yingshiyuan.starpark.R;
/**
 *@description:普通发票和专票的fragment
 *@author:袁东华
 *created at 2016/7/14 0014 上午 11:31
 */
public class InvoiceTabFragment extends Fragment {
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
    public static InvoiceTabFragment newInstance(int sectionNumber) {
        InvoiceTabFragment fragment = new InvoiceTabFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public InvoiceTabFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        position = getArguments().getInt(ARG_SECTION_NUMBER);
        View rootView = null;
        switch (position) {
            //进入普通发票界面
            case 1:
                rootView = InvoicePage.getInstance().getView();
                if (rootView == null) {
                    rootView = inflater.inflate(R.layout.fragment_invoice, container, false);
                     InvoicePage.getInstance().start(getActivity(), rootView);
                }

                break;
            //进入专票界面
            case 2:
                rootView = SpecialInvoicePage.getInstance().getView();
                if (rootView == null) {
                    rootView = inflater.inflate(R.layout.fragment_special_invoice, container, false);
                    SpecialInvoicePage.getInstance().start(getActivity(), rootView);
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

        }
    }


}
