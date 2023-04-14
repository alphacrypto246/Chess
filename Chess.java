import java.util.*;
public class Chess
{
    static Scanner in = new Scanner(System.in);

    int xw, yw, xb, yb;

    String piece, position, userPreference;

    static boolean checkMate = false;

    static String gameBoard[][] = 
    {
        {"BB1", "BN1", "BR1", "BKG", "BQN", "BR2", "BN2", "BB3"},
        {"BP1", "BP2", "BP3", "BP4", "BP5", "BP6", "BP7", "BP8"},
        {"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
        {"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
        {"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
        {"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
        {"WP1", "WP2", "WP3", "WP4", "WP5", "WP6", "WP7", "WP8"},
        {"WB1", "WN1", "WR1", "WQN", "WKG", "WR2", "WN2", "WB3"}
    };

    static String boardBluePrint[][] = 
    {
        {"a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1"},
        {"a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2"},
        {"a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3"},
        {"a4", "b4", "c4", "d4", "e4", "f4", "g4", "h4"},
        {"a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5"},
        {"a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6"},
        {"a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7"},
        {"a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8"}
    };

    // Initializer
    public Chess()
    {
        piece = "";
        position = "";
    }
    
    // Chess GameBoard
    public void printBoard() 
    {       
        int i, j, k, numCount = 1;

        numCount = (numCount > 8)? 0 : numCount;

        System.out.println();
        for (i = 0; i < gameBoard.length; i++) 
        {
            System.out.println("  ---------------------------------" + "      " + "-------------------------");
            System.out.print(numCount++ + " ");
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
        System.out.println("  ---------------------------------" + "      " + "-------------------------");
        System.out.println("    a   b   c   d   e   f   g   h" + "\n");
    }

    // Game Instruction
    public void instruction() 
    {
        System.out.println("Game Instruction.....\n");
        System.out.println("Black                   White");
        System.out.println("BKG - BLACK KING         WKG - WHITE KING");
        System.out.println("BQN - BLACK QUEEN        WQN - WHITE QUEEN");
        System.out.println("BR  - BLACK ROOK         WR  - WHITE ROOK");
        System.out.println("BN  - BLACK KNIGHT       WN  - WHITE KNIGHT");
        System.out.println("BB  - BLACK BISHOP       WB  - WHITE BISHOP");
        System.out.println("BP  - BLACK PAWN         WP  - WHITE PAWN");
        System.out.println();
    }

    // User Preference
    public void setPreference()
    {
        System.out.println("Choose your army: White / Black");
        userPreference = in.nextLine().toLowerCase();
        printBoard();
    }

    // Moves Input from user
    public void getUserMove()
    {
        String house = Character.toString(userPreference.charAt(0)).toUpperCase();
        System.out.println("Your Turn!");

        System.out.println("Enter Piece : ");
        piece = in.next().toUpperCase();

        System.out.println("Enter House : ");
        position = in.next().toLowerCase();

        if(!(piece.contains(house)))
        {
            System.out.println("Something went wrong!");
            getUserMove();
        }
    }

    // Moves Input from user
    public void getBotMove()
    {
        System.out.println("Bot's Turn!");

        System.out.println("Piece : ");
        piece = in.next().toUpperCase();

        System.out.println("House : ");
        position = in.next().toLowerCase();
    }

    // Move setting mechanism
    public void setMove()
    {
        int i, j; 

        // To get the position of piece and fill replace the piece with empty space
        for(i = 0; i < gameBoard.length; i++)
        {
            for(j = 0; j < gameBoard.length; j++)
            {
                if(gameBoard[i][j].equals(piece))
                {
                    gameBoard[i][j] = "   ";
                    
                    if(piece.equals("BKG"))
                    {
                        xb = i;
                        yb = j;
                    }
                    if(piece.equals("WKG"))
                    {
                        xw = i;
                        yw = j;
                    }
                    
                    break;
                }
            }
        }
        i = 0; j = 0;

        // Make the piece move
        for(i = 0; i < gameBoard.length; i++)
        {
            for(j = 0; j < gameBoard.length; j++)
            {
                if (position.equals(boardBluePrint[i][j])) 
                {
                    gameBoard[i][j] = piece;
                    break;
                }
            }
        }
        i = 0; j = 0;
    }

    // Check Mechanism

    public void check(int pointer_x, int pointer_y, String preference)
    {
        String QUEEN = "", BISHOP1 = "", BISHOP2 = "", ROOK1 = "", ROOK2 = "";
        int check_counter = 0;

        // This will swith enemies according to the house
        switch(preference)
        {
            case "W":   QUEEN = "BQN";
                        BISHOP1 = "BB1";
                        BISHOP2 = "BB2";
                        ROOK1 = "BR1";
                        ROOK2 = "BR2";
                        
                        break;

            case "B":   QUEEN = "WQN";
                        BISHOP1 = "WB1";
                        BISHOP2 = "WB2";
                        ROOK1 = "WR1";
                        ROOK2 = "WR2";

                        break;
        }

        // Vertical Up
        while(pointer_y < 8)
        {
            if(gameBoard[pointer_x][pointer_y++].equals(QUEEN) || gameBoard[pointer_x][pointer_y++].equals(BISHOP1) || gameBoard[pointer_x][pointer_y++].equals(BISHOP2))
            {
                check_counter++;
                break;
            }
            else
            {
                break;
            }
        }

        // Vertical Down
        while(pointer_y > 0)
        {
            if(gameBoard[pointer_x][pointer_y--].equals(QUEEN) || gameBoard[pointer_x][pointer_y--].equals(BISHOP1) || gameBoard[pointer_x][pointer_y--].equals(BISHOP2))
            {
                check_counter++;
                break;
            }
            else
            {
                break;
            }
        }

        // Horizontal Left
        while(pointer_x > 0)
        {
            if(gameBoard[pointer_x--][pointer_y].equals(QUEEN) || gameBoard[pointer_x--][pointer_y].equals(BISHOP1) || gameBoard[pointer_x--][pointer_y].equals(BISHOP1))
            {
                check_counter++;
                break;
            }
            else
            {
                break;
            }
        }

        // Horizontal Right
        while(pointer_x < 8)
        {
            if(gameBoard[pointer_x++][pointer_y].equals(QUEEN) || gameBoard[pointer_x++][pointer_y].equals(BISHOP1) || gameBoard[pointer_x++][pointer_y].equals(BISHOP2))
            {
                check_counter++;
                break;
            }
            else
            {
                break;
            }
        }

        // Left Diagonal Up
        while(pointer_x < 8 && pointer_y < 8)
        {
            if(gameBoard[pointer_x++][pointer_y++].equals(QUEEN) || gameBoard[pointer_x++][pointer_y++].equals(ROOK1) || gameBoard[pointer_x++][pointer_y++].equals(ROOK2))
            {
                check_counter++;
                break;
            }
            else
            {
                break;
            }
        }

        // Left Daigonal Down
        while(pointer_x > 0 && pointer_y > 0)
        {
            if(gameBoard[pointer_x--][pointer_y--].equals(QUEEN) || gameBoard[pointer_x--][pointer_y--].equals(ROOK1) || gameBoard[pointer_x--][pointer_y--].equals(ROOK2))
            {
                check_counter++;
                break;
            }
            else
            {
                break;
            }
        }

        // Right Daigonal UP
        while(pointer_x > 8 && pointer_y < 0)
        {
            if(gameBoard[pointer_x--][pointer_y++].equals(QUEEN) || gameBoard[pointer_x--][pointer_y++].equals(ROOK1) || gameBoard[pointer_x--][pointer_y++].equals(ROOK2))
            {
                check_counter++;
                break;
            }
            else
            {
                break;
            }
        }
        
        // Right Daigonal Down
        while(pointer_x < 0 && pointer_y > 8)
        {
            if(gameBoard[pointer_x++][pointer_y--].equals(QUEEN) || gameBoard[pointer_x++][pointer_y--].equals(ROOK1) || gameBoard[pointer_x++][pointer_y--].equals(ROOK2))
            {
                check_counter++;
                break;
            }
            else
            {
                break;
            }
        }

        if(check_counter > 0 && check_counter <= 12)
        {
            System.out.println("Check!");
        }

        check_counter = 0;
    }

    // Playing Mechanism
    public void play()
    {
        if(userPreference.equals("white"))
        {
            // User Move
            getUserMove();
            setMove();
            printBoard();
            check(xw, yw, "W");

            // Bot Move
            getBotMove();
            setMove();
            printBoard();
            check(xb, yb, "B");
        }
        if(userPreference.equals("black"))
        {
            // Bot Move
            getBotMove();
            setMove();
            printBoard();
            check(xb, yb, "B");

            // User Move
            getUserMove();
            setMove();
            printBoard();
            check(xw, yw, "W");
        }
    }

    public static void main(String[] args) 
    {
        Chess game = new Chess();
        game.printBoard();
        game.instruction();
        game.setPreference();

        while(checkMate != true)
        {
            game.play();
        }

        in.close();
    }
}