package com.depsa.controldeobra.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.depsa.controldeobra.R;
import com.depsa.controldeobra.bean.MenuItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Cristian Ramírez on 11/07/17.
 * Email: cristian.ramirez@mayan-tech.com
 * Copyright (c) 2017 Mayantech
 */
public class MenuAdapter
        extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private Context mContext;
    private List<MenuItem> mListaMenuItems;

    public interface OnItemClickListener {
        void onItemClick(ViewHolder item, int position);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public OnItemClickListener getOnItemClickListener() {
        return listener;
    }

    public MenuAdapter(List<MenuItem> items, Context context) {
        this.mListaMenuItems = items;
        this.mContext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        @BindView(R.id.imgLogoItem)
        ImageView mLogoItem;
        @BindView(R.id.txtTitle)
        TextView mTitle;
        @BindView(R.id.btnManual)
        ImageButton mBtnManual;
        @BindView(R.id.btnScan)
        ImageButton mBtnScan;

        private MenuAdapter parent = null;

        public ViewHolder(View v, MenuAdapter parent) {
            super(v);
            v.setOnClickListener(this);
            this.parent = parent;
            ButterKnife.bind(this, v);
        }

        @Override
        public void onClick(View v) {
            final OnItemClickListener listener = parent.getOnItemClickListener();
            if (listener != null) {
                listener.onItemClick(this, getAdapterPosition());
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_menu_items, parent, false);
        return new ViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MenuItem items = mListaMenuItems.get(position);
        Picasso.with(mContext)
                .load(items.getResourceId())
                .into(holder.mLogoItem);
        holder.mTitle.setText(items.getDescripcion());
    }

    @Override
    public int getItemCount() {
        return mListaMenuItems.size();
    }
}
