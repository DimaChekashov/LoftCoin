package foxsay.ru.loftcoin.screens.converter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding3.widget.RxTextView;
import com.jakewharton.rxbinding3.widget.TextViewAfterTextChangeEvent;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import foxsay.ru.loftcoin.App;
import foxsay.ru.loftcoin.R;
import foxsay.ru.loftcoin.data.db.Database;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class ConverterFragment extends Fragment {


    public ConverterFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.converter_toolbar)
    Toolbar toolbar;

    @BindView(R.id.source_currency)
    ViewGroup sourceCurrency;

    @BindView(R.id.source_amount)
    AppCompatEditText sourceAmount;

    @BindView(R.id.destination_currency)
    ViewGroup destinationCurrency;

    @BindView(R.id.destination_amount)
    TextView destinationAmount;

    TextView sourceCurrencySymbolText;
    TextView sourceCurrencySymbolName;

    TextView destinationCurrencySymbolText;
    TextView destinationCurrencySymbolName;

    private ConverterViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_converter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        Database database = ((App) getActivity().getApplication()).getDatabase();
        viewModel = new ConverterViewModelImpl(savedInstanceState, database);

        toolbar.setTitle(R.string.converter_screen_title);

        sourceCurrencySymbolText = sourceCurrency.findViewById(R.id.symbol_text);
        sourceCurrencySymbolName = sourceCurrency.findViewById(R.id.currency_name);

        destinationCurrencySymbolText = destinationCurrency.findViewById(R.id.symbol_text);
        destinationCurrencySymbolName = destinationCurrency.findViewById(R.id.currency_name);

        initOutputs();
        initInputs();
    }

    private void initOutputs() {

        Disposable disposable1 = RxTextView.afterTextChangeEvents(sourceAmount)
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(event -> {
                    viewModel.onSourceAmountChange(event.getEditable().toString());
                });

    }

    private void initInputs() {

    }
}
