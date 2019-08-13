package com.zeneo.abraj.Fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zeneo.abraj.Adapter.ViewPagerAdapter;
import com.zeneo.abraj.MainActivity;
import com.zeneo.abraj.R;
import com.zeneo.abraj.View.CustomViewPager;

/**
 * A simple {@link Fragment} subclass.
 */
public class HoroscopesFragment extends Fragment {


    public HoroscopesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_horoscopes, container, false);
    }


    String zodiak [] = {
            "الحمل",
            "الثور",
            "الجوزاء",
            "السرطان",
            "الأسد",
            "العذراء",
            "الميزان",
            "العقرب",
            "القوس",
            "الجدي",
            "الدلو",
            "الحوت"
    };

    String bd [] = {
            "19/4-21/3",
            "19/5-20/4",
            "20/6-21/5",
            "22/7-21/6",
            "22/8-23/7",
            "22/9-23/8",
            "22/10-23/9",
            "21/11-23/10",
            "21/12-22/11",
            "19/1-22/12",
            "18/2-20/1",
            "20/3-19/2",
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViewPager(view);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        int index = preferences.getInt("index",0);

        TextView title = view.findViewById(R.id.horo_title);
        TextView bd = view.findViewById(R.id.ahoro_bd);
        ImageView img = view.findViewById(R.id.horo_img);

        title.setText("البرج: برج "+zodiak[index]);
        img.setImageResource(MainActivity.img_res[index]);
        bd.setText("مواليد: "+this.bd[index]);


    }

    void setupViewPager (View view){


        CustomViewPager pager = view.findViewById(R.id.view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag("اليوم",HoroscopesTextFragment.newInstance("today"));
        adapter.addFrag("الغد",HoroscopesTextFragment.newInstance("tomorow"));
        adapter.addFrag("هذا الاسبوع",HoroscopesTextFragment.newInstance("thisWeek"));
        adapter.addFrag("هذا الشهر",HoroscopesTextFragment.newInstance("thisMonth"));

        pager.setAdapter(adapter);

        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(pager);


    }

}
