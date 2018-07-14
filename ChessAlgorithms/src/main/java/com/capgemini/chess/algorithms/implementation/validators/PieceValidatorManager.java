package com.capgemini.chess.algorithms.implementation.validators;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.PieceType;
import com.capgemini.chess.algorithms.data.generated.Board;

import java.util.Set;

public class PieceValidatorManager {

    private Coordinate fromCoordinate;
    private Board board;
    private Color opponentColor;

    public PieceValidatorManager(Coordinate fromCoordinate, Board board, Color opponentColor) {
        this.fromCoordinate = fromCoordinate;
        this.board = board;
        this.opponentColor = opponentColor;
    }

    public Set findAllPossibleMoves(){
        PieceType pieceType = board.getPieceAt(fromCoordinate).getType();
        PieceValidator pieceValidator = null;

        switch (pieceType) {
            case PAWN: pieceValidator = new PawnValidator();
            break;
            case ROOK: pieceValidator = new RookValidator();
            break;
            case KNIGHT: pieceValidator = new KnightValidator();
            break;
            case BISHOP: pieceValidator = new BishopValidator();
            break;
            case KING: pieceValidator = new KingValidator();
            break;
            case QUEEN: pieceValidator = new QueenValidator();
            break;
        }

        return pieceValidator.getAllPossibleMoves(fromCoordinate, board, opponentColor);
    }
}
