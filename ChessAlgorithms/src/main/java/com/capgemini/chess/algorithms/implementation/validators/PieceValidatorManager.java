package com.capgemini.chess.algorithms.implementation.validators;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.enums.PieceType;

import java.util.Set;

public class PieceValidatorManager {

    private Piece piece;
    private Coordinate fromCoordinate;

    public PieceValidatorManager(Piece piece, Coordinate fromCoordinate) {
        this.piece = piece;
        this.fromCoordinate = fromCoordinate;
    }

    public Set getAllPossibleMoves(){
        PieceType pieceType = piece.getType();
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

        return pieceValidator.findAllPossibleMoves(fromCoordinate);
    }
}
