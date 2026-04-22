package testing.Interfaces;

import javax.swing.*;
import java.util.Collection;
import java.util.List;

/**
 * Интерфейс для описания компонента дерева графических панелей.
 * Реализует паттерн проектирования Компоновщик.
 */
public interface Compositor {

    /**
     * Получение Swing-компонента.
     * @return Swing-компонент
     */
    JComponent getComponent();

    /**
     * Имя экземпляра.
     * @return имя компонента
     */
    String getName();

    /**
     * Обновление графического компонента.
     */
    void refresh();

    /**
     * Возвращает список дочерних узлов.
     * @return коллекция дочерних компонентов
     */
    default Collection<Compositor> getChilds() {
        return List.of();
    }

    /**
     * Добавляет дочерний компонент с указанными ограничениями.
     * @param compositor добавляемый компонент
     * @param constraints ограничения расположения
     */
    default void addComponent(Compositor compositor, Object constraints) {}

    /**
     * Удаляет дочерний компонент.
     * @param compositor удаляемый компонент
     */
    default void removeComponent(Compositor compositor) {}
}