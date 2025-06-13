package main.view;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

public class HoverEvent {
	JLabel label;
	Color hoverColor;
	Color defaultColor;
	
	public HoverEvent(JLabel label, Color hoverColor, Color defaultColor) {
		this.label = label;
		this.hoverColor = hoverColor;
		this.defaultColor = defaultColor;
	}
	
	public void addHoverEffect(JLabel label, Color hoverColor, Color defaultColor) {
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// 마우스가 라벨 영역에 진입했을 때
				label.setBackground(hoverColor);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// 마우스가 라벨 영역을 이탈했을 때
				label.setBackground(defaultColor);
			}
		});
	}
}
