package com.dhaval.memorygame.view;

import com.dhaval.memorygame.model.Game;

public class GamePlayPresenter implements GamePlayContract.Presenter {

    private Game currentGame;
    private GamePlayContract.View view = null;

    public GamePlayPresenter() {
    }

    @Override
    public void attach(GamePlayContract.View view) {
        this.view = view;
        currentGame = Game.getGame(1);
        view.onGameInitialized(currentGame);
    }

    @Override
    public void detach() {
        view = null;
    }

    @Override
    public void onItemClicked(int position) {
        currentGame.setCellRevealed(position);
    }

    @Override
    public void onGamePaused() {

    }

    @Override
    public void onGameResumed() {

    }

}
