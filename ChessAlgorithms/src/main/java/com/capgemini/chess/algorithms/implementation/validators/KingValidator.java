package com.capgemini.chess.algorithms.implementation.validators;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;

import java.util.HashSet;
import java.util.Set;

public class KingValidator extends PieceValidator {

    private Coordinate coordinate;

    public KingValidator(Coordinate coordinateFrom, Board board, Color playerColor) {
        super(coordinateFrom, board, playerColor);
    }


    @Override
    public Set getMoves() {
        Set possibleMoves = new HashSet<Move>();

        //Move 1
        coordinate = new Coordinate(coordinateFrom.getX() + 1, coordinateFrom.getY());
        addProperMove(possibleMoves, coordinate);

        //Move 2
        coordinate = new Coordinate(coordinateFrom.getX() + 1, coordinateFrom.getY() + 1);
        addProperMove(possibleMoves, coordinate);

        //Move 3
        coordinate = new Coordinate(coordinateFrom.getX(), coordinateFrom.getY() + 1);
        addProperMove(possibleMoves, coordinate);

        //Move 4
        coordinate = new Coordinate(Math.abs(coordinateFrom.getX() - 1), coordinateFrom.getY());
        addProperMove(possibleMoves, coordinate);

        //Move 5
        coordinate = new Coordinate(Math.abs(coordinateFrom.getX() - 1), Math.abs(coordinateFrom.getY() - 1));
        addProperMove(possibleMoves, coordinate);

        //Move 6
        coordinate = new Coordinate(coordinateFrom.getX(), Math.abs(coordinateFrom.getY() - 1));
        addProperMove(possibleMoves, coordinate);

        //Move 7
        coordinate = new Coordinate(coordinateFrom.getX() + 1, Math.abs(coordinateFrom.getY() - 1));
        addProperMove(possibleMoves, coordinate);

        //Move 8
        coordinate = new Coordinate(Math.abs(coordinateFrom.getX() - 1), coordinateFrom.getY() + 1);
        addProperMove(possibleMoves, coordinate);

        return possibleMoves;
    }
}
