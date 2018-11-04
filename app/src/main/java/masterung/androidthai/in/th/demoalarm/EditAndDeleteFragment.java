package masterung.androidthai.in.th.demoalarm;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditAndDeleteFragment extends Fragment {

    private String idString;
    private EditText notiEditText, dayEditText, monthEditText,
            hourEditText, minusEditText;
    private String tag = "4NovV2";

    public EditAndDeleteFragment() {
        // Required empty public constructor
    }

    public static EditAndDeleteFragment editAndDeleteInstante(String idString) {

        EditAndDeleteFragment editAndDeleteFragment = new EditAndDeleteFragment();
        Bundle bundle = new Bundle();
        bundle.putString("MyAlarm", idString);
        editAndDeleteFragment.setArguments(bundle);
        return editAndDeleteFragment;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        idString = getArguments().getString("MyAlarm");
        Log.d("4NovV2", "id at edtit ==> " + idString);

//        Create Toolbar
        createToolbar();

//        Show Data
        showData();

    }   // Main Method

    private void showData() {

        notiEditText = getView().findViewById(R.id.edittextNoti);
        dayEditText = getView().findViewById(R.id.edittextDay);
        monthEditText = getView().findViewById(R.id.edittextMonth);
        hourEditText = getView().findViewById(R.id.edittextHour);
        minusEditText = getView().findViewById(R.id.edittextMinus);

        try {

            SQLiteDatabase sqLiteDatabase = getActivity().openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                    Context.MODE_PRIVATE, null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM alarmTABLE WHERE id=" + idString, null);
            cursor.moveToFirst();

            String[] strings = new String[cursor.getColumnCount()];

            for (int i = 0; i < strings.length; i++) {

                strings[i] = cursor.getString(i);
                Log.d(tag, "strings[" + i + "] ==> " + strings[i]);

            }   // for

            notiEditText.setText(strings[1]);
            dayEditText.setText(strings[2]);
            monthEditText.setText(strings[3]);
            hourEditText.setText(strings[4]);
            minusEditText.setText(strings[5]);


        } catch (Exception e) {
            Log.d(tag, "e Error ==> " + e.toString());
        }

    }

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarEdit);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Edit and Delete");
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle("Please Click Item");

        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_and_delete, container, false);
    }

}
