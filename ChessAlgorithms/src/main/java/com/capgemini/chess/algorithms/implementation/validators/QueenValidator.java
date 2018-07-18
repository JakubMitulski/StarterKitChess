package com.capgemini.chess.algorithms.implementation.validators;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;

import java.util.HashSet;
import java.util.Set;

public class QueenValidator extends PieceValidator {

    public QueenValidator(Coordinate coordinateFrom, Board board, Color playerColor) {
        super(coordinateFrom, board, playerColor);
    }

    @Override
    public Set getMoves() {
        Set possibleMoves = new HashSet<Move>();

        Set verticalAndHorizontalMoves = new RookValidator(coordinateFrom, board, playerColor).getMoves();
        Set diagonalMoves = new BishopValidator(coordinateFrom, board, playerColor).getMoves();

        possibleMoves.addAll(verticalAndHorizontalMoves);
        possibleMoves.addAll(diagonalMoves);

        return possibleMoves;
    }
}
