package testing.Interfaces;

import java.awt.*;

/**
 * Интерфейс стандартного контроллера приложения.
 * Обеспечивает изменение представления из представления в паттерне MVC.
 */
public interface Controller {

    /** Обновляет название гарнитуры шрифта. */
    void updFontName(String fontName);

    /** Обновляет цвет текста. */
    void updColor(Color color);

    /** Обновляет размер шрифта. */
    void updSize(int size);

    /** Обновляет начертание шрифта. */
    void updOutline(int outline);

    /** Обновляет контент-текст. */
    void updText(String text);
}