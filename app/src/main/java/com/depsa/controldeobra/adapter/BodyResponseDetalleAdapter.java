package com.depsa.controldeobra.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.depsa.controldeobra.R;
import com.depsa.controldeobra.bean.BodyResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Cristian Ramírez on 24/07/2017.
 * Grupo Rosul
 * cristianramirezgt@gmail.com
 */


public class BodyResponseDetalleAdapter
        extends RecyclerView.Adapter<BodyResponseDetalleAdapter.ViewHolder> {


    private Context mContext;
    private List<BodyResponse> mListaBodyResponse;

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

    public BodyResponseDetalleAdapter(List<BodyResponse> items, Context context) {
        this.mListaBodyResponse = items;
        this.mContext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        @BindView(R.id.txtDescripcion)
        EditText txtDescripcion;
        @BindView(R.id.txtCantidad)
        EditText txtCantidad;
        @BindView(R.id.txtUnidad)
        EditText txtUnidad;

        private BodyResponseDetalleAdapter parent = null;

        public ViewHolder(View v, BodyResponseDetalleAdapter parent) {
            super(v);
            v.setOnClickListener(this);
            this.parent = parent;
            ButterKnife.bind(this, v);
        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
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
                .inflate(R.layout.layout_item_material, parent, false);
        return new ViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BodyResponse item = mListaBodyResponse.get(position);
        holder.txtDescripcion.setText(item.getNombre(), TextView.BufferType.EDITABLE);
        holder.txtCantidad.setText(item.getCantidad(), TextView.BufferType.EDITABLE);
        holder.txtUnidad.setText(item.getEsUnidad(), TextView.BufferType.EDITABLE);
    }

    @Override
    public int getItemCount() {
        return mListaBodyResponse.size();
    }
}