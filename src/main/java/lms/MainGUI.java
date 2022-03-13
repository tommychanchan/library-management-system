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
        
        // set form to screen center
        this.setLocationRelativeTo(null);
        
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
                            // no email in this row
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
        allBooksExportBt = new javax.swing.JButton();
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
        bookBorrowRecordExportBt = new javax.swing.JButton();
        bookBorrowRecordTitleLabel = new javax.swing.JLabel();
        allCustomersTab = new javax.swing.JPanel();
        allCustomersRefreshBt = new javax.swing.JButton();
        allCustomersExportBt = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        allCustomersTable = new javax.swing.JTable();
        searchCustomerTab = new javax.swing.JPanel();
        searchCustomerPage = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        searchCustomerPageHKIDInput = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        searchCustomerPageNameLabel = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        searchCustomerPageEmailLabel = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        searchCustomerPagePhoneLabel = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        searchCustomerPageGenderLabel = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        searchCustomerPageAddressLabel = new javax.swing.JLabel();
        searchCustomerPageSearchBt = new javax.swing.JButton();
        searchCustomerPageRecordBt = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        searchCustomerPageMoneyLabel = new javax.swing.JLabel();
        searchCustomerPagePayBt = new javax.swing.JButton();
        customerBorrowRecordPage = new javax.swing.JPanel();
        customerBorrowRecordPageBackBt = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        customerBorrowRecordTable = new javax.swing.JTable();
        customerBorrowRecordExportBt = new javax.swing.JButton();
        customerBorrowRecordNameLabel = new javax.swing.JLabel();
        borrowTab = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        borrowPageHKIDInput = new javax.swing.JTextField();
        borrowPageISBNInput1 = new javax.swing.JTextField();
        borrowPageISBNInput2 = new javax.swing.JTextField();
        borrowPageISBNInput3 = new javax.swing.JTextField();
        borrowPageISBNInput4 = new javax.swing.JTextField();
        borrowPageISBNInput5 = new javax.swing.JTextField();
        borrowPageISBNInput6 = new javax.swing.JTextField();
        borrowPageISBNInput7 = new javax.swing.JTextField();
        borrowPageISBNInput8 = new javax.swing.JTextField();
        borrowPageBorrowBt = new javax.swing.JButton();
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

        allBooksExportBt.setText("導出CSV");
        allBooksExportBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allBooksExportBtActionPerformed(evt);
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
                .addGap(18, 18, 18)
                .addComponent(allBooksExportBt)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        allBooksTabLayout.setVerticalGroup(
            allBooksTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(allBooksTabLayout.createSequentialGroup()
                .addGroup(allBooksTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(allBooksRefreshBt)
                    .addComponent(allBooksExportBt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE))
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
                .addContainerGap(267, Short.MAX_VALUE))
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
        bookBorrowRecordTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bookBorrowRecordTableMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(bookBorrowRecordTable);

        bookBorrowRecordExportBt.setText("導出CSV");
        bookBorrowRecordExportBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookBorrowRecordExportBtActionPerformed(evt);
            }
        });

        bookBorrowRecordTitleLabel.setText("title");

        javax.swing.GroupLayout bookBorrowRecordPageLayout = new javax.swing.GroupLayout(bookBorrowRecordPage);
        bookBorrowRecordPage.setLayout(bookBorrowRecordPageLayout);
        bookBorrowRecordPageLayout.setHorizontalGroup(
            bookBorrowRecordPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookBorrowRecordPageLayout.createSequentialGroup()
                .addComponent(bookBorrowRecordPageBackBt, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bookBorrowRecordExportBt)
                .addGap(18, 18, 18)
                .addComponent(bookBorrowRecordTitleLabel)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        bookBorrowRecordPageLayout.setVerticalGroup(
            bookBorrowRecordPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookBorrowRecordPageLayout.createSequentialGroup()
                .addGroup(bookBorrowRecordPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bookBorrowRecordPageBackBt)
                    .addComponent(bookBorrowRecordExportBt)
                    .addComponent(bookBorrowRecordTitleLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE))
        );

        searchBookTab.add(bookBorrowRecordPage, "bookBorrowRecordPage");

        pageTab.addTab("個別圖書", searchBookTab);

        allCustomersRefreshBt.setText("更新");
        allCustomersRefreshBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allCustomersRefreshBtActionPerformed(evt);
            }
        });

        allCustomersExportBt.setText("導出CSV");
        allCustomersExportBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allCustomersExportBtActionPerformed(evt);
            }
        });

        allCustomersTable.setModel(new javax.swing.table.DefaultTableModel(
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
        allCustomersTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        allCustomersTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        allCustomersTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                allCustomersTableMousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(allCustomersTable);

        javax.swing.GroupLayout allCustomersTabLayout = new javax.swing.GroupLayout(allCustomersTab);
        allCustomersTab.setLayout(allCustomersTabLayout);
        allCustomersTabLayout.setHorizontalGroup(
            allCustomersTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(allCustomersTabLayout.createSequentialGroup()
                .addComponent(allCustomersRefreshBt, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(allCustomersExportBt)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        allCustomersTabLayout.setVerticalGroup(
            allCustomersTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(allCustomersTabLayout.createSequentialGroup()
                .addGroup(allCustomersTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(allCustomersRefreshBt)
                    .addComponent(allCustomersExportBt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE))
        );

        pageTab.addTab("所有客戶", allCustomersTab);

        searchCustomerTab.setLayout(new java.awt.CardLayout());

        jLabel22.setText("HKID:");

        searchCustomerPageHKIDInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchCustomerPageHKIDInputKeyPressed(evt);
            }
        });

        jLabel23.setText("姓名:");

        searchCustomerPageNameLabel.setText("name");

        jLabel25.setText("Email:");

        searchCustomerPageEmailLabel.setText("email");

        jLabel27.setText("電話號碼:");

        searchCustomerPagePhoneLabel.setText("phone");

        jLabel29.setText("性別:");

        searchCustomerPageGenderLabel.setText("gender");

        jLabel31.setText("住址:");

        searchCustomerPageAddressLabel.setText("address");

        searchCustomerPageSearchBt.setText("搜尋");
        searchCustomerPageSearchBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchCustomerPageSearchBtActionPerformed(evt);
            }
        });

        searchCustomerPageRecordBt.setText("借書記錄");
        searchCustomerPageRecordBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchCustomerPageRecordBtActionPerformed(evt);
            }
        });

        jLabel24.setText("尚欠款項 (HKD):");

        searchCustomerPageMoneyLabel.setText("money");

        searchCustomerPagePayBt.setText("交還所有款項");
        searchCustomerPagePayBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchCustomerPagePayBtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout searchCustomerPageLayout = new javax.swing.GroupLayout(searchCustomerPage);
        searchCustomerPage.setLayout(searchCustomerPageLayout);
        searchCustomerPageLayout.setHorizontalGroup(
            searchCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchCustomerPageLayout.createSequentialGroup()
                .addGroup(searchCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchCustomerPageLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(searchCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(searchCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchCustomerPageHKIDInput)
                            .addGroup(searchCustomerPageLayout.createSequentialGroup()
                                .addGroup(searchCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(searchCustomerPageEmailLabel)
                                    .addComponent(searchCustomerPageNameLabel)
                                    .addComponent(searchCustomerPagePhoneLabel)
                                    .addComponent(searchCustomerPageGenderLabel)
                                    .addComponent(searchCustomerPageAddressLabel)
                                    .addComponent(searchCustomerPageMoneyLabel))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(searchCustomerPageLayout.createSequentialGroup()
                        .addGap(294, 294, 294)
                        .addComponent(searchCustomerPageSearchBt, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(searchCustomerPageRecordBt)
                        .addGap(59, 59, 59)
                        .addComponent(searchCustomerPagePayBt)
                        .addGap(0, 326, Short.MAX_VALUE)))
                .addContainerGap())
        );
        searchCustomerPageLayout.setVerticalGroup(
            searchCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchCustomerPageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(searchCustomerPageHKIDInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(searchCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(searchCustomerPageNameLabel))
                .addGap(18, 18, 18)
                .addGroup(searchCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(searchCustomerPageEmailLabel))
                .addGap(18, 18, 18)
                .addGroup(searchCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(searchCustomerPagePhoneLabel))
                .addGap(18, 18, 18)
                .addGroup(searchCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(searchCustomerPageGenderLabel))
                .addGap(18, 18, 18)
                .addGroup(searchCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(searchCustomerPageAddressLabel))
                .addGap(18, 18, 18)
                .addGroup(searchCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(searchCustomerPageMoneyLabel))
                .addGap(18, 18, 18)
                .addGroup(searchCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchCustomerPageSearchBt)
                    .addComponent(searchCustomerPageRecordBt)
                    .addComponent(searchCustomerPagePayBt))
                .addContainerGap(267, Short.MAX_VALUE))
        );

        searchCustomerTab.add(searchCustomerPage, "searchCustomerPage");
        searchCustomerPage.getAccessibleContext().setAccessibleName("");

        customerBorrowRecordPageBackBt.setText("返回");
        customerBorrowRecordPageBackBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerBorrowRecordPageBackBtActionPerformed(evt);
            }
        });

        customerBorrowRecordTable.setModel(new javax.swing.table.DefaultTableModel(
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
        customerBorrowRecordTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        customerBorrowRecordTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        customerBorrowRecordTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                customerBorrowRecordTableMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(customerBorrowRecordTable);

        customerBorrowRecordExportBt.setText("導出CSV");
        customerBorrowRecordExportBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerBorrowRecordExportBtActionPerformed(evt);
            }
        });

        customerBorrowRecordNameLabel.setText("name");

        javax.swing.GroupLayout customerBorrowRecordPageLayout = new javax.swing.GroupLayout(customerBorrowRecordPage);
        customerBorrowRecordPage.setLayout(customerBorrowRecordPageLayout);
        customerBorrowRecordPageLayout.setHorizontalGroup(
            customerBorrowRecordPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerBorrowRecordPageLayout.createSequentialGroup()
                .addComponent(customerBorrowRecordPageBackBt, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(customerBorrowRecordExportBt)
                .addGap(18, 18, 18)
                .addComponent(customerBorrowRecordNameLabel)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        customerBorrowRecordPageLayout.setVerticalGroup(
            customerBorrowRecordPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerBorrowRecordPageLayout.createSequentialGroup()
                .addGroup(customerBorrowRecordPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerBorrowRecordPageBackBt)
                    .addComponent(customerBorrowRecordExportBt)
                    .addComponent(customerBorrowRecordNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE))
        );

        searchCustomerTab.add(customerBorrowRecordPage, "customerBorrowRecordPage");

        pageTab.addTab("個別客戶", searchCustomerTab);

        jLabel26.setText("HKID:");

        jLabel28.setText("ISBN:");

        jLabel30.setText("ISBN:");

        jLabel32.setText("ISBN:");

        jLabel33.setText("ISBN:");

        jLabel34.setText("ISBN:");

        jLabel35.setText("ISBN:");

        jLabel36.setText("ISBN:");

        jLabel37.setText("ISBN:");

        borrowPageHKIDInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                borrowPageHKIDInputKeyPressed(evt);
            }
        });

        borrowPageISBNInput1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                borrowPageISBNInput1KeyPressed(evt);
            }
        });

        borrowPageISBNInput2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                borrowPageISBNInput2KeyPressed(evt);
            }
        });

        borrowPageISBNInput3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                borrowPageISBNInput3KeyPressed(evt);
            }
        });

        borrowPageISBNInput4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                borrowPageISBNInput4KeyPressed(evt);
            }
        });

        borrowPageISBNInput5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                borrowPageISBNInput5KeyPressed(evt);
            }
        });

        borrowPageISBNInput6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                borrowPageISBNInput6KeyPressed(evt);
            }
        });

        borrowPageISBNInput7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                borrowPageISBNInput7KeyPressed(evt);
            }
        });

        borrowPageISBNInput8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                borrowPageISBNInput8KeyPressed(evt);
            }
        });

        borrowPageBorrowBt.setText("借書");
        borrowPageBorrowBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrowPageBorrowBtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout borrowTabLayout = new javax.swing.GroupLayout(borrowTab);
        borrowTab.setLayout(borrowTabLayout);
        borrowTabLayout.setHorizontalGroup(
            borrowTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borrowTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(borrowTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(borrowTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(borrowPageHKIDInput, javax.swing.GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE)
                    .addComponent(borrowPageISBNInput1)
                    .addComponent(borrowPageISBNInput2)
                    .addComponent(borrowPageISBNInput3)
                    .addComponent(borrowPageISBNInput4)
                    .addComponent(borrowPageISBNInput5)
                    .addComponent(borrowPageISBNInput6)
                    .addComponent(borrowPageISBNInput7)
                    .addComponent(borrowPageISBNInput8))
                .addContainerGap())
            .addGroup(borrowTabLayout.createSequentialGroup()
                .addGap(460, 460, 460)
                .addComponent(borrowPageBorrowBt, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        borrowTabLayout.setVerticalGroup(
            borrowTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borrowTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(borrowTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(borrowPageHKIDInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(borrowTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(borrowPageISBNInput1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(borrowTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(borrowPageISBNInput2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(borrowTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(borrowPageISBNInput3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(borrowTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(borrowPageISBNInput4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(borrowTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(borrowPageISBNInput5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(borrowTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(borrowPageISBNInput6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(borrowTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(borrowPageISBNInput7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(borrowTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(borrowPageISBNInput8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(borrowPageBorrowBt)
                .addContainerGap(133, Short.MAX_VALUE))
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
                .addContainerGap(258, Short.MAX_VALUE))
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
                .addComponent(settingPageDateInput, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
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
        JTable table = (JTable) evt.getSource();
        int row = table.rowAtPoint(evt.getPoint());
        if (evt.getClickCount() == 2 && table.getSelectedRow() != -1) {
            String isbn = table.getValueAt(row, Utils.tableColumnNameToIndex(table, "ISBN")).toString();
            
            // change page to searchBookPage and show search result of isbn
            searchBookPageISBNInput.setText(isbn);
            searchBookPageSearch();
            CardLayout card = (CardLayout)searchBookTab.getLayout();
            card.show(searchBookTab, "searchBookPage");
            pageTab.setSelectedComponent(searchBookTab);
        }
    }//GEN-LAST:event_allBooksTableMousePressed

    private void pageTabStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_pageTabStateChanged
        if (pageTab.getSelectedComponent() == newBookTab) {
            newBookPageISBNInput.requestFocus();
        } else if (pageTab.getSelectedComponent() == borrowTab) {
            borrowPageHKIDInput.requestFocus();
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
        searchBookPageISBNInput.requestFocus();
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

    private void allBooksExportBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allBooksExportBtActionPerformed
        String filename = Utils.exportCSV("all_books", allBooksTable);
        JOptionPane.showMessageDialog(null, (filename == null ? "無法導出CSV。" : "已導出CSV: " + filename));
    }//GEN-LAST:event_allBooksExportBtActionPerformed

    private void customerBorrowRecordPageBackBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerBorrowRecordPageBackBtActionPerformed
        CardLayout card = (CardLayout)searchCustomerTab.getLayout();
		card.show(searchCustomerTab, "searchCustomerPage");
        searchCustomerPageHKIDInput.requestFocus();
    }//GEN-LAST:event_customerBorrowRecordPageBackBtActionPerformed

    private void searchCustomerPageHKIDInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchCustomerPageHKIDInputKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            searchCustomerPageSearch();
        }
    }//GEN-LAST:event_searchCustomerPageHKIDInputKeyPressed

    private void searchCustomerPageSearchBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchCustomerPageSearchBtActionPerformed
        searchCustomerPageSearch();
    }//GEN-LAST:event_searchCustomerPageSearchBtActionPerformed

    private void searchCustomerPageRecordBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchCustomerPageRecordBtActionPerformed
        searchCustomerPageShowRecord();
    }//GEN-LAST:event_searchCustomerPageRecordBtActionPerformed

    private void allCustomersRefreshBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allCustomersRefreshBtActionPerformed
        allCustomersRefresh();
    }//GEN-LAST:event_allCustomersRefreshBtActionPerformed

    private void allCustomersTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_allCustomersTableMousePressed
        JTable table = (JTable) evt.getSource();
        int row = table.rowAtPoint(evt.getPoint());
        if (evt.getClickCount() == 2 && table.getSelectedRow() != -1) {
            String hkid = table.getValueAt(row, Utils.tableColumnNameToIndex(table, "HKID")).toString();
            
            // change page to searchCustomerPage and show search result of hkid
            searchCustomerPageHKIDInput.setText(hkid);
            CardLayout card = (CardLayout)searchCustomerTab.getLayout();
            card.show(searchCustomerTab, "searchCustomerPage");
            searchCustomerPageSearch();
            pageTab.setSelectedComponent(searchCustomerTab);
        }
    }//GEN-LAST:event_allCustomersTableMousePressed

    private void allCustomersExportBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allCustomersExportBtActionPerformed
        String filename = Utils.exportCSV("all_customers", allCustomersTable);
        JOptionPane.showMessageDialog(null, (filename == null ? "無法導出CSV。" : "已導出CSV: " + filename));
    }//GEN-LAST:event_allCustomersExportBtActionPerformed

    private void bookBorrowRecordTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookBorrowRecordTableMousePressed
        JTable table = (JTable) evt.getSource();
        int row = table.rowAtPoint(evt.getPoint());
        if (evt.getClickCount() == 2 && table.getSelectedRow() != -1) {
            String hkid = table.getValueAt(row, Utils.tableColumnNameToIndex(table, "HKID")).toString();
            
            // change page to searchCustomerPage and show search result of hkid
            searchCustomerPageHKIDInput.setText(hkid);
            searchCustomerPageSearch();
            CardLayout card = (CardLayout)searchCustomerTab.getLayout();
            card.show(searchCustomerTab, "searchCustomerPage");
            pageTab.setSelectedComponent(searchCustomerTab);
        }
    }//GEN-LAST:event_bookBorrowRecordTableMousePressed

    private void bookBorrowRecordExportBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookBorrowRecordExportBtActionPerformed
        String filename = Utils.exportCSV("book_" + searchBookPageISBNInput.getText(), bookBorrowRecordTable);
        JOptionPane.showMessageDialog(null, (filename == null ? "無法導出CSV。" : "已導出CSV: " + filename));
    }//GEN-LAST:event_bookBorrowRecordExportBtActionPerformed

    private void customerBorrowRecordExportBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerBorrowRecordExportBtActionPerformed
        String filename = Utils.exportCSV("customer_" + searchCustomerPageHKIDInput.getText(), customerBorrowRecordTable);
        JOptionPane.showMessageDialog(null, (filename == null ? "無法導出CSV。" : "已導出CSV: " + filename));
    }//GEN-LAST:event_customerBorrowRecordExportBtActionPerformed

    private void customerBorrowRecordTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerBorrowRecordTableMousePressed
        JTable table = (JTable) evt.getSource();
        int row = table.rowAtPoint(evt.getPoint());
        if (evt.getClickCount() == 2 && table.getSelectedRow() != -1) {
            String isbn = table.getValueAt(row, Utils.tableColumnNameToIndex(table, "ISBN")).toString();
            
            // change page to searchCustomerPage and show search result of hkid
            searchBookPageISBNInput.setText(isbn);
            searchBookPageSearch();
            CardLayout card = (CardLayout)searchBookTab.getLayout();
            card.show(searchBookTab, "searchBookPage");
            pageTab.setSelectedComponent(searchBookTab);
        }
    }//GEN-LAST:event_customerBorrowRecordTableMousePressed

    private void searchCustomerPagePayBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchCustomerPagePayBtActionPerformed
        String hkid = searchCustomerPageHKIDInput.getText().trim().toUpperCase();
        if (!Utils.isValidHKID(hkid)) {
            // invalid HKID
            if (!hkid.equals("")) {
                JOptionPane.showMessageDialog(null, "無效的HKID。");
            }
            return;
        }
        
        Statement stmt = null;
        Savepoint savePoint = null;
        String sql, msg = "";
        ResultSet rs;
        try {
            boolean needRollBack = false;
            int affectedRow;
            Main.conn.setAutoCommit(false);
            stmt = Main.conn.createStatement();
            savePoint = Main.conn.setSavepoint();

            // check if HKID exists in userinfo
            sql = "select count(HKID) total from userinfo where HKID='" + hkid + "';";
            rs = stmt.executeQuery(sql);
            int temp = 0;
            while (rs.next()) {
                temp = rs.getInt("total");
            }
            if (temp == 0) {
                needRollBack = true;
                msg += "\n找不到此客戶。";
            }

            // check if all books are returned and
            // mark as paid for all TransactionDetail
            if (!needRollBack) {
                sql = "select count(*) total from transaction T inner join transactiondetail TD on T.transaction_id=TD.transaction_id where T.HKID='" + hkid + "' and TD.return_date is NULL;";
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    temp = rs.getInt("total");
                }
                if (temp > 0) {
                    needRollBack = true;
                    msg += "\n此客戶尚未交還所有圖書。";
                }
            }
            
            // calculate debt
            // check if debt greater than 0
            double debt = 0;
            java.sql.Date dueDate, returnDate;
            if (!needRollBack) {
                sql = "select * from transaction T inner join transactiondetail TD on T.transaction_id=TD.transaction_id where T.HKID='" + hkid + "' and (TD.return_date is NULL or TD.return_date > TD.due_date) and NOT T.paid;";
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    dueDate = rs.getDate("TD.due_date");
                    returnDate = rs.getDate("TD.return_date");
                    returnDate = (returnDate == null ? Main.fakeTime.getDate() : returnDate);
                    if (returnDate.after(dueDate)) {
                        debt += Utils.daysDifference(dueDate, returnDate) * Main.DEBT_EACH_DAY;
                    }
                }
                if (debt == 0) {
                    // no any debt need to pay
                    needRollBack = true;
                    msg += "\n此客戶沒有任何未還款項。";
                }
            }
            
            if (!needRollBack) {
                // clear the debt
                sql = "update transaction set paid=true where HKID='" + hkid + "';";
                stmt.executeUpdate(sql);
            }
            
            rs.close();
            stmt.close();
            if (needRollBack) {
                Main.conn.rollback(savePoint);
            }
            Main.conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (Main.conn != null) {
                try {
                    if (savePoint != null) {
                        Main.conn.rollback(savePoint);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            msg += "\nSQL Exception.";
        } finally {
            try{
                Main.conn.setAutoCommit(true);
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            }catch(SQLException se2){}
        }
        
        if (msg.equals("")) {
            // success
            // search again to update the page
            searchCustomerPageSearch();
        } else {
            // something wrong
            msg = "無法交還款項，原因如下：" + msg;
            JOptionPane.showMessageDialog(null, msg);
        }
    }//GEN-LAST:event_searchCustomerPagePayBtActionPerformed

    private void borrowPageHKIDInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_borrowPageHKIDInputKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            String hkid = borrowPageHKIDInput.getText().trim().toUpperCase();
            if (Utils.isValidHKID(hkid)) {
                borrowPageISBNInput1.requestFocus();
            } else if (!hkid.equals("")) {
                JOptionPane.showMessageDialog(null, "無效的HKID。");
            }
        }
    }//GEN-LAST:event_borrowPageHKIDInputKeyPressed

    private void borrowPageISBNInput1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_borrowPageISBNInput1KeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            String isbn = borrowPageISBNInput1.getText().trim().toUpperCase();
            if (Utils.isValidISBN(isbn)) {
                borrowPageISBNInput2.requestFocus();
            } else if (!isbn.equals("")) {
                JOptionPane.showMessageDialog(null, "無效的ISBN。");
            }
        }
    }//GEN-LAST:event_borrowPageISBNInput1KeyPressed

    private void borrowPageISBNInput2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_borrowPageISBNInput2KeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            String isbn = borrowPageISBNInput2.getText().trim().toUpperCase();
            if (isbn.equals("") || Utils.isValidISBN(isbn)) {
                borrowPageISBNInput3.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "無效的ISBN。");
            }
        }
    }//GEN-LAST:event_borrowPageISBNInput2KeyPressed

    private void borrowPageISBNInput3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_borrowPageISBNInput3KeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            String isbn = borrowPageISBNInput3.getText().trim().toUpperCase();
            if (isbn.equals("") || Utils.isValidISBN(isbn)) {
                borrowPageISBNInput4.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "無效的ISBN。");
            }
        }
    }//GEN-LAST:event_borrowPageISBNInput3KeyPressed

    private void borrowPageISBNInput4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_borrowPageISBNInput4KeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            String isbn = borrowPageISBNInput4.getText().trim().toUpperCase();
            if (isbn.equals("") || Utils.isValidISBN(isbn)) {
                borrowPageISBNInput5.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "無效的ISBN。");
            }
        }
    }//GEN-LAST:event_borrowPageISBNInput4KeyPressed

    private void borrowPageISBNInput5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_borrowPageISBNInput5KeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            String isbn = borrowPageISBNInput5.getText().trim().toUpperCase();
            if (isbn.equals("") || Utils.isValidISBN(isbn)) {
                borrowPageISBNInput6.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "無效的ISBN。");
            }
        }
    }//GEN-LAST:event_borrowPageISBNInput5KeyPressed

    private void borrowPageISBNInput6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_borrowPageISBNInput6KeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            String isbn = borrowPageISBNInput6.getText().trim().toUpperCase();
            if (isbn.equals("") || Utils.isValidISBN(isbn)) {
                borrowPageISBNInput7.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "無效的ISBN。");
            }
        }
    }//GEN-LAST:event_borrowPageISBNInput6KeyPressed

    private void borrowPageISBNInput7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_borrowPageISBNInput7KeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            String isbn = borrowPageISBNInput7.getText().trim().toUpperCase();
            if (isbn.equals("") || Utils.isValidISBN(isbn)) {
                borrowPageISBNInput8.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "無效的ISBN。");
            }
        }
    }//GEN-LAST:event_borrowPageISBNInput7KeyPressed

    private void borrowPageISBNInput8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_borrowPageISBNInput8KeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            String isbn = borrowPageISBNInput8.getText().trim().toUpperCase();
            if (isbn.equals("") || Utils.isValidISBN(isbn)) {
                borrowPageBorrow();
            } else {
                JOptionPane.showMessageDialog(null, "無效的ISBN。");
            }
        }
    }//GEN-LAST:event_borrowPageISBNInput8KeyPressed

    private void borrowPageBorrowBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrowPageBorrowBtActionPerformed
        borrowPageBorrow();
    }//GEN-LAST:event_borrowPageBorrowBtActionPerformed
    
    public void init() {
        // remove useless label text
        searchBookPageTitleLabel.setText("");
        searchBookPageAuthorLabel.setText("");
        searchBookPagePublisherLabel.setText("");
        searchBookPageEditionLabel.setText("");
        searchBookPageCostLabel.setText("");
        searchBookPageQuantityLabel.setText("");
        searchCustomerPageNameLabel.setText("");
        searchCustomerPageEmailLabel.setText("");
        searchCustomerPagePhoneLabel.setText("");
        searchCustomerPageGenderLabel.setText("");
        searchCustomerPageAddressLabel.setText("");
        searchCustomerPageMoneyLabel.setText("");
        bookBorrowRecordTitleLabel.setText("");
        customerBorrowRecordNameLabel.setText("");
        
        // set up table columns for allBooksTable
        DefaultTableModel allBooksTableModel = (DefaultTableModel) allBooksTable.getModel();
        allBooksTableModel.setColumnCount(0);
        allBooksTableModel.addColumn("ISBN");
        allBooksTableModel.addColumn("書名");
        allBooksTableModel.addColumn("出版社");
        allBooksTableModel.addColumn("作者");
        allBooksTableModel.addColumn("版本");
        allBooksTableModel.addColumn("成本價");
        allBooksTableModel.addColumn("存貨");
        
        // set up table columns for allCustomersTable
        DefaultTableModel allCustomersTableModel = (DefaultTableModel) allCustomersTable.getModel();
        allCustomersTableModel.setColumnCount(0);
        allCustomersTableModel.addColumn("HKID");
        allCustomersTableModel.addColumn("姓名");
        allCustomersTableModel.addColumn("Email");
        allCustomersTableModel.addColumn("電話號碼");
        allCustomersTableModel.addColumn("性別");
        allCustomersTableModel.addColumn("住址");
        
        // set up table columns for bookBorrowRecordTable
        DefaultTableModel bookBorrowRecordTableModel = (DefaultTableModel) bookBorrowRecordTable.getModel();
        bookBorrowRecordTableModel.setColumnCount(0);
        bookBorrowRecordTableModel.addColumn("HKID");
        bookBorrowRecordTableModel.addColumn("客戶姓名");
        bookBorrowRecordTableModel.addColumn("借書日期");
        bookBorrowRecordTableModel.addColumn("到期日");
        bookBorrowRecordTableModel.addColumn("還書日期");
        
        // set up table columns for bookBorrowRecordTable
        DefaultTableModel customerBorrowRecordTableModel = (DefaultTableModel) customerBorrowRecordTable.getModel();
        customerBorrowRecordTableModel.setColumnCount(0);
        customerBorrowRecordTableModel.addColumn("ISBN");
        customerBorrowRecordTableModel.addColumn("書名");
        customerBorrowRecordTableModel.addColumn("借書日期");
        customerBorrowRecordTableModel.addColumn("到期日");
        customerBorrowRecordTableModel.addColumn("還書日期");
        
        
        // show book info on allBooksTable
        allBooksRefresh();
        
        // show customer info on allCustomersTable
        allCustomersRefresh();
        
        // set editable false to tables
        allBooksTable.setDefaultEditor(Object.class, null);
        allCustomersTable.setDefaultEditor(Object.class, null);
        bookBorrowRecordTable.setDefaultEditor(Object.class, null);
        customerBorrowRecordTable.setDefaultEditor(Object.class, null);
        
        // select a default tab
        pageTab.setSelectedComponent(allBooksTab);
    }
    
    public void allCustomersRefresh() {
        // clear table
        DefaultTableModel tableModel = (DefaultTableModel) allCustomersTable.getModel();
        tableModel.setRowCount(0);
        
        Statement stmt = null;
        try{
            stmt = Main.conn.createStatement();
            String sql = "select * from userinfo order by name";
            ResultSet rs = stmt.executeQuery(sql);
            String hkid = null, name = null, email = null, phone = null, gender = null, address = null;
            while (rs.next()) {
                hkid = rs.getString("HKID");
                name = rs.getString("name");
                email = rs.getString("email");
                if (rs.wasNull()) {
                    // no email in this row
                    email = "";
                }
                phone = rs.getString("phone");
                if (rs.wasNull()) {
                    // no phone in this row
                    phone = "";
                }
                gender = rs.getString("gender");
                address = rs.getString("address");
                
                // add rows to table
                tableModel.addRow(new String[] {hkid, name, email, phone, (gender.equals("M") ? "男" : "女"), address});
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
    }
    
    public void allBooksRefresh() {
        // clear table
        DefaultTableModel tableModel = (DefaultTableModel) allBooksTable.getModel();
        tableModel.setRowCount(0);
        
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
                tableModel.addRow(books.get(i).getRow());
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
    
    private void borrowPageBorrow() {
        String hkid = borrowPageHKIDInput.getText().trim().toUpperCase();
        if (!Utils.isValidHKID(hkid)) {
            JOptionPane.showMessageDialog(null, (hkid.equals("") ? "請輸入HKID。" : "無效的HKID。"));
            return;
        }
        
        String msg = "";
        boolean[] needToCheck = new boolean[] {true, true, true, true, true, true, true, true};
        String[] isbns = new String[] {
            borrowPageISBNInput1.getText().trim().toUpperCase(),
            borrowPageISBNInput2.getText().trim().toUpperCase(),
            borrowPageISBNInput3.getText().trim().toUpperCase(),
            borrowPageISBNInput4.getText().trim().toUpperCase(),
            borrowPageISBNInput5.getText().trim().toUpperCase(),
            borrowPageISBNInput6.getText().trim().toUpperCase(),
            borrowPageISBNInput7.getText().trim().toUpperCase(),
            borrowPageISBNInput8.getText().trim().toUpperCase()
        };
        
        // filter all invalid ISBN
        for (int i = 0, n = isbns.length; i < n; i++) {
            if (isbns[i].equals("")) {
                needToCheck[i] = false;
            } else if (!Utils.isValidISBN(isbns[i])) {
                needToCheck[i] = false;
                msg += "\n無效的ISBN：" + isbns[i];
            }
        }
        
        // check if isbn exists in database
        Statement stmt = null;
        String sql;
        ResultSet rs = null;
        try{
            for (int i = 0, n = isbns.length; i < n; i++) {
                if (!needToCheck[i]) {
                    continue;
                }
                stmt = Main.conn.createStatement();
                sql = "select ISBN from bookinfo where ISBN='" + isbns[i] + "'";
                rs = stmt.executeQuery(sql);
                needToCheck[i] = rs.next();
                if (!needToCheck[i]) {
                    // ISBN valid but not exists
                    msg += "\n找不到此書：" + isbns[i];
                }
            }
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            }catch(SQLException se2){}
        }
        
        // check if user have enough quota to borrow these books
        int can_borrow_num = Main.MAX_BOOKS_BORROW;
        try{
            stmt = Main.conn.createStatement();
            sql = "select count(detail_id) total from transaction T left join transactiondetail TD on T.transaction_id=TD.transaction_id where hkid='" + hkid + "' and return_date is NULL";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                can_borrow_num = Main.MAX_BOOKS_BORROW - rs.getInt("total");
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            }catch(SQLException se2){}
        }
        for (int i = 0, n = isbns.length; i < n; i++) {
            if (needToCheck[i]) {
                can_borrow_num--;
            }
        }
        
        if (can_borrow_num < 0) {
            // not enough quota
            for (int i = 0, n = isbns.length; i < n; i++) {
                needToCheck[i] = false;
            }
            msg += "\n此客戶沒有足夠配額，每位客戶只可同時借" + Main.MAX_BOOKS_BORROW + "本書。";
        }
        
        
        
        if (msg.equals("")) {
            // so far no any problem
            // now use transaction to remove quantity and
            // insert borrow data to database.
            Savepoint savePoint = null;
            try {
                boolean needRollBack = false;
                int affectedRow;
                Main.conn.setAutoCommit(false);
                stmt = Main.conn.createStatement();
                savePoint = Main.conn.setSavepoint();
                
                // remove quantity of each book
                for (int i = 0, n = isbns.length; i < n; i++) {
                    if (!needToCheck[i]) {
                        continue;
                    }
                    sql = "update bookinfo set quantity = quantity-1 where isbn = '" + isbns[i] + "' and quantity > 0;";
                    affectedRow = stmt.executeUpdate(sql);
                    if (affectedRow == 0) {
                        // quantity is 0
                        needRollBack = true;
                        msg += "\n沒有足夠存貨：" + isbns[i];
                    }
                }
                
                // insert to table Transaction
                sql = "insert into transaction (HKID, borrow_date, paid) values ('" + hkid + "', '" + Main.fakeTime.formatDate() + "', false);";
                affectedRow = stmt.executeUpdate(sql);
                if (affectedRow == 0) {
                    // cannot insert to table Transaction
                    needRollBack = true;
                    msg += "\n無法插入交易資料。";
                }
                
                // retrieve transaction_id
                int transaction_id = -1;
                sql = "select LAST_INSERT_ID() ID;";
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    transaction_id = rs.getInt("ID");
                }
                rs.close();
                
                if (transaction_id == -1) {
                    needRollBack = true;
                    msg += "\n無法取回交易ID。";
                } else {
                    // insert transaction detail of each book
                    String dueDateStr = Utils.toString(Utils.addDays(Main.fakeTime.getDate(), Main.MAX_DAYS_BORROW));
                    for (int i = 0, n = isbns.length; i < n; i++) {
                        if (!needToCheck[i]) {
                            continue;
                        }
                        sql = "insert into transactiondetail (transaction_id, ISBN, due_date) VALUES (" + transaction_id + ", '" + isbns[i] + "', '" + dueDateStr + "');";
                        affectedRow = stmt.executeUpdate(sql);
                        if (affectedRow == 0) {
                            // cannot insert TransactionDetail
                            needRollBack = true;
                            msg += "\n無法插入交易詳細資料：" + isbns[i] + "。";
                        }
                    }
                }
                
                stmt.close();
                if (needRollBack) {
                    Main.conn.rollback(savePoint);
                }
                Main.conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                if (Main.conn != null) {
                    try {
                        if (savePoint != null) {
                            Main.conn.rollback(savePoint);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                msg += "\nSQL Exception.";
            } finally {
                try{
                    Main.conn.setAutoCommit(true);
                    if (stmt != null) {
                        stmt.close();
                        stmt = null;
                    }
                }catch(SQLException se2){}
            }
        }
        
        
        if (msg.equals("")) {
            // success
            // clear all input fields
            borrowPageHKIDInput.setText("");
            borrowPageISBNInput1.setText("");
            borrowPageISBNInput2.setText("");
            borrowPageISBNInput3.setText("");
            borrowPageISBNInput4.setText("");
            borrowPageISBNInput5.setText("");
            borrowPageISBNInput6.setText("");
            borrowPageISBNInput7.setText("");
            borrowPageISBNInput8.setText("");
            
            // focus hkid input
            borrowPageHKIDInput.requestFocus();
        } else {
            // something wrong
            msg = "無法借書，原因如下：" + msg;
            JOptionPane.showMessageDialog(null, msg);
        }
    }
    
    private void searchCustomerPageShowRecord() {
        if (!searchCustomerPageSearch()) {
            return;
        }
        
        // clear table
        DefaultTableModel tableModel = (DefaultTableModel) customerBorrowRecordTable.getModel();
        tableModel.setRowCount(0);
        
        String hkid = searchCustomerPageHKIDInput.getText().trim().toUpperCase();
        Statement stmt = null;
        try{
            stmt = Main.conn.createStatement();
            String sql = "select * from transaction T inner join transactiondetail TD on T.transaction_id=TD.transaction_id inner join bookinfo BI on TD.ISBN=BI.ISBN right join userinfo UI on T.HKID=UI.HKID where UI.HKID = '" + hkid + "' order by T.transaction_id desc;";
            ResultSet rs = stmt.executeQuery(sql);
            String isbn = null, name = null, title = null, borrowDate = null, dueDate = null, returnDate = null;
            java.sql.Date tempReturnDate;
            while (rs.next()) {
                isbn = rs.getString("TD.ISBN");
                name = rs.getString("UI.name");
                if (isbn == null) {
                    // this customer did not borrow book
                    continue;
                }
                title = rs.getString("BI.title");
                borrowDate = Utils.toString(rs.getDate("T.borrow_date"));
                dueDate = Utils.toString(rs.getDate("TD.due_date"));
                tempReturnDate = rs.getDate("TD.return_date");
                returnDate = (tempReturnDate == null ? "尚未還書" : Utils.toString(tempReturnDate));
                
                tableModel.addRow(new String[] {isbn, title, borrowDate, dueDate, returnDate});
            }
            customerBorrowRecordNameLabel.setText("姓名：" + name);
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if (stmt != null) stmt.close();
            }catch(SQLException se2){}
        }
        
        CardLayout card = (CardLayout)searchCustomerTab.getLayout();
		card.show(searchCustomerTab, "customerBorrowRecordPage");
    }
    
    private void searchBookPageShowRecord() {
        if (!searchBookPageSearch()) {
            return;
        }
        
        // clear table
        DefaultTableModel tableModel = (DefaultTableModel) bookBorrowRecordTable.getModel();
        tableModel.setRowCount(0);
        
        String isbn = searchBookPageISBNInput.getText().trim().toUpperCase();
        Statement stmt = null;
        try{
            stmt = Main.conn.createStatement();
            String sql = "select * from transaction T inner join transactiondetail TD on T.transaction_id=TD.transaction_id inner join userinfo UI on T.HKID=UI.HKID right join bookinfo BI on TD.ISBN=BI.ISBN where BI.ISBN = '" + isbn + "' order by T.transaction_id desc;";
            ResultSet rs = stmt.executeQuery(sql);
            String hkid = null, name = null, title = null, borrowDate = null, dueDate = null, returnDate = null;
            java.sql.Date tempReturnDate;
            while (rs.next()) {
                hkid = rs.getString("UI.HKID");
                name = rs.getString("UI.name");
                title = rs.getString("BI.title");
                if (hkid == null) {
                    // no people borrow this book
                    continue;
                }
                borrowDate = Utils.toString(rs.getDate("T.borrow_date"));
                dueDate = Utils.toString(rs.getDate("TD.due_date"));
                tempReturnDate = rs.getDate("TD.return_date");
                returnDate = (tempReturnDate == null ? "尚未還書" : Utils.toString(tempReturnDate));
                
                tableModel.addRow(new String[] {hkid, name, borrowDate, dueDate, returnDate});
            }
            bookBorrowRecordTitleLabel.setText("書名：" + title);
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if (stmt != null) stmt.close();
            }catch(SQLException se2){}
        }
        
        CardLayout card = (CardLayout)searchBookTab.getLayout();
		card.show(searchBookTab, "bookBorrowRecordPage");
    }
    
    private boolean searchCustomerPageSearch() {
        String hkid = searchCustomerPageHKIDInput.getText().trim().toUpperCase();
        if (!Utils.isValidHKID(hkid)) {
            // invalid HKID
            if (!hkid.equals("")) {
                JOptionPane.showMessageDialog(null, "無效的HKID。");
            }
            return false;
        }
        
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
                    // no email in this row
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
            
            // calculate and show the money should pay
            double debt = 0;
            java.sql.Date dueDate = null, returnDate = null;
            sql = "select * from transaction T inner join transactiondetail TD on T.transaction_id=TD.transaction_id where T.HKID='" + hkid + "' and (TD.return_date is NULL or TD.return_date > TD.due_date) and NOT T.paid;";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                dueDate = rs.getDate("TD.due_date");
                returnDate = rs.getDate("TD.return_date");
                returnDate = (returnDate == null ? Main.fakeTime.getDate() : returnDate);
                if (returnDate.after(dueDate)) {
                    debt += Utils.daysDifference(dueDate, returnDate) * Main.DEBT_EACH_DAY;
                }
            }
            
            rs.close();
            stmt.close();
            
            if (name == null) {
                JOptionPane.showMessageDialog(null, "找不到此客戶。");
                return false;
            }
            // update info label
            searchCustomerPageNameLabel.setText(name);
            searchCustomerPageEmailLabel.setText(email);
            searchCustomerPagePhoneLabel.setText(phone);
            searchCustomerPageGenderLabel.setText((gender.equals("M") ? "男" : "女"));
            searchCustomerPageAddressLabel.setText(address);
            searchCustomerPageMoneyLabel.setText(Double.toString(debt));
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
        
        String[] authors = (authorStr.equals("") ? new String[] {} : authorStr.split(",|，"));
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
    private javax.swing.JButton allBooksExportBt;
    private javax.swing.JButton allBooksRefreshBt;
    private javax.swing.JPanel allBooksTab;
    private javax.swing.JTable allBooksTable;
    private javax.swing.JButton allCustomersExportBt;
    private javax.swing.JButton allCustomersRefreshBt;
    private javax.swing.JPanel allCustomersTab;
    private javax.swing.JTable allCustomersTable;
    private javax.swing.JButton bookBorrowRecordExportBt;
    private javax.swing.JPanel bookBorrowRecordPage;
    private javax.swing.JButton bookBorrowRecordPageBackBt;
    private javax.swing.JTable bookBorrowRecordTable;
    private javax.swing.JLabel bookBorrowRecordTitleLabel;
    private javax.swing.JButton borrowPageBorrowBt;
    private javax.swing.JTextField borrowPageHKIDInput;
    private javax.swing.JTextField borrowPageISBNInput1;
    private javax.swing.JTextField borrowPageISBNInput2;
    private javax.swing.JTextField borrowPageISBNInput3;
    private javax.swing.JTextField borrowPageISBNInput4;
    private javax.swing.JTextField borrowPageISBNInput5;
    private javax.swing.JTextField borrowPageISBNInput6;
    private javax.swing.JTextField borrowPageISBNInput7;
    private javax.swing.JTextField borrowPageISBNInput8;
    private javax.swing.JPanel borrowTab;
    private javax.swing.JButton customerBorrowRecordExportBt;
    private javax.swing.JLabel customerBorrowRecordNameLabel;
    private javax.swing.JPanel customerBorrowRecordPage;
    private javax.swing.JButton customerBorrowRecordPageBackBt;
    private javax.swing.JTable customerBorrowRecordTable;
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
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
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
    private javax.swing.JPanel searchCustomerPage;
    private javax.swing.JLabel searchCustomerPageAddressLabel;
    private javax.swing.JLabel searchCustomerPageEmailLabel;
    private javax.swing.JLabel searchCustomerPageGenderLabel;
    private javax.swing.JTextField searchCustomerPageHKIDInput;
    private javax.swing.JLabel searchCustomerPageMoneyLabel;
    private javax.swing.JLabel searchCustomerPageNameLabel;
    private javax.swing.JButton searchCustomerPagePayBt;
    private javax.swing.JLabel searchCustomerPagePhoneLabel;
    private javax.swing.JButton searchCustomerPageRecordBt;
    private javax.swing.JButton searchCustomerPageSearchBt;
    private javax.swing.JPanel searchCustomerTab;
    private javax.swing.JTextField settingPageDateInput;
    private javax.swing.JPanel settingTab;
    // End of variables declaration//GEN-END:variables

}
