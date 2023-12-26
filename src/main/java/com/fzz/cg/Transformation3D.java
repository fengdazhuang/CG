package com.fzz.cg;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Shear;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class Transformation3D extends Application {

    // 创建一个三维长方体
    private Box createBox(double width, double height, double depth, Color color) {
        Box box = new Box(width, height, depth);
        box.setMaterial(new PhongMaterial(color));
        box.setTranslateX(300);
        box.setTranslateY(300);
//        box.setTranslateZ(400);
        return box;
    }

    // 创建一个按钮
    private Button createButton(String text, double x, double y, EventHandler<ActionEvent> handler) {
        Button button = new Button(text);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setOnAction(handler);
        return button;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 创建一个长方体
        Box box = createBox(250, 250, 250, Color.RED);

        // 创建一个场景
        Group root = new Group();
        Scene scene = new Scene(root, 1000, 800, true);
        scene.setFill(Color.LIGHTGRAY);
        scene.setCamera(new PerspectiveCamera());

        // 添加长方体和按钮到场景中
        root.getChildren().add(box);
        root.getChildren().add(createButton("平移", 900, 150, e -> {
            // 平移变换
            Translate translate = new Translate();
            translate.setX(50 * Math.random() - 25);
            translate.setY(50 * Math.random() - 25);
            translate.setZ(50 * Math.random() - 25);
            box.getTransforms().add(translate);
        }));
        root.getChildren().add(createButton("缩放", 900, 200, e -> {
            // 缩放变换
            Scale scale = new Scale();
            double random = Math.random();
            scale.setX(1 - 0.5 * random);
            scale.setY(1 - 0.5 * random);
            scale.setZ(1 - 0.5 * random);
            box.getTransforms().add(scale);
        }));
        root.getChildren().add(createButton("旋转", 900, 250, e -> {
            // 旋转变换
            Rotate rotate = new Rotate();
            rotate.setAngle(45 * Math.random());
            rotate.setAxis(Rotate.X_AXIS);
            box.getTransforms().add(rotate);
        }));
        root.getChildren().add(createButton("对称", 900, 300, e -> {
            // 对称变换
            Scale scale = new Scale();
            scale.setX(-1);
            box.getTransforms().add(scale);
        }));
        root.getChildren().add(createButton("错切", 900, 350, e -> {
            // 错切变换
            Shear shear = new Shear();
            double random = Math.random();
            shear.setX(0.2 * random);
            shear.setY(0.2 * random);
            box.getTransforms().add(shear);
        }));

        // 显示舞台
        primaryStage.setTitle("javaFX三维变换");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
