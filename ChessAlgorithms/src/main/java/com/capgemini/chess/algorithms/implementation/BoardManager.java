package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.*;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.KingInCheckException;
import com.capgemini.chess.algorithms.implementation.validators.*;

import java.util.*;

import static com.capgemini.chess.algorithms.implementation.validators.CoordinateValidator.*;

/**
 * Class for managing of basic operations on the Chess Board.
 *
 * @author Michal Bejm
 * @author Jakub Mitulski
 */
public class BoardManager {

    private Board board = new Board();
    private static final int BOARD_START = 0;
    private static final int BOARD_END = 7;
    private static final Coordinate INITIAL_WHITE_KING_COORDINATE = new Coordinate(4, 0);
    private static final Coordinate WHITE_KING_COORDINATE_AFTER_RIGHT_CASTLING = new Coordinate(6, 0);
    private static final Coordinate WHITE_KING_COORDINATE_AFTER_LEFT_CASTLING = new Coordinate(2, 0);
    private static final Coordinate INITIAL_WHITE_R_ROOK_COORDINATE = new Coordinate(7, 0);
    private static final Coordinate INITIAL_WHITE_L_ROOK_COORDINATE = new Coordinate(0, 0);
    private static final Coordinate INITIAL_BLACK_KING_COORDINATE = new Coordinate(4, 7);
    private static final Coordinate BLACK_KING_COORDINATE_AFTER_RIGHT_CASTLING = new Coordinate(6, 7);
    private static final Coordinate BLACK_KING_COORDINATE_AFTER_LEFT_CASTLING = new Coordinate(2, 7);
    private static final Coordinate INITIAL_BLACK_R_ROOK_COORDINATE = new Coordinate(7, 7);
    private static final Coordinate INITIAL_BLACK_L_ROOK_COORDINATE = new Coordinate(0, 7);

    public BoardManager() {
        initBoard();
    }

    public BoardManager(List<Move> moves) {
        initBoard();
        for (Move move : moves) {
            addMove(move);
        }
    }

    public BoardManager(Board board) {
        this.board = board;
    }

    /**
     * Getter for generated board
     *
     * @return board
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * Performs move of the chess piece on the chess board from one field to
     * another.
     *
     * @param from coordinates of 'from' field
     * @param to   coordinates of 'to' field
     * @return move object which includes moved piece and move type
     * @throws InvalidMoveException in case move is not valid
     */
    public Move performMove(Coordinate from, Coordinate to) throws InvalidMoveException {

        Move move = validateMove(from, to);

        addMove(move);

        return move;
    }

    /**
     * Calculates state of the chess board.
     *
     * @return state of the chess board
     */
    public BoardState updateBoardState() throws InvalidMoveException {

        Color nextMoveColor = calculateNextMoveColor();

        boolean isKingInCheck = isKingInCheck(nextMoveColor);
        boolean isAnyMoveValid = isAnyMoveValid(nextMoveColor);

        BoardState boardState;
        if (isKingInCheck) {
            if (isAnyMoveValid) {
                boardState = BoardState.CHECK;
            } else {
                boardState = BoardState.CHECK_MATE;
            }
        } else {
            if (isAnyMoveValid) {
                boardState = BoardState.REGULAR;
            } else {
                boardState = BoardState.STALE_MATE;
            }
        }
        this.board.setState(boardState);
        return boardState;
    }

    /**
     * Checks threefold repetition rule (one of the conditions to end the chess
     * game with a draw).
     *
     * @return true if current state repeated at list two times, false otherwise
     */
    public boolean checkThreefoldRepetitionRule() {

        // there is no need to check moves that where before last capture/en
        // passant/castling
        int lastNonAttackMoveIndex = findLastNonAttackMoveIndex();
        List<Move> omittedMoves = this.board.getMoveHistory().subList(0, lastNonAttackMoveIndex);
        BoardManager simulatedBoardManager = new BoardManager(omittedMoves);

        int counter = 0;
        for (int i = lastNonAttackMoveIndex; i < this.board.getMoveHistory().size(); i++) {
            Move moveToAdd = this.board.getMoveHistory().get(i);
            simulatedBoardManager.addMove(moveToAdd);
            boolean areBoardsEqual = Arrays.deepEquals(this.board.getPieces(),
                    simulatedBoardManager.getBoard().getPieces());
            if (areBoardsEqual) {
                counter++;
            }
        }

        return counter >= 2;
    }

