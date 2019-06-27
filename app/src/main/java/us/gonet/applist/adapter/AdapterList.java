package us.gonet.applist.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.LinkedList;

import us.gonet.applist.R;
import us.gonet.applist.model.ModelList;

public class AdapterList extends Adapter<AdapterList.ViewHolder> {

    private LinkedList<ModelList> list;
    private CheckList listener;

    public AdapterList(LinkedList<ModelList> list, CheckList listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_detail, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        viewHolder.mTextView.setText(list.get(i).getName());
        viewHolder.bind(listener);
        viewHolder.mCheckBox.setChecked(list.get(i).isChecked());
        Collections.sort(list);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mTextView;
        ImageView mImageView;
        CheckBox mCheckBox;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.item_texto);
            mImageView = itemView.findViewById(R.id.borrar);
            mCheckBox = itemView.findViewById(R.id.checkbox);
            mImageView.setOnClickListener(this);

        }

        void bind(final CheckList listener) {

            mCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean valor = list.get(getAdapterPosition()).isChecked();
                    if (!valor) {
                        list.get(getAdapterPosition()).setChecked(false);
                        listener.click(getAdapterPosition());
                        AdapterList.this.notifyDataSetChanged();
                    } else {
                        mCheckBox.setChecked(false);
                        list.get(getAdapterPosition()).setChecked(true);
                        listener.click(getAdapterPosition());
                    }
                }
            });
        }

        @Override
        public void onClick(View v) {
            if (v == mImageView) {
                list.remove(getAdapterPosition());
                AdapterList.this.notifyDataSetChanged();
            }
        }
    }

    public interface CheckList {
        void click(int position);
    }
}
