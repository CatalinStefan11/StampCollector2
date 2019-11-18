package ro.ase.stampcollector.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ro.ase.stampcollector.CollectorsRecyclerAdapter;
import ro.ase.stampcollector.Network;
import ro.ase.stampcollector.R;
import ro.ase.stampcollector.ui.tools.ToolsFragment;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private RecyclerView mCollectorsRecycler;
    private CollectorsRecyclerAdapter mCollectorsRecyclerAdapter;
    private GridLayoutManager mGridLayoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        galleryViewModel =
//                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_stamps_collectors, container, false);
//        final TextView textView = root.findViewById(R.id.text_gallery);
//        galleryViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });


        mCollectorsRecycler = root.findViewById(R.id.recycler_collectors2);
        mCollectorsRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mCollectorsRecyclerAdapter = new CollectorsRecyclerAdapter(getActivity(), Network.returnUsers());
        mCollectorsRecycler.setAdapter(mCollectorsRecyclerAdapter);


        return root;
    }




}

