package ro.ase.stampcollector;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;




public class MyStampsListFragment extends Fragment {


    private StampRecyclerAdapter mStampRecyclerAdapter;
    private RecyclerView mRecyclerStamps;
    private LinearLayoutManager mStampLayoutManager;
    private List<Stamp2> stamps;
    private User currentUser;
    private UserRepository mUserRepository;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);



        mUserRepository = UserRepository.getInstance(getContext());
        currentUser = mUserRepository.getCurrentUser();



        mRecyclerStamps = root.findViewById(R.id.recyclre_item);
        mStampLayoutManager = new LinearLayoutManager(getActivity());













//        mUserRepository.addStamp(currentUser, new Stamp2("Romania. 1906. King Karl I.",
//                "Romania 1906 Mi 181 King Karl I Sc 190",
//                "25 Aug 1906", "black", 2));
//
//        mUserRepository.addStamp(currentUser, new Stamp2("Poland. 1967. Tadeusz Kosciuszko.",
//                "Poland 1967 Mi 1806-1807 Tadeusz Kosciuszko Sc 1540-1541",
//                "23 Mar 1967", "slate green", 1));
//
//        mUserRepository.addStamp(currentUser, new Stamp2("Romania. 1936. King Carol II.",
//                "Romania 1936 Mi 502 King Carol II Sc 456",
//                "18 Nov 1936", "blue", 4));
//
//        mUserRepository.addStamp(currentUser, new Stamp2("Indonesia. 1983. Agricultural Census.",
//                "Indonesia 1983 Sc 1200-1201 Agricultural Census Mi 1104-1105",
//                "3 Jul 1983", "multicolor", 20));
//
//        mUserRepository.addStamp(currentUser, new Stamp2("Bulgaria. 1973. Nicolaus Copernicus.",
//                "Bulgaria 1973 Mi 2228 Nicolaus Copernicus Sc 2086",
//                "21 Mar 1973", "multicolor", 8));



        stamps = mUserRepository.getStamps(currentUser);


        mStampRecyclerAdapter = new StampRecyclerAdapter(getActivity(), stamps);
        mRecyclerStamps.setLayoutManager(mStampLayoutManager);
        mRecyclerStamps.setAdapter(mStampRecyclerAdapter);

        return root;
    }



    @Override
    public void onResume() {


        super.onResume();
        stamps = mUserRepository.getStamps(currentUser);
        mStampRecyclerAdapter = new StampRecyclerAdapter(getActivity(), stamps);
        mRecyclerStamps.setAdapter(mStampRecyclerAdapter);
        mStampRecyclerAdapter.notifyDataSetChanged();

    }
}