package dashBoard;

import services.ServiceCategorie;

public class MainTest {
    public static void main(String[] args) {
        ServiceCategorie serviceCategorie = new ServiceCategorie();
        System.out.println(serviceCategorie.getAll());
    }
}
