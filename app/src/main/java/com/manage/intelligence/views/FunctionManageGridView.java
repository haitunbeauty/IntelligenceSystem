package com.manage.intelligence.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class FunctionManageGridView extends NoScrollGridView {

    public FunctionManageGridView(Context context) {
        super(context);
    }

    public FunctionManageGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        View localView1 = getChildAt(0);
        int column = getWidth() / localView1.getWidth();//计算出一共有多少列

        int childCount = getChildCount();//子view的总数
        System.out.println("子view的总数childCount==" + childCount);
        Paint localPaint;//画笔
        localPaint = new Paint();
        localPaint.setStrokeWidth(1.0f);
        localPaint.setStyle(Paint.Style.STROKE);
        localPaint.setColor(Color.parseColor("#f3f3f3"));//设置画笔的颜色

        //总的行数
        int totalLine = childCount / column;
        if (childCount % column != 0){ totalLine = totalLine + 1; }


        for (int i = 0; i < childCount; i++) {//遍历子view
            View cellView = getChildAt(i);//获取子view

            //只能中间有竖线 两边不能有
            if ((i + 1) % column != 0){
                canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint);
            }

            //所在行
            int line = (i + 1) / column;
            if ((i + 1) % column != 0){ line = line + 1; }

            if (line != totalLine){
                canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(), localPaint);
            }

			/*if (i < 4) {//第一行
				canvas.drawLine(cellView.getLeft(), cellView.getTop(), cellView.getRight(), cellView.getTop(), localPaint);
			}
			if (i % column == 0) {//第一列
				canvas.drawLine(cellView.getLeft(), cellView.getTop(), cellView.getLeft(), cellView.getBottom(), localPaint);
			}
			if ((i + 1) % column == 0) {//第三列
				//画子view底部横线
				canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(), localPaint);
				canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint);
			} else if ((i + 1) > (childCount - (childCount % column))) {//如果view是最后一行
				//画子view的右边竖线
				canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint);
				canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(), localPaint);
			} else {//如果view不是最后一行
				//画子view的右边竖线
				canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint);
				//画子view的底部横线
				canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(), localPaint);
			}*/
        }
    }


}
