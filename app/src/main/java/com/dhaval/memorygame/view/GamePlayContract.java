package com.dhaval.memorygame.view;

import com.dhaval.memorygame.model.Game;

/**
 * MVP contract for game play.
 */
public interface GamePlayContract {

    /**
     * Callbacks to notify the view of the outcome of game.
     */
    interface View {
        /**
         * Call when game is initialized.
         */
        void onGameInitialized(Game game);

        void onEndGame();

    }

    interface Presenter {
        /**
         * Call to attach a [Presenter] and provide its [View].
         */
        void attach(View view);

        /**
         * Call to detach a [Presenter] and clean up resources.
         */
        void detach();

        void onItemClicked(int position);

        /**
         * Notifies the presenter that the user has paused the game.
         */
        void onGamePaused();

        /**
         * Notifies the presenter that the user has resumed the game.
         */
        void onGameResumed();
    }
}