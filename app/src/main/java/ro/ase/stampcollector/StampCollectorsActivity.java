package ro.ase.stampcollector;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StampCollectorsActivity extends MainActivity {

    private CollectorsRecyclerAdapter mCollectorsRecyclerAdapter;
    private RecyclerView mRecyclerCollectors;
    private GridLayoutManager mCollectorsLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp_collectors);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.hide();
//
//     getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initalizeDisplayContent();

    }

    private void initalizeDisplayContent(){
        mRecyclerCollectors = findViewById(R.id.recycler_collectors2);
        mCollectorsLayoutManager = new GridLayoutManager(this, 2);

//        List<User> users = Network.returnUsers();
//        mCollectorsRecyclerAdapter = new CollectorsRecyclerAdapter(this, users);
//        mRecyclerCollectors.setAdapter(mCollectorsRecyclerAdapter);
//        mRecyclerCollectors.setLayoutManager(mCollectorsLayoutManager);


    }

}
