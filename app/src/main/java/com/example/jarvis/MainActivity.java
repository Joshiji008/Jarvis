package com.example.jarvis;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.jarvis.functions.wishMe;

public class MainActivity extends AppCompatActivity {
    private SpeechRecognizer recognizer;
    private TextToSpeech tts;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findById();
        initializeTextToSpeech();
        initializeResult();
    }

    private void initializeTextToSpeech() {
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (tts.getEngines().size()==0){
                    Toast.makeText(MainActivity.this, "Engine is not available", Toast.LENGTH_SHORT).show();
                }
                else {
                    String s = wishMe();
                    speak("Hello Mohit , jai Shree Balaji "+s);


                }
            }
        });
    }

    private void speak(String msg) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(msg,TextToSpeech.QUEUE_FLUSH,null,null);
        }
        else {
            tts.speak(msg,TextToSpeech.QUEUE_FLUSH,null);
        }
    }

    private void findById() {
        tvResult = (TextView)findViewById(R.id.textView);

    }

    private void initializeResult() {
        if (SpeechRecognizer.isRecognitionAvailable(this)){
            recognizer = SpeechRecognizer.createSpeechRecognizer(this);
            recognizer.setRecognitionListener(new RecognitionListener() {
                @Override
                public void onReadyForSpeech(Bundle bundle) {

                }

                @Override
                public void onBeginningOfSpeech() {

                }

                @Override
                public void onRmsChanged(float v) {

                }

                @Override
                public void onBufferReceived(byte[] bytes) {

                }

                @Override
                public void onEndOfSpeech() {

                }

                @Override
                public void onError(int i) {

                }

                @Override
                public void onResults(Bundle bundle) {
                    ArrayList<String> result = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                    Toast.makeText(MainActivity.this, "" +result.get(0), Toast.LENGTH_SHORT).show();
                    tvResult.setText(result.get(0));
                    response(result.get(0));
                }

                @Override
                public void onPartialResults(Bundle bundle) {

                }

                @Override
                public void onEvent(int i, Bundle bundle) {

                }
            });
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void response(String msg) {
        String msgs = msg.toLowerCase();
        if (msgs.indexOf("hello jarvis")!=-1){
            speak("Hello sir ! How are You? ");
        }
        if (msgs.indexOf("fine")!=-1){
            speak("it's good to know that you are fine! how may i help you");
        }
        if (msgs.indexOf("i am fine")!=-1){
            speak("it's good to know tat you are fine! how may i help you");
        }
        if (msgs.indexOf("who is madan")!=-1){
            speak("chutiyaa ladka hai par aaj uska birthday hai to usko happy birthday wish kar deti hu");
        }
        if (msgs.indexOf("say something about jaat")!=-1){
            speak("wahi jaat jinka dimaag unke ghutno me hota hai");
        }
        if (msgs.indexOf("say something about joshi")!=-1){
            speak("joshi boys are really dangerous.");
        }
        if (msgs.indexOf("who is puneet")!=-1){
            speak("bakchod hai ek number ka");
        }
        if (msgs.indexOf("who is gaurav")!=-1){
            speak("goldee masaale , jahaan bhi jaae riste banaae");
        }
        if (msgs.indexOf("who is mohit")!=-1){
            speak("He is very interesting person");
        }
        if (msgs.indexOf("who is munni bai")!=-1){
            speak("mohit ki mummy ka naam hai munni bai");
        }
        if (msgs.indexOf("nandkishor")!=-1){
            if (msgs.indexOf("joshi")!=-1){
                speak("mohit ke paapa hai wo");

            }
        }
        if (msgs.indexOf("who is gopal")!=-1){
            speak("you mean gopal nath chhangaani, bdhiyaa hai wo par uske hisaab se sushil kumar yahaan rajasthan ka hai");
        }
        if (msgs.indexOf("who is guru")!=-1){
            speak("are yaar uska asli naam to haapu singh hai");
        }
        if (msgs.indexOf("ek hi nara ek hi naam")!=-1){
            speak("Jai shree ram , jai shree ram");
        }
        if (msgs.indexOf("tum mujhe khoon do")!=-1){
            speak("mai tumhe aajadi dungi");
        }
        if (msgs.indexOf("jay shri ram")!=-1) {
            speak("Jai shree ram");
        }
        if (msgs.indexOf("jay shri balaji")!=-1){
            speak("Jai shree balaji");
        }
        if (msgs.indexOf("jay mahakal")!=-1){
            speak("Jai mahakaal");
        }
        if (msgs.indexOf("ram lala ham aaenge")!=-1){
            speak("mandir wahin ban waenge");
        }
        if (msgs.indexOf("bete mauj kar de")!=-1){
            speak("waah bete, tum to bade heavy driver ho bhai");
        }
        if (msgs.indexOf("buy jarvis")!=-1){
            speak("hum hai raahi pyaar ke fir milenge chalte chalte");
        }
        if (msgs.indexOf("who")!=-1){
            if (msgs.indexOf("you")!=-1){
                speak("I am Your Personal Jarvis, your best friend");

            }
        }
        if (msgs.indexOf("time")!=-1){
            if (msgs.indexOf("now")!=-1){
                Date date = new Date();
                String time = DateUtils.formatDateTime(this,date.getTime(), DateUtils.FORMAT_SHOW_TIME);
                speak("The time now is"+time);

            }
        }

        if (msgs.indexOf("open")!=-1){
            if (msgs.indexOf("google")!=-1){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
                startActivity(intent);
            }
            if (msgs.indexOf("gmail")!=-1){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mail.google.com/mail/u/1/#inbox"));
                startActivity(intent);
            }

        }
        if (msgs.indexOf("open")!=-1){
            if (msgs.indexOf("facebook")!=-1){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com"));
                startActivity(intent);
            }
        }
        if (msgs.indexOf("open")!=-1){
            if (msgs.indexOf("weather")!=-1){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ventusky.com/bikaner"));
                startActivity(intent);
            }
        }
        if (msgs.indexOf("open")!=-1){
            if (msgs.indexOf("google translator")!=-1){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=google+translater&oq=google+&aqs=chrome.1.69i57j69i59j69i60l4j69i65l2.2408j0j15&sourceid=chrome&ie=UTF-8"));
                startActivity(intent);
            }
        }

        if (msgs.indexOf("open")!=-1){
            if (msgs.indexOf("whatsapp")!=-1){
                Context ctx = this;
                Intent i = ctx.getPackageManager().getLaunchIntentForPackage("com.whatsapp");
                ctx.startActivity(i);
            }
            if (msgs.indexOf("instagram")!=-1){
                Context ctx = this;
                Intent i = ctx.getPackageManager().getLaunchIntentForPackage("com.instagram.android");
                ctx.startActivity(i);
            }
        }
        if (msgs.indexOf("open")!=-1){
            if (msgs.indexOf("youtube")!=-1){
                Context ctx = this;
                Intent i = ctx.getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
                ctx.startActivity(i);
            }
            if (msgs.indexOf("telegram")!=-1){
                Context ctx = this;
                Intent i = ctx.getPackageManager().getLaunchIntentForPackage("org.telegram.messenger");
                ctx.startActivity(i);
            }
            if (msgs.indexOf("paytm")!=-1){
                Context ctx = this;
                Intent i = ctx.getPackageManager().getLaunchIntentForPackage("net.one97.paytm");
                ctx.startActivity(i);
            }
            if (msgs.indexOf("googlepay")!=-1){
                Context ctx = this;
                Intent i = ctx.getPackageManager().getLaunchIntentForPackage("com.google.android.apps.nbu.paisa.user");
                ctx.startActivity(i);
            }
            if (msgs.indexOf("phonepay")!=-1){
                Context ctx = this;
                Intent i = ctx.getPackageManager().getLaunchIntentForPackage("com.phonepe.app");
                ctx.startActivity(i);
            }
            if (msgs.indexOf("linkedin")!=-1){
                Context ctx = this;
                Intent i = ctx.getPackageManager().getLaunchIntentForPackage("com.linkedin.android");
                ctx.startActivity(i);
            }
        }

        if (msgs.indexOf("stopwatch")!=-1){
            Context ctx = this;
            Intent i = ctx.getPackageManager().getLaunchIntentForPackage("com.example.stopwatch");
            ctx.startActivity(i);
        }
        if (msgs.indexOf("mxplayer")!=-1){
            Context ctx = this;
            Intent i = ctx.getPackageManager().getLaunchIntentForPackage("com.mxtech.videoplayer.ad");
            ctx.startActivity(i);
        }
        if (msgs.indexOf("meme share")!=-1){
            Context ctx = this;
            Intent i = ctx.getPackageManager().getLaunchIntentForPackage("com.example.memeshare");
            ctx.startActivity(i);
        }

    }

    public void startRecording(View view) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,1);
        recognizer.startListening(intent);
    }
}