package foxsay.ru.loftcoin.screens.rate;

import foxsay.ru.loftcoin.utils.Fiat;

public interface RatePresenter {

    void attachView(RateView view);

    void detachView();

    void getRate();

    void onRefresh();

    void onMenuItemCurrencyClick();

    void onFiatCurrencySelected(Fiat currency);

    void onRateLongClick(String symbol);
}
