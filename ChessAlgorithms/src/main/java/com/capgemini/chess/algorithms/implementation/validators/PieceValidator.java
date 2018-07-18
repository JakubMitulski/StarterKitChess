package com.capgemini.chess.algorithms.implementation.validators;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.generated.Board;

import java.util.Set;

import static com.capgemini.chess.algorithms.implementation.validators.CoordinateValidator.isEmptySpot;

public abstract class PieceValidator {

    Coordinate coordinateFrom;
    Board board;
    Color playerColor;

    PieceValidator(Coordinate coordinateFrom, Board board, Color playerColor) {
        this.coordinateFrom = coordinateFrom;
        this.board = board;
        this.playerColor = playerColor;
    }

    public abstract Set getMoves();

    public boolean isPlayerPiece(Coordinate coordinate) {
        if (!isEmptySpot(coordinate, board)) {
            Color pieceColor = board.getPieceAt(coordinate).getColor();
            return pieceColor == playerColor;
        }
        return false;
    }

    public Move setAttackMove(Coordinate coordinate) {
        Move move = new Move();
        move.setType(MoveType.ATTACK);
        move.setFrom(coordinateFrom);
        move.setTo(coordinate);
        move.setMovedPiece(board.getPieceAt(coordinateFrom));
        return move;
    }

    public Move setCaptureMove(Coordinate coordinate) {
        Move move = new Move();
        move.setType(MoveType.CAPTURE);
        move.setFrom(coordinateFrom);
        move.setTo(coordinate);
        move.setMovedPiece(board.getPieceAt(coordinateFrom));
        return move;
    }

    public Move setEnPassantMove(Coordinate coordinate) {
        Move move = new Move();
        move.setType(MoveType.EN_PASSANT);
        move.setFrom(coordinateFrom);
        move.setTo(coordinate);
        move.setMovedPiece(board.getPieceAt(coordinateFrom));
        return move;
    }

    void addProperMove(Set possibleMoves, Coordinate coordinate, Boolean enPassant) {
        if (enPassant){
            possibleMoves.add(setEnPassantMove(coordinate));
        } else if (isEmptySpot(coordinate, board)) {
            possibleMoves.add(setAttackMove(coordinate));
        } else if (!isPlayerPiece(coordinate)) {
            possibleMoves.add(setCaptureMove(coordinate));
        }
    }

}
