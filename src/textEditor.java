import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class textEditor implements ActionListener {
    JFrame frame;
    JMenuBar menuBar;
    JMenu file,edit;
    JTextArea textArea;
    JMenuItem openFile,saveFile,newWindow;
    JMenuItem copy,paste,cut,selectAll,close;
    textEditor(){
        frame= new JFrame();
        menuBar = new JMenuBar();
        textArea = new JTextArea();
        file=new JMenu("file");
        edit=new JMenu("edit");

        openFile=new JMenuItem("open file");
        saveFile=new JMenuItem("save file");
        newWindow=new JMenuItem("new file");

        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        newWindow.addActionListener(this);

        copy = new JMenuItem("copy");
        paste = new JMenuItem("paste");
        cut = new JMenuItem("cut");
        selectAll = new JMenuItem("select all");
        close = new JMenuItem("close");

        copy.addActionListener(this);
        cut.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        file.add(openFile);
        file.add(saveFile);
        file.add(newWindow);

        edit.add(copy);
        edit.add(cut);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        menuBar.add(file);
        menuBar.add(edit);

        frame.setJMenuBar(menuBar);
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));

        panel.add(textArea, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);
        frame.add(panel);

        frame.setTitle("Text Editor");

        frame.setBounds(0,0,400,400);
        frame.setVisible(true);
        frame.setLayout(null);
    }
    @Override
    public void actionPerformed (ActionEvent actionEvent){
        if(actionEvent.getSource()==copy){
            textArea.copy();
        }

        if(actionEvent.getSource()==cut){
            textArea.cut();
        }
        if(actionEvent.getSource()==paste){
            textArea.paste();
        }
        if(actionEvent.getSource()==selectAll){
            textArea.selectAll();
        }
        if(actionEvent.getSource()==close){
            System.exit(0);
        }
        if(actionEvent.getSource()==openFile) {
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseoption = fileChooser.showOpenDialog(null);
            if (chooseoption == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                String filepath = file.getPath();
                try {
                    FileReader fileReader = new FileReader(filepath);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermidiate = "";
                    String total = "";
                    while (true) {
                        intermidiate = bufferedReader.readLine();
                        if (intermidiate == null)
                            break;
                        total += intermidiate + "\n";
                    }
                    textArea.setText(total);
                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }

            }
        }
            if(actionEvent.getSource()==saveFile){
                JFileChooser fileChooser = new JFileChooser("C:");
                int chooseoption1= fileChooser.showSaveDialog(null);
                if(chooseoption1==JFileChooser.APPROVE_OPTION){
                    File file1 = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                    try{
                        FileWriter fileWriter = new FileWriter(file1);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        textArea.write(bufferedWriter);
                        bufferedWriter.close();
                    }
                    catch (IOException ioException){
                           ioException.printStackTrace();
                    }
                }
            }
        if(actionEvent.getSource()==newWindow){
            textEditor newtexteditor = new textEditor();
        }

    }

    public static void main(String[] args) {

    textEditor TextEditor= new textEditor();

    }
}