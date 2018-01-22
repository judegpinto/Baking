package com.example.jp0517.baking.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jp0517.baking.R;
import com.example.jp0517.baking.recipe.Step;

import org.w3c.dom.Text;

import java.sql.Array;
import java.util.ArrayList;

/**
 * Created by jp0517 on 1/19/18.
 */

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {

    private Context mContext;
    private ArrayList<Step> mSteps;

    public StepAdapter(Context context) {
        mContext = context;
    }

    public void setSteps(ArrayList<Step> steps) {
        mSteps = steps;
        notifyDataSetChanged();
    }

    @Override
    public StepAdapter.StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.step_card,parent,false);
        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepViewHolder holder, int position) {
        String shortDescription = mSteps.get(position).getShortDescription();
        holder.stepDescription.setText(shortDescription);
    }

    @Override
    public int getItemCount() {
        if(mSteps == null) {return 0;}
        return mSteps.size();
    }

    class StepViewHolder extends RecyclerView.ViewHolder {
        TextView stepDescription;
        CardView stepCard;

        StepViewHolder(View itemView) {
            super(itemView);
            stepCard = (CardView) itemView.findViewById(R.id.card_view_step);
            stepDescription = (TextView) itemView.findViewById(R.id.step_name);
            stepCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showStep(getAdapterPosition());
                }
            });
        }

    }

    private void showStep(int pos) {
        Toast.makeText(mContext, "Showing step: " + pos, Toast.LENGTH_LONG).show();
    }
}
