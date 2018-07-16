package com.capgemini.chess.algorithms.implementation.validators;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.generated.Board;

public class CoordinateValidator {

    public static boolean isCoordinateOutOfBand(Coordinate coordinate) {
        int x = coordinate.getX();
        int y = coordinate.getY();
        return x < 0 || x > 7 || y < 0 || y > 7;
    }

    public static boolean isCoordinateFromSameAsCoordinateTo(Coordinate from, Coordinate to) {
        int fromX = from.getX();
        int fromY = from.getY();
        int toX = to.getX();
        int toY = to.getY();
        return fromX == toX && fromY == toY;
    }

    public static boolean isEmptySpot(Coordinate coordinate, Board board) {
        return board.getPieceAt(coordinate) == null;
    }

}
