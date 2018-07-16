package com.capgemini.chess.algorithms.implementation.validators;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;

import java.util.HashSet;
import java.util.Set;

public class BishopValidator extends PieceValidator {

    private int BOARD_START = 0;
    private int BOARD_END = 7;
    private Coordinate coordinate;

    public BishopValidator(Coordinate coordinateFrom, Board board, Color opponentColor) {
        super(coordinateFrom, board, opponentColor);
    }

    @Override
    public Set getMoves() {
        Set possibleMoves = new HashSet<Move>();
        int conX = coordinateFrom.getX();
        int conY = coordinateFrom.getY();

        //Move 1 - right-up direction
        for (int i = conX; i < BOARD_END; i++) {
            if (conY < BOARD_END) {
                coordinate = new Coordinate(i + 1, conY + 1);
                conY++;
                addProperMove(possibleMoves, coordinate);
            }
        }

        //Move 2 - left-up direction
        for (int i = conX; i > BOARD_START; i--) {
            if (conY < BOARD_END) {
                coordinate = new Coordinate(Math.abs(i - 1), conY + 1);
                conY++;
                addProperMove(possibleMoves, coordinate);
            }
        }

        //Move 3 - left-down direction
        for (int i = conX; i > BOARD_START; i--) {
            if (conY > BOARD_START) {
                coordinate = new Coordinate(Math.abs(i - 1), Math.abs(conY - 1));
                conY--;
                addProperMove(possibleMoves, coordinate);
            }
        }

        //Move 4 - right-down direction
        for (int i = conX; i < BOARD_END; i++) {
            if (conY > BOARD_START) {
                coordinate = new Coordinate(i + 1, Math.abs(conY - 1));
                conY--;
                addProperMove(possibleMoves, coordinate);
            }
        }

        return possibleMoves;
    }
}
