import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.*;

public class client implements ActionListener {

    JTextField text;
    static JPanel a1;
    static Box verticle = Box.createVerticalBox();

    static DataOutputStream dout;
    static JFrame f =new JFrame();

    client()
    {
        f.setLayout(null);
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(7,94,84));
        p1.setBounds(0,0,450,80);
        p1.setLayout(null);
        f.add(p1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(5,20,25,25);
        back.addMouseListener(new MouseAdapter() {
            //            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        p1.add(back);


        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/1.png"));
        Image i5 = i4.getImage().getScaledInstance(55,55,Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel profile = new JLabel(i6);
        profile.setBounds(40,10,55,55);
        p1.add(profile);


        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel video = new JLabel(i9);
        video.setBounds(300,20,30,30);
        p1.add(video);

        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i11 = i10.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel phone  = new JLabel(i12);
        phone.setBounds(360,20,30,30);
        p1.add(phone);

        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(10,25,Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel morevert = new JLabel(i15);
        morevert.setBounds(400,20,30,30);
        p1.add(morevert);

        JLabel mikk = new JLabel("Deep");
        mikk.setBounds(110,15,100,25);
        mikk.setForeground(Color.white);
        mikk.setFont(new Font("SAN SERIF",Font.BOLD,18));
        p1.add(mikk);

        JLabel stat = new JLabel("Active Now");
        stat.setBounds(110,35,100,25);
        stat.setForeground(Color.white);
        stat.setFont(new Font("SAN SERIF",Font.BOLD,14));
        p1.add(stat);

        a1 =new JPanel();
        a1.setBounds(5,75,430,570);
//        getContentPane().setBackground(Color.WHITE);
        f.add(a1);

        text = new JTextField();
        text.setBounds(5,655,310,40);
        text.setFont(new Font("SAN SERIF",Font.BOLD,14));
        f.add(text);

        JButton send = new JButton("SEND");
        send.setForeground(Color.WHITE);
        send.setBounds(320,655,123,40);
        send.setBackground(new Color(7,94,84));
        send.setFont(new Font("SAN SERIF",Font.BOLD,16));
        send.addActionListener(this);
        f.add(send);


        f.setSize(460,760);
        f.setVisible(true);
        f.setLocation(200,100);
        f.getContentPane().setBackground(Color.WHITE);


    }
    public void actionPerformed(ActionEvent e)
    {
        try {
            String out = text.getText();


            JPanel p2 = formatLable(out);
//        p2.add(output);
            a1.setLayout(new BorderLayout());
            JPanel right = new JPanel(new BorderLayout());
            right.add(p2, BorderLayout.LINE_END);
            verticle.add(right);
            verticle.add(Box.createVerticalStrut(15));
            a1.add(verticle, BorderLayout.PAGE_START);

            dout.writeUTF(out);
            text.setText("");
            f.repaint();
            f.invalidate();
            f.validate();
        }
        catch (Exception ea)
        {
            ea.printStackTrace();
        }
    }
    public static JPanel formatLable(String out)
    {
        JPanel panel  = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        JLabel output = new JLabel(out);
        output.setFont(new Font("Tohoma",Font.PLAIN,16 ));
        output.setBackground(new Color(37,211,102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15,15,15,50));
        panel.add(output);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);

        return panel;
    }

    public static void main(String[] args)

    {
        new client();

        try
        {
            Socket s =new Socket("127.0.0.1",6001);
            DataInputStream din =  new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());

            while (true)
            {
                a1.setLayout(new BorderLayout());
                String msg  = din.readUTF();
                JPanel pan = formatLable(msg);
                JPanel left = new JPanel(new BorderLayout());
                left.add(pan,BorderLayout.LINE_START);
                verticle.add(left);

                verticle.add(Box.createVerticalStrut(15));
                a1.add(verticle,BorderLayout.PAGE_START);
                f.validate();

            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
