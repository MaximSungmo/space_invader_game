import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame implements Commons {

    private static final long serialVersionUID = -4905230094675077405L;

    private JButton start, help;

    /*
     * Inicio
     */
    private static final String TOP_MESSAGE = "Space Invader Game";
    private static final String INITIAL_MESSAGE = "학번 / 이름";
    /*
     * Ajuda
     */
    private static final String HELP_TOP_MESSAGE = "도움말";
    private static final String HELP_MESSAGE = "Space button : 총쏘기<br/> " +
            "방향키 : 이동";

    JFrame frame = new JFrame("게임 화면");
    JFrame frame2 = new JFrame("메인 화면");
    JFrame frame3 = new JFrame("도움말");

    /*
     * Constructor
     */
    public Main() {
        String topmessage = "<html><br><br>" + TOP_MESSAGE + "</html>";
        String message = "<html>" + INITIAL_MESSAGE + "</html>";

        // 시작 버튼 만들기
        start = new JButton("Start");
        start.addActionListener(new ButtonListener());
        // 도움말 버튼 만들기
        help = new JButton("Help");
        help.addActionListener(new HelpButton());
        // Label 만들기
        JLabel content = new JLabel(message, SwingConstants.CENTER);
        JLabel title = new JLabel(topmessage, SwingConstants.CENTER);
        Font font = new Font("gothic", Font.BOLD, 12);
        content.setFont(font);
        Font font2 = new Font("gothic", Font.BOLD, 30);
        title.setFont(font2);

        frame2.add(content);
        frame2.add(title, BorderLayout.PAGE_START);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(help);
        buttonPanel.add(start);
        frame2.add(buttonPanel, BorderLayout.PAGE_END);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setSize(BOARD_WIDTH, BOARD_HEIGTH);
        frame2.setLocationRelativeTo(null);
        frame2.setVisible(true);
        frame2.setResizable(false);
    }

    public void closeIntro() {
        frame2.dispose();
        frame3.dispose();
    }

    public void closeHelp() {
        frame3.dispose();
    }


    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Display d = new Display();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(BOARD_WIDTH + 200, BOARD_HEIGTH + 100);
            frame.getContentPane().add(d);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            closeIntro();
        }
    }

    private class CloseHelp implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            closeHelp();
        }
    }

    private class HelpButton implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            JButton close = new JButton("닫기");
            close.addActionListener(new CloseHelp());

            String topmessage = "<html><br>" + HELP_TOP_MESSAGE + "</html>";
            String message = "<html>" + HELP_MESSAGE + "</html> ";
            JLabel content = new JLabel(message, SwingConstants.CENTER);
            JLabel title = new JLabel(topmessage, SwingConstants.CENTER);
            Font font = new Font("gothic", Font.BOLD, 12);
            content.setFont(font);
            Font font2 = new Font("gothic", Font.BOLD, 20);
            title.setFont(font2);

            frame3.add(title, BorderLayout.PAGE_START);
            frame3.add(content);
            frame3.add(close, BorderLayout.PAGE_END);

            frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame3.setSize(250, 300);
            frame3.setResizable(false);
            frame3.setLocationRelativeTo(null);
            frame3.setVisible(true);
        }
    }


    /**
     * 게임 실행 __main__
     *
     * @param args
     */
    public static void main(String[] args) {
        new Main();
    }

}


