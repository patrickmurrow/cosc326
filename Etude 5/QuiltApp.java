//package pkg326quilt;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class QuiltApp {

    private static final ArrayList<Double> scales = new ArrayList<>();
    private static final ArrayList<Integer> reds = new ArrayList<>();
    private static final ArrayList<Integer> greens = new ArrayList<>();
    private static final ArrayList<Integer> blues = new ArrayList<>();
    private static final ArrayList<Square> squares = new ArrayList<>();
    private static ArrayList<Coord> coordList = new ArrayList<>();
    static boolean stop = true;
    static int count = 0;

    public static void main(String[] args) {
        System.out.println("Starting...");
        Scanner input = new Scanner(System.in);
        boolean finish = true;
        while (finish && input.hasNextLine()) {
            String in = input.nextLine();
            //if (in.equals("s")) {
            //    finish = false;
            if (!in.isEmpty()) {
                String[] inList = in.split("\\s");
                scales.add(Double.parseDouble(inList[0]));
                reds.add(Integer.parseInt(inList[1]));
                greens.add(Integer.parseInt(inList[2]));
                blues.add(Integer.parseInt(inList[3]));
            }
        }
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                System.out.println("Creating GUI...");
                JFrame frame = new QuiltFrame("Quilt");
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            }
        });
    }

    public static class QuiltFrame extends JFrame {

        QuiltCanvas canvas;

        public QuiltFrame(String titleIn) {
            setTitle(titleIn);
            setLayout(new GridLayout());

            canvas = new QuiltCanvas();
            add(canvas, BorderLayout.CENTER);
            pack();
        }
    }
    

    public static class QuiltCanvas extends JPanel {

        private int dimH = 800;
        private int dimW = 800;
        private Double scale = 100.0;

        public QuiltCanvas() {
            Double sizeSum = 0.0;
            for(Double s : scales){
                sizeSum += s;
            }
            scale = (dimH/sizeSum);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(dimH, dimW);
        }

        @Override
        public void paintComponent(final Graphics g) {
            //setBackground(Color.black);
            Double tempXD = scales.get(0) * scale;
            int tempXI = tempXD.intValue();
            int coords = dimH / 2 - (tempXI / 2);

            while (count < scales.size()) {
                Color color = new Color(reds.get(count), greens.get(count), blues.get(count));
                Double d = scales.get(count) * scale;
                int sSize = d.intValue();
                
                if (stop) {
                    squares.add(new Square(coords, coords, sSize, 1, color));
                    stop = false;
                }
                int prevX = -1;
                for (Square s : squares) {
                    if (count == 0) {
                        count++;
                    } else if (s.level == count) {
                        prevX = s.x;
                        coordList.add(new Coord(s.x, s.y, s.level, s.size));
                    }
                }
                if (prevX != -1) {
                    for (Coord co : coordList) {
                        if (co.level == count) {
                            squares.add(new Square(co.x - (sSize / 2), co.y - (sSize / 2), sSize, co.level + 1, color));
                            squares.add(new Square(co.x - (sSize / 2), co.y - (sSize / 2) + co.size, sSize, co.level + 1, color));
                            squares.add(new Square(co.x - (sSize / 2) + co.size, co.y - (sSize / 2), sSize, co.level + 1, color));
                            squares.add(new Square(co.x - (sSize / 2) + co.size, co.y - (sSize / 2) + co.size, sSize, co.level + 1, color));

                        }
                    }
                    count++;
                }

            }
            for (Square s : squares) {
                g.setColor(s.color);
                g.fillRect(s.x, s.y, s.size, s.size);
            }
        }

    }

    public static class Square {

        int x;
        int y;
        int size;
        int level;
        Color color;

        public Square(int x, int y, int size, int level, Color color) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.level = level;
            this.color = color;
        }

    }

    public static class Coord {

        int x;
        int y;
        int level;
        int size;

        public Coord(int x, int y, int level, int size) {
            this.x = x;
            this.y = y;
            this.level = level;
            this.size = size;
        }
    }
}
