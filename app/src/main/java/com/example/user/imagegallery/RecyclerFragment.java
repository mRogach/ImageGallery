package com.example.user.imagegallery;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.imagegallery.global.Constants;

import java.util.ArrayList;

/**
 * Created by User on 04.02.2015.
 */
public class RecyclerFragment extends Fragment {

    private RecyclerView recyclerView;
    private int columnCount;
    private MyRecyclerAdapter adapter;
    private ArrayList<Image> mImages;
    private ImageList mImageList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        columnCount = 2;
        mImageList.get(getActivity()).setImages(createList());
        mImages = mImageList.get(getActivity()).getImages();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(columnCount, StaggeredGridLayoutManager.VERTICAL));
        new StaggeredGridLayoutManager.LayoutParams(5, 10);
        adapter = new MyRecyclerAdapter(mImages, R.layout.item);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        final Fragment fragment = ViewPagerFragment.newInstance(position);;
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.fragmentContainer, fragment);
                        ft.addToBackStack("tag");
                        ft.commitAllowingStateLoss();
                    }
                })
        );
        return view;

    }

    private ArrayList<Image> createList() {
        final ArrayList<Image> items = new ArrayList<Image>();
        for (int i = 0; i < 30; i++) {
            new DownloadImageTask(new MyCallBack() {
                @Override
                public void callBack(String c) {
                    items.add(new Image(c));
                    adapter.notifyDataSetChanged();
                }

            }).execute(Constants.URL);
        }
        return items;
    }


    }
