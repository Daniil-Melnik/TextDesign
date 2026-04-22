package testing.Implementations.BackEnd;

import testing.Interfaces.Holder;
import testing.Interfaces.Observer;
import testing.Interfaces.Publisher;

import java.util.HashSet;
import java.util.Set;

/**
 * Реализация менеджера обновлений субъект - наблюдатель по паттерну Наблюдатель.
 */
public class HolderChangePublisher implements Publisher {

    /** Множество подписанных наблюдателей. */
    private Set<Observer> observers = new HashSet<>(2);

    /**
     * Сигнализация на обновление для всех подписчиков.
     * @param holder модель, содержащая обновлённые данные
     */
    @Override
    public void notify(Holder holder) {
        for (Observer o : observers) o.update(holder);
    }

    /**
     * Подписка наблюдателя на обновления.
     * @param observer подписываемый наблюдатель
     */
    @Override
    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    /**
     * Отписка наблюдателя от обновлений.
     * @param observer отписываемый наблюдатель
     */
    @Override
    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }
}