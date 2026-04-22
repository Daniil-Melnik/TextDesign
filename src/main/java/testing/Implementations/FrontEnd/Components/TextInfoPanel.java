package testing.Implementations.FrontEnd.Components;

import testing.Implementations.FrontEnd.GBC;
import testing.Interfaces.Compositor;
import testing.Interfaces.Holder;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Компонент, описывающий панель информации о тексте.
 * Совместим с другими компонентами Compositor в составе компоновщика.
 */
public class TextInfoPanel extends JPanel implements Compositor {

    /** Использует Holder как источник данных. */
    private Holder holder;

    private static final Font font12 = new Font("Arial", Font.BOLD, 12);

    /** Отображение метрик шрифта. */
    LinkedHashMap<String, JLabel> valuesMap = new LinkedHashMap<>(4);

    /** Отображение метрик строки. */
    LinkedHashMap<String, JLabel> metricsMap = new LinkedHashMap<>(2);

    /** Отображение лейблов для метрик. */
    Map<String, JLabel> labelsMap;

    /** Отображение лейблов для метрик строки. */
    Map<String, JLabel> labelsMetrMap;

    /** Блок инициализации для отображений пометок для метрик. */
    {
        labelsMap = Map.of(
                "fontName", new JLabel("Название:"),
                "fontOutline", new JLabel("Начертание:"),
                "fontSize", new JLabel("Размер"),
                "fontColor", new JLabel("Цвет:")
        );

        labelsMetrMap = Map.of(
                "strWidth", new JLabel("Ширина (пкс):"),
                "strHeight", new JLabel("Высота (пкс):")
        );
    }

    /**
     * Конструктор панели информации.
     * @param holder модель приложения
     */
    public TextInfoPanel(Holder holder) {
        this.holder = holder;

        setLayout(new GridLayout(1, 3));

        JPanel fontInfoPanel = new JPanel();
        JPanel fontMetricsPanel = new JPanel();

        fontInfoPanel.setLayout(new GridBagLayout());
        fontMetricsPanel.setLayout(new GridBagLayout());

        for (Map.Entry<String, String> e : getFontInfo().entrySet()) {
            valuesMap.put(e.getKey(), new JLabel(e.getValue()));
        }

        for (Map.Entry<String, String> e : getStringMetrics().entrySet()) {
            metricsMap.put(e.getKey(), new JLabel(e.getValue()));
        }

        int i = 0;
        for (String s : labelsMap.keySet()) {
            labelsMap.get(s).setFont(font12);
            valuesMap.get(s).setFont(font12);
            fontInfoPanel.add(labelsMap.get(s),
                    new GBC(0, i, 1, 1)
                            .setWeight(0, 0.25)
                            .setInsets(0, 0, 10, 0)
                            .setAnchor(GBC.EAST));
            fontInfoPanel.add(valuesMap.get(s),
                    new GBC(1, i, 1, 1)
                            .setWeight(1, 0.25)
                            .setInsets(0, 0, 0, 5)
                            .setAnchor(GBC.EAST));
            i++;
        }

        i = 0;

        for (String s : metricsMap.keySet()) {
            fontMetricsPanel.add(labelsMetrMap.get(s),
                    new GBC(0, i, 1, 1)
                            .setWeight(0, 0.25)
                            .setAnchor(GBC.EAST)
                            .setInsets(0, 0, 10, 0));
            fontMetricsPanel.add(metricsMap.get(s),
                    new GBC(1, i, 1, 1)
                            .setWeight(1, 0.25)
                            .setAnchor(GBC.EAST)
                            .setInsets(0, 0, 0, 10));
            i++;
        }

        fontMetricsPanel.setBorder(new EtchedBorder());
        fontInfoPanel.setBorder(new EtchedBorder());

        add(fontInfoPanel);
        add(new JPanel());
        add(fontMetricsPanel);
    }

    /**
     * Обновление панели - обновление значений в соответствующих отображениях.
     */
    public void updatePanel() {
        for (Map.Entry<String, String> e : getFontInfo().entrySet()) {
            valuesMap.get(e.getKey()).setText(e.getValue());
        }

        for (Map.Entry<String, String> e : getStringMetrics().entrySet()) {
            metricsMap.get(e.getKey()).setText(e.getValue());
        }
        repaint();
    }

    /**
     * Формирует информацию о настройках шрифта для панели метрик.
     * @return отображение с информацией о шрифте
     */
    public LinkedHashMap<String, String> getFontInfo() {
        LinkedHashMap<String, String> info = new LinkedHashMap<>(4);
        info.put("fontName", holder.getFontName());
        info.put("fontOutline", holder.getOutline() == Font.BOLD ? "Bold"
                : holder.getOutline() == Font.PLAIN ? "Plain"
                : holder.getOutline() == Font.ITALIC ? "Italic" : "ERROR");
        info.put("fontSize", Integer.valueOf(holder.getSize()).toString());
        info.put("fontColor", String.format("%d, %d, %d",
                holder.getColor().getRed(),
                holder.getColor().getGreen(),
                holder.getColor().getBlue()));
        return info;
    }

    /**
     * Формирует метрики строки для панели метрик.
     * @return отображение с метриками строки
     */
    public LinkedHashMap<String, String> getStringMetrics() {
        LinkedHashMap<String, String> metrics = new LinkedHashMap<>(2);
        metrics.put("strWidth", Integer.toString(holder.getTextWidth()));
        metrics.put("strHeight", Integer.toString(holder.getTextHeight()));
        return metrics;
    }

    @Override
    public JComponent getComponent() {
        return this;
    }

    /**
     * Компонент листовой - обновление только панели.
     */
    @Override
    public void refresh() {
        this.updatePanel();
    }
}