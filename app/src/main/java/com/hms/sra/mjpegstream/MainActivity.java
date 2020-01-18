package com.hms.sra.mjpegstream;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class MainActivity extends AppCompatActivity {


    private MjpegView mv;
    private static final int MENU_QUIT = 1;

    /* Creates the menu items */
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MENU_QUIT, 0, "Quit");
        return true;
    }

    /* Handles item selections */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_QUIT:
                finish();
                return true;
        }
        return false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            //setContentView(R.layout.activity_main);
            String URL_ = "http://192.168.1.21:34567/cgi-bin/snapshot.cgi?loginuse=hasan&loginpas=gigabyte";
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            mv = new MjpegView(this);
            setContentView(mv);
            MjpegInputStream.read(URL_,mv);
            mv.setDisplayMode(MjpegView.SIZE_BEST_FIT);
            mv.showFps(false);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void onPause() {
        super.onPause();
        mv.stopPlayback();
    }
}
