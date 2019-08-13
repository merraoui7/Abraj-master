package com.zeneo.abraj.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zeneo.abraj.Adapter.ViewPagerAdapter;
import com.zeneo.abraj.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChineseFragment extends Fragment {


    public ChineseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chinese, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPager pager = view.findViewById(R.id.view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag("الفأر",ChineseItemFragment.newInstance(0,"الفأر"));
        adapter.addFrag("الثور",ChineseItemFragment.newInstance(1,"الثور"));
        adapter.addFrag("النمر",ChineseItemFragment.newInstance(2,"النمر"));
        adapter.addFrag("الارنب",ChineseItemFragment.newInstance(3,"الرنب"));
        adapter.addFrag("التنبن",ChineseItemFragment.newInstance(4,"التنين"));
        adapter.addFrag("الثعبان",ChineseItemFragment.newInstance(5,"اثعبان"));
        adapter.addFrag("الحصان",ChineseItemFragment.newInstance(6,"الحصان"));
        adapter.addFrag("العنزة",ChineseItemFragment.newInstance(7,"العننة"));
        adapter.addFrag("القرد",ChineseItemFragment.newInstance(8,"القرد"));
        adapter.addFrag("الديك",ChineseItemFragment.newInstance(9,"الديك"));
        adapter.addFrag("الكلب",ChineseItemFragment.newInstance(10,"الخشب"));
        adapter.addFrag("الخنزير",ChineseItemFragment.newInstance(11,"الخزير"));

        pager.setAdapter(adapter);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(pager);


    }
}
