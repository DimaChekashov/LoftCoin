package foxsay.ru.loftcoin.data.db.model;


import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Coin")
public class CoinEntity {

    @PrimaryKey
    public int id;

    public String name;

    public String symbol;

    public String slug;

    public String lastUpdated;

    @Embedded(prefix = "usd_")
    public QuoteEntity usd;

    @Embedded(prefix = "eur_")
    public QuoteEntity eur;

    @Embedded(prefix = "rub_")
    public QuoteEntity rub;

}
