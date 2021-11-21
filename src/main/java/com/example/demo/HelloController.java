package com.example.demo;

import com.example.demo.figure.Circ;
import com.example.demo.figure.Ellip;
import com.example.demo.figure.Poly;
import com.example.demo.figure.Rect;
import com.example.demo.myFigureList.MyFigureList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.*;
import java.io.File;
import java.util.Arrays;

public class HelloController {
    @FXML
    private Label welcomeText;
    //    @FXML
//    private Label welcomeText2;
    @FXML
    private Button btnMax;
    @FXML
    private Button btnMin;
    @FXML
    private Button btnArea;
    @FXML
    private Button btnRotate;
    @FXML
    private HBox imgBox;
    //test
    private String figure;
    private String figureColor = "#010101";
    private double rectX;
    private double rectY;
    private double circleCX;
    private double circleCY;
    private double circleR;
    private double ellipseCX;
    private double ellipseCY;
    private double ellipseRX;
    private double ellipseRY;
    private Double[] arrayDPoints;


    @FXML
    protected void onImportButtonClick() {

        imgBox.getChildren().clear(); // очищаем hBox перед повторной загрузкой

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выбрать фигуру...");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("*.svg", "*.svg");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(HelloApplication.stg);

        // кнопки не активны, пока не получим файл
        btnMax.setDisable(false);
        btnMin.setDisable(false);
        btnArea.setDisable(false);
        btnRotate.setDisable(false);

        // XML DOM
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document doc = null;
        try {
            doc = dbf.newDocumentBuilder().parse(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        MyFigureList myFigureList = new MyFigureList();
        myFigureList.add("rect");
        myFigureList.add("circle");
        myFigureList.add("polygon");
        myFigureList.add("ellipse");

        // ищем дочериние элементы в svg
        if (doc != null) {
            Node tegSVG = doc.getFirstChild().getNextSibling();
            NodeList childSVG = tegSVG.getChildNodes();
            for (int i = 0; i < childSVG.getLength(); i++) {
                for (int j = 0; j < myFigureList.getSize(); j++) {
                    // ищем цвет в стилях
                    if (childSVG.item(i).getNodeName().equals("style")) {
                        String str = childSVG.item(i).getTextContent();
                        figureColor = (str.substring(str.lastIndexOf("#")).substring(0, 7)); // отрезаем rgb цвет
                    }
                    // если нашел совпадение в myFigureList
                    if (myFigureList.get(j).equals(childSVG.item(i).getNodeName())) {
                        // вытаскиваем координаты
                        NamedNodeMap content = childSVG.item(i).getAttributes();
                        switch (childSVG.item(i).getNodeName()) {
                            case "rect" -> {
                                rectX = Double.parseDouble(content.getNamedItem("x").getNodeValue());
                                rectY = Double.parseDouble(content.getNamedItem("y").getNodeValue());
                                welcomeText.setText(String.format("x = %s, y = %s", rectX, rectY));
                                figure = myFigureList.get(j);
                                drawRect();
                            }
                            case "circle" -> {
                                circleCX = Double.parseDouble(content.getNamedItem("cx").getNodeValue());
                                circleCY = Double.parseDouble(content.getNamedItem("cy").getNodeValue());
                                circleR = Double.parseDouble(content.getNamedItem("r").getNodeValue());
                                welcomeText.setText(String.format("cx = %s, cy = %s, r = %s", circleCX, circleCY, circleR));
                                figure = myFigureList.get(j);
                                drawCircle();
                            }
                            case "ellipse" -> {
                                ellipseCX = Double.parseDouble(content.getNamedItem("cx").getNodeValue());
                                ellipseCY = Double.parseDouble(content.getNamedItem("cy").getNodeValue());
                                ellipseRX = Double.parseDouble(content.getNamedItem("rx").getNodeValue());
                                ellipseRY = Double.parseDouble(content.getNamedItem("ry").getNodeValue());
                                welcomeText.setText(String.format("cx = %s, cy = %s, rx = %s, ry = %s", ellipseCX, ellipseCY, ellipseRX, ellipseRY));
                                figure = myFigureList.get(i);
                                drawEllipse();
                            }
                            case "polygon" -> {
                                String str = content.getNamedItem("points").getNodeValue();
                                welcomeText.setText("points = " + str);
                                String points = str.trim().replaceAll(" ", ",");
                                String[] arrayPoints = points.split(",");
                                arrayPoints = Arrays.stream(arrayPoints).filter(e -> (e != null && e.length() > 0)).toArray(String[]::new); // убираем null в массиве
                                arrayDPoints = new Double[arrayPoints.length];
                                for (int q = 0; q < arrayPoints.length; q++) {
                                    if (arrayPoints[q] != null) {
                                        arrayDPoints[q] = Double.parseDouble(String.valueOf(arrayPoints[q]));
                                    }
                                }
                                figure = myFigureList.get(i);
                                drawPolygon();
                            }
                        }
                    }
                }
            }
        }
    }

    public void onMinButtonClick(ActionEvent actionEvent) {
        if (figure.equals("rect")) {
            Rect rect = new Rect(rectX, rectY);
            rect.min();
            rectX = rect.getX();
            rectY = rect.getY();
            imgBox.getChildren().clear();
            drawRect();
            welcomeText.setText(String.format("x = %s, y = %s", rect.getX(), rect.getY()));
        } else if (figure.equals("circle")) {
            Circ circle = new Circ(circleCX, circleCY, circleR);
            circle.min();
            circleCX = circle.getX();
            circleCY = circle.getY();
            circleR = circle.getR();
            imgBox.getChildren().clear();
            drawCircle();
            welcomeText.setText(String.format("cx = %s, cy = %s, r = %s", circle.getX(), circle.getY(), circle.getR()));
        } else if (figure.equals("ellipse")) {
            Ellip ellip = new Ellip(ellipseCX, ellipseCY, ellipseRX, ellipseRY);
            ellip.min();
            ellipseCX = ellip.getX();
            ellipseCY = ellip.getY();
            ellipseRX = ellip.getRx();
            ellipseRY = ellip.getRy();
            imgBox.getChildren().clear();
            drawEllipse();
            welcomeText.setText(String.format("cx = %s, cy = %s, rx = %s, ry = %s", ellipseCX, ellipseCY, ellipseRX, ellipseRY));
        }
    }

    public void onMaxButtonClick(ActionEvent actionEvent) {
        if (figure.equals("rect")) {
            Rect rect = new Rect(rectX, rectY);
            rect.max();
            rectX = rect.getX();
            rectY = rect.getY();
            imgBox.getChildren().clear(); // отчищаем старую прорисовку
            drawRect(); // рисум по новой
            welcomeText.setText(String.format("x = %s, y = %s", rect.getX(), rect.getY()));
        } else if (figure.equals("circle")) {
            Circ circle = new Circ(circleCX, circleCY, circleR);
            circle.max();
            circleCX = circle.getX();
            circleCY = circle.getY();
            circleR = circle.getR();
            imgBox.getChildren().clear();
            drawCircle();
            welcomeText.setText(String.format("cx = %s, cy = %s, r = %s", circle.getX(), circle.getY(), circle.getR()));
        } else if (figure.equals("ellipse")) {
            Ellip ellip = new Ellip(ellipseCX, ellipseCY, ellipseRX, ellipseRY);
            ellip.max();
            ellipseCX = ellip.getX();
            ellipseCY = ellip.getY();
            ellipseRX = ellip.getRx();
            ellipseRY = ellip.getRy();
            imgBox.getChildren().clear();
            drawEllipse();
            welcomeText.setText(String.format("cx = %s, cy = %s, rx = %s, ry = %s", ellipseCX, ellipseCY, ellipseRX, ellipseRY));
        } else if (figure.equals("polygon")) {
            Poly poly = new Poly(arrayDPoints);
            poly.max();
            arrayDPoints = poly.getArray();
            imgBox.getChildren().clear();
            drawPolygon();
            welcomeText.setText("points = ?");
        }
    }

    public void onAreaButtonClick(ActionEvent actionEvent) {
        String s = "площадь";
        if (figure.equals("rect")) {
            Rect rect = new Rect(rectX, rectY);
            welcomeText.setText(s + " прямоугольника = " + rect.area());
        } else if (figure.equals("circle")) {
            Circ circle = new Circ(circleCX, circleCY, circleR);
            welcomeText.setText(s + " круга = " + circle.area());
        } else if (figure.equals("ellipse")) {
            Ellip ellip = new Ellip(ellipseCX, ellipseCY, ellipseRX, ellipseRY);
            welcomeText.setText(s + " эллипса = " + ellip.area());
        }
    }

    public void drawRect() {
        Rectangle rectangleDraw = new Rectangle(rectX, rectY, Color.valueOf(figureColor));
        imgBox.getChildren().addAll(rectangleDraw);
    }

    public void drawCircle() {
        Circle circleDraw = new Circle(circleCX, circleCY, circleR, Color.valueOf(figureColor));
        imgBox.getChildren().addAll(circleDraw);
    }

    public void drawEllipse() {
        Ellipse ellipse = new Ellipse(ellipseCX, ellipseCY, ellipseRX, ellipseRY);
        ellipse.setFill(Color.valueOf(figureColor));
        imgBox.getChildren().addAll(ellipse);
    }

    public void drawPolygon(){
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(arrayDPoints);
        polygon.setFill(Color.valueOf(figureColor));
        imgBox.getChildren().addAll(polygon);
    }
    public void onRotateButtonClick(ActionEvent actionEvent) {
        imgBox.setRotate(30);
    }
}