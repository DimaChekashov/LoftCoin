package foxsay.ru.loftcoin.screens.start;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import foxsay.ru.loftcoin.App;
import foxsay.ru.loftcoin.R;
import foxsay.ru.loftcoin.data.api.Api;
import foxsay.ru.loftcoin.data.prefs.Prefs;
import foxsay.ru.loftcoin.screens.main.MainActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class StartActivity extends AppCompatActivity implements StartView {

    public static void start(Context context) {
        Intent starter = new Intent(context, StartActivity.class);
        context.startActivity(starter);
    }

    public static void startInNewTask(Context context) {
        Intent starter = new Intent(context, StartActivity.class);
        starter.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(starter);
    }

    @BindView(R.id.start_top_corner)
    ImageView topCorner;

    @BindView(R.id.start_bottom_corner)
    ImageView bottomCorner;

    private StartPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);

        Prefs prefs = ((App) getApplication()).getPrefs();
        Api api = ((App) getApplication()).getApi();

        presenter = new StartPresenterImpl(prefs, api);

        presenter.attachView(this);

        presenter.loadRates();
    }

    @Override
    protected void onStart() {
        super.onStart();

        startAnimation();
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public void navigateToMainScreen() {
        MainActivity.start(this);
    }

    private void startAnimation() {

        ObjectAnimator innerAnimation = ObjectAnimator.ofFloat(topCorner, "translationY", 0f, -100f);
        innerAnimation.setDuration(4000);
        innerAnimation.setRepeatMode(ValueAnimator.REVERSE);
        innerAnimation.setRepeatCount(ValueAnimator.INFINITE);
        innerAnimation.setInterpolator(new LinearInterpolator());


        ObjectAnimator innerAnimationRotate = ObjectAnimator.ofFloat(topCorner, "rotation", 0, 360);
        innerAnimationRotate.setDuration(20000);
        innerAnimationRotate.setRepeatMode(ValueAnimator.RESTART);
        innerAnimationRotate.setRepeatCount(ValueAnimator.INFINITE);
        innerAnimationRotate.setInterpolator(new LinearInterpolator());

        ObjectAnimator outerAnimation = ObjectAnimator.ofFloat(bottomCorner, "rotation", 0, 360);
        outerAnimation.setDuration(30000);
        outerAnimation.setRepeatMode(ValueAnimator.RESTART);
        outerAnimation.setRepeatCount(ValueAnimator.INFINITE);
        outerAnimation.setInterpolator(new LinearInterpolator());

        AnimatorSet set = new AnimatorSet();
        set.playTogether(innerAnimation, innerAnimationRotate, outerAnimation);
        set.start();

    }
}
