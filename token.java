import javax.swing.JOptionPane;
import java.util.*;

class TokenBucketForm extends javax.swing.JFrame {
    
    class  Bucket{
    int passedToNetwork=0;
    int packetLoss=0;
    int token_no=0;
    int tokenCount;
    int max_size;
    
    Bucket(int max_size)
    {
        tokenCount=0;
        this.max_size=max_size;
    }

    synchronized void addToken(int n)
    {
        if(tokenCount<max_size)
        tokenCount+=n;
        else
        return;
    }
    
    synchronized void sendPackets(int n,int device)
    {       
        if(n<=tokenCount)
        {
            for(int i=0;i<n;i++)
                printer(device);
            passedToNetwork+=n;
            tokenCount-=n;
        }
        else
        {
            packetLoss+=n;
            jTextArea1.append("Total packet loss till now : "+packetLoss+"\n");
            jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());

        }

    }
    synchronized void printer(int device)
    {
        token_no++;
        jTextArea1.append("Token "+token_no+" from device "+device+" transmitted \n");
        jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());

    }

}

class newToken extends Thread{
    Bucket b;
    
    newToken(Bucket b)
    {
        this.b=b;
    }
    public void run()
    {
        while(true)
        {
            b.addToken(1);
            try
            {
                Thread.sleep(10);
            }
            catch(Exception e)
            {
                jTextArea1.append("Error while increasing the value of tokenCount \n");
                jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());
            }
        }
    }
}

    class externalDevice extends Thread{
    Bucket b;
    int device;
    externalDevice(Bucket b,int device){
        this.b=b;
        this.device=device;
    }

    public void run()
    {

        while(true)
        {   
               
            try
            {
                Thread.sleep(500 + (int) (Math.random()*300));
            }
            catch(Exception e)
            {}
            b.sendPackets(1 + (int) (Math.random()*(b.max_size)),device);    
        }
    }
}
    Bucket b;

    /**
     * Creates new form TokenBucketForm
     */
    public TokenBucketForm() {
        initComponents();
    }
                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Fira Sans Semi-Light", 0, 24)); // NOI18N
        jLabel1.setText("TOKEN BUCKET ALGORITHM");

        jLabel2.setText("Number of Devices");

        jLabel3.setText("Maximum Size of Intermediate Device");

        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jButton1.setText("Start");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Stop");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(182, 182, 182))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 167, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(84, 84, 84)))))
                .addGap(71, 71, 71))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        pack();
    }                     

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {     
    }                                           
    Thread incToken;
   

    int nod = 0;
    ArrayList<TokenBucketForm.externalDevice> dev=new ArrayList<TokenBucketForm.externalDevice>();
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        int maxSize = 0;
        try{
            nod = Integer.parseInt(jTextField1.getText());
            maxSize = Integer.parseInt(jTextField2.getText());
            
            b = new Bucket(maxSize);
            incToken=new newToken(b);
        
            
            for(int i=1;i<=nod;i++)
            {
            dev.add(new externalDevice(b, i));
            }
            
        
         try{
        jLabel5.setText("");
        jLabel4.setText("");
        jTextArea1.setText("");
            incToken.start();
            for(int i=0;i<nod;i++)
            dev.get(i).start();
            //pacGenrate.start();
        }
        catch(Exception e)
        {}
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "Enter valid Input!");
        }
        
    }                

                           

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        
        try{
            incToken.stop();
            //pacGenrate.stop(); 
            
            for(int i=0;i<nod;i++)
            dev.get(i).suspend();
        }
        catch(Exception e)
        {}
        jLabel4.setText("Total packets packets successfully transmitted : " + b.passedToNetwork);
        jLabel5.setText("Total packets lost : " + b.packetLoss);r
        jTextField1.setText("");
        jTextField2.setText("");
    }                                        

    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TokenBucketForm().setVisible(true);
            }
        });
        
    }                    
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;                
}
