package com.redorigami.simpleweather.fragment;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.redorigami.simpleweather.CountryListAdapter;
import com.redorigami.simpleweather.CountryListItem;
import com.redorigami.simpleweather.MainActivity;
import com.redorigami.simpleweather.R;
import com.redorigami.simpleweather.fragment.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class CountryFragment extends Fragment implements AbsListView.OnItemClickListener {



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;
    private List exampleListItemList; // at the top of your fragment list

    // TODO: Rename and change types of parameters
    public static CountryFragment newInstance(String param1, String param2) {
        CountryFragment fragment = new CountryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CountryFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        exampleListItemList = new ArrayList();
        exampleListItemList.add(new CountryListItem("Oman"));
        exampleListItemList.add(new CountryListItem("UAE"));
        exampleListItemList.add(new CountryListItem("Bahrain"));
        exampleListItemList.add(new CountryListItem("Saudi Arabia"));
        exampleListItemList.add(new CountryListItem("Qatar"));
        exampleListItemList.add(new CountryListItem("Kuwait"));

        Integer[] imgid={
                R.drawable.oman,
                R.drawable.uae,
                R.drawable.bahrain,
                R.drawable.ksa,
                R.drawable.qater,
                R.drawable.iraqflag,

        };

        mAdapter = new CountryListAdapter(getActivity(), exampleListItemList, imgid);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        // TODO: Change Adapter to display your content
        /*mAdapter = new ArrayAdapter<DummyContent.DummyItem>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, DummyContent.ITEMS);*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_country, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //CountryListItem  item = this.exampleListItemList.get(position);
       // Toast.makeText(getActivity(), item.getItemTitle() + " Clicked!", Toast.LENGTH_SHORT).show();
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.


            mListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);
           // System.out.println(DummyContent.ITEMS.get(position).content);
            //FragmentManager fman = getFragmentManager();
            //String itemPos = DummyContent.ITEMS.get(position).id;
           /* String city = DummyContent.ITEMS.get(position).content;
            System.out.println("city name is " + city);
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            WeatherFragment llf = new WeatherFragment();
            llf.changeCity("Muscat");
            // new CityPreference(this.getActivity()).setCity("Muscat");

            ft.replace(R.id.container, llf);
            ft.commit();*/

            switch(position){
                case 0: {
                    Intent j = new Intent(getActivity(), MainActivity.class);
                    j.putExtra("cityname", "Muscat");
                    startActivity(j);
                    break;
                }
                case 1: {
                    Intent j = new Intent(getActivity(), MainActivity.class);
                    j.putExtra("cityname", "Dubai");
                    startActivity(j);
                    break;

                }
                case 2: {
                    Intent j = new Intent(getActivity(), MainActivity.class);
                    j.putExtra("cityname", "Manama");
                    startActivity(j);
                    break;

                }
                case 3: {
                    Intent j = new Intent(getActivity(), MainActivity.class);
                    j.putExtra("cityname", "Riyadh");
                    startActivity(j);
                    break;
                }
                case 4: {
                    Intent j = new Intent(getActivity(), MainActivity.class);
                    j.putExtra("cityname", "Doha");
                    startActivity(j);
                    break;
                }
                case 5: {
                    Intent j = new Intent(getActivity(), MainActivity.class);
                    j.putExtra("cityname", "Kuwait");
                    startActivity(j);
                    break;
                }

            }


            /*WeatherFragment wf = (WeatherFragment)getSupportFragmentManager()
                    .findFragmentById(R.id.container);
            wf.changeCity(DummyContent.ITEMS.get(position).content);*/




        }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
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
        public void onFragmentInteraction(String id);
    }

}
