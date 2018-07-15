package com.capgemini.chess.algorithms.implementation.validators;

import com.capgemini.chess.algorithms.data.Coordinate;

import java.util.Set;

public interface PieceValidator {
    Set getMoves(Coordinate coordinate);
}
