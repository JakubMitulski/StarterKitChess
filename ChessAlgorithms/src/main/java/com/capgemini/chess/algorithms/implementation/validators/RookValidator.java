//package com.capgemini.chess.algorithms.implementation.validators;
//
//import com.capgemini.chess.algorithms.data.Coordinate;
//import com.capgemini.chess.algorithms.data.Move;
//import com.capgemini.chess.algorithms.data.enums.Color;
//import com.capgemini.chess.algorithms.data.generated.Board;
//
//import java.util.HashSet;
//import java.util.Set;
//
//public class RookValidator extends PieceValidator {
//
//    private static final int BOARD_START = 0;
//    private static final int BOARD_END = 7;
//
//    public RookValidator(Coordinate coordinateFrom, Board board, Color opponentColor) {
//        super(coordinateFrom, board, opponentColor);
//    }
//
//    @Override
//    public Set getMoves() {
////        Set possibleMoves = new HashSet<Move>();
////        int x = fromCoordinate.getX();
////        int y = fromCoordinate.getY();
////
////        for (int i = BOARD_START; i <= BOARD_END; i++) {
////            Move move = new Move();
////            move.setFrom(fromCoordinate);
////            move.setTo(new Coordinate(i, y));
////            possibleMoves.add(move);
////        }
////        for (int i = BOARD_START; i <= BOARD_END; i++) {
////            Move move = new Move();
////            move.setFrom(fromCoordinate);
////            move.setTo(new Coordinate(x, i));
////            possibleMoves.add(move);
////        }
//
//        return null;
//    }
//
//}
