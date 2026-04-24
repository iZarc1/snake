/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake;

import java.io.*;
import java.util.*;

/**
 *
 * @author izaelb
 */


public class ScoreManager {
    private static final String FILE = "scores.txt";

    // Guarda nombre y puntuación al final del fichero
    public static void saveScore(String playerName, int score) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE, true))) {
            pw.println(playerName + "," + score);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Devuelve lista ordenada de "Nombre - Puntos"
    public static List<String> getTopScores() {
        List<int[]> scores = new ArrayList<>();
        List<String> names = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    names.add(parts[0]);
                    scores.add(new int[]{Integer.parseInt(parts[1].trim())});
                }
            }
        } catch (IOException e) {
            // Si no existe el fichero aún, no pasa nada
        }

        // Ordenar por puntuación descendente
        List<String> result = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            result.add(names.get(i) + " - " + scores.get(i)[0]);
        }
        result.sort((a, b) -> {
            int pA = Integer.parseInt(a.split(" - ")[1]);
            int pB = Integer.parseInt(b.split(" - ")[1]);
            return Integer.compare(pB, pA);
        });

        return result.subList(0, Math.min(5, result.size())); // Top 5
    }
}
