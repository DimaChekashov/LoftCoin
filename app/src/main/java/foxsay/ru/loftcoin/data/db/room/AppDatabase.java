package foxsay.ru.loftcoin.data.db.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import foxsay.ru.loftcoin.data.db.model.CoinEntity;
import foxsay.ru.loftcoin.data.db.model.Wallet;

@Database(entities = {CoinEntity.class, Wallet.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CoinDao coinDao();

    public abstract WalletDao walletDao();

}