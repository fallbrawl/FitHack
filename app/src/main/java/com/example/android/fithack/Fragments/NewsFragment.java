package com.example.android.fithack.Fragments;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.android.fithack.Articles;
import com.example.android.fithack.ArticlesAdapter;
import com.example.android.fithack.DownloadImageTask;
import com.example.android.fithack.R;
import com.example.android.fithack.RetrieveHeadersTask;
import com.example.android.fithack.RetrieveImagesSrcTask;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String URL_NEWS = "http://fithacker.ru/";
    private OnFragmentInteractionListener mListener;
    private ArrayList<Articles> articles = new ArrayList<>();
    private ArrayList<String> articleHeaders;
    private ArrayList<String> imageSrcs;
    private ArrayList<Bitmap> bitmaps = new ArrayList<>();
    private Bitmap bitmap;

    public NewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance(ArrayList<String> param1,ArrayList<String> param2, Bitmap bitmap) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_PARAM1,param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            articleHeaders = new RetrieveHeadersTask().execute(URL_NEWS).get();
            imageSrcs = new RetrieveImagesSrcTask().execute(URL_NEWS).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < articleHeaders.size();i++){
            try {
                bitmap = new DownloadImageTask().execute(imageSrcs.get(i)).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            bitmaps.add(bitmap);
        }
        Log.w("bitmapssize", String.valueOf(bitmaps.size()));
        Log.w("bitmapsEx", String.valueOf(bitmaps.get(1)));
        Log.w("bitmapsEx", String.valueOf(bitmaps.get(2)));

        Log.w("thus2", String.valueOf(articleHeaders.size()));
        Log.w("articlesheaders", String.valueOf(articleHeaders.size()));
        Log.w("imgsheaders", String.valueOf(imageSrcs.size()));
        Log.w("example", String.valueOf(imageSrcs.get(2)));

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.article_list, container, false);

        // Adding some items to array via iterator

        Iterator<Bitmap> iteratorImges = bitmaps.iterator();
        Iterator<String> iteratorHeaders = articleHeaders.iterator();
        while (iteratorImges.hasNext()) {
            articles.add(new Articles(iteratorImges.next(), iteratorHeaders.next()));
        }

        // Create an {@link Article}, whose data source is a list of {@link Article}s. The
        // adapter knows how to create list items for each item in the list.
        ArticlesAdapter adapter = new ArticlesAdapter(getActivity(), articles, R.color.colorNews);

        // Find the {@link GridView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link GridView} with the view ID called list, which is declared in the
        // article_list layout file.
        GridView gridView = (GridView) rootView.findViewById(R.id.gridList);

        // Make the {@link GridView} use the {@link ArticlesAdapter} we created above, so that the
        // {@link GridView} will display list items for each {@link Article} in the list.
        gridView.setAdapter(adapter);

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
