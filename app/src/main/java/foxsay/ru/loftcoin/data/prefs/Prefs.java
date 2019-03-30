package foxsay.ru.loftcoin.data.prefs;

import foxsay.ru.loftcoin.utils.Fiat;

public interface Prefs {

    boolean isFirstLaunch();

    void setFirstLaunch(boolean firstLaunch);

    Fiat getFiatCurrency();

    void setFiatCurrency(Fiat fiat);
}
