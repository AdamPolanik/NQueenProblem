package org.example;

//Adam Polanik, adan7490

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class calculates all solutions to the more general version of the eight
 * queen problem when placing N queens on an NxN board.
 */
public class NQueensSolver {

    /***
     * Denna metod anropar placeQueen() metoden för att hitta alla lösningar.
     * @param n Antalet damer som ska placeras ut samt storleken på brädet
     * @return En samling med alla lösningar
     */
    public static Collection<Solution> calculateAllSolutions(int n) {
        //Samling med lösningar som returneras
        Collection<Solution> solutions = new ArrayList<>();

        //Skapandet av "brädet" i storlek n, här placeras damerna ut
        int[] board = new int[n];

        //Den rekursiva metoden för att hitta lösningarna, startar på rad 0 och jobbar sig genom brädet
        placeQueen(solutions, board, 0, n);
        return solutions;
    }

    /***
     * Denna rekursiva metod kollar igenom spelplanen rad för rad och försöker placera ut en dam, om platsen för
     * placeringen är giltig anropas metoden igen tills alla damer är placerade. När n antal damer är placerade
     * skapas en Solution och läggs till i samlingen med lösningar.
     * @param solutions Samling med alla lösningar
     * @param board Brädet/Spelplanen som används
     * @param row Indexet fär raden
     * @param n Antalet damer som ska placeras
     */
    public static void placeQueen(Collection<Solution> solutions, int[] board, int row, int n) {
		/*Alla damer är placerade och en Solution är hittad och läggs till i listan,
		returnerar sedan för att hoppa tillbaks i rekursionen. */
        if (row == n) {
            solutions.add(new Solution(n, board.clone()));
            return;
        }

        for (int col = 1; col <= n; col++) {
            if (validPlacement(board, row, col)) {
                board[row] = col;
                placeQueen(solutions, board, row + 1, n);
            }
        }
    }

    /***
     * Denna metod kollar så placeringen av damen följer reglerna, inga andra damer får finnas på raden, kolumnen
     * eller diagonalerna.
     * @param board Spelplanen som används
     * @param row Raden man vill placera damen på
     * @param col Kolumnen man vill placera damen på
     * @return Boolean som säger om det är en valid plasering eller ej
     */
    public static boolean validPlacement(int[] board, int row, int col) {
        for (int i = 0; i < row; i++) {
            //Kollar för kollisioner på samma Kolumn
            if (board[i] == col) {
                return false;
            }
			/*
			Kollar efter kollisioner på diagonalerna genom att kolla absolutbeloppet på alla utsatta damers rad -
			den nya damens rad, samt samma sak för kolumnen, om dessa blir samma tal ligger en dam redan på en diagonal.
			Exempel: Dam1(1,3) ------- Dam2(3,1)
			Math.abs(1 - 3) = 2,
			Math.abs(3 - 1) = 2,
			eftersom att båda ger 2 vet vi att dam1 och dam2 ligger på diagonalen till varandra.
			 */
            if (Math.abs(row - i) == Math.abs(col - board[i])) {
                return false;
            }
        }

        //Om placeringen är giltig returneras true
        return true;
    }

}





