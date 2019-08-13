package com.zeneo.abraj.Fragment;


import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.DocumentsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zeneo.abraj.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HoroscopesTextFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HoroscopesTextFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "title";


    // TODO: Rename and change types of parameters
    private String title;

    private TextView textView;
    private TextView titleView;
    private ProgressBar progressBar;

    int index;

    public HoroscopesTextFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment HoroscopesTextFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HoroscopesTextFragment newInstance(String param1) {
        HoroscopesTextFragment fragment = new HoroscopesTextFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_PARAM1);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_horoscopes_text, container, false);
        textView = view.findViewById(R.id.horoscopes_expectations);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.progress_bar);
        titleView = view.findViewById(R.id.horoscopes_title);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        index = preferences.getInt("index",0);

        if (title.equals("today"))
            new GetTomorow().execute();
        else if (title.equals("tomorow")) {
            new GetToday().execute();
        } else if (title.equals("thisWeek")) {
            new GetWeek().execute();
        } else if (title.equals("thisMonth")) {
            new GetMonth().execute();
        }




    }

    private String zodiac[] = {
            "Aries",
            "Taurus",
            "Gemini",
            "Cancer",
            "Leo",
            "Virgo",
            "Libra",
            "Scorpio",
            "Sagittarius",
            "Capricorn",
            "Aquarius",
            "Pisces"
    };

    public class GetToday extends AsyncTask<Void,Void,Void> {

        String url = "http://www.al-abraj.com/Abraj/Daily/General/" + zodiac[index] + "/Tomorrow";
        String content;
        String title;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            ((View)textView.getParent()).setVisibility(View.GONE);

        }


        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Document document = Jsoup.connect(url).get();
                content = document.getElementsByClass("HoroDailyText").last().text();
                title = document.getElementsByClass("HoroDailySubTitle").last().text();

                Log.e("element_td", content);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            try {
                textView.setText(content);
                titleView.setText(title);
                progressBar.setVisibility(View.GONE);
                ((View)textView.getParent()).setVisibility(View.VISIBLE);

            } catch (NullPointerException e) {
                e.printStackTrace();
            }


        }
    }


    public class GetTomorow extends AsyncTask<Void,Void,Void> {

        String url = "http://www.al-abraj.com/Abraj/Daily/General/" + zodiac[index] + "/Today";
        String content;
        String title;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            ((View)textView.getParent()).setVisibility(View.GONE);

        }


        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Document document = Jsoup.connect(url).get();
                content = document.getElementsByClass("HoroDailyText").last().text();
                title = document.getElementsByClass("HoroDailySubTitle").last().text();
                Log.e("element_td", content);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            try {
                textView.setText(content);
                titleView.setText(title);
                progressBar.setVisibility(View.GONE);
                ((View)textView.getParent()).setVisibility(View.VISIBLE);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }


        }
    }




    public class GetWeek extends AsyncTask<Void,Void,Void> {


        String url = "http://www.al-abraj.com/Abraj/Weekly/General/" + zodiac[index];
        String content;
        String title;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            ((View)textView.getParent()).setVisibility(View.GONE);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Document document = Jsoup.connect(url).get();
                content = document.getElementsByClass("HoroDailyText").last().text();
                title = document.getElementsByClass("HoroDailySubTitle").last().text();

                Log.e("element_td", content);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {
                textView.setText(content);
                titleView.setText("هذا الأسبوع");
                progressBar.setVisibility(View.GONE);
                ((View)textView.getParent()).setVisibility(View.VISIBLE);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }


        }
    }

    public class GetMonth extends AsyncTask<Void,Void,Void> {


        String url = "http://www.al-abraj.com/Abraj/Monthly/General/" + zodiac[index];
        String content;
        String title;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            ((View)textView.getParent()).setVisibility(View.GONE);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Document document = Jsoup.connect(url).get();
                content = document.getElementsByClass("HoroDailyText").last().text();
                title = document.getElementsByClass("HoroDailySubTitle").last().text();

                Log.e("element_td", content);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            try {
                textView.setText(content);
                titleView.setText("هذا الشهر");
                progressBar.setVisibility(View.GONE);
                ((View)textView.getParent()).setVisibility(View.VISIBLE);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }


        }
    }

}
