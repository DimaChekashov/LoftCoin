package foxsay.ru.loftcoin.screens.launch;

import androidx.appcompat.app.AppCompatActivity;
import foxsay.ru.loftcoin.App;
import foxsay.ru.loftcoin.prefs.Prefs;
import foxsay.ru.loftcoin.screens.start.StartActivity;
import foxsay.ru.loftcoin.screens.welcome.WelcomeActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Prefs prefs = ((App) getApplication()).getPrefs();

        if (prefs.isFirstLaunch()) {
            WelcomeActivity.start(this);
        } else {
            StartActivity.start(this);
        }

        finish();
    }
}
