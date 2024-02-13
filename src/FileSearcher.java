import java.util.*;

public class FileSearcher {
    private Map<String, Map<String, Integer>> index;

    public FileSearcher(Map<String, Map<String, Integer>> index) {
        this.index = index;
    }

    public void search(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            System.out.println("Terme de recherche invalide.");
            return;
        }

        final String term = searchTerm.toLowerCase();

        boolean found = false;
        for (Map.Entry<String, Map<String, Integer>> entry : index.entrySet()) {
            String fileName = entry.getKey();
            Map<String, Integer> occurrences = entry.getValue();

            if (fileName.toLowerCase().contains(term)) {
                System.out.println("Nom de fichier trouvé : " + fileName);
                found = true;
            }

            if (occurrences.containsKey(term)) {
                found = true;
                System.out.println("Résultats de la recherche pour le mot '" + term + "' dans le fichier '" + fileName + "' :");
                occurrences.entrySet().stream()
                        .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()) // Trier par pertinence
                        .forEach(e -> System.out.println(e.getKey() + " : " + e.getValue() + " occurrence(s)"));
            }
        }

        if (!found) {
            System.out.println("Aucun fichier trouvé pour le terme : " + term);
        }
    }
}
