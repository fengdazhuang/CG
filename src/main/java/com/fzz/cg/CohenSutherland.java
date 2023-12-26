package com.fzz.cg;

// CohenSutherland类，封装直线裁剪的算法
class CohenSutherland {
    // 定义裁剪区域的边界
    private double x_min, y_min, x_max, y_max;
    // 定义区域码的常量
    private static final int INSIDE = 0; // 0000
    private static final int LEFT = 1;   // 0001
    private static final int RIGHT = 2;  // 0010
    private static final int BOTTOM = 4; // 0100
    private static final int TOP = 8;    // 1000

    // 构造方法，传入裁剪区域的边界
    public CohenSutherland(double x_min, double y_min, double x_max, double y_max) {
        this.x_min = x_min;
        this.y_min = y_min;
        this.x_max = x_max;
        this.y_max = y_max;
    }

    // 计算一个点的区域码
    private int computeCode(double x, double y) {
        int code = INSIDE;
        if (x < x_min) code |= LEFT;
        else if (x > x_max) code |= RIGHT;
        if (y < y_min) code |= BOTTOM;
        else if (y > y_max) code |= TOP;
        return code;
    }

    // 裁剪一条直线，返回裁剪后的直线的起点和终点坐标，如果直线完全在外部，返回null
    public double[] clip(double x0, double y0, double x1, double y1) {
        // 计算两个端点的区域码
        int code0 = computeCode(x0, y0);
        int code1 = computeCode(x1, y1);
        // 标记是否接受直线
        boolean accept = false;
        while (true) {
            if ((code0 | code1) == 0) { // 两个端点都在内部，直接接受
                accept = true;
                break;
            } else if ((code0 & code1) != 0) { // 两个端点都在同一外部区域，直接拒绝
                break;
            } else { // 两个端点在不同区域，需要计算交点
                // 选择一个在外部的端点
                int codeOut = code0 != 0 ? code0 : code1;
                // 计算交点的坐标
                double x = 0, y = 0;
                if ((codeOut & TOP) != 0) { // 交点在上边界
                    x = x0 + (x1 - x0) * (y_max - y0) / (y1 - y0);
                    y = y_max;
                } else if ((codeOut & BOTTOM) != 0) { // 交点在下边界
                    x = x0 + (x1 - x0) * (y_min - y0) / (y1 - y0);
                    y = y_min;
                } else if ((codeOut & RIGHT) != 0) { // 交点在右边界
                    y = y0 + (y1 - y0) * (x_max - x0) / (x1 - x0);
                    x = x_max;
                } else if ((codeOut & LEFT) != 0) { // 交点在左边界
                    y = y0 + (y1 - y0) * (x_min - x0) / (x1 - x0);
                    x = x_min;
                }
                // 用交点替换外部的端点
                if (codeOut == code0) {
                    x0 = x;
                    y0 = y;
                    code0 = computeCode(x0, y0);
                } else {
                    x1 = x;
                    y1 = y;
                    code1 = computeCode(x1, y1);
                }
            }
        }
        if (accept) { // 如果接受直线，返回裁剪后的坐标
            return new double[]{x0, y0, x1, y1};
        } else { // 如果拒绝直线，返回null
            return null;
        }
    }
}
