import java.io.*;
import java.util.*;

public class FileIndexer {
    private Map<String, Map<String, Integer>> index;

    public FileIndexer() {
        index = new HashMap<>();
    }

    public void indexFiles(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.isDirectory()) {
            System.err.println("Le chemin spécifié n'est pas un dossier.");
            return;
        }

        for (File file : Objects.requireNonNull(folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt")))) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String fileName = file.getName();
                index.putIfAbsent(fileName, new HashMap<>());
                Map<String, Integer> occurrences = index.get(fileName);

                String line;
                int lineNumber = 0;
                while ((line = reader.readLine()) != null) {
                    lineNumber++;
                    for (String word : line.toLowerCase().replaceAll("[^a-zA-Z0-9\\s]", "").split("\\s+")) {
                        if (!word.isEmpty()) {
                            String locationKey = file.getPath() + ":" + lineNumber;
                            occurrences.merge(word, 1, Integer::sum);
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Erreur lors de la lecture du fichier : " + file.getPath());
                e.printStackTrace();
            }
        }
    }

    public Map<String, Map<String, Integer>> getIndex() {
        return index;
    }
}
