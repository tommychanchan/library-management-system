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
                PreparedStatement stmt = null;
                ArrayList<String> authors = new ArrayList<>();
                try{
                    stmt = Main.conn.prepareStatement("select * from bookinfo BI left join bookauthor BA on BI.isbn=BA.isbn where BI.isbn = ?");
                    stmt.setString(1, isbn);
                    ResultSet rs = stmt.executeQuery();
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
                PreparedStatement stmt = null;
                try{
                    stmt = Main.conn.prepareStatement("select * from userinfo UI inner join usertype UT on UI.type_id = UT.type_id where hkid = ?");
                    stmt.setString(1, hkid);
                    ResultSet rs = stmt.executeQuery();
                    String name = null, type = null, email = null, phone = null, gender = null, address = null;
                    while (rs.next()) {
                        name = rs.getString("name");
                        type = rs.getString("UT.type_name");
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
                        newCustomerPageUserTypeInput.setSelectedItem(type);
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
        allBooksSearchInput = new javax.swing.JTextField();
        allBooksSearchBt = new javax.swing.JButton();
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
        jLabel40 = new javax.swing.JLabel();
        searchCustomerPageTypeLabel = new javax.swing.JLabel();
        customerBorrowRecordPage = new javax.swing.JPanel();
        customerBorrowRecordPageBackBt = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        customerBorrowRecordTable = new javax.swing.JTable();
        customerBorrowRecordExportBt = new javax.swing.JButton();
        customerBorrowRecordNameLabel = new javax.swing.JLabel();
        borrowTab = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        borrowPageHKIDInput = new javax.swing.JTextField();
        borrowPageISBNInput = new javax.swing.JTextField();
        borrowPageBorrowBt = new javax.swing.JButton();
        borrowPageAddBt = new javax.swing.JButton();
        borrowPageResetBt = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        borrowPageTable = new javax.swing.JTable();
        returnTab = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        returnPageHKIDInput = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        returnPageISBNInput = new javax.swing.JTextField();
        returnPageReturnBt = new javax.swing.JButton();
        returnPageResetBt = new javax.swing.JButton();
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
        jLabel41 = new javax.swing.JLabel();
        newCustomerPageUserTypeInput = new javax.swing.JComboBox<>();
        newUserTypeTab = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        newUserTypePageUserTypeInput = new javax.swing.JComboBox<>();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        newUserTypePageMaxBooksInput = new javax.swing.JTextField();
        newUserTypePageMaxDaysInput = new javax.swing.JTextField();
        newUserTypePageDebtInput = new javax.swing.JTextField();
        newUserTypePageSubmitBt = new javax.swing.JButton();
        reportTab = new javax.swing.JPanel();
        reportPageRefreshBt = new javax.swing.JButton();
        reportPageExportBt = new javax.swing.JButton();
        reportPageInput = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        reportPageTable = new javax.swing.JTable();
        settingTab = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        settingPageDateInput = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(Main.PROGRAM_NAME);
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

        allBooksSearchInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                allBooksSearchInputKeyPressed(evt);
            }
        });

        allBooksSearchBt.setText("搜尋書名");
        allBooksSearchBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allBooksSearchBtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout allBooksTabLayout = new javax.swing.GroupLayout(allBooksTab);
        allBooksTab.setLayout(allBooksTabLayout);
        allBooksTabLayout.setHorizontalGroup(
            allBooksTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(allBooksTabLayout.createSequentialGroup()
                .addComponent(allBooksRefreshBt, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(allBooksExportBt)
                .addGap(18, 18, 18)
                .addComponent(allBooksSearchInput)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(allBooksSearchBt))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        allBooksTabLayout.setVerticalGroup(
            allBooksTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(allBooksTabLayout.createSequentialGroup()
                .addGroup(allBooksTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(allBooksRefreshBt)
                    .addComponent(allBooksExportBt)
                    .addComponent(allBooksSearchInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(allBooksSearchBt))
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

        jLabel24.setText("尚欠罰款 (HKD):");

        searchCustomerPageMoneyLabel.setText("money");

        searchCustomerPagePayBt.setText("交還所有罰款");
        searchCustomerPagePayBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchCustomerPagePayBtActionPerformed(evt);
            }
        });

        jLabel40.setText("類型:");

        searchCustomerPageTypeLabel.setText("type");

        javax.swing.GroupLayout searchCustomerPageLayout = new javax.swing.GroupLayout(searchCustomerPage);
        searchCustomerPage.setLayout(searchCustomerPageLayout);
        searchCustomerPageLayout.setHorizontalGroup(
            searchCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchCustomerPageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchCustomerPageLayout.createSequentialGroup()
                        .addGroup(searchCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(searchCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchCustomerPageHKIDInput)
                            .addGroup(searchCustomerPageLayout.createSequentialGroup()
                                .addGroup(searchCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(searchCustomerPageNameLabel)
                                    .addComponent(searchCustomerPagePhoneLabel)
                                    .addComponent(searchCustomerPageMoneyLabel))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(searchCustomerPageLayout.createSequentialGroup()
                        .addGroup(searchCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(searchCustomerPageLayout.createSequentialGroup()
                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(searchCustomerPageTypeLabel))
                            .addGroup(searchCustomerPageLayout.createSequentialGroup()
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(searchCustomerPageEmailLabel)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(searchCustomerPageLayout.createSequentialGroup()
                .addGroup(searchCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchCustomerPageLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(searchCustomerPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(searchCustomerPageLayout.createSequentialGroup()
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(searchCustomerPageGenderLabel))
                            .addGroup(searchCustomerPageLayout.createSequentialGroup()
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(searchCustomerPageAddressLabel))))
                    .addGroup(searchCustomerPageLayout.createSequentialGroup()
                        .addGap(290, 290, 290)
                        .addComponent(searchCustomerPageSearchBt, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(searchCustomerPageRecordBt)
                        .addGap(58, 58, 58)
                        .addComponent(searchCustomerPagePayBt)))
                .addGap(0, 331, Short.MAX_VALUE))
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
                    .addComponent(jLabel40)
                    .addComponent(searchCustomerPageTypeLabel))
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
                .addContainerGap(228, Short.MAX_VALUE))
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

        borrowPageHKIDInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                borrowPageHKIDInputKeyPressed(evt);
            }
        });

        borrowPageISBNInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                borrowPageISBNInputKeyPressed(evt);
            }
        });

        borrowPageBorrowBt.setText("借書");
        borrowPageBorrowBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrowPageBorrowBtActionPerformed(evt);
            }
        });

        borrowPageAddBt.setText("加入");
        borrowPageAddBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrowPageAddBtActionPerformed(evt);
            }
        });

        borrowPageResetBt.setText("重設");
        borrowPageResetBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrowPageResetBtActionPerformed(evt);
            }
        });

        borrowPageTable.setModel(new javax.swing.table.DefaultTableModel(
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
        borrowPageTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        borrowPageTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        borrowPageTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                borrowPageTableMousePressed(evt);
            }
        });
        jScrollPane6.setViewportView(borrowPageTable);

        javax.swing.GroupLayout borrowTabLayout = new javax.swing.GroupLayout(borrowTab);
        borrowTab.setLayout(borrowTabLayout);
        borrowTabLayout.setHorizontalGroup(
            borrowTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borrowTabLayout.createSequentialGroup()
                .addGroup(borrowTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(borrowTabLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(borrowTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(borrowTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(borrowPageHKIDInput, javax.swing.GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE)
                            .addComponent(borrowPageISBNInput)))
                    .addGroup(borrowTabLayout.createSequentialGroup()
                        .addGap(352, 352, 352)
                        .addComponent(borrowPageAddBt, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(borrowPageBorrowBt, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(borrowPageResetBt, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jScrollPane6)
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
                    .addComponent(borrowPageISBNInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(borrowTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(borrowPageBorrowBt)
                    .addComponent(borrowPageAddBt)
                    .addComponent(borrowPageResetBt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE))
        );

        pageTab.addTab("借書", borrowTab);

        jLabel38.setText("HKID:");

        returnPageHKIDInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                returnPageHKIDInputKeyPressed(evt);
            }
        });

        jLabel39.setText("ISBN:");

        returnPageISBNInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                returnPageISBNInputKeyPressed(evt);
            }
        });

        returnPageReturnBt.setText("還書");
        returnPageReturnBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnPageReturnBtActionPerformed(evt);
            }
        });

        returnPageResetBt.setText("清空輸入");
        returnPageResetBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnPageResetBtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout returnTabLayout = new javax.swing.GroupLayout(returnTab);
        returnTab.setLayout(returnTabLayout);
        returnTabLayout.setHorizontalGroup(
            returnTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(returnTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(returnTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(returnTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(returnPageHKIDInput)
                    .addComponent(returnPageISBNInput))
                .addContainerGap())
            .addGroup(returnTabLayout.createSequentialGroup()
                .addGap(388, 388, 388)
                .addComponent(returnPageReturnBt, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84)
                .addComponent(returnPageResetBt)
                .addContainerGap(376, Short.MAX_VALUE))
        );
        returnTabLayout.setVerticalGroup(
            returnTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(returnTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(returnTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(returnPageHKIDInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(returnTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(returnPageISBNInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(returnTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(returnPageReturnBt)
                    .addComponent(returnPageResetBt))
                .addContainerGap(450, Short.MAX_VALUE))
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

        pageTab.addTab("新增/修改圖書", newBookTab);

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

        jLabel41.setText("用戶類型:");

        javax.swing.GroupLayout newCustomerTabLayout = new javax.swing.GroupLayout(newCustomerTab);
        newCustomerTab.setLayout(newCustomerTabLayout);
        newCustomerTabLayout.setHorizontalGroup(
            newCustomerTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newCustomerTabLayout.createSequentialGroup()
                .addGroup(newCustomerTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(newCustomerTabLayout.createSequentialGroup()
                        .addGap(462, 462, 462)
                        .addComponent(newCustomerPageSubmitBt, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 459, Short.MAX_VALUE))
                    .addGroup(newCustomerTabLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(newCustomerTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(newCustomerTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(newCustomerPageHKIDInput)
                            .addComponent(newCustomerPageNameInput)
                            .addComponent(newCustomerPageEmailInput)
                            .addComponent(newCustomerPagePhoneInput)
                            .addComponent(newCustomerPageAddressInput)
                            .addGroup(newCustomerTabLayout.createSequentialGroup()
                                .addComponent(newCustomerPageMaleRadio)
                                .addGap(18, 18, 18)
                                .addComponent(newCustomerPageFemaleRadio)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(newCustomerPageUserTypeInput, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
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
                .addGroup(newCustomerTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(newCustomerPageUserTypeInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(newCustomerPageSubmitBt)
                .addContainerGap(213, Short.MAX_VALUE))
        );

        pageTab.addTab("新增/修改客戶", newCustomerTab);

        jLabel42.setText("客戶類型:");

        newUserTypePageUserTypeInput.setEditable(true);
        newUserTypePageUserTypeInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newUserTypePageUserTypeInputActionPerformed(evt);
            }
        });

        jLabel43.setText("借書限額 (本):");

        jLabel44.setText("借書期限 (日):");

        jLabel45.setText("逾期罰款 (每本每日):");

        newUserTypePageMaxBooksInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                newUserTypePageMaxBooksInputKeyPressed(evt);
            }
        });

        newUserTypePageMaxDaysInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                newUserTypePageMaxDaysInputKeyPressed(evt);
            }
        });

        newUserTypePageDebtInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                newUserTypePageDebtInputKeyPressed(evt);
            }
        });

        newUserTypePageSubmitBt.setText("確定");
        newUserTypePageSubmitBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newUserTypePageSubmitBtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout newUserTypeTabLayout = new javax.swing.GroupLayout(newUserTypeTab);
        newUserTypeTab.setLayout(newUserTypeTabLayout);
        newUserTypeTabLayout.setHorizontalGroup(
            newUserTypeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newUserTypeTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(newUserTypeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(newUserTypeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(newUserTypePageUserTypeInput, 0, 799, Short.MAX_VALUE)
                    .addComponent(newUserTypePageMaxBooksInput)
                    .addComponent(newUserTypePageMaxDaysInput)
                    .addComponent(newUserTypePageDebtInput))
                .addContainerGap())
            .addGroup(newUserTypeTabLayout.createSequentialGroup()
                .addGap(461, 461, 461)
                .addComponent(newUserTypePageSubmitBt, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(466, Short.MAX_VALUE))
        );
        newUserTypeTabLayout.setVerticalGroup(
            newUserTypeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newUserTypeTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(newUserTypeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(newUserTypePageUserTypeInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(newUserTypeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(newUserTypePageMaxBooksInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(newUserTypeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(newUserTypePageMaxDaysInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(newUserTypeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(newUserTypePageDebtInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(newUserTypePageSubmitBt)
                .addContainerGap(354, Short.MAX_VALUE))
        );

        pageTab.addTab("新增/修改客戶類型", newUserTypeTab);

        reportPageRefreshBt.setText("更新");
        reportPageRefreshBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportPageRefreshBtActionPerformed(evt);
            }
        });

        reportPageExportBt.setText("導出CSV");
        reportPageExportBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportPageExportBtActionPerformed(evt);
            }
        });

        reportPageInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportPageInputActionPerformed(evt);
            }
        });

        reportPageTable.setModel(new javax.swing.table.DefaultTableModel(
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
        reportPageTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        reportPageTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        reportPageTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                reportPageTableMousePressed(evt);
            }
        });
        jScrollPane5.setViewportView(reportPageTable);

        javax.swing.GroupLayout reportTabLayout = new javax.swing.GroupLayout(reportTab);
        reportTab.setLayout(reportTabLayout);
        reportTabLayout.setHorizontalGroup(
            reportTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportTabLayout.createSequentialGroup()
                .addComponent(reportPageRefreshBt, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(reportPageExportBt)
                .addGap(18, 18, 18)
                .addComponent(reportPageInput, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        reportTabLayout.setVerticalGroup(
            reportTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportTabLayout.createSequentialGroup()
                .addGroup(reportTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reportPageRefreshBt)
                    .addComponent(reportPageExportBt)
                    .addComponent(reportPageInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE))
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
            // Enter
            String isbn = newBookPageISBNInput.getText().trim().toUpperCase();
            if (Utils.isValidISBN(isbn)) {
                newBookPageTitleInput.requestFocus();
            } else if (!isbn.equals("")) {
                JOptionPane.showMessageDialog(null, "無效的ISBN。");
            }
        } else if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) {
            // Esc
            newBookPageISBNInput.setText("");
            newBookPageTitleInput.setText("");
            newBookPageAuthorInput.setText("");
            newBookPagePublisherInput.setModel(new DefaultComboBoxModel<>(Utils.publisherChoices()));
            newBookPagePublisherInput.getEditor().setItem("");
            newBookPageEditionInput.setText("");
            newBookPageCostInput.setText("");
            newBookPageQuantityInput.setText("");
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
            // Enter
            searchBookPageSearch();
        } else if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) {
            // Esc
            searchBookPageISBNInput.setText("");
            searchBookPageTitleLabel.setText("");
            searchBookPageAuthorLabel.setText("");
            searchBookPagePublisherLabel.setText("");
            searchBookPageEditionLabel.setText("");
            searchBookPageCostLabel.setText("");
            searchBookPageQuantityLabel.setText("");
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
            // Enter
            String hkid = newCustomerPageHKIDInput.getText().trim().toUpperCase();
            if (Utils.isValidHKID(hkid)) {
                newCustomerPageNameInput.requestFocus();
            } else if (!hkid.equals("")) {
                JOptionPane.showMessageDialog(null, "無效的HKID。");
            }
        } else if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) {
            // Esc
            newCustomerPageHKIDInput.setText("");
            newCustomerPageNameInput.setText("");
            newCustomerPageEmailInput.setText("");
            newCustomerPagePhoneInput.setText("");
            newCustomerPageGenderBtGp.clearSelection();
            newCustomerPageAddressInput.setText("");
            newCustomerPageUserTypeInput.setModel(new DefaultComboBoxModel<>(Utils.userTypeChoices()));
            newCustomerPageHKIDInput.requestFocus();
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
            // Enter
            searchCustomerPageSearch();
        } else if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) {
            // Esc
            searchCustomerPageHKIDInput.setText("");
            searchCustomerPageNameLabel.setText("");
            searchCustomerPageEmailLabel.setText("");
            searchCustomerPagePhoneLabel.setText("");
            searchCustomerPageGenderLabel.setText("");
            searchCustomerPageAddressLabel.setText("");
            searchCustomerPageMoneyLabel.setText("");
            searchCustomerPageTypeLabel.setText("");
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
        
        PreparedStatement stmt = null;
        Savepoint savePoint = null;
        String msg = "";
        ResultSet rs;
        try {
            boolean needRollBack = false;
            int affectedRow;
            Main.conn.setAutoCommit(false);
            stmt = Main.conn.prepareStatement("select UT.debt_each_day from userinfo UI inner join usertype UT on UI.type_id = UT.type_id where HKID = ?");
            stmt.setString(1, hkid);
            savePoint = Main.conn.setSavepoint();

            // check if HKID exists in userinfo
            // and get the debt each day
            rs = stmt.executeQuery();
            double debtEachDay = -1;
            while (rs.next()) {
                debtEachDay = rs.getDouble("UT.debt_each_day");
            }
            if (debtEachDay == -1) {
                needRollBack = true;
                msg += "\n找不到此客戶。";
            }

            // check if all books are returned and
            // mark as paid for all TransactionDetail
            if (!needRollBack) {
                stmt = Main.conn.prepareStatement("select count(*) total from transaction T inner join transactiondetail TD on T.transaction_id = TD.transaction_id where T.HKID = ? and TD.return_date is NULL;");
                stmt.setString(1, hkid);
                rs = stmt.executeQuery();
                int temp = 0;
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
                stmt = Main.conn.prepareStatement("select * from transaction T inner join transactiondetail TD on T.transaction_id = TD.transaction_id where T.HKID = ? and (TD.return_date is NULL or TD.return_date > TD.due_date) and NOT T.paid");
                stmt.setString(1, hkid);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    dueDate = rs.getDate("TD.due_date");
                    returnDate = rs.getDate("TD.return_date");
                    returnDate = (returnDate == null ? Main.fakeTime.getDate() : returnDate);
                    if (returnDate.after(dueDate)) {
                        debt += Utils.daysDifference(dueDate, returnDate) * debtEachDay;
                    }
                }
                if (debt == 0) {
                    // no any debt need to pay
                    needRollBack = true;
                    msg += "\n此客戶沒有任何未還罰款。";
                }
            }
            
            if (!needRollBack) {
                // clear the debt
                stmt = Main.conn.prepareStatement("update transaction set paid = true where HKID = ?");
                stmt.setString(1, hkid);
                stmt.executeUpdate();
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
            msg = "無法交還罰款，原因如下：" + msg;
            JOptionPane.showMessageDialog(null, msg);
        }
    }//GEN-LAST:event_searchCustomerPagePayBtActionPerformed

    private void returnPageHKIDInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_returnPageHKIDInputKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            // Enter
            String hkid = returnPageHKIDInput.getText().trim().toUpperCase();
            if (Utils.isValidHKID(hkid)) {
                returnPageISBNInput.requestFocus();
            } else if (!hkid.equals("")) {
                JOptionPane.showMessageDialog(null, "無效的HKID。");
            }
        } else if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) {
            // Esc
            returnPageHKIDInput.setText("");
            returnPageISBNInput.setText("");
        }
    }//GEN-LAST:event_returnPageHKIDInputKeyPressed

    private void returnPageISBNInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_returnPageISBNInputKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            // Enter
            returnPageReturn();
        } else if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) {
            // Esc
            returnPageHKIDInput.setText("");
            returnPageISBNInput.setText("");
            returnPageHKIDInput.requestFocus();
        }
    }//GEN-LAST:event_returnPageISBNInputKeyPressed

    private void returnPageReturnBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnPageReturnBtActionPerformed
        returnPageReturn();
    }//GEN-LAST:event_returnPageReturnBtActionPerformed

    private void returnPageResetBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnPageResetBtActionPerformed
        returnPageHKIDInput.setText("");
        returnPageISBNInput.setText("");
        returnPageHKIDInput.requestFocus();
    }//GEN-LAST:event_returnPageResetBtActionPerformed

    private void newUserTypePageUserTypeInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newUserTypePageUserTypeInputActionPerformed
        String typeName = newUserTypePageUserTypeInput.getEditor().getItem().toString().trim();
        if (typeName.equals("")) {
            return;
        }
        
        PreparedStatement stmt = null;
        int typeID = -1, maxBooks = 0, maxDays = 0;
        double debt = 0;
        ResultSet rs;
        try{
            stmt = Main.conn.prepareStatement("select * from usertype where type_name = ?");
            stmt.setString(1, typeName);
            // try to find the usertype information (it can be empty set)
            rs = stmt.executeQuery();
            while (rs.next()) {
                typeID = rs.getInt("type_id");
                maxBooks = rs.getInt("max_books_borrow");
                maxDays = rs.getInt("max_days_borrow");
                debt = rs.getDouble("debt_each_day");
            }
            
            if (typeID != -1) {
                // usertype exists
                newUserTypePageMaxBooksInput.setText(Integer.toString(maxBooks));
                newUserTypePageMaxDaysInput.setText(Integer.toString(maxDays));
                newUserTypePageDebtInput.setText(Double.toString(debt));
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
    }//GEN-LAST:event_newUserTypePageUserTypeInputActionPerformed

    private void newUserTypePageMaxBooksInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_newUserTypePageMaxBooksInputKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER && !newUserTypePageMaxBooksInput.getText().trim().equals("")) {
            newUserTypePageMaxDaysInput.requestFocus();
        }
    }//GEN-LAST:event_newUserTypePageMaxBooksInputKeyPressed

    private void newUserTypePageMaxDaysInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_newUserTypePageMaxDaysInputKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER && !newUserTypePageMaxDaysInput.getText().trim().equals("")) {
            newUserTypePageDebtInput.requestFocus();
        }
    }//GEN-LAST:event_newUserTypePageMaxDaysInputKeyPressed

    private void newUserTypePageDebtInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_newUserTypePageDebtInputKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER && !newUserTypePageDebtInput.getText().trim().equals("")) {
            newUserTypePageSubmit();
        }
    }//GEN-LAST:event_newUserTypePageDebtInputKeyPressed

    private void newUserTypePageSubmitBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newUserTypePageSubmitBtActionPerformed
        newUserTypePageSubmit();
    }//GEN-LAST:event_newUserTypePageSubmitBtActionPerformed

    private void reportPageInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportPageInputActionPerformed
        reportPageTableUpdate(reportPageInput.getSelectedIndex());
    }//GEN-LAST:event_reportPageInputActionPerformed

    private void reportPageRefreshBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportPageRefreshBtActionPerformed
        reportPageTableUpdate(reportPageInput.getSelectedIndex());
    }//GEN-LAST:event_reportPageRefreshBtActionPerformed

    private void reportPageExportBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportPageExportBtActionPerformed
        String filename = Utils.exportCSV(Main.REPORT_DATA[reportPageInput.getSelectedIndex()].getFilename(), reportPageTable);
        JOptionPane.showMessageDialog(null, (filename == null ? "無法導出CSV。" : "已導出CSV: " + filename));
    }//GEN-LAST:event_reportPageExportBtActionPerformed

    private void reportPageTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportPageTableMousePressed
        JTable table = (JTable) evt.getSource();
        int row = table.rowAtPoint(evt.getPoint());
        int index = reportPageInput.getSelectedIndex();
        if (evt.getClickCount() == 2 && table.getSelectedRow() != -1) {
            if (index == 0) {
                // 所有未還欠書的客戶
                String hkid = table.getValueAt(row, Utils.tableColumnNameToIndex(table, "HKID")).toString();
                // change page to searchCustomerPage and show search result of hkid
                searchCustomerPageHKIDInput.setText(hkid);
                CardLayout card = (CardLayout)searchCustomerTab.getLayout();
                card.show(searchCustomerTab, "searchCustomerPage");
                searchCustomerPageSearch();
                pageTab.setSelectedComponent(searchCustomerTab);
            } else if (index == 1) {
                
            }
        }
    }//GEN-LAST:event_reportPageTableMousePressed

    private void borrowPageBorrowBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrowPageBorrowBtActionPerformed
        borrowPageBorrow();
    }//GEN-LAST:event_borrowPageBorrowBtActionPerformed

    private void borrowPageISBNInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_borrowPageISBNInputKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            // Enter
            borrowPageAdd();
        } else if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) {
            // Esc
            borrowPageISBNInput.setText("");
        }
    }//GEN-LAST:event_borrowPageISBNInputKeyPressed

    private void borrowPageHKIDInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_borrowPageHKIDInputKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            // Enter
            String hkid = borrowPageHKIDInput.getText().trim().toUpperCase();
            if (Utils.isValidHKID(hkid)) {
                borrowPageISBNInput.requestFocus();
            } else if (!hkid.equals("")) {
                JOptionPane.showMessageDialog(null, "無效的HKID。");
            }
        } else if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) {
            // Esc
            resetBorrowPage();
        }
    }//GEN-LAST:event_borrowPageHKIDInputKeyPressed

    private void borrowPageResetBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrowPageResetBtActionPerformed
        resetBorrowPage();
    }//GEN-LAST:event_borrowPageResetBtActionPerformed

    private void borrowPageAddBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrowPageAddBtActionPerformed
        borrowPageAdd();
    }//GEN-LAST:event_borrowPageAddBtActionPerformed

    private void borrowPageTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_borrowPageTableMousePressed
        JTable table = (JTable) evt.getSource();
        int row = table.rowAtPoint(evt.getPoint());
        if (evt.getClickCount() == 2 && table.getSelectedRow() != -1) {
            Main.borrowPageBooks.remove(row);
            
            updateBorrowPageTable();
        }
    }//GEN-LAST:event_borrowPageTableMousePressed

    private void allBooksSearchBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allBooksSearchBtActionPerformed
        allBooksSearch();
    }//GEN-LAST:event_allBooksSearchBtActionPerformed

    private void allBooksSearchInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_allBooksSearchInputKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            // Enter
            allBooksSearch();
        } else if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) {
            // Esc
            allBooksRefresh();
        }
    }//GEN-LAST:event_allBooksSearchInputKeyPressed
    
    public void init() {
        // remove useless label text
        searchBookPageTitleLabel.setText("");
        searchBookPageAuthorLabel.setText("");
        searchBookPagePublisherLabel.setText("");
        searchBookPageEditionLabel.setText("");
        searchBookPageCostLabel.setText("");
        searchBookPageQuantityLabel.setText("");
        searchCustomerPageTypeLabel.setText("");
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
        allCustomersTableModel.addColumn("用戶類型");
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
        
        // set up table columns for borrowPageTable
        DefaultTableModel borrowPageTableModel = (DefaultTableModel) borrowPageTable.getModel();
        borrowPageTableModel.setColumnCount(0);
        borrowPageTableModel.addColumn("ISBN");
        borrowPageTableModel.addColumn("書名");
        borrowPageTableModel.addColumn("出版社");
        borrowPageTableModel.addColumn("作者");
        borrowPageTableModel.addColumn("版本");
        
        
        // show book info on allBooksTable
        allBooksRefresh();
        
        // show customer info on allCustomersTable
        allCustomersRefresh();
        
        // clear updateBorrowPageTable
        updateBorrowPageTable();
        
        // set editable false to tables
        allBooksTable.setDefaultEditor(Object.class, null);
        allCustomersTable.setDefaultEditor(Object.class, null);
        bookBorrowRecordTable.setDefaultEditor(Object.class, null);
        customerBorrowRecordTable.setDefaultEditor(Object.class, null);
        reportPageTable.setDefaultEditor(Object.class, null);
        borrowPageTable.setDefaultEditor(Object.class, null);
        
        // update combo box choices
        newBookPagePublisherInput.setModel(new DefaultComboBoxModel<>(Utils.publisherChoices()));
        newCustomerPageUserTypeInput.setModel(new DefaultComboBoxModel<>(Utils.userTypeChoices()));
        newUserTypePageUserTypeInput.setModel(new DefaultComboBoxModel<>(Utils.userTypeChoices()));
        reportPageInput.setModel(new DefaultComboBoxModel<>(Utils.reportChoices()));
        
        // update report
        reportPageTableUpdate(0);
        
        // select a default tab
        pageTab.setSelectedComponent(allBooksTab);
    }
    
    public void allCustomersRefresh() {
        // clear table
        DefaultTableModel tableModel = (DefaultTableModel) allCustomersTable.getModel();
        tableModel.setRowCount(0);
        
        PreparedStatement stmt = null;
        try{
            stmt = Main.conn.prepareStatement("select * from userinfo UI inner join usertype UT on UI.type_id = UT.type_id order by name");
            ResultSet rs = stmt.executeQuery();
            String hkid, type, name, email, phone, gender, address;
            while (rs.next()) {
                hkid = rs.getString("HKID");
                name = rs.getString("name");
                type = rs.getString("UT.type_name");
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
                tableModel.addRow(new String[] {hkid, name, type, email, phone, (gender.equals("M") ? "男" : "女"), address});
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
        
        // clear search input
        allBooksSearchInput.setText("");
        
        allBooksExportBt.setEnabled(true);
        
        PreparedStatement stmt = null;
        ArrayList<Book> books = new ArrayList<>();
        Hashtable<String, ArrayList<String>> tempAuthors = new Hashtable<>();
        try{
            stmt = Main.conn.prepareStatement("select * from bookinfo BI left join bookauthor BA on BI.isbn = BA.isbn order by BI.title");
            ResultSet rs = stmt.executeQuery();
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if (stmt != null) stmt.close();
            }catch(SQLException se2){}
        }
        
        allBooksSearchInput.requestFocus();
    }
    
    private void changeFakeTime() {
        if (!Main.fakeTime.setFakeTime(settingPageDateInput.getText().trim())) {
            // invalid format
            JOptionPane.showMessageDialog(null, "格式錯誤。");
        }
    }
    
    private void returnPageReturn() {
        String hkid = returnPageHKIDInput.getText().trim().toUpperCase();
        String isbn = returnPageISBNInput.getText().trim().toUpperCase();
        if (!Utils.isValidHKID(hkid)) {
            JOptionPane.showMessageDialog(null, (hkid.equals("") ? "請輸入HKID。" : "無效的HKID。"));
            return;
        }
        if (!Utils.isValidISBN(isbn)) {
            JOptionPane.showMessageDialog(null, (isbn.equals("") ? "請輸入ISBN。" : "無效的ISBN。"));
            return;
        }
        
        PreparedStatement stmt = null;
        String msg = "";
        Savepoint savePoint = null;
        ResultSet rs;
        try {
            boolean needRollBack = false;
            int affectedRow;
            Main.conn.setAutoCommit(false);
            savePoint = Main.conn.setSavepoint();

            // check if customer exists
            stmt = Main.conn.prepareStatement("select hkid from userinfo where hkid = ?");
            stmt.setString(1, hkid);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                // customer does NOT exists
                needRollBack = true;
                msg += "\n找不到此客戶。";
            }
            
            // update the quantity
            stmt = Main.conn.prepareStatement("update bookinfo set quantity = quantity+1 where isbn = ?");
            stmt.setString(1, isbn);
            affectedRow = stmt.executeUpdate();
            if (affectedRow == 0) {
                // cannot find the book
                needRollBack = true;
                msg += "\n找不到此書。";
            }

            // find which detail_id is the book from
            stmt = Main.conn.prepareStatement("select TD.detail_id from transaction T inner join transactiondetail TD on T.transaction_id = TD.transaction_id where T.HKID = ? and TD.ISBN = ? and return_date is NULL order by TD.due_date, TD.detail_id");
            stmt.setString(1, hkid);
            stmt.setString(2, isbn);
            rs = stmt.executeQuery();
            int detailID = -1;
            if (rs.next()) {
                detailID = rs.getInt("detail_id");
            }
            if (detailID == -1) {
                // user does not borrow this book
                needRollBack = true;
                msg += "\n此客戶沒有借這本書。";
            }
            
            // update TransactionDetail
            if (msg.equals("")) {
                stmt = Main.conn.prepareStatement("update transactiondetail set return_date = ? where detail_id = ?");
                stmt.setString(1, Main.fakeTime.formatDate());
                stmt.setInt(2, detailID);
                affectedRow = stmt.executeUpdate();
                if (affectedRow == 0) {
                    needRollBack = true;
                    msg += "\n無法更新還書資料，請再試一次。";
                }
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
            // clear isbn input
            returnPageISBNInput.setText("");
            
            // focus isbn input
            returnPageISBNInput.requestFocus();
        } else {
            // something wrong
            msg = "無法還書，原因如下：" + msg;
            JOptionPane.showMessageDialog(null, msg);
        }
    }
    
    private void borrowPageAdd() {
        String isbn = borrowPageISBNInput.getText().trim().toUpperCase();
        if (isbn.equals("")) {
            return;
        }
        if (!Utils.isValidISBN(isbn)) {
            JOptionPane.showMessageDialog(null, "無效的ISBN。");
            return;
        }
        
        PreparedStatement stmt = null;
        String title = null, publisher = null, author = null;
        int edition = -1, quantity = -1;
        double cost = -1;
        ArrayList<String> tempAuthors = new ArrayList<>();
        ResultSet rs = null;
        try {
            // check if isbn exists in database
            stmt = Main.conn.prepareStatement("select * from bookinfo BI left join bookauthor BA on BI.isbn = BA.isbn where BI.isbn = ?");
            stmt.setString(1, isbn);
            rs = stmt.executeQuery();
            while (rs.next()) {
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
                    tempAuthors.add(author);
                }
            }
            
            if (title == null) {
                // isbn not found
                JOptionPane.showMessageDialog(null, "找不到此書。");
                return;
            }
            
            Main.borrowPageBooks.add(new Book(isbn, title, publisher, edition, cost, quantity, tempAuthors));
            
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
        
        // clear and focus the input field
        borrowPageISBNInput.setText("");
        borrowPageISBNInput.requestFocus();
        
        // update the table
        updateBorrowPageTable();
    }
    
    private void updateBorrowPageTable() {
        DefaultTableModel tableModel = (DefaultTableModel) borrowPageTable.getModel();
        tableModel.setRowCount(0);
        Book book;
        for (int i = 0, n = Main.borrowPageBooks.size(); i < n; i++) {
            book = Main.borrowPageBooks.get(i);
            tableModel.addRow(new String[] {book.getISBN(), book.getTitle(), book.getPublisher(), book.joinAuthors(", "), Integer.toString(book.getEdition())});
        }
    }
    
    private void borrowPageBorrow() {
        String hkid = borrowPageHKIDInput.getText().trim().toUpperCase();
        if (!Utils.isValidHKID(hkid)) {
            JOptionPane.showMessageDialog(null, (hkid.equals("") ? "請輸入HKID。" : "無效的HKID。"));
            return;
        }
        if (Main.borrowPageBooks.isEmpty()) {
            JOptionPane.showMessageDialog(null, "請加入至少一本圖書。");
            return;
        }
        
        String msg = "";
        Book book;
        
        for (int i = 0, n = Main.borrowPageBooks.size(); i < n; i++) {
            Main.borrowPageBooks.get(i).setNeedToCheck(true);
        }
        
        int canBorrowNum = -1, maxBorrowNum = -1, maxDaysBorrow = 0;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // check if isbn exists in database
            for (int i = 0, n = Main.borrowPageBooks.size(); i < n; i++) {
                book = Main.borrowPageBooks.get(i);
                if (!book.getNeedToCheck()) {
                    continue;
                }
                stmt = Main.conn.prepareStatement("select ISBN from bookinfo where ISBN = ?");
                stmt.setString(1, book.getISBN());
                rs = stmt.executeQuery();
                book.setNeedToCheck(rs.next());
                if (!book.getNeedToCheck()) {
                    // ISBN valid but not exists
                    msg += "\n找不到此書：" + book.getISBN();
                }
            }
            if (rs != null) {
                rs.close();
            }
            
            // check if customer exists and get the
            // max books borrow
            stmt = Main.conn.prepareStatement("select UT.max_books_borrow, UT.max_days_borrow from userinfo UI inner join usertype UT on UI.type_id = UT.type_id where hkid = ?");
            stmt.setString(1, hkid);
            rs = stmt.executeQuery();
            if (rs.next()) {
                maxBorrowNum = rs.getInt("UT.max_books_borrow");
                maxDaysBorrow = rs.getInt("UT.max_days_borrow");
            } else {
                // customer does NOT exists
                msg += "\n找不到此客戶。";
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
        if (maxBorrowNum != -1) {
            try{
                stmt = Main.conn.prepareStatement("select count(detail_id) total from transaction T left join transactiondetail TD on T.transaction_id = TD.transaction_id where hkid = ? and return_date is NULL");
                stmt.setString(1, hkid);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    canBorrowNum = maxBorrowNum - rs.getInt("total");
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
            for (int i = 0, n = Main.borrowPageBooks.size(); i < n; i++) {
                if (Main.borrowPageBooks.get(i).getNeedToCheck()) {
                    canBorrowNum--;
                }
            }

            if (canBorrowNum < 0) {
                // not enough quota
                for (int i = 0, n = Main.borrowPageBooks.size(); i < n; i++) {
                    Main.borrowPageBooks.get(i).setNeedToCheck(false);
                }
                msg += "\n此客戶沒有足夠配額，此客戶只可同時借" + maxBorrowNum + "本書。";
            }
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
                savePoint = Main.conn.setSavepoint();
                
                // remove quantity of each book
                for (int i = 0, n = Main.borrowPageBooks.size(); i < n; i++) {
                    book = Main.borrowPageBooks.get(i);
                    if (!book.getNeedToCheck()) {
                        continue;
                    }
                    stmt = Main.conn.prepareStatement("update bookinfo set quantity = quantity-1 where isbn = ? and quantity > 0");
                    stmt.setString(1, book.getISBN());
                    affectedRow = stmt.executeUpdate();
                    if (affectedRow == 0) {
                        // quantity is 0
                        needRollBack = true;
                        msg += "\n沒有足夠存貨：" + book.getISBN();
                    }
                }
                
                // insert to table Transaction
                stmt = Main.conn.prepareStatement("insert into transaction (HKID, borrow_date, paid) values (?, ?, ?)");
                stmt.setString(1, hkid);
                stmt.setString(2, Main.fakeTime.formatDate());
                stmt.setBoolean(3, false);
                affectedRow = stmt.executeUpdate();
                if (affectedRow == 0) {
                    // cannot insert to table Transaction
                    needRollBack = true;
                    msg += "\n無法插入交易資料。";
                }
                
                // retrieve transaction_id
                int transaction_id = -1;
                stmt = Main.conn.prepareStatement("select LAST_INSERT_ID() ID");
                rs = stmt.executeQuery();
                while (rs.next()) {
                    transaction_id = rs.getInt("ID");
                }
                rs.close();
                
                if (transaction_id == -1) {
                    needRollBack = true;
                    msg += "\n無法取回交易ID。";
                } else {
                    // insert transaction detail of each book
                    String dueDateStr = Utils.toString(Utils.addDays(Main.fakeTime.getDate(), maxDaysBorrow));
                    for (int i = 0, n = Main.borrowPageBooks.size(); i < n; i++) {
                        book = Main.borrowPageBooks.get(i);
                        if (!book.getNeedToCheck()) {
                            continue;
                        }
                        stmt = Main.conn.prepareStatement("insert into transactiondetail (transaction_id, ISBN, due_date) VALUES (?, ?, ?)");
                        stmt.setInt(1, transaction_id);
                        stmt.setString(2, book.getISBN());
                        stmt.setString(3, dueDateStr);
                        affectedRow = stmt.executeUpdate();
                        if (affectedRow == 0) {
                            // cannot insert TransactionDetail
                            needRollBack = true;
                            msg += "\n無法插入交易詳細資料：" + book.getISBN() + "。";
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
            borrowPageISBNInput.setText("");
            
            // clear books
            Main.borrowPageBooks.clear();
            updateBorrowPageTable();
            
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
        PreparedStatement stmt = null;
        try{
            stmt = Main.conn.prepareStatement("select * from transaction T inner join transactiondetail TD on T.transaction_id = TD.transaction_id inner join bookinfo BI on TD.ISBN = BI.ISBN right join userinfo UI on T.HKID = UI.HKID where UI.HKID = ? order by T.transaction_id desc, TD.return_date desc");
            stmt.setString(1, hkid);
            ResultSet rs = stmt.executeQuery();
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
        PreparedStatement stmt = null;
        try{
            stmt = Main.conn.prepareStatement("select * from transaction T inner join transactiondetail TD on T.transaction_id = TD.transaction_id inner join userinfo UI on T.HKID = UI.HKID right join bookinfo BI on TD.ISBN = BI.ISBN where BI.ISBN = ? order by T.transaction_id desc, TD.return_date desc");
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();
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
        
        PreparedStatement stmt = null;
        try{
            stmt = Main.conn.prepareStatement("select * from userinfo UI inner join usertype UT on UI.type_id = UT.type_id where UI.hkid = ?");
            stmt.setString(1, hkid);
            ResultSet rs = stmt.executeQuery();
            String name = null, type = null, email = null, phone = null, gender = null, address = null;
            double debtEachDay = 0;
            while (rs.next()) {
                hkid = rs.getString("HKID");
                name = rs.getString("name");
                type = rs.getString("UT.type_name");
                debtEachDay = rs.getDouble("UT.debt_each_day");
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
            stmt = Main.conn.prepareStatement("select * from transaction T inner join transactiondetail TD on T.transaction_id = TD.transaction_id where T.HKID = ? and (TD.return_date is NULL or TD.return_date > TD.due_date) and NOT T.paid");
            stmt.setString(1, hkid);
            rs = stmt.executeQuery();
            while (rs.next()) {
                dueDate = rs.getDate("TD.due_date");
                returnDate = rs.getDate("TD.return_date");
                returnDate = (returnDate == null ? Main.fakeTime.getDate() : returnDate);
                if (returnDate.after(dueDate)) {
                    debt += Utils.daysDifference(dueDate, returnDate) * debtEachDay;
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
            searchCustomerPageTypeLabel.setText(type);
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
        
        PreparedStatement stmt = null;
        ArrayList<String> authors = new ArrayList<>();
        try{
            stmt = Main.conn.prepareStatement("select * from bookinfo BI left join bookauthor BA on BI.isbn = BA.isbn where BI.isbn = ?");
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();
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
        String typeName = newCustomerPageUserTypeInput.getEditor().getItem().toString().trim();
        int typeID = -1;
        
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
        if (typeName.equals("")) {
            JOptionPane.showMessageDialog(null, "請選擇用戶類型。");
            return;
        }

        // valid data
        String gender = (m ? "M" : "F");
        
        
        PreparedStatement stmt = null;
        Savepoint savePoint = null;
        ResultSet rs;
        boolean HKIDExist;
        try{
            // find type_id
            stmt = Main.conn.prepareStatement("select type_id from usertype where type_name = ?");
            stmt.setString(1, typeName);
            rs = stmt.executeQuery();
            while (rs.next()) {
                typeID = rs.getInt("type_id");
            }
            
            if (typeID == -1) {
                // usertype not exists
                JOptionPane.showMessageDialog(null, "找不到此用戶類型。");
                return;
            }
            
            boolean needRollBack = false;
            Main.conn.setAutoCommit(false);
            savePoint = Main.conn.setSavepoint();
            
            // check if HKID already exists in table
            stmt = Main.conn.prepareStatement("select hkid from userinfo where hkid = ?");
            stmt.setString(1, hkid);
            rs = stmt.executeQuery();
            HKIDExist = rs.next();
            stmt.close();
            if (HKIDExist) {
                // existing customer
                stmt = Main.conn.prepareStatement("update userinfo set type_id = ?, name = ?, email = ?, phone = ?, gender = ?, address = ? where hkid = ?");
                stmt.setInt(1, typeID);
                stmt.setString(2, name);
                if (email.equals("")) {
                    stmt.setNull(3, java.sql.Types.NULL);
                } else {
                    stmt.setString(3, email);
                }
                if (phone.equals("")) {
                    stmt.setNull(4, java.sql.Types.NULL);
                } else {
                    stmt.setString(4, phone);
                }
                stmt.setString(5, gender);
                stmt.setString(6, address);
                stmt.setString(7, hkid);
            } else {
                // new customer
                stmt = Main.conn.prepareStatement("insert into userinfo values (?, ?, ?, ?, ?, ?, ?)");
                stmt.setString(1, hkid);
                stmt.setInt(2, typeID);
                stmt.setString(3, name);
                if (email.equals("")) {
                    stmt.setNull(4, java.sql.Types.NULL);
                } else {
                    stmt.setString(4, email);
                }
                if (phone.equals("")) {
                    stmt.setNull(5, java.sql.Types.NULL);
                } else {
                    stmt.setString(5, phone);
                }
                stmt.setString(6, gender);
                stmt.setString(7, address);
            }
            stmt.executeUpdate();
            
            rs.close();
            stmt.close();
            if (needRollBack) {
                Main.conn.rollback(savePoint);
            }
            Main.conn.commit();
            
            // clear the input box
            newCustomerPageHKIDInput.setText("");
            newCustomerPageNameInput.setText("");
            newCustomerPageEmailInput.setText("");
            newCustomerPagePhoneInput.setText("");
            newCustomerPageGenderBtGp.clearSelection();
            newCustomerPageAddressInput.setText("");
            newCustomerPageUserTypeInput.setModel(new DefaultComboBoxModel<>(Utils.userTypeChoices()));
            newCustomerPageHKIDInput.requestFocus();
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
        if (publisher.equals("")) {
            JOptionPane.showMessageDialog(null, "請輸入出版社。");
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
        edition = Integer.parseInt(newBookPageEditionInput.getText().trim());
        if (edition < 0) {
            JOptionPane.showMessageDialog(null, "版本不能是負數。");
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
        cost = Double.parseDouble(newBookPageCostInput.getText().trim());
        if (cost < 0) {
            JOptionPane.showMessageDialog(null, "成本價不能是負數。");
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
        quantity = Integer.parseInt(newBookPageQuantityInput.getText().trim());
        if (quantity < 0) {
            JOptionPane.showMessageDialog(null, "存貨數量不能是負數。");
            return;
        }
        
        
        // valid data
        String[] authors = (authorStr.equals("") ? new String[] {} : authorStr.split(",|，"));
        for (int i = 0, n = authors.length; i < n; i++) {
            authors[i] = authors[i].trim();
        }
        
        PreparedStatement stmt = null;
        boolean ISBNExist = false;
        // check if ISBN already exists in table
        try{
            stmt = Main.conn.prepareStatement("select isbn from bookinfo where isbn = ?");
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();
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
                stmt = Main.conn.prepareStatement("update bookinfo set title = ?, publisher = ?, edition = ?, cost = ?, quantity = ? where isbn = ?");
                stmt.setString(1, title);
                stmt.setString(2, publisher);
                stmt.setInt(3, edition);
                stmt.setDouble(4, cost);
                stmt.setInt(5, quantity);
                stmt.setString(6, isbn);
                stmt.executeUpdate();
                stmt.close();
                stmt = Main.conn.prepareStatement("delete from bookauthor where isbn = ?");
                stmt.setString(1, isbn);
                stmt.executeUpdate();
                stmt.close();
                for (int i = 0, n = authors.length; i < n; i++) {
                    stmt = Main.conn.prepareStatement("insert into bookauthor values (?, ?)");
                    stmt.setString(1, isbn);
                    stmt.setString(2, authors[i]);
                    stmt.executeUpdate();
                    stmt.close();
                }

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
                stmt = Main.conn.prepareStatement("insert into bookinfo values (?, ?, ?, ?, ?, ?)");
                stmt.setString(1, isbn);
                stmt.setString(2, title);
                stmt.setString(3, publisher);
                stmt.setInt(4, edition);
                stmt.setDouble(5, cost);
                stmt.setInt(6, quantity);
                stmt.executeUpdate();
                stmt.close();
                for (int i = 0, n = authors.length; i < n; i++) {
                    stmt = Main.conn.prepareStatement("insert into bookauthor values (?, ?)");
                    stmt.setString(1, isbn);
                    stmt.setString(2, authors[i]);
                    stmt.executeUpdate();
                    stmt.close();
                }

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
    
    private void newUserTypePageSubmit() {
        String typeName = newUserTypePageUserTypeInput.getEditor().getItem().toString().trim();
        String maxBooksStr = newUserTypePageMaxBooksInput.getText().trim();
        String maxDaysStr = newUserTypePageMaxDaysInput.getText().trim();
        String debtStr = newUserTypePageDebtInput.getText().trim();
        int maxBooks, maxDays;
        double debt;
        
        if (typeName.equals("")) {
            JOptionPane.showMessageDialog(null, "請輸入客戶類型。");
            return;
        }
        if (maxBooksStr.equals("")) {
            JOptionPane.showMessageDialog(null, "請輸入借書限額。");
            return;
        }
        if (maxDaysStr.equals("")) {
            JOptionPane.showMessageDialog(null, "請輸入借書期限。");
            return;
        }
        if (debtStr.equals("")) {
            JOptionPane.showMessageDialog(null, "請輸入逾期罰款。");
            return;
        }
        if (!Utils.isInt(maxBooksStr)) {
            JOptionPane.showMessageDialog(null, "借書限額必須是整數。");
            return;
        }
        if (!Utils.isInt(maxDaysStr)) {
            JOptionPane.showMessageDialog(null, "借書期限必須是整數。");
            return;
        }
        if (!Utils.isDouble(debtStr)) {
            JOptionPane.showMessageDialog(null, "逾期罰款必須是數字。");
            return;
        }
        maxBooks = Integer.parseInt(maxBooksStr);
        maxDays = Integer.parseInt(maxDaysStr);
        debt = Double.parseDouble(debtStr);
        if (maxBooks < 0) {
            JOptionPane.showMessageDialog(null, "借書限額不能是負數。");
            return;
        }
        if (maxDays < 0) {
            JOptionPane.showMessageDialog(null, "借書期限不能是負數。");
            return;
        }
        if (debt < 0) {
            JOptionPane.showMessageDialog(null, "逾期罰款不能是負數。");
            return;
        }
        
        // valid data
        PreparedStatement stmt = null;
        int typeID = -1;
        // check if user type exists in table already
        try{
            stmt = Main.conn.prepareStatement("select type_id from usertype where type_name = ?");
            stmt.setString(1, typeName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                typeID = rs.getInt("type_id");
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
        
        try{
            if (typeID != -1) {
                // existing user type
                stmt = Main.conn.prepareStatement("update usertype set type_name = ?, max_books_borrow = ?, max_days_borrow = ?, debt_each_day = ? where type_id = ?");
                stmt.setString(1, typeName);
                stmt.setInt(2, maxBooks);
                stmt.setInt(3, maxDays);
                stmt.setDouble(4, debt);
                stmt.setInt(5, typeID);
            } else {
                // new user type
                stmt = Main.conn.prepareStatement("insert into usertype (type_name, max_books_borrow, max_days_borrow, debt_each_day) values (?, ?, ?, ?)");
                stmt.setString(1, typeName);
                stmt.setInt(2, maxBooks);
                stmt.setInt(3, maxDays);
                stmt.setDouble(4, debt);
            }
            stmt.executeUpdate();
            stmt.close();
            
            // update type choice
            newCustomerPageUserTypeInput.setModel(new DefaultComboBoxModel<>(Utils.userTypeChoices()));
            newUserTypePageUserTypeInput.setModel(new DefaultComboBoxModel<>(Utils.userTypeChoices()));
            newCustomerPageUserTypeInput.getEditor().setItem("");
            newUserTypePageUserTypeInput.getEditor().setItem("");
            
            // clear the input box
            newUserTypePageMaxBooksInput.setText("");
            newUserTypePageMaxDaysInput.setText("");
            newUserTypePageDebtInput.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if (stmt != null) stmt.close();
            }catch(SQLException se2){}
        }
    }
    
    private void resetBorrowPage() {
        borrowPageHKIDInput.setText("");
        borrowPageISBNInput.setText("");

        // clear all books
        Main.borrowPageBooks.clear();
        updateBorrowPageTable();
        
        borrowPageHKIDInput.requestFocus();
    }
    
    private void allBooksSearch() {
        String toSearch = allBooksSearchInput.getText().trim().toLowerCase();
        if (toSearch.equals("")) {
            allBooksExportBt.setEnabled(true);
            allBooksRefresh();
            return;
        }
        
        // format toSearch
        toSearch = "%" + toSearch.replaceAll("\\%", "\\\\%").replaceAll("\\*", "%") + "%";
        
        // clear table
        DefaultTableModel tableModel = (DefaultTableModel) allBooksTable.getModel();
        tableModel.setRowCount(0);
        
        allBooksExportBt.setEnabled(false);
        
        PreparedStatement stmt = null;
        ArrayList<Book> books = new ArrayList<>();
        Hashtable<String, ArrayList<String>> tempAuthors = new Hashtable<>();
        try{
            stmt = Main.conn.prepareStatement("select * from bookinfo BI left join bookauthor BA on BI.isbn = BA.isbn where lower(title) like lower(?) order by BI.title");
            stmt.setString(1, toSearch);
            ResultSet rs = stmt.executeQuery();
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if (stmt != null) stmt.close();
            }catch(SQLException se2){}
        }
        
        allBooksSearchInput.requestFocus();
    }
    
    private void reportPageTableUpdate(int index) {
        // clear columns for reportPageTable
        DefaultTableModel reportPageTableModel = (DefaultTableModel) reportPageTable.getModel();
        reportPageTableModel.setColumnCount(0);
        reportPageTableModel.setRowCount(0);
        
        // set up table columns for reportPageTable
        Report reportDatum;
        reportDatum = Main.REPORT_DATA[index];
        for (int i = 0, n = reportDatum.getColumnNames().length; i < n; i++) {
            reportPageTableModel.addColumn(reportDatum.getColumnNames()[i]);
        }
        
        
        PreparedStatement stmt = null;
        try {
            String hkid, name, bookNumStr, isbn;
            ResultSet rs;
            if (index == 0) {
                // 所有未還欠書的客戶
                stmt = Main.conn.prepareStatement("select UI.HKID, UI.name, count(*) book_num from userinfo UI inner join transaction T on UI.HKID = T.HKID inner join transactiondetail TD on T.transaction_id = TD.transaction_id where due_date < ? and return_date is NULL group by UI.HKID order by book_num desc");
                stmt.setString(1, Main.fakeTime.formatDate());
                rs = stmt.executeQuery();
                while (rs.next()) {
                    hkid = rs.getString("UI.HKID");
                    name = rs.getString("UI.name");
                    bookNumStr = Integer.toString(rs.getInt("book_num"));
                    
                    reportPageTableModel.addRow(new String[] {hkid, name, bookNumStr});
                }
                stmt.close();
                rs.close();
            } else if (index == 1) {
                //TODO
                stmt = Main.conn.prepareStatement("select * from bookinfo");
                //stmt.setString(1, "sth");
                rs = stmt.executeQuery();
                while (rs.next()) {
                    //hkid = rs.getString("HKID");
                    //name = rs.getString("name");
                    isbn = rs.getString("isbn");
                    reportPageTableModel.addRow(new String[] {isbn, "columne 2", "3", "four", "", "6"});
                }
                stmt.close();
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {}
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton allBooksExportBt;
    private javax.swing.JButton allBooksRefreshBt;
    private javax.swing.JButton allBooksSearchBt;
    private javax.swing.JTextField allBooksSearchInput;
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
    private javax.swing.JButton borrowPageAddBt;
    private javax.swing.JButton borrowPageBorrowBt;
    private javax.swing.JTextField borrowPageHKIDInput;
    private javax.swing.JTextField borrowPageISBNInput;
    private javax.swing.JButton borrowPageResetBt;
    private javax.swing.JTable borrowPageTable;
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
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
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
    private javax.swing.JComboBox<String> newCustomerPageUserTypeInput;
    private javax.swing.JPanel newCustomerTab;
    private javax.swing.JTextField newUserTypePageDebtInput;
    private javax.swing.JTextField newUserTypePageMaxBooksInput;
    private javax.swing.JTextField newUserTypePageMaxDaysInput;
    private javax.swing.JButton newUserTypePageSubmitBt;
    private javax.swing.JComboBox<String> newUserTypePageUserTypeInput;
    private javax.swing.JPanel newUserTypeTab;
    private javax.swing.JTabbedPane pageTab;
    private javax.swing.JButton reportPageExportBt;
    private javax.swing.JComboBox<String> reportPageInput;
    private javax.swing.JButton reportPageRefreshBt;
    private javax.swing.JTable reportPageTable;
    private javax.swing.JPanel reportTab;
    private javax.swing.JTextField returnPageHKIDInput;
    private javax.swing.JTextField returnPageISBNInput;
    private javax.swing.JButton returnPageResetBt;
    private javax.swing.JButton returnPageReturnBt;
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
    private javax.swing.JLabel searchCustomerPageTypeLabel;
    private javax.swing.JPanel searchCustomerTab;
    private javax.swing.JTextField settingPageDateInput;
    private javax.swing.JPanel settingTab;
    // End of variables declaration//GEN-END:variables

}
