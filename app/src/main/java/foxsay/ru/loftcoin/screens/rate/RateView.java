package foxsay.ru.loftcoin.screens.rate;

import java.util.List;

import foxsay.ru.loftcoin.data.db.model.CoinEntity;

public interface RateView {

    void setCoins(List<CoinEntity> coins);

    void setRefreshing(Boolean refreshing);

    void showCurrencyDialog();

    void invalidateRates();

}
