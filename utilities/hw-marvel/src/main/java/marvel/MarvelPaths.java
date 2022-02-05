package marvel;

import graph.Graph;
import graph.Graph.Edge;
import java.util.*;

/**
 * MarvelPaths builds graphs using datasets from .tsv files and is able to
 * find the alphabetically least path between two heroes.
 */
public class MarvelPaths {

    /** Create a Graph from dataset in the given .tsv file
     *
     * @param filename File to be imported
     * @spec.requires <var>filename</var> is a valid file in the <var>resources/data</var>
     * folder and is not <code>null</code>
     * @return a Graph representing Marvel Character relationships according to the dataset
     * of the given .tsv file
     */
    public static Graph<String, String> createGraph(String filename) {
        Iterator<CharacterInfo> characters = MarvelParser.parseData(filename);
        Graph<String, String> graph = new Graph<>();
        Map<String, List<String>> books = new HashMap<>();
        while(characters.hasNext()) {
            CharacterInfo character = characters.next();
            graph.addNode(character.getCharacter());
            if(!books.containsKey(character.getBook())) {
                books.put(character.getBook(), new ArrayList<>());
            }
            books.get(character.getBook()).add(character.getCharacter());
        }
        for(String book : books.keySet()) {
            List<String> bookCharacters = books.get(book);
            for(int i = 0; i < bookCharacters.size(); i++) {
                String firstCharacter = bookCharacters.get(i);
                for(int j = i; j < bookCharacters.size(); j++) {
                    String secondCharacter = bookCharacters.get(j);
                    if(!firstCharacter.equals(secondCharacter)) {
                        graph.addEdge(firstCharacter, secondCharacter, book);
                        graph.addEdge(secondCharacter, firstCharacter, book);
                    }
                }
            }
        }
        return graph;
    }

    /** Return the alphabetically least path found from one hero to another via breadth-first search.
     *
     * @param graph a Graph representing character relationships to be searched
     * @param start Starting character(hero)
     * @param dest Destination character(hero)
     * @spec.requires graph != null , start != null , dest != null
     * @throws IllegalArgumentException if <var>start</var> or <var>dest</var> is not in graph
     * @return List of edges from <var>start</var> to  <var>dest</var>, representing in the
     * alphabetically least path. <code>null</code> if there's not a path from <var>start</var>
     * to  <var>dest</var>.
     */
    public static List<Edge<String, String>> findPath(Graph<String, String> graph, String start, String dest) {
        if (graph == null) {
            throw new IllegalArgumentException("graph is null.");
        }
        if (start == null || dest == null) {
            throw new IllegalArgumentException("start or end is null.");
        }
        if (!(graph.containNode(start)) || !(graph.containNode(dest))) {
            throw new IllegalArgumentException("start or dest is not in the graph.");
        }
        Queue<String> workList = new LinkedList<>();
        Map<String, List<Edge<String, String>>> paths = new HashMap<>();
        workList.add(start);
        paths.put(start, new ArrayList<>());
        while(!workList.isEmpty()) {
            String character = workList.remove();
            if (character.equals(dest)) {
                return paths.get(character);
            }
            List<Edge<String, String>> edges = new ArrayList<>(graph.getEdges(character));
            edges.sort(new Comparator<>() {
                @Override
                public int compare(Edge<String, String> o1, Edge<String, String> o2) {
                    if(!o1.getDestination().equals(o2.getDestination())) {
                        return o1.getDestination().compareTo(o2.getDestination());
                    } else if(!o1.getLabel().equals(o2.getLabel())) {
                        return o1.getLabel().compareTo(o2.getLabel());
                    } else {
                        return 0;
                    }
                }
            });
            for(Edge<String, String> edge : edges) {
                if (!paths.containsKey(edge.getDestination())) {
                    List<Edge<String, String>> newPath = new ArrayList<>(paths.get(character));
                    newPath.add(edge);
                    paths.put(edge.getDestination(), newPath);
                    workList.add(edge.getDestination());
                }
            }
        }
        return null;
    }


    /**
     * Allows user to input two Marvel superheroes and get the shortest path between them.
     * @param args arguments
     * @throws Exception if <code>findPath()</code> meets exceptions
     */
    public static void main(String[] args)
            throws Exception {
        Graph<String, String> graph = createGraph("marvel.tsv");
        Scanner console = new Scanner(System.in);
        System.out.println("This is a path searcher for characters in Marvel universe.");
        System.out.println("It could find the shortest path between two superheroes.");

        Boolean againFlag = true;
        do {
            System.out.println("Please input the name of the first superhero: ");
            String start = console.nextLine();
            System.out.println("Please input the name of the second superhero: ");
            String dest = console.nextLine();
            while(!graph.containNode(start) && !graph.containNode(dest)) {
                if(!graph.containNode(start)) {
                    System.out.println("Invalid first superhero " + start);
                    System.out.println("Please input the name of the first superhero: ");
                    start = console.nextLine();
                }
                if(!graph.containNode(dest)) {
                    System.out.println("Invalid second superhero " + dest);
                    System.out.println("Please input the name of the second superhero: ");
                    dest = console.nextLine();
                }
            }
            List<Edge<String, String>> path = findPath(graph, start, dest);
            if(path == null) {
                System.out.println("There are no paths between " + start + " and " + dest + " :(");
            } else {
                // print out the path someway
                System.out.println("There is a shortest path between " + start + " and " + dest + "!");
                String s = start;
                for(Edge<String, String> edge : path) {
                    System.out.println(s + " to " + edge.getDestination() + " via " + edge.getLabel());
                    s = edge.getDestination();
                }
            }

            System.out.println("Would you want to search again?(\"y\" for YES; \"n\" for NO): ");
            String answer = console.nextLine().toLowerCase();
            againFlag = answer.charAt(0) == 'y';
        } while(againFlag);
        System.out.println("Thank you for using this searching tool!! Goodbye~");
    }
}
