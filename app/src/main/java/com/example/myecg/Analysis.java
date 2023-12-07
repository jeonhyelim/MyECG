package com.example.myecg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Analysis extends AppCompatActivity {
    TextView txt_weather;
    TextView txt2_weather;
    TextView feel_weather;
    ImageView imageview = null;
    ImageView imageView_r;
    ImageView imageView_w;
    ImageView imageView_e;
    ImageView imageView_l;
    ImageView imageView_n;
    ImageView imageView_h;

    float value;
    float ecg_mean;
    String state;

    Button bt_log;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        imageView_r = findViewById(R.id.imageView_r);
        imageView_w = findViewById(R.id.imageView_w);
        imageView_e = findViewById(R.id.imageView_e);
        imageView_l = findViewById(R.id.imageView_l);
        imageView_n = findViewById(R.id.imageView_n);
        imageView_h = findViewById(R.id.imageView_h);

        imageView_r.setColorFilter(null);
        imageView_w.setColorFilter(null);
        imageView_e.setColorFilter(null);


        Toast.makeText(getApplicationContext(), "심박수 분석페이지 입니다!", Toast.LENGTH_SHORT).show();

        txt_weather = (TextView) findViewById(R.id.txt_weather);
        txt2_weather = (TextView) findViewById(R.id.txt2_weather);
        feel_weather = (TextView) findViewById(R.id.txt_weather_feel);
//        imageview = (ImageView)findViewById(R.id.imageView);

        txt2_weather.setText("날씨");
        //https://119.seoul.go.kr/asims/wether/selectWetherList.do 사용

        new WeatherAsyncTask(txt_weather).execute("https://119.seoul.go.kr/asims/wether/selectWetherList.do", "ul[class=w_r]");

        //imageview.setImageResource(R.drawable.lotsofclouds);

        //        if("맑음"){
//            imageview.setImageResource(R.drawable.sunny);
//        } else if (구름많음) {
//            imageview.setImageResource(R.drawable.lotsofclouds);
//        } else if(흐림){
//            imageview.setImageResource(R.drawable.cloudy);
//        }

        new WeatherAsyncTask(feel_weather).execute("https://www.kweather.co.kr/kma/kma_digital.html","li[class = kma_digital_predent_wspeed]");




        Intent intent = getIntent();
        ecg_mean = intent.getFloatExtra("ecg_mean",ecg_mean);
//        Toast.makeText(getApplicationContext(), "ecg_mean? "+ecg_mean, Toast.LENGTH_SHORT).show();
        Log.d("CCHECK",""+ecg_mean);
        state = intent.getStringExtra("state"); //state 띄우기
//        Toast.makeText(getApplicationContext(), "현재 상태? "+state, Toast.LENGTH_SHORT).show();


        //현재 상태 띄우기
        if (state.equals("rest")){
            imageView_w.setColorFilter(Color.parseColor("#C0C0C0"));
            imageView_e.setColorFilter(Color.parseColor("#C0C0C0"));
        }
        else if (state.equals("walk")){
            imageView_r.setColorFilter(Color.parseColor("#C0C0C0"));
            imageView_e.setColorFilter(Color.parseColor("#C0C0C0"));
        }
        else {
            imageView_r.setColorFilter(Color.parseColor("#C0C0C0"));
            imageView_w.setColorFilter(Color.parseColor("#C0C0C0"));
        }



        SharedPreferences sharedPreferences= getSharedPreferences("user", MODE_PRIVATE);

        int age = Integer.parseInt(sharedPreferences.getString("age",""));
        String gender = sharedPreferences.getString("gender","");

        Toast.makeText(getApplicationContext(), "age: "+age, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "gender: "+gender, Toast.LENGTH_SHORT).show();



        bt_log = findViewById(R.id.bt_log);
        bt_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Analysis.this,LogActivity.class);
                startActivity(intent);
            }
        });



