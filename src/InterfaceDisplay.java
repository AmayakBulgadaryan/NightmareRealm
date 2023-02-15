import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Амаяк
 * Класс, отвечающий за отображение игрового поля
 */
public class InterfaceDisplay {

    /**
     * Массив с загруженными и отмасштабированными изображениями
     * фишек, блоков и пустых полей
     */
    private BufferedImage[] resizedImages = new BufferedImage[5];

    /**
     * Метод, создающий игровое поле и его элементы
     * !!!Метод на адаптирован под другие разрешения (текущее разрешение - 1920х1080)
     * @param game - игровое поле
     * @param buttons - игровые ячейки
     */
    public void display(Game game, MyButton[] buttons){
        createElementsOfArray(buttons);
        createElementsOfResizedImages();
        game.setBounds(550, 300, 400, 500);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setLayout(null);
        game.setVisible(true);
        settingCellTypesAndIcons(game, buttons);
        ProcessingAnArrayOfButtons(game, buttons);
        MyButton restart = new MyButton("RESTART");
        restart.setBounds(145, 335, 100, 75);
        restart.addActionListener(game);
        game.add(restart);
        game.repaint();
    }

    /**
     * Метод, назначаюший каждой кнопке свой тип.
     * В случае фишек тип фишки назначается в случайном порядке.
     * Метод используется при нажатии кнопки "RESTART" для обновления начальных данных
     * на игровом поле
     * @param game - игровое поле
     * @param buttons - игровые ячейки
     */
    public void settingCellTypesAndIcons(Game game, MyButton[] buttons){
        int sn1 = 0, sn2 = 0, sn3 = 0, x = 0, y = 0, n = 0;
        for (int i = 0; i < buttons.length; i++){
            if (i==6||i==8||i==16||i==18)
                buttons[i].setIsEmptyCell(true);
            else if (i==1||i==3||i==11||i==13||i==21||i==23)
                buttons[i].setIsBlockingCell(true);
            else {
                buttons[i].setIsChips(true);
                if (sn1 < 5 && sn2 < 5 && sn3 < 5){
                    int type = (int)(Math.random()*3);
                    if (type == 0){
                        buttons[i].setIsSnake1(true);
                        sn1++;
                    }
                    if (type == 1){
                        buttons[i].setIsSnake2(true);
                        sn2++;
                    }
                    if (type == 2){
                        buttons[i].setIsSnake3(true);
                        sn3++;
                    }
                } else{
                    if (sn1 == 5 && sn2 < 5 && sn3 < 5){
                        int type = (int)(Math.random()*2);
                        if (type == 0){
                            buttons[i].setIsSnake2(true);
                            sn2++;
                        }
                        if (type == 1){
                            buttons[i].setIsSnake3(true);
                            sn3++;
                        }
                    } else if (sn1 < 5 && sn2 == 5 && sn3 < 5){
                        int type = (int)(Math.random()*2);
                        if (type == 0){
                            buttons[i].setIsSnake1(true);
                            sn1++;
                        }
                        if (type == 1){
                            buttons[i].setIsSnake3(true);
                            sn3++;
                        }
                    } else if (sn1 < 5 && sn2 < 5 && sn3 == 5){
                        int type = (int)(Math.random()*2);
                        if (type == 0){
                            buttons[i].setIsSnake1(true);
                            sn1++;
                        }
                        if (type == 1){
                            buttons[i].setIsSnake2(true);
                            sn2++;
                        }
                    } else if (sn1 == 5 && sn2 == 5 && sn3 < 5){
                        buttons[i].setIsSnake3(true);
                        sn3++;
                    } else if (sn1 < 5 && sn2 == 5 && sn3 == 5){
                        buttons[i].setIsSnake1(true);
                        sn1++;
                    } else if (sn1 == 5 && sn2 < 5 && sn3 == 5){
                        buttons[i].setIsSnake2(true);
                        sn2++;
                    }
                }
            }
            if (buttons[i].isSnake1()){
                buttons[i].setIcon(new ImageIcon(resizedImages[0]));
            }
            if (buttons[i].isSnake2()){
                buttons[i].setIcon(new ImageIcon(resizedImages[1]));
            }
            if (buttons[i].isSnake3()){
                buttons[i].setIcon(new ImageIcon(resizedImages[2]));
            }
            if (buttons[i].isEmptyCell()){
                buttons[i].setIcon(new ImageIcon(resizedImages[3]));
            }
            if (buttons[i].isBlockingCell()){
                buttons[i].setIcon(new ImageIcon(resizedImages[4]));
            }
        }
        game.repaint();
    }

    /**
     * Метод, устанавливающий игровые ячейки на игровом поле в определенном порядке.
     * Он также назначает ячейкам свой индекс и ActionListener
     * @param game - игровое поле
     * @param buttons - массив кнопок (ячеек)
     */
    private void ProcessingAnArrayOfButtons(Game game, MyButton[] buttons) {
        int x1 = 65;
        int y1 = 50;
        int x = 0, y = 0, n = 0;
        for (int i = 0; i < buttons.length; i++){
            buttons[i].setBounds(x + x1, y + y1, 50, 50);
            buttons[i].addActionListener(game);
            buttons[i].setIndex(i+1);
            game.add(buttons[i]);
            x+=50;
            n++;
            if (n%5==0){
                x=0;
                y+=50;
            }
        }

        game.repaint();
    }

