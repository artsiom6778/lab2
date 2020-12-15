package Strakovichlab2;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.StrictMath.*;
import java.lang.*;


public class MainFrame extends JFrame {
    private static final int WIDTH = 400; private static final int HEIGHT = 320;
    // Текстовые поля для считывания значений переменных,
// как компоненты, совместно используемые в различных методах
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    // Текстовое поле для отображения результата,
// как компонент, совместно используемый в различных методах
   private JTextField textFieldResult;
// Группа радио-кнопок для обеспечения уникальности выделения в группе
    private ButtonGroup radioButtons = new ButtonGroup();
    private Box ForMemoryButtons = Box.createHorizontalBox();// Контейнер для отображения радио-кнопок
    private Box hboxFormulaType = Box.createHorizontalBox(); private int formulaId = 1;
    // Формула No1 для рассчѐта
    public Double calculate1(Double x, Double y, Double z) { return x*y*z;
    }
    // Формула No2 для рассчѐта
    public Double calculate2(Double x, Double y, Double z) { return x*y*z + 3/x*y;
    }
    Formula seattoledo = new Formula();
    public Double result;
    // Вспомогательный метод для добавления кнопок на панель
    private void addRadioButton(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.formulaId = formulaId;

            }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }
        // Конструктор класса
public MainFrame() {
            super("Вычисление формулы");
            setSize(WIDTH, HEIGHT);
            Toolkit kit = Toolkit.getDefaultToolkit();
// Отцентрировать окно приложения на экране
        setLocation((kit.getScreenSize().width - WIDTH)/2,
            (kit.getScreenSize().height - HEIGHT)/2);
            hboxFormulaType.add(Box.createHorizontalGlue());
            addRadioButton("Формула 1", 1); addRadioButton("Формула 2", 2); radioButtons.setSelected(
                    radioButtons.getElements().nextElement().getModel(), true);
            hboxFormulaType.add(Box.createHorizontalGlue());
                    hboxFormulaType.setBorder(
                    BorderFactory.createLineBorder(Color.YELLOW));
// Создать область с полями ввода для X и Y
            JLabel labelForX = new JLabel("X:");
            textFieldX = new JTextField("0", 10);
            textFieldX.setMaximumSize(textFieldX.getPreferredSize());
            JLabel labelForY = new JLabel("Y:");
            textFieldY = new JTextField("0", 10);
            textFieldY.setMaximumSize(textFieldY.getPreferredSize());
    JLabel labelForZ = new JLabel("Z:");
    textFieldZ = new JTextField("0", 10);
    textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
            Box hboxVariables = Box.createHorizontalBox();
            hboxVariables.setBorder(
                    BorderFactory.createLineBorder(Color.RED));
            hboxVariables.add(Box.createHorizontalGlue());
            hboxVariables.add(labelForX);
                    hboxVariables.add(Box.createHorizontalStrut(10));
                    hboxVariables.add(textFieldX);
                    hboxVariables.add(Box.createHorizontalStrut(50));
                    hboxVariables.add(labelForY);
                    hboxVariables.add(Box.createHorizontalStrut(10));
                    hboxVariables.add(textFieldY);
                    hboxVariables.add(Box.createHorizontalStrut(50));
                    hboxVariables.add(labelForZ);
                    hboxVariables.add(Box.createHorizontalStrut(10));
                    hboxVariables.add(textFieldZ);
                    hboxVariables.add(Box.createHorizontalGlue());
            // Создать область для вывода результата
            JLabel labelForResult = new JLabel("Результат:");
   //  labelResult = new JLabel("0");
            textFieldResult = new JTextField("0", 10);
            textFieldResult.setMaximumSize(
                    textFieldResult.getPreferredSize());
            Box hboxResult = Box.createHorizontalBox();
            hboxResult.add(Box.createHorizontalGlue());
            hboxResult.add(labelForResult);
            hboxResult.add(Box.createHorizontalStrut(10));
            hboxResult.add(textFieldResult);
            hboxResult.add(Box.createHorizontalGlue());
            hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    JButton MemoryClear = new JButton("MC");
    MemoryClear.addActionListener(new ActionListener()
    {
        public void actionPerformed(ActionEvent ev)
        {
            seattoledo.sum = 0.0;
            textFieldResult.setText(String.valueOf(seattoledo.sum));
        }
    });
    JButton MemoryPlus = new JButton(("M+"));
    MemoryPlus.addActionListener(new ActionListener()
    {
        public void actionPerformed(ActionEvent ev)
        {
            seattoledo.sum+=result;
            textFieldResult.setText(String.valueOf(seattoledo.sum));
        }
    });
            JButton buttonCalc = new JButton("Вычислить");
            buttonCalc.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ev) {
                    try {Double x = Double.parseDouble(textFieldX.getText());
                        Double y = Double.parseDouble(textFieldY.getText());
                        Double z = Double.parseDouble(textFieldZ.getText());
                    if (formulaId==1)
                        result = calculate1(x, y, z);
                    else
                        result = calculate2(x, y, z);

                    textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex) { JOptionPane.showMessageDialog(MainFrame.this,
                        "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
                } }
    });
    JButton buttonReset = new JButton("Очистить поля"); buttonReset.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ev)
        { textFieldX.setText("0");
        textFieldY.setText("0");
        textFieldResult.setText("0");
        } });
    ForMemoryButtons.add(MemoryClear);
    ForMemoryButtons.add(MemoryPlus);
    Box hboxButtons = Box.createHorizontalBox();
    hboxButtons.add(Box.createHorizontalGlue());
    hboxButtons.add(buttonCalc);
    hboxButtons.add(Box.createHorizontalStrut(30));
    hboxButtons.add(buttonReset);
    hboxButtons.add(Box.createHorizontalGlue());
    hboxButtons.setBorder(
            BorderFactory.createLineBorder(Color.GREEN));
// Связать области воедино в компоновке BoxLayout
    Box contentBox = Box.createVerticalBox();
    contentBox.add(Box.createVerticalGlue());
    contentBox.add(hboxFormulaType);
    contentBox.add(hboxVariables);
    contentBox.add(hboxResult);
    contentBox.add(hboxButtons);
    contentBox.add(ForMemoryButtons);
    contentBox.add(Box.createVerticalGlue());
    getContentPane().add(contentBox, BorderLayout.CENTER);
}
    // Главный метод класса
    public static void main(String[] args) {
        MainFrame frame = new MainFrame(); frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); frame.setVisible(true);
    }
}
