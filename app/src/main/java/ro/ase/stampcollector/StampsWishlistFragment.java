package ro.ase.stampcollector;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import java.util.List;

public class StampsWishlistFragment extends Fragment {



    private WishlistAdapter mWishlistAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_wishlist, container, false);

        ListView wishlist = root.findViewById(R.id.list_wishlist);

        List<NoteStampInfo> notes = DataManager.getInstance().getmNotes();



        mWishlistAdapter = new WishlistAdapter(getContext(), R.layout.item_wishlist, notes);

        wishlist.setAdapter( mWishlistAdapter);



        return root;
    }

    @Override
    public void onResume() {
        mWishlistAdapter.notifyDataSetChanged();
        super.onResume();
    }
}