package testing.Implementations.FrontEnd;

import java.awt.*;

/**
 * Класс-шаблон для описания расположения компонента в сеточно-контейнерной компоновке (GridBagLayout).
 */
public class GBC extends GridBagConstraints {

    /**
     * Конструктор по координатам левого верхнего угла.
     * @param gx столбец ЛВ-угла
     * @param gy строка
     */
    public GBC(int gx, int gy) {
        this.gridx = gx;
        this.gridy = gy;
    }

    /**
     * Конструктор с шириной и высотой.
     * @param gx столбец ЛВ-угла
     * @param gy строка
     * @param w ширина
     * @param h высота
     */
    public GBC(int gx, int gy, int w, int h) {
        this.gridx = gx;
        this.gridy = gy;
        this.gridwidth = w;
        this.gridheight = h;
    }

    /**
     * Устанавливает привязку компонента, если вокруг него в ячейке есть свободное место.
     * @param a привязка (север, запад, восток, юг и промежуточные)
     * @return текущий объект для цепочки вызовов
     */
    public GBC setAnchor(int a) {
        this.anchor = a;
        return this;
    }

    /**
     * Устанавливает, растягиваться ли компоненту на пространство в ячейке.
     * @param f параметр заполнения
     * @return текущий объект для цепочки вызовов
     */
    public GBC setFill(int f) {
        this.fill = f;
        return this;
    }

    /**
     * Устанавливает процент занимаемого компонентом свободного места в ячейке.
     * @param wx вес по горизонтали
     * @param wy вес по вертикали
     * @return текущий объект для цепочки вызовов
     */
    public GBC setWeight(double wx, double wy) {
        this.weightx = wx;
        this.weighty = wy;
        return this;
    }

    /**
     * Устанавливает внутренние отступы от границ ячейки до компонента (padding).
     * @param dist отступ со всех сторон
     * @return текущий объект для цепочки вызовов
     */
    public GBC setInsets(int dist) {
        this.insets = new Insets(dist, dist, dist, dist);
        return this;
    }

    /**
     * Устанавливает внутренние отступы для каждой стороны в отдельности.
     * @param t отступ сверху
     * @param b отступ снизу
     * @param l отступ слева
     * @param r отступ справа
     * @return текущий объект для цепочки вызовов
     */
    public GBC setInsets(int t, int b, int l, int r) {
        this.insets = new Insets(t, l, b, r);
        return this;
    }

    /**
     * Устанавливает дополнительный минимальный размер ячейки (margin).
     * @param ix дополнительная ширина
     * @param iy дополнительная высота
     * @return текущий объект для цепочки вызовов
     */
    public GBC setIpad(int ix, int iy) {
        this.ipadx = ix;
        this.ipady = iy;
        return this;
    }
}