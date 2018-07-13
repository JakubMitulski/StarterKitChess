package com.capgemini.chess.algorithms.implementation.exceptions;

/**
 * Exception thrown in case the invalid move is about to be performed
 * 
 * @author Michal Bejm
 *
 */
public class InvalidPieceException extends Exception {

	public InvalidPieceException() {
		super("It is not your piece!");
	}

	public InvalidPieceException(String message) {
		super("It is not your piece! " + message);
	}
}
