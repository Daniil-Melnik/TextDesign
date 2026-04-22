package testing;

import testing.Implementations.FrontEnd.MainFrame;
import testing.Implementations.BackEnd.HolderChangePublisher;
import testing.Implementations.BackEnd.SimpleController;
import testing.Implementations.BackEnd.SimpleHolder;
import testing.Interfaces.Controller;
import testing.Interfaces.Holder;
import testing.Interfaces.Publisher;

import java.awt.*;

/**
 * Главный класс приложения.
 * Собирает все компоненты воедино в соответствии с паттерном MVC.
 */
public class Main {

    /**
     * Точка входа в приложение.
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String... args) {
        EventQueue.invokeLater(() -> {
            Publisher publisher = new HolderChangePublisher();
            Holder holder = new SimpleHolder(publisher);
            Controller controller = new SimpleController(holder);
            MainFrame mainFrame = new MainFrame(holder, controller);

            publisher.subscribe(mainFrame);
            mainFrame.setVisible(true);
        });
    }
}