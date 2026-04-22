package testing.Interfaces;

import java.awt.*;

/**
 * Интерфейс, описывающий модель приложения.
 * Хранит настройки отображения текста и размерные характеристики.
 */
public interface Holder {

    void setFontName(String fontName);
    void setColor(Color color);
    void setSize(int size);
    void setText(String text);
    void setOutline(int outline);
    void setTextWidth(int textWidth);
    void setTextHeight(int textHeight);

    String getFontName();
    Color getColor();
    int getSize();
    String getText();
    int getOutline();
    int getTextWidth();
    int getTextHeight();
}