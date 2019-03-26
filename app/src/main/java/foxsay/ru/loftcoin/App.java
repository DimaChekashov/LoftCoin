package foxsay.ru.loftcoin;

import android.app.Application;

import foxsay.ru.loftcoin.prefs.Prefs;
import foxsay.ru.loftcoin.prefs.PrefsImpl;

public class App extends Application {

    private Prefs prefs;

    @Override
    public void onCreate() {
        super.onCreate();

        prefs = new PrefsImpl(this);
    }

    public Prefs getPrefs() {
        return prefs;
    }
}
