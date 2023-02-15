import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;


/**
 * Created by Амаяк on 15.09.2021.
 * Класс, отвечающий за механику игры (Нажатия кнопок, перемещение фишек на
 * игровом поле)
 */
public class Game extends JFrame implements ActionListener {

    /**
     * Игровые поля
     */
    private MyButton[] buttons;

    private InterfaceDisplay interfaceDisplay;

    public Game() {
        super("Snakes");
        buttons = new MyButton[25];
        interfaceDisplay = new InterfaceDisplay();
        interfaceDisplay.display(this, buttons);
    }


    public static void main(String[] args) {
        Game game = new Game();
    }

    /**
     * Метод, осуществляющий замену изображений двух кнопок, что создает иллюзию
     * передвижения фишки на пустую ячейку
     * @param currentButton - текущая по нажатию мыши кнопка
     * @param previousButton - предыдущая по нажатию мыши кнопка
     */
    private void reposition(MyButton currentButton, MyButton previousButton) {
        BufferedImage[] images = interfaceDisplay.getResizedImages();
        if (previousButton.isSnake1()) {
            currentButton.setIcon(new ImageIcon(images[0]));
            previousButton.setIcon(new ImageIcon(images[3]));

            currentButton.setIsChips(true);
            currentButton.setIsSnake1(true);

            previousButton.setIsChips(false);
            previousButton.setIsSnake1(false);
            previousButton.setIsEmptyCell(true);
        } else if (previousButton.isSnake2()) {
            currentButton.setIcon(new ImageIcon(images[1]));
            previousButton.setIcon(new ImageIcon(images[3]));

            currentButton.setIsChips(true);
            currentButton.setIsSnake2(true);

            previousButton.setIsChips(false);
            previousButton.setIsSnake2(false);
            previousButton.setIsEmptyCell(true);
        } else {
            currentButton.setIcon(new ImageIcon(images[2]));
            previousButton.setIcon(new ImageIcon(images[3]));

            currentButton.setIsChips(true);
            currentButton.setIsSnake3(true);

            previousButton.setIsChips(false);
            previousButton.setIsSnake3(false);
            previousButton.setIsEmptyCell(true);
        }
    }

    /**
     * Метод, описывающий действие, происходящее при нажатии кнопки:
     * проверяет, является ли нажатая кнопка кнопкой перезапуска игры. Также
     * устанавливает соответствующие флаги (текущая по нажатию кнопка и предыдущая),
     * вызывает метод перестановки (хода) фишек на пустые ячейки, а также после этого проверяет,
     * не является ли положение фишек на игровом поле победным.
     * @param e - нажатая кнопка
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        MyButton currentButton = (MyButton) e.getSource();
        if (currentButton.getText().equals("RESTART")){
            interfaceDisplay.settingCellTypesAndIcons(this, buttons);
            return;
        }

        MyButton previousButton = null;
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].isCurrent()) {
                buttons[i].setIsCurrent(false);
                buttons[i].setIsPrevious(true);
                previousButton = buttons[i];
            }
        }

        currentButton.setIsCurrent(true);

        int indexCB = currentButton.getIndex();

        if (previousButton != null) {
            int indexPB = previousButton.getIndex();
            boolean centralPositions = (indexCB == indexPB + 1) || (indexCB == indexPB - 1) || (indexCB == indexPB + 5) ||
                    (indexCB == indexPB - 5);
            boolean specPositions1 = (indexCB == 3 && indexPB == 8);
            boolean specPositions2 = (indexCB == 23 && indexPB == 18);
            boolean specPositions3 = ((indexCB == 6 || indexCB == 11 || indexCB == 16)&&(indexPB == indexCB + 1 ||
                    indexPB == indexCB + 5 || indexPB == indexCB - 5));
            boolean specPositions4 = ((indexCB == 10 || indexCB == 15 || indexCB == 25)&&(indexPB == indexCB - 1 ||
                    indexPB == indexCB + 5 || indexPB == indexCB - 5));
            boolean specPositions5  = (indexCB == 1 && indexPB == 6) || (indexCB == 5 && indexPB == 10) || (indexCB == 21 && indexPB == 16)
                    || (indexCB == 25 && indexPB == 20);
            boolean specPositions = specPositions1 || specPositions2 || specPositions3 || specPositions4 || specPositions5;

            if (currentButton.isEmptyCell() && previousButton.isChips()) {
              if (specPositions) {
                  reposition(currentButton, previousButton);
                  if (interfaceDisplay.isVictory(buttons)){
                      JOptionPane.showMessageDialog(null,"ВЫ ВЫИГРАЛИ!", "ПОБЕДА", JOptionPane.CANCEL_OPTION);
                  }
              } else if (centralPositions){
                  reposition(currentButton, previousButton);
                  if (interfaceDisplay.isVictory(buttons)){
                        JOptionPane.showMessageDialog(null,"ВЫ ВЫИГРАЛИ!", "ПОБЕДА", JOptionPane.CANCEL_OPTION);
                  }
              }
            }
        }
    }
}