//        성별,연령,상태별 분석
        if(gender.equals("MALE")){
            if(18<=age&&age<=25){
                if(ecg_mean<70){
                    imageView_n.setColorFilter(Color.parseColor("#FFFFFF"));
                    imageView_h.setColorFilter(Color.parseColor("#FFFFFF"));
                }
                else if(70<=ecg_mean&&ecg_mean<=85){
                    imageView_l.setColorFilter(Color.parseColor("#FFFFFF"));
                    imageView_h.setColorFilter(Color.parseColor("#FFFFFF"));
                }
                else{
                    imageView_l.setColorFilter(Color.parseColor("#FFFFFF"));
                    imageView_n.setColorFilter(Color.parseColor("#FFFFFF"));
                }
            }
            else if(26<=age&&age<=35){
                if(ecg_mean<71){
                    imageView_n.setColorFilter(Color.parseColor("#FFFFFF"));
                    imageView_h.setColorFilter(Color.parseColor("#FFFFFF"));
                }
                else if(71<=ecg_mean&&ecg_mean<=85){
                    imageView_l.setColorFilter(Color.parseColor("#FFFFFF"));
                    imageView_h.setColorFilter(Color.parseColor("#FFFFFF"));
                }
                else{
                    imageView_l.setColorFilter(Color.parseColor("#FFFFFF"));
                    imageView_n.setColorFilter(Color.parseColor("#FFFFFF"));
                }
            }
            else if(36<=age&&age<=45){
                if(ecg_mean<71){
                    imageView_n.setColorFilter(Color.parseColor("#FFFFFF"));
                    imageView_h.setColorFilter(Color.parseColor("#FFFFFF"));
                }
                else if(71<=ecg_mean&&ecg_mean<=85){
                    imageView_l.setColorFilter(Color.parseColor("#FFFFFF"));
                    imageView_h.setColorFilter(Color.parseColor("#FFFFFF"));
                }
                else{
                    imageView_l.setColorFilter(Color.parseColor("#FFFFFF"));
                    imageView_n.setColorFilter(Color.parseColor("#FFFFFF"));
                }
            }
            else if(46<=age&&age<=55){
                if(ecg_mean<72){
                    imageView_n.setColorFilter(Color.parseColor("#FFFFFF"));
                    imageView_h.setColorFilter(Color.parseColor("#FFFFFF"));
                }
                else if(72<=ecg_mean&&ecg_mean<=85){
                    imageView_l.setColorFilter(Color.parseColor("#FFFFFF"));
                    imageView_h.setColorFilter(Color.parseColor("#FFFFFF"));
                }
                else{
                    imageView_l.setColorFilter(Color.parseColor("#FFFFFF"));
                    imageView_n.setColorFilter(Color.parseColor("#FFFFFF"));
                }

            }
            else if(56<=age&&age<=65){
                if(ecg_mean<72){
                    imageView_n.setColorFilter(Color.parseColor("#FFFFFF"));
                    imageView_h.setColorFilter(Color.parseColor("#FFFFFF"));
                }
                else if(72<=ecg_mean&&ecg_mean<=85){
                    imageView_l.setColorFilter(Color.parseColor("#FFFFFF"));
                    imageView_h.setColorFilter(Color.parseColor("#FFFFFF"));
                }
                else{
                    imageView_l.setColorFilter(Color.parseColor("#FFFFFF"));
                    imageView_n.setColorFilter(Color.parseColor("#FFFFFF"));
                }

            }
            else {
                if(ecg_mean<70){
                    imageView_n.setColorFilter(Color.parseColor("#FFFFFF"));
                    imageView_h.setColorFilter(Color.parseColor("#FFFFFF"));
                }
                else if(70<=ecg_mean&&ecg_mean<=85){
                    imageView_l.setColorFilter(Color.parseColor("#FFFFFF"));
                    imageView_h.setColorFilter(Color.parseColor("#FFFFFF"));
                }
                else{
                    imageView_l.setColorFilter(Color.parseColor("#FFFFFF"));
                    imageView_n.setColorFilter(Color.parseColor("#FFFFFF"));
                }

            }

        }
        else { //gender=="woman"
            if(18<=age&&age<=25){
                if(ecg_mean<74){
                    imageView_n.setColorFilter(Color.parseColor("#FFFFFF"));
                    imageView_h.setColorFilter(Color.parseColor("#FFFFFF"));
                }
                else if(74<=ecg_mean&&ecg_mean<=85){
                    imageView_l.setColorFilter(Color.parseColor("#FFFFFF"));
                    imageView_h.setColorFilter(Color.parseColor("#FFFFFF"));
                }
                else{
                    imageView_l.setColorFilter(Color.parseColor("#FFFFFF"));
                    imageView_n.setColorFilter(Color.parseColor("#FFFFFF"));
                }
            }
            else if(26<=age&&age<=35){
                if(ecg_mean<73){
                    imageView_n.setColorFilter(Color.parseColor("#FFFFFF"));
                    imageView_h.setColorFilter(Color.parseColor("#FFFFFF"));
                }
                else if(73<=ecg_mean&&ecg_mean<=85){
                    imageView_l.setColorFilter(Color.parseColor("#FFFFFF"));
                    imageView_h.setColorFilter(Color.parseColor("#FFFFFF"));
                }
                else{
                    imageView_l.setColorFilter(Color.parseColor("#FFFFFF"));
                    imageView_n.setColorFilter(Color.parseColor("#FFFFFF"));
                }
            }
            else if(36<=age&&age<=45){
                if(ecg_mean<74){
                    imageView_n.setColorFilter(Color.parseColor("#FFFFFF"));
                    imageView_h.setColorFilter(Color.parseColor("#FFFFFF"));
                }
                else if(74<=ecg_mean&&ecg_mean<=85){
                    imageView_l.setColorFilter(Color.parseColor("#FFFFFF"));
                    imageView_h.setColorFilter(Color.parseColor("#FFFFFF"));
                }
                else{
                    imageView_l.setColorFilter(Color.parseColor("#FFFFFF"));
                    imageView_n.setColorFilter(Color.parseColor("#FFFFFF"));
                }
            }
            else if(46<=age&&age<=55){
                if(ecg_mean<74){
                    imageView_n.setColorFilter(Color.parseColor("#FFFFFF"));
                    imageView_h.setColorFilter(Color.parseColor("#FFFFFF"));
                }
                else if(74<=ecg_mean&&ecg_mean<=85){
                    imageView_l.setColorFilter(Color.parseColor("#FFFFFF"));
                    imageView_h.setColorFilter(Color.parseColor("#FFFFFF"));
                }
                else{
                    imageView_l.setColorFilter(Color.parseColor("#FFFFFF"));
                    imageView_n.setColorFilter(Color.parseColor("#FFFFFF"));
                }

            }
            else if(56<=age&&age<=65){
                if(ecg_mean<74){
                    imageView_n.setColorFilter(Color.parseColor("#FFFFFF"));
                    imageView_h.setColorFilter(Color.parseColor("#FFFFFF"));
                }
                else if(74<=ecg_mean&&ecg_mean<=85){
                    imageView_l.setColorFilter(Color.parseColor("#FFFFFF"));
                    imageView_h.setColorFilter(Color.parseColor("#FFFFFF"));
                }
                else{
                    imageView_l.setColorFilter(Color.parseColor("#FFFFFF"));
                    imageView_n.setColorFilter(Color.parseColor("#FFFFFF"));
                }

            }
            else {
                if(ecg_mean<73){
                    imageView_n.setColorFilter(Color.parseColor("#FFFFFF"));
                    imageView_h.setColorFilter(Color.parseColor("#FFFFFF"));
                }
                else if(73<=ecg_mean&&ecg_mean<=85){
                    imageView_l.setColorFilter(Color.parseColor("#FFFFFF"));
                    imageView_h.setColorFilter(Color.parseColor("#FFFFFF"));
                }
                else{
                    imageView_l.setColorFilter(Color.parseColor("#FFFFFF"));
                    imageView_n.setColorFilter(Color.parseColor("#FFFFFF"));
                }

            }


        }
    }
}
class WeatherAsyncTask extends AsyncTask<String, Void, String>{

