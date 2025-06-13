package login.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//www.perplexity.ai/search/4784f156-b9b9-4290-bfa3-1cd912832095?0=c
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class LoginPage extends JFrame {

    public LoginPage() {
        setTitle("로그인 화면");
        setSize(1440, 960);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // 로그인 패널
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBounds(220, 100, 1000, 600);
        loginPanel.setBackground(Color.WHITE);
        add(loginPanel);

        // Welcome Text
        JLabel welcomeLabel = new JLabel("Welcome Back !!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        welcomeLabel.setBounds(350, 50, 300, 30);
        loginPanel.add(welcomeLabel);

        JLabel subLabel = new JLabel("오늘도 안정적인 물류 운영을 도와드릴게요.", SwingConstants.CENTER);
        subLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subLabel.setBounds(300, 80, 400, 20);
        loginPanel.add(subLabel);

        // 구분선
        JSeparator separator = new JSeparator();
        separator.setBounds(300, 120, 400, 1);
        loginPanel.add(separator);

        // ID 입력 필드
        JTextField idField = new JTextField("ID");
        idField.setBounds(300, 160, 400, 40);
        idField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        idField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        loginPanel.add(idField);

        // PASSWORD 입력 필드
        JPasswordField passwordField = new JPasswordField("PASSWORD");
        passwordField.setBounds(300, 210, 400, 40);
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        loginPanel.add(passwordField);

        // 아이디/비밀번호 찾기
        JLabel findId = new JLabel("아이디 찾기");
        findId.setBounds(360, 270, 100, 20);
        loginPanel.add(findId);

        JLabel divider = new JLabel("|");
        divider.setBounds(480, 270, 10, 20);
        loginPanel.add(divider);

        JLabel findPw = new JLabel("비밀번호 찾기");
        findPw.setBounds(500, 270, 120, 20);
        loginPanel.add(findPw);

        // 로그인 버튼
        JButton loginButton = new JButton("SIGN IN");
        loginButton.setBounds(400, 320, 200, 45);
        loginButton.setBackground(Color.blue); // 파란색
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        loginPanel.add(loginButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	LoginPage frame = new LoginPage();
            frame.setVisible(true);
        });
    }
}
