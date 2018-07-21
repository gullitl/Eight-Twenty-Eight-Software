
import static gullit.IdGenerator.generateId;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Plamedi L. Lusembo
 */
public class NewClass {

    public static void main(String[] args) {
        try {
            for (int x = 0; x <= 10; x++) {
                System.out.println(generateId());
            }

        } catch (Exception ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
