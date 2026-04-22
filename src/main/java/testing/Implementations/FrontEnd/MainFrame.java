package testing.Implementations.FrontEnd;

import testing.Constants;
import testing.Implementations.FrontEnd.Components.*;
import testing.Implementations.FrontEnd.Components.TextComponent;
import testing.Interfaces.Compositor;
import testing.Interfaces.Controller;
import testing.Interfaces.Holder;
import testing.Interfaces.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.*;

/**
 * Главное окно приложения.
 * Реализует паттерн Наблюдатель для отслеживания изменений в модели.
 */
public class MainFrame extends JFrame implements Observer {

    private static Controller controller;
    private static Holder holder;
    private static Compositor rootCompositor;

    /**
     * Конструктор главного окна.
     * @param h модель приложения
     * @param c контроллер приложения
     */
    public MainFrame(Holder h, Controller c) {
        holder = h;
        controller = c;
        rootCompositor = composeMainFrame();

        setSize(Constants.FRAME_W, Constants.FRAME_H);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setTitle("Просмотр текста");
        setLayout(new BorderLayout());
        setIconImage(new ImageIcon(
                Objects.requireNonNull(this.getClass().getResource("/text.png"))
        ).getImage());

        add(rootCompositor.getComponent(), BorderLayout.CENTER);
        setJMenuBar(new MainMenuBar());
    }

    @Override
    public void update(Holder holder) {
        rootCompositor.refresh();
    }

    /**
     * Метод для сборки графического интерфейса через компоновщик.
     * @return корневой компонент для размещения в окне
     */
    private Compositor composeMainFrame() {
        Compositor rootCompositor = new MainPanel();
        Compositor textPanel = new TextPanel(holder);
        Compositor bottomPanel = new BottomPanel(holder, controller);
        Compositor textInfoPanel = new TextInfoPanel(holder);
        Compositor textCompositor = new TextComponent(holder);

        rootCompositor.addComponent(textPanel, BorderLayout.CENTER);
        rootCompositor.addComponent(bottomPanel, BorderLayout.SOUTH);
        textPanel.addComponent(textCompositor, BorderLayout.CENTER);
        bottomPanel.addComponent(textInfoPanel,
                new GBC(0, 1, 16, 2)
                        .setWeight(1, 1)
                        .setFill(1));

        return rootCompositor;
    }

    /**
     * Панель меню приложения.
     */
    private static class MainMenuBar extends JMenuBar {
        private static final boolean PALETTE = false;
        private static final boolean ADDITIVE = true;

        public MainMenuBar() {
            JMenu fileMenu = new JMenu("Файл (F)");
            JMenu fontMenu = new JMenu("Шрифт (S)");
            JMenu colorMenu = new JMenu("Цвет (C)");
            JMenu outlineMenu = new JMenu("Начертание (O)");

            outlineMenu.setMnemonic(KeyEvent.VK_O);
            fileMenu.setMnemonic(KeyEvent.VK_F);
            fontMenu.setMnemonic(KeyEvent.VK_S);
            colorMenu.setMnemonic(KeyEvent.VK_C);

            JMenuItem exitItem = new JMenuItem("Выход (E)", 'E');
            JMenuItem aboutItem = new JMenuItem("О программе (A)", 'A');

            exitItem.addActionListener((e) -> System.exit(0));

            aboutItem.addActionListener((e) -> {
                JOptionPane.showMessageDialog(null,
                        "Программа подбора дизайна строки по гарнитуре, размеру, начертанию и цвету.",
                        "О программе",
                        JOptionPane.INFORMATION_MESSAGE);
            });

            fileMenu.add(exitItem);
            fileMenu.add(aboutItem);

            JMenuItem shriftItem = new JMenuItem("Гарнитура (G)", 'G');

            ButtonGroup radioGroup = new ButtonGroup();
            JRadioButtonMenuItem outlineItemPlain = new JRadioButtonMenuItem(new OutlineAction("Plain"));
            JRadioButtonMenuItem outlineItemBold = new JRadioButtonMenuItem(new OutlineAction("Bold"));
            JRadioButtonMenuItem outlineItemItalic = new JRadioButtonMenuItem(new OutlineAction("Italic"));
            radioGroup.add(outlineItemPlain);
            radioGroup.add(outlineItemBold);
            radioGroup.add(outlineItemItalic);

            outlineMenu.add(outlineItemPlain);
            outlineMenu.add(outlineItemBold);
            outlineMenu.add(outlineItemItalic);

            shriftItem.addActionListener((e) -> {
                try {
                    DialogWindows.TextAdder tA = DialogWindows.getTextAddedDialog(null, 0);
                    if (tA.showDialog()) {
                        controller.updFontName(tA.getText());
                    }
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            });

            JMenuItem colorAddsItem = new JMenuItem("Наборный (N)", 'N');
            JMenuItem colorPaletteItem = new JMenuItem("Плитка (P)", 'P');

            colorAddsItem.addActionListener((e) -> {
                DialogWindows.TextColorChooser tC = DialogWindows.getTextColorChooser(null, ADDITIVE, holder.getColor());
                if (tC.showDialog()) {
                    controller.updColor(tC.getColor());
                }
            });

            colorPaletteItem.addActionListener((e) -> {
                DialogWindows.TextColorChooser tC = DialogWindows.getTextColorChooser(null, PALETTE, holder.getColor());
                if (tC.showDialog()) {
                    controller.updColor(tC.getColor());
                }
            });

            JMenuItem textSizeItem = new JMenuItem("Размер (S)", 'S');
            textSizeItem.addActionListener((e) -> {
                DialogWindows.TextSizeChooser tSC = DialogWindows.getTextSizeChooser(null, holder.getSize() - 1);
                if (tSC.showDialog()) {
                    controller.updSize(tSC.getSizeFromCombo());
                }
            });

            colorMenu.add(colorAddsItem);
            colorMenu.add(colorPaletteItem);

            fontMenu.add(shriftItem);
            fontMenu.add(outlineMenu);
            fontMenu.add(textSizeItem);

            add(fileMenu);
            add(fontMenu);
            add(colorMenu);
        }
    }

    /**
     * Действие для выбора начертания шрифта.
     */
    private static class OutlineAction extends AbstractAction {
        HashMap<String, Integer> outlineMap = new HashMap<>(3);

        public OutlineAction(String name) {
            putValue(Action.NAME, name);
            outlineMap.put("Plain", Font.PLAIN);
            outlineMap.put("Bold", Font.BOLD);
            outlineMap.put("Italic", Font.ITALIC);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = this.getValue(Action.NAME).toString();
            int outline = outlineMap.get(name);
            controller.updOutline(outline);
        }
    }
}