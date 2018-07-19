package com.capgemini.chess.algorithms.implementation.validators;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;

import java.util.HashSet;
import java.util.Set;

import static com.capgemini.chess.algorithms.implementation.validators.CoordinateValidator.isCoordinateOutOfBand;

public class KingValidator extends PieceValidator {

    private Coordinate coordinate;
    private Set possibleMoves = new HashSet<Move>();


    public KingValidator(Coordinate coordinateFrom, Board board, Color playerColor) {
        super(coordinateFrom, board, playerColor);
    }


    @Override
    public Set getMoves() {
        int coordX = coordinateFrom.getX();
        int coordY = coordinateFrom.getY();

        //Move 1
        coordinate = new Coordinate(coordX + 1, coordY);
        if (!isCoordinateOutOfBand(coordinate)) {
            addProperMove(possibleMoves, coordinate, false, false);
        }

        //Move 2
        coordinate = new Coordinate(coordX + 1, coordY + 1);
        if (!isCoordinateOutOfBand(coordinate)) {
            addProperMove(possibleMoves, coordinate, false, false);
        }

        //Move 3
        coordinate = new Coordinate(coordX, coordY + 1);
        if (!isCoordinateOutOfBand(coordinate)) {
            addProperMove(possibleMoves, coordinate, false, false);
        }

        //Move 4
        coordinate = new Coordinate(coordX - 1, coordY);
        if (!isCoordinateOutOfBand(coordinate)) {
            addProperMove(possibleMoves, coordinate, false, false);
        }

        //Move 5
        coordinate = new Coordinate(coordX - 1, coordY - 1);
        if (!isCoordinateOutOfBand(coordinate)) {
            addProperMove(possibleMoves, coordinate, false, false);
        }

        //Move 6
        coordinate = new Coordinate(coordX, coordY - 1);
        if (!isCoordinateOutOfBand(coordinate)) {
            addProperMove(possibleMoves, coordinate, false, false);
        }

        //Move 7
        coordinate = new Coordinate(coordX + 1, coordY - 1);
        if (!isCoordinateOutOfBand(coordinate)) {
            addProperMove(possibleMoves, coordinate, false, false);
        }

        //Move 8
        coordinate = new Coordinate(coordX - 1, coordY + 1);
        if (!isCoordinateOutOfBand(coordinate)) {
            addProperMove(possibleMoves, coordinate, false, false);
        }

        return possibleMoves;
    }
}
