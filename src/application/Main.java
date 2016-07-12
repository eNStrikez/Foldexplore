package application;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class Main extends Application {

	TreeView<File> treeFiles;

	private final Node rootIcon = new ImageView(
			new Image("http://icons.iconarchive.com/icons/hopstarter/sleek-xp-basic/16/Folder-icon.png"));

	@Override
	public void start(Stage primaryStage) {
		try {
			Button cB = new Button("C Drive Documents");
			Button dB = new Button("D Drive Documents");

			treeFiles = new TreeView<File>();
			BorderPane root1 = new BorderPane();
			StackPane root2 = new StackPane();

			Scene scene1 = new Scene(root1, 1000, 50);
			Scene scene2 = new Scene(root2, 1000, 1000);

			cB.setOnAction(e -> {
				File currentDir = new File("C:/Users/Neil Patil/Documents");
				findFiles(currentDir, null);
				primaryStage.setScene(scene2);
			});

			dB.setOnAction(e -> {
				File currentDir = new File("D:/Neil Patil/Documents");
				findFiles(currentDir, null);
				primaryStage.setScene(scene2);
			});

			root1.setLeft(cB);
			root1.setRight(dB);

			root2.getChildren().add(treeFiles);

			primaryStage.setScene(scene1);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void findFiles(File dir, TreeItem<File> parent) {
		TreeItem<File> root = new TreeItem<>(dir);

		File[] files = dir.listFiles();

		for (File file : files) {
			if (file.isDirectory()) {
				findFiles(file, root);
			} else {
				root.getChildren().add(new TreeItem<>(file));
			}

		}

		if (parent == null) {
			treeFiles.setRoot(root);
		} else {
			parent.getChildren().add(root);
		}
	}

	public static void main(String[] args) {
		launch(args);
		
	}
}
