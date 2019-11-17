package ro.ase.stampcollector;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CollectorsRecyclerAdapter extends
        RecyclerView.Adapter<CollectorsRecyclerAdapter.ViewHolder> {

    private final Context mContext;
    private final List<User> mUsers;
    private final LayoutInflater mLayoutInflater;

    public CollectorsRecyclerAdapter(Context context, List<User> users) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.mUsers = users;
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
        User user = mUsers.get(position);
        holder.mUserName.setText(user.getName());
        holder.mUserCity.setText(user.getCity());
        holder.mCurrentPosition = position;

    }

    @Override
    public int getItemCount() {
        return mUsers.size();
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
//                    Intent intent = new Intent(mContext, StampActivity.class);
//                    intent.putExtra(StampActivity.STAMP_POSITION, mCurrentPosition);
//                    mContext.startActivity(intent);
                }
            });

        }
    }
}
