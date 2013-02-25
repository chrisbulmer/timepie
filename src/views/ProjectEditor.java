
package views;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import models.Project;

/**
 *
 * @author Chris Bulmer
 */
public class ProjectEditor extends JDialog {
    private Project project;
    private JTextField projectName;
    private JTextField projectMaxWeekly;   
    
    public ProjectEditor(Project project){
        super();
        this.setTitle("Edit project");
        this.project = project;
    }
    
    public ProjectEditor(){
        super();
        this.setTitle("New project");
    }
    
    public void init(){
        setSize(300, 150);
        setResizable(false);
        
        try {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Tasks");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //set the looks to be like the native OS
        } catch (Exception e) {
            System.err.println("Error setting native look and feel: " + e);
        }
        
        Container contentPane = getContentPane();
        contentPane.setBackground(new Color(200, 200, 200));
        setFont(new Font("Helvetica", Font.PLAIN, 12));
        this.setLocationRelativeTo(null);
        
        JLabel projectNameLabel = new JLabel("Project name");
        contentPane.add(projectNameLabel);
        
        projectName = new JTextField(15);
        if (this.project != null){
            projectName.setText(this.project.getName());
        } else {
            projectName.setText("New project");
        }
        contentPane.add(projectName);
        
        
        JLabel projectMaxWeeklyLabel = new JLabel("Weekly hours");
        contentPane.add(projectMaxWeeklyLabel);
        projectMaxWeekly = new JTextField(5);
        
        if (this.project != null){
            projectMaxWeekly.setText(Integer.toString(this.project.getMaxWeekly() / 60));
        } else {
            projectMaxWeekly.setText("0");
        }
        contentPane.add(projectMaxWeekly);
        
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new controllers.SaveProjectListener(this, project, projectName, projectMaxWeekly));
        contentPane.add(saveButton);
        
        JButton cancelButton = new JButton("Cancel");
        
                
        cancelButton.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               dispose();
           } 
        });
        
        contentPane.add(cancelButton);

        JButton deleteButton = new JButton("Delete");

        if (project != null){
            deleteButton.addActionListener(new controllers.DeleteProjectListener(this, project));
            contentPane.add(deleteButton);
        }
        
        
        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);
        
        layout.putConstraint(SpringLayout.WEST, projectNameLabel, 10, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, projectNameLabel, 20, SpringLayout.NORTH, contentPane);
        
        layout.putConstraint(SpringLayout.WEST, projectName, 7, SpringLayout.EAST, projectNameLabel);
        layout.putConstraint(SpringLayout.NORTH, projectName, 17, SpringLayout.NORTH, contentPane);
        
        layout.putConstraint(SpringLayout.WEST, projectMaxWeeklyLabel, 10, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, projectMaxWeeklyLabel, 20, SpringLayout.SOUTH, projectName);
        
        layout.putConstraint(SpringLayout.WEST, projectMaxWeekly, 7, SpringLayout.EAST, projectMaxWeeklyLabel);
        layout.putConstraint(SpringLayout.NORTH, projectMaxWeekly, 15, SpringLayout.SOUTH, projectName);
        
        layout.putConstraint(SpringLayout.WEST, saveButton, 60, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, saveButton, 10, SpringLayout.SOUTH, projectMaxWeekly);
        
        layout.putConstraint(SpringLayout.WEST, cancelButton, 15, SpringLayout.EAST, saveButton);
        layout.putConstraint(SpringLayout.NORTH, cancelButton, 10, SpringLayout.SOUTH, projectMaxWeekly);
        
        if (project != null){
            layout.putConstraint(SpringLayout.WEST, saveButton, 24, SpringLayout.WEST, contentPane);
            layout.putConstraint(SpringLayout.WEST, cancelButton, 10, SpringLayout.EAST, saveButton);
            
            layout.putConstraint(SpringLayout.WEST, deleteButton, 10, SpringLayout.EAST, cancelButton);
            layout.putConstraint(SpringLayout.NORTH, deleteButton, 10, SpringLayout.SOUTH, projectMaxWeekly);
        }
        
        setVisible(true);
        
    }

    
}
