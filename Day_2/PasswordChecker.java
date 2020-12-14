
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.stream.Stream;

public class PasswordChecker {

    private static boolean checkPassword1(String passwordLine){
        String[] elements = passwordLine.split("[\\s-]");
        int min = Integer.parseInt(elements[0]);
        int max = Integer.parseInt(elements[1]);
        char letter = elements[2].charAt(0);
        long ocurrences = elements[3].chars().filter(ch -> ch == letter).count();
        //System.out.format("%d-%d %c: %s %d\n", min, max, letter, elements[3], ocurrences);

        return  (ocurrences >= min) && (ocurrences <= max);
    }

    private static boolean checkPassword2(String passwordLine){
        String[] elements = passwordLine.split("[\\s-]");
        int first = Integer.parseInt(elements[0])-1;
        int second = Integer.parseInt(elements[1])-1;
        char letter = elements[2].charAt(0);
        boolean check = false;
        
        try{
            if (elements[3].charAt(first) == letter) check = true;
        }catch (Exception e){}
        try{
            if (elements[3].charAt(second) == letter) check = !check;
        }catch (Exception e){}
        //System.out.format("%d-%d %c: %s %b\n", first, second, letter, elements[3], check);

        return check;
    }

    public static void main(String[] args) {
        
        if (args.length != 2){
            System.out.println("Usage: PasswordChecker path policy[0, 1]");
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
            switch (args[1]){
                case "0":
                    System.out.println(
                        lines.parallel().filter(PasswordChecker::checkPassword1).count()
                    );
                    break;
                case "1":
                    System.out.println(
                        lines.parallel().filter(PasswordChecker::checkPassword2).count()
                    );
                    break;
                default:
                    System.out.println("Unrecognized Policy, values accepted are 0 or 1");
            }

        }catch (Exception ex){
            System.out.println(ex.getMessage());

        }finally{
            if (lines != null) lines.close();
        }
    }
}