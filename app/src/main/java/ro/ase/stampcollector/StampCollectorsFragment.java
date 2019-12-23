package ro.ase.stampcollector;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ro.ase.stampcollector.ui.home.HomeViewModel;


public class StampCollectorsFragment extends Fragment {

    private RecyclerView mCollectorsRecycler;
    private CollectorsRecyclerAdapter mCollectorsRecyclerAdapter;
    private HomeViewModel homeViewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_stamps_collectors, container, false);


        mCollectorsRecycler = root.findViewById(R.id.recycler_collectors2);
        mCollectorsRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mCollectorsRecyclerAdapter = new CollectorsRecyclerAdapter(getActivity(), Network.returnUsers());
        mCollectorsRecycler.setAdapter(mCollectorsRecyclerAdapter);

        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
    }
}

