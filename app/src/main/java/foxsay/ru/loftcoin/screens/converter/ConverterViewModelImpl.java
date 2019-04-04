package foxsay.ru.loftcoin.screens.converter;

import android.os.Bundle;

import foxsay.ru.loftcoin.data.db.Database;
import foxsay.ru.loftcoin.data.db.model.CoinEntity;
import timber.log.Timber;

public class ConverterViewModelImpl implements ConverterViewModel {

    private Database database;

    private CoinEntity sourceCoin;
    private CoinEntity destinationCoin;

    private String sourceCurrencySymbol = "BTC";
    private String destinationCurrencySymbol = "ETH";

    ConverterViewModelImpl(Bundle savedInstanceState, Database database) {
        this.database = database;

        if (savedInstanceState != null) {

        }

        loadCoins();
    }

    private void loadCoins() {
        CoinEntity source = database.getCoin(sourceCurrencySymbol);
        CoinEntity destination = database.getCoin(destinationCurrencySymbol);


    }

    @Override
    public void onSourceAmountChange(String amount) {
        Timber.d("onSourceAmountChange: " + amount);
    }

}
