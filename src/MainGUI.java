/*
 * Author: Josue Galeas
 * Last Edit: Apr 4, 2016
 * Description: TODO
 */

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;

@SuppressWarnings("serial")
public class MainGUI extends JFrame implements ActionListener
{
	private JTextField console;
	private static String filePath;
	private static String qGPS = "-g";
	private static String qMST = "-k";

	public MainGUI()
	{
		setTitle("Algorithm (WIP)");
		setLayout(new GridLayout(5, 1));
		JButton start = new JButton("Run");

		add(new FileSelect());
		add(new QuestionGPS());
		add(new QuestionMST());

		start.addActionListener(this);
		add(start);

		console = new JTextField("");
		console.setEditable(false);
		console.addActionListener(this);
		add(console);

		setVisible(true);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private class FileSelect extends JPanel implements ActionListener
	{
		private JTextField fileName;
		private JFileChooser fileChooser;
		private JButton openFile;

		public FileSelect()
		{
			JLabel fileSelected = new JLabel("File Selected:");
			fileName = new JTextField("", 10);
			fileName.setEditable(false);
			openFile = new JButton("Browse");
			fileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");

			fileChooser.setCurrentDirectory(new File("."));
			fileChooser.setDialogTitle("Input Data File");
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooser.setFileFilter(filter);

			add(fileSelected);
			add(fileName);
			add(openFile);
			setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Input Data File"));

			openFile.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent event)
		{
			File selectedFile;
			int returnValue = fileChooser.showOpenDialog(openFile);

			if (returnValue == JFileChooser.APPROVE_OPTION)
			{
				selectedFile = fileChooser.getSelectedFile();
				fileName.setText(selectedFile.getName() + "");
				filePath = selectedFile.toString();
			}
		}
	}

	private class QuestionGPS extends JPanel implements ActionListener
	{
		public QuestionGPS()
		{
			setLayout(new GridLayout(1, 2));
			ButtonGroup choice = new ButtonGroup();

			JRadioButton GPS = new JRadioButton("GPS");
			GPS.setActionCommand("-g");
			GPS.setSelected(true);
			GPS.addActionListener(this);

			JRadioButton MATLAB = new JRadioButton("MATLAB");
			MATLAB.setActionCommand("-m");
			MATLAB.addActionListener(this);

			choice.add(GPS);
			choice.add(MATLAB);
			add(GPS);
			add(MATLAB);

			setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Input Data Type"));
		}

		@Override
		public void actionPerformed(ActionEvent event)
		{
			qGPS = event.getActionCommand();
		}
	}

	private class QuestionMST extends JPanel implements ActionListener
	{
		public QuestionMST()
		{
			setLayout(new GridLayout(1, 2));
			ButtonGroup choice = new ButtonGroup();

			JRadioButton K = new JRadioButton("Kruskal's");
			K.setActionCommand("-k");
			K.setSelected(true);
			K.addActionListener(this);

			JRadioButton P = new JRadioButton("Prim's");
			P.setActionCommand("-p");
			P.addActionListener(this);

			choice.add(K);
			choice.add(P);
			add(K);
			add(P);

			setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "MST Algorithm"));
		}

		@Override
		public void actionPerformed(ActionEvent event)
		{
			qMST = event.getActionCommand();
		}
	}

	public static void main(String[] args)
	{
		MainGUI app = new MainGUI();
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		if (filePath == null)
		{
			JOptionPane.showMessageDialog(null, "ERROR: Missing input data file.", "", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String[] argsArray = new String[4];

		argsArray[0] = "-i";
		argsArray[1] = filePath;
		argsArray[2] = qGPS;
		argsArray[3] = qMST;

		console.setText("Complete.");
		MainBody2.main(argsArray);
	}
}
