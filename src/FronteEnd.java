import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FronteEnd {

    private JFrame frmDdeCharacter;
    private JTextField txtPlayerName;
    private JTextField textField;
    private JLabel lblRace;
    private JLabel lblClass;
    private JComboBox comBoxClass;
    private JLabel lblLvl;
    private JTable tableStats;

    private Character character = new Character();
    private CharacterDAO cDAO = new CharacterDAO();

    private int charStr = 10;
    private int charDex = 10;
    private int charCon = 10;
    private int charInt = 10;
    private int charWis = 10;
    private int charCha = 10;

    private int charStrMod = (int) Math.floor((charStr - 10) / 2);
    private int charDexMod = (int) Math.floor((charDex - 10) / 2);
    private int charConMod = (int) Math.floor((charCon - 10) / 2);
    private int charIntMod = (int) Math.floor((charInt - 10) / 2);
    private int charWisMod = (int) Math.floor((charWis - 10) / 2);
    private int charChaMod = (int) Math.floor((charCha - 10) / 2);

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FronteEnd window = new FronteEnd();
                    window.frmDdeCharacter.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public FronteEnd() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmDdeCharacter = new JFrame();
        frmDdeCharacter.setTitle("D&D 5e Character Manager");
        frmDdeCharacter.setBounds(100, 100, 450, 347);
        frmDdeCharacter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblPlayerName = new JLabel("Player Name: ");

        txtPlayerName = new JTextField();
        txtPlayerName.setColumns(10);

        JLabel lblCharacterName = new JLabel("Character Name: ");

        textField = new JTextField();
        textField.setColumns(10);

        lblRace = new JLabel("Race: ");

        JComboBox comBoxRace = new JComboBox();
        comBoxRace.setModel(new DefaultComboBoxModel(new String[]{"Select Race", "Dwarf", "Elf", "Halfling", "Human", "Dragonborn", "Gnome", "Half-Elf", "Half-Orc", "Tiefling"}));

        lblClass = new JLabel("Class: ");

        comBoxClass = new JComboBox();
        comBoxClass.setModel(new DefaultComboBoxModel(new String[]{"Select Class", "Barbarian", "Bard", "Cleric", "Druid", "Fighter", "Monk", "Paladin", "Ranger", "Rogue", "Sorcerer", "Warlock", "Wizard"}));

        lblLvl = new JLabel("Level: ");

        tableStats = new JTable();
        tableStats.setCellSelectionEnabled(true);
        tableStats.setBorder(new LineBorder(new Color(0, 0, 0)));
        tableStats.setModel(new DefaultTableModel(
                new Object[][]{
                        {"Stat", "Number", "Modifier"},
                        {"Str", charStr, charStrMod},
                        {"Dex", charDex, charDexMod},
                        {"Con", charCon, charConMod},
                        {"Int", charInt, charIntMod},
                        {"Wis", charWis, charWisMod},
                        {"Cha", charCha, charChaMod},
                },
                new String[]{
                        "Stat", "Number", "Modifier"
                }
        ) {
            Class[] columnTypes = new Class[]{
                    String.class, Object.class, Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });

        JButton btnExportExistingCharacter = new JButton("Export Existing Character");
        btnExportExistingCharacter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ExportCharacter cDialog = new ExportCharacter();
                cDialog.setVisible(true);
            }
        });

        JButton btnSubmitCharacter = new JButton("Submit Character");
        btnSubmitCharacter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                character.setcName(textField.getText());
                character.setpName(txtPlayerName.getText());
                character.setRace(comBoxRace.getSelectedItem().toString());
                character.setcClass(comBoxClass.getSelectedItem().toString());
                character.setStr(charStr);
                character.setDex(charDex);
                character.setCon(charCon);
                character.setInte(charInt);
                character.setWis(charWis);
                character.setCha(charCha);

                cDAO.writeCharacterToDatabase(character);
            }
        });

        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[]{"Select Level", "1", "2", "3", "4", "5"}));
        GroupLayout groupLayout = new GroupLayout(frmDdeCharacter.getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addComponent(lblPlayerName)
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(txtPlayerName, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addComponent(lblCharacterName)
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(textField))
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addComponent(lblLvl)
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(comboBox, 0, 277, Short.MAX_VALUE))
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addComponent(lblClass)
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(comBoxClass, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addComponent(lblRace)
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(comBoxRace, 0, 278, Short.MAX_VALUE)))
                                                .addGap(111))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(tableStats, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                                .addGap(18)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(btnExportExistingCharacter)
                                                        .addComponent(btnSubmitCharacter))
                                                .addGap(56))))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblPlayerName)
                                        .addComponent(txtPlayerName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblCharacterName)
                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblRace)
                                        .addComponent(comBoxRace, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblClass)
                                        .addComponent(comBoxClass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblLvl)
                                        .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(19)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(tableStats, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(btnExportExistingCharacter)
                                                .addGap(14)
                                                .addComponent(btnSubmitCharacter)))
                                .addContainerGap(42, Short.MAX_VALUE))
        );
        frmDdeCharacter.getContentPane().setLayout(groupLayout);
    }
}
