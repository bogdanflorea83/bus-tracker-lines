import java.net.URL;

public class main {

    public static void main(String[] args) throws Exception{
        String hostName = new URL("http://localhost:8090/mgn-mobiel/").getHost();
        System.out.println(hostName.toString());

    }
}
