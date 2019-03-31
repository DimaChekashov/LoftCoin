package foxsay.ru.loftcoin.data.db.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import foxsay.ru.loftcoin.data.api.model.Coin;

@Database(entities = {Coin.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CoinDao coinDao();

}