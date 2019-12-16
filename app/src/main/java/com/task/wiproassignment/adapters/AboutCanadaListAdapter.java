package com.task.wiproassignment.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.task.wiproassignment.R;
import com.task.wiproassignment.databinding.ItemExerciseListBinding;
import com.task.wiproassignment.models.AboutCanadaListItemModel;

import java.util.Objects;

public class AboutCanadaListAdapter extends ListAdapter<AboutCanadaListItemModel, RecyclerView.ViewHolder> {

    private static final DiffUtil.ItemCallback<AboutCanadaListItemModel> aboutCanadaListItemModelItemCallback =
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
        ((AboutCanadaViewHolder) Objects.requireNonNull(viewHolder)).bindDataWithView(position);
    }

    /*
    * View Holder Class of the RecyclerView item List
    * */
    public class AboutCanadaViewHolder extends RecyclerView.ViewHolder {

        private ItemExerciseListBinding mItemExerciseListBinding;

        AboutCanadaViewHolder(ItemExerciseListBinding itemExerciseListBinding) {
            super(itemExerciseListBinding.getRoot());
            this.mItemExerciseListBinding = itemExerciseListBinding;
        }

        /*
        * Method to bind list item data into view
        * */
        private void bindDataWithView(int position) {
            AboutCanadaListItemModel item = getItem(position);

            mItemExerciseListBinding.setListitem(item);

            if (item.getImageHref() == null || item.getImageHref().trim().isEmpty())
                mItemExerciseListBinding.ivHref.setImageResource(R.drawable.placeholder_iamge);
            else {
                Glide.with(mContext).load(item.getImageHref().replace("http","https"))
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .placeholder(R.drawable.placeholder_iamge)
                        .into(mItemExerciseListBinding.ivHref);
            }

        }
    }

}
