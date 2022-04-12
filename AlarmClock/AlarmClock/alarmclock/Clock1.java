package alarmclock;

import jaco.mp3.player.MP3Player;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import static java.lang.Thread.sleep;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Clock1 extends JFrame {

    private Container c;
    private Font f, f1, f2, f3, f4;
    private JLabel jLabel1, jLabel2, jLabel3, jLabel4, imgLabel;
    private ImageIcon icon;
    private ImageIcon img, img2, img3;
    private JTextField tfh, tfm, tfam;
    private JButton btnOk, btnStop, btncl;

    public int temp_h, temp_m;
    public String temp_am;
    private int flag = 1;

    Clock1() {
        initComponents();
        currentTime();
    }

    public static final String path = "E:/_.Programming/Java/zzzzzz_____icons/beep_alarm.mp3";
    MP3Player mp3 = new MP3Player(new File(path));

    public void initComponents() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(1270, 170, 560, 320);
        this.setTitle("Alarm Clock");

        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.BLACK);
        
        f1 = new Font("Arial", Font.BOLD, 20);
        f2 = new Font("Digital-7 Mono", Font.BOLD, 46);
        f3 = new Font("Digital-7", Font.PLAIN, 90);
        f4 = new Font("Tahoma", Font.BOLD, 36);

        icon = new ImageIcon(getClass().getResource("Miscellaneous-Icon.jpg"));
        this.setIconImage(icon.getImage());

        img = new ImageIcon(getClass().getResource("alarm.jpg"));
        imgLabel = new JLabel(img);
        imgLabel.setBounds(30, 215, img.getIconWidth(), 40);
        c.add(imgLabel);

        jLabel1 = new JLabel();
        jLabel1.setBounds(60, 5, 360, 130);
        jLabel1.setFont(f3);
        jLabel1.setForeground(new Color(0, 204, 51));
        c.add(jLabel1);

        jLabel3 = new JLabel();
        jLabel3.setBounds(435, 35, 100, 110);
        jLabel3.setFont(f2);
        jLabel3.setForeground(new Color(0, 204, 51));
        c.add(jLabel3);

        jLabel2 = new JLabel();
        jLabel2.setBounds(55, 130, 260, 50);
        jLabel2.setFont(f4);
        jLabel2.setForeground(new Color(0, 204, 51));
        c.add(jLabel2);

        jLabel4 = new JLabel();
        jLabel4.setBounds(335, 130, 230, 50);
        jLabel4.setFont(f4);
        jLabel4.setForeground(new Color(0, 204, 51));
        c.add(jLabel4);

        tfh = new JTextField();
        tfh.setBounds(100, 215, 50, 40);
        tfh.setFont(new Font("Tahoma", Font.BOLD, 20));
        tfh.setHorizontalAlignment(JTextField.CENTER);
        c.add(tfh);

        tfm = new JTextField();
        tfm.setBounds(165, 215, 50, 40);
        tfm.setFont(new Font("Tahoma", Font.BOLD, 20));
        tfm.setHorizontalAlignment(JTextField.CENTER);
        c.add(tfm);

        tfam = new JTextField();
        tfam.setBounds(230, 215, 50, 40);
        tfam.setFont(new Font("Tahoma", Font.BOLD, 20));
        tfam.setHorizontalAlignment(JTextField.CENTER);
        c.add(tfam);

        btnOk = new JButton("Ok");
        btnOk.setBounds(295, 215, 60, 40);
        btnOk.setFont(new Font("Tahoma", Font.BOLD, 16));
        c.add(btnOk);
        
        btncl = new JButton("CL");
        btncl.setBounds(370, 215, 60, 40);
        btncl.setFont(new Font("Tahoma", Font.BOLD, 16));
        c.add(btncl);

        btnStop = new JButton("Stop");
        btnStop.setBounds(445, 215, 75, 40);
        btnStop.setFont(new Font("Tahoma", Font.BOLD, 16));
        c.add(btnStop);

        //Button Ok
        btnOk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                temp_h = Integer.parseInt(tfh.getText());
                temp_m = Integer.parseInt(tfm.getText());
                temp_am = tfam.getText();
                flag = 1; 
            } 
    });

        //Stop Button
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent ae) {
                flag = 0;
                mp3.stop();
            }
        });
        
        btncl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent ae) {
                flag = 0;
                tfh.setText("");
                tfm.setText("");
                tfam.setText("");
                
            }
        });
        
        
        
}

    public void currentTime() {

        Thread clock;
        clock = new Thread() {

            public void run() {

                for (;;) {
                    Calendar cal = new GregorianCalendar();

                    int second = cal.get(Calendar.SECOND);
                    int minute = cal.get(Calendar.MINUTE);
                    int hour = cal.get(Calendar.HOUR);
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    int month = cal.get(Calendar.MONTH) + 1;
                    int year = cal.get(Calendar.YEAR);

                    //AM_PM
                    Calendar datetime = Calendar.getInstance();
                    String am_pm = "";
                    if (datetime.get(Calendar.AM_PM) == Calendar.AM) {
                        am_pm = "AM";
                    } else if (datetime.get(Calendar.AM_PM) == Calendar.PM) {
                        am_pm = "PM";
                    }

                    //week day
                    String[] strDays = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thusday",
                        "Friday", "Saturday"};
                    String wd;
                    wd = strDays[datetime.get(Calendar.DAY_OF_WEEK) - 1];

                    //setting to label
                    jLabel1.setText(hour + " : " + minute + " : " + second);
                    jLabel2.setText(day + " - " + month + " - " + year);
                    jLabel3.setText(am_pm);
                    jLabel4.setText(" " + wd);

                    //Alarm ---------------
                    
                    if (temp_h == hour && temp_m == minute && temp_am.equals(am_pm) && flag == 1) {
                        mp3.play();

                        if (second == 59 || flag == 0) {
                            mp3.stop();
                        }
                    }
                    

                    try {
                        sleep(1000);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }

                }
            }
        };
        clock.start();
    }
    
    
}
