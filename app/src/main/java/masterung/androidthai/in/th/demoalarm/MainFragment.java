package masterung.androidthai.in.th.demoalarm;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;

import java.util.Calendar;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private CalendarView calendarView;
    private String tag = "3NovV1";
    private final int[] dayInt = new int[1];
    private final int[] monthInt = new int[1];
    private final int[] yearInt = new int[1];
    private int hourInt, minusInt;


    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Setup CurrentDate
        setupCurrentDate();

//        Set Controller
        setController();

    }   // Method Main

    private void setController() {
        Button button = getView().findViewById(R.id.buttonSet);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(tag, "Date ==> " + dayInt[0]);
                Log.d(tag, "Month ==> " + monthInt[0]);
                Log.d(tag, "Year ==> " + yearInt[0]);
                Log.d(tag, "Hour ==> " + hourInt);

                minusInt = minusInt + 2;
                Log.d(tag, "Minus ==> " + minusInt);

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_MONTH, dayInt[0]);
                calendar.set(Calendar.MONTH, monthInt[0]);
                calendar.set(Calendar.YEAR, yearInt[0]);
                calendar.set(Calendar.HOUR_OF_DAY, hourInt);
                calendar.set(Calendar.MINUTE, minusInt);

                Log.d(tag, "calendar ==> " + calendar.getTime());

                sentValueToReceiver(calendar);


            }
        });
    }

    private void sentValueToReceiver(Calendar notiCalendar) {

        Random random = new Random();
        int requestInt = random.nextInt(100);

        Intent intent = new Intent(getActivity(), MyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(),
                requestInt, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, notiCalendar.getTimeInMillis(), pendingIntent);


    }

    private void setupCurrentDate() {
        calendarView = getView().findViewById(R.id.calendarViewSet);

        Calendar calendar = Calendar.getInstance();
        dayInt[0] = calendar.get(Calendar.DAY_OF_MONTH);
        monthInt[0] = calendar.get(Calendar.MONTH);    // 0 ==> Jan
        yearInt[0] = calendar.get(Calendar.YEAR);
        hourInt = calendar.get(Calendar.HOUR_OF_DAY);
        minusInt = calendar.get(Calendar.MINUTE);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                dayInt[0] = dayOfMonth;
                monthInt[0] = month;
                yearInt[0] = year;
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

}
