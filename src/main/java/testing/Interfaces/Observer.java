package testing.Interfaces;

/**
 * Интерфейс, описывающий наблюдателя изменений в модели.
 * Реализует паттерн проектирования Наблюдатель.
 */
public interface Observer {

    /**
     * Обновляет наблюдателя при изменении модели.
     * @param holder изменённая модель
     */
    void update(Holder holder);
}