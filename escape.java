import java.awt.*;
import java.util.Scanner;
import javax.swing.*;
import java.util.Base64;

public class escape {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        char[] welcome = "rotaiV evlaS".toCharArray();
        for (char c : welcome)
            System.out.print(c);

        int shift = (int) (Math.PI * 5) % 26;
        int attempts = 3;
        int score = 100;

        String encoded = "THd0YyBScHRocGcgdXRhYSwgaXd0IEh0Y3BpdCBsd3hoZXRndHM6IEhFRkcgamN4aXRzIGx4aXcgaXd0IFhzdGggY2picXRnLCB5ZHhjdHMgcW4gamNzdGdocmRndC4gVXhnaGksIGd0aGlkZ3QgaXd4aCBocmdkYWEgbHhpdyBpd3QgWHN0aCBpZCBhdHBnYyBiZGd0Lg==";

        byte[] decode = Base64.getDecoder().decode(encoded);
        String encrypted = new String(decode);

        System.out.println("\n\n\u001B[34mFragmentum Obscurum:\u001B[0m");
        System.out.println("\u001B[43m" + encrypted + "\u001B[0m");
        System.out.println("\nTo proceed, restore the scroll. (Type 1 for omen)");

        String restored = null;

        while (attempts > 0 && restored == null) {
            String result = input.nextLine();

            if (result.equals("1")) {
                new OmenWindow("Idus Martiae", "When daggers met destiny");
                continue;
            } else {
                try {
                    int k = Integer.parseInt(result);

                    if (k == shift) {
                        restored = CaesarCipher.cipher(encrypted, -k);
                        System.out.println("\u001B[32mScroll Restored:\u001B[0m\n" + restored);
                    } else {
                        attempts--;
                        score -= 20;

                        String rot = CaesarCipher.cipher(encrypted, 13);

                        System.out.println("\u001B[31mIncorrect shift.\u001B[0m");
                        System.out.println("Attempts left: " + attempts);
                        System.out.println("Hint of corruption (ROT13):");
                        System.out.println(rot);
                        if (attempts == 0) {
                            System.out.println("\n\u001B[31mThe gods abandon you. Game Over.\u001B[0m");
                            System.exit(0);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("The gods do not understand this symbol.");
                }
            }
        }

        new SecretGate(restored, shift);
        input.close();
    }
}

class OmenWindow {
    public OmenWindow(String omen, String meaning) {
        JFrame frame = new JFrame("Omen of the Augurs");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 200);

        frame.setLayout(new BorderLayout());
        frame.add(new JLabel(omen, SwingConstants.CENTER), BorderLayout.CENTER);
        frame.add(new JLabel(meaning, SwingConstants.CENTER), BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

class CaesarCipher {
    public static String cipher(String text, int key) {
        StringBuilder out = new StringBuilder();
        int shift = key % 26;

        for (char c : text.toCharArray()) {
            if (Character.isUpperCase(c)) {
                out.append((char) ('A' + (c - 'A' + shift + 26) % 26));
            } else if (Character.isLowerCase(c)) {
                out.append((char) ('a' + (c - 'a' + shift + 26) % 26));
            } else {
                out.append(c);
            }
        }
        return out.toString();
    }
}

class SecretGate {
    private final String flag;

    public SecretGate(String restored, int shift) {

        this.flag = "c3Bxcl8=";

        JFrame frame = new JFrame("Gate of the Dictator");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(4, 1));

        JLabel nameLabel = new JLabel("Enter your Roman Name:");
        JTextField name = new JTextField();

        JLabel passLabel = new JLabel("Enter the Secret Phrase:");
        JPasswordField pass = new JPasswordField();

        JButton submit = new JButton("Invoke the Senate");

        frame.add(nameLabel);
        frame.add(name);
        frame.add(passLabel);
        frame.add(pass);
        frame.add(submit);

        String x = new String(Base64.getDecoder().decode(flag)) + shift;

        submit.addActionListener(e -> {
            String attempt = new String(pass.getPassword());
            if (attempt.equals(x)) {
                JOptionPane.showMessageDialog(frame,
                        "Access Granted.\n" +
                                "The Dictator whispers: Veni, Vidi, Vici.");
            } else {
                JOptionPane.showMessageDialog(frame,
                        "The Senate rejects your claim.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}