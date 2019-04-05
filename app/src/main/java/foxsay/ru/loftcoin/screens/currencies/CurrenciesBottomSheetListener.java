package foxsay.ru.loftcoin.screens.currencies;

import foxsay.ru.loftcoin.data.db.model.CoinEntity;

public interface CurrenciesBottomSheetListener {

    void onCurrencySelected(CoinEntity coin);

}
