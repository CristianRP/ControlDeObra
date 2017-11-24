package com.depsa.controldeobra.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.depsa.controldeobra.R;
import com.depsa.controldeobra.bean.DetalleSolicitudResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cristian on 9/23/17.
 */

public class SolicitudDetalleResponseAdapter
        extends RecyclerView.Adapter<SolicitudDetalleResponseAdapter.ViewHolder> {


    private Context mContext;
    private List<DetalleSolicitudResponse> mListaDetalleSolicitudResponse;

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

    public SolicitudDetalleResponseAdapter(List<DetalleSolicitudResponse> items, Context context) {
        this.mListaDetalleSolicitudResponse = items;
        this.mContext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        @BindView(R.id.txtDescripcion)
        TextView mDescripcion;
        @BindView(R.id.txtDespacho)
        EditText mDespacho;
        @BindView(R.id.txtCantidad)
        EditText mBodega;
        @BindView(R.id.txtSaldo)
        EditText mSaldo;
        @BindView(R.id.txtCod)
        EditText mCod;


        private SolicitudDetalleResponseAdapter parent = null;

        public ViewHolder(View v, SolicitudDetalleResponseAdapter parent) {
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
                .inflate(R.layout.layout_item_material_detalle, parent, false);
        return new ViewHolder(v, this);
    }

    DetalleSolicitudResponse item2 = null;
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final DetalleSolicitudResponse item = mListaDetalleSolicitudResponse.get(position);

        try {
            if (item.getNombre().equals("")) {
                holder.mDescripcion.setText("");
            } else {
                holder.mDescripcion.setText(item.getNombre());
            }
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
        holder.mBodega.setText(String.valueOf(item.getBodega()));
        holder.mDespacho.setTag(position);
        holder.mDespacho.setText(String.valueOf(item.getDespacho()), TextView.BufferType.EDITABLE);
        holder.mSaldo.setText(String.valueOf(item.getSaldo()));
        Log.e("test", " " + holder.mDespacho.getTag());
        holder.mCod.setText(item.getCodigo());
        holder.mDespacho.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    item.setDespacho(Double.parseDouble(s.toString()));
                    if (!item.calcularSaldo()) {
                        Toast.makeText(mContext, "El despacho sobrepasa el valor de la existencia en bodega revisa los datos!", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        holder.mDespacho.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    for (int i = 0; i < mListaDetalleSolicitudResponse.size(); i++) {
                        if (mListaDetalleSolicitudResponse.get(i).getCodigo().equals(holder.mCod.getText().toString())) {
                            item2 = mListaDetalleSolicitudResponse.get(i);
                        }
                    }
                    //item.setDespacho(Double.parseDouble(holder.mDespacho.getText().toString().trim()));
                    final int position = (Integer) view.getTag();
                    final EditText etDespacho = (EditText) view;
                    item2.setDespacho(Double.parseDouble(etDespacho.getText().toString()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListaDetalleSolicitudResponse.size();
    }
}
