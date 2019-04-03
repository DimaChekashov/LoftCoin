package foxsay.ru.loftcoin.screens.rate;


import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import foxsay.ru.loftcoin.App;
import foxsay.ru.loftcoin.R;
import foxsay.ru.loftcoin.data.api.Api;
import foxsay.ru.loftcoin.data.api.model.Coin;
import foxsay.ru.loftcoin.data.db.Database;
import foxsay.ru.loftcoin.data.db.model.CoinEntity;
import foxsay.ru.loftcoin.data.db.model.CoinEntityMapper;
import foxsay.ru.loftcoin.data.db.model.CoinEntityMapperImpl;
import foxsay.ru.loftcoin.data.prefs.Prefs;
import foxsay.ru.loftcoin.utils.Fiat;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class RateFragment extends Fragment implements RateView, Toolbar.OnMenuItemClickListener, CurrencyDialog.CurrencyDialogListener {

    private static final String LAYOUT_MANAGER_STATE = "layout_manager_state";

    public RateFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recycler)
    RecyclerView recycler;

    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    @BindView(R.id.content)
    ViewGroup content;

    private RatePresenter presenter;
    private RateAdapter adapter;

    public Parcelable layoutManagerState;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Activity activity = getActivity();

        if (activity == null) {
            return;
        }

        Api api = ((App) getActivity().getApplication()).getApi();
        Prefs prefs = ((App) getActivity().getApplication()).getPrefs();
        Database database = ((App) getActivity().getApplication()).getDatabase();
        CoinEntityMapper mapper = new CoinEntityMapperImpl();

        presenter = new RatePresenterImpl(prefs, api, database, mapper);
        adapter = new RateAdapter(prefs);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rate, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        toolbar.setTitle(R.string.rate_screen_title);
        toolbar.inflateMenu(R.menu.menu_rate);
        toolbar.setOnMenuItemClickListener(this);

        recycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        recycler.setHasFixedSize(true);
        recycler.setAdapter(adapter);

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onRefresh();
            }
        });

        if (savedInstanceState != null) {
            layoutManagerState = savedInstanceState.getParcelable(LAYOUT_MANAGER_STATE);
        }

        presenter.attachView(this);
        presenter.getRate();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(LAYOUT_MANAGER_STATE, recycler.getLayoutManager().onSaveInstanceState());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void setCoins(List<CoinEntity> coins) {
        Timber.d("setCoins: " + coins);
        adapter.setItems(coins);

        if (layoutManagerState != null) {
            recycler.getLayoutManager().onRestoreInstanceState(layoutManagerState);
            layoutManagerState = null;
        }
    }

    @Override
    public void setRefreshing(Boolean refreshing) {
        refresh.setRefreshing(refreshing);
    }

    @Override
    public void showCurrencyDialog() {
        CurrencyDialog dialog = new CurrencyDialog();
        dialog.show(getFragmentManager(), CurrencyDialog.TAG);
        dialog.setListener(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_currency:
                presenter.onMenuItemCurrencyClick();
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onCurrencySelected(Fiat currency) {
        presenter.onFiatCurrencySelected(currency);
    }

    @Override
    public void invalidateRates() {
        adapter.notifyDataSetChanged();
    }
}
