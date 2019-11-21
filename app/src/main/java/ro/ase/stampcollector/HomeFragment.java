package ro.ase.stampcollector;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ro.ase.stampcollector.DataManager;
import ro.ase.stampcollector.R;
import ro.ase.stampcollector.StampInfo;
import ro.ase.stampcollector.StampRecyclerAdapter;

public class HomeFragment extends Fragment {


    private StampRecyclerAdapter mStampRecyclerAdapter;
    private RecyclerView mRecyclerStamps;
    private LinearLayoutManager mStampLayoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel =
//                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        mRecyclerStamps = root.findViewById(R.id.recyclre_item);
        mStampLayoutManager = new LinearLayoutManager(getActivity());


        List<StampInfo> stamps = DataManager.getInstance().getStamps();
        mStampRecyclerAdapter = new StampRecyclerAdapter(getActivity(), stamps);
        mRecyclerStamps.setLayoutManager(mStampLayoutManager);
        mRecyclerStamps.setAdapter(mStampRecyclerAdapter);

        return root;
    }
}