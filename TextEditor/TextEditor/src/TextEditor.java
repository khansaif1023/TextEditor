import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor  implements ActionListener {
        JFrame frame;
        JMenuBar menuBar;
        JMenu file,edit;
        // file menu item
        JMenuItem newFile ,openFile,saveFile;
        //edit menu item
        JMenuItem cut,copy,paste,selectAll,close;
        // text area
        JTextArea textArea;
        TextEditor() {
            // Initialize a frame
            frame = new JFrame();

            // Intialize a menuBar
            menuBar =new JMenuBar();

            // Initialize text area
            textArea=new JTextArea();

            // Intialize menu
            file=new JMenu("File");
            edit=new JMenu("Edit");

            // Initalize file menu item
            newFile=new JMenuItem("New Window");
            openFile=new JMenuItem("Open File");
            saveFile=new JMenuItem("Save File");

            // add actionListener to file menu item
            newFile.addActionListener(this);
            openFile.addActionListener(this);
            saveFile.addActionListener(this);

            // add menu item file to menu
            file.add(newFile);
            file.add(openFile);
            file.add(saveFile);

            //Initalize edit menu item
            cut=new JMenuItem("Cut");
            copy=new JMenuItem("Copy");
            paste=new JMenuItem("Paste");
            selectAll=new JMenuItem("select All");
            close=new JMenuItem("Close");

            // add actionListner to edit menu item
            cut.addActionListener(this);
            copy.addActionListener(this);
            paste.addActionListener(this);
            selectAll.addActionListener(this);
            close.addActionListener(this);

            //adding to edit menu
            edit.add(cut);
            edit.add(copy);
            edit.add(paste);
            edit.add(selectAll);
            edit.add(close);


            // add  menu to a fram
            menuBar.add(file);
            menuBar.add(edit);

            // set menu bar to a fram
            frame.setJMenuBar(menuBar);

            // add text area to a frame
            frame.add(textArea);

            //set diminsion of a frame
            frame.setBounds(100, 100, 400, 400);
            frame.setVisible(true);
            frame.setLayout(null);
        }
        @Override
        public void actionPerformed(ActionEvent actionEvent){
            // perform cut op
            if(actionEvent.getSource()==cut){
                textArea.cut();
            }
            // perform copy op
            if(actionEvent.getSource()==copy){
                textArea.copy();
            }
            //perform paste op
            if(actionEvent.getSource()==paste){
                textArea.paste();
            }
            // perform selectAll op
            if(actionEvent.getSource()==selectAll) {
                textArea.selectAll();
            }
            if(actionEvent.getSource()==close){
                System.exit(0);
            }
            if(actionEvent.getSource()==openFile){
                // open a file chooser
                JFileChooser fileChooser=new JFileChooser("C:");
                int chooseOption=fileChooser.showOpenDialog(null);
                //if we have clicked open button
                if(chooseOption==JFileChooser.APPROVE_OPTION){
                    // getting select file
                    File file=fileChooser.getSelectedFile();
                    //get the path of selected file
                    String filePath=file.getPath();
                    try {
                        // Initialize file reader
                        FileReader fileReader=new FileReader(filePath);
                        // Initialize buffer reader
                        BufferedReader bufferedReader=new BufferedReader(fileReader);
                        String intermediate="",output="";
                        //reader contents of file line by line
                        while ((intermediate=bufferedReader.readLine())!=null){
                            output+=intermediate+"\n";
                        }
                        // set the output string to text area
                        textArea.setText(output);
                    }
                    catch (IOException fileNotFoundException){
                        fileNotFoundException.printStackTrace();
                    }
                }
            }
            if(actionEvent.getSource()==saveFile){
                //Initialize the file picker
                JFileChooser fileChooser=new JFileChooser("C:");
                //get choose option from file chooser
                int chooseOption=fileChooser.showSaveDialog(null);
                //check if we clicked on save button
                if(chooseOption==JFileChooser.APPROVE_OPTION){
                    // create a new file with choose directry path and file now
                    File file=new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                    try{
                        //Initialize file writer
                        FileWriter fileWriter=new FileWriter(file);
                        // Initialize buffer writer
                        BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                        // write content of text area file
                        textArea.write(bufferedWriter);
                        bufferedWriter.close();
                    }
                    catch (IOException ioException){
                        ioException.printStackTrace();
                    }
                }
            }
            if(actionEvent.getSource()==newFile){
                TextEditor newtextEditor=new TextEditor();
            }
        }
    public static void main(String[] args) {
        TextEditor textEditor=new TextEditor();
    }
}