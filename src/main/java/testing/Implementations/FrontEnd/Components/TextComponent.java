package testing.Implementations.FrontEnd.Components;

import testing.Constants;
import testing.Interfaces.Compositor;
import testing.Interfaces.Holder;

import javax.swing.*;
import java.awt.*;

/**
 * Компонент отрисовки текста. Совместим с компонентами Compositor в составе компоновщика.
 * Используется как источник данных - Holder.
 */
public class TextComponent extends JComponent implements Compositor {

    /** Источник данных - Holder. */
    private Holder holder;

    /**
     * Конструктор текстового компонента.
     * @param holder модель приложения
     */
    public TextComponent(Holder holder) {
        this.holder = holder;
    }

    /**
     * Отрисовка компонента.
     * @param g графический контекст
     */
    @Override
    protected void paintComponent(Graphics g) {
        g.setFont(new Font(holder.getFontName(), holder.getOutline(), holder.getSize()));
        g.setColor(holder.getColor());

        int strWidth = holder.getTextWidth();
        int strHeight = holder.getTextHeight();

        g.drawString(holder.getText(),
                (Constants.TEXT_PANEL_W - strWidth) / 2,
                (Constants.TEXT_PANEL_H - strHeight) / 2);
    }

    @Override
    public JComponent getComponent() {
        return this;
    }

    /**
     * Компонент листовой - перерисовка текущего компонента.
     */
    @Override
    public void refresh() {
        this.repaint();
    }
}