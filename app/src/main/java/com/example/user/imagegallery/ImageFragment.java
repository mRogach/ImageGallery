package com.example.user.imagegallery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by User on 07.02.2015.
 */
public class ImageFragment extends Fragment {
    private ImageView imageView;
    private String url;

    public static ImageFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putString("url", url);
        ImageFragment imageFragment = new ImageFragment();
        imageFragment.setArguments(args);
        return imageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getArguments().getString("image");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_image_fragment, container, false);
        imageView = (ImageView) view.findViewById(R.id.img);
        Picasso.with(getActivity())
                .load(url)
                .into(imageView);
        return view;
    }
}
