package com.example.mcda5550_a00431008_android_assignment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HotelsListFragment extends Fragment {

    View view;
    TextView headingTextView;
    ProgressBar progressBar;
    ArrayList<Hotel> userListResponseData;

    Button backButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.hotel_list_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //heading text view
        headingTextView = view.findViewById(R.id.heading_text_view);
        progressBar = view.findViewById(R.id.progress_bar);

        String checkInDate = getArguments().getString("checkInDate");
        String checkOutDate = getArguments().getString("checkOutDate");
        String numberOfGuests = getArguments().getString("numberOfGuests");
        String guestName = getArguments().getString("guestName");

        //Set up the header
        headingTextView.setText("Welcome " + guestName +
                "!! Displaying hotel for " + numberOfGuests + " guests staying from " + checkInDate +
                " to " + checkOutDate);

        backButton = view.findViewById(R.id.back_button);
        // Set up click listener for the back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    getActivity().onBackPressed();
                }
            }
        });

        getHotelsListsData();
    }

    public ArrayList<Hotel> initHotelListData() {
        ArrayList<Hotel> list = new ArrayList<Hotel>();

        list.add(new Hotel("Hotel California", "1500$", "Available"));
        list.add(new Hotel("Grand Plaza Hotel", "1200$", "Not Available"));
        list.add(new Hotel("Sunset View Hotel", "900$", "Available"));
        list.add(new Hotel("Ocean Breeze Resort", "1800$", "Not Available"));
        list.add(new Hotel("Mountain Retreat Inn", "1300$", "Available"));
        list.add(new Hotel("Lakeside Lodge", "1100$", "Not Available"));
        list.add(new Hotel("Golden Sands Resort", "1600$", "Available"));
        list.add(new Hotel("City Lights Hotel", "1400$", "Not Available"));
        list.add(new Hotel("Riverfront Suites", "1700$", "Available"));
        list.add(new Hotel("Hilltop Haven Hotel", "1900$", "Not Available"));
        list.add(new Hotel("Royal Paradise Hotel", "2000$", "Available"));
        list.add(new Hotel("Silver Moon Resort", "2200$", "Not Available"));
        list.add(new Hotel("Emerald Isle Inn", "1600$", "Available"));
        list.add(new Hotel("Tropical Oasis Lodge", "1800$", "Not Available"));
        list.add(new Hotel("Azure Sky Hotel", "2100$", "Available"));

        return list;
    }

    private void getHotelsListsData() {
        progressBar.setVisibility(View.VISIBLE);

        // Get mock data directly
        ArrayList<Hotel> mockData = initHotelListData();

        // Simulate API response delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Set up the RecyclerView with mock data
                userListResponseData = mockData;
                setupRecyclerView();
            }
        }, 2000); // Simulate a delay of 2 seconds before mock data is available
    }

    private void setupRecyclerView() {
        progressBar.setVisibility(View.GONE);
        RecyclerView recyclerView = view.findViewById(R.id.hotel_list_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        HotelListAdapter hotelListAdapter = new HotelListAdapter(getActivity(), userListResponseData);
        recyclerView.setAdapter(hotelListAdapter);
    }



}