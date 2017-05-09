
/** *****************************************************************************
 * file Main.java
 * author: Arsham Ravanipour
 * class CS 445 - Computer Graphics
 *
 * assignment: Program 3
 * date last modified 5/2/2017
 *
 * purpose: This program reads a file and draws a polygon filled with the
 * specified color and with the specified transformations performed.
 * This program also doesn't work so thats an extra feature.
 ***************************************************************************** */

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.util.glu.GLU.gluPerspective;

public class Main {

    public static final int DISPLAY_HEIGHT = 480;
    public static final int DISPLAY_WIDTH = 640;
    private DisplayMode displayMode;

    public static void main(String[] args) {
        Main main = null;
        main = new Main();
        main.start();
    }

    public void start() {
        try {
            createWindow();
            Keyboard.create();
            initGL();
            render();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        Keyboard.destroy();
        Display.destroy();
    }

    private void createWindow() throws Exception {
        displayMode = new DisplayMode(DISPLAY_WIDTH, DISPLAY_HEIGHT);
        Display.setDisplayMode(displayMode);
        Display.setFullscreen(false);
        Display.setTitle("Program 3");
        Display.create();
    }

    public void render() {
        while (!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            try {
                glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
                glLoadIdentity();
                glPointSize(4);
                glEnable(GL_CULL_FACE);
                glEnable(GL_DEPTH_TEST);
                glTranslatef(0f, 0.0f, -7f);
                glRotatef(45f, 0.0f, 1.0f, 0.0f);
                glColor3f(0.5f, 0.5f, 1.0f);
                glBegin(GL_QUADS);
                glColor3f(0.0f, 0.0f, 1.0f);
                glVertex3f(1.0f, 1.0f, -1.0f);         // (Top)
                glVertex3f(-1.0f, 1.0f, -1.0f);
                glVertex3f(-1.0f, 1.0f, 1.0f);
                glVertex3f(1.0f, 1.0f, 1.0f);
                glColor3f(1.0f, 0.5f, 0.5f);
                glVertex3f(1.0f, -1.0f, 1.0f);         // (Bottom)
                glVertex3f(-1.0f, -1.0f, 1.0f);
                glVertex3f(-1.0f, -1.0f, -1.0f);
                glVertex3f(1.0f, -1.0f, -1.0f);
                glColor3f(1.0f, 0.6f, 0.8f);
                glVertex3f(1.0f, 1.0f, 1.0f);         //(Front)
                glVertex3f(-1.0f, 1.0f, 1.0f);
                glVertex3f(-1.0f, -1.0f, 1.0f);
                glVertex3f(1.0f, -1.0f, 1.0f);
                glColor3f(1.0f, 1.0f, 0.0f);
                glVertex3f(1.0f, -1.0f, -1.0f);         //(Back)
                glVertex3f(-1.0f, -1.0f, -1.0f);
                glVertex3f(-1.0f, 1.0f, -1.0f);
                glVertex3f(1.0f, 1.0f, -1.0f);
                glColor3f(0.0f, 1.0f, 1.0f);
                glVertex3f(-1.0f, 1.0f, 1.0f);         // (Left)
                glVertex3f(-1.0f, 1.0f, -1.0f);
                glVertex3f(-1.0f, -1.0f, -1.0f);
                glVertex3f(-1.0f, -1.0f, 1.0f);
                glColor3f(1.0f, 0.0f, 1.0f);
                glVertex3f(1.0f, 1.0f, -1.0f);         // (Right)
                glVertex3f(1.0f, 1.0f, 1.0f);
                glVertex3f(1.0f, -1.0f, 1.0f);
                glVertex3f(1.0f, -1.0f, -1.0f);
                glEnd();
                Display.update();
                Display.sync(60);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
   
    public void initGL() {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        gluPerspective(
                45.0f,
                (float) displayMode.getWidth() / (float) displayMode.getHeight(),
                0.1f,
                100.0f);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        glOrtho(0, 640, 0, 480, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
    }
}
