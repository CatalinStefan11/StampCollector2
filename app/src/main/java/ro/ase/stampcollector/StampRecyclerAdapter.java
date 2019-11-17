package ro.ase.stampcollector;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StampRecyclerAdapter extends RecyclerView.Adapter<StampRecyclerAdapter.ViewHolder> {

    private final Context mContext;
    private final List<StampInfo> mStamps;
    private final LayoutInflater mLayoutInflater;

    public StampRecyclerAdapter(Context context, List<StampInfo> stamps) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.mStamps = stamps;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StampInfo stamp = mStamps.get(position);
        holder.mStampTitle.setText(stamp.getTitle());
        holder.mStampDescription.setText(stamp.getColor());
        holder.mCurrentPosition = position;

    }

    @Override
    public int getItemCount() {
        return mStamps.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public final TextView mStampTitle;
        public final TextView mStampDescription;
        public int mCurrentPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mStampTitle = (TextView)itemView.findViewById(R.id.text_name);
            mStampDescription = (TextView)itemView.findViewById(R.id.text_city);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, StampActivity.class);
                    intent.putExtra(StampActivity.STAMP_POSITION, mCurrentPosition);
                    mContext.startActivity(intent);
                }
            });

        }
    }
}
