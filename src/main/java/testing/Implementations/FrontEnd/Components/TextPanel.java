package testing.Implementations.FrontEnd.Components;

import testing.Constants;
import testing.Interfaces.Compositor;
import testing.Interfaces.Holder;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Компонент, описывающий панель, содержащую текстовый компонент.
 * Совместим с компонентами Compositor в составе компоновщика.
 */
public class TextPanel extends JPanel implements Compositor {

    /** Содержит дочерние компоненты. */
    private Set<Compositor> childs = new HashSet<>(1);

    /**
     * Конструктор панели текста.
     * @param holder модель приложения
     */
    public TextPanel(Holder holder) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Constants.TEXT_PANEL_W, Constants.TEXT_PANEL_H);
    }

    @Override
    public JComponent getComponent() {
        return this;
    }

    /**
     * Компонент промежуточный, не содержит ничего особенного -
     * рекурсивное обновление дочерних компонентов.
     */
    @Override
    public void refresh() {
        for (Compositor c : childs) c.refresh();
    }

    @Override
    public Collection<Compositor> getChilds() {
        return this.childs;
    }

    /**
     * Добавляет дочерний компонент на панель и в множество дочерних компонентов.
     * @param compositor добавляемый компонент
     * @param constraints ограничения компоновки
     */
    @Override
    public void addComponent(Compositor compositor, Object constraints) {
        this.add(compositor.getComponent(), constraints);
        childs.add(compositor);
    }

    @Override
    public void removeComponent(Compositor compositor) {
        childs.remove(compositor);
    }
}