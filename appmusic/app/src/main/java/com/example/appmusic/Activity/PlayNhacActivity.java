package com.example.appmusic.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.appmusic.Adapter.ViewPagerPlayListNhac;
import com.example.appmusic.Fragment.Fragment_Dia_Nhac;
import com.example.appmusic.Fragment.Fragment_Play_Danh_Sach_Bai_Hat;
import com.example.appmusic.Model.Baihat;
import com.example.appmusic.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayNhacActivity extends AppCompatActivity {
    Toolbar toolbarplaynhac;
    TextView txtTimesong, txtTotaltimesong;
    SeekBar sktime;
    ImageButton imgplay, imgnext, imgpreview, imgrandom, imgrepeat;
    ViewPager viewPagerplaynhac;
    public static ArrayList<Baihat> baihatArrayList = new ArrayList<>();
    public static ViewPagerPlayListNhac adapternhac;
    Fragment_Dia_Nhac fragment_dia_nhac;
    Fragment_Play_Danh_Sach_Bai_Hat fragment_play_danh_sach_bai_hat;
    MediaPlayer mediaPlayer;
    int position = 0;
    boolean repeat = false;
    boolean checkrandom = false;
    boolean next = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetDataFromIntent();
        init();
        enventClick();
    }

    private void enventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(adapternhac.getItem(1) != null){
                    if(baihatArrayList.size() > 0){
                        fragment_dia_nhac.PlayNhac(baihatArrayList.get(0).getHinhBaiHat());
                        handler.removeCallbacks(this);
                    }else{
                        handler.postDelayed(this, 300);
                    }
                }
            }
        }, 500);
        imgplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgplay.setImageResource(R.drawable.iconplay);
                }else{
                    mediaPlayer.start();
                    imgplay.setImageResource(R.drawable.iconpause);
                }
            }
        });

        imgrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!repeat){
                    if(checkrandom){
                        checkrandom = false;
                        imgrandom.setImageResource(R.drawable.iconsuffle);
                    }
                    imgrepeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                }else{
                    imgrepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });

        imgrandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkrandom){
                    if(repeat){
                        repeat = false;
                        imgrepeat.setImageResource(R.drawable.iconrepeat);
                    }
                    imgrandom.setImageResource(R.drawable.iconshuffled);
                    checkrandom = true;
                }else{
                    imgrandom.setImageResource(R.drawable.iconsuffle);
                    checkrandom = false;
                }
            }
        });

        sktime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(baihatArrayList.size() > 0){
                    if(mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }

                    if(position < baihatArrayList.size()){
                        imgplay.setImageResource(R.drawable.iconpause);
                        position++;
                        if(repeat){
                            if(position == 0){
                                position = baihatArrayList.size();
                            }
                            position -= 1;
                        }
                        if(checkrandom){
                            Random random = new Random();
                            int index = random.nextInt(baihatArrayList.size());
                            if(index == position){
                                position = index - 1;
                            }
                            position = index;
                        }
                        if(position > baihatArrayList.size() - 1){
                            position = 0;
                        }
                        new PlayMp3().execute(baihatArrayList.get(position).getLinkBaiHat());
                        fragment_dia_nhac.PlayNhac(baihatArrayList.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(baihatArrayList.get(position).getTenBaiHat());
                        UpdateTime();
                    }
                }
                imgpreview.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpreview.setClickable(true);
                        imgnext.setClickable(true);
                    }
                }, 2000);
            }
        });

        imgpreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(baihatArrayList.size() > 0){
                    if(mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }

                    if(position < baihatArrayList.size()){
                        imgplay.setImageResource(R.drawable.iconpause);
                        position--;
                        if(position < 0){
                            position = baihatArrayList.size() - 1;
                        }

                        if(repeat){
                            position += 1;
                        }
                        if(checkrandom){
                            Random random = new Random();
                            int index = random.nextInt(baihatArrayList.size());
                            if(index == position){
                                position = index - 1;
                            }
                            position = index;
                        }

                        new PlayMp3().execute(baihatArrayList.get(position).getLinkBaiHat());
                        fragment_dia_nhac.PlayNhac(baihatArrayList.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(baihatArrayList.get(position).getTenBaiHat());
                        UpdateTime();
                    }
                }
                imgpreview.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpreview.setClickable(true);
                        imgnext.setClickable(true);
                    }
                }, 2000);
            }
        });
    }

    private void GetDataFromIntent() {
        Intent intent= getIntent();
        baihatArrayList.clear();
        if(intent.hasExtra("cakhuc")){
            Baihat baihat = intent.getParcelableExtra("cakhuc");
            baihatArrayList.add(baihat);
        }
        if(intent.hasExtra("cacbaihat")){
            baihatArrayList = intent.getParcelableArrayListExtra("cacbaihat");
        }
    }

    private void init() {
        toolbarplaynhac = findViewById(R.id.toolbarplaynhac);
        txtTimesong = findViewById(R.id.texviewtimesong);
        txtTotaltimesong = findViewById(R.id.textviewtotaltimesong);
        sktime = findViewById(R.id.seekbarsong);
        imgplay = findViewById(R.id.imagebuttonplay);
        imgnext = findViewById(R.id.imagebuttonnext);
        imgpreview = findViewById(R.id.imagebuttonpreview);
        imgrandom = findViewById(R.id.imagebuttonsuffle);
        imgrepeat = findViewById(R.id.imagebuttonrepeat);
        viewPagerplaynhac = findViewById(R.id.viewpagerplaynhac);
        setSupportActionBar(toolbarplaynhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarplaynhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                mediaPlayer.stop();
                baihatArrayList.clear();
            }
        });
        toolbarplaynhac.setTitleTextColor(Color.WHITE);
        fragment_dia_nhac = new Fragment_Dia_Nhac();
        fragment_play_danh_sach_bai_hat = new Fragment_Play_Danh_Sach_Bai_Hat();

        adapternhac = new ViewPagerPlayListNhac(getSupportFragmentManager());
        adapternhac.addFragment(fragment_play_danh_sach_bai_hat);
        adapternhac.addFragment(fragment_dia_nhac);
        viewPagerplaynhac.setAdapter(adapternhac);
        fragment_dia_nhac = (Fragment_Dia_Nhac) adapternhac.getItem(1);
        if(baihatArrayList.size() > 0){
            getSupportActionBar().setTitle(baihatArrayList.get(0).getTenBaiHat());
            new PlayMp3().execute(baihatArrayList.get(0).getLinkBaiHat());
            imgplay.setImageResource(R.drawable.iconpause);
        }
    }

    class PlayMp3 extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });

                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            UpdateTime();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        sktime.setMax(mediaPlayer.getDuration());
    }

    private void UpdateTime(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer != null){
                    sktime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txtTimesong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }, 300);
        final Handler handler1 = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(next){
                    if(position < baihatArrayList.size()){
                        imgplay.setImageResource(R.drawable.iconpause);
                        position++;
                        if(repeat){
                            if(position == 0){
                                position = baihatArrayList.size();
                            }
                            position -= 1;
                        }
                        if(checkrandom){
                            Random random = new Random();
                            int index = random.nextInt(baihatArrayList.size());
                            if(index == position){
                                position = index - 1;
                            }
                            position = index;
                        }
                        if(position > baihatArrayList.size() - 1){
                            position = 0;
                        }
                        new PlayMp3().execute(baihatArrayList.get(position).getLinkBaiHat());
                        fragment_dia_nhac.PlayNhac(baihatArrayList.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(baihatArrayList.get(position).getTenBaiHat());

                    }
                    imgpreview.setClickable(false);
                    imgnext.setClickable(false);
                    Handler handler1 = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgpreview.setClickable(true);
                            imgnext.setClickable(true);
                        }
                    }, 2000);

                    next = false;
                    handler1.removeCallbacks(this);
                }else {
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }
}