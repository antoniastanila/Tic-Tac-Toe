import java.util.*;

public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String args[]) {
        char[][] gameBoard = { {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};
        printBoard(gameBoard);
        while(true){
            Scanner scanner = new Scanner(System.in);
            int playerPos;
            int cpuPos;
            System.out.print("Enter a number between 1-9: ");
            playerPos = scanner.nextInt();

            while(playerPos<1 || playerPos>9) {
                System.out.print("That place doesn't exist. Choose a valid one: ");
                playerPos = scanner.nextInt();
            }

            while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)){
                System.out.print("Place taken. Choose another: ");
                playerPos = scanner.nextInt();
            }

            gameBoard=addSymbol(gameBoard,playerPos,"player");
            String result = checkWinner();
            if(result.length() > 0){
                printBoard(gameBoard);
                System.out.println(result);
                break;
            }

            Random rand = new Random();
            cpuPos = rand.nextInt(9) + 1;

            while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)){
                cpuPos = rand.nextInt(9) + 1;
            }

            gameBoard = addSymbol(gameBoard, cpuPos, "cpu");
            printBoard(gameBoard);
            result = checkWinner();
            if(result.length() > 0){
                printBoard(gameBoard);
                System.out.println(result);
                break;
            }
        }
    }

    public static void printBoard(char[][] gameBoard){

        for(char[] row : gameBoard)
            System.out.println(row);
    }
    public static char[][] addSymbol(char[][] gameBoard, int position, String user){
        char symbol = ' ';
        if(user.equals("player")){
            symbol = 'X';
            playerPositions.add(position);
        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(position);
        }

        switch (position){ //initial ai pus symbol, insa dupa position te iei, evident!
                case 1:
                    gameBoard[0][0]=symbol;
                    break;
                case 2:
                    gameBoard[0][2]=symbol;
                    break;
                case 3:
                    gameBoard[0][4]=symbol;
                    break;
                case 4:
                    gameBoard[2][0]=symbol;
                    break;
                case 5:
                    gameBoard[2][2]=symbol;
                    break;
                case 6:
                    gameBoard[2][4]=symbol;
                    break;
                case 7:
                    gameBoard[4][0]=symbol;
                    break;
                case 8:
                    gameBoard[4][2]=symbol;
                    break;
                case 9:
                    gameBoard[4][4]=symbol;
                    break;
                default:
                    break;
        }

        return gameBoard;
    }
    public static String checkWinner(){
        List<Integer> topRow = Arrays.asList(1, 2, 3);
        List<Integer> midRow = Arrays.asList(4, 5, 6);
        List<Integer> botRow = Arrays.asList(7, 8, 9);

        List<Integer> leftCol = Arrays.asList(1, 4, 7);
        List<Integer> midCol = Arrays.asList(2, 5, 8);
        List<Integer> rightCol = Arrays.asList(3,6,9);

        List<Integer> cross1 = Arrays.asList(1, 5, 9);
        List<Integer> cross2 = Arrays.asList(7, 5, 3);

        List<List<Integer>> winner = new ArrayList<List<Integer>>();
        winner.add(topRow);
        winner.add(midRow);
        winner.add(botRow);
        winner.add(leftCol);
        winner.add(midCol);
        winner.add(rightCol);
        winner.add(cross1);
        winner.add(cross2);

        for (List list : winner){
            if (playerPositions.containsAll(list)) {
                return "Congrats, you won!!";
            } else if (cpuPositions.containsAll(list)) {
                return "Cpu won, sorry :(";
            } else if (playerPositions.size()+cpuPositions.size()==9) {
                return "CAT!";
            }
        }
        return "";
    }
}
