package exercise.daytime;
import jakarta.annotation.PostConstruct;

public class Day implements Daytime {
    private String name = "day";

    public String getName() {
        return name;
    }

    // BEGIN
    @PostConstruct
    public void init(){
        var massage = "\nBean Day is initialized!\n";
        System.out.println(massage);
    }
    // END
}
