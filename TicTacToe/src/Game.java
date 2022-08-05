import java.util.Scanner;

public class Game {
    static char[][] board = {{'□','□','□'}, {'□','□','□'}, {'□','□','□'}};
    static String letter1 = "";
    static String letter2 = "";

    public static void printBoard(){
        System.out.println("╭───────────╮");

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (j == 0) System.out.print("│  ");

                System.out.print(board[i][j] + "  ");

                if (j == 2) System.out.print("│");
            }
            System.out.println();
        }
        System.out.println("╰───────────╯");
        System.out.println();
    }

    public static boolean finishedOrNot(){
        return board[0][0] == board[1][0] && board[0][0] == board[2][0] && board[0][0] != '□' ||
               board[0][1] == board[1][1] && board[0][1] == board[2][1] && board[0][1] != '□' ||
               board[0][2] == board[1][2] && board[0][2] == board[2][2] && board[0][2] != '□' ||
               board[0][0] == board[0][1] && board[0][0] == board[0][2] && board[0][0] != '□' ||
               board[1][0] == board[1][1] && board[1][0] == board[1][2] && board[1][0] != '□' ||
               board[2][0] == board[2][1] && board[2][0] == board[2][2] && board[2][0] != '□' ||
               board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != '□' ||
               board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] != '□';
    }

    public static boolean isTableFull(){
        for (char[] rows: board)
            for (char value : rows) if (value == '□') return false;
        return true;
    }

    public static void xOrO(){
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.print("Select X or O: ");
            letter1 = input.next();

            if (letter1.equalsIgnoreCase("x") || letter1.equalsIgnoreCase("o")) break;
            else {
                System.out.println("You entered unauthorized letter!");
                System.out.println();
            }
        }
        if (letter1.equalsIgnoreCase("O")) letter2 = "X";
        if (letter1.equalsIgnoreCase("X")) letter2 = "O";
    }

    public static void player(String letter){
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("ROW: ");
            int row = input.nextInt();

            System.out.print("COLUMN: ");
            int column = input.nextInt();

            if (board[row][column] != 'X' && board[row][column] != 'O') {
                board[row][column] = letter.toUpperCase().charAt(0);
                break;
            } else {
                System.out.println("This position is already filled with " + board[row][column] + "!");
                System.out.println("Enter another position...");
                System.out.println();
            }
        }
        printBoard();
    }

    public static void computer(){
        System.out.println("(Computer's Turn - " + letter2.toUpperCase() + ")");

        while (true){
            int rowComp = (int) (Math.random() * 3);
            int columnComp = (int) (Math.random() * 3);

            if (board[rowComp][columnComp] != 'X' && board[rowComp][columnComp] != 'O'){
                board[rowComp][columnComp] = letter2.charAt(0);
                System.out.println("ROW: " + rowComp);
                System.out.println("COLUMN: " + columnComp);
                break;
            }
        }
        printBoard();
    }

    public static void clearBoard(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                board[i][j] = '□';
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("~ WELCOME TO MY TIC-TAC-TOE GAME ~");
        Scanner input = new Scanner(System.in);

        boolean b = true;
        while (b) {
            System.out.println("·One Player -> Press 1");
            System.out.println("·Two Players -> Press 2");
            int option = input.nextInt();

            switch (option) {
                case 1:
                    xOrO();
                    System.out.println();
                    System.out.println("YOU -> " + letter1.toUpperCase());
                    System.out.println("COMPUTER -> " + letter2.toUpperCase());
                    printBoard();

                    while (true) {
                        // HUMAN
                        System.out.println("(Your Turn - " + letter1.toUpperCase() + ")");
                        player(letter1);
                        if (finishedOrNot()) {
                            System.out.println("YOU WON :)");
                            break;
                        } else if (isTableFull()){
                            System.out.println("DRAW :|");
                            break;
                        }

                        // COMPUTER
                        computer();
                        if (finishedOrNot()) {
                            System.out.println("COMPUTER WON :(");
                            break;
                        } else if (isTableFull()){
                            System.out.println("DRAW :|");
                            break;
                        }
                    }
                    break;

                case 2:
                    xOrO();
                    System.out.println();
                    System.out.println("PLAYER1 -> " + letter1.toUpperCase());
                    System.out.println("PLAYER2 -> " + letter2.toUpperCase());
                    printBoard();

                    while (true) {
                        // PLAYER1
                        System.out.println("(Player1's Turn - " + letter1.toUpperCase() + ")");
                        player(letter1);
                        if (finishedOrNot()) {
                            System.out.println("PLAYER1 WON :)");
                            break;
                        } else if (isTableFull()){
                            System.out.println("DRAW :|");
                            break;
                        }
                        // PLAYER2
                        System.out.println("(Player2's Turn - " + letter2.toUpperCase() + ")");
                        player(letter2);
                        if (finishedOrNot()) {
                            System.out.println("PLAYER2 WON :)");
                            break;
                        } else if (isTableFull()){
                            System.out.println("DRAW :|");
                            break;
                        }
                    }
                    break;

                default:
                    System.out.println("You entered unauthorized digit!");
                    System.out.println();
                    continue;
            }

            System.out.println();
            System.out.println("Do you want to play again?");

            while (true) {
                System.out.print("Enter Y or N: ");
                String yesOrNo = input.next();
                if (yesOrNo.equalsIgnoreCase("n")) {
                    b = false;
                    break;
                } else if (yesOrNo.equalsIgnoreCase("y")) {
                    clearBoard();
                    System.out.println();
                    break;
                }
                System.out.println("You entered unauthorized letter!");
                System.out.println();
            }
        }
    }
}