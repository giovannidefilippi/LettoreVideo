package giovanni.it.lettorevideo;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ProgressDialog mDialog;
    VideoView videoView;
    ImageButton btnPlayPause;

    String videoUrl= "http://192.168.1.74:8088/video/ahatakeonme.mp4";//http://www.ansa.it/sito/video/getEmbed.html?r=0&w=628&as=0&v=m20171126134538140.flv

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = (VideoView)findViewById(R.id.videoView);
        btnPlayPause = (ImageButton) findViewById(R.id.btn_play_pause);
        btnPlayPause.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
    mDialog = new ProgressDialog(MainActivity.this);
    mDialog.setMessage("Please wait ....");
    mDialog.setCanceledOnTouchOutside(false);
    mDialog.show();

    try{
        if(!videoView.isPlaying()){
        Uri uri = Uri.parse(videoUrl);
        videoView.setVideoURI(uri);
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
               btnPlayPause.setImageResource(R.drawable.ic_play);
            }
        });
            }
            else
        {
            videoView.pause();
            btnPlayPause.setImageResource(R.drawable.ic_play);
        }



    }catch (Exception e)
    {

    }

        videoView.requestFocus();
    videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
            mDialog.dismiss();
            mp.setLooping(true);
            videoView.start();
            btnPlayPause.setImageResource(R.drawable.ic_pause);
        }
    });


    }
}
