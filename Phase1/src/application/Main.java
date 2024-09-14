package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import application.Record;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class Main extends Application {
	public static SingleLinkListYear year;
	public static Record found;
	public static TableView<Object> table = new TableView();

	public static void table() {
		table.getItems().clear();
		NodeYear currentY = year.getfirst();
		for (int i = 0; i < year.count; i++) {
			NodeMonth currentMonth = currentY.getElement().getfirst();
			for (int k = 0; k < currentY.getElement().count; k++) {
				Node currentRecord = currentMonth.getElement().getfirst();
				for (int j = 0; j < currentMonth.getElement().count; j++) {
					table.getItems().add(currentRecord.getElement());
					currentRecord = currentRecord.getNext();
				}

				currentMonth = currentMonth.getNext();
			}
			currentY = currentY.getNext();
		}
		table.refresh();
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			ReadFile();
			Stage main = new Stage();
			BorderPane MainStage = new BorderPane();
			Button mangementScreen = new Button("MangementScreen");
			Button stat = new Button("StatisticsScreen");
			Button save = new Button("SaveScreen");
			HBox hbox121 = new HBox(mangementScreen, stat, save);
			hbox121.setAlignment(Pos.CENTER);
			hbox121.setSpacing(10);
			hbox121.setMargin(mangementScreen, new Insets(10, 10, 10, 10));
			hbox121.setMargin(stat, new Insets(0, 10, 0, 10));
			hbox121.setMargin(save, new Insets(10, 10, 10, 10));
			save.setPrefSize(100, 0);
			stat.setPrefSize(100, 0);
			ImageView electric = new ImageView("electric.jpg");
			MainStage.setCenter(electric);
			electric.setFitWidth(300);
			electric.setFitHeight(340);
			electric.setPreserveRatio(true);

			MainStage.setBottom(hbox121);
			MainStage.setStyle("-fx-background-color: gray");
			main.setScene(new Scene(MainStage, 500, 400));
			main.setTitle("MainStage");
			main.show();
			// ************************SaveScreen***********************************
			save.setOnAction(e -> {
				File file = new FileChooser().showOpenDialog(new Stage());
				try {
					FileWriter write = new FileWriter(file);
					NodeYear current = year.getfirst();
					for (int i = 0; i < year.count; i++) {
						NodeMonth currentmonth = current.getElement().getfirst();
						for (int j = 0; j < current.getElement().count; j++) {
							Node currentday = currentmonth.getElement().getfirst();
							for (int k = 0; k < currentmonth.getElement().count; k++) {
								String date = ((Record) currentday.getElement()).getDay();
								write.write(date + "," + ((Record) currentday.getElement()).getIsrealline() + ","
										+ ((Record) currentday.getElement()).getGazaPower() + ","
										+ ((Record) currentday.getElement()).getEygetLine() + ","
										+ ((Record) currentday.getElement()).getTotalDaily() + ","
										+ ((Record) currentday.getElement()).getDemand() + ","
										+ ((Record) currentday.getElement()).getPowerCutsHourDay() + ","
										+ ((Record) currentday.getElement()).getTemp() + "\n");
								currentday = currentday.getNext();
							}
							currentmonth = currentmonth.getNext();
						}
						current = current.getNext();
					}
					write.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});
			// ********************StatisticsScreen********************************
			stat.setOnAction(e -> {
				Label dayl = new Label("Day");
				Label monthl = new Label("Month");
				Label yearl = new Label("Year");
				TextField daytxt = new TextField();
				TextField monthtxt = new TextField();
				TextField yeartxt = new TextField();
				Button calculate = new Button("Calculate");
				Label total = new Label("total");
				Label avg = new Label("avarage");
				Label max = new Label("Maximam");
				Label min = new Label("Minimam");
				Label dayTotal = new Label();
				Label dayAvg = new Label();
				Label dayMax = new Label();
				Label dayMin = new Label();
				Label monthTotal = new Label();
				Label monthAvg = new Label();
				Label monthMax = new Label();
				Label monthMin = new Label();
				Label yearTotal = new Label();
				Label yearAvg = new Label();
				Label yearMax = new Label();
				Label yearMin = new Label();
				HBox hbox1 = new HBox(yearl, yeartxt, yearTotal, yearAvg, yearMax, yearMin);
				HBox hbox2 = new HBox(monthl, monthtxt, monthTotal, monthAvg, monthMax, monthMin);
				HBox hbox3 = new HBox(dayl, daytxt, dayTotal, dayAvg, dayMax, dayMin);
				HBox hbox4 = new HBox(calculate, total, avg, max, min);
				VBox vbox = new VBox(hbox4, hbox3, hbox2, hbox1);
				total.setFont(new Font("Cambria", 18));
				avg.setFont(new Font("Cambria", 18));
				max.setFont(new Font("Cambria", 18));
				min.setFont(new Font("Cambria", 18));
				calculate.setTranslateX(150);
				calculate.setTranslateY(150);
				calculate.setPrefSize(150, 10);
				vbox.setAlignment(Pos.CENTER);
				vbox.setSpacing(10);
				hbox1.setAlignment(Pos.CENTER);
				hbox1.setSpacing(10);
				hbox2.setAlignment(Pos.CENTER);
				hbox2.setSpacing(10);
				hbox3.setAlignment(Pos.CENTER);
				hbox3.setSpacing(10);
				hbox4.setAlignment(Pos.CENTER);
				hbox4.setSpacing(10);
				hbox4.setTranslateX(-55);
				BorderPane root1 = new BorderPane();
				root1.setCenter(vbox);
				root1.setStyle("-fx-background-color: silver");
				Stage stages = new Stage();
				stages.setScene(new Scene(root1, 600, 470));
				stages.setTitle("StatisticsScreen");
				stages.show();

				// ************calculate***************************************
				calculate.setOnAction(ev -> {
					String day = daytxt.getText().trim();
					NodeYear current = year.getfirst();
					Double sum = 0.0;
					int count = 0;
					Double maxans = 0.0;
					Double minans = 0.0;
					for (int i = 0; i < year.count; i++) {
						NodeMonth currentMonth = current.getElement().getfirst();
						for (int k = 0; k < current.getElement().count; k++) {
							Node currecord = currentMonth.getElement().getfirst();
							for (int j = 0; j < currentMonth.getElement().count; j++) {
								if (((Record) currecord.getElement()).getDay().split("-")[2].equalsIgnoreCase(day)) {
									sum += ((Record) currecord.getElement()).getTotalDaily();
									count++;
									if (maxans < ((Record) currecord.getElement()).getTotalDaily()) {
										maxans = ((Record) currecord.getElement()).getTotalDaily();
									}
									if (minans > ((Record) currecord.getElement()).getTotalDaily()) {
										minans = ((Record) currecord.getElement()).getTotalDaily();
									}
									break;
								}
								currecord = currecord.getNext();
							}
							currentMonth = currentMonth.getNext();
						}
						current = current.getNext();
					}
					dayTotal.setText(sum + "");
					dayAvg.setText((sum / count) + "");
					dayMin.setText(minans + "");
					dayMax.setText(maxans + "");
					String month = monthtxt.getText().trim();
					current = year.getfirst();
					Double sumMonth = 0.0;
					sum = 0.0;
					count = 0;
					maxans = 0.0;
					minans = 0.0;
					for (int i = 0; i < year.count; i++) {
						NodeMonth currentMonth = current.getElement().getfirst();
						for (int k = 0; k < current.getElement().count; k++) {
							if (currentMonth.getElement().date.equalsIgnoreCase(month)) {
								Node currecord = currentMonth.getElement().getfirst();
								for (int j = 0; j < currentMonth.getElement().count; j++) {
									sumMonth += ((Record) currecord.getElement()).getTotalDaily();
									count++;
									sum += ((Record) currecord.getElement()).getTotalDaily();
								}
								currecord = currecord.getNext();
								break;
							}
							if (maxans < sumMonth)
								maxans = sumMonth;
							if (minans > sumMonth)
								minans = sumMonth;
							sumMonth = 0.0;
							currentMonth = currentMonth.getNext();

						}
					}
					current = current.getNext();

					monthTotal.setText(sum + "");
					monthAvg.setText((sum / count) + "");
					monthMin.setText(minans + "");
					monthMax.setText(maxans + "");
					String years = yeartxt.getText().trim();
					current = year.getfirst();
					sumMonth = 0.0;
					sum = 0.0;
					count = 0;
					maxans = 0.0;
					minans = 0.0;
					for (int i = 0; i < year.count; i++) {
						if (current.getElement().date.equalsIgnoreCase(years)) {
							NodeMonth currentMonth = current.getElement().getfirst();
							for (int k = 0; k < current.getElement().count; k++) {

								Node currecord = currentMonth.getElement().getfirst();
								for (int j = 0; j < currentMonth.getElement().count; j++) {
									sumMonth += ((Record) currecord.getElement()).getTotalDaily();
									count++;
									sum += ((Record) currecord.getElement()).getTotalDaily();
									currecord = currecord.getNext();
								}
								currentMonth = currentMonth.getNext();
							}

							break;
						}
						if (maxans < sumMonth)
							maxans = sumMonth;
						if (minans > sumMonth)
							minans = sumMonth;
						sumMonth = 0.0;
						current = current.getNext();
					}

					yearTotal.setText(sum + "");
					yearAvg.setText((sum / count) + "");
					yearMin.setText(minans + "");
					yearMax.setText(maxans + "");
				});
			});
			// **********************************************************
			mangementScreen.setOnAction(eve -> {
				TableColumn date = new TableColumn("Date");
				TableColumn isrealLine = new TableColumn("Isreali Lines");
				TableColumn gazapower = new TableColumn("Gaza Power");
				TableColumn egypt = new TableColumn("Egyptian power");
				TableColumn total = new TableColumn("totalDaily");
				TableColumn demand = new TableColumn("Demand");
				TableColumn power = new TableColumn("PowerCutsHoursDay");
				TableColumn temp = new TableColumn("Temp");
				date.setMinWidth(150);
				date.setCellValueFactory(new PropertyValueFactory<Record, String>("day"));
				table.getColumns().add(date);
				isrealLine.setMinWidth(150);
				isrealLine.setCellValueFactory(new PropertyValueFactory<Record, Double>("isrealline"));
				table.getColumns().add(isrealLine);
				gazapower.setMinWidth(150);
				gazapower.setCellValueFactory(new PropertyValueFactory<Record, Double>("gazaPower"));
				table.getColumns().add(gazapower);
				egypt.setMinWidth(150);
				egypt.setCellValueFactory(new PropertyValueFactory<Record, Double>("eygetLine"));
				table.getColumns().add(egypt);
				total.setMinWidth(150);
				total.setCellValueFactory(new PropertyValueFactory<Record, Double>("totalDaily"));
				table.getColumns().add(total);
				demand.setMinWidth(150);
				demand.setCellValueFactory(new PropertyValueFactory<Record, String>("Demand"));
				table.getColumns().add(demand);
				power.setMinWidth(150);
				power.setCellValueFactory(new PropertyValueFactory<Record, Double>("powerCutsHourDay"));
				table.getColumns().add(power);
				temp.setMinWidth(150);
				temp.setCellValueFactory(new PropertyValueFactory<Record, Double>("Temp"));
				table.getColumns().add(temp);
				table();

				DatePicker datePicker = new DatePicker();
				datePicker.setPrefSize(100, 30);
				BorderPane root = new BorderPane();
				Button insert = new Button("Insert ");
				// ******************InsertScreen*********************************
				insert.setOnAction(e -> {
					Stage stageMousaInsert = new Stage();
					Label datel = new Label("Date");
					Label isrealLine2 = new Label("Isreali line");
					Label gazapowerl = new Label("Gaza Power MW");
					Label egyptline = new Label("Egypt Line MW");
					Label totaldaily = new Label("Total Daily");
					Label demandl = new Label("Demand");
					Label powercuts = new Label("Power Cuts");
					Label templ = new Label("Temp");
					TextField datetxt = new TextField();
					TextField isrealtxt = new TextField();
					TextField gazatxt = new TextField();
					TextField egypttxt = new TextField();
					TextField totaltxt = new TextField();
					TextField demandtxt = new TextField();
					TextField powercutstxt = new TextField();
					TextField temptxt = new TextField();
					HBox hbox1 = new HBox(datel, datetxt);
					hbox1.setAlignment(Pos.CENTER);
					hbox1.setSpacing(5);
					HBox hbox2 = new HBox(isrealLine2, isrealtxt);
					hbox2.setAlignment(Pos.CENTER);
					hbox2.setSpacing(5);
					HBox hbox3 = new HBox(gazapowerl, gazatxt);
					hbox3.setAlignment(Pos.CENTER);
					hbox3.setSpacing(5);
					HBox hbox4 = new HBox(egyptline, egypttxt);
					hbox4.setAlignment(Pos.CENTER);
					hbox4.setSpacing(5);
					HBox hbox5 = new HBox(totaldaily, totaltxt);
					hbox5.setAlignment(Pos.CENTER);
					hbox5.setSpacing(5);
					HBox hbox6 = new HBox(demandl, demandtxt);
					hbox6.setAlignment(Pos.CENTER);
					hbox6.setSpacing(5);
					HBox hbox7 = new HBox(powercuts, powercutstxt);
					hbox7.setAlignment(Pos.CENTER);
					hbox7.setSpacing(5);
					HBox hbox8 = new HBox(templ, temptxt);
					hbox8.setAlignment(Pos.CENTER);

					hbox8.setSpacing(5);
					Button ok = new Button("OK");
					Button cancel = new Button("Cancel");
					HBox hbox9 = new HBox(ok, cancel);
					hbox9.setAlignment(Pos.CENTER);
					hbox9.setSpacing(10);
					VBox vbox = new VBox(hbox1, hbox2, hbox3, hbox4, hbox5, hbox6, hbox7, hbox8, hbox9);
					vbox.setAlignment(Pos.CENTER);
					vbox.setSpacing(15);
					BorderPane root1 = new BorderPane();
					root1.setStyle("-fx-background-color: silver");
					root1.setCenter(vbox);
					stageMousaInsert.setTitle("InsertDate");
					stageMousaInsert.setScene(new Scene(root1, 600, 600));

					// *****************OK**********************************
					ok.setOnAction(ev -> {
						try {
							String dates = datetxt.getText().trim();
							String[] datea = dates.split("-");
							boolean flag = false;
							NodeYear currentY = year.getfirst();
							Record r = new Record(dates, Double.parseDouble(isrealtxt.getText()),
									Double.parseDouble(gazatxt.getText()), Double.parseDouble(egypttxt.getText()),
									Double.parseDouble(totaltxt.getText()), Double.parseDouble(demandtxt.getText()),
									Double.parseDouble(powercutstxt.getText()), Double.parseDouble(temptxt.getText()));
							while (currentY != null && currentY.getNext() != year.getfirst()) {
								if (currentY.getElement().date.equals(datea[0])) {
									flag = true;
									break;
								}
								currentY = currentY.getNext();
							}
							if (flag == false) {
								year.addLast(new SingleLinkListMonth(datea[0]));
								year.getLast().getElement().addLast(new SingleLinkList(datea[1]));
								year.getLast().getElement().getLast().getElement().addLast(r);
							} else {
								NodeMonth currentmonth = currentY.getElement().getfirst();
								flag = false;
								while (currentmonth != null
										&& currentmonth.getNext() != currentY.getElement().getfirst()) {
									if (currentmonth.getElement().date.equals(datea[1])) {
										flag = true;
										break;
									}
									currentmonth = currentmonth.getNext();
								}
								if (flag == false) {
									currentY.getElement().addLast(new SingleLinkList(datea[1]));
									currentY.getElement().getLast().getElement().addLast(r);
								} else {
									currentmonth.getElement().addLast(r);
								}
							}
						} catch (Exception ex) {
							ex.printStackTrace();

						}
						table();
						stageMousaInsert.close();
					});
					cancel.setOnAction(ev -> {
						table();
						stageMousaInsert.close();
					});
					stageMousaInsert.show();

				});
				// **********************************************
				Button delete = new Button("Delete ");
				delete.setOnAction(e -> {
					Stage x = new Stage();
					if (found != null) {
						String[] dates = found.getDay().split("-");
						NodeYear current = year.getfirst();
						boolean flag = false;
						while (current != null && current.getNext() != year.getfirst()) {
							if (current.getElement().date.equals(dates[0])) {
								flag = true;
								break;
							}
							current = current.getNext();
						}
						if (flag == false) {

						} else {
							NodeMonth currentmonth = current.getElement().getfirst();
							flag = false;
							while (currentmonth != null && currentmonth.getNext() != current.getElement().getfirst()) {
								if (currentmonth.getElement().date.equals(dates[1])) {
									flag = true;
									break;
								}
								currentmonth = currentmonth.getNext();
							}
							if (flag == false) {

							} else {
								Node currentRecord = currentmonth.getElement().getfirst();
								for (int i = 0; i < currentmonth.getElement().count; i++) {
									if (((Record) currentRecord.getElement()).getDay().equals(found.getDay())) {
										currentmonth.getElement().remove(currentRecord.getElement());
										break;
									}
									currentRecord = currentRecord.getNext();
								}
							}
							table();

						}
					} else {

					}
				});

				// **************************Update*****************************

				Button update = new Button("Update ");
				update.setOnAction(e -> {
					if (found != null) {
						Record re = found;
						Stage x = new Stage();
						Label datel = new Label("Date");
						Label isrealline = new Label("Isreali line");
						Label gazapowerl = new Label("Gaza Power MW");
						Label egyptline = new Label("Egypt Line MW");
						Label totaldaily = new Label("Total Daily");
						Label demandl = new Label("Demand");
						Label powercuts = new Label("Power Cuts");
						Label templ = new Label("Temp");
						TextField datetxt = new TextField();
						datetxt.setEditable(false);
						datetxt.setText(re.getDay());
						TextField isrealtxt = new TextField();
						isrealtxt.setText(re.getIsrealline() + "");
						TextField gazatxt = new TextField();
						gazatxt.setText(re.getGazaPower() + "");
						TextField egypttxt = new TextField();
						egypttxt.setText(re.getEygetLine() + "");
						TextField totaltxt = new TextField();
						totaltxt.setText(re.getTotalDaily() + "");
						TextField demandtxt = new TextField();
						demandtxt.setText(re.getDemand() + "");
						TextField powercutstxt = new TextField();
						powercutstxt.setText(re.getPowerCutsHourDay() + "");
						TextField temptxt = new TextField();
						temptxt.setText(re.getTemp() + "");
						HBox hbox1 = new HBox(datel, datetxt);
						hbox1.setAlignment(Pos.CENTER);
						hbox1.setSpacing(5);
						HBox hbox2 = new HBox(isrealline, isrealtxt);
						hbox2.setAlignment(Pos.CENTER);
						hbox2.setSpacing(5);
						HBox hbox3 = new HBox(gazapowerl, gazatxt);
						hbox3.setAlignment(Pos.CENTER);
						hbox3.setSpacing(5);
						HBox hbox4 = new HBox(egyptline, egypttxt);
						hbox4.setAlignment(Pos.CENTER);
						hbox4.setSpacing(5);
						HBox hbox5 = new HBox(totaldaily, totaltxt);
						hbox5.setAlignment(Pos.CENTER);
						hbox5.setSpacing(5);
						HBox hbox6 = new HBox(demandl, demandtxt);
						hbox6.setAlignment(Pos.CENTER);
						hbox6.setSpacing(5);
						HBox hbox7 = new HBox(powercuts, powercutstxt);
						hbox7.setAlignment(Pos.CENTER);
						hbox7.setSpacing(5);
						HBox hbox8 = new HBox(templ, temptxt);
						hbox8.setAlignment(Pos.CENTER);
						hbox8.setSpacing(5);
						Button ok = new Button("Done");
						Button cancel = new Button("Cancel");
						HBox hbox9 = new HBox(ok, cancel);
						hbox9.setAlignment(Pos.CENTER);
						hbox9.setSpacing(10);
						VBox vbox = new VBox(hbox1, hbox2, hbox3, hbox4, hbox5, hbox6, hbox7, hbox8, hbox9);
						vbox.setAlignment(Pos.CENTER);
						vbox.setSpacing(15);
						BorderPane root1 = new BorderPane();
						root1.setStyle("-fx-background-color: silver");
						root1.setCenter(vbox);
						x.setTitle("UpdateDate");
						x.setScene(new Scene(root1, 600, 600));

						// *******************OKAction*******************************
						ok.setOnAction(ev -> {
							String[] dates = re.getDay().split("-");
							NodeYear current = year.getfirst();
							boolean flag = false;
							while (current != null && current.getNext() != year.getfirst()) {
								if (current.getElement().date.equals(dates[0])) {
									flag = true;
									break;
								}
								current = current.getNext();
							}
							if (flag == false) {

							} else {
								NodeMonth currentmonth = current.getElement().getfirst();
								flag = false;
								while (currentmonth != null
										&& currentmonth.getNext() != current.getElement().getfirst()) {
									if (currentmonth.getElement().date.equals(dates[1])) {
										flag = true;
										break;
									}
									currentmonth = currentmonth.getNext();
								}
								if (flag == false) {

								} else {
									Node currentRecord = currentmonth.getElement().getfirst();
									for (int i = 0; i < currentmonth.getElement().count; i++) {
										if (((Record) currentRecord.getElement()).getDay().equals(re.getDay())) {
											currentmonth.getElement().remove(currentRecord.getElement());
											Record r = new Record(re.getDay(), Double.parseDouble(isrealtxt.getText()),
													Double.parseDouble(gazatxt.getText()),
													Double.parseDouble(egypttxt.getText()),
													Double.parseDouble(totaltxt.getText()),
													Double.parseDouble(demandtxt.getText()),
													Double.parseDouble(powercutstxt.getText()),
													Double.parseDouble(temptxt.getText()));
											currentmonth.getElement().addFirst(r);
											break;
										}
										currentRecord = currentRecord.getNext();
									}
								}
							}
							table();

							x.close();
						});
						cancel.setOnAction(ev -> {
							table();

							x.close();
						});
						x.show();

					}

				});
				// ***************************CALENDER***********************

				datePicker.setOnAction(e -> {
					try {
						String day = datePicker.getValue().toString();
						String[] dates = day.split("-");
						NodeYear current = year.getfirst();
						boolean flag = false;
						for (int i = 0; i < year.count; i++) {
							if (current.getElement().date.equals(dates[0])) {
								flag = true;
								break;
							}
							current = current.getNext();
						}
						if (flag == false) {
							found = null;

						} else {
							NodeMonth currentmonth = current.getElement().getfirst();
							flag = false;
							for (int i = 0; i < current.getElement().count; i++) {
								if (currentmonth.getElement().date.equals(dates[1])) {
									flag = true;
									break;
								}
								currentmonth = currentmonth.getNext();
							}
							if (flag == false) {
								found = null;
							} else {
								Node currentRecord = currentmonth.getElement().getfirst();
								for (int i = 0; i < currentmonth.getElement().count; i++) {
									if (((Record) currentRecord.getElement()).getDay().equals(day)) {
										found = ((Record) currentRecord.getElement());
										Record re = (Record) currentRecord.getElement();
										Stage stagee = new Stage();
										Label datel = new Label("Date");
										Label isrealline = new Label("Isreali line");
										Label gazapowerl = new Label("Gaza Power MW");
										Label egyptline = new Label("Egypt Line MW");
										Label totaldaily = new Label("Total Daily");
										Label demandl = new Label("Demand");
										Label powercuts = new Label("Power Cuts");
										Label templ = new Label("Temp");
										TextField datetxt = new TextField();
										datetxt.setEditable(false);
										datetxt.setText(re.getDay());
										TextField isrealtxt = new TextField();
										isrealtxt.setText(re.getIsrealline() + "");
										TextField gazatxt = new TextField();
										gazatxt.setText(re.getGazaPower() + "");
										TextField egypttxt = new TextField();
										egypttxt.setText(re.getEygetLine() + "");
										TextField totaltxt = new TextField();
										totaltxt.setText(re.getTotalDaily() + "");
										TextField demandtxt = new TextField();
										demandtxt.setText(re.getDemand() + "");
										TextField powercutstxt = new TextField();
										powercutstxt.setText(re.getPowerCutsHourDay() + "");
										TextField temptxt = new TextField();
										temptxt.setText(re.getTemp() + "");
										HBox hbox1 = new HBox(datel, datetxt);
										hbox1.setAlignment(Pos.CENTER);
										hbox1.setSpacing(5);
										HBox hbox2 = new HBox(isrealline, isrealtxt);
										hbox2.setAlignment(Pos.CENTER);
										hbox2.setSpacing(5);
										HBox hbox3 = new HBox(gazapowerl, gazatxt);
										hbox3.setAlignment(Pos.CENTER);
										hbox3.setSpacing(5);
										HBox hbox4 = new HBox(egyptline, egypttxt);
										hbox4.setAlignment(Pos.CENTER);
										hbox4.setSpacing(5);
										HBox hbox5 = new HBox(totaldaily, totaltxt);
										hbox5.setAlignment(Pos.CENTER);
										hbox5.setSpacing(5);
										HBox hbox6 = new HBox(demandl, demandtxt);
										hbox6.setAlignment(Pos.CENTER);
										hbox6.setSpacing(5);
										HBox hbox7 = new HBox(powercuts, powercutstxt);
										hbox7.setAlignment(Pos.CENTER);
										hbox7.setSpacing(5);
										HBox hbox8 = new HBox(templ, temptxt);
										hbox8.setAlignment(Pos.CENTER);
										hbox8.setSpacing(5);
										Button cancel = new Button("Cancel");
										HBox hbox9 = new HBox(cancel);
										hbox9.setAlignment(Pos.CENTER);
										hbox9.setSpacing(10);
										VBox vbox = new VBox(hbox1, hbox2, hbox3, hbox4, hbox5, hbox6, hbox7, hbox8,
												hbox9);
										vbox.setAlignment(Pos.CENTER);
										vbox.setSpacing(15);
										BorderPane root1 = new BorderPane();
										root1.setCenter(vbox);
										stagee.setTitle("Calender");
										root1.setStyle("-fx-background-color: silver");
										stagee.setScene(new Scene(root1, 600, 600));
										stagee.show();
										cancel.setOnAction(ev -> {
											stagee.close();
										});
										break;
									}
									found = null;
									currentRecord = currentRecord.getNext();
								}
							}
						}
					} catch (Exception ex) {

					}
				});
				root.setCenter(table);
				HBox hbox = new HBox(insert, delete, update, datePicker);
				hbox.setAlignment(Pos.CENTER);
				hbox.setSpacing(10);
				root.setBottom(hbox);
				root.setCenter(table);

				root.setStyle("-fx-background-color: gray");
				primaryStage.setScene(new Scene(root, 850, 600));
				primaryStage.show();
			});

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}
	// ************************ReadFile***********************

	public static void ReadFile() {
		year = new SingleLinkListYear();
		File file = new FileChooser().showOpenDialog(new Stage());
		try {
			Scanner in = new Scanner(file);
			in.nextLine();

			while (in.hasNextLine()) {
				String[] str = in.nextLine().split(",");
				String[] date = str[0].split("-");
				boolean flag = false;
				NodeYear current = year.getfirst();
				while (current != null && current.getNext() != year.getfirst()) {
					if (current.getElement().date.equals(date[0])) {
						flag = true;
						break;
					}
					current = current.getNext();
				}
				if (flag == false) {
					year.addLast(new SingleLinkListMonth(date[0]));
					year.getLast().getElement().addLast(new SingleLinkList(date[1]));
					year.getLast().getElement().getLast().getElement()
							.addLast(new Record(str[0], Double.parseDouble(str[1]), Double.parseDouble(str[2]),
									Double.parseDouble(str[3]), Double.parseDouble(str[4]), Double.parseDouble(str[5]),
									Double.parseDouble(str[6]), Double.parseDouble(str[7])));
				} else {
					NodeMonth currentmonth = current.getElement().getfirst();
					flag = false;
					while (currentmonth != null && currentmonth.getNext() != current.getElement().getfirst()) {
						if (currentmonth.getElement().date.equals(date[1])) {
							flag = true;
							break;
						}
						currentmonth = currentmonth.getNext();
					}
					if (flag == false) {
						current.getElement().addLast(new SingleLinkList(date[1]));
						current.getElement().getLast().getElement()
								.addLast(new Record(str[0], Double.parseDouble(str[1]), Double.parseDouble(str[2]),
										Double.parseDouble(str[3]), Double.parseDouble(str[4]),
										Double.parseDouble(str[5]), Double.parseDouble(str[6]),
										Double.parseDouble(str[7])));
					} else {
						currentmonth.getElement().addLast(new Record(str[0], Double.parseDouble(str[1]),
								Double.parseDouble(str[2]), Double.parseDouble(str[3]), Double.parseDouble(str[4]),
								Double.parseDouble(str[5]), Double.parseDouble(str[6]), Double.parseDouble(str[7])));
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	static NodeYear currentY;
	static NodeMonth currentM;
	static int count = 0;

	public static void main(String[] args) {
		launch(args);

	}

}
