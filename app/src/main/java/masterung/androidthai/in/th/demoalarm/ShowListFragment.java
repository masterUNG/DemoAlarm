package masterung.androidthai.in.th.demoalarm;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowListFragment extends Fragment {


    public ShowListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Create RecyclerView
        createRecyclerView();

//        Create Toolbar
        createToolbar();

    }   // Main Method

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarListAlarm);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Show All Alarm");

        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

    }

    private void createRecyclerView() {

        RecyclerView recyclerView = getView().findViewById(R.id.recyclerViewListAlarm);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        try {

            SQLiteDatabase sqLiteDatabase = getActivity()
                    .openOrCreateDatabase(MyOpenHelper.DATABASE_NAME, Context.MODE_PRIVATE,
                            null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM alarmTABLE", null);

            Log.d("4NovV1", "cursor.count ==> " + cursor.getCount());

            cursor.moveToFirst();


            ArrayList<String> notiStringArrayList = new ArrayList<>();
            ArrayList<String> dayStringArrayList = new ArrayList<>();
            ArrayList<String> monthStringArrayList = new ArrayList<>();
            ArrayList<String> hourStringArrayList = new ArrayList<>();
            ArrayList<String> minusStringArrayList = new ArrayList<>();
            final ArrayList<String> idStringArrayList = new ArrayList<>();


            for (int i = 0; i < cursor.getCount(); i += 1) {

                idStringArrayList.add(cursor.getString(0));
                notiStringArrayList.add(cursor.getString(1));
                dayStringArrayList.add(cursor.getString(2));
                monthStringArrayList.add(cursor.getString(3));
                hourStringArrayList.add(cursor.getString(4));
                minusStringArrayList.add(cursor.getString(5));

                Log.d("4NovV1", "noti[" + i + "] ==> " + notiStringArrayList.get(i));

                cursor.moveToNext();

            }   // for

            Log.d("4NovV1", "noti ==> " + notiStringArrayList.toString());
            Log.d("4NovV1", "day ==> " + dayStringArrayList.toString());
            Log.d("4NovV1", "month ==> " + monthStringArrayList.toString());
            Log.d("4NovV1", "hour ==> " + hourStringArrayList.toString());
            Log.d("4NovV1", "minus ==> " + minusStringArrayList.toString());

//            Call RecyclerView Adapter
            ListAlarmAdapter listAlarmAdapter = new ListAlarmAdapter(getActivity(),
                    notiStringArrayList, dayStringArrayList, monthStringArrayList,
                    hourStringArrayList, minusStringArrayList, new OnClickListItem() {
                @Override
                public void onClickListItem(View view, int position) {
                    Log.d("4NovV2", "id Sent ==> " + idStringArrayList.get(position));

//                    Replace Fragment and put Value
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.contentMainFragment,
                                    EditAndDeleteFragment.editAndDeleteInstante(idStringArrayList.get(position)))
                            .addToBackStack(null)
                            .commit();


                }
            });
            recyclerView.setAdapter(listAlarmAdapter);

        cursor.close();
        } catch (Exception e) {
            Log.d("4NovV1", "e ==> " + e.toString());
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_list, container, false);
    }

}
