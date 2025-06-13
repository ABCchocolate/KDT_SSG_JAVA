package main.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class PageLayout extends JFrame {
    JPanel p_west;
    JPanel p_west_left;
    JPanel p_west_right;

    JPanel p_center;
    JPanel p_center_north;
    RoundedPanel p_north_profile; // This is the rounded panel
    JPanel p_center_main;

    ImageIcon icon_search;
    ImageIcon icon_help;
    ImageIcon icon_message;
    ImageIcon icon_profile;
    ImageIcon icon_config;
    
    ImageIcon icon_inbound_menu;
    ImageIcon icon_outbound_menu;
    ImageIcon icon_stock_menu;
    ImageIcon icon_statistics_menu;
    ImageIcon icon_user_menu;
    
    // New icon for the profile in the north panel
    ImageIcon icon_north_profile_img; 
 

    JLabel la_search;
    JLabel la_help;
    JLabel la_message;
    JLabel la_profile;
    JLabel la_config;
    
    JLabel la_inbound_icon_display;
    JLabel la_outbound_icon_display;
    JLabel la_stock_icon_display;
    JLabel la_statistics_icon_display;
    JLabel la_user_icon_display;
    
    JLabel la_title;
    JLabel la_inbound; //입고 라벨 (대분류 텍스트)
    JLabel la_current_inbound;
    JLabel la_request_inbound;
    JLabel la_check_inbound;
    JLabel la_outbound; //출고 라벨 (대분류 텍스트)
    JLabel la_current_outbound;
    JLabel la_request_outbount;
    JLabel la_check_outbound;
    JLabel la_stock; //재고 현황 (대분류 텍스트)
    JLabel la_statistics_Reports; //통계 및 보고서 (대분류 텍스트)
    JLabel la_statistics;
    JLabel la_reports;
    JLabel la_user_management; //유저 관리 (대분류 텍스트)
    
    // New labels for the north panel content
    JLabel la_north_page_title; // "사용자 페이지"
    JLabel la_north_user_info;  // "마케팅 1팀\n김범수님"
    JLabel la_north_profile_display_img; // Profile icon for the north panel
    
    private JPanel inboundSubMenuPanel;
    private JPanel outboundSubMenuPanel;
    private JPanel statisticsSubMenuPanel;

    private final Color HIGHLIGHT_COLOR = new Color(144, 199, 233);
    private final Color DEFAULT_MAJOR_MENU_BG = new Color(227, 227, 227);

    private JPanel inboundMajorMenuPanel;
    private JPanel outboundMajorMenuPanel;
    private JPanel stockMajorMenuPanel;
    private JPanel statisticsMajorMenuPanel;
    private JPanel userMajorMenuPanel;
    
    Color background;
    Color side_background;
    Color sub_menu_color;

    public PageLayout() {
        p_west = new JPanel();
        p_west_left = new JPanel();
        p_west_right = new JPanel(); 
        
        p_north_profile = new RoundedPanel(30);

        p_center = new JPanel();
        p_center_north = new JPanel();
        p_center_main = new RoundedPanel(30);

        la_search = new JLabel();
        la_profile = new JLabel();
        la_message = new JLabel();
        la_help = new JLabel();
        la_config = new JLabel();
        
        la_north_page_title = new JLabel("사용자 페이지"); // Initialize "사용자 페이지" label
        la_north_user_info = new JLabel("<html>마케팅 1팀<br/>김범수님</html>"); // Use HTML for multi-line text
        la_north_profile_display_img = new JLabel(); // Initialize profile icon label
       
        la_title = new JLabel("사용자 페이지", SwingConstants.CENTER); 
        la_inbound  = new JLabel("입고");
        la_current_inbound = new JLabel("입고 현황");
        la_request_inbound = new JLabel("입고 요청");
        la_check_inbound = new JLabel("입고 검수");
        la_outbound = new JLabel("출고");
        la_current_outbound = new JLabel("출고 현황");
        la_request_outbount = new JLabel("출고 요청");
        la_check_outbound = new JLabel("출고 검수");
        la_stock = new JLabel("재고 현황");
        la_statistics_Reports = new JLabel("통계 및 보고서");
        la_statistics = new JLabel("입/출고 현황 통계");
        la_reports = new JLabel("입/출고 현황 보고서");
        la_user_management = new JLabel("사용자 관리");
        
        la_inbound_icon_display = new JLabel();
        la_outbound_icon_display = new JLabel();
        la_stock_icon_display = new JLabel();
        la_statistics_icon_display = new JLabel();
        la_user_icon_display = new JLabel();
       
        background = new Color(227, 227, 227);
        side_background = new Color(34, 124, 181);
        sub_menu_color = new Color(100, 100, 100);

        p_west.setLayout(new BorderLayout());
        p_west_left.setLayout(new BoxLayout(p_west_left, BoxLayout.Y_AXIS));
        p_west_right.setLayout(new BoxLayout(p_west_right, BoxLayout.Y_AXIS)); 

        p_center.setLayout(new BorderLayout());

        p_west.setPreferredSize(new Dimension(450, 960));
        p_west_left.setPreferredSize(new Dimension(100, 960));
        p_west_right.setPreferredSize(new Dimension(350, 960));
        p_center_north.setPreferredSize(new Dimension(990,100));

        p_west.setBorder(new LineBorder(Color.BLACK, 1));

        icon_search = new ImageIcon(getClass().getResource("/search.png"));
        icon_profile = new ImageIcon(getClass().getResource("/profile.png"));
        icon_message = new ImageIcon(getClass().getResource("/message.png"));
        icon_help = new ImageIcon(getClass().getResource("/help.png"));
        icon_config = new ImageIcon(getClass().getResource("/config.png"));
        
        icon_inbound_menu = new ImageIcon(getClass().getResource("/inbound.png")); 
        icon_outbound_menu = new ImageIcon(getClass().getResource("/outbound.png"));
        icon_stock_menu = new ImageIcon(getClass().getResource("/stock.png"));
        icon_statistics_menu = new ImageIcon(getClass().getResource("/statics.png"));
        icon_user_menu = new ImageIcon(getClass().getResource("/user.png"));

        // Load the profile icon for the north panel
        icon_north_profile_img = new ImageIcon(getClass().getResource("/Generic_avatar.png")); // Replace with your actual profile icon path
        la_north_profile_display_img.setIcon(icon_north_profile_img);

        la_search.setIcon(icon_search);
        la_profile.setIcon(icon_profile);
        la_message.setIcon(icon_message);
        la_help.setIcon(icon_help);
        la_config.setIcon(icon_config);
        
        la_inbound_icon_display.setIcon(icon_inbound_menu);
        la_outbound_icon_display.setIcon(icon_outbound_menu);
        la_stock_icon_display.setIcon(icon_stock_menu);
        la_statistics_icon_display.setIcon(icon_statistics_menu);
        la_user_icon_display.setIcon(icon_user_menu);
        
        la_search.setAlignmentX(Component.CENTER_ALIGNMENT);
        la_profile.setAlignmentX(Component.CENTER_ALIGNMENT);
        la_message.setAlignmentX(Component.CENTER_ALIGNMENT);
        la_help.setAlignmentX(Component.CENTER_ALIGNMENT);
        la_config.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        Dimension label_size = new Dimension(100, 100);
        la_search.setPreferredSize(label_size);
        la_profile.setPreferredSize(label_size);
        la_message.setPreferredSize(label_size);
        la_help.setPreferredSize(label_size);
        la_config.setPreferredSize(label_size);
        
        la_title.setFont(new Font("맑은 고딕", Font.BOLD, 45));
        la_title.setForeground(side_background);
        la_title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Styling for the north panel content
        la_north_page_title.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        la_north_page_title.setForeground(new Color(34, 124, 181)); // Blue color from sidebar

        la_north_user_info.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        la_north_user_info.setForeground(Color.DARK_GRAY);

        // Adjust profile icon size for the north panel
        icon_north_profile_img = new ImageIcon(icon_north_profile_img.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)); // Scale image
        la_north_profile_display_img.setIcon(icon_north_profile_img);
        la_north_profile_display_img.setVerticalAlignment(SwingConstants.CENTER);
        la_north_profile_display_img.setPreferredSize(new Dimension(100,80));

        Font majorMenuFont = new Font("맑은 고딕", Font.BOLD, 22);
        Color majorMenuColor = new Color(50, 50, 50);

        la_inbound.setFont(majorMenuFont);
        la_inbound.setForeground(majorMenuColor);
        la_outbound.setFont(majorMenuFont);
        la_outbound.setForeground(majorMenuColor);
        la_stock.setFont(majorMenuFont);
        la_stock.setForeground(majorMenuColor);
        la_statistics_Reports.setFont(majorMenuFont);
        la_statistics_Reports.setForeground(majorMenuColor);
        la_user_management.setFont(majorMenuFont);
        la_user_management.setForeground(majorMenuColor);

        Font subMenuFont = new Font("맑은 고딕", Font.PLAIN, 16);
        
        la_current_inbound.setFont(subMenuFont);
        la_current_inbound.setForeground(sub_menu_color);
        la_request_inbound.setFont(subMenuFont);
        la_request_inbound.setForeground(sub_menu_color);
        la_check_inbound.setFont(subMenuFont);
        la_check_inbound.setForeground(sub_menu_color);

        la_current_outbound.setFont(subMenuFont);
        la_current_outbound.setForeground(sub_menu_color);
        la_request_outbount.setFont(subMenuFont);
        la_request_outbount.setForeground(sub_menu_color);
        la_check_outbound.setFont(subMenuFont);
        la_check_outbound.setForeground(sub_menu_color);

        la_statistics.setFont(subMenuFont);
        la_statistics.setForeground(sub_menu_color);
        la_reports.setFont(subMenuFont);
        la_reports.setForeground(sub_menu_color);

        p_west_left.setBackground(side_background);
        p_west_right.setBackground(background);
        p_center.setBackground(background);
        p_center_main.setBackground(background);
        p_center_north.setBackground(Color.LIGHT_GRAY);
        p_center_main.setBackground(Color.WHITE);

        p_north_profile.setBackground(Color.WHITE);
        p_north_profile.setPreferredSize(new Dimension(900, 80));
        p_north_profile.setLayout(new BorderLayout()); // Use BorderLayout for p_north_profile

        // Add components to p_north_profile
        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS)); // Vertical stack for text
        userInfoPanel.setOpaque(false); // Make it transparent
        userInfoPanel.add(la_north_user_info);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0)); // FlowLayout for icon and text
        rightPanel.setOpaque(false); // Make it transparent
        rightPanel.add(la_north_profile_display_img);
        rightPanel.add(userInfoPanel);
        
        p_north_profile.add(Box.createRigidArea(new Dimension(20, 0)), BorderLayout.WEST); // Left padding
        p_north_profile.add(la_north_page_title, BorderLayout.WEST);
        p_north_profile.add(rightPanel, BorderLayout.EAST);
        p_north_profile.add(Box.createRigidArea(new Dimension(0, 0)), BorderLayout.CENTER); // Filler for center

        p_center_north.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        p_center_north.add(p_north_profile);

        p_west_left.add(Box.createRigidArea(new Dimension(0,30)));
        p_west_left.add(la_profile);
        p_west_left.add(la_message);
        p_west_left.add(la_search);
        p_west_left.add(Box.createRigidArea(new Dimension(0,400)));
        p_west_left.add(la_help);
        p_west_left.add(la_config);

        p_west_right.add(Box.createVerticalStrut(50));
        p_west_right.add(la_title);
        p_west_right.add(Box.createVerticalStrut(50));

        inboundMajorMenuPanel = createMajorMenuPanel(la_inbound_icon_display, la_inbound);
        p_west_right.add(inboundMajorMenuPanel);
        inboundSubMenuPanel = createSubMenuPanel(new JLabel[]{la_current_inbound, la_request_inbound, la_check_inbound});
        p_west_right.add(inboundSubMenuPanel);
        p_west_right.add(Box.createVerticalStrut(20));

        outboundMajorMenuPanel = createMajorMenuPanel(la_outbound_icon_display, la_outbound);
        p_west_right.add(outboundMajorMenuPanel);
        outboundSubMenuPanel = createSubMenuPanel(new JLabel[]{la_current_outbound, la_request_outbount, la_check_outbound});
        p_west_right.add(outboundSubMenuPanel);
        p_west_right.add(Box.createVerticalStrut(20));

        stockMajorMenuPanel = createMajorMenuPanel(la_stock_icon_display, la_stock);
        p_west_right.add(stockMajorMenuPanel);
        p_west_right.add(Box.createVerticalStrut(20));

        statisticsMajorMenuPanel = createMajorMenuPanel(la_statistics_icon_display, la_statistics_Reports);
        p_west_right.add(statisticsMajorMenuPanel);
        statisticsSubMenuPanel = createSubMenuPanel(new JLabel[]{la_statistics, la_reports}); 
        p_west_right.add(statisticsSubMenuPanel);
        p_west_right.add(Box.createVerticalStrut(20));

        userMajorMenuPanel = createMajorMenuPanel(la_user_icon_display, la_user_management); 
        p_west_right.add(userMajorMenuPanel);
        p_west_right.add(Box.createVerticalGlue());
        
        p_west.add(p_west_left, BorderLayout.WEST);
        p_west.add(p_west_right, BorderLayout.CENTER);
        
        add(p_west, BorderLayout.WEST);

        p_center.add(p_center_north, BorderLayout.NORTH);
        p_center.add(p_center_main, BorderLayout.CENTER);
        add(p_center, BorderLayout.CENTER);

        setSize(1440, 960);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(background);

        setVisible(true);
        
        // Event Listeners
        la_inbound_icon_display.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                inboundSubMenuPanel.setVisible(!inboundSubMenuPanel.isVisible());
                
                if (inboundSubMenuPanel.isVisible()) {
                    inboundMajorMenuPanel.setBackground(HIGHLIGHT_COLOR);
                } else {
                    inboundMajorMenuPanel.setBackground(DEFAULT_MAJOR_MENU_BG);
                }
                p_west_right.revalidate();
                p_west_right.repaint();
            }
        });

        la_outbound_icon_display.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                outboundSubMenuPanel.setVisible(!outboundSubMenuPanel.isVisible());
                if (outboundSubMenuPanel.isVisible()) {
                    outboundMajorMenuPanel.setBackground(HIGHLIGHT_COLOR);
                } else {
                    outboundMajorMenuPanel.setBackground(DEFAULT_MAJOR_MENU_BG);
                }
                p_west_right.revalidate();
                p_west_right.repaint();
            }
        });

        la_stock_icon_display.addMouseListener(new MouseAdapter() {
            private boolean isHighlighted = false;
            @Override
            public void mouseClicked(MouseEvent e) {
                isHighlighted = !isHighlighted;
                if (isHighlighted) {
                    stockMajorMenuPanel.setBackground(HIGHLIGHT_COLOR);
                } else {
                    stockMajorMenuPanel.setBackground(DEFAULT_MAJOR_MENU_BG);
                }
                p_west_right.revalidate();
                p_west_right.repaint();
            }
        });

        la_statistics_icon_display.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                statisticsSubMenuPanel.setVisible(!statisticsSubMenuPanel.isVisible());
                if (statisticsSubMenuPanel.isVisible()) {
                    statisticsMajorMenuPanel.setBackground(HIGHLIGHT_COLOR);
                } else {
                    statisticsMajorMenuPanel.setBackground(DEFAULT_MAJOR_MENU_BG);
                }
                p_west_right.revalidate();
                p_west_right.repaint();
            }
        });

        la_user_icon_display.addMouseListener(new MouseAdapter() {
            private boolean isHighlighted = false;
            @Override
            public void mouseClicked(MouseEvent e) {
                isHighlighted = !isHighlighted;
                if (isHighlighted) {
                    userMajorMenuPanel.setBackground(HIGHLIGHT_COLOR);
                } else {
                    userMajorMenuPanel.setBackground(DEFAULT_MAJOR_MENU_BG);
                }
                p_west_right.revalidate();
                p_west_right.repaint();
            }
        });
    }

    private JPanel createMajorMenuPanel(JLabel iconLabel, JLabel textLabel) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panel.setBackground(DEFAULT_MAJOR_MENU_BG);
        panel.setOpaque(true);

        iconLabel.setPreferredSize(new Dimension(100, 100));
        iconLabel.setMaximumSize(new Dimension(100, 100));
        iconLabel.setMinimumSize(new Dimension(100, 100));
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER); 
        iconLabel.setVerticalAlignment(SwingConstants.CENTER);
         
        panel.add(iconLabel);
        panel.add(textLabel);
        return panel;
    }

    private JPanel createSubMenuPanel(JLabel[] subLabels) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(background);
        panel.setBorder(new EmptyBorder(0, 40, 0, 0));

        for (JLabel subLabel : subLabels) {
            subLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(subLabel);
            panel.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        panel.setVisible(false);
        return panel;
    }
    
    public static void main(String[] args) {
        new PageLayout();
    }
}