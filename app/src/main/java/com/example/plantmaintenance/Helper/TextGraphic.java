package com.example.plantmaintenance.Helper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.google.firebase.ml.vision.text.FirebaseVisionText;

public class TextGraphic extends GraphicOverlay.Graphic{
    private static final int TEXT_COLOR= Color.BLUE;
    private static final float TEXT_SIZE=54.0f;
    private static final float STROKE_WIDTH=4.0f;
    private final Paint rectpaint,textpaint;
    private final FirebaseVisionText.Element text;
    public TextGraphic(GraphicOverlay graphicOverlay, FirebaseVisionText.Element text) {
        super(graphicOverlay);
        this.text=text;
        rectpaint=new Paint();
        rectpaint.setColor(TEXT_COLOR);
        rectpaint.setStyle(Paint.Style.STROKE);
        rectpaint.setStrokeWidth(STROKE_WIDTH);
        textpaint=new Paint();
        textpaint.setColor(TEXT_COLOR);
        textpaint.setTextSize(TEXT_SIZE);
        postInvalidate();
    }

    @Override
    public void draw(Canvas canvas) {



        if(text==null){
            throw new IllegalStateException("Attempt to draw Null Text");
        }
        RectF rect=new RectF(text.getBoundingBox());
        rect.left=translateX(rect.left);
        rect.top=translateX(rect.top);
        rect.right=translateX(rect.right);
        rect.bottom=translateX(rect.bottom);
        canvas.drawRect(rect,rectpaint);
        canvas.drawText(text.getText(),rect.left,rect.bottom,textpaint);


    }
}
