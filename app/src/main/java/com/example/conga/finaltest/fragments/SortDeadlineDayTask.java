package com.example.conga.finaltest.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.conga.finaltest.R;
import com.example.conga.finaltest.adapters.ListTasksAdapter1;
import com.example.conga.finaltest.database.databaseadapters.TaskDatabaseAdapter;
import com.example.conga.finaltest.models.Task;

import java.util.ArrayList;

/**
 * Created by ConGa on 8/04/2016.
 */
public class SortDeadlineDayTask extends Fragment {
    private static String TAG = SortDeadlineDayTask.class.getSimpleName();
   // private ListView mListView;
   private SwipeMenuListView swipeMenuListView;
    private TaskDatabaseAdapter mTaskDatabaseAdapter;
    private ListTasksAdapter1 mListTasksAdapter;
    private ArrayList<Task> mArrayList;
    private Integer[] img_id_alarm = {R.drawable.ic_alarm_off_black_18dp, R.drawable.ic_alarm_on_black_18dp};
    private Integer[] img_id_mark_important_task = {R.drawable.star_off, R.drawable.star_on};
    private Integer[] img_id_mark_done_task = {R.drawable.ic_done_black_18dp, R.drawable.ic_clear_black_18dp};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, " ON CREATE SORT DEADLINE DAY TASK");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sort_day_end_task_frag, container, false);
        swipeMenuListView = (SwipeMenuListView) view.findViewById(R.id.listView);
        mTaskDatabaseAdapter = new TaskDatabaseAdapter(getActivity());
        mTaskDatabaseAdapter.openDB();
        mArrayList = new ArrayList<Task>();
        mArrayList = mTaskDatabaseAdapter.sortDeadLineDayTask();
        mListTasksAdapter= new ListTasksAdapter1(getActivity(), mArrayList, img_id_alarm, img_id_mark_important_task, img_id_mark_done_task);
        swipeMenuListView.setAdapter(mListTasksAdapter);
        mListTasksAdapter.notifyDataSetChanged();
        return view;
    }


}
