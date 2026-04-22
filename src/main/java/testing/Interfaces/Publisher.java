package testing.Interfaces;

/**
 * Менеджер обновлений для субъекта наблюдений.
 * Реализует паттерн проектирования Наблюдатель.
 */
public interface Publisher {

    /** Оповещает всех подписчиков об изменении модели. */
    void notify(Holder holder);

    /** Добавляет подписчика на обновления. */
    void subscribe(Observer observer);

    /** Удаляет подписчика из списка уведомлений. */
    void unsubscribe(Observer observer);
}