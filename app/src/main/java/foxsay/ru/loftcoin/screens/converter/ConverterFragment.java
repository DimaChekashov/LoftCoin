package foxsay.ru.loftcoin.screens.converter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import foxsay.ru.loftcoin.R;

public class ConverterFragment extends Fragment {


    public ConverterFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.converter_toolbar)
    Toolbar toolbar;

    @BindView(R.id.source_currency)
    ViewGroup sourceCurrency;

    @BindView(R.id.source_amount)
    EditText sourceAmount;

    @BindView(R.id.destination_currency)
    ViewGroup destinationCurrency;

    @BindView(R.id.destination_amount)
    TextView destinationAmount;

    TextView sourceCurrencySymbolText;
    TextView sourceCurrencySymbolName;

    TextView destinationCurrencySymbolText;
    TextView destinationCurrencySymbolName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_converter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        toolbar.setTitle(R.string.converter_screen_title);

        sourceCurrencySymbolText = sourceCurrency.findViewById(R.id.symbol_text);
        sourceCurrencySymbolName = sourceCurrency.findViewById(R.id.currency_name);

        destinationCurrencySymbolText = destinationCurrency.findViewById(R.id.symbol_text);
        destinationCurrencySymbolName = destinationCurrency.findViewById(R.id.currency_name);

    }
}
