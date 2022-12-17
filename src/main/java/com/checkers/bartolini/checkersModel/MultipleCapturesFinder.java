package com.checkers.bartolini.checkersModel;

import java.security.InvalidAlgorithmParameterException;
import java.util.List;

public interface MultipleCapturesFinder {
    List<Move> getMultipleCaptures(Board board, int x, int y) throws InvalidAlgorithmParameterException;
}
