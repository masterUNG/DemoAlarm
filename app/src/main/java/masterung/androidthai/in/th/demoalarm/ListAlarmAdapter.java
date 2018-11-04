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
    private OnClickListItem onClickListItem;

    public ListAlarmAdapter(Context context,
                            ArrayList<String> notiStringArrayList,
                            ArrayList<String> dayStringArrayList,
                            ArrayList<String> monthStringArrayList,
                            ArrayList<String> hourStringArrayList,
                            ArrayList<String> minusStringArrayList,
                            OnClickListItem onClickListItem) {
        this.layoutInflater = LayoutInflater.from(context);
        this.notiStringArrayList = notiStringArrayList;
        this.dayStringArrayList = dayStringArrayList;
        this.monthStringArrayList = monthStringArrayList;
        this.hourStringArrayList = hourStringArrayList;
        this.minusStringArrayList = minusStringArrayList;
        this.onClickListItem = onClickListItem;
    }

    @NonNull
    @Override
    public ListAlarmViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = layoutInflater.inflate(R.layout.layout_list_alarm, viewGroup, false);
        ListAlarmViewHolder listAlarmViewHolder = new ListAlarmViewHolder(view);

        return listAlarmViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ListAlarmViewHolder listAlarmViewHolder, int i) {

        String notiString = notiStringArrayList.get(i);
        String dayString = dayStringArrayList.get(i);
        String monthString = monthStringArrayList.get(i);
        String hourtring = hourStringArrayList.get(i);
        String minusString = minusStringArrayList.get(i);

        listAlarmViewHolder.notiTextView.setText(notiString);
        listAlarmViewHolder.dayTextView.setText("Day = " + dayString);
        String realMonthString = Integer.toString(Integer.parseInt(monthString) + 1);
        listAlarmViewHolder.monthTextView.setText("Month = " + realMonthString);
        listAlarmViewHolder.hourTextView.setText("HH = " + hourtring);
        listAlarmViewHolder.minusTextView.setText("Min = " + minusString);

//        Add After Create OnClickListItem
        listAlarmViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListItem.onClickListItem(v, listAlarmViewHolder.getAdapterPosition());
            }
        });


    }   // onBind

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
