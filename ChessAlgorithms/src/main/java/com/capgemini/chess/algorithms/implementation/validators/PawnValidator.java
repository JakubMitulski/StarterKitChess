package com.capgemini.chess.algorithms.implementation.validators;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.enums.PieceType;
import com.capgemini.chess.algorithms.data.generated.Board;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.capgemini.chess.algorithms.implementation.validators.CoordinateValidator.isCoordinateOutOfBand;
import static com.capgemini.chess.algorithms.implementation.validators.CoordinateValidator.isEmptySpot;

public class PawnValidator extends PieceValidator {

    private Coordinate coordinate;
    private Set possibleMoves = new HashSet<Move>();

    public PawnValidator(Coordinate coordinateFrom, Board board, Color playerColor) {
        super(coordinateFrom, board, playerColor);
    }

    @Override
    public Set getMoves() {
        int coordX = coordinateFrom.getX();
        int coordY = coordinateFrom.getY();

        //Validation to avoid NullPointer at checkEnPassant method
        if (board.getMoveHistory().size() != 0) {
            checkEnPassant(coordX, coordY);
        }

        //Move 1: first move ever, up direction, +2 spots
        if (coordY == 1 && playerColor == Color.WHITE) {
            coordinate = new Coordinate(coordX, coordY + 2);
            if (isEmptySpot(coordinate, board)) {
                addProperMove(possibleMoves, coordinate, false);
            }
        }

        //Move 2: first move ever, down direction, -2 spots
        if (coordY == 6 && playerColor == Color.BLACK) {
            coordinate = new Coordinate(coordX, coordY - 2);
            if (isEmptySpot(coordinate, board)) {
                addProperMove(possibleMoves, coordinate, false);
            }
        }

        //Move 3: regular move, up direction
        if (playerColor == Color.WHITE) {
            coordinate = new Coordinate(coordX, coordY + 1);
            if (!isCoordinateOutOfBand(coordinate) && isEmptySpot(coordinate, board)) {
                addProperMove(possibleMoves, coordinate, false);
            }
        }

        //Move 4: regular move, down direction
        if (playerColor == Color.BLACK) {
            coordinate = new Coordinate(coordX, coordY - 1);
            if (!isCoordinateOutOfBand(coordinate) && isEmptySpot(coordinate, board)) {
                addProperMove(possibleMoves, coordinate, false);
            }
        }

        //Move 5: capture move, up-right direction
        if (playerColor == Color.WHITE) {
            coordinate = new Coordinate(coordX + 1, coordY + 1);
            if (!isCoordinateOutOfBand(coordinate)) {
                if (!isEmptySpot(coordinate, board) && board.getPieceAt(coordinate).getColor() != playerColor) {
                    addProperMove(possibleMoves, coordinate, false);
                }
            }
        }

        //Move 6: capture move, up-left direction
        if (playerColor == Color.WHITE) {
            coordinate = new Coordinate(coordX - 1, coordY + 1);
            if (!isCoordinateOutOfBand(coordinate)) {
                if (!isEmptySpot(coordinate, board) && board.getPieceAt(coordinate).getColor() != playerColor) {
                    addProperMove(possibleMoves, coordinate, false);
                }
            }
        }

        //Move 7: capture move, down-right direction
        if (playerColor == Color.BLACK) {
            coordinate = new Coordinate(coordX + 1, coordY - 1);
            if (!isCoordinateOutOfBand(coordinate)) {
                if (!isEmptySpot(coordinate, board) && board.getPieceAt(coordinate).getColor() != playerColor) {
                    addProperMove(possibleMoves, coordinate, false);
                }
            }
        }

        //Move 8: capture move, down-left direction
        if (playerColor == Color.BLACK) {
            coordinate = new Coordinate(coordX - 1, coordY - 1);
            if (!isCoordinateOutOfBand(coordinate)) {
                if (!isEmptySpot(coordinate, board) && board.getPieceAt(coordinate).getColor() != playerColor) {
                    addProperMove(possibleMoves, coordinate, false);
                }
            }
        }

        return possibleMoves;
    }

    private void checkEnPassant(int coordFromX, int coordFromY) {
        final int initialBlackPawnYCoordinate = 6;
        final int blackPawnYCoordinateAfterDoubledFirstMove = 4;
        final int initialWhitePawnCoordinate = 1;
        final int whitePawnYCoordinateAfterDoubledFirstMove = 3;

        List<Move> moveHistory = board.getMoveHistory();
        Move lastMove = moveHistory.get(moveHistory.size() - 1);
        Piece lastMovedPiece = lastMove.getMovedPiece();

        if (lastMovedPiece.getColor() != playerColor && lastMovedPiece.getType() == PieceType.PAWN) {

            switch (playerColor) {
                case WHITE:
                    if (lastMove.getFrom().getY() == initialBlackPawnYCoordinate
                            && coordFromY == blackPawnYCoordinateAfterDoubledFirstMove) {

                        if (coordFromX == lastMove.getTo().getX() + 1) {

                            addProperMove(possibleMoves
                                    , new Coordinate(lastMove.getTo().getX(), lastMove.getTo().getY() + 1)
                                    , true);
                        }
                        if (coordFromX == lastMove.getTo().getX() - 1) {
                            addProperMove(possibleMoves
                                    , new Coordinate(lastMove.getTo().getX(), lastMove.getTo().getY() + 1)
                                    , true);
                        }
                    }
                    break;
                case BLACK:
                    if (lastMove.getFrom().getY() == initialWhitePawnCoordinate
                            && coordFromY == whitePawnYCoordinateAfterDoubledFirstMove) {

                        if (coordFromX == lastMove.getTo().getX() + 1) {
                            addProperMove(possibleMoves
                                    , new Coordinate(lastMove.getTo().getX(), lastMove.getTo().getY() - 1)
                                    , true);
                        }
                        if (coordFromX == lastMove.getTo().getX() - 1) {
                            addProperMove(possibleMoves
                                    , new Coordinate(lastMove.getTo().getX(), lastMove.getTo().getY() - 1)
                                    , true);
                        }
                    }
                    break;
            }
        }
    }
}