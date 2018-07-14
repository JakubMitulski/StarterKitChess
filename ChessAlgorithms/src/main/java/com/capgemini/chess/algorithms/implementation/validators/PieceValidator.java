package com.capgemini.chess.algorithms.implementation.validators;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;

import java.util.Set;

public interface PieceValidator {

    Set getAllPossibleMoves(Coordinate fromCoordinate, Board board, Color opponentColor);
}
