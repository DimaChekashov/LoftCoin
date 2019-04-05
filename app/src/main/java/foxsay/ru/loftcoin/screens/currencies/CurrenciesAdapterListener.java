package foxsay.ru.loftcoin.screens.currencies;


import foxsay.ru.loftcoin.data.db.model.CoinEntity;

public interface CurrenciesAdapterListener {

    void onCurrencyClick(CoinEntity coin);

}
