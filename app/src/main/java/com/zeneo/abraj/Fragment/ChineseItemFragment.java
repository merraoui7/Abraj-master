package com.zeneo.abraj.Fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zeneo.abraj.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChineseItemFragment extends Fragment {


    public ChineseItemFragment() {
        // Required empty public constructor
    }

    int num;
    LinearLayout container;
    TextView title;
    ProgressBar progressBar;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chinese_item, container, false);
    }

    public static ChineseItemFragment newInstance(int num,String title) {

        Bundle args = new Bundle();
        args.putInt("num",num);
        args.putString("title",title);
        ChineseItemFragment fragment = new ChineseItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        num = getArguments().getInt("num")+1;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        title = view.findViewById(R.id.title);
        title.setText("خصائص برج "+ getArguments().getString("title"));

        container = view.findViewById(R.id.container);

        progressBar = view.findViewById(R.id.progress_bar);

        new GetData().execute();

    }

    public class GetData extends AsyncTask<Void,Void,Void> {


        String url = "https://www.elabraj.net/ar/sectionHoroscope/itemContent/"+num;
        Elements content;


        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Document document = Jsoup.connect(url).get();
                content = document.getElementsByClass("article-body").get(0).children();

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
                        textView.setTextSize(19f);
                        textView.setPadding(0,10,0,10);
                        textView.setTextColor(getResources().getColor(android.R.color.black));
                        container.addView(textView);
                    }else if (content.get(i).getElementsByTag("p").size()>0){
                        TextView textView = new TextView(getContext());
                        textView.setText(content.get(i).getElementsByTag("p").text());
                        textView.setTextColor(getResources().getColor(android.R.color.darker_gray));
                        textView.setTextSize(16f);
                        container.addView(textView);
                    }else if (content.get(i).getElementsByTag("ul").size()>0){
                        Elements subElements = content.get(i).getElementsByTag("ul").get(0).getElementsByTag("li");

                        for (int y = 0 ; y < subElements.size() ; y++ ) {
                            Log.e("Tag",content.get(i).text());
                            TextView textView = new TextView(getContext());
                            textView.setPadding(10,0,10,0);
                            textView.setText(subElements.get(y).text());
                            textView.setTextColor(getResources().getColor(android.R.color.darker_gray));
                            textView.setTextSize(14f);
                            container.addView(textView);

                        }

                    }
                }

                ((View)container.getParent()).setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);

            } catch (NullPointerException e) {
                e.printStackTrace();
            }

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ((View)container.getParent()).setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }
    }




}
