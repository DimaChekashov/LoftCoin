package foxsay.ru.loftcoin.screens.wallets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import foxsay.ru.loftcoin.R;
import foxsay.ru.loftcoin.data.db.model.QuoteEntity;
import foxsay.ru.loftcoin.data.db.model.WalletModel;
import foxsay.ru.loftcoin.data.prefs.Prefs;
import foxsay.ru.loftcoin.utils.CurrencyFormatter;
import foxsay.ru.loftcoin.utils.Fiat;

public class WalletsPagerAdapter extends PagerAdapter {

    private static final String TAG = "WalletsPagerAdapter";

    private List<WalletModel> wallets = Collections.emptyList();

    private Prefs prefs;

    public WalletsPagerAdapter(Prefs prefs) {
        this.prefs = prefs;
    }

    public void setWallets(List<WalletModel> wallets) {
        this.wallets = wallets;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return wallets.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v = LayoutInflater.from(container.getContext()).inflate(R.layout.item_wallet, container, false);

        WalletViewHolder viewHolder = new WalletViewHolder(v, prefs);
        viewHolder.bind(wallets.get(position));

        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }


    static class WalletViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.symbol_text)
        TextView symbolText;

        @BindView(R.id.currency)
        TextView currency;

        @BindView(R.id.primary_amount)
        TextView primaryAmount;

        @BindView(R.id.secondary_amount)
        TextView secondaryAmount;


        private Random random = new Random();

        private CurrencyFormatter currencyFormatter = new CurrencyFormatter();

        private Context context;

        private Prefs prefs;

        private static int[] colors = {
                0xFFF5FF30,
                0xFFFFFFFF,
                0xFF2ABDF5,
                0xFFFF7416,
                0xFFFF7416,
                0xFF534FFF,
        };

        WalletViewHolder(View itemView, Prefs prefs) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            this.prefs = prefs;
        }

        void bind(WalletModel model) {
            bindCurrency(model);
            bindSymbol(model);
            bindPrimaryAmount(model);
            bindSecondaryAmount(model);
        }

        private void bindCurrency(WalletModel model) {
            currency.setText(model.coin.symbol);
        }


        private void bindSymbol(WalletModel model) {
            symbolText.setVisibility(View.VISIBLE);

            Drawable background = symbolText.getBackground();
            Drawable wrapped = DrawableCompat.wrap(background);
            DrawableCompat.setTint(wrapped, colors[random.nextInt(colors.length)]);

            symbolText.setText(String.valueOf(model.coin.symbol.charAt(0)));
        }

        private void bindPrimaryAmount(WalletModel model) {
            String value = currencyFormatter.format(model.wallet.amount, true);
            primaryAmount.setText(itemView.getContext().getString(R.string.currency_amount, value, model.coin.symbol));
        }

        private void bindSecondaryAmount(WalletModel model) {

            Fiat fiat = prefs.getFiatCurrency();
            QuoteEntity quote = model.coin.getQuote(fiat);

            double amount = model.wallet.amount * quote.price;
            String value = currencyFormatter.format(amount, false);

            secondaryAmount.setText(itemView.getContext().getString(R.string.currency_amount, value, fiat.symbol));
        }

    }
}
