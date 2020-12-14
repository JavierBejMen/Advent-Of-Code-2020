
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.stream.Stream;

public class PasswordChecker {
    private static boolean checkPassword(String passwordLine){
        String[] elements = passwordLine.split("[\\s-]");
        int min = Integer.parseInt(elements[0]);
        int max = Integer.parseInt(elements[1]);
        char letter = elements[2].charAt(0);
        long ocurrences = elements[3].chars().filter(ch -> ch == letter).count();
        //System.out.format("%d-%d %c: %s %d\n", min, max, letter, elements[3], ocurrences);

        return  (ocurrences >= min) && (ocurrences <= max);
    }

    public static void main(String[] args) {
        
        if (args.length == 0){
            System.out.println("Needed Path to input file");
            System.exit(0);
        }

        Path path = Paths.get(args[0]);
        if (!Files.exists(path)){
            System.out.println("File " + args[0] + " Does not exists");
            System.exit(0);
        }

        Stream<String> lines = null;
        try {
            lines = Files.lines(path);
            System.out.println(lines.parallel().filter(PasswordChecker::checkPassword).count());

        }catch (Exception ex){
            System.out.println(ex.getMessage());

        }finally{
            if (lines != null) lines.close();
        }
        
        
    
    }
}