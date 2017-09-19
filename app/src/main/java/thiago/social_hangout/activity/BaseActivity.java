package thiago.social_hangout.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by thiago on 30/08/17.
 */

public class BaseActivity extends AppCompatActivity {

    protected static String TAG = "Social";

    protected void replaceFragment(int container, Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(container,fragment).commit();
    }
}
