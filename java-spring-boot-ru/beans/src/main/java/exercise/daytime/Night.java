package exercise.daytime;
import jakarta.annotation.PostConstruct;

public class Night implements Daytime {
    private String name = "night";

    public String getName() {
        return name;
    }

    // BEGIN
    @PostConstruct
    public void init(){
        var massage = "\nBean Night is initialized!\n";
        System.out.println(massage);
    }
    // END
}
