import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.stream.Stream;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.stream.StreamSupport;
import java.util.stream.Collectors;
import java.lang.Iterable;



public class Questionnair {
    
    public static Iterable<ArrayList<String>> getQuestionnair(Scanner file) {
        return new Iterable<ArrayList<String>>() {
    
            @Override
            public Iterator<ArrayList<String>> iterator() {
                return new Iterator<ArrayList<String>>() {
    
                    @Override
                    public boolean hasNext() {
                        return file.hasNext();
                    }
    
                    @Override
                    public ArrayList<String> next() {
                        ArrayList<String> lines = new ArrayList<>();
                        boolean endReached = false;
                        String line;
                        while (file.hasNext() && !endReached){
                            line = file.nextLine();
                            if (line.isEmpty()){
                                endReached = true;
                            } else{
                                lines.add(line);
                            }
                        }
                        return lines;
                    }
    
                    @Override
                    public void remove() {}
                };
            }
        };
    }
    public static void main(String[] args) {
        if (args.length != 1){
            System.out.println("Usage: java Cuestionary path_to_input");
            System.exit(1);
        }
        
        Path path = Paths.get(args[0]);
        // HashSet<Character> reference = new HashSet<Character>
        //     (IntStream.range(97, 123)
        //     .mapToObj(v -> (char)v)
        //     .collect(Collectors.toCollection(HashSet::new)));

        //System.out.println(reference);
        
        final Integer[] total = {0, 0, 0, 0};
        try {
            try (Scanner fScann = new Scanner(path.toFile())){
                Stream<ArrayList<String>> questionnaires;
                questionnaires = StreamSupport.stream(getQuestionnair(fScann).spliterator(), false);

                questionnaires.forEach((questionnair) -> {
                    HashSet<Character> reference1 = new HashSet<Character>();
                    HashSet<Character> reference2 = new HashSet<Character>();
                    boolean[] first = {true};
                    questionnair.forEach(question -> {
                        HashSet<Character> reference2Aux = new HashSet<Character>();
                        question.chars().forEach(answer -> {
                            reference1.add((char)answer);
                            reference2Aux.add((char)answer);
                        });
                        if (first[0]){
                            reference2.addAll(reference2Aux);
                            first[0] = false;
                        } else{
                            reference2.retainAll(reference2Aux);
                        }
                    });
                    
                    total[0] += reference1.size();
                    total[1] += reference2.size();
                });

                System.out.println("Part 1: " + total[0]);
                System.out.println("Part 2: " + total[1]);
            }
        } catch(FileNotFoundException e){
            System.out.println("File " + args[0] + " Does not exists");
            System.out.println(e.getMessage());
            System.exit(1);
        }

    }
    
}
