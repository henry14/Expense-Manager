package org.grameenfoundation.expensemanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import org.grameenfoundation.expensemanager.Models.Expense;

import java.util.List;

/**
 * Created by henry on 3/18/16.
 */
public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.DisplayViewHolder>{

    private List<Expense> data;
    private Context context;

    //private Str

    public DisplayAdapter(List<Expense> data) {
        this.data = data;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onBindViewHolder(DisplayViewHolder mDisplayViewHolder, int i) {
        Expense mExpense = data.get(i);
        mDisplayViewHolder.mlabelTextView.setText(mExpense.getLabel());
        mDisplayViewHolder.mCostTextView.setText(mExpense.getCost().toString());
//        mDisplayViewHolder.mDateTextView.setText(mExpense.getDate());
        mDisplayViewHolder.mTitleTextView.setText("Expense");

    }

    @Override
    public DisplayViewHolder onCreateViewHolder(ViewGroup mViewGroup, int i) {
        View mView = LayoutInflater.from(mViewGroup.getContext()).inflate(R.layout.expense_instance, mViewGroup, false);
        return new DisplayViewHolder(mView);
    }

    public static class DisplayViewHolder extends RecyclerView.ViewHolder {

        private TextView mlabelTextView;
        private TextView mCostTextView;
//        private TextView mDateTextView;
        private TextView mTitleTextView;

        public DisplayViewHolder(View itemView) {
            super(itemView);

            mlabelTextView = (TextView)itemView.findViewById(R.id.txtItem);
            mCostTextView = (TextView)itemView.findViewById(R.id.txtCost);
//            mDateTextView = (TextView)itemView.findViewById(R.id.txtEmail);
            mTitleTextView = (TextView)itemView.findViewById(R.id.title);
        }
    }
}

    /*@Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Expense getItem(int position) {
        return data.get(position);
    }*/


   /* @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Expense rowItem = getItem(position);
        RecyclerView.ViewHolder mViewHolder = null;
        LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
//            mViewHolder = new RecyclerView.ViewHolder();
        }

        return null;
    }*/


