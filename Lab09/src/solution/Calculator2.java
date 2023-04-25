package solution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator2
{

    public final int LOCATION = 300;
    public final int LOCATION2 = 200;
    public final int SIZE = 400;
    public final int SIZE2 = 150;

    private JFrame frame;
    private JPanel buttonPanel;

    private JPanel resultPanel;
    private JLabel resultLabel;
    private JLabel titleLabel;
    private JPanel textField;

    // private JPanel infix;
    private JButton calculateButton;
    private JButton clearButton;

    private JTextField infixExpression;

    public Calculator2()
    {

        initalize();
        frame.pack();
        frame.setVisible(true);

    }

    public void initalize()
    {
        frame = new JFrame();
        frame.setLocation(LOCATION, LOCATION2);
        frame.setSize(SIZE, SIZE2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttonPanel = new JPanel();
        frame.setTitle("GUI Calculator2");
        // buttonPanel = new JPanel();
        resultPanel = new JPanel();
        textField = new JPanel();

        resultLabel = new JLabel("result = ");
        resultLabel.setName("resultLabel");

        calculateButton = new JButton("calculate");
        calculateButton.setName("calculateButton");

        clearButton = new JButton("clear");
        clearButton.setName("clearButton");

        buttonPanel.add(calculateButton);
        buttonPanel.add(clearButton);

        infixExpression = new JTextField(10);
        infixExpression.setName("infixExpression");

        resultPanel.add(resultLabel);
        // resultPanel.add(resultLabel);

        textField.add(infixExpression);
        // resultPanel.add(infixExpression);

        // frame.add(resultLabel, BorderLayout.CENTER);
        frame.add(textField, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(resultPanel, BorderLayout.CENTER);

        clearButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                // if(Double.parseDouble(rightOperand).getText())

                resultLabel.setText(String.format("Result = "));
                infixExpression.setText("");

            }
        });

        calculateButton.addActionListener(new ActionListener()
        {
            ExpressionEvaluator cal = new ExpressionEvaluator();

            public void actionPerformed(ActionEvent e)
            {
                try
                {

                    double b =
                        cal.evaluate(cal.toPostfix(infixExpression.getText()));
                    System.out.print(b);

                    if (Double.isNaN(b))
                    {
                        resultLabel.setText(String.format("Result = Error"));

                    }
                    else
                    {

                        resultLabel.setText(String.format("Result = %f", b));
                    }
                }
                catch (NumberFormatException f)
                {
                    resultLabel.setText(String.format("Result = Error"));
                }
            }
        });

    }

    public JFrame getFrame()
    {
        return frame;
    }

    public static void main(String[] args)
    {
        Calculator2 calc = new Calculator2();

    }
}
