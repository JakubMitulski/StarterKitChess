package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.validators.QueenValidator;
import com.capgemini.chess.algorithms.implementation.validators.RookValidator;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
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

    @Test
    public void shouldSetAttackMove() {
        //Given
        Board board = new Board();
        Coordinate coordinateFrom = new Coordinate(6, 6);
        Coordinate coordinateTo = new Coordinate(0, 0);
        Color color = Color.WHITE;
        board.setPieceAt(Piece.WHITE_QUEEN, coordinateFrom);

        //When
        Move move = new QueenValidator(coordinateFrom, board, color).setAttackMove(coordinateTo);

        //Then
        assertEquals(MoveType.ATTACK, move.getType());
    }

    @Test
    public void shouldSetCaptureMove() {
        //Given
        Board board = new Board();
        Coordinate coordinateFrom = new Coordinate(6, 6);
        Coordinate coordinateTo = new Coordinate(0, 0);
        Color color = Color.WHITE;
        board.setPieceAt(Piece.WHITE_QUEEN, coordinateFrom);

        //When
        Move move = new QueenValidator(coordinateFrom, board, color).setCaptureMove(coordinateTo);

        //Then
        assertEquals(MoveType.CAPTURE, move.getType());
    }
}

