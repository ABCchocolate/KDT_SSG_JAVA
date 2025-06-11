package com.sinse.shopadmin.config.view;

import java.awt.Dimension;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.sinse.shopadmin.AppMain;
import com.sinse.shopadmin.common.config.Config;
import com.sinse.shopadmin.common.view.Page;
import com.sinse.shopadmin.product.model.Color;
import com.sinse.shopadmin.product.model.Size;
import com.sinse.shopadmin.product.repository.ColorDAO;
import com.sinse.shopadmin.product.repository.SizeDAO;

public class ConfigPage extends Page {
    
    JPanel p_color;
    JLabel la_color;
    JTextField t_color;
    JButton bt_color;
    JList<Color> list_color;
    JScrollPane scroll_color;
    
    JPanel p_size;
    JLabel la_size;
    JTextField t_size;
    JButton bt_size;
    JList<Size> list_size;
    JScrollPane scroll_size;
    
    ColorDAO colorDAO;
    SizeDAO sizeDAO;
    
    public ConfigPage(AppMain appMain) {
        super(appMain);
        setBackground(java.awt.Color.GREEN);
        
        // DAO 초기화
        colorDAO = new ColorDAO();
        sizeDAO = new SizeDAO();

        // 컴포넌트 생성
        p_color = new JPanel();
        la_color = new JLabel("색상 등록");
        t_color = new JTextField();
        bt_color = new JButton("등록");
        list_color = new JList<>();
        scroll_color = new JScrollPane(list_color);
        
        p_size = new JPanel();
        la_size = new JLabel("사이즈 등록");
        t_size = new JTextField();
        bt_size = new JButton("등록");
        list_size = new JList<>();
        scroll_size = new JScrollPane(list_size);
        
        // 스타일 설정
        Dimension d = new Dimension(150, 30);
        la_color.setPreferredSize(d);
        t_color.setPreferredSize(d);
        la_size.setPreferredSize(d);
        t_size.setPreferredSize(d);
        
        scroll_color.setPreferredSize(new Dimension(200, 300));
        scroll_size.setPreferredSize(new Dimension(200, 300));
        
        p_color.setPreferredSize(new Dimension(Config.ADMINMAIN_WIDTH - 300, 350));
        p_size.setPreferredSize(new Dimension(Config.ADMINMAIN_WIDTH - 300, 350));
        
        p_color.setBorder(new TitledBorder("색상 정보 등록"));
        p_size.setBorder(new TitledBorder("사이즈 정보 등록"));
        
        // 조립
        p_color.add(la_color);
        p_color.add(t_color);
        p_color.add(bt_color);
        p_color.add(scroll_color);
        
        p_size.add(la_size);
        p_size.add(t_size);
        p_size.add(bt_size);
        p_size.add(scroll_size);
        
        add(p_color);
        add(p_size);
        
        // 초기 데이터 로딩
        loadColorList();
        loadSizeList();
        
        // 색상 등록 이벤트
        bt_color.addActionListener(e -> {
            registColor();
            loadColorList();
        });

        // 사이즈 등록 이벤트
        bt_size.addActionListener(e -> {
            registSize();
            loadSizeList();
        });
    }

    public void registColor() {
        String colorName = t_color.getText().trim();
        if (colorName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "색상명을 입력해주세요.");
            return;
        }

        Color color = new Color();
        color.setColor_name(colorName);
        int result = colorDAO.insert(color);
        if (result > 0) {
            JOptionPane.showMessageDialog(this, "색상 등록 성공");
            t_color.setText(""); // 입력창 초기화
        } else {
            JOptionPane.showMessageDialog(this, "색상 등록 실패");
        }
    }

    public void loadColorList() {
        List<Color> colorList = colorDAO.selectAll();
        Vector<Color> vec = new Vector<>(colorList);
        list_color.setListData(vec);
    }

    public void registSize() {
        String sizeName = t_size.getText().trim();
        if (sizeName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "사이즈명을 입력해주세요.");
            return;
        }

        Size size = new Size();
        size.setSize_name(sizeName);
        int result = sizeDAO.insert(size);
        if (result > 0) {
            JOptionPane.showMessageDialog(this, "사이즈 등록 성공");
            t_size.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "사이즈 등록 실패");
        }
    }

    public void loadSizeList() {
        List<Size> sizeList = sizeDAO.selectAll();
        Vector<Size> vec = new Vector<>(sizeList);
        list_size.setListData(vec);
    }
}
