package com.capgemini.chess.algorithms.implementation.validators;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;

import java.util.HashSet;
import java.util.Set;

import static com.capgemini.chess.algorithms.implementation.validators.CoordinateValidator.isCoordinateOutOfBand;

public class PawnValidator extends PieceValidator {

    private Coordinate coordinate;
    private int BOARD_START = 0;
    private int BOARD_END = 7;

    public PawnValidator(Coordinate coordinateFrom, Board board, Color playerColor) {
        super(coordinateFrom, board, playerColor);
    }

    @Override
    public Set getMoves() {
        Set possibleMoves = new HashSet<Move>();
        int coordX = coordinateFrom.getX();
        int coordY = coordinateFrom.getY();

        //Move 1: first move ever, up direction, +2 spots
        if (coordY == 1) {
            coordinate = new Coordinate(coordX, coordY + 2);
            if (isFieldEmpty(coordinate)) {
                addProperMove(possibleMoves, coordinate);
            }
        }

        //Move 2: first move ever, down direction, -2 spots
        if (coordY == 6) {
            coordinate = new Coordinate(coordX, coordY - 2);
            if (isFieldEmpty(coordinate)) {
                addProperMove(possibleMoves, coordinate);
            }
        }

        //Move 3: regular move, up direction
        if (playerColor == Color.WHITE) {
            coordinate = new Coordinate(coordX, coordY + 1);
            if (!isCoordinateOutOfBand(coordinate) && isFieldEmpty(coordinate)) {
                addProperMove(possibleMoves, coordinate);
            }
        }

        //Move 4: regular move, down direction
        if (playerColor == Color.BLACK) {
            coordinate = new Coordinate(coordX, coordY - 1);
            if (!isCoordinateOutOfBand(coordinate) && isFieldEmpty(coordinate)) {
                addProperMove(possibleMoves, coordinate);
            }
        }

        //Move 5: capture move, up-right direction
        if (playerColor == Color.WHITE) {
            coordinate = new Coordinate(coordX + 1, coordY + 1);
            if (!isCoordinateOutOfBand(coordinate)) {
                if (!isFieldEmpty(coordinate) && board.getPieceAt(coordinate).getColor() != playerColor) {
                    addProperMove(possibleMoves, coordinate);
                }
            }
        }

        //Move 6: capture move, up-left direction
        if (playerColor == Color.WHITE) {
            coordinate = new Coordinate(coordX - 1, coordY + 1);
            if (!isCoordinateOutOfBand(coordinate)) {
                if (!isFieldEmpty(coordinate) && board.getPieceAt(coordinate).getColor() != playerColor) {
                    addProperMove(possibleMoves, coordinate);
                }
            }
        }

        //Move 7: capture move, down-right direction
        if (playerColor == Color.BLACK) {
            coordinate = new Coordinate(coordX + 1, coordY - 1);
            if (!isCoordinateOutOfBand(coordinate)) {
                if (!isFieldEmpty(coordinate) && board.getPieceAt(coordinate).getColor() != playerColor) {
                    addProperMove(possibleMoves, coordinate);
                }
            }
        }

        //Move 8: capture move, down-left direction
        if (playerColor == Color.BLACK) {
            coordinate = new Coordinate(coordX - 1, coordY - 1);
            if (!isCoordinateOutOfBand(coordinate)) {
                if (!isFieldEmpty(coordinate) && board.getPieceAt(coordinate).getColor() != playerColor) {
                    addProperMove(possibleMoves, coordinate);
                }
            }
        }

        return possibleMoves;
    }
}
