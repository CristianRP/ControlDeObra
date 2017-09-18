package com.depsa.controldeobra.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        TextView txtDescripcion;
        @BindView(R.id.txtCantidad)
        EditText txtCantidad;
        @BindView(R.id.txtUnidad)
        EditText txtUnidad;
        @BindView(R.id.txtBodega)
        EditText txtBodega;
        @BindView(R.id.txtDespacho)
        EditText txtDespacho;
        @BindView(R.id.checkBoxSelect)
        CheckBox checkBoxSelect;

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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final BodyResponse item = mListaBodyResponse.get(position);
        holder.txtDescripcion.setText(item.getNombre());
        holder.txtCantidad.setText(String.valueOf(item.getCantidad()), TextView.BufferType.EDITABLE);
        holder.txtUnidad.setText(String.valueOf(item.getEsUnidad()), TextView.BufferType.EDITABLE);
        holder.txtBodega.setText(String.valueOf(item.getBodega()), TextView.BufferType.EDITABLE);
        //holder.txtDespacho.setText(String.valueOf(item.getDespacho()), TextView.BufferType.EDITABLE)\
        item.setIncluir("FALSE");
        holder.checkBoxSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    item.setIncluir("YES");
                } else {
                    item.setIncluir("FALSE");
                }
            }
        });
        holder.txtDespacho.setTag(position);
        holder.txtDespacho.setText(String.valueOf(item.getDespacho()), TextView.BufferType.EDITABLE);
        Log.e("test", " " + holder.txtDespacho.getTag());
        holder.txtDespacho.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    item.setDespacho(Integer.parseInt(s.toString()));
                    if (!item.calcularPorcentaje()) {
                        Toast.makeText(mContext, "El despacho sobrepasa el 100% revisa los datos!", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListaBodyResponse.size();
    }

}