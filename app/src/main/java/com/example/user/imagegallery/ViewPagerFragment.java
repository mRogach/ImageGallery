package com.example.user.imagegallery;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by User on 04.02.2015.
 */
public class ViewPagerFragment extends Fragment {
    private ViewPager viewPager;
    private ArrayList <Image> list;
    private int position;

    public static ViewPagerFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt("image", position);
        ViewPagerFragment countryDetailFragment = new ViewPagerFragment();
        countryDetailFragment.setArguments(args);
        return countryDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        //list = ImageList.get(getActivity()).getImages();
        //list = (ArrayList<Image>) getArguments().getSerializable("image");
        position = getArguments().getInt("image");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image_viewpager, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.pager);
        FragmentManager fm = getFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public int getCount() {
                return ImageList.get(getActivity()).getImages().size();
            }
            @Override
            public Fragment getItem(int pos) {
                Image image = ImageList.get(getActivity()).getImages().get(pos);
                return ImageFragment.newInstance(image.getmUrl());
            }
        });

        viewPager.setCurrentItem(position);
        return view;
    }


}
