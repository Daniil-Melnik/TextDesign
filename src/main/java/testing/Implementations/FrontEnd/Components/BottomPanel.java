package testing.Implementations.FrontEnd.Components;

import testing.Constants;
import testing.Implementations.FrontEnd.GBC;
import testing.Interfaces.Compositor;
import testing.Interfaces.Controller;
import testing.Interfaces.Holder;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Компонент интерфейса - нижняя панель с полем для ввода текста.
 * Возможно использование в компоновке с другими компонентами в составе компоновщика.
 */
public class BottomPanel extends JPanel implements Compositor {

    private JTextField inputTextField;
    private Holder holder;

    /**
     * Множество дочерних компонентов. Панель может содержать другие компоненты Compositor.
     */
    private Set<Compositor> childs = new HashSet<>(1);

    /**
     * Конструктор нижней панели.
     * @param holder модель приложения
     * @param controller контроллер приложения
     */
    public BottomPanel(Holder holder, Controller controller) {
        this.holder = holder;

        setBorder(new EtchedBorder());
        setLayout(new GridBagLayout());

        inputTextField = new JTextField(new LimitDocument(100, controller), holder.getText(), 100);
        inputTextField.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel textLabel = new JLabel("Строка:");
        textLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        add(textLabel, new GBC(0, 0, 2, 1).setWeight(0, 0.1).setAnchor(GBC.EAST).setInsets(0, 0, 10, 0));
        add(inputTextField, new GBC(2, 0, 14, 1).setWeight(1, 0.1).setFill(1).setInsets(10));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Constants.TEXT_PANEL_W, Constants.FRAME_H - Constants.TEXT_PANEL_H);
    }

    @Override
    public JComponent getComponent() {
        return this;
    }

    @Override
    public Set<Compositor> getChilds() {
        return this.childs;
    }

    /**
     * Рекурсивное обновление. Обновляется фон поля для ввода.
     */
    @Override
    public void refresh() {
        inputTextField.setBackground(holder.getTextWidth() > Constants.MAX_TEXT_W - holder.getTextHeight() / 2
                ? Constants.PALE_RED : Color.WHITE);
        for (Compositor c : childs) c.refresh();
    }

    @Override
    public void removeComponent(Compositor compositor) {
        childs.remove(compositor);
    }

    /**
     * Добавляет дочерний компонент на панель и в список дочерних элементов.
     * @param compositor добавляемый компонент
     * @param constraints ограничения компоновки
     */
    @Override
    public void addComponent(Compositor compositor, Object constraints) {
        this.add(compositor.getComponent(), constraints);
        childs.add(compositor);
    }

    /**
     * Документ с ограничением длины строки.
     */
    private static class LimitDocument extends PlainDocument {
        private int limit;
        private Controller controller;

        public LimitDocument(int l, Controller controller) {
            super();
            this.controller = controller;
            limit = l;
            addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    try {
                        controller.updText(getText(0, getLength()));
                    } catch (BadLocationException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    try {
                        controller.updText(getText(0, getLength()));
                    } catch (BadLocationException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                }
            });
        }

        /**
         * Переопределение метода вставки строки с учётом ограничения длины.
         */
        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            if (str == null) return;
            if (getLength() + str.length() <= limit) {
                super.insertString(offs, str, a);
            }
        }
    }
}