import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileIndexer indexer = new FileIndexer();
        indexer.indexFiles("C:\\Users\\e2304691\\Desktop\\COURS");

        FileSearcher searcher = new FileSearcher(indexer.getIndex());

        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            System.out.println("Entrez le terme à rechercher (ou 'exit' pour quitter) :");
            input = scanner.nextLine();
            searcher.search(input);
        } while (!input.equalsIgnoreCase("exit"));

        scanner.close();
    }
}
