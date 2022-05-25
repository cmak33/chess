package com.chess.chessapplication.models.board;

import com.chess.chessapplication.models.common.Color;

import java.util.List;

public class TurnSwitcher {
    protected List<Color> players;
    private int playerIndex;

    public TurnSwitcher(List<Color> players){
        this.players = players;
    }

    public Color getCurrentColor(){
        return players.get(playerIndex);
    }

    public List<Color> getPlayers(){
        return players;
    }

    public void switchToNextPlayerTurn(){
        changePlayerTurn(1);
    }

    public void switchToPreviousPlayerTurn(){
        changePlayerTurn(-1);
    }

    private void changePlayerTurn(int offset){
        playerIndex = Math.abs(playerIndex + offset) % players.size();
    }

}
