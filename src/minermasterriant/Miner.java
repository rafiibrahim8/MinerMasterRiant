/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package minermasterriant;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
/**
 *
 * @author ibrahim
 */
public class Miner extends javax.swing.JFrame {
    
    public static final String VERSION = "1.0.2";
    public static final int PORT_GYRO = 17916;
    public static final int PORT_ACC = 17908;
    public static final int PORT_COM = 17938;
    
    private ArrayList<SensorData> accData,gyData;
    private AtomicBoolean isConnected;
    private XYSeries accSeriesX,accSeriesY,accSeriesZ,gySeriesX,gySeriesY,gySeriesZ;
    private XYSeriesCollection accCollection,gyCollection;
    private JFreeChart accChart,gyChart;
    private ChartPanel accChartPanel,gyChartPanel;
    
    Socket socketAcc, socketGy, socketCom;
    InputStream inAcc, inGy, inCom;
    OutputStream outCom;
    
    /**
     * Creates new form MainFrame
     */
    public Miner() {
        initPlots();
        initComponents();
        String ip = getIP();
        lbVersion.setText("v"+VERSION);
        initKeyListener();
        isConnected = new AtomicBoolean(false);
        accData = new ArrayList<>();
        gyData = new ArrayList<>();
        clearAllData();
        if(ip == null) return;        
        initServer(ip);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnReset = new javax.swing.JButton();
        lbConStatus = new javax.swing.JLabel();
        lbVersion = new javax.swing.JLabel();
        scrollGyPanelTA = new javax.swing.JScrollPane();
        gyPanelTA = new javax.swing.JTextArea();
        scrollTALog = new javax.swing.JScrollPane();
        taLog = new javax.swing.JTextArea();
        btnCollect = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        scrollAccPanelTA = new javax.swing.JScrollPane();
        accPanelTA = new javax.swing.JTextArea();
        lblPidHint = new javax.swing.JLabel();
        tfTrim = new javax.swing.JTextField();
        lbTrimHint0 = new javax.swing.JLabel();
        lbTrimHint1 = new javax.swing.JLabel();
        btnDiscard = new javax.swing.JButton();
        lbClass = new javax.swing.JLabel();
        tfPid = new javax.swing.JTextField();
        tfSample = new javax.swing.JTextField();
        lblSample = new javax.swing.JLabel();
        cbClass = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Master Miner");
        setAutoRequestFocus(false);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        setMinimumSize(new java.awt.Dimension(1300, 620));
        setName("frameMain"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnReset.setText("Reset Fields");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        getContentPane().add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 110, -1));

