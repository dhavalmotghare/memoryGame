package com.dhaval.memorygame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dhaval.memorygame.model.Game;
import com.dhaval.memorygame.view.GameBoardAdapter;
import com.dhaval.memorygame.view.GamePlayContract;
import com.dhaval.memorygame.view.GamePlayPresenter;

public class MainActivity extends AppCompatActivity implements GamePlayContract.View, GameBoardAdapter.ItemClickListener {

    private GameBoardAdapter gameBoardAdapter;
    private GamePlayPresenter gamePlayPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gamePlayPresenter = new GamePlayPresenter();
    }

    @Override
    public void onStart() {
        super.onStart();
        gamePlayPresenter.attach(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        gamePlayPresenter.detach();
    }

    @Override
    public void onGameInitialized(Game game) {
        RecyclerView recyclerView = findViewById(R.id.game_board);
        recyclerView.setLayoutManager(new GridLayoutManager(this, game.getColumn()));
        gameBoardAdapter = new GameBoardAdapter(this, game.getBoardValues());
        gameBoardAdapter.setClickListener(this);
        recyclerView.setAdapter(gameBoardAdapter);
    }

    @Override
    public void onGamePaused() {

    }

    @Override
    public void onGameResumed() {

    }

    @Override
    public void onItemClick(View view, int position) {
        gamePlayPresenter.onItemClicked(position);
    }
}
