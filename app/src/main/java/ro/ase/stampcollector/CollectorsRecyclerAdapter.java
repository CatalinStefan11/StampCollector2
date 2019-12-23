package ro.ase.stampcollector;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CollectorsRecyclerAdapter extends
        RecyclerView.Adapter<CollectorsRecyclerAdapter.ViewHolder> {

    private final Context mContext;
    private final ArrayList<Collector> mCollectors;
    private final LayoutInflater mLayoutInflater;

    public CollectorsRecyclerAdapter(Context context, ArrayList<Collector> collectors) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.mCollectors = collectors;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_collectors_list, parent,
                false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Collector collector = mCollectors.get(position);
        holder.mUserName.setText(collector.getName());
        holder.mUserCity.setText(collector.getCity());
        holder.mCurrentPosition = position;

    }

    @Override
    public int getItemCount() {
        return mCollectors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public final TextView mUserName;
        public final TextView mUserCity;
        public int mCurrentPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mUserName = (TextView)itemView.findViewById(R.id.text_name);
            mUserCity = (TextView)itemView.findViewById(R.id.text_city);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, CollectorActivity.class);
                    intent.putExtra(CollectorActivity.PARCELABLE_USER, mCollectors.get(mCurrentPosition));
                    mContext.startActivity(intent);
                }
            });

        }
    }
}
