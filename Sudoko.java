import java.util.*;

public class Sudoko 
{
    //Function use to place the number on a correct matrix location.
    public boolean isSafe(char board[][], int row, int col, int number)
    {
        for(int i = 0; i < board.length; i++)
        {
            if(board[i][col] == (char)(number + '0'))
            {
                return false;
            }
            if(board[row][i] == (char)(number + '0'))
            {
                return false;
            }
        }
        int srow = (row / 3) * 3;
        int scol = (col / 3) * 3;
        for(int i = srow; i < srow + 3; i++)
        {
            for(int j = scol; j < scol + 3; j++)
            {
                if(board[i][j] == (char)(number + '0'))
                {
                    return false;
                }
            }
        }
        return true;
    }

    //Function for Backtracking and Recursion.
    public boolean helper(char  board [][], int row, int col)  
    {
        if(row == board.length)
        {
            return true;
        }
        int nrow = 0;
        int ncol = 0;
        if(col != board.length - 1)
        {
            nrow = row;
            ncol = col + 1;
        }
        else
        {
            nrow = row + 1;
            ncol = 0;
        }

        if(board[row][col] != '.')
        {
            if(helper(board, nrow, ncol))
            {
                return true;
            }
        }
        else
        {
            for(int i = 1; i <= 9; i++)
            {
                if(isSafe(board, row, col, i))
                {
                    board[row][col] = (char)(i + '0');
                    if(helper(board, nrow, ncol))
                    {
                        return true;
                    }
                    else
                    {
                        board[row][col] = '.';
                    }
                }
            }
        }
        return false;
    }

    //Function to call the helper function. 
    public void SolveSudoko(char board[][])
    {
        helper(board, 0, 0);
    }

    public static void main(String[] args) 
    {
        //We can write the main function with two ways :-
        Sudoko solver = new Sudoko();
        Scanner sc = new Scanner(System.in);

        //1.Main function for taking sudoko as a input to solve at runtime.

        System.out.println("Input the Sudoko: ");
        char[][] board = new char[9][9];
        for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                board[i][j] = sc.next().charAt(0);
            }
        }
        solver.SolveSudoko(board);
        System.out.println("Solved Sudoko: ");
        for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }

         //2. Directly declaring the sudoko in code                
        char[][] board = 
        {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
            System.out.println("Solved Sudoko: ");
            solver.SolveSudoko(board);
            for(int i = 0; i < 9; i++)
            {
                for(int j = 0; j < 9; j++)
                {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
    }
}
