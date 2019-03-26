package foxsay.ru.loftcoin.screens.start;

import androidx.appcompat.app.AppCompatActivity;
import foxsay.ru.loftcoin.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class StartActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, StartActivity.class);
        context.startActivity(starter);
    }

    public static void startInNewTask(Context context) {
        Intent starter = new Intent(context, StartActivity.class);
        starter.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }
}
