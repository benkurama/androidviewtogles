package support.prime.cell.androidviewtogles.gles;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

/**
 * Created by Michael on 8/26/2016.
 */
public class GLLinearLayout extends LinearLayout implements GLRenderable {
    private ViewToGLRenderer mViewToGLRenderer;

    // default constructors

    public GLLinearLayout(Context context) {
        super(context);
    }

    public GLLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GLLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    // drawing magic
    @Override
    public void draw(Canvas canvas) {
        Canvas glAttachedCanvas = mViewToGLRenderer.onDrawViewBegin();
        if(glAttachedCanvas != null) {
            //prescale canvas to make sure content fits
            float xScale = glAttachedCanvas.getWidth() / (float)canvas.getWidth();
            glAttachedCanvas.scale(xScale, xScale);
            Log.e("INFO", xScale + "");
            //glAttachedCanvas.sc
            //draw the view to provided canvas
            super.draw(glAttachedCanvas);
        }
        // notify the canvas is updated
        mViewToGLRenderer.onDrawViewEnd();
    }

    public void setViewToGLRenderer(ViewToGLRenderer viewToGLRenderer){
        mViewToGLRenderer = viewToGLRenderer;
    }
}
