import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;  // ต้องเพิ่มบรรทัดนี้



public class Gui {


    public static void main(String[] args) {
        

        // ==================== 1. การตั้งค่าหน้าต่างหลัก ====================
        JFrame frame = new JFrame("Gas Calculator");
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // ==================== 2. การกำหนดฟอนต์และสี ====================
        Font font1 = new Font("Tohoma", Font.BOLD, 15);    // ฟอนต์เล็ก
        Font font = new Font("Tohoma", Font.BOLD, 100);    // ฟอนต์ใหญ่สำหรับหัวเรื่อง

        // สีต่างๆ ที่ใช้ในโปรแกรม:
        // Color(149, 149, 149) - สีเทาสำหรับพื้นที่เว้นระยะห่าง
        // Color(255,0,0) - สีแดง (ไม่มีแก๊ส)
        // Color(255,254,0) - สีเหลือง (แก๊สน้อยกว่า 50%)
        // Color(0,255,0) - สีเขียว (แก๊สมากกว่า 50%)

        // ==================== 3. Layout และ Panel Structure ====================
        JPanel panel = new JPanel(new BorderLayout()); // คือพาแนลตัวแรก 1

        // สร้าง Panel เว้นระยะห่างรอบๆ
        JPanel edgeN = new JPanel();
        edgeN.setBackground(new Color(149, 149, 149));// คือพวกขอบนอกสุดที่ใส่ไปใน panel
        JPanel edgeS = new JPanel();
        edgeS.setBackground(new Color(149, 149, 149));
        JPanel edgeW = new JPanel();
        edgeW.setBackground(new Color(149, 149, 149));
        JPanel edgeE = new JPanel();
        edgeE.setBackground(new Color(149, 149, 149));

        JPanel panel1 = new JPanel(new BorderLayout()); // คือพาแนลที่ใส่ไปตรงกลางของ panel 2

        // ==================== 4. ส่วนหัว (Header) ====================
        JLabel namePj = new JLabel("Gas Calculator", JLabel.CENTER); // ช่องแสดงข้อความที่แอไปที่ด้านบนของpanel1
        namePj.setPreferredSize(new Dimension(0, 100));
        namePj.setFont(font); // ใช้ฟอนต์ขนาด 100

        JPanel panel1N = new JPanel(); // ไม่แน่ใจแต่เซ็ตไปที่ด้านบนของpanel1
        panel1N.setBackground(new Color(149, 149, 149));

        // ==================== 5. ตารางแสดงข้อมูลหลัก (Main Grid) ====================
        JPanel panelTable = new JPanel(new BorderLayout()); // คือตารางปุ่มที่เซ็ตไปที่ตรงกลาง panel1 และเป็น BorderLayout 3

        // Panel เว้นระยะห่างรอบตาราง
        JPanel TableN = new JPanel();
        TableN.setBackground(new Color(149, 149, 149)); // คือพาแนลขอบที่ แอดไปที่ด้านบน ด้านขวา ด้านล่างของ panelTable
        JPanel TableS = new JPanel();
        TableS.setBackground(new Color(149, 149, 149));
        JPanel TableE = new JPanel();
        TableE.setBackground(new Color(149, 149, 149));

        // สร้างตารางกริด 10x20 = 200 ปุ่ม
        JPanel TableC = new JPanel(new BorderLayout());// คือพาแนลที่จะใช้ใส่ตารางปุ่มโดยแอดไปที่ panelTable ตรงกลางและเป็น BorderLayout 4
        JPanel Table = new JPanel(new GridLayout(10, 20));// คือพาแนลที่เป็นตารางปุ่ม แอดไปที่ตรงกลางของพอแนล TableC
        for (int i = 0; i < 200; i++) {
            Button button = new Button();
            Table.add(button);
        }

        // ==================== 6. แถบควบคุม (Control Panel) ====================
        JPanel controlTable = new JPanel(new BorderLayout());// คือพาแนลที่แอไปที่ด้านล่างของพาแนล TableC 5
        controlTable.setPreferredSize(new Dimension(0, 100));

        // ปุ่ม Start ตรงกลาง
        JPanel CTableS1N = new JPanel(); // คือพาแนลที่แอดไปที่ controlTable ด้าน บน
        CTableS1N.setBackground(new Color(149, 149, 149));
        JButton CTableS1C = new JButton("Start"); // คือปุ่ม สตาทที่แอดไปตรงกลางของ controlTable
        CTableS1C.setFont(font1);

        // ส่วนเลือกไฟล์ (ด้านซ้าย)
        JPanel CTableS1W = new JPanel(new GridLayout(2, 1)); // คือพาแนลที่แอดไปที่ controlTable ด้าน ซ้าย
        JTextField textFile = new JTextField("File.txt");// คือแอไปที่ CTableS1W และใช้GridLayout 1.2
        JButton chooseFile = new JButton("Choose File");
        CTableS1W.add(textFile);
        CTableS1W.add(chooseFile);
        CTableS1W.setPreferredSize(new Dimension(500, 0));

        // ส่วนตั้งค่าของเหลว (ด้านขวา)
        JPanel CTableS1E = new JPanel(new GridLayout(2, 1));// คือพาแนลที่แอดไปที่ controlTable ด้าน บน
        JTextField textfluid = new JTextField("depth"); // คือแอไปที่ CTableS1E และใช้GridLayout 1.2
        JButton inputfluid = new JButton("Set Fluid");
        CTableS1E.add(textfluid);
        CTableS1E.add(inputfluid);
        CTableS1E.setPreferredSize(new Dimension(500, 0));

        // ==================== 7. แผงสรุปผล (Summary Panel) ====================
        JPanel panelSummary = new JPanel(new BorderLayout()); // คือพาแนลที่แอดไปที่ ด้านขวาของ Panel1
        panelSummary.setPreferredSize(new Dimension(300, 0));// กำหนดขนาดของ พาแนล

        // 7.1 ส่วนแสดงค่าปริมาตรและเปอร์เซ็นต์
        JPanel panelSummary1N = new JPanel(new BorderLayout());// คือพาแนลที่แอดไปที่ ด้านบนของ panelSummary
        panelSummary1N.setPreferredSize(new Dimension(0, 250));

        String strV = "----";    // ค่าปริมาตร
        String strPer = "----";  // ค่าเปอร์เซ็นต์
        JPanel VolumeOrarea = new JPanel(new GridLayout(2, 1)); //แอดไปที่ตรงกลางของ panelSummary1N และเป็น ลง1 2แถว

        JPanel volume = new JPanel(new GridLayout(1, 2));//แอดไปที่VolumeOrarea ก็จะทำงานตามGridLayoutของVolumeOrarea และ GridLayout ของvolume คือ 2 ตั้ง 1แถว
        JLabel textVolume = new JLabel("Volume" + "  " + strV, JLabel.CENTER); //แอดไปที่ volume ก็จะทำงานตามGridLayout
        JLabel textgas = new JLabel("Percen" + "  " + strPer + " %", JLabel.CENTER);//แอดไปที่ volume ก็จะทำงานตามGridLayout


        // 7.2 ส่วนแสดงเปอร์เซ็นต์ของพื้นที่
        JPanel Ofarea = new JPanel(new BorderLayout());//แอดไปที่VolumeOrarea ก็จะทำงานตามGridLayoutของVolumeOrarea และ อันนี้คือ BorderLayout
        JPanel edgesum = new JPanel();//อันนี้คือขอบที่แอดไปที่ด้านบนของOfarea
        edgesum.setBackground(new Color(149, 149, 149));
        String strEq = "----";
        JLabel textcolpercen = new JLabel("  " + strEq + " % of the total area");//คือลาเบลที่แอดไปที่ตรงกลางของOfarea

        // ปุ่มแสดงสีตัวอย่าง
        JPanel colGas = new JPanel(new BorderLayout());//คือพาแนลที่แอดไปที่ด้านซ้ายของOfarea และเป็น BorderLayout
        colGas.setPreferredSize(new Dimension(100, 0));
        Button color = new Button();//คือปุ่มที่แอดไปที่ตรงกลางของ colGas

        // Panel เว้นระยะห่างรอบปุ่มสี
        JPanel spacecolN = new JPanel();
        spacecolN.setPreferredSize(new Dimension(0, 30));//คือพาแนลที่แอดไปที่ บน ซ้าย ขวา ล่าง ของ colGas เพื่อเว้นระยะให้ปุ่อยู๋ตรงกลาง
        JPanel spacecolS = new JPanel();
        spacecolS.setPreferredSize(new Dimension(0, 30));
        JPanel spacecolW = new JPanel();
        spacecolW.setPreferredSize(new Dimension(20, 0));
        JPanel spacecolE = new JPanel();
        spacecolE.setPreferredSize(new Dimension(20, 0));

        // ==================== 8. คำอธิบายระบบสี (Color Legend) ====================
        JPanel panelSummary1C = new JPanel(new BorderLayout());//คือพาแนลที่แอดไปที่ ตรงกลางของ panelSummary และเป็น BorderLayout
        JPanel CSummary1C = new JPanel(new GridLayout(3, 1));//คือพาแนลที่แอดไปที่ตรงกลางของ panelSummary1C เป็นGridLayout ตรง1 3แถว

        // ข้อความอธิบาย
        JLabel Nogas = new JLabel("No Gas"); //คือลาเบลที่แอดไปที่CSummary1C และ ทำตามเป็นGridLayout ตรง1 3แถว
        JLabel less = new JLabel("Gas less than 50%");
        JLabel more = new JLabel("Gas more than 50%");
        Nogas.setFont(font1);
        less.setFont(font1);
        more.setFont(font1);
        CSummary1C.add(Nogas);
        CSummary1C.add(less);
        CSummary1C.add(more);

        // ปุ่มแสดงสีตัวอย่าง
        Panel CSummary1W = new Panel(new GridLayout(3, 1)); //คือพาแนลที่แอดไปที่ด้านซ้ายของ panelSummary1C เป็น GridLayout ตรง1 3แถว
        CSummary1W.setPreferredSize(new Dimension(100, 0));

        // สีแดง - ไม่มีแก๊ส
        Panel CSummary1WR = new Panel(new BorderLayout());//คือพาแนลที่ยัดไปที่ CSummary1W ก็จะทำตาม GridLayout และยังเป็นBorderLayout
        JButton space1C = new JButton();//คือปุ่มที่ไปตรงกลางของ CSummary1WR
        space1C.setBackground(new Color(255, 0, 0));
        JPanel space1N = new JPanel(); //พวกนี้ก็จะไปยัดไว้ บน ซ้าย ขวา ล่างเพื่อให้ปุ่มพอดี
        JPanel space1S = new JPanel();
        JPanel space1W = new JPanel();
        JPanel space1E = new JPanel();

        // สีเหลือง - แก๊สน้อยกว่า 50%
        JPanel CSummary1WY = new JPanel(new BorderLayout());//คือพาแนลที่ยัดไปที่ CSummary1W ก็จะทำตาม GridLayout และยังเป็นBorderLayout
        JButton space2C = new JButton();//คือปุ่มที่ไปตรงกลางของ CSummary1WY
        space2C.setBackground(new Color(255, 254, 0));
        JPanel space2N = new JPanel();//พวกนี้ก็จะไปยัดไว้ บน ซ้าย ขวา ล่างเพื่อให้ปุ่มพอดี
        JPanel space2S = new JPanel();
        JPanel space2W = new JPanel();
        JPanel space2E = new JPanel();

        // สีเขียว - แก๊สมากกว่า 50%
        JPanel CSummary1WG = new JPanel(new BorderLayout());//คือพาแนลที่ยัดไปที่ CSummary1W ก็จะทำตาม GridLayout และยังเป็นBorderLayout
        JButton space3C = new JButton();//คือปุ่มที่ไปตรงกลางของ CSummary1WG
        space3C.setBackground(new Color(0, 255, 0));
        JPanel space3N = new JPanel();//พวกนี้ก็จะไปยัดไว้ บน ซ้าย ขวา ล่างเพื่อให้ปุ่มพอดี
        JPanel space3S = new JPanel();
        JPanel space3W = new JPanel();
        JPanel space3E = new JPanel();

        // ==================== 9. การจัดวาง Layout ทั้งหมด ====================

        // จัดวางตาราง
        panelTable.add(TableC, BorderLayout.CENTER);
        panelTable.add(TableN, BorderLayout.NORTH);
        panelTable.add(TableS, BorderLayout.SOUTH);
        panelTable.add(TableE, BorderLayout.EAST);

        // จัดวางแถบควบคุม
        controlTable.add(CTableS1C, BorderLayout.CENTER);
        controlTable.add(CTableS1N, BorderLayout.NORTH);
        controlTable.add(CTableS1W, BorderLayout.WEST);
        controlTable.add(CTableS1E, BorderLayout.EAST);

        // รวมตารางและแถบควบคุม
        TableC.add(Table, BorderLayout.CENTER);
        TableC.add(controlTable, BorderLayout.SOUTH);

        // จัดวางสีในแผงสรุปผล
        CSummary1WR.add(space1C, BorderLayout.CENTER);
        CSummary1WR.add(space1N, BorderLayout.NORTH);
        CSummary1WR.add(space1S, BorderLayout.SOUTH);
        CSummary1WR.add(space1W, BorderLayout.WEST);
        CSummary1WR.add(space1E, BorderLayout.EAST);
        CSummary1W.add(CSummary1WR);

        CSummary1WY.add(space2C, BorderLayout.CENTER);
        CSummary1WY.add(space2N, BorderLayout.NORTH);
        CSummary1WY.add(space2S, BorderLayout.SOUTH);
        CSummary1WY.add(space2W, BorderLayout.WEST);
        CSummary1WY.add(space2E, BorderLayout.EAST);
        CSummary1W.add(CSummary1WY);

        CSummary1WG.add(space3C, BorderLayout.CENTER);
        CSummary1WG.add(space3N, BorderLayout.NORTH);
        CSummary1WG.add(space3S, BorderLayout.SOUTH);
        CSummary1WG.add(space3W, BorderLayout.WEST);
        CSummary1WG.add(space3E, BorderLayout.EAST);
        CSummary1W.add(CSummary1WG);

        // รวมแผงสรุปผล
        colGas.add(color, BorderLayout.CENTER);
        colGas.add(spacecolN, BorderLayout.NORTH);
        colGas.add(spacecolS, BorderLayout.SOUTH);
        colGas.add(spacecolW, BorderLayout.WEST);
        colGas.add(spacecolE, BorderLayout.EAST);

        // volume และ percen
        volume.add(textVolume);
        volume.add(textgas);
        //  เปอร์เซ็นพื้นที่
        Ofarea.add(colGas, BorderLayout.WEST);gi
        Ofarea.add(edgesum, BorderLayout.NORTH);
        Ofarea.add(textcolpercen, BorderLayout.CENTER);
        // summmary แย่ก 2 ช่อง
        VolumeOrarea.add(volume);
        VolumeOrarea.add(Ofarea);

        panelSummary1C.add(CSummary1C, BorderLayout.CENTER);
        panelSummary1C.add(CSummary1W, BorderLayout.WEST);

        panelSummary1N.add(VolumeOrarea, BorderLayout.CENTER);
        panelSummary1N.add(new JPanel() {{
            setBackground(new Color(149, 149, 149));
        }}, BorderLayout.NORTH);
        panelSummary1N.add(new JPanel() {{
            setBackground(new Color(149, 149, 149));
        }}, BorderLayout.SOUTH);

        panelSummary.add(panelSummary1C, BorderLayout.CENTER);
        panelSummary.add(panelSummary1N, BorderLayout.NORTH);
        panelSummary.add(new JPanel() {{
            setBackground(new Color(149, 149, 149));
        }}, BorderLayout.SOUTH);
        panelSummary.add(new JPanel(), BorderLayout.EAST);

        // รวม Panel หลักทั้งหมด
        panel1.add(panelTable, BorderLayout.CENTER);
        panel1.add(panel1N, BorderLayout.NORTH);
        panel1.add(namePj, BorderLayout.NORTH);
        panel1.add(panelSummary, BorderLayout.EAST);

        // รวม Panel หลักกับเว้นระยะห่างรอบๆ
        panel.add(panel1, BorderLayout.CENTER);
        panel.add(edgeN, BorderLayout.NORTH);
        panel.add(edgeS, BorderLayout.SOUTH);
        panel.add(edgeW, BorderLayout.WEST);
        panel.add(edgeE, BorderLayout.EAST);

        // แสดงผล
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        // ดึง ReaderFile
        ReaderFile readFile = new ReaderFile();
        chooseFile.addActionListener(e -> {
            readFile.loadFile();
        });
        // f7' SetFluidAc....
        SetFluidActionListener setFluid = new SetFluidActionListener(textfluid);
        inputfluid.addActionListener(setFluid);

        // ดึง ReadAndCal
        ReadAndCal calculator = new ReadAndCal();

        // ปุ่ม Start
        CTableS1C.addActionListener(new StartActionListener(readFile, setFluid, calculator));
    }
}