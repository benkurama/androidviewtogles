package support.prime.cell.androidviewtogles.gles;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Michael on 8/26/2016.
 */
public class Cube extends  ViewToGLRenderer{

    private static final int GL_TEXTURE_EXTERNAL_OES = 0x8D65;

    Context context;   // Application's context

    Square left;           // ( NEW )

    Square right;

    int[] texture = new int[1];
    Context delegate;
    private SurfaceTexture surface;

    // Constructor with global application context
    public Cube(Context context) {
        this.delegate = context;
        left = new Square();         // ( NEW )
        right = new Square();         // ( NEW )

    }

    private void  createTexture(GL10 gl) {

        gl.glGenTextures(1, texture, 0);

        gl.glBindTexture(GL_TEXTURE_EXTERNAL_OES, texture[0]);
        gl.glTexParameterf(GL_TEXTURE_EXTERNAL_OES, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
        gl.glTexParameterf(GL_TEXTURE_EXTERNAL_OES, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);


//	   gl.glTexParameteri(GL_TEXTURE_EXTERNAL_OES,
//	             GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        super.onSurfaceCreated(gl, config);

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);  // Set color's clear-value to black
        gl.glClearDepthf(1.0f);            // Set depth's clear-value to farthest
        gl.glEnable(GL10.GL_DEPTH_TEST);   // Enables depth-buffer for hidden surface removal
        gl.glDepthFunc(GL10.GL_LEQUAL);    // The type of depth testing to do
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);  // nice perspective view
        gl.glShadeModel(GL10.GL_SMOOTH);   // Enable smooth shading of color
        gl.glDisable(GL10.GL_DITHER);      // Disable dithering for better performance

        createTexture(gl);
        //right.loadTexture(gl, delegate);
        gl.glEnable(GL10.GL_TEXTURE_2D);  // Enable texture (NEW)
        gl.glEnable(GL_TEXTURE_EXTERNAL_OES);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        super.onSurfaceChanged(gl, width, height);

        if (height == 0) height = 1;   // To prevent divide by zero
        float aspect = (float)width / height;

        // Set the viewport (display area) to cover the entire window
        gl.glViewport(0, 0, width, height);

        // Setup perspective projection, with aspect ratio matches viewport
        gl.glMatrixMode(GL10.GL_PROJECTION); // Select projection matrix
        gl.glLoadIdentity();                 // Reset projection matrix
        // Use perspective projection
        GLU.gluPerspective(gl, 45, aspect, 0.1f, 100.f);

        gl.glMatrixMode(GL10.GL_MODELVIEW);  // Select model-view matrix
        gl.glLoadIdentity();
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        super.onDrawFrame(gl);
    }
}
