package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import org.junit.Test;

import static com.capgemini.chess.algorithms.implementation.validators.CoordinateValidator.*;
import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class CoordinateValidatorTest {

    @Test
    public void shouldReturnTrueWhenCoordinateIsOutOfBand() {
        //Given
        Coordinate firstCoordinate = new Coordinate(-1, -1);
        Coordinate secondCoordinate = new Coordinate(8, 8);

        //When
        boolean firstCoordinateOutOfBand = isCoordinateOutOfBand(firstCoordinate);
        boolean secondCoordinateOutOfBand = isCoordinateOutOfBand(secondCoordinate);

        //Then
        assertTrue(firstCoordinateOutOfBand);
        assertTrue(secondCoordinateOutOfBand);
    }

    @Test
    public void shouldReturnTrueWhenCoordinatesAreEqual() {
        //Given
        Coordinate firstCoordinate = new Coordinate(2, 2);
        Coordinate secondCoordinate = new Coordinate(2, 2);

        //When
        boolean result = isCoordinateFromSameAsCoordinateTo(firstCoordinate, secondCoordinate);

        //Then
        assertTrue(result);
    }

    @Test
    public void shouldReturnTrueWhenSpotIsEmpty() {
        //Given
        Board board = new Board();
        Coordinate emptyCoordinate = new Coordinate(2, 2);
        Coordinate notEmptyCoordinate = new Coordinate(3, 3);
        board.setPieceAt(Piece.WHITE_BISHOP, notEmptyCoordinate);

        //When
        boolean firstSpot = isEmptySpot(emptyCoordinate, board);
        boolean secondSpot = isEmptySpot(notEmptyCoordinate, board);

        //Then
        assertTrue(firstSpot);
        assertFalse(secondSpot);
    }
}
