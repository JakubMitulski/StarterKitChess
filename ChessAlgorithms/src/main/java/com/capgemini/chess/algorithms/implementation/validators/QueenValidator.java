package com.capgemini.chess.algorithms.implementation.validators;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;

import java.util.HashSet;
import java.util.Set;

public class QueenValidator extends PieceValidator {

    private int BOARD_START = 0;
    private int BOARD_END = 7;
    private Coordinate coordinate;

    public QueenValidator(Coordinate coordinateFrom, Board board, Color playerColor) {
        super(coordinateFrom, board, playerColor);
    }

    @Override
    public Set getMoves() {
        Set possibleMoves = new HashSet<Move>();
        int conX = coordinateFrom.getX();
        int conY = coordinateFrom.getY();


        //Move 1 - X axis, + direction
        for (int i = conX; i < BOARD_END; i++) {
            coordinate = new Coordinate(i + 1, conY);
            addProperMove(possibleMoves, coordinate);
        }

        //Move 2 - X axis, - direction
        for (int i = conX; i > BOARD_START; i--) {
            coordinate = new Coordinate(i - 1, conY);
            addProperMove(possibleMoves, coordinate);
        }

        //Move 3 - Y axis, + direction
        for (int i = conY; i < BOARD_END; i++) {
            coordinate = new Coordinate(conX, i + 1);
            addProperMove(possibleMoves, coordinate);
        }

        //Move 4 - Y axis, - direction
        for (int i = conY; i > BOARD_START; i--) {
            coordinate = new Coordinate(conX, i - 1);
            addProperMove(possibleMoves, coordinate);
        }

        //Move 5 - right-up direction
        int coordX = coordinateFrom.getX();
        int coordY = coordinateFrom.getY();
        for (int i = coordX; i < BOARD_END; i++) {
            if (coordY < BOARD_END) {
                coordinate = new Coordinate(i + 1, coordY + 1);
                coordY++;
                addProperMove(possibleMoves, coordinate);
            }
        }

        //Move 6 - left-up direction
        coordX = coordinateFrom.getX();
        coordY = coordinateFrom.getY();
        for (int i = coordX; i > BOARD_START; i--) {
            if (coordY < BOARD_END) {
                coordinate = new Coordinate(Math.abs(i - 1), coordY + 1);
                coordY++;
                addProperMove(possibleMoves, coordinate);
            }
        }

        //Move 7 - left-down direction
        coordX = coordinateFrom.getX();
        coordY = coordinateFrom.getY();
        for (int i = coordX; i > BOARD_START; i--) {
            if (coordY > BOARD_START) {
                coordinate = new Coordinate(Math.abs(i - 1), Math.abs(coordY - 1));
                coordY--;
                addProperMove(possibleMoves, coordinate);
            }
        }

        //Move 8 - right-down direction
        coordX = coordinateFrom.getX();
        coordY = coordinateFrom.getY();
        for (int i = coordX; i < BOARD_END; i++) {
            if (coordY > BOARD_START) {
                coordinate = new Coordinate(i + 1, Math.abs(coordY - 1));
                coordY--;
                addProperMove(possibleMoves, coordinate);
            }
        }

        return possibleMoves;
    }
}
