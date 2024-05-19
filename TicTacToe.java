//https://www.lintcode.com/problem/746/descriptionclass
//https://leetcode.com/problems/design-tic-tac-toe/description/
class TicTacToe {

    int[][] mat ;
    int turn ;
    int winner;

    // denote  0 for X and 1 for other

    /** Initialize your data structure here. */
    public TicTacToe() {
        this.mat = new int[3][3];
        this.turn = 0;
        this.winner = -1;
        for(int i = 0 ; i < 3 ; i++)
            for(int j = 0 ; j < 3 ; j++)
                mat[i][j] = -1;
    }

    public boolean moveV2(int row, int col) throws AlreadyTakenException, GameEndException {
        try {
            if (winner != -1)
                throw new GameEndException("game end");
            if (allPlacesTaken()) {
                System.out.println("it's a draw");
                return false;
            }
            if (turn % 2 == 0) {
                if (canMove(row, col))
                    process(row, col, 'X');
                else
                    throw new AlreadyTakenException("already");
                winner = checkForWinner();
                if (winner != -1) {
                    printWinner();
                    return true;
                }
                turn++;
            } else {
                if (canMove(row, col))
                    process(row, col, 'O');
                else
                    throw new AlreadyTakenException("already");
                // means pos already taken
                winner = checkForWinner();
                if (winner != -1) {
                    printWinner();
                    return true;
                }
                turn++;
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
            if(e instanceof GameEndException)
                reset();
        }
        return false;
    }

    private boolean allPlacesTaken(){
        for(int i = 0; i < 3 ; i++)
            for(int j = 0 ; j < 3 ; j++)
            {
                if( mat[i][j] == -1 )
                    return false;
            }
        return true;
    }
    private boolean canMove(int row,int col){
        if(mat[row][col] == -1)
            return true;
        return false;

    }

    private void process(int row,int col, char c){
        int no ;
        if( c == 'X'){
            no = 0 ;
            mat[row][col] = no;
        }
        else if ( c == 'O'){
            no = 1;
            mat[row][col] = no;
        }

    }

    private int checkForWinner(){

        if( mat[0][0] != -1 && mat[0][0] == mat[0][1] && mat[0][1] == mat[0][2])
            return mat[0][0];
        if( mat[1][0] != -1 && mat[1][0] == mat[1][1] && mat[1][1] == mat[1][2])
            return mat[1][0];
        if( mat[2][0] != -1 && mat[2][0] == mat[2][1] && mat[2][1] == mat[2][2])
            return mat[2][0];


        if( mat[0][0] != -1 && mat[0][0] == mat[1][0]   && mat[1][0] == mat[2][0] )
            return mat[0][0];
        if( mat[0][1] != -1 && mat[0][1] == mat[1][1]   && mat[1][1] == mat[2][1] )
            return mat[0][1];
        if( mat[0][2] != -1 && mat[0][2] == mat[1][2]   && mat[1][2] == mat[2][2] )
            return mat[0][2];

        if(mat[0][0] != -1 &&  mat[0][0] == mat[1][1] && mat[1][1] == mat[2][2])
            return mat[0][0];
        if( mat[0][2] != -1 && mat[0][2] == mat[1][1] && mat[1][1] == mat[2][0])
            return mat[0][2];
        return -1;

    }

    private void print(){
        System.out.println("matrix curr state");
        for(int i = 0 ; i < 3 ; i++)
        {
            for(int j = 0 ; j < 3 ; j++){
                System.out.print(mat[i][j] + " ");
            }
            System.out.println("\n");
        }
    }
    private void reset(){
        this.turn = 0;
        this.winner = -1;
        for(int i = 0 ; i < 3 ; i++)
            for(int j = 0 ; j < 3 ; j++)
                mat[i][j] = -1;

    }
    public void move(int row, int col) throws GameEndException, AlreadyTakenException {
        moveV2(row,col);
        print();

    }
    public void printWinner(){
        if(this.winner == 0)
            System.out.println("X has won");
        else if( this.winner == 1)
            System.out.println("O has won");

    }


}
class AlreadyTakenException extends Exception {
    public AlreadyTakenException(String message) {
        super(message);
    }
}

class GameEndException extends Exception {
    public GameEndException(String message) {
        super(message);
    }
}


class HelloWorld {
    public static void main(String[] args) throws GameEndException, AlreadyTakenException {
        TicTacToe t = new TicTacToe();

 t.move(0, 0); // X turn

 t.move(1, 0); // O trun
 t.move(1, 1); // X turn
 t.move(2, 0); // O turn
 t.move(2, 2); // X turn and win
        // put this exception
        t.move(0, 0);

 t.move(0, 0); // X turn

 t.move(0, 0); // throw AlreadyTakenException
 t.move(1, 0); // O turn
 t.move(1, 1); // X turn
 t.move(2, 0); // o turn
 t.move(2, 2); // X turn and win
// Output:
// x player wins!
// x player wins!
        System.out.println("Try programiz.pro");
    }
}
