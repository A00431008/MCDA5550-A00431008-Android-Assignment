package com.example.mcda5550_a00431008_android_assignment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class HotelSearchFragment extends Fragment {

    // Declare variables
    View view;
    ConstraintLayout mainLayout;
    TextView titleTextView;
    EditText guestsCountEditText, guestNameEditText;
    Button searchButton;
    DatePicker checkInDatePicker, checkOutDatePicker;
    String checkInDate, checkOutDate, numberOfGuests, guestName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.hotel_search_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // get all the views by ID to associate with the variables
        mainLayout = view.findViewById(R.id.main_layout);
        titleTextView = view.findViewById(R.id.title_text_view);

        guestsCountEditText = view.findViewById(R.id.guests_count_edit_text);
        guestNameEditText = view.findViewById(R.id.guest_name_edit_text);
        searchButton = view.findViewById(R.id.search_button);

        checkInDatePicker = view.findViewById(R.id.check_in_date_picker_view);
        checkOutDatePicker = view.findViewById(R.id.check_out_date_picker_view);

        //Setting welcome text or title text
        titleTextView.setText(R.string.welcome_text);

        //Search Button click Listener
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInDate = dateFormatter(checkInDatePicker);
                checkOutDate = dateFormatter(checkOutDatePicker);
                guestName = guestNameEditText.getText().toString();
                //Get input of guests count
                numberOfGuests = guestsCountEditText.getText().toString();

                if (dateValidator(checkInDatePicker, checkOutDatePicker)
                        && fieldsValidator(numberOfGuests, guestName)
                ) {
                    Bundle bundle = new Bundle();
                    bundle.putString("checkInDate", checkInDate);
                    bundle.putString("checkOutDate", checkOutDate);
                    bundle.putString("numberOfGuests", numberOfGuests);
                    bundle.putString("guestName", guestName);

                    HotelsListFragment hotelsListFragment = new HotelsListFragment();
                    hotelsListFragment.setArguments(bundle);

                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, hotelsListFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });
    }

    private Calendar getCalendarFor(DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.set(year, month, day);

        return  calendar;
    }

    private String dateFormatter(DatePicker datePicker) {
        Calendar calendar = getCalendarFor(datePicker);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = simpleDateFormat.format(calendar.getTime());

        return formattedDate;
    }

    private boolean dateValidator (DatePicker checkInDate, DatePicker checkOutDate){
        Calendar checkInCal = getCalendarFor(checkInDate);
        Calendar checkOutCal = getCalendarFor(checkOutDate);
        Calendar today = Calendar.getInstance(TimeZone.getDefault());

        if (checkInCal.before(today)) {
            Toast.makeText(getContext(), "Check-in date can't be in the past. Please book for tomorrow or a future date", Toast.LENGTH_SHORT).show();
            return false;
        } else if (checkOutCal.before(checkInCal) || (checkOutCal.getTimeInMillis() - checkInCal.getTimeInMillis()) < 24 * 60 * 60 * 1000) {
            Toast.makeText(getContext(), "Check-out date must be at least one day after the check-in date.", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean fieldsValidator(String numGuests, String nameGuest) {
        int num = numGuests.isEmpty() ? 0: Integer.parseInt(numGuests);
        if (num <= 0) {
            Toast.makeText(getContext(), "Number of guests must be at least 1", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!nameGuest.matches("[a-zA-Z ]+")) {
            Toast.makeText(getContext(), "Name can only contain alphabets", Toast.LENGTH_SHORT).show();
            return false;
        } else if (nameGuest.length() > 50) {
            Toast.makeText(getContext(), "Name must be less than 50 characters", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

}