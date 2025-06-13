package main.view; // PageLayout과 같은 패키지에 둡니다.

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class RoundedPanel extends JPanel {

    private int cornerRadius = 30; // 기본 모서리 둥글기 값

    public RoundedPanel(int radius) {
        super();
        this.cornerRadius = radius;
        setOpaque(false); // JPanel의 배경을 투명하게 설정 (이 부분이 중요!)
    }

    public RoundedPanel() {
        super();
        setOpaque(false); // 기본 생성자
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // 이전에 그려진 것들을 지웁니다.

        Graphics2D g2 = (Graphics2D) g.create(); // Graphics 객체를 복사하여 사용
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // 앤티앨리어싱 활성화

        // 이 RoundedPanel의 현재 배경색으로 둥근 사각형을 채웁니다.
        // setBackground()로 설정한 색상이 여기에 사용됩니다.
        g2.setColor(getBackground()); 
        
        // 둥근 사각형 그리기 (0,0부터 시작하여 패널 전체 크기)
        // 너비와 높이에서 1을 빼는 이유는 그리기 API가 픽셀을 채우는 방식 때문입니다.
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);

        g2.dispose(); // Graphics 객체 해제
    }

    // 선택 사항: 테두리도 둥글게 그리고 싶다면 paintBorder를 오버라이드합니다.
    // 하지만 JPanel의 setBorder()를 통해 설정되는 기본 테두리는 이 메서드에 의해 그려집니다.
    // 만약 테두리도 둥글게 하고 싶고, setBorder()가 아닌 paintComponent에서 직접 테두리를 그리는 방식을 선택한다면
    // g2.drawRoundRect(...) 코드를 paintComponent 안에 추가하거나, Custom Border를 만드는 것이 좋습니다.
    // 여기서는 기본 JPanel의 border를 사용하도록 super.paintBorder(g)만 호출합니다.
    @Override
    protected void paintBorder(Graphics g) {
        // super.paintBorder(g); // 기본 테두리를 그립니다 (사각형 테두리).
                              // 만약 둥근 테두리를 원한다면 이 줄을 주석 처리하고
                              // g2.drawRoundRect(...)로 직접 그려야 합니다.
                              // 또는 RoundedCornerBorder 클래스를 이 패널에 setBorder() 할 수 있습니다.
        // 여기서는 깔끔하게 배경만 둥글게 하고 기본 테두리는 그리지 않거나
        // 다른 Custom Border를 적용하는 것을 상정합니다.
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        repaint(); // 변경사항을 즉시 반영합니다.
    }
}