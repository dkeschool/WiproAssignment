package com.example.wiproassignment.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.wiproassignment.R;
import com.example.wiproassignment.databinding.ItemExerciseListBinding;
import com.example.wiproassignment.models.AboutCanadaListItemModel;

public class AboutCanadaListAdapter extends ListAdapter<AboutCanadaListItemModel, RecyclerView.ViewHolder> {

    public static final DiffUtil.ItemCallback<AboutCanadaListItemModel> aboutCanadaListItemModelItemCallback =
        new DiffUtil.ItemCallback<AboutCanadaListItemModel>() {
            @Override
            public boolean areItemsTheSame(@NonNull AboutCanadaListItemModel t1, @NonNull AboutCanadaListItemModel t2) {
                return t1.equals(t2);
            }
            @SuppressLint("DiffUtilEquals")
            @Override
            public boolean areContentsTheSame(@NonNull AboutCanadaListItemModel t1, @NonNull AboutCanadaListItemModel t2) {
                return t1.equals(t2);
            }
        };

    private Context mContext;

    /**
    * Parameterised Constructor
    * @param context  Context of the Activity
    * */
    public AboutCanadaListAdapter(Context context) {
        super(aboutCanadaListItemModelItemCallback);
        mContext = context;
    }

    @NonNull
    @Override
    public AboutCanadaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        ItemExerciseListBinding itemExerciseListBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
            R.layout.item_exercise_list, viewGroup, false);
        return new AboutCanadaViewHolder(itemExerciseListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        ((AboutCanadaViewHolder)viewHolder).bindDataWithView(position);
    }

    /*
    * View Holder Class of the RecyclerView item List
    * */
    public class AboutCanadaViewHolder extends RecyclerView.ViewHolder {

        private ItemExerciseListBinding itemExerciseListBinding;

        AboutCanadaViewHolder(ItemExerciseListBinding itemExerciseListBinding) {
            super(itemExerciseListBinding.getRoot());
            this.itemExerciseListBinding = itemExerciseListBinding;
        }

        /*
        * Method to bind list item data into view
        * */
        private void bindDataWithView(int position) {
            AboutCanadaListItemModel exercise = getItem(position);
            itemExerciseListBinding.setListitem(exercise);
        }
    }

}
