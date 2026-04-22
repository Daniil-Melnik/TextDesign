package testing.Implementations.BackEnd;

import lombok.AllArgsConstructor;
import testing.Constants;
import testing.Interfaces.Controller;
import testing.Interfaces.Holder;

import java.awt.*;

/**
 * Реализация простого контроллера.
 * Обрабатывает запросы на изменение параметров текста и проверяет,
 * помещается ли текст в отведённую область.
 */
@AllArgsConstructor
public class SimpleController implements Controller {

    /** Хранит экземпляр модели. */
    private final Holder holder;

    /**
     * Обновляет название гарнитуры шрифта.
     * @param fontName новое название гарнитуры
     */
    @Override
    public void updFontName(String fontName) {
        Font newFont = new Font(fontName, holder.getOutline(), holder.getSize());
        if (isStringSuitable(holder.getText(), newFont)) {
            updateTextDimentions(newFont, holder.getText());
            holder.setFontName(fontName);
        }
    }

    /**
     * Обновляет цвет текста.
     * @param color новый цвет
     */
    @Override
    public void updColor(Color color) {
        holder.setColor(color);
    }

    /**
     * Обновляет размер шрифта.
     * @param size новый размер
     */
    @Override
    public void updSize(int size) {
        Font newFont = new Font(holder.getFontName(), holder.getOutline(), size);
        if (isStringSuitable(holder.getText(), newFont)) {
            updateTextDimentions(newFont, holder.getText());
            holder.setSize(size);
        }
    }

    /**
     * Обновляет текст.
     * @param text новый текст
     */
    @Override
    public void updText(String text) {
        Font newFont = new Font(holder.getFontName(), holder.getOutline(), holder.getSize());
        if (isStringSuitable(text, newFont)) {
            updateTextDimentions(newFont, text);
            holder.setText(text);
        }
    }

    /**
     * Обновляет начертание шрифта.
     * @param outline новое начертание
     */
    @Override
    public void updOutline(int outline) {
        Font newFont = new Font(holder.getFontName(), outline, holder.getSize());
        if (isStringSuitable(holder.getText(), newFont)) {
            updateTextDimentions(newFont, holder.getText());
            holder.setOutline(outline);
        }
    }

    /**
     * Проверяет, помещается ли строка при заданных настройках шрифта.
     * @param str проверяемая строка
     * @param font шрифт для проверки
     * @return true, если ширина строки меньше максимальной
     */
    private boolean isStringSuitable(String str, Font font) {
        return getTextWidth(str, font) < Constants.MAX_TEXT_W;
    }

    /**
     * Получает ширину строки средствами Toolkit.
     * @param str строка для измерения
     * @param font шрифт строки
     * @return ширина строки в пикселях
     */
    private int getTextWidth(String str, Font font) {
        return Toolkit.getDefaultToolkit().getFontMetrics(font).stringWidth(str);
    }

    /**
     * Получает высоту текста средствами Toolkit.
     * @param font шрифт для измерения
     * @return высота текста в пикселях
     */
    private int getTextHeight(Font font) {
        return Toolkit.getDefaultToolkit().getFontMetrics(font).getHeight();
    }

    /**
     * Обновляет размерные характеристики текста при каждом изменении настроек.
     * @param font шрифт для пересчёта размеров
     * @param str строка для пересчёта ширины
     */
    private void updateTextDimentions(Font font, String str) {
        holder.setTextWidth(getTextWidth(str, font));
        holder.setTextHeight(getTextHeight(font));
    }
}