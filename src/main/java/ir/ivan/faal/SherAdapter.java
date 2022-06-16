package ir.ivan.faal;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;
public class SherAdapter extends RecyclerView.Adapter<SherAdapter.MyViewHolder>
        implements Filterable {
    private Context context;
    private List<Sher> sherList;
    private List<Sher> sherListFiltered;
    private PoetAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView birthdate;
        public TextView deathdate;
        public TextView   birthplace;
        public ImageView thumbnail;



        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.txtname);
            birthdate = view.findViewById(R.id.txtbirth);
            deathdate = view.findViewById(R.id.txtdeath);
            birthplace = view.findViewById(R.id.txtbirthplace);
            thumbnail = view.findViewById(R.id.imgpoet);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onPoetSelected(sherListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }


    public SherAdapter(Context context, List<Sher> poettList, PoetAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.sherList = sherList;
        this.sherListFiltered = sherList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.poets_row_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Sher poets = sherListFiltered.get(position);
        holder.name.setText(poets.getName());
        holder.birthdate.setText(poets.getBirthdate());
        holder.deathdate.setText(poets.getDeathdate());
        holder.birthplace.setText(poets.getBirthplace());

        Glide.with(context)
                .load(poets.getImgpoet())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return sherListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    sherListFiltered = sherList;
                } else {
                    List<Sher> filteredList = new ArrayList<>();
                    for (Sher row : sherList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getBirthplace().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    sherListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = sherListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                sherListFiltered = (ArrayList<Sher>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface PoetAdapterListener {
        void onPoetSelected(Sher sher2);
    }
}