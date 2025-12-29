import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;
import java.util.Set;

import javax.swing.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class escape {
    public static int score = 100;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        char[] welcome = "rotaiV evlaS".toCharArray();
        for (char c : welcome)
            System.out.print(c);

        int shift = (int) (Math.PI * 5) % 26;
        int attempts = 3;

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
                Decoded safe = new Decoded();
                safe.waitForCompletion();

                for (Frame f : Frame.getFrames()) {
                    f.dispose();
                }

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

        System.out.println("\n\n\u001B[35m╔════════════════════════════════════╗\u001B[0m");
        System.out.println("\u001B[35m║   CHAPTER II: THE RENAISSANCE      ║\u001B[0m");
        System.out.println("\u001B[35m╔════════════════════════════════════╗\u001B[0m");
        System.out.println("\nFlorence, 1503...");

        LeonardoMirror leonardo = new LeonardoMirror();
        leonardo.waitForCompletion();

        for (Frame f : Frame.getFrames()) {
            f.dispose();
        }

        System.out.println("\nYou arrive at the Palazzo Vecchio...");
        System.out.println("\n\u001B[36m=== The Guardian's Question ===\u001B[0m");
        System.out.println("A hooded figure blocks your path to the Black Plinth.");
        System.out.println("'Answer me this,' they whisper:");
        System.out.println("\n'What is the name of the biggest and most family of Florence?'");
        System.out.println("'The family that ruled this city and patronized the greatest artists?'\n");

        attempts = 3;
        boolean family = false;

        String correct [] = {"TWVkaWNp", "VGhlIE1lZGljaQ==", "TWVkaWNpIGZhbWlseQ==", "SG91c2Ugb2YgTWVkaWNp", "ZGUnIE1lZGljaQ=="};

        while (attempts > 0 && !family) {
            System.out.print("> ");
            String familya = input.nextLine().trim();

            for (String right : correct) {
                if (familya.equalsIgnoreCase(new String(Base64.getDecoder().decode(right)))) {
                    System.out.println("\n\u001B[32mCorrect! The " + new String(Base64.getDecoder().decode(correct[0])) + " family.\u001B[0m");
                    System.out.println("The figure nods and steps aside.");
                    System.out.println("'The Black Plinth awaits you...'\n");
                    family = true;
                    break;
                }
            }

            if (!family) {
                attempts--;
                score -= 10;

                if (attempts > 0) {
                    System.out.println("\u001B[31mIncorrect.\u001B[0m Attempts remaining: " + attempts);
                    if (attempts == 1) {
                        System.out.println("Hint: Leonardo mentioned them as his patrons...");
                    }
                } else {
                    System.out.println("\n\u001B[31mThe guardian blocks your path permanently. Game Over.");
                    System.exit(0);
                }
            }
        }

        new MediciCipher(input);

        for (Frame f : Frame.getFrames()) {
            f.dispose();
        }

        System.out.println("\n\n\u001B[31m╔════════════════════════════════════╗\u001B[0m");
        System.out.println("\u001B[31m║   CHAPTER III: DANTE'S INFERNO     ║\u001B[0m");
        System.out.println("\u001B[31m╚════════════════════════════════════╝\u001B[0m");
        System.out.println("\nFlorence, Year 1300...\n");

        new DanteInferno(input);

        for (Frame f : Frame.getFrames()) {
            f.dispose();
        }

        System.out.println("\n\n\u001B[36m╔════════════════════════════════════╗\u001B[0m");
        System.out.println("\u001B[36m║   CHAPTER IV: THE NEW WORLD        ║\u001B[0m");
        System.out.println("\u001B[36m╚════════════════════════════════════╝\u001B[0m");

        for (Frame f : Frame.getFrames()) {
            f.dispose();
        }

        new Chapter1492(score);

        input.close();
        System.out.println("Final score: " + score);
    }
}

class OmenWindow {
    public OmenWindow(String omen, String meaning) {
        JFrame frame = new JFrame("Omen of the Augurs");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.setBackground(new Color(133, 29, 33));
        panel.setBorder(BorderFactory.createLineBorder(new Color(212, 175, 55), 3));

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
                                "Now it is your turn, decode this message to progress.");
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
        frame.setSize(450, 350);
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

        JLabel instruction = new JLabel("<html><center>A mysterious safe stands before you.<br><br>" +
                "The inscription reads: <i>'Three symbols of victory'</i><br><br>" +
                "Enter the 3-digit combination:</center></html>");
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

