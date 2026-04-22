package testing.Implementations.FrontEnd.Components;

import testing.Interfaces.Compositor;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Компонент главной панели. Совместим в работе с компонентами Compositor в составе компоновщика.
 */
public class MainPanel extends JPanel implements Compositor {

    /**
     * Множество дочерних элементов. Панель содержит дочерние компоненты.
     */
    private Set<Compositor> childs = new HashSet<>(2);

    /**
     * Конструктор с объявлением менеджера компоновки.
     */
    public MainPanel() {
        setLayout(new BorderLayout());
    }

    @Override
    public JComponent getComponent() {
        return this;
    }

    /**
     * Сама панель не содержит ничего кроме дочерних компонентов,
     * поэтому обновляются только дочерние элементы рекурсивно.
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
     * Добавляет дочерний компонент на панель и в множество дочерних узлов.
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