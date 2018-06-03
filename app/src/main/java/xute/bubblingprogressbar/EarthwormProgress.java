package xute.bubblingprogressbar;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class EarthwormProgress extends View{

    public static final int SEGMENT_DIMENSION_IN_DP = 2;
    private static final long TRANSLATION_TIME = 2500;
    public static final long SEGMENT_DELAY = 200;
    private static int mSegmenetDimensionInPx;
    private Resources resources;
    private Paint mPaint;
    public static final String color = "#ffffff";

    private static int mSegment1StartX;
    private static int mSegment2StartX;
    private static int mSegment3StartX;
    private static int mSegment4StartX;

    private int mWidth;

    public EarthwormProgress(Context context) {
        super(context);
        init(context);
    }

    public EarthwormProgress(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public EarthwormProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        resources = context.getResources();
        mSegmenetDimensionInPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, SEGMENT_DIMENSION_IN_DP, resources.getDisplayMetrics());
        mWidth = resources.getDisplayMetrics().widthPixels;
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.parseColor(color));
        mPaint.setAntiAlias(true);
        startAnimating();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //draw single segment1
        canvas.drawRect(new Rect(mSegment1StartX,0,mSegment1StartX + mSegmenetDimensionInPx,mSegmenetDimensionInPx),mPaint);
        //draw segment2
        canvas.drawRect(new Rect(mSegment2StartX,0,mSegment2StartX + mSegmenetDimensionInPx,mSegmenetDimensionInPx),mPaint);
        //draw segment3
        canvas.drawRect(new Rect(mSegment3StartX,0,mSegment3StartX + mSegmenetDimensionInPx,mSegmenetDimensionInPx),mPaint);
        //draw segment4
        canvas.drawRect(new Rect(mSegment4StartX,0,mSegment4StartX + mSegmenetDimensionInPx,mSegmenetDimensionInPx),mPaint);
    }

    private void startAnimating(){

        final ValueAnimator segment1Animator = ValueAnimator.ofInt(0,mWidth);
        segment1Animator.setDuration(TRANSLATION_TIME);
        segment1Animator.setInterpolator(CubicBezierInterpolator.CURVE_TYPE_ONE);
        segment1Animator.setStartDelay(SEGMENT_DELAY);
        segment1Animator.setRepeatCount(100);
        segment1Animator.setRepeatMode(ValueAnimator.RESTART);
        segment1Animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mSegment1StartX = (int) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });

        final ValueAnimator segment2Animator = ValueAnimator.ofInt(0,mWidth);
        segment2Animator.setDuration(TRANSLATION_TIME);
        segment2Animator.setStartDelay(2*SEGMENT_DELAY);
        segment2Animator.setRepeatCount(100);
        segment2Animator.setRepeatMode(ValueAnimator.RESTART);
        segment2Animator.setInterpolator(CubicBezierInterpolator.CURVE_TYPE_ONE);
        segment2Animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mSegment2StartX = (int) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });

        final ValueAnimator segment3Animator = ValueAnimator.ofInt(0,mWidth);
        segment3Animator.setDuration(TRANSLATION_TIME);
        segment3Animator.setStartDelay(3*SEGMENT_DELAY);
        segment3Animator.setRepeatCount(100);
        segment3Animator.setRepeatMode(ValueAnimator.RESTART);
        segment3Animator.setInterpolator(CubicBezierInterpolator.CURVE_TYPE_ONE);
        segment3Animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mSegment3StartX = (int) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });

        final ValueAnimator segment4Animator = ValueAnimator.ofInt(0,mWidth);
        segment4Animator.setDuration(TRANSLATION_TIME);
        segment4Animator.setStartDelay(4*SEGMENT_DELAY);
        segment4Animator.setRepeatCount(100);
        segment4Animator.setRepeatMode(ValueAnimator.RESTART);
        segment4Animator.setInterpolator(CubicBezierInterpolator.CURVE_TYPE_ONE);
        segment4Animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mSegment4StartX = (int) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(segment1Animator,segment2Animator,segment3Animator,segment4Animator);
        animatorSet.start();

    }

}
