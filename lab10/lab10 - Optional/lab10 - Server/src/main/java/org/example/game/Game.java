package org.example.game;


public class Game {
    public final static int LINE = 5;
    private final Board board = new Board();
    public int players = 1;
    private Player currentPlayer = null;


    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public synchronized void addPiece(Player player, int x, int y) {

        if (player.getOpponent() == null) {
            System.err.println("Where is your opponent, mate?");
        }

        if (player != currentPlayer) {
            System.err.println("Someone is too excited to play...");
        }

        boolean move = board.addPiece(player.getPlayerId(), x, y);

        if (!move) {
            System.err.println("This move cannot be made!");
        }

        currentPlayer = currentPlayer.getOpponent();
    }


    public void restart() {
        board.resetBoard();
    }

    public boolean checkWin()
    {
        return checkHorizontalLine() || checkVerticalLine() || checkDiagonal();
    }

    private boolean checkHorizontalLine() {
        int[][] matrix = board.getBoard();

        for (int i = 0; i < Board.SIZE; i++) {
            int j = 0;
            while (j < Board.SIZE) {
                if (matrix[i][j] != 0) {
                    int player = matrix[i][j];
                    int counter = 1;
                    j++;
                    while (counter < LINE && j < Board.SIZE) {
                        if (matrix[i][j] == player) {
                            counter++;
                            j++;
                        } else {
                            break;
                        }
                    }
                    if (counter == LINE) {
                        return true;
                    }
                } else {
                    j++;
                }
            }
        }
        return false;
    }

    private boolean checkVerticalLine() {
        int[][] matrix = board.getBoard();

        for (int j = 0; j < Board.SIZE; j++) {
            int i = 0;
            while (i < Board.SIZE) {
                if (matrix[i][j] != 0) {
                    int player = matrix[i][j];
                    int counter = 1;
                    i++;
                    while (counter < LINE && j < Board.SIZE) {
                        if (matrix[i][j] == player) {
                            counter++;
                            i++;
                        } else {
                            break;
                        }
                    }
                    if (counter == LINE) {
                        return true;
                    }
                } else {
                    i++;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonal() {
        return checkRightDiagonal() || checkLeftDiagonals();
    }

    private boolean checkRightDiagonal() {
        int[][] matrix = board.getBoard();

        for (int i = 0; i <= Board.SIZE - LINE; i++) {
            for (int j = 0; j <= Board.SIZE - LINE; j++) {
                int x = i;
                int y = j;
                int player = matrix[x][y];
                if (player != 0) {
                    int counter = 1;
                    while (counter < LINE && x < Board.SIZE - 1 && y < Board.SIZE - 1) {
                        x++;
                        y++;
                        if (matrix[x][y] == player) {
                            counter++;
                        } else {
                            break;
                        }
                    }
                    if (counter == LINE) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkLeftDiagonals() {
        int[][] matrix = board.getBoard();

        for (int i = LINE - 1; i < Board.SIZE; i++) {
            for (int j = 0; j <= Board.SIZE - LINE; j++) {
                int x = i;
                int y = j;
                int player = matrix[x][y];
                if (player != 0) {
                    int counter = 1;
                    while (counter < LINE && x > 0 && y < Board.SIZE - 1) {
                        x--;
                        y++;
                        if (matrix[x][y] == player) {
                            counter++;
                        } else {
                            break;
                        }
                    }
                    if (counter == LINE) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkDraw() {
        int[][] matrix = board.getBoard();

        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                if (matrix[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

}



