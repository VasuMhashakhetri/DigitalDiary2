package com.example.vasua.digital_diary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViewById(R.id.makeDiaryEntry).setOnClickListener(this);
        findViewById(R.id.viewDiaryEntry).setOnClickListener(this);
        findViewById(R.id.sharedEntries).setOnClickListener(this);
        findViewById(R.id.fix_meetings).setOnClickListener(this);
        findViewById(R.id.finalizeMeetings).setOnClickListener(this);
        findViewById(R.id.viewSchedule).setOnClickListener(this);
        findViewById(R.id.viewCalendar).setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){


            case R.id.makeDiaryEntry:
                startActivity(new Intent(this,MakeDiaryEntry.class));
                break;
            case R.id.viewDiaryEntry:
                startActivity(new Intent(this,ViewDiary.class));
                break;
            case R.id.sharedEntries:
                startActivity(new Intent(this,Shared.class));
                break;
            case R.id.fix_meetings:
                startActivity(new Intent(this,FixMeeting.class));
                break;
            case R.id.finalizeMeetings:
                startActivity(new Intent(this,FinalizeMeeting.class));
                break;
            case R.id.viewSchedule:
                startActivity(new Intent(this,ScheduleActivity.class));
                break;
            case R.id.viewCalendar:
                startActivity(new Intent(this,CalendarActivity.class));
                break;
        }
    }
}
