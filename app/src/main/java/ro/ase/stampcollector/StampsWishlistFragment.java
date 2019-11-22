package ro.ase.stampcollector;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import ro.ase.stampcollector.ui.share.ShareViewModel;

public class StampsWishlistFragment extends Fragment {


    private ArrayAdapter<NoteStampInfo> mArrayAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_wishlist, container, false);

        ListView wishlist = root.findViewById(R.id.list_wishlist);

        List<NoteStampInfo> notes = DataManager.getInstance().getmNotes();

        mArrayAdapter = new ArrayAdapter<NoteStampInfo>(
                getContext(),android.R.layout.simple_list_item_1, notes);

        wishlist.setAdapter(mArrayAdapter);

        return root;
    }

    @Override
    public void onResume() {
        mArrayAdapter.notifyDataSetChanged();
        super.onResume();
    }
}