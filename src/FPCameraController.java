
import org.lwjgl.Sys;
import org.lwjgl.input.*;
import org.lwjgl.opengl.Display;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.vector.Vector3f;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jonathanquiros
 */
public class FPCameraController {
    private Vector3f position = null;
    private Vector3f lPosition = null;
    
    private float yaw = 0.0f;
    private float pitch = 0.0f;
    private Vector3Float me;
    
    public FPCameraController(float x, float y, float z) {
        position = new Vector3f(x, y, z);
        lPosition = new Vector3f(x, y, z);
        lPosition.x = 0f;
        lPosition.y = 15f;
        lPosition.z = 0f;
    }
    
    //Increment the camera's current yaw rotation
    public void yaw(float amount) {
        yaw += amount;
    }
    
    //Increment the camera's current yaw rotation public void pitch(float amount)
    public void pitch(float amount) {
        pitch -= amount; 
    }
    
    //Moves the camera forward relative to its current rotation (yaw) public void walkForward(float distance)
    public void walkForward(float distance) {
        float xOffset = distance * (float)Math.sin(Math.toRadians(yaw)); float zOffset = distance * (float)Math.cos(Math.toRadians(yaw)); position.x -= xOffset;
        position.z += zOffset;
    }
    
    //Moves the camera backward relative to its current rotation (yaw)
    public void walkBackwards(float distance) {
        float xOffset = distance * (float)Math.sin(Math.toRadians(yaw));
        float zOffset = distance * (float)Math.cos(Math.toRadians(yaw));
        position.x += xOffset;
        position.z -= zOffset;
    }
    
    //Strafes the camera left relative to its current rotation (yaw)
    public void strafeLeft(float distance) {
        float xOffset = distance * (float)Math.sin(Math.toRadians(yaw - 90));
        float zOffset = distance * (float)Math.cos(Math.toRadians(yaw - 90));
        position.x -= xOffset;
        position.z += zOffset;
    }
    
    //Strafes the camera right relative to its current rotation (yaw) public void strafeRight(float distance)
    public void strafeRight(float distance) {
        float xOffset = distance * (float)Math.sin(Math.toRadians(yaw + 90)); float zOffset = distance * (float)Math.cos(Math.toRadians(yaw+90)); position.x -= xOffset;
        position.z += zOffset;
    }
    
    //Moves the camera up relative to its current rotation (yaw) public void moveUp(float distance)
    public void moveUp(float distance) {
        position.y -= distance; 
    }
    
    //Moves the camera down
    public void moveDown(float distance) {
        position.y += distance; 
    }
    
    //Translates and rotate the matrix so that it looks through the camera //this does basically what gluLookAt() does
    public void lookThrough() {
        glRotatef(pitch, 1.0f, 0.0f, 0.0f);
        glRotatef(yaw, 0.0f, 1.0f, 0.0f);
        glTranslatef(position.x, position.y, position.z);
    }
    
    public void gameLoop() {
        FPCameraController camera = new FPCameraController(0, 0, 0);
        float dx = 0.0f;
        float dy = 0.0f;
        float dt = 0.0f; //Length of frame
        float lastTime = 0.0f; //When the last frame was
        float longTime = 0;
        float mouseSensitivity = 0.09f;
        float movementSpeed = .35f;
        //Hide the mouse
        Mouse.setGrabbed(true);
        
        //Keep looping until the display window is closed or the ESC key is down
        while (!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            float time = Sys.getTime();
            lastTime = time;
            dx = Mouse.getDX();
            dy = Mouse.getDY();
            camera.yaw(dx * mouseSensitivity);
            camera.pitch(dy * mouseSensitivity);
            
            if (Keyboard.isKeyDown(Keyboard.KEY_W)) 
                camera.walkForward(movementSpeed);
            
            if (Keyboard.isKeyDown(Keyboard.KEY_S)) 
                camera.walkBackwards(movementSpeed);
            
            if (Keyboard.isKeyDown(Keyboard.KEY_A))
                camera.strafeLeft(movementSpeed);
            
            if (Keyboard.isKeyDown(Keyboard.KEY_D))
                camera.strafeRight(movementSpeed); 
            
            if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
                camera.moveUp(movementSpeed);
            
            if (Keyboard.isKeyDown(Keyboard.KEY_E)) 
                camera.moveDown(movementSpeed); 
            
            glLoadIdentity();
            
            camera.lookThrough();
            
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            
            Display.update();
            Display.sync(60);
        }
        Display.destroy();
    }
}