    /**
     * Checks 50-move rule (one of the conditions to end the chess game with a
     * draw).
     *
     * @return true if no pawn was moved or not capture was performed during
     * last 50 moves, false otherwise
     */
    public boolean checkFiftyMoveRule() {

        // for this purpose a "move" consists of a player completing his turn
        // followed by his opponent completing his turn
        if (this.board.getMoveHistory().size() < 100) {
            return false;
        }

        for (int i = this.board.getMoveHistory().size() - 1; i >= this.board.getMoveHistory().size() - 100; i--) {
            Move currentMove = this.board.getMoveHistory().get(i);
            PieceType currentPieceType = currentMove.getMovedPiece().getType();
            if (currentMove.getType() != MoveType.ATTACK || currentPieceType == PieceType.PAWN) {
                return false;
            }
        }

        return true;
    }

    // PRIVATE

    private void initBoard() {

        this.board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(0, 7));
        this.board.setPieceAt(Piece.BLACK_KNIGHT, new Coordinate(1, 7));
        this.board.setPieceAt(Piece.BLACK_BISHOP, new Coordinate(2, 7));
        this.board.setPieceAt(Piece.BLACK_QUEEN, new Coordinate(3, 7));
        this.board.setPieceAt(Piece.BLACK_KING, new Coordinate(4, 7));
        this.board.setPieceAt(Piece.BLACK_BISHOP, new Coordinate(5, 7));
        this.board.setPieceAt(Piece.BLACK_KNIGHT, new Coordinate(6, 7));
        this.board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(7, 7));

        for (int x = 0; x < Board.SIZE; x++) {
            this.board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(x, 6));
        }

        this.board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(0, 0));
        this.board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(1, 0));
        this.board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(2, 0));
        this.board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(3, 0));
        this.board.setPieceAt(Piece.WHITE_KING, new Coordinate(4, 0));
        this.board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(5, 0));
        this.board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(6, 0));
        this.board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(7, 0));

        for (int x = 0; x < Board.SIZE; x++) {
            this.board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(x, 1));
        }
    }

    private void addMove(Move move) {

        addRegularMove(move);

        if (move.getType() == MoveType.CASTLING) {
            addCastling(move);
        } else if (move.getType() == MoveType.EN_PASSANT) {
            addEnPassant(move);
        }

        this.board.getMoveHistory().add(move);
    }

    private void addRegularMove(Move move) {
        Piece movedPiece = this.board.getPieceAt(move.getFrom());
        this.board.setPieceAt(null, move.getFrom());
        this.board.setPieceAt(movedPiece, move.getTo());

        performPromotion(move, movedPiece);
    }

    private void performPromotion(Move move, Piece movedPiece) {
        if (movedPiece == Piece.WHITE_PAWN && move.getTo().getY() == (Board.SIZE - 1)) {
            this.board.setPieceAt(Piece.WHITE_QUEEN, move.getTo());
        }
        if (movedPiece == Piece.BLACK_PAWN && move.getTo().getY() == 0) {
            this.board.setPieceAt(Piece.BLACK_QUEEN, move.getTo());
        }
    }

    private void addCastling(Move move) {
        if (move.getFrom().getX() > move.getTo().getX()) {
            Piece rook = this.board.getPieceAt(new Coordinate(0, move.getFrom().getY()));
            this.board.setPieceAt(null, new Coordinate(0, move.getFrom().getY()));
            this.board.setPieceAt(rook, new Coordinate(move.getTo().getX() + 1, move.getTo().getY()));
        } else {
            Piece rook = this.board.getPieceAt(new Coordinate(Board.SIZE - 1, move.getFrom().getY()));
            this.board.setPieceAt(null, new Coordinate(Board.SIZE - 1, move.getFrom().getY()));
            this.board.setPieceAt(rook, new Coordinate(move.getTo().getX() - 1, move.getTo().getY()));
        }
    }

    private void addEnPassant(Move move) {
        Move lastMove = this.board.getMoveHistory().get(this.board.getMoveHistory().size() - 1);
        this.board.setPieceAt(null, lastMove.getTo());
    }


    private Move validateMove(Coordinate from, Coordinate to) throws InvalidMoveException {

        //TODO przenieść walidację do odrębnej klasy wraz z walidacją w walidatorach figór
        if (isCoordinateOutOfBand(to) || isCoordinateOutOfBand(from)) {
            throw new InvalidMoveException();
        }
        if (isEmptySpot(from, board)) {
            throw new InvalidMoveException();
        }
        if (isCoordinateFromSameAsCoordinateTo(from, to)) {
            throw new InvalidMoveException();
        }

        Piece piece = board.getPieceAt(from);
        if (piece.getColor() != calculateNextMoveColor()) {
            throw new InvalidMoveException();
        }

        Color playerColor = calculateNextMoveColor();


        PieceValidator pieceValidator = callPieceValidator(from, playerColor);
        Set<Move> allPossibleMoves = pieceValidator.getMoves();

        Move castlingMove = validateCastlingMove(from, to, playerColor);
        if (castlingMove != null) {
            allPossibleMoves.add(castlingMove);
        }

        Optional<Move> optionalMove = allPossibleMoves.stream()
                .filter(move -> move.getTo().getX() == to.getX())
                .filter(move -> move.getTo().getY() == to.getY())
                .findAny();

        if (!optionalMove.isPresent()) {
            throw new InvalidMoveException();
        }

        Move move = optionalMove.get();

        //Duplicate board and then perform move
        Board duplicatedBoard = duplicateBoard(this.board);
        this.board.setPieceAt(move.getMovedPiece(), move.getTo());
        this.board.setPieceAt(null, move.getFrom());

        //Throw exception in case of check
        if (isKingInCheck(playerColor)) {
            throw new KingInCheckException();
        }

        //Revert move perform
        this.board = duplicatedBoard;

        return move;
    }


    private boolean isKingInCheck(Color kingColor) {
        Set<Move> moves = new HashSet<>();

        Coordinate kingCoordinate = getKingCoordinate(kingColor);

        for (int i = BOARD_START; i <= BOARD_END; i++) {
            for (int j = BOARD_START; j <= BOARD_END; j++) {

                Coordinate coordinate = new Coordinate(i, j);
                Piece piece = board.getPieceAt(coordinate);

                if (piece != null && piece.getColor() != kingColor) {
                    Color playerColor = piece.getColor();
                    Set<Move> pieceMoves = callPieceValidator(coordinate, playerColor).getMoves();
                    moves.addAll(pieceMoves);
                }

            }
        }

        Optional<Move> optionalMove = moves.stream()
                .filter(move -> move.getTo().getX() == kingCoordinate.getX())
                .filter(move -> move.getTo().getY() == kingCoordinate.getY())
                .findAny();

        return optionalMove.isPresent();
    }


    private boolean isAnyMoveValid(Color nextMoveColor) {

        for (int i = BOARD_START; i <= BOARD_END; i++) {
            for (int j = BOARD_START; j <= BOARD_END; j++) {

                Coordinate coordinate = new Coordinate(i, j);
                Piece piece = board.getPieceAt(coordinate);

                if (piece != null && piece.getColor() == nextMoveColor) {

                    Set<Move> moves = callPieceValidator(coordinate, nextMoveColor).getMoves();

                    for (Move move : moves) {

                        //Duplicate board and then perform move
                        Board duplicatedBoard = duplicateBoard(this.board);
                        this.board.setPieceAt(move.getMovedPiece(), move.getTo());
                        this.board.setPieceAt(null, move.getFrom());

                        //Throw exception in case of check
                        if (!isKingInCheck(nextMoveColor)) {
                            return true;
                        }

                        //Revert move perform
                        this.board = duplicatedBoard;
                    }

                }
            }
        }
        return false;
    }


    //Method returns moves validator specified for each piece
    private PieceValidator callPieceValidator(Coordinate from, Color playerColor) {

        PieceValidator pieceValidator;
        Piece piece = board.getPieceAt(from);

        switch (piece.getType()) {
            case PAWN:
                pieceValidator = new PawnValidator(from, board, playerColor);
                break;
            case ROOK:
                pieceValidator = new RookValidator(from, board, playerColor);
                break;
            case KNIGHT:
                pieceValidator = new KnightValidator(from, board, playerColor);
                break;
            case BISHOP:
                pieceValidator = new BishopValidator(from, board, playerColor);
                break;
            case KING:
                pieceValidator = new KingValidator(from, board, playerColor);
                break;
            case QUEEN:
                pieceValidator = new QueenValidator(from, board, playerColor);
                break;
            default:
                pieceValidator = null;
        }

        return pieceValidator;
    }


    //Method duplicates board to allow king-check validation before move perform
    private Board duplicateBoard(Board board) {
        Board duplicatedBoard = new Board();
        duplicatedBoard.setMoveHistory(board.getMoveHistory());
        duplicatedBoard.setState(board.getState());

        for (int i = BOARD_START; i <= BOARD_END; i++) {
            for (int j = BOARD_START; j <= BOARD_END; j++) {
                Coordinate coordinate = new Coordinate(i, j);
                Piece piece = board.getPieceAt(coordinate);
                duplicatedBoard.setPieceAt(piece, coordinate);
            }
        }

        return duplicatedBoard;
    }


    //Method returns castling move if is possible
    private Move validateCastlingMove(Coordinate from, Coordinate to, Color playerColor) {

        PieceType pieceType = board.getPieceAt(from).getType();
        if (pieceType != PieceType.KING) {
            return null;
        }

        switch (playerColor) {
            case WHITE:
                if (!wasPieceMoved(INITIAL_WHITE_KING_COORDINATE))
                    if (to.getX() == WHITE_KING_COORDINATE_AFTER_RIGHT_CASTLING.getX()) {
                        boolean wasRookMoved = wasPieceMoved(INITIAL_WHITE_R_ROOK_COORDINATE);
                        boolean areSpotsBetweenEmpty = areSpotsToPointEmpty(INITIAL_WHITE_KING_COORDINATE, to);
                        boolean areSpotsToPointInCheck = areSpotsToPointInCheck(INITIAL_WHITE_KING_COORDINATE
                                , WHITE_KING_COORDINATE_AFTER_RIGHT_CASTLING
                                , playerColor);
                        boolean isKingInCheck = isSpotInCheck(Color.WHITE, INITIAL_WHITE_KING_COORDINATE);

                        if (!wasRookMoved && !isKingInCheck && !areSpotsToPointInCheck && areSpotsBetweenEmpty) {
                            return setCastlingMove(from, to);
                        }
                    }
                if (!wasPieceMoved(INITIAL_WHITE_KING_COORDINATE))
                    if (to.getX() == WHITE_KING_COORDINATE_AFTER_LEFT_CASTLING.getX()) {
                        boolean wasRookMoved = wasPieceMoved(INITIAL_WHITE_L_ROOK_COORDINATE);
                        boolean areSpotsBetweenEmpty = areSpotsToPointEmpty(INITIAL_WHITE_KING_COORDINATE, to);
                        boolean areSpotsToPointInCheck = areSpotsToPointInCheck(INITIAL_WHITE_KING_COORDINATE
                                , WHITE_KING_COORDINATE_AFTER_LEFT_CASTLING
                                , playerColor);
                        boolean isKingInCheck = isSpotInCheck(Color.WHITE, INITIAL_WHITE_KING_COORDINATE);

                        if (!wasRookMoved && !isKingInCheck && !areSpotsToPointInCheck && areSpotsBetweenEmpty) {
                            return setCastlingMove(from, to);
                        }
                    }
                break;

            case BLACK:
                if (!wasPieceMoved(INITIAL_BLACK_KING_COORDINATE))
                    if (to.getX() == BLACK_KING_COORDINATE_AFTER_RIGHT_CASTLING.getX()) {
                        boolean wasRookMoved = wasPieceMoved(INITIAL_BLACK_R_ROOK_COORDINATE);
                        boolean areSpotsBetweenEmpty = areSpotsToPointEmpty(INITIAL_BLACK_KING_COORDINATE, to);
                        boolean areSpotsToPointInCheck = areSpotsToPointInCheck(INITIAL_BLACK_KING_COORDINATE
                                , BLACK_KING_COORDINATE_AFTER_RIGHT_CASTLING
                                , playerColor);
                        boolean isKingInCheck = isSpotInCheck(Color.BLACK, INITIAL_BLACK_KING_COORDINATE);

                        if (!wasRookMoved && !isKingInCheck && !areSpotsToPointInCheck && areSpotsBetweenEmpty) {
                            return setCastlingMove(from, to);
                        }
                    }
                if (!wasPieceMoved(INITIAL_BLACK_KING_COORDINATE))
                    if (to.getX() == BLACK_KING_COORDINATE_AFTER_LEFT_CASTLING.getX()) {
                        boolean wasRookMoved = wasPieceMoved(INITIAL_BLACK_L_ROOK_COORDINATE);
                        boolean areSpotsBetweenEmpty = areSpotsToPointEmpty(INITIAL_BLACK_KING_COORDINATE, to);
                        boolean areSpotsToPointInCheck = areSpotsToPointInCheck(INITIAL_BLACK_KING_COORDINATE
                                , BLACK_KING_COORDINATE_AFTER_LEFT_CASTLING
                                , playerColor);
                        boolean isKingInCheck = isSpotInCheck(Color.BLACK, INITIAL_BLACK_KING_COORDINATE);

                        if (!wasRookMoved && !isKingInCheck && !areSpotsToPointInCheck && areSpotsBetweenEmpty) {
                            return setCastlingMove(from, to);
                        }
                    }
                break;
        }
        return null;
    }


    //Method creates castling move
    private Move setCastlingMove(Coordinate from, Coordinate to) {
        Move move = new Move();
        move.setType(MoveType.CASTLING);
        move.setFrom(from);
        move.setTo(to);
        move.setMovedPiece(board.getPieceAt(from));
        return move;
    }


    //Method checks whether piece was ever moved
    private boolean wasPieceMoved(Coordinate initialPieceCoordinate) {
        List<Move> moveHistory = board.getMoveHistory();

        return moveHistory.stream().anyMatch(move -> move.getFrom().getX() == initialPieceCoordinate.getX()
                && move.getFrom().getY() == initialPieceCoordinate.getY());
    }


    //Method checks whether spots during castling move would be empty
    private boolean areSpotsToPointEmpty(Coordinate from, Coordinate to) {
        int fromX = from.getX();
        int toX = to.getX();

        if (fromX < toX) {
            for (int i = fromX + 1; i <= toX; i++) {
                boolean emptySpot = isEmptySpot(new Coordinate(i, from.getY()), board);
                if (!emptySpot) {
                    return false;
                }
            }
        }
        if (fromX > toX) {
            for (int i = fromX - 1; i <= toX; i--) {
                boolean emptySpot = isEmptySpot(new Coordinate(i, from.getY()), board);
                if (!emptySpot) {
                    return false;
                }
            }
        }
        return true;
    }


    //Method checks whether spots during castling move would be checked
    private boolean areSpotsToPointInCheck(Coordinate from, Coordinate to, Color playerColor) {
        int fromX = from.getX();
        int fromY = from.getY();
        int toX = to.getX();

        if (fromX < toX) {
            for (int i = fromX + 1; i <= toX; i++) {
                boolean inCheck = isSpotInCheck(playerColor, new Coordinate(i, fromY));
                if (inCheck) {
                    return true;
                }
            }
        }
        if (fromX > toX) {
            for (int i = fromX - 1; i <= toX; i--) {
                boolean inCheck = isSpotInCheck(playerColor, new Coordinate(i, fromY));
                if (inCheck) {
                    return true;
                }
            }
        }
        return false;
    }


    //Method checks whether spot is under check
    private boolean isSpotInCheck(Color playerColor, Coordinate spotCoordinate) {

        for (int i = BOARD_START; i <= BOARD_END; i++) {
            for (int j = BOARD_START; j <= BOARD_END; j++) {

                Coordinate coordinate = new Coordinate(i, j);
                Piece piece = board.getPieceAt(coordinate);

                if (piece != null && piece.getColor() != playerColor) {
                    Set<Move> pieceMoves = callPieceValidator(coordinate, playerColor).getMoves();

                    boolean result = pieceMoves.stream().anyMatch(move -> move.getTo().getX() == spotCoordinate.getX()
                            && move.getTo().getY() == spotCoordinate.getY());

                    if (result) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    //Method searches for king coordinates
    private Coordinate getKingCoordinate(Color kingColor) {
        for (int i = BOARD_START; i <= BOARD_END; i++) {
            for (int j = BOARD_START; j <= BOARD_END; j++) {

                Coordinate coordinate = new Coordinate(i, j);
                if (!isEmptySpot(coordinate, board)) {
                    Piece piece = board.getPieceAt(coordinate);
                    if (piece.getType() == PieceType.KING && piece.getColor() == kingColor) {
                        return coordinate;
                    }
                }
            }
        }
        return null;
    }


    private Color calculateNextMoveColor() {
        if (this.board.getMoveHistory().size() % 2 == 0) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    }

    private int findLastNonAttackMoveIndex() {
        int counter = 0;
        int lastNonAttackMoveIndex = 0;

        for (Move move : this.board.getMoveHistory()) {
            if (move.getType() != MoveType.ATTACK) {
                lastNonAttackMoveIndex = counter;
            }
            counter++;
        }

        return lastNonAttackMoveIndex;
    }
}
