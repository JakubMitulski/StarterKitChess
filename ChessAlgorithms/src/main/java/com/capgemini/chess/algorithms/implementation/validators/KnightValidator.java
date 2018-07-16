package com.capgemini.chess.algorithms.implementation.validators;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;

import java.util.HashSet;
import java.util.Set;

public class KnightValidator extends PieceValidator {

    private Coordinate coordinate;

    public KnightValidator(Coordinate coordinateFrom, Board board, Color playerColor) {
        super(coordinateFrom, board, playerColor);
    }

    @Override
    public Set getMoves() {
        Set possibleMoves = new HashSet<Move>();

        //Move 1: right-up direction in Y-axis
        coordinate = new Coordinate(coordinateFrom.getX() + 1, coordinateFrom.getY() + 2);
        addProperMove(possibleMoves, coordinate);

        //Move 2: right-up direction in X-axis
        coordinate = new Coordinate(coordinateFrom.getX() + 2, coordinateFrom.getY() + 1);
        addProperMove(possibleMoves, coordinate);

        //Move 3: right-down direction in Y-axis
        coordinate = new Coordinate(coordinateFrom.getX() + 1, Math.abs(coordinateFrom.getY() - 2));
        addProperMove(possibleMoves, coordinate);

        //Move 4: right-down direction in X-axis
        coordinate = new Coordinate(coordinateFrom.getX() + 2, Math.abs(coordinateFrom.getY() - 1));
        addProperMove(possibleMoves, coordinate);

        //Move 5: left-up direction in Y-axis
        coordinate = new Coordinate(Math.abs(coordinateFrom.getX() - 1), coordinateFrom.getY() + 2);
        addProperMove(possibleMoves, coordinate);

        //Move 6: left-up direction in X-axis
        coordinate = new Coordinate(Math.abs(coordinateFrom.getX() - 2), coordinateFrom.getY() + 1);
        addProperMove(possibleMoves, coordinate);

        //Move 7: left-down direction in Y-axis
        coordinate = new Coordinate(Math.abs(coordinateFrom.getX() - 1), Math.abs(coordinateFrom.getY() - 2));
        addProperMove(possibleMoves, coordinate);

        //Move 8: left-down direction in X-axis
        coordinate = new Coordinate(Math.abs(coordinateFrom.getX() - 2), Math.abs(coordinateFrom.getY() - 1));
        addProperMove(possibleMoves, coordinate);

        return possibleMoves;
    }
}
