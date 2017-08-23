package com.jyph.wsdapp.common.utils.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.jyph.wsdapp.R;


/**
 * 可以添加文字的ImageButton
 * @author meng
 *
 */
public class TextImageButton extends ImageButton {
	
	private String text ="";
	
	private int textColor = Color.BLACK;
	
	private float textSize = 30f;
	
	private Paint mPaint;
	
	
	
	public TextImageButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TextImageButton);
		
		text = a.getString(R.styleable.TextImageButton_ibText);
		textColor = a.getColor(R.styleable.TextImageButton_ibTextColor,0x00ffffff);
		textSize = a.getDimension(R.styleable.TextImageButton_ibTextSize, 30f);
		
		mPaint = new Paint();
		mPaint.setColor(textColor);
		mPaint.setTextSize(textSize);
		mPaint.setTextAlign(Align.CENTER);
		
		a.recycle();
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawText(text, canvas.getWidth()/2, canvas.getHeight()/2+10, mPaint);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getColor() {
		return textColor;
	}

	public void setTextColor(int textColor) {
		this.textColor = textColor;
	}

	public float getTextSize() {
		return textSize;
	}

	public void setTextSize(float textSize) {
		this.textSize = textSize;
	}
	
	
	


}
