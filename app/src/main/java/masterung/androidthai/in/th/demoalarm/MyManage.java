package masterung.androidthai.in.th.demoalarm;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class MyManage {

    private Context context;
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public MyManage(Context context) {
        this.context = context;
        myOpenHelper = new MyOpenHelper(context);
        sqLiteDatabase = myOpenHelper.getWritableDatabase();
    }

    public long addValueToSQLite(String notiString,
                                 String dayString,
                                 String monthString,
                                 String hourString,
                                 String minusString) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("NotiTime", notiString);
        contentValues.put("Day", dayString);
        contentValues.put("Month", monthString);
        contentValues.put("Hour", hourString);
        contentValues.put("Minus", minusString);
        return sqLiteDatabase.insert("alarmTABLE", null, contentValues);

    }


}
