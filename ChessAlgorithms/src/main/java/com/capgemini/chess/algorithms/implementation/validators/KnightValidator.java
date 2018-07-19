package com.capgemini.chess.algorithms.implementation.validators;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;

import java.util.HashSet;
import java.util.Set;

import static com.capgemini.chess.algorithms.implementation.validators.CoordinateValidator.isCoordinateOutOfBand;

public class KnightValidator extends PieceValidator {

    private Coordinate coordinate;

    public KnightValidator(Coordinate coordinateFrom, Board board, Color playerColor) {
        super(coordinateFrom, board, playerColor);
    }

    @Override
    public Set getMoves() {
        Set possibleMoves = new HashSet<Move>();
        int coordX = coordinateFrom.getX();
        int coordY = coordinateFrom.getY();

        //Move 1: right-up direction in Y-axis
        coordinate = new Coordinate(coordX + 1, coordY + 2);
        if (!isCoordinateOutOfBand(coordinate)) {
            addProperMove(possibleMoves, coordinate, false);
        }

        //Move 2: right-up direction in X-axis
        coordinate = new Coordinate(coordX + 2, coordY + 1);
        if (!isCoordinateOutOfBand(coordinate)) {
            addProperMove(possibleMoves, coordinate, false);
        }

        //Move 3: right-down direction in Y-axis
        coordinate = new Coordinate(coordX + 1, coordY - 2);
        if (!isCoordinateOutOfBand(coordinate)) {
            addProperMove(possibleMoves, coordinate, false);
        }

        //Move 4: right-down direction in X-axis
        coordinate = new Coordinate(coordX + 2, coordY - 1);
        if (!isCoordinateOutOfBand(coordinate)) {
            addProperMove(possibleMoves, coordinate, false);
        }

        //Move 5: left-up direction in Y-axis
        coordinate = new Coordinate(coordX - 1, coordY + 2);
        if (!isCoordinateOutOfBand(coordinate)) {
            addProperMove(possibleMoves, coordinate, false);
        }

        //Move 6: left-up direction in X-axis
        coordinate = new Coordinate(coordX - 2, coordY + 1);
        if (!isCoordinateOutOfBand(coordinate)) {
            addProperMove(possibleMoves, coordinate, false);
        }

        //Move 7: left-down direction in Y-axis
        coordinate = new Coordinate(coordX - 1, coordY - 2);
        if (!isCoordinateOutOfBand(coordinate)) {
            addProperMove(possibleMoves, coordinate, false);
        }

        //Move 8: left-down direction in X-axis
        coordinate = new Coordinate(coordX - 2, coordY - 1);
        if (!isCoordinateOutOfBand(coordinate)) {
            addProperMove(possibleMoves, coordinate, false);
        }

        return possibleMoves;
    }
}