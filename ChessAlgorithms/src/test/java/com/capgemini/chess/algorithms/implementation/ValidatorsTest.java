package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.validators.BishopValidator;
import com.capgemini.chess.algorithms.implementation.validators.KingValidator;
import org.junit.Test;

import java.util.Set;

import static junit.framework.TestCase.assertEquals;

public class ValidatorsTest {

    @Test
    public void shouldGetAllPossibleMovesOfBishop(){
        //Given
        Board board = new Board();
        board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(3, 3));
        board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(1, 5));
        board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(4, 4));

        //When
        Set<Move> allBishopMoves = new BishopValidator(new Coordinate(3, 3), board, Color.WHITE).getMoves();

        //Then
        assertEquals(8, allBishopMoves.size());
    }

    @Test
    public void shouldGetAllPossibleMovesOfKing(){
        //Given
        Board board = new Board();
        board.setPieceAt(Piece.BLACK_KING, new Coordinate(3, 0));
        board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(2, 1));
        board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(3, 1));

        //When
        Set<Move> allKingMoves = new KingValidator(new Coordinate(3, 0), board, Color.BLACK).getMoves();

        //Then
        assertEquals(4, allKingMoves.size());
    }
}
