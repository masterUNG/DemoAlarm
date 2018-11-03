package masterung.androidthai.in.th.demoalarm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAlarmAdapter extends RecyclerView.Adapter<ListAlarmAdapter.ListAlarmViewHolder> {

    private Context context;
    private ArrayList<String> notiStringArrayList, dayStringArrayList,
            monthStringArrayList, hourStringArrayList, minusStringArrayList;
    private LayoutInflater layoutInflater;

    public ListAlarmAdapter(Context context,
                            ArrayList<String> notiStringArrayList,
                            ArrayList<String> dayStringArrayList,
                            ArrayList<String> monthStringArrayList,
                            ArrayList<String> hourStringArrayList,
                            ArrayList<String> minusStringArrayList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.notiStringArrayList = notiStringArrayList;
        this.dayStringArrayList = dayStringArrayList;
        this.monthStringArrayList = monthStringArrayList;
        this.hourStringArrayList = hourStringArrayList;
        this.minusStringArrayList = minusStringArrayList;
    }   // Constructor

    @NonNull
    @Override
    public ListAlarmViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {



        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ListAlarmViewHolder listAlarmViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return notiStringArrayList.size();
    }

    public class ListAlarmViewHolder extends RecyclerView.ViewHolder {

        private TextView notiTextView, dayTextView, monthTextView,
                hourTextView, minusTextView;

        public ListAlarmViewHolder(@NonNull View itemView) {
            super(itemView);

//            Initial View
            notiTextView = itemView.findViewById(R.id.textViewNoti);
            dayTextView = itemView.findViewById(R.id.textViewDay);
            monthTextView = itemView.findViewById(R.id.textViewMonth);
            hourTextView = itemView.findViewById(R.id.textViewHour);
            minusTextView = itemView.findViewById(R.id.textViewMinus);

        }
    }   // ViewHolder Class

}   // Class Main
