package com.zeneo.abraj.Fragment;


import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zeneo.abraj.MainActivity;
import com.zeneo.abraj.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZodiacFeaturesFragment extends Fragment {


    public ZodiacFeaturesFragment() {
        // Required empty public constructor
    }

    LinearLayout textContainer;
    int index;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_zodiac_features, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        index = preferences.getInt("index",0);

        textContainer = view.findViewById(R.id.container);

        TextView title = view.findViewById(R.id.horo_title);
        ImageView img = view.findViewById(R.id.horo_img);
        title.setText("برج "+MainActivity.zodiak[index]);
        img.setImageResource(MainActivity.img_res[index]);

        new GetData().execute();


    }

    public class GetData extends AsyncTask<Void,Void,Void> {



        String url = "http://www.elabraj.net/ar/horoscope/content/5?content_id="+(8+index*9);
        Elements content;



        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Document document = Jsoup.connect(url).get();
                content = document.getElementsByClass("horoscope-content-body").get(0).children();

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
                for (int i = 0 ; i < content.size() ; i++ ) {
                    if(content.get(i).getElementsByTag("h3").size()>0){
                        TextView textView = new TextView(getContext());
                        textView.setText(content.get(i).getElementsByTag("h3").text());
                        textView.setTextSize(20f);
                        textView.setPadding(0,10,0,10);
                        textView.setTextColor(getResources().getColor(android.R.color.black));
                        textContainer.addView(textView);
                    }else if (content.get(i).getElementsByTag("p").size()>0){
                        TextView textView = new TextView(getContext());
                        textView.setText(content.get(i).getElementsByTag("p").text());
                        textView.setTextColor(getResources().getColor(android.R.color.darker_gray));
                        textView.setTextSize(15f);
                        textView.setPadding(0,5,0,5);
                        textContainer.addView(textView);
                    }
                }

            } catch (NullPointerException e) {
                e.printStackTrace();
            }


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }
    }


}
