package com.capgemini.chess.algorithms.implementation.validators;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;

import java.util.HashSet;
import java.util.Set;

import static com.capgemini.chess.algorithms.implementation.validators.CoordinateValidator.isEmptySpot;

public class RookValidator extends PieceValidator {

    private int BOARD_START = 0;
    private int BOARD_END = 7;
    private Coordinate coordinate;

    public RookValidator(Coordinate coordinateFrom, Board board, Color playerColor) {
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
            if (isPlayerPiece(coordinate)) {
                break;
            }
            addAtackOrCaptureMove(possibleMoves, coordinate);
            if (!isEmptySpot(coordinate, board)) {
                break;
            }
        }

        //Move 2 - X axis, - direction
        for (int i = conX; i > BOARD_START; i--) {
            coordinate = new Coordinate(i - 1, conY);
            if (isPlayerPiece(coordinate)) {
                break;
            }
            addAtackOrCaptureMove(possibleMoves, coordinate);
            if (!isEmptySpot(coordinate, board)) {
                break;
            }
        }

        //Move 3 - Y axis, + direction
        for (int i = conY; i < BOARD_END; i++) {
            coordinate = new Coordinate(conX, i + 1);
            if (isPlayerPiece(coordinate)) {
                break;
            }
            addAtackOrCaptureMove(possibleMoves, coordinate);
            if (!isEmptySpot(coordinate, board)) {
                break;
            }
        }

        //Move 4 - Y axis, - direction
        for (int i = conY; i > BOARD_START; i--) {
            coordinate = new Coordinate(conX, i - 1);
            if (isPlayerPiece(coordinate)) {
                break;
            }
            addAtackOrCaptureMove(possibleMoves, coordinate);
            if (!isEmptySpot(coordinate, board)) {
                break;
            }
        }

        return possibleMoves;
    }
}