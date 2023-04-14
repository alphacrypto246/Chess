import java.util.*;

public class gamePrototype
{
    Scanner in = new Scanner(System.in);

    String piece, house;
    String gameBoard[][] = 
    {
        {"BB1", "BN1", "BR1", "BK ", "BQ ", "BR2", "BN2", "BB3"},
        {"BP1", "BP2", "BP3", "BP4", "BP5", "BP6", "BP7", "BP8"},
        {"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
        {"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
        {"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
        {"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
        {"WP1", "WP2", "WP3", "WP4", "WP5", "WP6", "WP7", "WP8"},
        {"WB1", "WN1", "WR1", "WQ ", "WK ", "WR2", "WN2", "WB3"}
    };

    String boardBluePrint[][] = 
    {
        {"a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8"},
        {"a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7"},
        {"a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6"},
        {"a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5"},
        {"a4", "b4", "c4", "d4", "e4", "f4", "g4", "h4"},
        {"a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3"},
        {"a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2"},
        {"a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1"}
    };

    // Chess GameBoard
    public void printBoard() 
    {       
        int i, j, k;

        for (i = 0; i < gameBoard.length; i++) 
        {
            System.out.println("---------------------------------" + "      " + "-------------------------");

            // This prints the Game Board
            for (j = 0; j < gameBoard.length; j++) 
            {
                System.out.print("|" + gameBoard[i][j]);
            }

            System.out.print("|" + "      ");

            // This prints the Game Board Blueprint
            for (k = 0; k < gameBoard.length; k++) 
            {
                System.out.print("|" + boardBluePrint[i][k]);
            }
            System.out.print("|" + "\n");
        }
        System.out.println("---------------------------------" + "      " + "-------------------------");
    }

    // Game Instruction
    public void instruction() 
    {
        System.out.println("Game Instruction.....\n");
        System.out.println("Black                   White");
        System.out.println("BK - BLACK KING         WK - WHITE KING");
        System.out.println("BQ - BLACK QUEEN        WQ - WHITE QUEEN");
        System.out.println("BR - BLACK ROOK         WR - WHITE ROOK");
        System.out.println("BN - BLACK KNIGHT       WN - WHITE KNIGHT");
        System.out.println("BB - BLACK BISHOP       WB - WHITE BISHOP");
        System.out.println("BP - BLACK PAWN         WP - WHITE PAWN");
        System.out.println();
        System.out.println("Play as WHITE");
        System.out.println();
    }

    // Moves Input
    public void getmoves() 
    {
        System.out.println("Enter Piece : ");
        piece = in.next().toUpperCase();

        System.out.println("Enter House : ");
        house = in.next().toLowerCase();
    }

    // Game Mechanics
    public void play() 
    {
        getmoves();
        
        switch (piece.substring(1, 2)) 
        {
            case "K":  KING();
                
                break;

            case "Q":  QUEEN();
                
                break;

            case "R":  ROOK();
                
                break;
        
            case "N":  KNIGHT();
                
                break;

            case "B":  BISHOP();
                
                break;

            case "P":  PAWN();
                
                break;

            default:    System.out.println("Oops! Something Went Wrong... Try Again!");
                        play();

                break;
        }
    }

    // Pieces Working Mechanism

    public void setMove()
    {
        // Liner Search

        int i, j, x = 0, y = 0; 

        // To get the position of piece
        for(i = 0; i < gameBoard.length; i++)
        {
            for(j = 0; j < gameBoard.length; j++)
            {
                if(gameBoard[i][j].equals(piece))
                {
                    x = i; 
                    y = j;
                }
            }
        }
        i = 0; j = 0;

        // Make the piece move and fill the last position with space
        for(i = 0; i < gameBoard.length; i++)
        {
            for(j = 0; j < gameBoard.length; j++)
            {
                if (house.equals(boardBluePrint[i][j])) 
                {
                    gameBoard[i][j] = piece;
                    gameBoard[x][y] = "   ";
                }
            }
        }
    }

    // KING
    public void KING() 
    {
        setMove();
        printBoard();
    }

    // QUEEN
    public void QUEEN() 
    {
        setMove();
        printBoard();
    }

    // ROOK
    public void ROOK() 
    {
        setMove();
        printBoard();
    }

    // KNIGHT
    public void KNIGHT() 
    {
        setMove();
        printBoard();
    }
    
    // BISHOP
    public void BISHOP() 
    {
        setMove();
        printBoard();
    }

    // PAWN
    public void PAWN() 
    {
        setMove();
        printBoard();
    }
    public static void main(String[] args) throws InterruptedException 
    {
        gamePrototype game = new gamePrototype();
        game.instruction();
        game.printBoard();
        while(true)
        {
            game.play();    
        }
    }
}