        lbConStatus.setForeground(new java.awt.Color(255, 0, 0));
        lbConStatus.setText("Not Connected");
        getContentPane().add(lbConStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 560, 400, -1));

        lbVersion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbVersion.setText("v1.0");
        getContentPane().add(lbVersion, new org.netbeans.lib.awtextra.AbsoluteConstraints(1194, 560, 100, -1));

        gyPanelTA.setColumns(20);
        gyPanelTA.setRows(5);
        scrollGyPanelTA.setViewportView(gyPanelTA);

        getContentPane().add(scrollGyPanelTA, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 250, 630, 300));

        taLog.setColumns(20);
        taLog.setRows(5);
        taLog.setText(">>Log will appear here.");
        scrollTALog.setViewportView(taLog);

        getContentPane().add(scrollTALog, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 500, 230));

        btnCollect.setText("Collect");
        btnCollect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCollectActionPerformed(evt);
            }
        });
        getContentPane().add(btnCollect, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 250, -1));

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        getContentPane().add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 130, 250, -1));

        accPanelTA.setColumns(20);
        accPanelTA.setRows(5);
        scrollAccPanelTA.setViewportView(accPanelTA);

        getContentPane().add(scrollAccPanelTA, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 630, 300));

        lblPidHint.setText("Perticipent ID");
        getContentPane().add(lblPidHint, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 110, 20));
        getContentPane().add(tfTrim, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 80, 90, 30));

        lbTrimHint0.setText("Enter sec to trim (optional)");
        getContentPane().add(lbTrimHint0, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, -1, 20));

        lbTrimHint1.setFont(new java.awt.Font("Dialog", 1, 8)); // NOI18N
        lbTrimHint1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbTrimHint1.setText("eg. 1 or 1-10");
        lbTrimHint1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(lbTrimHint1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 100, 80, -1));

        btnDiscard.setText("Discard");
        btnDiscard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiscardActionPerformed(evt);
            }
        });
        getContentPane().add(btnDiscard, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 250, -1));

        lbClass.setText("Class");
        getContentPane().add(lbClass, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 50, -1));
        getContentPane().add(tfPid, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 150, -1));
        getContentPane().add(tfSample, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 150, -1));

        lblSample.setText("Sample");
        getContentPane().add(lblSample, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 50, -1));

        cbClass.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Please select", "Stand - A", "Sit - B", "Talking-sitting - C", "Talking-standing - D", "Standing-sitting - E", "Laying - F", "Laying-standing - G", "Picking - H", "Jumping - I", "Push-up - J", "Sit-up - K", "Walk forward - L", "Walk backward - M", "Walk Circle - N", "Running - O", "Biking - P", "Kicking - Q", "Stationary biking - R", "Stair-up - S", "Stair-down - T", "Table-tennis - U" }));
        cbClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbClassActionPerformed(evt);
            }
        });
        getContentPane().add(cbClass, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 220, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        tfPid.setText("");
        cbClass.setSelectedIndex(0);
        tfSample.setText("");
        btnCollect.setText("Collect");
        //clearAllData();
    }//GEN-LAST:event_btnResetActionPerformed
    
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        btnCollect.setEnabled(true);
        log("Saving...");
        String fileName = tfPid.getText()+"_"+dispatchClassCB()+"_"+tfSample.getText()+".csv";
        File file=new File(fileName);
        int j=0;
        while(file.exists()){
            fileName = tfPid.getText()+"_"+dispatchClassCB()+"_"+tfSample.getText()+"_"+j+".csv";
            file=new File(fileName);
            j++;
        }
        FileOutputStream fos;
        
        if(tfTrim.getText().isEmpty()){
            try {
                fos = new FileOutputStream(file);
                writeToFile(fos);
            } catch (Exception ex) {
                log(ex);
                return;
            }
            clearAllData();
            log("Data Saved without trimming. FileName:"+fileName);
            tfTrim.setText("");
            tfSample.setText("");
        }
        else{
            float[] lim;
            try {
                lim = parseLimits();
            } catch (Exception ex) {
                log("Invalid Limit field");
                log(ex);
                return;
            }
            try {
                fos = new FileOutputStream(file);
                writeToFile(fos,lim[0],lim[1]);
            } catch (IOException ex) {
                log(ex);
                return;
            }
            clearAllData();
            log("Data Saved with trimming ["+lim[0]+"-"+lim[1]+"]. FileName:"+fileName);
            tfTrim.setText("");
            tfSample.setText("");
        }
    }//GEN-LAST:event_btnSaveActionPerformed
    
    private void btnCollectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCollectActionPerformed
        if(!isConnected.get()){
            log("Device is not connected.");
            return;
        }
        if(!isEveryFieldOkay()){
            log("Please fill up the fields..");
            return;
        }
        if(btnCollect.getText().equalsIgnoreCase("Collect")){
            if(!sendCommand("S")){
                log("Unable to communicate with the phone.");
            }
            clearAllData();
            return;
        }
        sendCommand("P");
        btnCollect.setEnabled(false);
    }//GEN-LAST:event_btnCollectActionPerformed
    
    private void btnDiscardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiscardActionPerformed
        clearAllData();
        btnCollect.setEnabled(true);
    }//GEN-LAST:event_btnDiscardActionPerformed

    private void cbClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbClassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbClassActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the GTK+ look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
        * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
        */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("GTK+".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
                //System.out.println(info.getName());
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Miner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Miner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Miner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Miner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Miner().setVisible(true);
            }
        });
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea accPanelTA;
    private javax.swing.JButton btnCollect;
    private javax.swing.JButton btnDiscard;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cbClass;
    private javax.swing.JTextArea gyPanelTA;
    private javax.swing.JLabel lbClass;
    private javax.swing.JLabel lbConStatus;
    private javax.swing.JLabel lbTrimHint0;
    private javax.swing.JLabel lbTrimHint1;
    private javax.swing.JLabel lbVersion;
    private javax.swing.JLabel lblPidHint;
    private javax.swing.JLabel lblSample;
    private javax.swing.JScrollPane scrollAccPanelTA;
    private javax.swing.JScrollPane scrollGyPanelTA;
    private javax.swing.JScrollPane scrollTALog;
    private javax.swing.JTextArea taLog;
    private javax.swing.JTextField tfPid;
    private javax.swing.JTextField tfSample;
    private javax.swing.JTextField tfTrim;
    // End of variables declaration//GEN-END:variables
    
    private void initPlots() {
        //helpful: https://www.youtube.com/watch?v=cw31L_OwX3A
        accSeriesX = new XYSeries("X");
        accSeriesY = new XYSeries("Y");
        accSeriesZ = new XYSeries("Z");
        accCollection = new XYSeriesCollection(null);
        accCollection.addSeries(accSeriesX);
        accCollection.addSeries(accSeriesY);
        accCollection.addSeries(accSeriesZ);
        accChart = ChartFactory.createXYLineChart("Accelerometer Data", "Time (s)", "Acceleration (m/s2)", accCollection);
        accChartPanel = new ChartPanel(accChart);
        accChartPanel.setBounds(10, 250, 630, 300);
        getContentPane().add(accChartPanel);
        
        gySeriesX = new XYSeries("X");
        gySeriesY = new XYSeries("Y");
        gySeriesZ = new XYSeries("Z");
        gyCollection = new XYSeriesCollection(null);
        gyCollection.addSeries(gySeriesX);
        gyCollection.addSeries(gySeriesY);
        gyCollection.addSeries(gySeriesZ);
        gyChart = ChartFactory.createXYLineChart("Gyro Data", "Time (s)", "Angular Velocity (rad/s)", gyCollection);
        gyChartPanel = new ChartPanel(gyChart);
        gyChartPanel.setBounds(660, 250, 630, 300);
        getContentPane().add(gyChartPanel);
    }
    
    
    private boolean isEveryFieldOkay(){
        return !(dispatchClassCB()=='@' || tfPid.getText().isEmpty() || tfSample.getText().isEmpty());
    }
    
    private void log(Exception ex){
        log("Exception: "+ex.getLocalizedMessage());
    }
    private void log(String text){
        taLog.append("\n>>"+text);
    }
    
    private void startServer(String ip) throws IOException{
        ServerSocket ssAcc = new ServerSocket(Miner.PORT_ACC);
        ServerSocket ssGy = new ServerSocket(Miner.PORT_GYRO);
        ServerSocket ssCom = new ServerSocket(Miner.PORT_COM);
        lbConStatus.setText("Server started on IP: "+ip);
        lbConStatus.setForeground(Color.BLACK);
        //make sure to connect to port as order below.
        socketCom = ssCom.accept();
        log("Command Channel Establised.");
        socketAcc = ssAcc.accept();
        log("Accelerometer Channel Establised.");
        socketGy =ssGy.accept();
        log("Gyro Channel Establised.");
        lbConStatus.setText("Connected");
        lbConStatus.setForeground(Color.GREEN);
        inCom = socketCom.getInputStream();
        outCom = socketCom.getOutputStream();
        inAcc = socketAcc.getInputStream();
        inGy = socketGy.getInputStream();
        isConnected.set(true);
        listenForUpdate();
        updatePlots();
    }
    
    private void updatePlots(){
        new Thread(()->{
            while(true){
                accChartPanel.repaint();
                sleepms(10);
            }
        }).start();
        
        new Thread(()->{
            while(true){
                gyChartPanel.repaint();
                sleepms(10);
            }
        }).start();
    }
    
    private void listenForUpdate(){
        
        new Thread(() -> {
            while(true)
                listenSensor(inGy,gyData,gySeriesX,gySeriesY,gySeriesZ);
        }).start();
        
        new Thread(() -> {
            while(true)
                listenSensor(inAcc,accData,accSeriesX,accSeriesY,accSeriesZ);
        }).start();
        
        new Thread(() -> {
            while(true)
                listenCmd();
        }).start();
    }
    
    private void listenCmd(){
        byte[] comm;
        try {
            if(inCom.available()<2){ //two bytes
                sleepms(5);
                return;
            }
            comm = new byte[2];
            inCom.read(comm);
        } catch (IOException ex) {
            log(ex);
            return;
        }
        
        switch(new String(comm)){
            case "SA":
                btnCollect.setText("Stop");
                break;
            case "PA":
                btnCollect.setText("Collect");
                btnDiscard.setEnabled(true);
                btnSave.setEnabled(true);
                break;
            case "DX":
                disConnectClient();
                break;
        }
        
        
    }
    
    private void listenSensor(InputStream is,ArrayList<SensorData> arrList,XYSeries xs,XYSeries ys,XYSeries zs){
        byte[] readed;
        try {
            if(is.available()<16){ //4 floats
                sleepus(5);
                return;
            }
            readed = new byte[16];
            is.read(readed);
        } catch (IOException ex) {
            log(ex);
            return;
        }
        byte[] x = new byte[Float.BYTES];
        byte[] y = new byte[Float.BYTES];
        byte[] z = new byte[Float.BYTES];
        byte[] t = new byte[Float.BYTES];
        System.arraycopy(readed, 0, x, 0, Float.BYTES);
        System.arraycopy(readed, 4, y, 0, Float.BYTES);
        System.arraycopy(readed, 8, z, 0, Float.BYTES);
        System.arraycopy(readed, 12, t, 0, Float.BYTES);
        float tf = toFloat(t);
        float xf = toFloat(x);
        float yf = toFloat(y);
        float zf = toFloat(z);
        
        xs.add(tf,xf);
        ys.add(tf,yf);
        zs.add(tf,zf);
        arrList.add(new SensorData(tf,xf,yf,zf));
    }
    
    private boolean sendCommand(String com){
        try {
            outCom.write(com.getBytes());
        } catch (IOException ex) {
            log(ex);
            return false;
        }
        return true;
    }
    
    private void sleepms(int t){
        try {
            Thread.sleep(t);
        } catch (InterruptedException ex) {
            log(ex);
        }
    }
    
    private void sleepus(int t){
        try {
            Thread.sleep(0,1000*t);
        } catch (InterruptedException ex) {
            log(ex);
        }
    }
    
    private static float toFloat(byte[] bytes){
        return ByteBuffer.wrap(bytes).getFloat();
    }
    
    public static String getIP(){
        //https://stackoverflow.com/questions/8083479/java-getting-my-ip-address answer by roylaurie
        
        InetAddressValidator validator = InetAddressValidator.getInstance();
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                // filters out 127.0.0.1 and inactive interfaces
                if (iface.isLoopback() || !iface.isUp())
                    continue;
                
                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while(addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    String ip = addr.getHostAddress();
                    if(validator.isValidInet4Address(ip))
                        return ip;
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    
    private void clearAllData() {
        accData.clear();
        gyData.clear();
        accSeriesX.clear();
        accSeriesY.clear();
        accSeriesZ.clear();
        gySeriesX.clear();
        gySeriesY.clear();
        gySeriesZ.clear();
        btnSave.setEnabled(false);
        btnDiscard.setEnabled(false);
    }
    
    private void writeToFile(FileOutputStream fos) throws IOException {
        int dataSize = accData.size()<gyData.size()?accData.size():gyData.size();
        for(int i=0;i<dataSize;++i){
            String accSingleData = accData.get(i).getT()+","+accData.get(i).getX()+","+accData.get(i).getY()+","+accData.get(i).getZ();
            String gySingleData = gyData.get(i).getT()+","+gyData.get(i).getX()+","+gyData.get(i).getY()+","+gyData.get(i).getZ();
            fos.write((accSingleData+","+gySingleData+"\r\n").getBytes());
        }
        fos.close();
    }
    
    private float[] parseLimits() throws Exception{
        float[] ret = new float[2];
        float ll,ul;
        String[] sLim = tfTrim.getText().split("-");
        if(sLim.length<2){
            ll = Float.parseFloat(sLim[0]);
            ul = Float.POSITIVE_INFINITY;
        }
        else{
            ll = Float.parseFloat(sLim[0]);
            ul = Float.parseFloat(sLim[1]);
            if(ul<ll){
            ll = Float.parseFloat(sLim[1]);
            ul = Float.parseFloat(sLim[0]);
        }
        }
        ret[0]=ll;
        ret[1]=ul;
        return ret;
    }
    
    private void writeToFile(FileOutputStream fos, float ll, float ul) throws IOException {
        int dataSize = accData.size()<gyData.size()?accData.size():gyData.size();
        for(int i=0;i<dataSize;++i){
            float t = accData.get(i).t;
            if(t <ll || t>ul) continue;
            String accSingleData = accData.get(i).getT()+","+accData.get(i).getX()+","+accData.get(i).getY()+","+accData.get(i).getZ();
            String gySingleData = gyData.get(i).getT()+","+gyData.get(i).getX()+","+gyData.get(i).getY()+","+gyData.get(i).getZ();
            fos.write((accSingleData+","+gySingleData+"\r\n").getBytes());
        }
        fos.close();
    }

    private void disConnectClient() {
        
    }

    private void initServer(String ip) {
        //starting server in another thread
        /*if the flowing thread doesn't work try https://www.geeksforgeeks.org/swingworker-in-java/ */
        new Thread(() -> {
            sleepms(1000);
            try{
                startServer(ip);
            } catch(IOException ex){
                log(ex);
            }
        }).start();
    }
    
    private char dispatchClassCB(){
        return (char)(64+cbClass.getSelectedIndex());
    }
    
    private void initKeyListener() {
        InputMap im0 = getRootPane().getInputMap(javax.swing.JComponent.WHEN_FOCUSED);
        ActionMap am = getRootPane().getActionMap();
        im0.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE,0),"space_event");
        am.put("space_event", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                btnCollectActionPerformed(null);
            }
        }); 
    }
}
