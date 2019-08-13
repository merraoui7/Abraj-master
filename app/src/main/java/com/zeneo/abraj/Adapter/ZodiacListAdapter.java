package com.zeneo.abraj.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zeneo.abraj.Model.Zodiak;
import com.zeneo.abraj.R;

import java.util.List;

public class ZodiacListAdapter extends RecyclerView.Adapter<ZodiacListAdapter.ViewHolder> {


    private Context context;
    private List<Zodiak> dataList;

    public ZodiacListAdapter(Context context, List<Zodiak> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.picker_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.pickerTxt.setText(dataList.get(position).getName());
        holder.date.setText(dataList.get(position).getDate());
        holder.logo.setImageResource(dataList.get(position).getImageRes());

        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        float dip = 70f;
        Resources r = context.getResources();
        float px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                r.getDisplayMetrics()
        );

        //if you need three fix imageview in width
        int devicewidth = (int) (displaymetrics.widthPixels / 3-px);
        holder.pickerTxt.setWidth(devicewidth);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder {
        TextView pickerTxt;
        TextView date;
        ImageView logo;

        public ViewHolder(View itemView) {
            super(itemView);
            pickerTxt = (TextView) itemView.findViewById(R.id.name);
            date = (TextView) itemView.findViewById(R.id.date);
            logo = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }


}