        final int attempts[] = { 3 };

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
                                    "--- Chapter I: Complete ---");
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
                                    JOptionPane.WARNING_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(frame,
                                    "Wrong combination! Try again.",
                                    "Incorrect",
                                    JOptionPane.ERROR_MESSAGE);
                        }

                        primo.setText("");
                        secondo.setText("");
                        terzo.setText("");
                        primo.requestFocus();
                    } else {
                        JOptionPane.showMessageDialog(frame,
                                "The safe locks permanently. \nYou have failed.",
                                "Game Over",
                                JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                    }
                }
            } catch (Exception ev) {
                JOptionPane.showMessageDialog(frame,
                        "Please enter valid digits (0-9)",
                        "Invalid Input",
                        JOptionPane.WARNING_MESSAGE);
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

class LeonardoMirror {
    private boolean completed = false;
    private final Object lock = new Object();

    public LeonardoMirror() {
        JFrame frame = new JFrame("Leonardo's Workshop - 1503");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLayout(new BorderLayout(10, 10));

        JPanel header = new JPanel();
        header.setBackground(new Color(101, 67, 33));
        JLabel headerl = new JLabel("Leonardo da Vinci's Secret Notes");
        headerl.setFont(new Font("Serif", Font.BOLD, 22));
        headerl.setForeground(new Color(255, 223, 186));
        header.add(headerl);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        content.setBackground(new Color(245, 235, 220));

        JTextArea story = new JTextArea(
            "Florence, Year 1503\n\n" +
            "You enter Leonardo's workshop. Scattered papers cover his desk.\n" +
            "The master wrote in a peculiar way, readable only in a one way.\n" +
            "This was his method to protect his discoveries from prying eyes.\n\n" +
            "On his desk, you find a mysterious note..."
        );
        story.setEditable(false);
        story.setLineWrap(true);
        story.setWrapStyleWord(true);
        story.setFont(new Font("Serif", Font.ITALIC, 13));
        story.setBackground(new Color(245, 235, 220));
        story.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel specchio = new JPanel();
        specchio.setLayout(new BorderLayout());
        specchio.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.DARK_GRAY, 2),
            "Leonardo's Note"
        ));
        specchio.setBackground(Color.WHITE);

        String primo = ".oihcceV ozzalaP eht ni htnilP kcalB eht keeS .tsap eht ot yek eht sdloh";
        String secondo = "ecnerolF fo ylimaf tseggiB ehT";
        String text = primo + " " + secondo;

        JTextArea texta = new JTextArea(text);
        texta.setEditable(false);
        texta.setLineWrap(true);
        texta.setWrapStyleWord(true);
        texta.setFont(new Font("Monospaced", Font.BOLD, 16));
        texta.setMargin(new Insets(15, 15, 15, 15));
        texta.setBackground(new Color(255, 253, 240));

        specchio.add(new JScrollPane(texta), BorderLayout.CENTER);

        JLabel hint = new JLabel("Hint: Try reading it backwards, or use a mirror...");
        hint.setFont(new Font("Serif", Font.ITALIC, 11));
        hint.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel input = new JPanel();
        input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));
        input.setBackground(new Color(245, 235, 220));
        input.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel inputl = new JLabel("Decode the message and enter it below:");
        inputl.setFont(new Font("Serif", Font.BOLD, 13));
        inputl.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextArea answera = new JTextArea(3, 40);
        answera.setLineWrap(true);
        answera.setWrapStyleWord(true);
        answera.setFont(new Font("Serif", Font.PLAIN, 13));
        JScrollPane answers = new JScrollPane(answera);
        answers.setAlignmentX(Component.LEFT_ALIGNMENT);

        input.add(inputl);
        input.add(Box.createVerticalStrut(5));
        input.add(answers);

        JPanel button = new JPanel(new FlowLayout());
        button.setBackground(new Color(245, 235, 220));

        JButton hintb = new JButton("-30p Hint");
        JButton sumbit = new JButton("Sumbit Answer");
        JButton copy = new JButton("Copy Text");

        hintb.setFont(new Font("Serif", Font.PLAIN, 12));
        sumbit.setFont(new Font("Serif", Font.BOLD, 14));
        copy.setFont(new Font("Serif", Font.PLAIN, 12));

        button.add(copy);
        button.add(hintb);
        button.add(sumbit);

        copy.addActionListener(e -> {
            StringSelection selection = new StringSelection(text);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, null);
            copy.setText("Copied!");
            Timer timer = new Timer(1500, ev -> copy.setText("Copy Text"));
            timer.setRepeats(false);
            timer.start();
        });

        hintb.addActionListener(e -> {
            escape.score -= 30;
            String decode = new StringBuilder(secondo).reverse().toString();
            JOptionPane.showMessageDialog(frame,
                "Mirror Reflection:\n\n" + decode,
                "Looking Glass",
                JOptionPane.INFORMATION_MESSAGE
             );
        });

        final int attempts [] = {3};
        JLabel attempt = new JLabel("Attempts: 3", SwingConstants.CENTER);
        attempt.setFont(new Font("Serif", Font.ITALIC, 11));

        sumbit.addActionListener(e -> {
            String answer = answera.getText().trim();
            String correct = new StringBuilder(text).reverse().toString().trim();

            if (answer.equalsIgnoreCase(correct)) {
                showSuccessDialog(frame);
                JOptionPane.showMessageDialog(frame, "Correct! The path is revealed.");
            } else {
                attempts[0]--;
                attempt.setText("Attempts: " + attempts[0]);

                if (attempts[0] > 0) {
                    JOptionPane.showMessageDialog(frame, 
                        "Not quite right. Remeber: Mirror\n" +
                        "Attemtps remaining: " + attempts[0],
                        "Try Again",
                        JOptionPane.WARNING_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(frame, 
                        "You've run out of attempts.\nThe workshop doors close forever.",
                        "Game Over",
                        JOptionPane.ERROR_MESSAGE
                    );
                    System.exit(0);
                }
            }
        });

        content.add(story);
        content.add(Box.createVerticalStrut(15));
        content.add(specchio);
        content.add(Box.createVerticalStrut(10));
        //content.add(hint);
        content.add(Box.createVerticalStrut(10));
        content.add(input);
        content.add(Box.createVerticalStrut(10));
        //content.add();

        frame.add(header, BorderLayout.NORTH);
        frame.add(content, BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void showSuccessDialog(JFrame parentFrame) {
        JDialog dialog = new JDialog(parentFrame, "Mystery Solved!", true);
        dialog.setSize(550, 350);
        dialog.setLayout(new BorderLayout(10, 10));

        JLabel header = new JLabel("Leonardo's Secret Revealed", SwingConstants.CENTER);
        header.setFont(new Font("Serif", Font.BOLD, 20));
        header.setForeground(new Color(0, 100, 0));

        JTextArea message = new JTextArea(
            "You've decoded Leonardo's mirror writing!\n\n" +
            "\"The Biggest family of Florence holds the key to the past.\n" +
            "Seek the Black Plinth in the Palazzo Vecchio.\"\n\n" +
            "Leonardo nods approvingly as you decipher his notes.\n" +
            "He gestures to a hidden passage:\n\n" +
            "\"This family were my patrons, but also keepers of secrets\n" +
            "older than Florence itself. Their cipher guards knowledge\n" +
            "from a darker time...\"\n\n" +
            "The passage leads toward the Palazzo Vecchio..."
        );
        message.setEditable(false);
        message.setLineWrap(true);
        message.setWrapStyleWord(true);
        message.setFont(new Font("Serif", Font.PLAIN, 13));
        message.setMargin(new Insets(15, 15, 15, 15));

        JPanel button = new JPanel();

        JButton continueb = new JButton("Continue to Palazzo Vecchio");
        continueb.setFont(new Font("Serif", Font.BOLD, 14));
        continueb.addActionListener(e -> {
            dialog.dispose();
            parentFrame.dispose();

            synchronized (lock) {
                completed = true;
                lock.notify();
            }
        });
        button.add(continueb);

        dialog.add(header, BorderLayout.NORTH);
        dialog.add(new JScrollPane(message), BorderLayout.CENTER);
        dialog.add(button, BorderLayout.SOUTH);

        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
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

class MediciCipher {
    private boolean completed = false;
    private final Object lock = new Object();

    private static final Map<Character, Character> CIPHER_MAP = new HashMap<>();
    static {
        String plain = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String cipher = "TVhWS1BGWkhJSllRV0VSVEdVU0NBQkxET04=";

        for (int i = 0; i < plain.length(); i++) {
            CIPHER_MAP.put(new String(Base64.getDecoder().decode(cipher)).charAt(i), plain.charAt(i));
        }
    }

    public MediciCipher(Scanner input) {
        showIntroFrame();

        waitForIntroCompletion();

        System.out.println("\n\u001B[36m=== The Medici Cipher Challenge ===\u001B[0m");
        System.out.println("\nThe encrypted message on the Black Plinth:");
        System.out.println("\u001B[43m");

        String text = "KMWCP GMUAKP UPIASW MW CSP KMUY RFSGCP. " +
        "MW CSP JPMU 1300, M EPGC OPCW KWCG CSP MXNUU. " +
        "CPU WMYP: KMWCP MHLSHLPUL.";

        System.out.println(text);
        System.out.println("\u001b[0m");

        System.out.println("\n\u001B[33mKnown cipher key:\u001B[0m");
        System.out.println("M = A    |    C = T    |    P = E    |    S = H");
        System.out.println("W = N    |    G = G    |    U = S    |    K = D");

        System.out.println("\nDecode the message using the cipher key.");
        System.out.println("Type 'hint' for help, 'decode' to auto-decode (-20p), or enter your answer:");

        int attempts = 3;
        String correct = "DANTE GUARDS SECRETS OF THE DARK FUTURE. " +
                        "IN THE YEAR 1300, A POET WENT INTO THE ABYSS. " +
                        "HIS NAME: DANTE ALIGHIERI.";

        while (attempts > 0) {
            System.out.print("\n>");
            String response = input.nextLine().trim();

            if (response.equalsIgnoreCase("hint")) {
                System.out.println("\n\u001B[33mHint:\u001B[0m Look at the pattern 'KMWCP'");
                System.out.println("Using the key: K=D, M=A, W=N, C=T, P=E");
                System.out.println("This spells 'DANTE'!");
                continue;
            } else if (response.equalsIgnoreCase("decode")) {
                escape.score -= 20;
                String decoded = decodeMediciCipher(response);
                System.out.println("\n\u001B[32mAuto-decoded message:\u001B[0m");
                System.out.println(decoded);
                System.out.println("\n(You can now enter this as yuor answer)");
                continue;
            }

            String normalizedAnswer = response.toUpperCase().replaceAll("[^A-Z0-9\\s", "");
            String normalizedCorrect = correct.replaceAll("[^A-Z0-9\\s", "");

            if (normalizedAnswer.equals(normalizedCorrect)) {
                System.out.println("\n\u001B[32mCorrect! You've decoded the Medici cipher!\u001B[0m");

                SwingUtilities.invokeLater(() -> showSuccessFrame());
                break;
            } else {
                attempts--;
                if (attempts > 0) {
                    System.out.println("\u001B[31mIncorrect decoding.\u001B[0m Attempts remaining: " + attempts);
                } else {
                    System.out.println("\n\u001B[31mYou've failed to decode the Medici cipher. Game Over.\u001B[0m");
                    System.exit(0);
                }
            }
        }

        waitForCompletion();
    }

    private void showIntroFrame() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Palazzo Vecchio - The Black Plinth");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(650, 400);
            frame.setLayout(new BorderLayout(15, 15));

            JPanel header = new JPanel();
            header.setBackground(new Color(128, 0,0));
            JLabel headerl = new JLabel("⚜️ The Medici's Secret");
            headerl.setFont(new Font("Serif", Font.BOLD, 24));
            headerl.setForeground(new Color(255, 215, 0));
            header.add(headerl);

            JTextArea story = new JTextArea(
                "Palazzo Vecchio, Florence - 1503\n\n" +
                "You enter the Hall of the Five Hundred, a vast chamber adorned\n" +
                "with Renaissance frescoes. In the center stands the Black Plinth,\n" +
                "an ancient stone monument bearing the Medici crest.\n\n" +
                "Carved into its dark surface is an encrypted message.\n\n" +
                "The Medici family, rulers of Florence and patrons of the arts,\n" +
                "were also keepers of ancient secrets. They used an uncknown\n" +
                "cipher to protect their most guarded knowledge.\n\n" +
                "The inscription glows faintly in the torchlight...\n" +
                "You must decipher it to proceed."
            );
            story.setEditable(false);
            story.setLineWrap(true);
            story.setWrapStyleWord(true);
            story.setFont(new Font("Serif", Font.PLAIN, 14));
            story.setMargin(new Insets(20, 30, 20, 30));
            story.setBackground(new Color(245, 240, 230));

            JPanel button = new JPanel();

            JButton continueb = new JButton("Examine the Cipher");
            continueb.setFont(new Font("Serif", Font.BOLD, 16));
            continueb.addActionListener(e -> {
                frame.dispose();
                synchronized (introLock) {
                    introComplete = true;
                    introLock.notify();
                }
            });
            button.add(continueb);

            frame.add(header, BorderLayout.NORTH);
            frame.add(new JScrollPane(story), BorderLayout.CENTER);
            frame.add(button, BorderLayout.SOUTH);

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private boolean introComplete = false;
    private final Object introLock = new Object();

    private void waitForIntroCompletion() {
        synchronized (introLock) {
            while (!introComplete) {
                try {
                    introLock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private String decodeMediciCipher(String encrypted) {
        StringBuilder decoded = new StringBuilder();
        for (char c : encrypted.toCharArray()) {
            if (Character.isLetter(c)) {
                char upper = Character.toUpperCase(c);
                char decrypted = CIPHER_MAP.getOrDefault(upper, upper);
                decoded.append(decrypted);
            } else {
                decoded.append(c);
            }
        }
        return decoded.toString();
    }

    private void showSuccessFrame() {
        JFrame frame = new JFrame("The Medici Secret Revealed");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(650, 450);
        frame.setLayout(new BorderLayout(10, 10));

        JLabel header = new JLabel("⚜️ Cipher Decoded Successfully", SwingConstants.CENTER);
        header.setFont(new Font("Serif", Font.BOLD, 22));
        header.setForeground(new Color(128, 0, 0));
        header.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));

        JTextArea message = new JTextArea(
            "You've cracked the Medici cipher!\n\n" +
            "\"DANTE GUARDS SECRETS OF THE DARK FUTURE.\n" +
            "IN THE YEAR 1300, A POET WENT INTO THE ABYSS.\n" +
            "HIS NAME: DANTE ALIGHIERI.\"\n\n" +
            "As you speak the decoded words aloud, the Black Plinth begins\n" +
            "to tremble. Ancient mechanism rumble deep beneath the\n" +
            "Palazzo Vecchio.\n\n" +
            "A section of the marble floor slides away, revealing a spiral\n" +
            "staircase descending into darkness.\n\n" +
            "Carved into the stone archway, you see words that send a\n" +
            "chill down your spine:\n\n" +
            "\"LASCIATE OGNE SPERANZA, VOI CH'INTRATE\"\n\n" +
            "The passage leads backward through time...\n" +
            "To Florence, 1300. To the age of Dante Alighieri."
        );
        message.setEditable(false);
        message.setLineWrap(true);
        message.setWrapStyleWord(true);
        message.setFont(new Font("Serif", Font.PLAIN, 13));
        message.setMargin(new Insets(15, 20, 15, 20));
        message.setBackground(new Color(245, 240, 230));

        JPanel button = new JPanel();

        JButton continueb = new JButton("Descend into the Darkness");
        continueb.setFont(new Font("Serif", Font.BOLD, 16));
        continueb.setBackground(new Color(139, 0, 0));
        continueb.setForeground(Color.WHITE);
        continueb.addActionListener(e -> {
            frame.dispose();
            synchronized (lock) {
                completed = true;
                lock.notify();
            }
        });
        button.add(continueb);

        frame.add(header, BorderLayout.NORTH);
        frame.add(new JScrollPane(message), BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);

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

class DanteInferno {
    private boolean completed = false;
    private final Object lock = new Object();

    private static final String CIRCLES [] = {
        "First Circle - Limbo (Virtuous Pagans)",
        "Second Circle - Lust",
        "Third Circle - Gluttony",
        "Fourth Circle - Greed",
        "Fifth Circle - Wrath",
        "Sixth Circle - Heresy",
        "Seventh Circle - Violence",
        "Eighth Circle - Fraud",
        "Ninth Circle - Treachery"
    };

    public DanteInferno(Scanner input) {
        showIntroFrame();
        waitForIntroCompletion();

        System.out.println("\n\u001B[31m=== Journey Through the Nine Circles ===\u001B[0m");
        System.out.println("\nYou stand at the gates of the Hell with the poet Dante as your guide.");
        System.out.println("To find the secret you seek, you must answer riddles from the circles.\n");

        System.out.println("\u001B[33mThe nine Circles of Hell:\u001B[0m");
        for (int i = 0; i < CIRCLES.length; i++) {
            System.out.println((i + 1) + ". " + CIRCLES[i]);
        }

        System.out.println("\n\u001B[36mDante speaks:\u001B[0m");
        System.out.println("\"The answer you seek lies in the deepest circle,");
        System.out.println("where the greatest traitors are punished for eternity.\"");

        System.out.println("\n\u001B[31mFirst Riddle:\u001B[0m");
        System.out.println("In which circle are traitors punished?");
        System.out.println("(Enter the circle number: 1-9)");

        int attempts = 3;
        boolean riddle = false;

        while (attempts > 0 && !riddle) {
            System.out.print("\n>");
            String answer = input.nextLine().trim();

            if (answer.equals(String.valueOf((int) (Math.PI * 3)))) {
                System.out.println("\n\u001B[32mCorrect! The Ninth Circle - Treachery.\u001B[0m");
                riddle = true;
            } else {
                attempts--;
                if (attempts > 0) {
                    System.out.println("\u001B[31mWrong circle.\u001B[0m Attempts remaining: " + attempts);
                    if (attempts == 1) {
                        System.out.println("\u001B[33mHint: It's the LOWEST circle, the worst of all sins.\u001B[0m");
                    }
                } else {
                    System.out.println("\n\u001B[31mYou are lost in the circles, AntiInferno attends you. Game Over.\u001B[0m");
                    System.exit(0);
                }
            }
        }

        System.out.println("\n\u001B[36mDante continues:\u001B[0m");
        System.out.println("\"In the center of the Ninth Circle, frozen in ice,");
        System.out.println("Satan himself devours three traitors for eternity");
        System.out.println("One betrayed Christ. Two betrayed Caesar on the Ides of March.\"");

        System.out.println("\n\u001B[31mSecond Riddle:\u001B[0m");
        System.out.println("Name TWO traitors who assassinated Julius Caesar.");
        System.out.println("(Enter both names separated by 'and', e.g., 'Virgilio and Dante')");
        System.out.println("You encountered their deed at the beginning of your journey...");

        attempts = 3;
        riddle = false;

        while (attempts > 0 && !riddle) {
            System.out.print("\n> ");
            String answer = input.nextLine().trim().toLowerCase();

            boolean primo = answer.contains(new String(Base64.getDecoder().decode("YnJ1dHVz"))) || answer.contains(new String(Base64.getDecoder().decode("YnJ1dG8=")));
            boolean secondo = answer.contains(new String(Base64.getDecoder().decode("Y2Fzc2l1cw=="))) || answer.contains(new String(Base64.getDecoder().decode("Y2Fzc2lv")));

            if (primo && secondo) {
                System.out.println("\n\u001B[32mCorrect! Brutus and Cassius - the assassins of Caesar!\u001B[0m");
                System.out.println("'Et tu, Brute?' - Caesar's final words echo through eternity.");
                riddle = true;
            } else if (primo || secondo) {
                System.out.println("\u001B[33mYou have one correct, but TWO traitors killed Caesar.\u001B[0m");
                attempts--;
            } else {
                attempts--;
                if (attempts > 0) {
                    System.out.println("\u001B[31mIncorrect.\u001B[0m " + attempts + " attempts left.");
                    if (attempts == 1) {
                        System.out.println("\u001B[33mHint: Remember the 'Omen' from the Roman chapter - Idus Martiae.\u001B[0m");
                    }
                } else {
                    System.out.println("\n\u001B[31mThe names of the traitors remain unknown. Game Over.\u001B[0m");
                    System.exit(0);
                }
            }
        }

        System.out.println("\n\u001B[36mDante reveals:\u001B[0m");
        System.out.println("\"Now you understand. The betrayel of Caesar connects all things.");
        System.out.println("But to unlock the final secret, slove this mystery:\"");

        System.out.println("\n\u001B[31mFinal Riddle:\u001B[0m");
        System.out.println("How many circles must you descend to reach the traitors?");
        System.out.println("How many daggers struck Caesar?");
        System.out.println("On which day was Caesar killed?");
        System.out.println("\nWhat is the sum? (int)");

        attempts = 3;
        riddle = false;

        while (attempts > 0 && !riddle) {
            System.out.print("\n> ");
            String answer = input.nextLine().trim();

            if (answer.equals(new String(Base64.getDecoder().decode("NDc=")))) {
                System.out.println("\n\u001B[32mCorrect! 9 + 23 + 15 = 47\u001B[0m");
                System.out.println("\nThe frozen lake at the center of Hell begins to crack...");
                riddle = true;
            } else {
                attempts--;
                if (attempts > 0 ) {
                    System.out.println("\u001B[31mIncorrect calculation.\u001B[0m Attempts remaining: " + attempts);
                    if (attempts == 1) {
                        escape.score -= 10;
                        System.out.println("\u001B[33mHint: 9 circles + 23 daggers + 15th of March\u001B[0m");
                    }
                } else {
                    System.out.println("\n\u001B[31mThe path forward is sealed. Game Over.\u001B[0m");
                    System.exit(0);
                }
            }
        }

        SwingUtilities.invokeLater(() -> showSuccessFrame());
        waitForCompletion();
    }

    private boolean introComplete = false;
    private final Object introLock = new Object();

    private void showIntroFrame() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("The Gates of Hell");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(700, 500);
            frame.setLayout(new BorderLayout(10, 10));

            JPanel header = new JPanel();
            header.setBackground(new Color(80, 0, 0));
            JLabel headerl = new JLabel("Dante's Inferno - Anno 1300");
            headerl.setFont(new Font("Serif", Font.BOLD, 26));
            headerl.setForeground(new Color(255, 100, 100));
            header.add(headerl);

            JTextArea story = new JTextArea(
                "Florence, Year 1300 - Good Fridat\n\n" +
                "You find yourself in a dark wood, the straight way lost.\n\n" +
                "A figure emerges from the shadows - the poet Dante Alighieri,\n" +
                "author of the Divine Comedy. He has journeyed through Hell itself\n" +
                "and lived to tell the tale.\n\n" +
                "\"I know why you have come,\" Dante says, his eyes haunted by\n" +
                "visions of the damned. \"You seek the connection between\n" +
                "Caesar's fall and the mysteries that followed.\"\n\n" +
                "He points to a dark gateway carved into the earth.\n" +
                "Above it, words are insctibed in stone:\n\n" +
                "\"LASCIATE OGNE SPERANZA; VOI CH'INTRATE\"\n\n" +
                "\"The answer lies in the deepest circle,\" Dante whispers.\n" +
                "\"Where the greatest traitors are punished.\n" +
                "Come, I shall be your guide through the Nine Circles of Hell.\""
            );
            story.setEditable(false);
            story.setLineWrap(true);
            story.setWrapStyleWord(true);
            story.setFont(new Font("Serif", Font.PLAIN, 14));
            story.setMargin(new Insets(20, 30, 20,30));
            story.setBackground(new Color(20, 20, 20));
            story.setForeground(new Color(220, 220, 220));

            JPanel button = new JPanel();
            button.setBackground(new Color(40, 40, 4));
            JButton enter = new JButton("Enter the Inferno");
            enter.setFont(new Font("Serif", Font.BOLD, 16));
            enter.setBackground(new Color(139, 0, 0));
            enter.setForeground(Color.WHITE);
            enter.addActionListener(e -> {
                frame.dispose();
                synchronized (introLock) {
                    introComplete = true;
                    introLock.notify();
                }
            });
            button.add(enter);

            frame.add(header, BorderLayout.NORTH);
            frame.add(new JScrollPane(story), BorderLayout.CENTER);
            frame.add(button, BorderLayout.SOUTH);

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private void waitForIntroCompletion() {
        synchronized (introLock) {
            while (!introComplete) {
                try {
                    introLock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private void showSuccessFrame() {
        JFrame frame = new JFrame("The Heart of the Inferno");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(700, 550);
        frame.setLayout(new BorderLayout(10, 10));

        JLabel header = new JLabel("The Ninth Circle Revealed", SwingConstants.CENTER);
        header.setFont(new Font("Serif", Font.BOLD, 24));
        header.setForeground(new Color(139, 0, 0));
        header.setBorder(BorderFactory.createEmptyBorder(15, 0,10, 0));

        JTextArea message = new JTextArea(
            "You have descended through all Nine Circles.\n\n" +
            "At the frozen center of Hell, you witness a terrifying sight:\n" +
            "Satan himself, a gaint beast trapped in ice, eternaly devouring\n" +
            "three traitors:\n\n" +
            ". JUDAS ISCARIOT - who betrayed Christ\n" +
            ". BRUTUS - who betrayed Caesar\n" +
            ". CASSIUS - who betrayed Caesar\n\n" +
            "\"Now you see,\" Dante says solenly. \"The circle is complete.\n" +
            "Your journey began with Caesar's triumph - Veni, Vidi, Vici.\n" +
            "It led through the Renaissance, where knowledge was rebron.\n" +
            "And now you witness the eternal punishment of htise who\n" +
            "betrayed the greatest of Romans.\"\n\n" +
            "From the frozen depths, a scroll emerges, preserved in ice \n" +
            "for centuries. On it, a riddle:\n\n" +
            "\"When the Genoese sailor finds the edge of known world,\n" +
            "and the Renaissance man draws the perfect proportions,\n" +
            "the final truth shall be revealed.\"\n\n" +
            "The year 1492 calls to you..."
        );
        message.setEditable(false);
        message.setLineWrap(true);
        message.setWrapStyleWord(true);
        message.setFont(new Font("serif", Font.PLAIN, 13));
        message.setMargin(new Insets(15, 20, 15, 20));

        JPanel button = new JPanel();
        JButton continueb = new JButton("Emerge from Hell -> 1492");
        continueb.setFont(new Font("Serif", Font.BOLD, 16));
        continueb.addActionListener(e -> {
            frame.dispose();
            synchronized (lock) {
                completed = true;
                lock.notify();
            }
        });
        button.add(continueb);

        frame.add(header, BorderLayout.NORTH);
        frame.add(new JScrollPane(message), BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);

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

class Chapter1492 {
    private int finalScore;

    public Chapter1492(int score) {
        this.finalScore = score;

        new InteractiveMapPuzzle(this);
    }

    public void startStage2() {
        new VitruvianManPuzzle(this);
    }

    public void startStage3() {
        new ShipNavigationPuzzle(this);
    }
}

class InteractiveMapPuzzle extends JFrame {
    private Chapter1492 parent;
    private Set<String> clickedL = new HashSet<>();
    private JLabel status;

    private Map<String, Point> locations = new HashMap<>();

    public InteractiveMapPuzzle(Chapter1492 parent) {
        this.parent = parent;

        setTitle("Stage 1: Map the Journey");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel header = new JLabel("Stage 1: Trace Your Journey Through Time", SwingConstants.CENTER);
        header.setFont(new Font("Serif", Font.BOLD, 20));
        header.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JPanel instruction = new JPanel();
        instruction.setBackground(new Color(255, 248, 220));
        JLabel instructions = new JLabel("<html><center>Click on the 4 locations you visited in chronological order.</center></html>");
        instructions.setFont(new Font("Serif", Font.ITALIC, 13));
        instruction.add(instructions);

        MapPanel map = new MapPanel();

        add(header, BorderLayout.NORTH);
        add(instruction, BorderLayout.SOUTH);
        add(map, BorderLayout.CENTER);
        add(status, BorderLayout.PAGE_END);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    class MapPanel extends JPanel {
        private Map<String, Rectangle> locationA = new HashMap<>();

        public MapPanel() {
            setBackground(new Color(172, 216, 230));

            locationA.put("Rome", new Rectangle(450, 280, 60, 60));
            locationA.put("Florence", new Rectangle(100, 250, 60, 60));
            locationA.put("Atlantic", new Rectangle(250, 300, 80, 80));

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    handleClick(e.getPoint());
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D gr = (Graphics2D) g;
            gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            gr.setColor(new Color(34, 139, 34));

            gr.fillOval(400, 200, 200, 200);
            gr.fillOval(380, 380, 150, 180);
            gr.fillOval(100, 250, 120, 300);

            gr.setColor(Color.RED);
            gr.setFont(new Font("Serif", Font.BOLD, 14));

            for (Map.Entry<String, Rectangle> entry : locationA.entrySet()) {
                Rectangle r = entry.getValue();

                if (clickedL.contains(entry.getKey())) {
                    gr.setColor(new Color(0, 200, 0));
                    gr.fillOval(r.x, r.y, r.width, r.height);
                    gr.setColor(Color.WHITE);
                    gr.drawString("✓", r.x + 20, r.y + 20);
                } else {
                    gr.setColor(Color.RED);
                    gr.fillOval(r.x, r.y, r.width, r.height);
                    gr.setColor(Color.WHITE);
                    gr.drawString("?", r.x + 20, r.y + 20);
                }

                gr.setColor(Color.BLACK);
                gr.drawString(entry.getKey(), r.x - 10, r.y - 10);
            }

            gr.setColor(Color.BLACK);
            gr.drawString("Timeline: 44 BC → 1503 → 1300 → 1492", 200, 600);
        }

        private void handleClick(Point p) {
            for (Map.Entry<String, Rectangle> entry : locationA.entrySet()) {
                clickedL.add(entry.getKey());
                status.setText("Location found: " + clickedL.size() + "/4 - " + entry.getKey() + " marked!");
                repaint();

                if (clickedL.size() >= 3) {
                    Timer timer = new Timer(1000, e -> {
                        JOptionPane.showMessageDialog(InteractiveMapPuzzle.this, 
                            "Excellent! You've traced the journey through time.\n\n" +
                            "Now, let's explore the mathematics of perfection...",
                            "Stage 1 Complete!",
                            JOptionPane.INFORMATION_MESSAGE
                        );
                        dispose();
                        parent.startStage2();
                    });
                    timer.setRepeats(false);
                    timer.start();
                }
                break;
            }
        }
    }
}

class VitruvianManPuzzle extends JFrame {
    private Chapter1492 parent;
    private JLabel result;
    private Point point1 = null;
    private Point point2 = null;
    private int measurements = 0;

    public VitruvianManPuzzle(Chapter1492 parent) {
        this.parent = parent;

        setTitle("Stage 2: The Vitruvian Man");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel header = new JLabel("Stage 2: Measure the Golden Ratio", SwingConstants.CENTER);
        header.setFont(new Font("Serif", Font.BOLD, 20));

        JPanel instruction = new JPanel(new BorderLayout());
        instruction.setBackground(new Color(255, 248, 220));
        JLabel instructions = new JLabel("<html><center>Click two points on the Vitruvian Man to measure.<br>" +
        "Find measurements that approximate the Golden Ratio φ</center></html>"
        );
        instructions.setFont(new Font("Serif", Font.ITALIC, 13));
        instruction.add(instructions, BorderLayout.CENTER);

        result = new JLabel("Click two points to measure", SwingConstants.CENTER);
        result.setFont(new Font("Serif", Font.BOLD, 14));
        instruction.add(result, BorderLayout.SOUTH);

        VitruvianPanel vitruvian = new VitruvianPanel();

        add(header, BorderLayout.NORTH);
        add(vitruvian, BorderLayout.CENTER);
        add(instruction, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    class VitruvianPanel extends JPanel {
        public VitruvianPanel() {
            setBackground(new Color(250, 240, 230));

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    handleClick(e.getPoint());
                }
            });
        }

        @Override
        public void paintComponents(Graphics g) {
            super.paintComponents(g);
            Graphics2D gr = (Graphics2D) g;
            gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int cX = getWidth() / 2;
            int cY = getHeight() / 2;

            gr.setColor(new Color(200, 200, 200));
            gr.setStroke(new BasicStroke(2));
            gr.drawOval(cX - 150, cY - 150, 300, 300);

            gr.drawRect(cX -120, cY -120, 240, 240);

            gr.setColor(new Color(101, 67, 33));

            gr.fillOval(cX - 25, cY - 120, 50, 50);
            gr.fillOval(cX - 15, cY - 70, 30, 80);

            gr.setStroke(new BasicStroke(8));
            gr.drawLine(cY, cY - 50, cX - 100, cY -30);
            gr.drawLine(cY, cY - 50, cX + 100, cY - 30);

            gr.drawLine(cY, cY + 10, cX - 40, cY + 100);
            gr.drawLine(cY, cY + 10, cX + 40, cY + 100);

            gr.setColor(Color.RED);
            int points [][] = {
                {cX, cY - 95},
                {cX, cY},
                {cX, cY + 100}
            };

            for (int[] pt : points) {
                gr.fillOval(pt[0] - 5, pt[1] - 5, 10, 10);
            }

            if (point1 != null) {
                gr.setColor(Color.BLUE);
                gr.fillOval(point1.x - 8, point1.y - 8, 16, 16);
            }
            if (point2 != null) {
                gr.setColor(Color.GREEN);
                gr.fillOval(point2.x - 8, point2.y - 8, 16, 16);

                gr.setColor(Color.BLUE);
                gr.setStroke(new BasicStroke(2));
                gr.drawLine(point1.x, point1.y, point2.x, point2.y);
            }
        }

        private void handleClick(Point p) {
            if (point1 == null) {
                point1 = p;
                result.setText("First point selected. Click second point.");
            } else if (point2 == null) {
                point2 = p;

                double d = Math.sqrt(Math.pow(point2.x - point1.x, 2) + Math.pow(point2.y - point1.y, 2));
                measurements++;

                result.setText("Distance: " + (int)d + " pixels");

                if (measurements >= 2) {
                    Timer timer = new Timer(1500, e -> {
                        int response = JOptionPane.showConfirmDialog(VitruvianManPuzzle.this, 
                            "The Golden Ratio φ ≈ 1.618 governs perfect proportions.\n\n" +
                            "The ratio of total height to navel height ≈ 1.618\n" +
                            "This divine proportion appears throughout nature and art.\n\n" +
                            "Do you understand the Golden Ratio?",
                            "Stage 2 Complete!",
                            JOptionPane.YES_NO_OPTION
                        );

                        if (response == JOptionPane.YES_OPTION) {
                            dispose();
                            parent.startStage3();
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                }

                Timer rTimer = new Timer(2000, e -> {
                    point1 = null;
                    point2 = null;
                    repaint();
                });
                rTimer.setRepeats(false);
                rTimer.start();
            }
        }
    }
}