package masterung.androidthai.in.th.demoalarm;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;

import java.util.ArrayList;
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

//        Toolbar Controller
        toolbarController();

//        Check Alarm
        checkAlarm();

    }   // Method Main

    private void checkAlarm() {

        try {

            SQLiteDatabase sqLiteDatabase = getActivity().openOrCreateDatabase(
                    MyOpenHelper.DATABASE_NAME, Context.MODE_PRIVATE, null
            );

            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM alarmTABLE", null);


            if (cursor.moveToFirst()) {

                for (int i = 0; i < cursor.getCount(); i++) {

                    String idString = cursor.getString(0);
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(cursor.getString(2)));
                    calendar.set(Calendar.MONTH, 10);
                    calendar.set(Calendar.HOUR_OF_DAY, 3);
                    calendar.set(Calendar.MINUTE, Integer.parseInt(cursor.getString(5)));
                    calendar.set(Calendar.YEAR, 2018);
                    calendar.set(Calendar.SECOND, 0);

                    Log.d("4NovV3", "notiCalendar[" + i + "] ==> " + calendar.getTime().toString());

                    sentValueToReceiver(calendar, idString);

                    cursor.moveToNext();

                }   // for

            } else {
                Log.d("4NovV3", "Empty Data");
            }


        } catch (Exception e) {
            Log.d("4NovV3", "e checkAlarm ==> " + e.toString());
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.itemShowList) {

            replaceFragment();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_main, menu);

    }

    private void toolbarController() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarMain);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

        setHasOptionsMenu(true);

    }

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

//                sentValueToReceiver(calendar);

                MyManage myManage = new MyManage(getActivity());
                myManage.addValueToSQLite(calendar.getTime().toString(),
                        Integer.toString(dayInt[0]),
                        Integer.toString(monthInt[0]),
                        Integer.toString(hourInt),
                        Integer.toString(minusInt));

//                Replace Fragment
                replaceFragment();


            }
        });
    }

    private void replaceFragment() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contentMainFragment, new ShowListFragment())
                .addToBackStack(null)
                .commit();
    }

    private void sentValueToReceiver(Calendar notiCalendar, String idString) {

        Random random = new Random();
        int requestInt = random.nextInt(100);

        Intent intent = new Intent(getActivity(), MyReceiver.class);
        intent.putExtra("Message", notiCalendar.getTime().toString());
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
