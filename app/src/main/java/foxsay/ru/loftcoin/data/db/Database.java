package foxsay.ru.loftcoin.data.db;

import java.util.List;

import foxsay.ru.loftcoin.data.db.model.CoinEntity;
import foxsay.ru.loftcoin.data.db.model.Transaction;
import foxsay.ru.loftcoin.data.db.model.Wallet;
import io.reactivex.Flowable;

public interface Database {

    void open();

    void close();

    void saveCoins(List<CoinEntity> coins);

    Flowable<List<CoinEntity>> getCoins();

    CoinEntity getCoin(String symbol);

    void saveWallet(Wallet wallet);

    Flowable<List<Wallet>> getWallets();

    void saveTransaction(List<Transaction> transactions);

    Flowable<List<Transaction>> getTransactions(String walletId);

}