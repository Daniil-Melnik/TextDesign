package testing.Implementations.BackEnd;

import lombok.Getter;
import lombok.Setter;
import testing.Interfaces.Holder;
import testing.Interfaces.Publisher;

import java.awt.*;

/**
 * Реализация простой модели.
 * Модель является субъектом наблюдения в паттерне Наблюдатель.
 */
@Getter
public class SimpleHolder implements Holder {

    /** Название гарнитуры шрифта. */
    private String fontName = "Arial";

    /** Цвет текста. */
    private Color color = Color.BLACK;

    /** Размер шрифта. */
    private int size = 24;

    /** Контент-текст. */
    private String text = "Some string in Spring!";

    /** Начертание текста. */
    private int outline = Font.PLAIN;

    /** Менеджер обновлений наблюдателей. */
    private final Publisher publisher;

    /** Ширина строки-текста. */
    @Setter
    private int textWidth = 241;

    /** Высота строки-текста. */
    @Setter
    private int textHeight = 28;

    /**
     * Конструктор модели.
     * @param publisher менеджер обновлений наблюдателей
     */
    public SimpleHolder(Publisher publisher) {
        this.publisher = publisher;
    }

    /**
     * Устанавливает цвет текста и оповещает наблюдателей.
     * @param color новый цвет
     */
    public void setColor(Color color) {
        this.color = color;
        publisher.notify(this);
    }

    /**
     * Устанавливает текст и оповещает наблюдателей.
     * @param text новый текст
     */
    public void setText(String text) {
        this.text = text;
        publisher.notify(this);
    }

    /**
     * Устанавливает название гарнитуры и оповещает наблюдателей.
     * @param fontName новое название гарнитуры
     */
    public void setFontName(String fontName) {
        this.fontName = fontName;
        publisher.notify(this);
    }

    /**
     * Устанавливает размер шрифта и оповещает наблюдателей.
     * @param size новый размер
     */
    public void setSize(int size) {
        this.size = size;
        publisher.notify(this);
    }

    /**
     * Устанавливает начертание шрифта и оповещает наблюдателей.
     * @param outline новое начертание
     */
    public void setOutline(int outline) {
        this.outline = outline;
        publisher.notify(this);
    }
}