    static int count = 0;
    TextView textView;

    static String temperature = "";
    static String wind_speed = "";

    public WeatherAsyncTask(TextView textView){
        this.textView = textView;
    }
    @Override
    protected String doInBackground(String... params) {

        String URL = params[0];
        String El = params[1];
        String result = "";

        try{
            Document document = Jsoup.connect(URL).get();
            Elements elements = document.select(El);

            for(Element element : elements){

                result = result+element.text()+"\n";
            }

            return result;
        }
        catch (IOException e){
            e.printStackTrace();
        }



        return null;
    }
    String wthr;
    ImageView imageview = null;
    @Override
    protected void onPostExecute(String s){
        super.onPostExecute(s);
        if (count == 0) {
            Log.d("DKU", "받아온 값 : " + s);
            String[] temp = s.split(" ");
            String good = "온도 : " + temp[0] + "\n" + temp[1] + " : " + temp[2] + "\n" + temp[3] + temp[4] + " : " + temp[5] + "\n" + temp[6] + temp[7] + " : " + temp[8] + "\n" + temp[9];
            Log.d("DKU", "문자열로 바꾼값 : " + good);

//            wthr = temp[9];
//            if(wthr.equals("맑음")){
//                imageview.setImageResource(R.drawable.sunny);
//            } else if (wthr.equals("구름")) {
//
//            } else if(wthr.equals("흐림")){
//                imageview.setImageResource(R.drawable.cloudy);
//            }




            String good1 = temp[0];
            String[] temp1 = good1.split("°");
            temperature = temp1[0];
            Log.d("DKU", "첫번째 웹크롤링 온도값 들어감");

            //String[] real_temp = s.
            count++;
            textView.setText(good);
        }

        else{
            Log.d("DKU", "받아온 값1 : " + s);
            String[] temp = s.split(" ");
            //String good = "온도 : " + temp[0] + "\n" + temp[1] + " : " + temp[2] + "\n" + temp[3] + temp[4] + " : " + temp[5] + "\n" + temp[6] + temp[7] + " : " + temp[8] + "\n" + temp[9] + temp[10];
            String good1 = temp[2];

            String[] temp1 = good1.split("m");
            wind_speed = temp1[0];


            Log.d("DKU", "풍소의 숫자만을 가져오고 저장한 값 : " + wind_speed);

            float KM_wind_speed = Float.valueOf(wind_speed) * 60 * 60 / 1000;

            float real_temp = (float) (13.12 + (Float.valueOf(temperature) * 0.6215) - (11.37 * Math.pow(KM_wind_speed, 0.16)) + 0.3965 * Float.valueOf(temperature) * Math.pow(KM_wind_speed, 0.16));
            //float temp_temp = (float) (13.12 + 0.6215 * -5.0   - 11.37 * Math.pow(14.4, 0.16) + 0.3965 * -5.0 * Math.pow(14.4, 0.16));
            //Log.d("DKU","테스트 값 : "+temp_temp);


            Log.d("DKUTEMP",""+real_temp);
            textView.setText( "실제 체감온도 : 약 "+String.format("%.1f",real_temp)+"°c");

        }
    }
}
