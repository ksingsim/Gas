
import java.io.*;

public class ReadAndCal {


//    private JTextField filenameField;
//    ChooseFileActionListener file = new ChooseFileActionListener(filenameField);
//    Fluid fluidnumber = new Fluid(new JTextField());

//    int fluid = fluidnumber.getFluidnumber();
//    String filepath = filepath.getFilepath();

    // สร้างอาเรย์เพื่อเก็บผลลัพธ์
    private int[] sumv;
    private double[] gasper;

    int btred = 0;
    int btyellow = 0;
    int btgreen = 0;

    // Constructor สำหรับการคำนวณและเก็บผลลัพธ์
    public void startCalculation(String filepath, double fluid) {
        try {

            BufferedReader bfd = new BufferedReader(new FileReader(filepath));

            String str = "";
            for (; ; ) {
                String line = bfd.readLine();
                if (line == null) {
                    break;
                }
                str = str + line;
            }

            String[] Number = str.split(" ");  // แยกข้อมูลที่ได้จากไฟล์
            int[] num = new int[Number.length];  // อาเรย์เก็บตัวเลขจากไฟล์
            double area = 150 * 150 * 200;    // พื้นที่คำนวณ (ตัวอย่าง)

            // สร้างอาเรย์เพื่อเก็บผลลัพธ์
            sumv = new int[Number.length];    // อาเรย์เก็บค่าปริมาตร (sum)
            gasper = new double[Number.length];  // อาเรย์เก็บค่า gaspercent (เปอร์เซ็นต์ของแก๊ส)



            // คำนวณและเก็บผลลัพธ์ในอาเรย์
            for (int i = 0; i < Number.length; i++) {
                num[i] = Integer.parseInt(Number[i]);  // แปลงค่าจาก String เป็น int
                int sum = (int) (150 * 150 * (fluid - (num[i] - 200))); // คำนวณ sum
                double gaspercent = ((double) sum / area) * 100; // คำนวณ gaspercent

                // เก็บผลลัพธ์ในอาเรย์
                sumv[i] = sum;
                gasper[i] =  gaspercent; // แปลงค่า gaspercent เป็น int เพื่อเก็บในอาเรย์




                if (gasper[i] < 50 && gasper[i] > 0)
                {
                    btyellow++;
                }
                else if (gasper[i] > 50)
                {
                    btgreen++;
                }else
                {
                    btred++;
                }

                System.out.println(sumv[i] + "\n" + gasper[i]);


            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
    }

    // Getter สำหรับ sumv[] (ค่าปริมาตร)
    public int[] getSumv() {
        return sumv;
    }

    // Getter สำหรับ gasper[] (เปอร์เซ็นต์ของแก๊ส)
    public double[] getGasper() {
        return gasper;
    }

    public int getBtred() {
        return btred;
    }
    public int getBtyellow() {
        return btyellow;
    }
    public int getBtgreen() {
        return btgreen;
    }

}
