package rcd27.github.com.dribbbleapp.view;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import rcd27.github.com.dribbbleapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            ShotsSwipeRefreshFragment fragment = new ShotsSwipeRefreshFragment();
            transaction.replace(R.id.fragment_container, fragment);
            transaction.commit();
        }
    }
}