    /**
     * Метод, читающий файлы с изображениями фишек, пустых клеток и блоков и
     * добавляющий их в массив
     * @return массив изображений (неотмасштабированных)
     * @throws IOException
     */
    private BufferedImage[] loadImage() throws IOException {
        BufferedImage[] bimg = new BufferedImage[5];
        for (int i = 0; i < bimg.length; i++) {
            bimg[i] = ImageIO.read(new File("" + (i + 1) + ".jpg"));
        }
        return bimg;
    }

    /**
     * Метод, масштабирующий принимаемое на вход изображение согласно остальным параметрам
     * @param originImage
     * @param width
     * @param height
     * @param type
     * @return отмасштабированное изображение
     */
    private BufferedImage resizeImage(BufferedImage originImage, int width, int height, int type) {
        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originImage, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    }

    public BufferedImage[] getResizedImages() {
        return resizedImages;
    }

    private  void createElementsOfArray(MyButton[] buttons){
        for (int i = 0; i < 25; i++) {
            buttons[i] = new MyButton();
        }
    }

    private void createElementsOfResizedImages(){
        try {
            BufferedImage[] images = loadImage();
            for (int i = 0; i < images.length; i++) {
                resizedImages[i] = resizeImage(images[i], 50, 50, BufferedImage.TYPE_INT_ARGB);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод, проверяющий, не является ли расположение фишек на игровом поле победным
     * @param buttons
     * @return true, если положение фишек победное
     */
    public boolean isVictory(MyButton[] buttons){
        boolean first = (buttons[0].isSnake1()&&buttons[5].isSnake1()&&buttons[10].isSnake1()&&buttons[15].isSnake1()&&buttons[20].isSnake1())
                &&(buttons[2].isSnake2()&&buttons[7].isSnake2()&&buttons[12].isSnake2()&& buttons[17].isSnake2()&&
                buttons[22].isSnake2())&&(buttons[4].isSnake3()&&buttons[9].isSnake3()&&buttons[14].isSnake3()&&buttons[19].isSnake3()&&
                buttons[24].isSnake3());
        boolean second = (buttons[0].isSnake1()&&buttons[5].isSnake1()&&buttons[10].isSnake1()&&buttons[15].isSnake1()&&buttons[20].isSnake1())
                &&(buttons[2].isSnake3()&&buttons[7].isSnake3()&&buttons[12].isSnake3()&& buttons[17].isSnake3()&&
                buttons[22].isSnake3())&&(buttons[4].isSnake2()&&buttons[9].isSnake2()&&buttons[14].isSnake2()&&buttons[19].isSnake2()&&
                buttons[24].isSnake2());
        boolean third = (buttons[0].isSnake2()&&buttons[5].isSnake2()&&buttons[10].isSnake2()&&buttons[15].isSnake2()&&buttons[20].isSnake2())
                &&(buttons[2].isSnake1()&&buttons[7].isSnake1()&&buttons[12].isSnake1()&& buttons[17].isSnake1()&&
                buttons[22].isSnake1())&&(buttons[4].isSnake3()&&buttons[9].isSnake3()&&buttons[14].isSnake3()&&buttons[19].isSnake3()&&
                buttons[24].isSnake3());
        boolean fourth = (buttons[0].isSnake2()&&buttons[5].isSnake2()&&buttons[10].isSnake2()&&buttons[15].isSnake2()&&buttons[20].isSnake2())
                &&(buttons[2].isSnake3()&&buttons[7].isSnake3()&&buttons[12].isSnake3()&& buttons[17].isSnake3()&&
                buttons[22].isSnake3())&&(buttons[4].isSnake1()&&buttons[9].isSnake1()&&buttons[14].isSnake1()&&buttons[19].isSnake1()&&
                buttons[24].isSnake1());
        boolean fifth = (buttons[0].isSnake3()&&buttons[5].isSnake3()&&buttons[10].isSnake3()&&buttons[15].isSnake3()&&buttons[20].isSnake3())
                &&(buttons[2].isSnake2()&&buttons[7].isSnake2()&&buttons[12].isSnake2()&& buttons[17].isSnake2()&&
                buttons[22].isSnake2())&&(buttons[4].isSnake1()&&buttons[9].isSnake1()&&buttons[14].isSnake1()&&buttons[19].isSnake1()&&
                buttons[24].isSnake1());
        boolean sixth = (buttons[0].isSnake3()&&buttons[5].isSnake3()&&buttons[10].isSnake3()&&buttons[15].isSnake3()&&buttons[20].isSnake3())
                &&(buttons[2].isSnake1()&&buttons[7].isSnake1()&&buttons[12].isSnake1()&& buttons[17].isSnake1()&&
                buttons[22].isSnake1())&&(buttons[4].isSnake2()&&buttons[9].isSnake2()&&buttons[14].isSnake2()&&buttons[19].isSnake2()&&
                buttons[24].isSnake2());

        return first||second||third||fourth||fifth||sixth;
    }

}
