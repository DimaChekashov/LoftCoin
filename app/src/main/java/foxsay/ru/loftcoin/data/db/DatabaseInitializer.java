package foxsay.ru.loftcoin.data.db;

import android.content.Context;

import androidx.room.Room;
import foxsay.ru.loftcoin.data.db.room.AppDatabase;
import foxsay.ru.loftcoin.data.db.room.DatabaseImplRoom;

public class DatabaseInitializer {

    public Database init(Context context) {
        AppDatabase appDatabase = Room.databaseBuilder(context, AppDatabase.class, "loftcoin.db")
                .fallbackToDestructiveMigration()
                .build();

        return new DatabaseImplRoom(appDatabase);
    }

}