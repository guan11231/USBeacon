package com.THLight.USBeacon.Sample.ui;

import android.app.Activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import com.THLight.USBeacon.Sample.R;

public class todayAttendantInquire extends Activity {
    String inquireClass;
    ArrayList<String> temp;
    ArrayList<String> temp1;
    String[] splittedFlagData;
    String[] splittedAllStudentData;
    int record = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.today_attendant_inquire);


        TableLayout tableLayout = (TableLayout) findViewById(R.id.table);


        new Thread(new Runnable(){
            @Override
            public void run(){
                MysqlCon con = new MysqlCon();
                con.run();

                // 先找 class 資料表 flag = 1 的 data(即當天開放點名的課)
                temp = con.getClass_Flag();
                splittedFlagData = temp.get(0).split(",");
                inquireClass = splittedFlagData[0];



                // 找出當週簽到記錄
                temp1 = con.getClassAllData_AllStudent(inquireClass);
                String[] splittedTeacherData = temp1.get(0).split(",");
                int index = 3;

                while(!(splittedTeacherData[index].equals("null"))){
                    record = index;
                    index++;
                }
                TextView textview = (TextView) findViewById(R.id.title);
                TextView textview1 = (TextView) findViewById(R.id.week);
//                textview.setText("課名：" + inquireClass);
//                textview1.setText("第 " + (record - 2) + " 週");

                int id = 0;

                for (int i = 1; i < temp1.size(); i++) {
                    splittedAllStudentData = temp1.get(i).split(",");

                    TableRow tableRow = new TableRow(todayAttendantInquire.this);

                    TextView textView1 = new TextView(todayAttendantInquire.this);
                    textView1.setId(id + 1);
                    textView1.setBackgroundColor(Color.WHITE);
                    textView1.setText(splittedAllStudentData[0]);
                    textView1.setPadding(50,50,50,50);
                    textView1.setTextSize(20);
                    textView1.setTypeface(null, Typeface.BOLD);
                    textView1.setGravity(Gravity.CENTER);
                    TableRow.LayoutParams TextView1Params = new TableRow.LayoutParams(
                            TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT,1.0f);
                    TextView1Params.setMargins(1,0,1,1);
                    textView1.setLayoutParams(TextView1Params);
                    tableRow.addView(textView1);


                    TextView textView2 = new TextView(todayAttendantInquire.this);
                    textView2.setId(id + 2);
                    textView2.setBackgroundColor(Color.WHITE);
                    textView2.setText(splittedAllStudentData[1]);
                    textView2.setPadding(50,50,50,50);
                    textView2.setTextSize(20);
                    textView2.setTypeface(null, Typeface.BOLD);
                    textView2.setGravity(Gravity.CENTER);
                    TableRow.LayoutParams TextView2Params = new TableRow.LayoutParams(
                            TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT,1.0f);
                    TextView2Params.setMargins(1,0,1,1);
                    textView2.setLayoutParams(TextView2Params);
                    tableRow.addView(textView2);

                    TextView textView3 = new TextView(todayAttendantInquire.this);
                    textView3.setId(id + 3);
                    textView3.setBackgroundColor(Color.WHITE);
                    textView3.setText(splittedAllStudentData[2]);
                    textView3.setPadding(50,50,50,50);
                    textView3.setTextSize(20);
                    textView3.setTypeface(null, Typeface.BOLD);
                    textView3.setGravity(Gravity.CENTER);
                    TableRow.LayoutParams TextView3Params = new TableRow.LayoutParams(
                            TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT,1.0f);
                    TextView3Params.setMargins(1,0,1,1);
                    textView3.setLayoutParams(TextView3Params);
                    tableRow.addView(textView3);

                    TextView textView4 = new TextView(todayAttendantInquire.this);
                    textView4.setId(id + 4);
                    textView4.setBackgroundColor(Color.WHITE);

                    if(splittedAllStudentData[record].equals("1")){
                        textView4.setText("已簽到");
                        textView4.setTextColor(Color.GREEN);
                    }else{
                        textView4.setText("未簽到");
                        textView4.setTextColor(Color.RED);
                    }

                    textView4.setPadding(50,50,50,50);
                    textView4.setTextSize(20);
                    textView4.setTypeface(null, Typeface.BOLD);
                    textView4.setGravity(Gravity.CENTER);
                    TableRow.LayoutParams TextView4Params = new TableRow.LayoutParams(
                            TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT,1.0f);
                    TextView4Params.setMargins(1,0,1,1);
                    textView4.setLayoutParams(TextView4Params);
                    tableRow.addView(textView4);

                    tableLayout.post(new Runnable() {
                        public void run() {
                            tableLayout.addView(tableRow);
                        }
                    });



                    id += 4;
                }

            }
        }).start();





    }
}
