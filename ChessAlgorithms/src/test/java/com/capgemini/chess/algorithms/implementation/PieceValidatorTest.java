package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.validators.RookValidator;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class PieceValidatorTest {

    @Test
    public void shouldReturnTrueWhenPieceBelongsToCurrentPlayer() {
        //Given
        Board board = new Board();
        Coordinate coordinate = new Coordinate(3, 3);
        Color color = Color.BLACK;
        board.setPieceAt(Piece.BLACK_ROOK, coordinate);

        //When
        boolean playerPiece = new RookValidator(coordinate, board, color).isPlayerPiece(coordinate);

        //Then
        assertTrue(playerPiece);
    }
}

