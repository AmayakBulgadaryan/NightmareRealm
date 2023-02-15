import javax.swing.*;

/**
 * Created by Амаяк
 * Класс, отвечающий за кнопку на игровом дисплее
 */
public class MyButton extends JButton {

    /**
     * Индекс ячейки (кнопки) на игровом пространстве
     */
    private int index;

    /**
     * Булево поле, обозначающее, является ли кнопка фишкой какого-либо
     *  цвета
     */
    private boolean isChips;

    /**
     *  Булево поле, обозначающее, является ли кнопка пустой ячейкой,
     *  куда можно будет переместить фишку
     */
    private boolean isEmptyCell;

    /**
     *  Булево поле, обозначающее, является ли кнопка блоком,
     *  куда нельзя будет переместить фишку
     */
    private boolean isBlockingCell;

    /**
     *  Булевы поля, обозначающие, является ли кнопка фишкой определенного,
     *  цвета
     */
    private boolean isSnake1;
    private boolean isSnake2;
    private boolean isSnake3;

    /**
     *  Булево поле, обозначающее, является ли кнопка текущей
     *  по нажатию мыши
     */
    private boolean isCurrent;

    /**
     *  Булево поле, обозначающее, является ли кнопка предыдущей
     *  по нажатию мыши
     */
    private boolean isPrevious;

    public MyButton(String name){
        super(name);
    }

    public MyButton(){}

    public boolean isCurrent(){
        return isCurrent;
    }

    public int getIndex(){
        return index;
    }

    public boolean isPrevious(){
        return isPrevious;
    }

    public boolean isSnake1(){
        return isSnake1;
    }

    public boolean isSnake2(){
        return isSnake2;
    }

    public boolean isSnake3(){
        return isSnake3;
    }

    public boolean isChips(){
        return isChips;
    }

    public boolean isEmptyCell(){
        return isEmptyCell;
    }

    public boolean isBlockingCell(){
        return isBlockingCell;
    }

    public void setIndex(int index){
        this.index = index;
    }

    public void setIsCurrent(boolean isCurrent){
        this.isCurrent = isCurrent;
    }

    public void setIsPrevious(boolean isPrevious){
        this.isPrevious = isPrevious;
    }

    public void setIsSnake1(boolean isSnake1){
        this.isSnake1 = isSnake1;
        if (isSnake1) {
            this.isChips = true;
            this.isSnake2 = false;
            this.isSnake3 = false;
            this.isEmptyCell = false;
        }
    }

    public void setIsSnake2(boolean isSnake2){
        this.isSnake2 = isSnake2;
        if (isSnake2) {
            this.isChips = true;
            this.isSnake1 = false;
            this.isSnake3 = false;
            this.isEmptyCell = false;
        }
    }

    public void setIsSnake3(boolean isSnake3){
        this.isSnake3 = isSnake3;
        if (isSnake3) {
            this.isChips = true;
            this.isSnake1 = false;
            this.isSnake2 = false;
            this.isEmptyCell = false;
        }
    }

    public void setIsChips(boolean isChips){
        this.isChips = isChips;
        if (isChips) {
            this.isEmptyCell = false;
        }

    }

    public void setIsEmptyCell(boolean isEmptyCell){
        this.isEmptyCell = isEmptyCell;
        if (isEmptyCell) {
            this.isChips = false;
            this.isSnake1 = false;
            this.isSnake2 = false;
            this.isSnake3 = false;
        }
    }

    public void setIsBlockingCell(boolean isBlockingCell){
        this.isBlockingCell = isBlockingCell;
    }

}
