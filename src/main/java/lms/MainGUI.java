package lms;

import java.awt.*;
import java.sql.*;
import java.util.*;
import java.util.regex.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class MainGUI extends JFrame {

    public MainGUI() {
        initComponents();
        
        newBookPageISBNInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent evt) {
                searchResult();
            }
            @Override
            public void removeUpdate(DocumentEvent evt) {
                searchResult();
            }
            @Override
            public void insertUpdate(DocumentEvent evt) {
                searchResult();
            }
            public void searchResult() {
                String isbn = newBookPageISBNInput.getText().trim().toUpperCase();
                if (!Utils.isValidISBN(isbn)) {
                    // invalid ISBN
                    return;
                }
                
                // valid ISBN
                Statement stmt = null;
                ArrayList<String> authors = new ArrayList<>();
                try{
                    stmt = Main.conn.createStatement();
                    String sql = "select * from bookinfo BI left join bookauthor BA on BI.isbn=BA.isbn where BI.isbn='" + isbn + "'";
                    ResultSet rs = stmt.executeQuery(sql);
                    String title = null, publisher = null, author = null;
                    int edition = 0, quantity = 0;
                    double cost = 0;
                    while (rs.next()) {
                        isbn = rs.getString("BI.ISBN");
                        title = rs.getString("title");
                        publisher = rs.getString("publisher");
                        author = rs.getString("author");
                        if (rs.wasNull()) {
                            // no author in this row
                            author = null;
                        }
                        edition = rs.getInt("edition");
                        cost = rs.getDouble("cost");
                        quantity = rs.getInt("quantity");
                        if (author != null) {
                            authors.add(author);
                        }
                    }
                    rs.close();
                    stmt.close();
                    Book book = new Book(isbn, title, publisher, edition, cost, quantity, authors);
                    
                    if (title != null) {
                        // update other fields
                        newBookPageTitleInput.setText(title);
                        newBookPageAuthorInput.setText(book.joinAuthors(", "));
                        newBookPagePublisherInput.getEditor().setItem(publisher);
                        newBookPageEditionInput.setText(Integer.toString(edition));
                        newBookPageCostInput.setText(Double.toString(cost));
                        newBookPageQuantityInput.setText(Integer.toString(quantity));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try{
                        if (stmt != null) stmt.close();
                    }catch(SQLException se2){}
                }
            }
        });
        
        newCustomerPageHKIDInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent evt) {
                searchResult();
            }
            @Override
            public void removeUpdate(DocumentEvent evt) {
                searchResult();
            }
            @Override
            public void insertUpdate(DocumentEvent evt) {
                searchResult();
            }
            public void searchResult() {
                String hkid = newCustomerPageHKIDInput.getText().trim().toUpperCase();
                if (!Utils.isValidHKID(hkid)) {
                    // invalid HKID
                    return;
                }
                
                // valid HKID
                Statement stmt = null;
                try{
                    stmt = Main.conn.createStatement();
                    String sql = "select * from userinfo where hkid='" + hkid + "'";
                    ResultSet rs = stmt.executeQuery(sql);
                    String name = null, email = null, phone = null, gender = null, address = null;
                    while (rs.next()) {
                        hkid = rs.getString("HKID");
                        name = rs.getString("name");
                        email = rs.getString("email");
                        if (rs.wasNull()) {
                            // no phone in this row
                            email = "";
                        }
                        phone = rs.getString("phone");
                        if (rs.wasNull()) {
                            // no phone in this row
                            phone = "";
                        }
                        gender = rs.getString("gender");
                        address = rs.getString("address");
                    }
                    rs.close();
                    stmt.close();
                    
                    if (name != null) {
                        // update other fields
                        newCustomerPageNameInput.setText(name);
                        newCustomerPageEmailInput.setText(email);
                        newCustomerPagePhoneInput.setText(phone);
                        newCustomerPageMaleRadio.setSelected("M".equals(gender));
                        newCustomerPageFemaleRadio.setSelected("F".equals(gender));
                        newCustomerPageAddressInput.setText(address);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try{
                        if (stmt != null) stmt.close();
                    }catch(SQLException se2){}
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        newCustomerPageGenderBtGp = new javax.swing.ButtonGroup();
        logoutBt = new javax.swing.JButton();
        pageTab = new javax.swing.JTabbedPane();
        allBooksTab = new javax.swing.JPanel();
        allBooksRefreshBt = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        allBooksTable = new javax.swing.JTable();
        searchBookTab = new javax.swing.JPanel();
        searchBookPage = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        searchBookPageSearchBt = new javax.swing.JButton();
        searchBookPageRecordBt = new javax.swing.JButton();
        searchBookPageISBNInput = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        searchBookPageTitleLabel = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        searchBookPageAuthorLabel = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        searchBookPagePublisherLabel = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        searchBookPageEditionLabel = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        searchBookPageCostLabel = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        searchBookPageQuantityLabel = new javax.swing.JLabel();
        bookBorrowRecordPage = new javax.swing.JPanel();
        bookBorrowRecordPageBackBt = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        bookBorrowRecordTable = new javax.swing.JTable();
        searchCustomerTab = new javax.swing.JPanel();
        borrowTab = new javax.swing.JPanel();
        returnTab = new javax.swing.JPanel();
        newBookTab = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        newBookPageISBNInput = new javax.swing.JTextField();
        newBookPageTitleInput = new javax.swing.JTextField();
        newBookPageAuthorInput = new javax.swing.JTextField();
        newBookPagePublisherInput = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        newBookPageEditionInput = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        newBookPageCostInput = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        newBookPageQuantityInput = new javax.swing.JTextField();
        newBookPageSubmitBt = new javax.swing.JButton();
        newCustomerTab = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        newCustomerPageHKIDInput = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        newCustomerPageNameInput = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        newCustomerPageEmailInput = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        newCustomerPagePhoneInput = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        newCustomerPageAddressInput = new javax.swing.JTextField();
        newCustomerPageMaleRadio = new javax.swing.JRadioButton();
        newCustomerPageFemaleRadio = new javax.swing.JRadioButton();
        newCustomerPageSubmitBt = new javax.swing.JButton();
        reportTab = new javax.swing.JPanel();
        settingTab = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        settingPageDateInput = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(Main.PROGRAM_NAME);
        setMaximumSize(new java.awt.Dimension(1000, 650));
        setName("mainFrame"); // NOI18N
        setResizable(false);

        logoutBt.setText("登出");
        logoutBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtActionPerformed(evt);
            }
        });

        pageTab.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                pageTabStateChanged(evt);
            }
        });

        allBooksRefreshBt.setText("更新");
        allBooksRefreshBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allBooksRefreshBtActionPerformed(evt);
            }
        });

        allBooksTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        allBooksTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        allBooksTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        allBooksTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                allBooksTableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(allBooksTable);

        javax.swing.GroupLayout allBooksTabLayout = new javax.swing.GroupLayout(allBooksTab);
        allBooksTab.setLayout(allBooksTabLayout);
        allBooksTabLayout.setHorizontalGroup(
            allBooksTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(allBooksTabLayout.createSequentialGroup()
                .addComponent(allBooksRefreshBt, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        allBooksTabLayout.setVerticalGroup(
            allBooksTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(allBooksTabLayout.createSequentialGroup()
                .addComponent(allBooksRefreshBt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE))
        );

        pageTab.addTab("所有圖書", allBooksTab);

        searchBookTab.setLayout(new java.awt.CardLayout());

        jLabel8.setText("ISBN:");

        searchBookPageSearchBt.setText("搜尋");
        searchBookPageSearchBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBookPageSearchBtActionPerformed(evt);
            }
        });

        searchBookPageRecordBt.setText("外借記錄");
        searchBookPageRecordBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBookPageRecordBtActionPerformed(evt);
            }
        });

        searchBookPageISBNInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchBookPageISBNInputKeyPressed(evt);
            }
        });

        jLabel9.setText("書名:");

        searchBookPageTitleLabel.setText("title");

        jLabel11.setText("作者:");

        searchBookPageAuthorLabel.setText("authors");

        jLabel13.setText("出版社:");

        searchBookPagePublisherLabel.setText("publisher");

        jLabel15.setText("版本:");

        searchBookPageEditionLabel.setText("edition");

        jLabel17.setText("成本價 (HKD):");

        searchBookPageCostLabel.setText("cost");

        jLabel19.setText("存貨:");

        searchBookPageQuantityLabel.setText("quantity");

        javax.swing.GroupLayout searchBookPageLayout = new javax.swing.GroupLayout(searchBookPage);
        searchBookPage.setLayout(searchBookPageLayout);
        searchBookPageLayout.setHorizontalGroup(
            searchBookPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchBookPageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchBookPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchBookPageLayout.createSequentialGroup()
                        .addGroup(searchBookPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(searchBookPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchBookPageISBNInput)
                            .addGroup(searchBookPageLayout.createSequentialGroup()
                                .addGroup(searchBookPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(searchBookPageTitleLabel)
                                    .addComponent(searchBookPageAuthorLabel)
                                    .addComponent(searchBookPagePublisherLabel)
                                    .addComponent(searchBookPageEditionLabel)
                                    .addComponent(searchBookPageCostLabel))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(searchBookPageLayout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(searchBookPageQuantityLabel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchBookPageLayout.createSequentialGroup()
                .addContainerGap(399, Short.MAX_VALUE)
                .addComponent(searchBookPageSearchBt, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(searchBookPageRecordBt)
                .addGap(414, 414, 414))
        );
        searchBookPageLayout.setVerticalGroup(
            searchBookPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchBookPageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchBookPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(searchBookPageISBNInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(searchBookPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(searchBookPageTitleLabel))
                .addGap(18, 18, 18)
                .addGroup(searchBookPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(searchBookPageAuthorLabel))
                .addGap(18, 18, 18)
                .addGroup(searchBookPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(searchBookPagePublisherLabel))
                .addGap(18, 18, 18)
                .addGroup(searchBookPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(searchBookPageEditionLabel))
                .addGap(18, 18, 18)
                .addGroup(searchBookPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(searchBookPageCostLabel))
                .addGap(18, 18, 18)
                .addGroup(searchBookPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(searchBookPageQuantityLabel))
                .addGap(18, 18, 18)
                .addGroup(searchBookPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchBookPageRecordBt)
                    .addComponent(searchBookPageSearchBt))
                .addContainerGap(292, Short.MAX_VALUE))
        );

        searchBookTab.add(searchBookPage, "searchBookPage");

        bookBorrowRecordPageBackBt.setText("返回");
        bookBorrowRecordPageBackBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookBorrowRecordPageBackBtActionPerformed(evt);
            }
        });

        bookBorrowRecordTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        bookBorrowRecordTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        bookBorrowRecordTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane2.setViewportView(bookBorrowRecordTable);

        javax.swing.GroupLayout bookBorrowRecordPageLayout = new javax.swing.GroupLayout(bookBorrowRecordPage);
        bookBorrowRecordPage.setLayout(bookBorrowRecordPageLayout);
        bookBorrowRecordPageLayout.setHorizontalGroup(
            bookBorrowRecordPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookBorrowRecordPageLayout.createSequentialGroup()
                .addComponent(bookBorrowRecordPageBackBt, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        bookBorrowRecordPageLayout.setVerticalGroup(
            bookBorrowRecordPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookBorrowRecordPageLayout.createSequentialGroup()
                .addComponent(bookBorrowRecordPageBackBt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE))
        );

        searchBookTab.add(bookBorrowRecordPage, "bookBorrowRecordPage");

        pageTab.addTab("圖書搜尋", searchBookTab);

        javax.swing.GroupLayout searchCustomerTabLayout = new javax.swing.GroupLayout(searchCustomerTab);
        searchCustomerTab.setLayout(searchCustomerTabLayout);
        searchCustomerTabLayout.setHorizontalGroup(
            searchCustomerTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        searchCustomerTabLayout.setVerticalGroup(
            searchCustomerTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 581, Short.MAX_VALUE)
        );

        pageTab.addTab("客戶搜尋", searchCustomerTab);

        javax.swing.GroupLayout borrowTabLayout = new javax.swing.GroupLayout(borrowTab);
        borrowTab.setLayout(borrowTabLayout);
        borrowTabLayout.setHorizontalGroup(
            borrowTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        borrowTabLayout.setVerticalGroup(
            borrowTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 581, Short.MAX_VALUE)
        );

        pageTab.addTab("借書", borrowTab);

        javax.swing.GroupLayout returnTabLayout = new javax.swing.GroupLayout(returnTab);
        returnTab.setLayout(returnTabLayout);
        returnTabLayout.setHorizontalGroup(
            returnTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        returnTabLayout.setVerticalGroup(
            returnTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 581, Short.MAX_VALUE)
        );

        pageTab.addTab("還書", returnTab);

        jLabel1.setText("ISBN:");

        jLabel2.setText("書名:");

        jLabel3.setText("作者 (用逗號分隔):");

        jLabel4.setText("出版社:");

        newBookPageISBNInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                newBookPageISBNInputKeyPressed(evt);
            }
        });

        newBookPageTitleInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                newBookPageTitleInputKeyPressed(evt);
            }
        });

        newBookPageAuthorInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                newBookPageAuthorInputKeyPressed(evt);
            }
        });

        newBookPagePublisherInput.setEditable(true);

        jLabel5.setText("版本:");

        newBookPageEditionInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                newBookPageEditionInputKeyPressed(evt);
            }
        });

        jLabel6.setText("成本價 (HKD):");

        newBookPageCostInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                newBookPageCostInputKeyPressed(evt);
            }
        });

        jLabel7.setText("存貨數量:");

        newBookPageQuantityInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                newBookPageQuantityInputKeyPressed(evt);
            }
        });

        newBookPageSubmitBt.setText("確定");
        newBookPageSubmitBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newBookPageSubmitBtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout newBookTabLayout = new javax.swing.GroupLayout(newBookTab);
        newBookTab.setLayout(newBookTabLayout);
        newBookTabLayout.setHorizontalGroup(
            newBookTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newBookTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(newBookTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newBookTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newBookPageAuthorInput)
                    .addComponent(newBookPageTitleInput)
                    .addComponent(newBookPageISBNInput)
                    .addComponent(newBookPagePublisherInput, 0, 850, Short.MAX_VALUE)
                    .addComponent(newBookPageEditionInput)
                    .addComponent(newBookPageCostInput)
                    .addComponent(newBookPageQuantityInput))
                .addContainerGap())
            .addGroup(newBookTabLayout.createSequentialGroup()
                .addGap(464, 464, 464)
                .addComponent(newBookPageSubmitBt, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(463, Short.MAX_VALUE))
        );
        newBookTabLayout.setVerticalGroup(
            newBookTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newBookTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(newBookTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(newBookPageISBNInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(newBookTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(newBookPageTitleInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(newBookTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(newBookPageAuthorInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(newBookTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(newBookPagePublisherInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(newBookTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(newBookPageEditionInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(newBookTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(newBookPageCostInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(newBookTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(newBookPageQuantityInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(newBookPageSubmitBt)
                .addContainerGap(196, Short.MAX_VALUE))
        );

        pageTab.addTab("新圖書/修改", newBookTab);

        jLabel12.setText("HKID:");

        newCustomerPageHKIDInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                newCustomerPageHKIDInputKeyPressed(evt);
            }
        });

        jLabel14.setText("姓名:");

        newCustomerPageNameInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                newCustomerPageNameInputKeyPressed(evt);
            }
        });

        jLabel16.setText("Email:");

        newCustomerPageEmailInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                newCustomerPageEmailInputKeyPressed(evt);
            }
        });

        jLabel18.setText("電話號碼:");

        newCustomerPagePhoneInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                newCustomerPagePhoneInputKeyPressed(evt);
            }
        });

        jLabel20.setText("性別:");

        jLabel21.setText("住址:");

        newCustomerPageAddressInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                newCustomerPageAddressInputKeyPressed(evt);
            }
        });

        newCustomerPageGenderBtGp.add(newCustomerPageMaleRadio);
        newCustomerPageMaleRadio.setText("男");

        newCustomerPageGenderBtGp.add(newCustomerPageFemaleRadio);
        newCustomerPageFemaleRadio.setText("女");

        newCustomerPageSubmitBt.setText("確定");
        newCustomerPageSubmitBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newCustomerPageSubmitBtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout newCustomerTabLayout = new javax.swing.GroupLayout(newCustomerTab);
        newCustomerTab.setLayout(newCustomerTabLayout);
        newCustomerTabLayout.setHorizontalGroup(
            newCustomerTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newCustomerTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(newCustomerTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(newCustomerTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newCustomerPageHKIDInput)
                    .addComponent(newCustomerPageNameInput)
                    .addComponent(newCustomerPageEmailInput)
                    .addComponent(newCustomerPagePhoneInput)
                    .addGroup(newCustomerTabLayout.createSequentialGroup()
                        .addComponent(newCustomerPageMaleRadio)
                        .addGap(18, 18, 18)
                        .addComponent(newCustomerPageFemaleRadio)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(newCustomerPageAddressInput))
                .addContainerGap())
            .addGroup(newCustomerTabLayout.createSequentialGroup()
                .addGap(461, 461, 461)
                .addComponent(newCustomerPageSubmitBt, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(466, Short.MAX_VALUE))
        );
        newCustomerTabLayout.setVerticalGroup(
            newCustomerTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newCustomerTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(newCustomerTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(newCustomerPageHKIDInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(newCustomerTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(newCustomerPageNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(newCustomerTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(newCustomerPageEmailInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(newCustomerTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(newCustomerPagePhoneInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(newCustomerTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(newCustomerPageMaleRadio)
                    .addComponent(newCustomerPageFemaleRadio))
                .addGap(18, 18, 18)
                .addGroup(newCustomerTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(newCustomerPageAddressInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(newCustomerPageSubmitBt)
                .addContainerGap(261, Short.MAX_VALUE))
        );

        pageTab.addTab("客戶登記/修改", newCustomerTab);

        javax.swing.GroupLayout reportTabLayout = new javax.swing.GroupLayout(reportTab);
        reportTab.setLayout(reportTabLayout);
        reportTabLayout.setHorizontalGroup(
            reportTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        reportTabLayout.setVerticalGroup(
            reportTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 581, Short.MAX_VALUE)
        );

        pageTab.addTab("Report", reportTab);

        jLabel10.setText("系統日期 (yyyy-MM-dd):");

        settingPageDateInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                settingPageDateInputKeyPressed(evt);
            }
        });

        jButton1.setText("完成");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout settingTabLayout = new javax.swing.GroupLayout(settingTab);
        settingTab.setLayout(settingTabLayout);
        settingTabLayout.setHorizontalGroup(
            settingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(settingPageDateInput, javax.swing.GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        settingTabLayout.setVerticalGroup(
            settingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(settingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(settingPageDateInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(547, Short.MAX_VALUE))
        );

        pageTab.addTab("設定", settingTab);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(logoutBt, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(pageTab)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(logoutBt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pageTab, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtActionPerformed
        try {
            if (Main.conn != null) Main.conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        Main.mainGUI.setVisible(false);
        
        // clear preSettingGUI's input box (username, password)
        Main.preSettingGUI.resetInputBox();
        
        Main.preSettingGUI.setVisible(true);
    }//GEN-LAST:event_logoutBtActionPerformed

    private void allBooksRefreshBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allBooksRefreshBtActionPerformed
        allBooksRefresh();
    }//GEN-LAST:event_allBooksRefreshBtActionPerformed

    private void allBooksTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_allBooksTableMousePressed
        JTable table =(JTable) evt.getSource();
        Point point = evt.getPoint();
        int row = table.rowAtPoint(point);
        if (evt.getClickCount() == 2 && table.getSelectedRow() != -1) {
            String isbn = table.getValueAt(row, 0).toString();
            
            // change page to searchBookPage and show search result of isbn
            searchBookPageISBNInput.setText(isbn);
            searchBookPageShowRecord();
            pageTab.setSelectedIndex(1);
        }
    }//GEN-LAST:event_allBooksTableMousePressed

    private void pageTabStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_pageTabStateChanged
        if (pageTab.getSelectedComponent() == newBookTab) {
            newBookPageISBNInput.requestFocus();
        } else if (pageTab.getSelectedComponent() == settingTab) {
            // update date
            settingPageDateInput.setText(Main.fakeTime.formatDate());
        }
    }//GEN-LAST:event_pageTabStateChanged

    private void newBookPageISBNInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_newBookPageISBNInputKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            String isbn = newBookPageISBNInput.getText().trim().toUpperCase();
            if (Utils.isValidISBN(isbn)) {
                newBookPageTitleInput.requestFocus();
            } else if (!isbn.equals("")) {
                JOptionPane.showMessageDialog(null, "無效的ISBN。");
            }
        }
    }//GEN-LAST:event_newBookPageISBNInputKeyPressed

    private void newBookPageTitleInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_newBookPageTitleInputKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            newBookPageAuthorInput.requestFocus();
        }
    }//GEN-LAST:event_newBookPageTitleInputKeyPressed

    private void newBookPageAuthorInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_newBookPageAuthorInputKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            newBookPagePublisherInput.requestFocus();
        }
    }//GEN-LAST:event_newBookPageAuthorInputKeyPressed

    private void newBookPageEditionInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_newBookPageEditionInputKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            newBookPageCostInput.requestFocus();
        }
    }//GEN-LAST:event_newBookPageEditionInputKeyPressed

    private void newBookPageCostInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_newBookPageCostInputKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            newBookPageQuantityInput.requestFocus();
        }
    }//GEN-LAST:event_newBookPageCostInputKeyPressed

    private void newBookPageQuantityInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_newBookPageQuantityInputKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            newBookPageAddBook();
        }
    }//GEN-LAST:event_newBookPageQuantityInputKeyPressed

    private void newBookPageSubmitBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newBookPageSubmitBtActionPerformed
        newBookPageAddBook();
    }//GEN-LAST:event_newBookPageSubmitBtActionPerformed

    private void searchBookPageRecordBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBookPageRecordBtActionPerformed
        searchBookPageShowRecord();
    }//GEN-LAST:event_searchBookPageRecordBtActionPerformed

    private void bookBorrowRecordPageBackBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookBorrowRecordPageBackBtActionPerformed
        CardLayout card = (CardLayout)searchBookTab.getLayout();
		card.show(searchBookTab, "searchBookPage");
    }//GEN-LAST:event_bookBorrowRecordPageBackBtActionPerformed

    private void searchBookPageSearchBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBookPageSearchBtActionPerformed
        searchBookPageSearch();
    }//GEN-LAST:event_searchBookPageSearchBtActionPerformed

    private void searchBookPageISBNInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchBookPageISBNInputKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            searchBookPageSearch();
        }
    }//GEN-LAST:event_searchBookPageISBNInputKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        changeFakeTime();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void settingPageDateInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_settingPageDateInputKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            changeFakeTime();
        }
    }//GEN-LAST:event_settingPageDateInputKeyPressed

    private void newCustomerPageSubmitBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newCustomerPageSubmitBtActionPerformed
        newCustomerPageAddCustomer();
    }//GEN-LAST:event_newCustomerPageSubmitBtActionPerformed

    private void newCustomerPageHKIDInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_newCustomerPageHKIDInputKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            String hkid = newCustomerPageHKIDInput.getText().trim().toUpperCase();
            if (Utils.isValidHKID(hkid)) {
                newCustomerPageNameInput.requestFocus();
            } else if (!hkid.equals("")) {
                JOptionPane.showMessageDialog(null, "無效的HKID。");
            }
        }
    }//GEN-LAST:event_newCustomerPageHKIDInputKeyPressed

    private void newCustomerPageNameInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_newCustomerPageNameInputKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            newCustomerPageEmailInput.requestFocus();
        }
    }//GEN-LAST:event_newCustomerPageNameInputKeyPressed

    private void newCustomerPageEmailInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_newCustomerPageEmailInputKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            newCustomerPagePhoneInput.requestFocus();
        }
    }//GEN-LAST:event_newCustomerPageEmailInputKeyPressed

    private void newCustomerPageAddressInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_newCustomerPageAddressInputKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            newCustomerPageAddCustomer();
        }
    }//GEN-LAST:event_newCustomerPageAddressInputKeyPressed

    private void newCustomerPagePhoneInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_newCustomerPagePhoneInputKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            newCustomerPageAddressInput.requestFocus();
        }
    }//GEN-LAST:event_newCustomerPagePhoneInputKeyPressed
    
    public void init() {
        // remove useless label text
        searchBookPageTitleLabel.setText("");
        searchBookPageAuthorLabel.setText("");
        searchBookPagePublisherLabel.setText("");
        searchBookPageEditionLabel.setText("");
        searchBookPageCostLabel.setText("");
        searchBookPageQuantityLabel.setText("");
        
        // show book info on allBooksTable
        allBooksRefresh();
        
        // set editable false to tables
        allBooksTable.setDefaultEditor(Object.class, null);
        bookBorrowRecordTable.setDefaultEditor(Object.class, null);
        
        // select first tab
        pageTab.setSelectedIndex(0);
    }
    
    public void allBooksRefresh() {
        // clear table
        DefaultTableModel allBooksTableModel = (DefaultTableModel) allBooksTable.getModel();
        allBooksTableModel.setColumnCount(0);
        allBooksTableModel.addColumn("ISBN");
        allBooksTableModel.addColumn("書名");
        allBooksTableModel.addColumn("出版社");
        allBooksTableModel.addColumn("作者");
        allBooksTableModel.addColumn("版本");
        allBooksTableModel.addColumn("成本價");
        allBooksTableModel.addColumn("存貨");
        allBooksTableModel.setRowCount(0);
        
        Statement stmt = null;
        ArrayList<Book> books = new ArrayList<>();
        Hashtable<String, ArrayList<String>> tempAuthors = new Hashtable<>();
        try{
            stmt = Main.conn.createStatement();
            String sql = "select * from bookinfo BI left join bookauthor BA on BI.isbn=BA.isbn order by BI.title";
            ResultSet rs = stmt.executeQuery(sql);
            String isbn, title, publisher, author;
            int edition, quantity;
            double cost;
            while (rs.next()) {
                isbn = rs.getString("BI.ISBN");
                title = rs.getString("title");
                publisher = rs.getString("publisher");
                author = rs.getString("author");
                if (rs.wasNull()) {
                    // no author in this row
                    author = null;
                }
                edition = rs.getInt("edition");
                cost = rs.getDouble("cost");
                quantity = rs.getInt("quantity");
                if (tempAuthors.containsKey(isbn)) {
                    // old record, only author is new
                    if (author != null) {
                        tempAuthors.get(isbn).add(author);
                    }
                } else {
                    // new record
                    tempAuthors.put(isbn, new ArrayList<>());
                    if (author != null) {
                        tempAuthors.get(isbn).add(author);
                    }
                    books.add(new Book(isbn, title, publisher, edition, cost, quantity, tempAuthors.get(isbn)));
                }
            }
            rs.close();
            stmt.close();
            
            // add rows to table
            for (int i = 0, n = books.size(); i < n; i++) {
                allBooksTableModel.addRow(books.get(i).getRow());
            }
            
            // update newBookPagePublisherInput choices
            newBookPagePublisherInput.setModel(new DefaultComboBoxModel<>(Utils.publisherChoices()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if (stmt != null) stmt.close();
            }catch(SQLException se2){}
        }
    }
    
    private void changeFakeTime() {
        if (!Main.fakeTime.setFakeTime(settingPageDateInput.getText().trim())) {
            // invalid format
            JOptionPane.showMessageDialog(null, "格式錯誤。");
        }
    }
    
    private void searchBookPageShowRecord() {
        if (!searchBookPageSearch()) {
            return;
        }
        
        String isbn = searchBookPageISBNInput.getText().trim().toUpperCase();
        // TODO: show record according to ISBN
        
        CardLayout card = (CardLayout)searchBookTab.getLayout();
		card.show(searchBookTab, "bookBorrowRecordPage");
    }
    
    private boolean searchBookPageSearch() {
        String isbn = searchBookPageISBNInput.getText().trim().toUpperCase();
        if (!Utils.isValidISBN(isbn)) {
            // invalid ISBN
            if (!isbn.equals("")) {
                JOptionPane.showMessageDialog(null, "無效的ISBN。");
            }
            return false;
        }
        
        Statement stmt = null;
        ArrayList<String> authors = new ArrayList<>();
        try{
            stmt = Main.conn.createStatement();
            String sql = "select * from bookinfo BI left join bookauthor BA on BI.isbn=BA.isbn where BI.isbn='" + isbn + "'";
            ResultSet rs = stmt.executeQuery(sql);
            String title = null, publisher = null, author = null;
            int edition = 0, quantity = 0;
            double cost = 0;
            while (rs.next()) {
                isbn = rs.getString("BI.ISBN");
                title = rs.getString("title");
                publisher = rs.getString("publisher");
                author = rs.getString("author");
                if (rs.wasNull()) {
                    // no author in this row
                    author = null;
                }
                edition = rs.getInt("edition");
                cost = rs.getDouble("cost");
                quantity = rs.getInt("quantity");
                if (author != null) {
                    authors.add(author);
                }
            }
            rs.close();
            stmt.close();
            Book book = new Book(isbn, title, publisher, edition, cost, quantity, authors);

            if (title == null) {
                JOptionPane.showMessageDialog(null, "找不到此書。");
                return false;
            }
            // update info label
            searchBookPageTitleLabel.setText(title);
            searchBookPageAuthorLabel.setText(book.joinAuthors(", "));
            searchBookPagePublisherLabel.setText(publisher);
            searchBookPageEditionLabel.setText(Integer.toString(edition));
            searchBookPageCostLabel.setText(Double.toString(cost));
            searchBookPageQuantityLabel.setText(Integer.toString(quantity));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try{
                if (stmt != null) stmt.close();
            }catch(SQLException se2){}
        }
        return true;
    }
    
    private void newCustomerPageAddCustomer() {
        String hkid = newCustomerPageHKIDInput.getText().trim().toUpperCase();
        String name = newCustomerPageNameInput.getText().trim();
        String email = newCustomerPageEmailInput.getText().trim();
        String phone = newCustomerPagePhoneInput.getText().trim();
        boolean m = newCustomerPageMaleRadio.isSelected();
        boolean f = newCustomerPageFemaleRadio.isSelected();
        String address = newCustomerPageAddressInput.getText().trim();
        
        
        if (!Utils.isValidHKID(hkid)) {
            // invalid HKID
            JOptionPane.showMessageDialog(null, "無效的HKID。");
            return;
        }
        if (name.equals("")) {
            JOptionPane.showMessageDialog(null, "請輸入姓名。");
            return;
        }
        if (!email.equals("") && !Pattern.matches("^(.+)@(.+)$", email)) {
            JOptionPane.showMessageDialog(null, "Email格式錯誤。");
            return;
        }
        if (!phone.equals("") && !Pattern.matches("^\\d{8}$", phone)) {
            JOptionPane.showMessageDialog(null, "電話號碼格式錯誤。");
            return;
        }
        if (!m && !f) {
            JOptionPane.showMessageDialog(null, "請選擇性別。");
            return;
        }
        if (address.equals("")) {
            JOptionPane.showMessageDialog(null, "請輸入住址。");
            return;
        }


        // valid data
        String gender = (m ? "M" : "F");
        
        email = (email.equals("") ? "NULL" : "'" + email + "'");
        phone = (phone.equals("") ? "NULL" : "'" + phone + "'");
        
        Statement stmt = null;
        boolean HKIDExist = false;
        // check if HKID already exists in table
        try{
            stmt = Main.conn.createStatement();
            String sql = "select hkid from userinfo where hkid='" + hkid + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                HKIDExist = true;
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if (stmt != null) stmt.close();
            }catch(SQLException se2){}
        }
        
        if (HKIDExist) {
            // existing customer
            try{
                stmt = Main.conn.createStatement();
                String sql = "update userinfo set name = '" + name + "', email = " + email + ", phone = " + phone + ", gender = '" + gender + "', address = '" + address + "' where hkid = '" + hkid + "'";
                stmt.executeUpdate(sql);
                stmt.close();

                // clear the input box
                newCustomerPageHKIDInput.setText("");
                newCustomerPageNameInput.setText("");
                newCustomerPageEmailInput.setText("");
                newCustomerPagePhoneInput.setText("");
                newCustomerPageGenderBtGp.clearSelection();
                newCustomerPageAddressInput.setText("");
                newCustomerPageHKIDInput.requestFocus();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try{
                    if (stmt != null) stmt.close();
                }catch(SQLException se2){}
            }
        } else {
            // new customer
            try{
                stmt = Main.conn.createStatement();
                String sql = "insert into userinfo values ('" + hkid + "', '" + name + "', " + email + ", " + phone + ", '" + gender + "', '" + address + "')";
                stmt.executeUpdate(sql);
                stmt.close();

                // clear the input box
                newCustomerPageHKIDInput.setText("");
                newCustomerPageNameInput.setText("");
                newCustomerPageEmailInput.setText("");
                newCustomerPagePhoneInput.setText("");
                newCustomerPageGenderBtGp.clearSelection();
                newCustomerPageAddressInput.setText("");
                newCustomerPageHKIDInput.requestFocus();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try{
                    if (stmt != null) stmt.close();
                }catch(SQLException se2){}
            }
        }
    }
    
    private void newBookPageAddBook() {
        String isbn = newBookPageISBNInput.getText().trim().toUpperCase();
        String title = newBookPageTitleInput.getText().trim();
        String authorStr = newBookPageAuthorInput.getText().trim();
        String publisher = newBookPagePublisherInput.getEditor().getItem().toString().trim();
        int edition, quantity;
        double cost;
        
        
        if (!Utils.isValidISBN(isbn)) {
            // invalid ISBN
            JOptionPane.showMessageDialog(null, "無效的ISBN。");
            return;
        }
        if (title.equals("")) {
            JOptionPane.showMessageDialog(null, "請輸入書名。");
            return;
        }
        if (newBookPageEditionInput.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "請輸入版本。");
            return;
        }
        if (!Utils.isInt(newBookPageEditionInput.getText().trim())) {
            JOptionPane.showMessageDialog(null, "版本必須是整數。");
            return;
        }
        if (newBookPageCostInput.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "請輸入成本價。");
            return;
        }
        if (!Utils.isDouble(newBookPageCostInput.getText().trim())) {
            JOptionPane.showMessageDialog(null, "成本價必須是數字。");
            return;
        }
        if (newBookPageQuantityInput.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "請輸入存貨數量。");
            return;
        }
        if (!Utils.isInt(newBookPageQuantityInput.getText().trim())) {
            JOptionPane.showMessageDialog(null, "存貨數量必須是整數。");
            return;
        }
        
        // valid data
        edition = Integer.parseInt(newBookPageEditionInput.getText().trim());
        cost = Double.parseDouble(newBookPageCostInput.getText().trim());
        quantity = Integer.parseInt(newBookPageQuantityInput.getText().trim());
        
        String[] authors = (authorStr.equals("") ? new String[] {} : authorStr.split(",|, |，"));
        for (int i = 0, n = authors.length; i < n; i++) {
            authors[i] = authors[i].trim();
        }
        
        Statement stmt = null;
        boolean ISBNExist = false;
        // check if ISBN already exists in table
        try{
            stmt = Main.conn.createStatement();
            String sql = "select isbn from bookinfo where isbn='" + isbn + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ISBNExist = true;
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if (stmt != null) stmt.close();
            }catch(SQLException se2){}
        }
        
        if (ISBNExist) {
            // existing book
            try{
                stmt = Main.conn.createStatement();
                String sql = "update bookinfo set title = '" + title + "', publisher = '" + publisher + "', edition = " + edition + ", cost = " + cost + ", quantity = " + quantity + " where isbn = '" + isbn + "'";
                stmt.executeUpdate(sql);
                sql = "delete from bookauthor where isbn = '" + isbn + "'";
                stmt.executeUpdate(sql);
                for (int i = 0, n = authors.length; i < n; i++) {
                    sql = "insert into bookauthor values ('" + isbn + "', '" + authors[i] + "')";
                    stmt.executeUpdate(sql);
                }
                stmt.close();

                // clear the input box
                newBookPageISBNInput.setText("");
                newBookPageTitleInput.setText("");
                newBookPageAuthorInput.setText("");
                newBookPagePublisherInput.setModel(new DefaultComboBoxModel<>(Utils.publisherChoices()));
                newBookPagePublisherInput.getEditor().setItem("");
                newBookPageEditionInput.setText("");
                newBookPageCostInput.setText("");
                newBookPageQuantityInput.setText("");
                newBookPageISBNInput.requestFocus();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try{
                    if (stmt != null) stmt.close();
                }catch(SQLException se2){}
            }
        } else {
            // new book
            try{
                stmt = Main.conn.createStatement();
                String sql = "insert into bookinfo values ('" + isbn + "', '" + title + "', '" + publisher + "', " + edition + ", " + cost + ", " + quantity + ")";
                stmt.executeUpdate(sql);
                for (int i = 0, n = authors.length; i < n; i++) {
                    sql = "insert into bookauthor values ('" + isbn + "', '" + authors[i] + "')";
                    stmt.executeUpdate(sql);
                }
                stmt.close();

                // clear the input box
                newBookPageISBNInput.setText("");
                newBookPageTitleInput.setText("");
                newBookPageAuthorInput.setText("");
                newBookPagePublisherInput.setModel(new DefaultComboBoxModel<>(Utils.publisherChoices()));
                newBookPagePublisherInput.getEditor().setItem("");
                newBookPageEditionInput.setText("");
                newBookPageCostInput.setText("");
                newBookPageQuantityInput.setText("");
                newBookPageISBNInput.requestFocus();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try{
                    if (stmt != null) stmt.close();
                }catch(SQLException se2){}
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton allBooksRefreshBt;
    private javax.swing.JPanel allBooksTab;
    private javax.swing.JTable allBooksTable;
    private javax.swing.JPanel bookBorrowRecordPage;
    private javax.swing.JButton bookBorrowRecordPageBackBt;
    private javax.swing.JTable bookBorrowRecordTable;
    private javax.swing.JPanel borrowTab;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton logoutBt;
    private javax.swing.JTextField newBookPageAuthorInput;
    private javax.swing.JTextField newBookPageCostInput;
    private javax.swing.JTextField newBookPageEditionInput;
    private javax.swing.JTextField newBookPageISBNInput;
    private javax.swing.JComboBox<String> newBookPagePublisherInput;
    private javax.swing.JTextField newBookPageQuantityInput;
    private javax.swing.JButton newBookPageSubmitBt;
    private javax.swing.JTextField newBookPageTitleInput;
    private javax.swing.JPanel newBookTab;
    private javax.swing.JTextField newCustomerPageAddressInput;
    private javax.swing.JTextField newCustomerPageEmailInput;
    private javax.swing.JRadioButton newCustomerPageFemaleRadio;
    private javax.swing.ButtonGroup newCustomerPageGenderBtGp;
    private javax.swing.JTextField newCustomerPageHKIDInput;
    private javax.swing.JRadioButton newCustomerPageMaleRadio;
    private javax.swing.JTextField newCustomerPageNameInput;
    private javax.swing.JTextField newCustomerPagePhoneInput;
    private javax.swing.JButton newCustomerPageSubmitBt;
    private javax.swing.JPanel newCustomerTab;
    private javax.swing.JTabbedPane pageTab;
    private javax.swing.JPanel reportTab;
    private javax.swing.JPanel returnTab;
    private javax.swing.JPanel searchBookPage;
    private javax.swing.JLabel searchBookPageAuthorLabel;
    private javax.swing.JLabel searchBookPageCostLabel;
    private javax.swing.JLabel searchBookPageEditionLabel;
    private javax.swing.JTextField searchBookPageISBNInput;
    private javax.swing.JLabel searchBookPagePublisherLabel;
    private javax.swing.JLabel searchBookPageQuantityLabel;
    private javax.swing.JButton searchBookPageRecordBt;
    private javax.swing.JButton searchBookPageSearchBt;
    private javax.swing.JLabel searchBookPageTitleLabel;
    private javax.swing.JPanel searchBookTab;
    private javax.swing.JPanel searchCustomerTab;
    private javax.swing.JTextField settingPageDateInput;
    private javax.swing.JPanel settingTab;
    // End of variables declaration//GEN-END:variables

}
