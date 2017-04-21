/*

	Design Chess with OOD

*/

/*

Board

A Board class has an attribute of Squares Array (8x8) and PieceSets (black and white).

A Board class also has an attribute of "pieceSetOnTop". The attribute helps to figure the piece moves that are direction-restricted.


PieceSets

A PieceSet class has an attribute of a List<Piece>. The size of the List<Piece> is initially set to 16.

A Piece class has two attributes: color and placeAt (i.e. located at which square).

A Piece class is an abstract class. The extended classes (Pawn, King, Queen, Rook, Knight, Bishop) implements the abstracted operations:
validMoves() - The valid movement for a Piece
attackSquares() - The squares that a Piece can attack
captureFreeMove - The squares that a Piece can move to without being captured. 
toBeCaptured() - The boolean indicates whether a Piece is going to be captured.

The validMoves() operation implements the movement rules. For example, the validMoves of a Pawn class ensures that the Pawn can only move in the direction towards the opponent side. A Pawn class has additional attributes of promoted and promotedTo, which describes the movement/conversion rule of a Pawn at reaching the end of an opponent side and at the conversion about the piece that a Pawn converted to.

Game

A Game class controls the flow of a game. The class has attributes:
playedMoves - Keep a record of moves
turn - Indicate either it is a Black's turn or a White's turn
players - Represent the two players, this can be Human/Human, Computer/Computer or Human/Computer
result - Indicate the result of a game
checkStatus - Indicate which side is being checked or checkmated

Player

A Player class represents a Player. A Player has two attributes:
pieceColor - The color that used by a Player
engine - The engine that makes the moves. This can be a human or a computer
Design a chess game using OO principles | Runhe Tian Coding Practice
Design a chess game using object oriented principles.




or 
Basic Object Design

Game:
Contains the Board and 2 Players
Commands List (for history tracking)

Board (Singleton):
Hold spots with 8*8
Initialize the piece when game start
Move Piece
Remove Piece

Spot:
Hold Pieces

Piece (Abstract):
Hold the color to represent the affiliation.
Extended by concreted classes with 8 Pawns, 2 Rooks, 2 Bishops, 2 Knights, 1 Queen, 1 King
Concreted classes define the detail step approach

Player (Abstract):
Has a list of piece reference it owns.
Concreted classes for Human and Computer players

Command
Piece
Destination x, y

*/

public class ChessPieceTurn {

};


public class GameManager {

    void processTurn(PlayerBase player) {

    };


    boolean acceptTurn(ChessPieceTurn turn) {

        return true;

    };


    Position currentPosition;

}


public abstract class PlayerBase {

    public abstract ChessPieceTurn getTurn(Position p);

}


class ComputerPlayer extends PlayerBase {

    @Override

    public ChessPieceTurn getTurn(Position p) {

        return null;

    }


    public void setDifficulty() {

    };


    public PositionEstimator estimater;

    public PositionBackTracker backtracter;

}


public class HumanPlayer extends PlayerBase {

    @Override

    public ChessPieceTurn getTurn(Position p) {

        return null;

    }

}


public abstract class ChessPieceBase {

    abstract boolean canBeChecked();


    abstract boolean isSupportCastle();

}


public class King extends ChessPieceBase {


    @Override

    boolean canBeChecked() {

        // TODO Auto-generated method stub

        return false;

    }


    @Override

    boolean isSupportCastle() {

        // TODO Auto-generated method stub

        return false;

    }

}


public class Queen extends ChessPieceBase {


    @Override

    boolean canBeChecked() {

        // TODO Auto-generated method stub

        return false;

    }


    @Override

    boolean isSupportCastle() {

        // TODO Auto-generated method stub

        return false;

    }

}


public class Position { // represents chess positions in compact form

    ArrayList<ChessPieceBase> black;


    ArrayList<ChessPieceBase> white;

}


public class PositionBackTracker {

    public Position getNext(Position p) {

        return null;

    }

}


public class PositionEstimator {

    public PositionPotentialValue estimate(Position p) {

        return null;

    }

}


public abstract class PositionPotentialValue {

    abstract boolean lessThan(PositionPotentialValue pv);

}