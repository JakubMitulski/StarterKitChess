package com.capgemini.chess.algorithms.implementation.validators;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;

import java.util.Set;

public abstract class PieceValidator {

    Coordinate coordinateFrom;
    Board board;
    Color playerColor;

    public PieceValidator(Coordinate coordinateFrom, Board board, Color playerColor) {
        this.coordinateFrom = coordinateFrom;
        this.board = board;
        this.playerColor = playerColor;
    }

    public abstract Set getMoves();

    public boolean isFieldEmpty(Coordinate coordinate) {
        Piece piece = board.getPieceAt(coordinate);
        return piece == null;
    }

    public boolean isPlayerPiece(Coordinate coordinate) {
        if (!isFieldEmpty(coordinate)) {
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

    //set possiblemoves wyrzucic do pola lub innej zmiennej
    public Set addProperMove(Set possibleMoves, Coordinate coordinate) {
        if (isFieldEmpty(coordinate)) {
            possibleMoves.add(setAttackMove(coordinate));
        } else if (!isPlayerPiece(coordinate)) {
            possibleMoves.add(setCaptureMove(coordinate));
        }
        return possibleMoves;
    }

}
