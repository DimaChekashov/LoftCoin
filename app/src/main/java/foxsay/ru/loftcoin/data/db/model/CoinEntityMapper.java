package foxsay.ru.loftcoin.data.db.model;

import java.util.List;

import foxsay.ru.loftcoin.data.api.model.Coin;

public interface CoinEntityMapper {

    List<CoinEntity> map(List<Coin> coins);

}
