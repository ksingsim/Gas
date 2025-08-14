import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartActionListener implements ActionListener {

    private ReaderFile readerFile;                 // ดึง filepath
    private SetFluidActionListener setFluid;       // ดึง fluid
    private ReadAndCal calculator;                 // ดึง Readerfile

    public StartActionListener(ReaderFile readFile, SetFluidActionListener setFluid, ReadAndCal calculator) {

        this.readerFile = readFile;
        this.setFluid = setFluid;
        this.calculator = calculator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String filepath = readerFile.getFilepath();
        int fluid = setFluid.getFluid();

        // เช็ดว่า มีค่าแล้วหรือยัง
        if (filepath == null || filepath.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please choose a file first.");
            return;
        }

        if (fluid == 0) {
            JOptionPane.showMessageDialog(null, "Please set fluid value first.");
            return;
        }

        // เรียกคำนวณ
        calculator.startCalculation(filepath, fluid);

        // ไม่แน่ใจว่าออกไหม
        // แสดงผลใน ด้านซ้าย บน
       /* System.out.println("Volume: ");
        for (int v : calculator.getSumv()) System.out.print(v + " ");
        System.out.println("\nPercent: ");
        for (double p : calculator.getGasper()) System.out.print(p + " ");*/

        JOptionPane.showMessageDialog(null, "Calculation complete!");
    }
}
