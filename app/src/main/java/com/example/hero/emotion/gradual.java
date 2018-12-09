package com.example.hero.emotion;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import java.util.jar.Attributes;

public class gradual extends View
{
    private int animatedValue;
    private int animatedValue1;
    private int animatedValue2;
    private int animatedValue3;
    private int animatedValue4;
    private int animatedValue5;
    private int animatedValue6;
    private int colorEnd;
    private int colorStart;

    public gradual(Context context)
    {
        super(context);
        init();
    }

    public gradual(Context context, @Nullable AttributeSet attrs)
    {
        super(context,attrs);
        init();
    }

    public void init()
    {
        postInvalidate();
        ValueAnimator animator=ValueAnimator.ofInt(128,255);
        animator.setDuration(10000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                animatedValue = (int) animation.getAnimatedValue();
                if (animatedValue<255)
                {
                    colorStart = Color.rgb(255, animatedValue-20, 128-20);                                                            //1111111111111111  (255,128,128)-->(255,255,128)
                    colorEnd = Color.rgb(200, 200, 200);
                }
                else if (animatedValue==255)
                {
                    ValueAnimator animator1=ValueAnimator.ofInt(0,127);
                    animator1.setDuration(10000);
                    animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
                    {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation)
                        {
                            animatedValue1 = (int) animation.getAnimatedValue();
                            if(animatedValue1<127)
                            {
                                colorStart = Color.rgb(255- animatedValue1-20,255, 128-20);                                 //2222222222222222222  (255,255,128)--->(128,255,128)
                                colorEnd = Color.rgb(200,200, 200);
                            }
                            else if(animatedValue1==127)
                            {
                                ValueAnimator animator2=ValueAnimator.ofInt(128,255);
                                animator2.setDuration(10000);
                                animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
                                {
                                    @Override
                                    public void onAnimationUpdate(ValueAnimator animation)
                                    {
                                        final int animatedValue2 = (int) animation.getAnimatedValue();
                                        if(animatedValue2<255)
                                        {
                                            colorStart = Color.rgb(128-20,255,animatedValue2-20);                      //333333333333333  (128,255,128)--->(128,255,255)
                                            colorEnd = Color.rgb(200,200,200);
                                        }
                                        else if(animatedValue2==255)
                                        {
                                            ValueAnimator animator3=ValueAnimator.ofInt(0,127);
                                            animator3.setDuration(10000);
                                            animator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
                                            {
                                                @Override
                                                public void onAnimationUpdate(ValueAnimator animation) {
                                                    int animatedValue3 = (int) animation.getAnimatedValue();
                                                    if (animatedValue3<127)
                                                    {
                                                        colorStart = Color.rgb(128-20, 255-animatedValue3-20, 255);           //444444444444444  (128,255,255)--->(128,128,255)
                                                        colorEnd = Color.rgb(200, 200, 200);
                                                    }
                                                    else if (animatedValue3==127)
                                                    {
                                                        ValueAnimator animator4=ValueAnimator.ofInt(128,255);
                                                        animator4.setDuration(10000);
                                                        animator4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
                                                        {
                                                            @Override
                                                            public void onAnimationUpdate(ValueAnimator animation)
                                                            {
                                                                int animatedValue4 = (int) animation.getAnimatedValue();
                                                                if (animatedValue4<255)
                                                                {
                                                                    colorStart = Color.rgb(animatedValue4, 128, 255);         //5555555555555555555  (128,128,255)--->(255,128,255)
                                                                    colorEnd = Color.rgb(200, 200, 200);
                                                                }
                                                                else if (animatedValue4==255)
                                                                {
                                                                    ValueAnimator animator5 = ValueAnimator.ofInt(0, 127);
                                                                    animator5.setDuration(10000);
                                                                    animator5.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
                                                                    {
                                                                        @Override
                                                                        public void onAnimationUpdate(ValueAnimator animation)
                                                                        {
                                                                            int animatedValue5 = (int) animation.getAnimatedValue();
                                                                            colorStart = Color.rgb(255, 128-20, 255-animatedValue5-20);
                                                                            colorEnd = Color.rgb(200, 200, 200);
                                                                            invalidate();
                                                                        }
                                                                    });
                                                                    animator5.start();
                                                                }
                                                                invalidate();
                                                            }
                                                        });
                                                        animator4.start();
                                                    }
                                                    invalidate();
                                                }
                                            });
                                            animator3.start();
                                        }
                                        invalidate();
                                    }
                                });
                                animator2.start();

                            }
                            invalidate();
                        }
                    });
                    animator1.start();
                }
                invalidate();
            }
        });
        animator.start();
    }

    public gradual(Context context,@Nullable AttributeSet attrs,int defStyleAttr)
    {
        super(context,attrs,defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获取View的宽高
        int width = getWidth();
        int height = getHeight();
        Paint paint = new Paint();
        LinearGradient backGradient = new LinearGradient(0, 0, 0, height, new int[]{colorStart,colorEnd}, new float[]{0,1f}, Shader.TileMode.CLAMP);
        paint.setShader(backGradient);
        canvas.drawRect(0, 0, width, height, paint);
    }
}
