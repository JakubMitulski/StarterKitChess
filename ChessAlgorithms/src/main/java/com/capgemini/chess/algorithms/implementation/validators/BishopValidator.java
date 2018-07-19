package com.capgemini.chess.algorithms.implementation.validators;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;

import java.util.HashSet;
import java.util.Set;

import static com.capgemini.chess.algorithms.implementation.validators.CoordinateValidator.isEmptySpot;

public class BishopValidator extends PieceValidator {

    private int BOARD_START = 0;
    private int BOARD_END = 7;
    private Coordinate coordinate;

    public BishopValidator(Coordinate coordinateFrom, Board board, Color playerColor) {
        super(coordinateFrom, board, playerColor);
    }

    @Override
    public Set getMoves() {
        Set possibleMoves = new HashSet<Move>();

        //Move 1 - right-up direction
        int coordX = coordinateFrom.getX();
        int coordY = coordinateFrom.getY();
        for (int i = coordX; i < BOARD_END; i++) {
            if (coordY < BOARD_END) {
                coordinate = new Coordinate(i + 1, coordY + 1);
                coordY++;
                addProperMove(possibleMoves, coordinate, false, false);
                if (!isEmptySpot(coordinate, board)) {
                    break;
                }
            }
        }

        //Move 2 - left-up direction
        coordX = coordinateFrom.getX();
        coordY = coordinateFrom.getY();
        for (int i = coordX; i > BOARD_START; i--) {
            if (coordY < BOARD_END) {
                coordinate = new Coordinate(Math.abs(i - 1), coordY + 1);
                coordY++;
                addProperMove(possibleMoves, coordinate, false, false);
                if (!isEmptySpot(coordinate, board)) {
                    break;
                }
            }
        }

        //Move 3 - left-down direction
        coordX = coordinateFrom.getX();
        coordY = coordinateFrom.getY();
        for (int i = coordX; i > BOARD_START; i--) {
            if (coordY > BOARD_START) {
                coordinate = new Coordinate(Math.abs(i - 1), Math.abs(coordY - 1));
                coordY--;
                addProperMove(possibleMoves, coordinate, false, false);
                if (!isEmptySpot(coordinate, board)) {
                    break;
                }
            }
        }

        //Move 4 - right-down direction
        coordX = coordinateFrom.getX();
        coordY = coordinateFrom.getY();
        for (int i = coordX; i < BOARD_END; i++) {
            if (coordY > BOARD_START) {
                coordinate = new Coordinate(i + 1, Math.abs(coordY - 1));
                coordY--;
                addProperMove(possibleMoves, coordinate, false, false);
                if (!isEmptySpot(coordinate, board)) {
                    break;
                }
            }
        }

        return possibleMoves;
    }
}
