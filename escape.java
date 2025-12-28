import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

        JPanel panel = new JPanel(new GridLayout(2,1));
        panel.setBackground(new Color(133, 29, 33));
        panel.setBorder(BorderFactory.createLineBorder(new Color(212, 175, 55),3));

        JLabel omenl = new JLabel(omen, SwingConstants.CENTER);
        omenl.setFont(new Font("Serif", Font.BOLD, 22));
        omenl.setForeground(new Color(212, 175, 55));

        JLabel meaningl = new JLabel("<html><center>" + meaning + "</center></html>", SwingConstants.CENTER);
        meaningl.setFont(new Font("Serif", Font.PLAIN, 16));
        meaningl.setForeground(Color.WHITE);

        panel.add(omenl);
        panel.add(meaningl);

        frame.add(panel);
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
        header.setFont(new Font("Serif", Font.BOLD, 24));
        header.setForeground(Color.WHITE);
        header.add(hLabel);

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel instruction = new JLabel("<html><center>A mysterious safe stands before you.<br><br>"+
            "The inscription reads: <i>'Three symbols of victory'</i><br><br>" + 
            "Enter the 3-digit combination:</center></html>"
        );
        instruction.setAlignmentX(Component.CENTER_ALIGNMENT);
        instruction.setFont(new Font("Serif", Font.PLAIN, 14));

        JPanel input = new JPanel(new FlowLayout());

        JTextField primo = new JTextField(2);
        JTextField secondo = new JTextField(2);
        JTextField terzo = new JTextField(2);

        Font digit = new Font("Monospaced", Font.BOLD, 24);
        primo.setFont(digit);
        secondo.setFont(digit);
        terzo.setFont(digit);

        primo.setHorizontalAlignment(JTextField.CENTER);
        secondo.setHorizontalAlignment(JTextField.CENTER);
        terzo.setHorizontalAlignment(JTextField.CENTER);

        primo.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (primo.getText().length() == 1) secondo.requestFocus();
            }
        });
        secondo.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (secondo.getText().length() == 1) terzo.requestFocus();
            }
        });

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

        JLabel attempt = new JLabel("3 attempts left", SwingConstants.CENTER);
        attempt.setFont(new Font("Serif", Font.ITALIC, 12));

        final int attempts [] = {3};

        unlock.addActionListener(e -> {
            try {
                String pin = primo.getText() + secondo.getText() + terzo.getText();

                if (pin.equals(new String(Base64.getDecoder().decode("NTU1")))) {
                    JDialog success = new JDialog(frame, "Safe Unlocked!", true);
                    success.setSize(500, 300);
                    success.setLayout(new BorderLayout(10, 10));

                    JLabel successh = new JLabel(" The Safe Opens...", SwingConstants.CENTER);
                    successh.setFont(new Font("Serif", Font.BOLD, 20));
                    successh.setForeground(new Color(0, 128, 0));

                    JTextArea message = new JTextArea(
                        "Inside, you find an ancient scroll:\n\n" +
                        "\"Veni, Vidi, Vici - I came, I saw, I conquered.\n" +
                        "But even the mighty Caesar knew:\n" +
                        "True power lies not in conquest, but in knowledge.\n\n" +
                        "The Roman Empire has fallen, yet its wisdom endures.\n" +
                        "Journey now to Florence, where the ancient knowledge\n" +
                        "was reborn in art, science, and mystery.\n\n" +
                        "Seek the Master of Renaissance...\n" +
                        "His mirror holds the key.\"\n\n" +
                        "--- Chapter I: Complete ---"
                    );
                    message.setEditable(false);
                    message.setLineWrap(true);
                    message.setWrapStyleWord(true);
                    message.setMargin(new Insets(15, 15, 15, 15));
                    message.setFont(new Font("Serif", Font.PLAIN, 13));

                    JButton continueButton = new JButton("Continue to Renaissance");
                    continueButton.setFont(new Font("Serif", Font.BOLD, 14));
                    continueButton.addActionListener(ev -> {
                        success.dispose();
                        frame.dispose();

                        synchronized (lock) {
                            completed = true;
                            lock.notify();
                        }
                    });

                    JPanel button = new JPanel();
                    button.add(continueButton);

                    success.add(successh, BorderLayout.NORTH);
                    success.add(new JScrollPane(message), BorderLayout.CENTER);
                    success.add(button, BorderLayout.SOUTH);

                    success.setLocationRelativeTo(frame);
                    success.setVisible(true);
                } else {
                    attempts[0]--;

                    if (attempts[0] > 0) {
                        attempt.setText(attempts[0] + "attempts left");
                        attempt.setForeground(Color.RED);

                        if (attempts[0] == 1) {
                            JOptionPane.showMessageDialog(frame,
                                "Wrong combination!\n\nHint: Think about the message...",
                                "Incorrect",
                                JOptionPane.WARNING_MESSAGE
                            );
                        } else {
                            JOptionPane.showMessageDialog(frame, 
                                "Wrong combination! Try again.",
                                "Incorrect",
                                JOptionPane.ERROR_MESSAGE
                            );
                        }

                        primo.setText("");
                        secondo.setText("");
                        terzo.setText("");
                        primo.requestFocus();
                    } else {
                        JOptionPane.showMessageDialog(frame, 
                            "The safe locks permanently. \nYou have failed.",
                            "Game Over",
                            JOptionPane.ERROR_MESSAGE
                        );
                        System.exit(0);
                    }
                }
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
        frame.add(attempt, BorderLayout.SOUTH);

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