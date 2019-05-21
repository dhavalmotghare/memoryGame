package com.dhaval.memorygame.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dhaval.memorygame.R;
import com.dhaval.memorygame.model.Game;

public class GameBoardAdapter extends RecyclerView.Adapter<GameBoardAdapter.ViewHolder> {

    private Game game;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public GameBoardAdapter(Context context, Game game) {
        this.mInflater = LayoutInflater.from(context);
        this.game = game;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.grid_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (game.getRevealedCells()[position]) {
            holder.myTextView.setText(game.getBoardValues()[position] + "");
        } else {
            // don't show anything unless it is revealed
        }
    }

    @Override
    public int getItemCount() {
        return game.getBoardValues().length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.grid_content);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public int getItem(int id) {
        return game.getBoardValues()[id];
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
