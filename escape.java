import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
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

        SecretGate gate = new SecretGate(restored, shift);
        gate.waitForCompletion();

        for (Frame f : Frame.getFrames()) {
            f.dispose();
        }

        System.out.println("\n\n\u001B[36m=== The Dictator's Challange ===\u001B[0m");
        System.out.println("You received an encoded message. Decode it and enter the result:");
        
        attempts = 3;
        String encodedm = "VGhlIERpY3RhdG9yIHdoaXNwZXJzOiBWZW5pLCBWaWRpLCBWaWNpLg==";

        while (attempts > 0) {
            String answer = input.nextLine().trim();

            if (answer.equals(new String(Base64.getDecoder().decode(encodedm)))) {
                System.out.println("\n\u001B[32mCorrect! The path forward is revealed.\u001B[0m");
                new Decoded();
                break;
            } else {
                attempts--;
                score -= 10;
                if (attempts > 0) {
                    System.out.println("\u001B[31mIncorrect. Attemtps remaining: " + attempts + "\u001B[0m");
                } else {
                    System.out.println("\n\u001B[31mYou have failed the Dictator's test. Game Over.👎🏼\u001B[0m");
                    System.exit(0);
                }
            }
        }

        input.close();
        System.out.println("Final score: " + score);
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
    private boolean completed = false;
    private final Object lock = new Object();

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
                String encoded = "VGhlIERpY3RhdG9yIHdoaXNwZXJzOiBWZW5pLCBWaWRpLCBWaWNpLg==";

                JDialog dialog = new JDialog(frame, "Access Granted", true);
                dialog.setLayout(new BorderLayout(10, 10));
                dialog.setSize(500, 250);

                JLabel header = new JLabel("Access Granted.", SwingConstants.CENTER);
                header.setFont(header.getFont().deriveFont(16f));

                JTextArea message = new JTextArea(
                    encoded + "\n\n" +
                    "Now it is your turn, decode this message to progress."
                );
                message.setEditable(false);
                message.setLineWrap(true);
                message.setWrapStyleWord(true);
                message.setMargin(new Insets(10, 10, 10, 10));

                JScrollPane scrollPane = new JScrollPane(message);

                JPanel button = new JPanel(new FlowLayout());

                JButton copy = new JButton("Copy Encoded Message");
                copy.addActionListener(ev -> {
                    StringSelection stringSelection = new StringSelection(encoded);
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(stringSelection, null);

                    copy.setText("Copied!");
                    Timer timer = new Timer(1500, eve -> copy.setText("Copy Encoded Message"));
                    timer.setRepeats(false);
                    timer.start();
                });

                JButton ok = new JButton("OK");
                ok.addActionListener(ev -> {
                    dialog.dispose();
                    
                    synchronized (lock) {
                        completed = true;
                        lock.notify();
                    }
                });
                
                button.add(copy);
                button.add(ok);

                dialog.add(header, BorderLayout.NORTH);
                dialog.add(scrollPane, BorderLayout.CENTER);
                dialog.add(button, BorderLayout.SOUTH);

                dialog.setLocationRelativeTo(frame);
                dialog.setVisible(true);
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

    public void waitForCompletion() {
        synchronized (lock) {
            while (!completed) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}

class Decoded {
    private boolean completed = false;
    private final Object lock = new Object();

    public Decoded() {
        JFrame frame = new JFrame("The Ancient Safe");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(450, 300);
        frame.setLayout(new BorderLayout(15, 15));

        JPanel header = new JPanel();
        header.setBackground(new Color(139, 69, 19));
        JLabel hLabel = new JLabel("The Senator's Safe");
        header.add(hLabel);

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel instruction = new JLabel("A mysterious safe stands before you.\n\n"+
            "The inscription reads: 'Three symbols of victory'\n\n" + 
            "Enter the 3-digit combination:"
        );
        instruction.setAlignmentX(Component.CENTER_ALIGNMENT);
        instruction.setFont(new Font("Serif", Font.PLAIN, 14));

        JPanel input = new JPanel(new FlowLayout());

        JTextField primo = new JTextField(2);
        JTextField secondo = new JTextField(2);
        JTextField terzo = new JTextField(2);

        primo.setHorizontalAlignment(JTextField.CENTER);
        secondo.setHorizontalAlignment(JTextField.CENTER);
        terzo.setHorizontalAlignment(JTextField.CENTER);

        input.add(primo);
        input.add(new JLabel(" - "));
        input.add(secondo);
        input.add(new JLabel(" - "));
        input.add(terzo);

        JButton unlock = new JButton("Unlock Safe");
        unlock.setAlignmentX(Component.CENTER_ALIGNMENT);
        unlock.setFont(new Font("Serif", Font.BOLD, 16));

        center.add(instruction);
        center.add(Box.createVerticalStrut(20));
        center.add(input);
        center.add(Box.createHorizontalStrut(20));
        center.add(unlock);

        unlock.addActionListener(e -> {
            try {
                String pin = primo.getText() + secondo.getText() + terzo.getText();
            } catch (Exception ev) {
                JOptionPane.showMessageDialog(frame,
                    "Please enter valid digits (0-9)",
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE
                );
            }
        });

        frame.add(header, BorderLayout.NORTH);
        frame.add(center, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}