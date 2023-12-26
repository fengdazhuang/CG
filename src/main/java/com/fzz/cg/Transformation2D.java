package com.fzz.cg;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Shear;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class Transformation2D extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 创建一个Pane对象，作为画布
        Scene scene = new Scene(new BorderPane(), 1000, 600);
        // 创建一个Pane对象，用于放置按钮和形状
        BorderPane pane = (BorderPane) scene.getRoot();
        // 创建一个Canvas对象，用于绘制形状
        Canvas canvas = new Canvas(800, 600);
        // 获取GraphicsContext对象
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // 将Canvas对象添加到Pane的中心区域
        pane.setCenter(canvas);
        // 创建一个HBox对象，用来放置按钮
        VBox hbox = new VBox(10);
        hbox.setPadding(new Insets(10));
        hbox.setAlignment(Pos.CENTER_RIGHT);



        // 创建一个Button对象，用来画直线
        Button btnLine = new Button("画直线");
        btnLine.setOnAction(e -> {
            // 清除画布上的内容
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            // 创建一个Line对象，表示一条线
            Line line = new Line(200, 300, 500, 300);
            // 设置线的颜色和宽度
            gc.setStroke(Color.BLUE);
            gc.setLineWidth(5);
            // 绘制线
            gc.strokeLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());

        });

        // 创建一个Button对象，用来画圆
        Button btnCircle = new Button("画圆");
        btnCircle.setOnAction(e -> {
            // 清除画布上的内容
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            // 创建一个Circle对象，表示一个圆
            Circle circle = new Circle(250, 200, 100);
            // 设置圆的颜色和填充
            gc.setStroke(Color.RED);
            gc.setFill(Color.YELLOW);
            // 绘制圆
            gc.strokeOval(circle.getCenterX() - circle.getRadius(), circle.getCenterY() - circle.getRadius(), circle.getRadius() * 2, circle.getRadius() * 2);
            gc.fillOval(circle.getCenterX() - circle.getRadius(), circle.getCenterY() - circle.getRadius(), circle.getRadius() * 2, circle.getRadius() * 2);
        });

        // 创建一个Button对象，用来画三角形
        Button btnTriangle = new Button("画三角形");
        btnTriangle.setOnAction(e -> {
            // 清除画布上的内容
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            // 创建一个Polygon对象，表示一个三角形
            Polygon triangle = new Polygon(250, 100, 150, 300, 350, 300);
            // 设置三角形的颜色和填充
            gc.setStroke(Color.GREEN);
            gc.setFill(Color.PINK);
            // 绘制三角形
            gc.strokePolygon(new double[]{triangle.getPoints().get(0), triangle.getPoints().get(2), triangle.getPoints().get(4)}, new double[]{triangle.getPoints().get(1), triangle.getPoints().get(3), triangle.getPoints().get(5)}, 3);
            gc.fillPolygon(new double[]{triangle.getPoints().get(0), triangle.getPoints().get(2), triangle.getPoints().get(4)}, new double[]{triangle.getPoints().get(1), triangle.getPoints().get(3), triangle.getPoints().get(5)}, 3);
        });

        // 创建一个Button对象，用来平移图形
        Button btnTranslate = new Button("平移图形");
        btnTranslate.setOnAction(e -> {
            // 创建一个Translate对象，指定平移的距离
            Translate translate = new Translate(100, 100);
            // 将Translate对象应用到Pane中的所有图形上
            canvas.getTransforms().add(translate);
        });

        // 创建一个Button对象，用来缩放图形
        Button btnScale = new Button("缩放图形");
        btnScale.setOnAction(e -> {
            // 创建一个Scale对象，指定缩放的比例
            Scale scale = new Scale(0.8, 0.8,400,300);
            // 将Scale对象应用到Pane中的所有图形上
            canvas.getTransforms().add(scale);
        });

        // 创建一个Button对象，用来旋转图形
        Button btnRotate = new Button("旋转图形");
        btnRotate.setOnAction(e -> {
            // 创建一个Rotate对象，指定旋转的角度
            Rotate rotate = new Rotate(30,400,300);
            // 将Rotate对象应用到Pane中的所有图形上
            canvas.getTransforms().add(rotate);
        });

        // 创建一个Button对象，用来对称图形
        Button btnReflect = new Button("对称图形");
        btnReflect.setOnAction(e -> {
            // 创建一个Scale对象，指定对称的轴
            Scale reflect = new Scale(-1, 1,400,300);
            // 将Scale对象应用到Pane中的所有图形上
            canvas.getTransforms().add(reflect);
        });

        // 创建一个Button对象，用来错切图形
        Button btnShear = new Button("错切图形");
        btnShear.setOnAction(e -> {
            // 创建一个Shear对象，指定错切的比例
            Shear shear = new Shear(0.2, 0.2);
            // 将Shear对象应用到Pane中的所有图形上
            canvas.getTransforms().add(shear);
        });

        // 创建一个Button对象，用来相对任意点的旋转变换
        Button btnRotateAround = new Button("相对任意点的旋转变换");
        btnRotateAround.setOnAction(e -> {
            // 创建一个Rotate对象，指定旋转的角度和旋转中心
            Rotate rotateAround = new Rotate(45, 600, 400);
            // 将Rotate对象应用到Pane中的所有图形上
            canvas.getTransforms().add(rotateAround);
        });

        // 创建一个Button对象，用来相对任意点的缩放变换
        Button btnScaleAround = new Button("相对任意点的缩放变换");
        btnScaleAround.setOnAction(e -> {
            // 创建一个Scale对象，指定缩放的比例和缩放中心
            Scale scaleAround = new Scale(0.5, 0.5, 600, 400);
            // 将Scale对象应用到Pane中的所有图形上
            canvas.getTransforms().add(scaleAround);
        });

        // 创建一个Button对象，用来直线裁剪
        Button btnClip = new Button("直线裁剪");
        btnClip.setOnAction(e -> {
            // 创建一个CohenSutherland对象，用来封装直线裁剪的算法
            CohenSutherland cs = new CohenSutherland(400, 200, 800, 600);
            // 遍历Pane中的所有Line对象
            for (Node node : pane.getChildren()) {
                if (node instanceof Line) {
                    // 调用clip方法，得到裁剪后的直线的起点和终点坐标
                    double[] clipped = cs.clip(((Line) node).getStartX(), ((Line) node).getStartY(), ((Line) node).getEndX(), ((Line) node).getEndY());
                    if (clipped != null) {
                        // 更新Line对象的属性
                        ((Line) node).setStartX(clipped[0]);
                        ((Line) node).setStartY(clipped[1]);
                        ((Line) node).setEndX(clipped[2]);
                        ((Line) node).setEndY(clipped[3]);
                    }
                }
            }
        });

        // 创建一个Rectangle对象，用来表示裁剪的区域
        Rectangle clipRect = new Rectangle(200, 200, 400, 400);
        clipRect.setFill(Color.TRANSPARENT);
        clipRect.setStroke(Color.RED);
        clipRect.setStrokeWidth(2);


        // 将所有的按钮添加到HBox中
        hbox.getChildren().addAll(btnLine, btnCircle, btnTriangle, btnTranslate, btnScale,
                btnClip,btnRotate, btnReflect, btnShear, btnRotateAround, btnScaleAround);

        // 创建一个Scene对象，将Pane和HBox添加到其中，并设置宽度为1400，高度为800
        pane.setRight(hbox);
        pane.getChildren().add(clipRect);

        // 将Scene对象设置到Stage对象上，并显示出来
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX图形绘制和变换");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
