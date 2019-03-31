package foxsay.ru.loftcoin.data.db;

import java.util.List;

import foxsay.ru.loftcoin.data.db.model.CoinEntity;

public interface Database {

    void saveCoins(List<CoinEntity> coins);

    List<CoinEntity> getCoins();

}
