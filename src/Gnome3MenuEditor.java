import java.awt.Toolkit;
import java.io.File;

import javax.swing.JFrame;

/**
 * This Program will display and allow the editing of .desktop entries. In Gnome
 * 3. Poorly put together, I know! :)
 * 
 * @author toriscope
 * 
 */
public class Gnome3MenuEditor {

	public static String entryDirectorySuffix = "/.local/share/applications/";

	public static JFrame frame;

	public static void main(final String[] args) {

		checkForTerminalCommands(args);

		frame = new JFrame("Gnome 3 Application Menu Editor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
				"images/icon.png"));
		frame.setResizable(false);
		frame.add(new FileListPanel(new File(entryDirectorySuffix)));
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Check for and handle terminal commands.
	 * 
	 * @param args
	 *            the command parameters
	 */
	public static void checkForTerminalCommands(final String... args) {
		if (args.length == 0) {
			entryDirectorySuffix = "/home/" + System.getProperty("user.name")
					+ entryDirectorySuffix;
			return;
		}

		/*
		 * Help param.
		 */
		if (args[0].contains("-h") || args[0].contains("-help")) {
			System.out
					.println("This editor allows you to edit the entries in /usr/share/applications/, "
							+ "which controls the Gnome 3 Applications Menu. "
							+ "To specify a different directory to parse for entries, "
							+ "pass it in through command line with '-d'. ex: 'sudo java -jar gnome3-menu-editor -d /home/username/Desktop'.");
			System.exit(0);
		}

		if (args[0].contains("-d")) {
			if (args.length == 1) {
				System.out.println("Please supply a directory along with -d");
				System.exit(0);
			}
			entryDirectorySuffix = args[1];
		}
	}
}
