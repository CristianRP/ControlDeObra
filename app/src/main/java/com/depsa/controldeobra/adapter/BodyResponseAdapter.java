package com.depsa.controldeobra.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class BodyResponseAdapter
        extends RecyclerView.Adapter<BodyResponseAdapter.ViewHolder> {

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

    public BodyResponseAdapter(List<BodyResponse> items, Context context) {
        this.mListaBodyResponse = items;
        this.mContext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        /*@BindView(R.id.imgLogoGenerico)
        ImageView imgLogoGenerico;*/
        @BindView(R.id.txtCodigo)
        TextView txtCodigo;
        @BindView(R.id.txtNombreBody)
        TextView txtNombreBody;

        private BodyResponseAdapter parent = null;

        public ViewHolder(View v, BodyResponseAdapter parent) {
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
                .inflate(R.layout.layout_item_bodyresponse, parent, false);
        return new ViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BodyResponse item = mListaBodyResponse.get(position);
        /*  setImageWithPicasso(R.drawable.ic_proyecto, holder);
        } else if (item.getTipo().equals("MODELO")) {
            setImageWithPicasso(R.drawable.ic_modelo, holder);
        } else if (item.getTipo().equals("OBRAS")) {
            setImageWithPicasso(R.drawable.ic_obra, holder);
        } else if (item.getTipo().equals("ACTIVIDAD")) {
            setImageWithPicasso(R.drawable.ic_actividad, holder);
        } else if (item.getTipo().equals("TAREA")) {
            setImageWithPicasso(R.drawable.ic_tarea, holder);
        } else {
            setImageWithPicasso(R.drawable.logo_depsa, holder);
        }*/
        holder.txtCodigo.setText(item.getCodigo());
        if (!item.getNombre().isEmpty()) {
            holder.txtNombreBody.setText(item.getNombre());
        } else {
            holder.txtNombreBody.setText("N/A");
        }
    }

    /*private void setImageWithPicasso(int drawable, ViewHolder holder) {
        Picasso.with(mContext)
                .load(drawable)
                .into(holder.imgLogoGenerico);
    }*/

    @Override
    public int getItemCount() {
        return mListaBodyResponse.size();
    }
}