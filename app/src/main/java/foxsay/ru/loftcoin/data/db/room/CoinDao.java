package foxsay.ru.loftcoin.data.db.room;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import foxsay.ru.loftcoin.data.db.model.CoinEntity;
import io.reactivex.Flowable;

@Dao
public interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveCoins(List<CoinEntity> coins);


    @Query("SELECT * FROM Coin")
    Flowable<List<CoinEntity>> getCoins();

}
