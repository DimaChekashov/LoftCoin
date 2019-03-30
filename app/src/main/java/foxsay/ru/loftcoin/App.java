package foxsay.ru.loftcoin;

import android.app.Application;

import foxsay.ru.loftcoin.data.api.Api;
import foxsay.ru.loftcoin.data.api.ApiInitializer;
import foxsay.ru.loftcoin.data.prefs.Prefs;
import foxsay.ru.loftcoin.data.prefs.PrefsImpl;
import timber.log.Timber;

public class App extends Application {

    private Prefs prefs;
    private Api api;

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());

        prefs = new PrefsImpl(this);
        api = new ApiInitializer().init();
    }

    public Prefs getPrefs() {
        return prefs;
    }

    public Api getApi() {
        return api;
    }
}
