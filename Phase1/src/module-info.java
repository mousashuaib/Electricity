module Phase1 {
	requires javafx.controls;
	
	opens application to javafx.graphics, javafx.fxml,javafx.base;
}
