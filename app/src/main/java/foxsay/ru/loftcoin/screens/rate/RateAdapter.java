package foxsay.ru.loftcoin.screens.rate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import foxsay.ru.loftcoin.R;
import foxsay.ru.loftcoin.data.api.model.Coin;

public class RateAdapter extends RecyclerView.Adapter<RateAdapter.RateViewHolder> {

    private List<Coin> items = Collections.emptyList();

    public void setItems(List<Coin> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rate, parent, false);
        return new RateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RateViewHolder holder, int position) {
        holder.bind(items.get(position), position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class RateViewHolder extends RecyclerView.ViewHolder {

        public RateViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(Coin coin, int position) {

        }
    }
}
