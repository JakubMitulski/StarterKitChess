package com.capgemini.chess.algorithms.implementation.validators;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.generated.Board;

import java.util.HashSet;
import java.util.Set;

public class KingValidator implements PieceValidator {

    @Override
    public Set findAllPossibleMoves(Coordinate fromCoordinate) {
        Set possibleMoves = new HashSet<Move>();
        int x = fromCoordinate.getX();
        int y = fromCoordinate.getY();

        Move move1 = new Move();
        move1.setFrom(fromCoordinate);
        move1.setTo(new Coordinate(x + 1, y));
        possibleMoves.add(move1);

        Move move2 = new Move();
        move1.setFrom(fromCoordinate);
        move1.setTo(new Coordinate(x + 1, y + 1));
        possibleMoves.add(move2);

        Move move3 = new Move();
        move1.setFrom(fromCoordinate);
        move1.setTo(new Coordinate(x, y + 1));
        possibleMoves.add(move3);

        Move move4 = new Move();
        move1.setFrom(fromCoordinate);
        move1.setTo(new Coordinate(Math.abs(x - 1), y));
        possibleMoves.add(move4);

        Move move5 = new Move();
        move1.setFrom(fromCoordinate);
        move1.setTo(new Coordinate(Math.abs(x - 1), Math.abs(y - 1)));
        possibleMoves.add(move5);

        Move move6 = new Move();
        move1.setFrom(fromCoordinate);
        move1.setTo(new Coordinate(x, Math.abs(y - 1)));
        possibleMoves.add(move6);

        return possibleMoves;
    }
}
