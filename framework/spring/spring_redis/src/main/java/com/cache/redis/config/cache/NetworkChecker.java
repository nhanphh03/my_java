package com.cache.redis.config.cache;

/**
 * @Package: com.cache.redis.config.cache
 * @author: nhanph
 * @date: 1/22/2025 2025
 * @Copyright: @nhanph
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import javafx.stage.Stage;

public class NetworkChecker extends Application {

    Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Wi-FI Connection Checker");

        //GridPane with 10px padding around edge
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        //Name Label - constrains use (child, column, row)
        Label nameLabel = new Label("Find my IP Address:");
        nameLabel.setId("bold-label");
        GridPane.setConstraints(nameLabel, 0, 0);

        //Search IP Address
        Button IPlookupButton = new Button("Search for IP");
        GridPane.setConstraints(IPlookupButton, 3, 0);

        //Name Input
        TextField nameInput = new TextField();
        nameInput.setPromptText("IP Address");
        GridPane.setConstraints(nameInput, 1, 0);

        IPlookupButton.setOnAction(event -> {
            try {
                InetAddress thisIp = InetAddress.getLocalHost();
                nameInput.setText("IP:" + thisIp.getHostAddress());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        //MAC Address Lookup Label
        Label passLabel = new Label("MAC Address Look UP:");
        GridPane.setConstraints(passLabel, 0, 1);

        //MAC Address Input
        TextField passInput = new TextField();
        passInput.setPromptText("MAC Address");
        GridPane.setConstraints(passInput, 1, 1);

        //Search MAC Address
        Button MacAddressButton = new Button("Search for MAC Address");
        GridPane.setConstraints(MacAddressButton, 3, 1);

        MacAddressButton.setOnAction(event -> {

                    InetAddress ip;
                    try {

                        ip = InetAddress.getLocalHost();
                        NetworkInterface network = NetworkInterface.getByInetAddress(ip);
                        byte[] mac = network.getHardwareAddress();

                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < mac.length; i++) {
                            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                        }
                        passInput.setText(sb.toString());

                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (SocketException e) {
                        e.printStackTrace();

                    }
                }
        );

        //Wi-Fi Connection Button
        Button WifiButton = new Button("Search my Wi-Fi");
        GridPane.setConstraints(WifiButton, 1, 3);

        TextField WifiInfo = new TextField();
        GridPane.setConstraints(WifiInfo, 1, 4);

        WifiButton.setOnAction(event -> {

            try {
                Process process = Runtime.getRuntime().exec("arp -a");
                process.waitFor();
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                int i = 0;
                while (reader.ready()) {
                    i++;
                    String ip = reader.readLine();
                    if (i >= 4) {
                        ip = ip.substring(2, 56) + "\n";
                    }
                    WifiInfo.setText(ip);
                    WifiInfo.setPrefWidth(WifiInfo.getText().length() * 7);
                }
            } catch (IOException | InterruptedException ioe) {
                ioe.printStackTrace();
            }

        });

        //Add everything to grid
        grid.getChildren().addAll(nameLabel, IPlookupButton, nameInput, passLabel, passInput, MacAddressButton, WifiButton, WifiInfo);

        Scene scene = new Scene(grid, 600, 300);
//        scene.getStylesheets().add("colour.css");
        window.setScene(scene);
        window.show();
    }

}
