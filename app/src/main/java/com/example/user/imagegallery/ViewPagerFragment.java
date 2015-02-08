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

    public static ViewPagerFragment newInstance(ArrayList<Image> list) {
        Bundle args = new Bundle();
        args.putSerializable("image", list);
        ViewPagerFragment countryDetailFragment = new ViewPagerFragment();
        countryDetailFragment.setArguments(args);
        return countryDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        list = new ArrayList<Image>();
        list = (ArrayList<Image>) getArguments().getSerializable("image");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image_viewpager, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.pager);
        FragmentManager fm = getFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public int getCount() {
                return list.size();
            }
            @Override
            public Fragment getItem(int pos) {
                Image image = list.get(pos);
                return ImageFragment.newInstance(image.getmUrl());
            }
        });
        return view;
    }


}
