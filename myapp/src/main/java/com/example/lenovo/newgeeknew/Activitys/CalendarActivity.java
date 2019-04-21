package com.example.lenovo.newgeeknew.Activitys;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.lenovo.newgeeknew.R;
import com.example.lenovo.newgeeknew.Utils.DateUtil;
import com.example.lenovo.newgeeknew.Utils.RxBus;
import com.example.lenovo.newgeeknew.ZhiHuFragments.DailyNewsFragment;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mToolBar;
    private MaterialCalendarView mViewCalender;
    /**
     * 确定
     */
    private TextView mTvCalenderEnter;

    CalendarDay mDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        initView();
    }

    private void initView() {
        mToolBar = (Toolbar) findViewById(R.id.tool_bar);
        mViewCalender = (MaterialCalendarView) findViewById(R.id.view_calender);
        mTvCalenderEnter = (TextView) findViewById(R.id.tv_calender_enter);
        mTvCalenderEnter.setOnClickListener(this);



        setSupportActionBar(mToolBar);

        mViewCalender.state().edit()
                .setFirstDayOfWeek(Calendar.WEDNESDAY)
                .setMinimumDate(CalendarDay.from(2013, 5, 20))
                .setMaximumDate(CalendarDay.from(DateUtil.getCurrentYear(), DateUtil.getCurrentMonth(), DateUtil.getCurrentDay()))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        mViewCalender.setSelectionColor(getResources().getColor(R.color.seclctor));
        mViewCalender.setSelectionColor(0xff4285f4);

        mViewCalender.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                mDate = date;
            }
        });



    }


    @Override
    public void onClick(View v) {
        if (mDate != null) {
            RxBus.getDefault().post(mDate);
        }
        finish();
    }
}
