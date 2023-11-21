package org.acme.core.solver.solution;

import org.acme.core.utils.Coord;

import java.util.*;

/**
 * Représente une solution pour une grille de jeu (et un pattern). Il s'agit de
 * la liste des coordonnées sur lesquelles appliquer le pattern pour résoudre la
 * grille
 *
 * @author MOLLIN Florian
 */
public class Solution extends HashSet<Coord> {
    /**
     * Constructeur d'une solution (utiliser principalement par le Solver)
     *
     * @param coordToValue La table faisant la correspondance entre une
     *                     coordonnée et son état (si l'on doit appliquer ou non le pattern sur
     *                     celle-ci)
     */
    public Solution(Map<Coord, Boolean> coordToValue) {
        super();
        if (coordToValue != null) {
            coordToValue.entrySet().stream()
                    .filter(Map.Entry::getValue)
                    .map(Map.Entry::getKey)
                    .forEach(this::add);
        }
    }

    /**
     * Constructeur d'une solution.
     *
     * @param coords Ensemble des coordonnées sur lesquelles appliquer le
     *               pattern
     */
    public Solution(Coord... coords) {
        super(Arrays.asList(coords));
    }

    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "[]";
        }
        StringBuilder res = new StringBuilder();
        int maxRow = 0;
        int maxCol = 0;
        for (Coord coord : this) {
            maxRow = Integer.max(maxRow, coord.getRow());
            maxCol = Integer.max(maxCol, coord.getColumn());
        }
        for (int r = 0; r <= maxRow; r++) {
            for (int c = 0; c <= maxCol; c++) {
                Coord coord = Coord.of(r, c);
                res.append(this.contains(coord) ? "X " : "_ ");
            }
            if (r != maxRow) {
                res.append("\n");
            }
        }
        return res.toString();
    }

    public List<Integer> coordinatesToArray() {
        int maxRow = 0;
        int maxCol = 0;

        // Find the maximum row and column values
        for (Coord coord : this) {
            maxRow = Integer.max(maxRow, coord.getRow());
            maxCol = Integer.max(maxCol, coord.getColumn());
        }

        // Create a 2D array to represent the coordinates
        int[][] array = new int[maxRow + 1][maxCol + 1];

        // Populate the array based on the coordinates set
        for (Coord coord : this) {
            array[coord.getRow()][coord.getColumn()] = 1;
        }

        // Flatten the array
        int[] flatArray = new int[array.length * array[0].length];
        for (int i = 0; i < array.length; i++) {
            System.arraycopy(array[i], 0, flatArray, i * array[i].length, array[i].length);
        }
        //if 1 then add index to new array
        List<Integer>  lista = new ArrayList<>(); // list of indexes
        for(int i = 0; i < flatArray.length; i++){
            if(flatArray[i] == 1){
                lista.add(i);
            }
        }

        return lista;
    }
}
