import java.awt.BorderLayout;
import java.util.Scanner;
import javax.swing.*;

public class escape {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char welcome[] = { 'e', 'p', 'a', 'c', 's', 'E', ' ', 'o', 't', ' ', 'e', 'm', 'o', 'c', 'l', 'e', 'W' };
        char abc[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
                't', 'u', 'v', 'w', 'x', 'y', 'z' };

        

        for (int i = 0; i < welcome.length; i++) {
            System.out.print(welcome[i]);
        }

        String text = first(first, );
        System.out.println("\n\n" + text);
        System.out.println("First case, Resolve this to go forward. (for hints print 1)");
        while (true) {
            String result = input.nextLine();
            if (result.equals("1")) {
                
            } else if (result.equals("")) {
                System.out.println(first(text, ));
                break;
            }
        }
        CaesarSecret psw = new CaesarSecret();

        input.close();
    }

    public static String first(String text, int k) {
        return CifrarioCesare.cifra(text, k);
    }

    

}

class Hints {
    public Hints(String hints, String suggest) {
        JFrame frame = new JFrame("Hints");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 300);

        JLabel hint = new JLabel(hints, SwingConstants.CENTER);
        frame.add(hint);

        JLabel label = new JLabel(suggest, SwingConstants.CENTER);
        frame.add(label, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

class CifrarioCesare {
    
    public static String cifra(String testo, int chiave) {
        StringBuilder risult = new StringBuilder();
        int shift = chiave % 26;

        
        for (char c : testo.toCharArray()) {
            
            if (Character.isUpperCase(c)) {
                char nuovo = (char) ('A' + (c - 'A' + shift + 26) % 26);
                risult.append(nuovo);
                
            } else if (Character.isLowerCase(c)) {
                char nuovo = (char) ('a' + (c - 'a' + shift + 26) % 26);
                risult.append(nuovo);
                
            } else {
                risult.append(c);
            }
            
        }
        return risult.toString();
    }
}

class CaesarSecret {
    public CaesarSecret() {
        JFrame frame = new JFrame("Caesar's Secret");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300,300);

        JTextField name = new JTextField();
        frame.add(name);
        JPasswordField psw = new JPasswordField();
        frame.add(psw);

        frame.setVisible(true);
    }
}

class EnterFlags {
    public EnterFlags() {
        JFrame frame = new JFrame("Flags");
    }
}