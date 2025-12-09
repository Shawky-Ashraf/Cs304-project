
// graphics packages

import Pages.*;
import Texture.TextureReader;
import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Home extends JFrame {
  AudioInputStream audioStream;
  Clip clip;

  public Home() {
    try {
      audioStream = AudioSystem.getAudioInputStream(new File("Assets\\sound\\game.wav"));
      clip = AudioSystem.getClip();
      clip.open(audioStream);
      clip.loop(Clip.LOOP_CONTINUOUSLY);
    } catch (Exception e) {
      e.printStackTrace();
    }

    HomeEventListener listener = new HomeEventListener(clip);
    GLCanvas glcanvas = new GLCanvas();
    glcanvas.addKeyListener(listener);
    glcanvas.addGLEventListener(listener);
    glcanvas.addMouseListener(listener);
    glcanvas.addMouseMotionListener(listener);
    getContentPane().add(glcanvas, BorderLayout.CENTER);

    Animator animator = new FPSAnimator(60);
    animator.add(glcanvas);
    animator.start();

    setTitle("Home");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(1200, 700);
    setLocationRelativeTo(null);
    setVisible(true);
    setFocusable(true);
    glcanvas.requestFocus();

    // Play background music or sound effect
    // clip.start();
  }
}

class HomeEventListener implements GLEventListener, MouseMotionListener, MouseListener, KeyListener {

  final static String ASSETS_PATH = "Assets\\Sprites";
  final static String[] textureNames = new File(ASSETS_PATH).list();
  final TextureReader.Texture texture[] = new TextureReader.Texture[textureNames.length];
  final int textures[] = new int[textureNames.length];
  final int orthoX = 600, orthoY = 350;
  int windowWidth = 2 *  orthoX, windowHight = 2* orthoY, flag[] = { 0 };

  GL gl; // global gl drawable to use in the class

  public HomeEventListener() {
  }

  @Override
  public void init(GLAutoDrawable arg0) {
    this.gl = arg0.getGL(); // set the gl drawable
    gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f); // This Will Clear The Background Color To Black
    gl.glOrtho(-orthoX, orthoX, -orthoY, orthoY, -1, 1); // setting the orth and the coordinates of the window

    // set textures
    gl.glEnable(GL.GL_TEXTURE_2D); // Enable Texture Mapping
    gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
    gl.glGenTextures(textureNames.length, textures, 0);

    for (int i = 0; i < textureNames.length; i++) {
      try {
        texture[i] = TextureReader
            .readTexture(ASSETS_PATH + "\\" + textureNames[i], true);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[i]);

        // mipmapsFromPNG(gl, new GLU(), texture[i]);
        new GLU().gluBuild2DMipmaps(
            GL.GL_TEXTURE_2D,
            GL.GL_RGBA, // Internal Texel Format,
            texture[i].getWidth(), texture[i].getHeight(),
            GL.GL_RGBA, // External format from image,
            GL.GL_UNSIGNED_BYTE,
            texture[i].getPixels() // Imagedata
        );
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void display(GLAutoDrawable arg0) {
    // Clear the screen
    gl.glClear(GL.GL_COLOR_BUFFER_BIT);


  }

  @Override
  public void mousePressed(MouseEvent e) {
    windowHight = e.getComponent().getHeight();
    windowWidth = e.getComponent().getWidth();
    
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    windowHight = e.getComponent().getHeight();
    windowWidth = e.getComponent().getWidth();

  }

  @Override
  public void mouseDragged(MouseEvent e) {
    windowHight = e.getComponent().getHeight();
    windowWidth = e.getComponent().getWidth();

  }

  @Override
  public void mouseMoved(MouseEvent e) {
    windowHight = e.getComponent().getHeight();
    windowWidth = e.getComponent().getWidth();

  }

  @Override
  public void keyTyped(KeyEvent e) {
    
  }

  @Override
  public void keyPressed(KeyEvent e) {
    
  }

  @Override
  public void keyReleased(KeyEvent e) {
    
  }

  private double convertX(double x) {
    return x * (2 * orthoX) / windowWidth - orthoX;
  }

  private double convertY(double y) {
    return orthoY - ((2f * orthoY) / windowHight * y);
  }

  private void transfer() {
    
  }

  private void draw(int index, double x, double y) {
  }

  private void drawHome() {
    

  }


  @Override
  public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
  }

  @Override
  public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    windowHight = e.getComponent().getHeight();
    windowWidth = e.getComponent().getWidth();
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    windowHight = e.getComponent().getHeight();
    windowWidth = e.getComponent().getWidth();
  }

  @Override
  public void mouseExited(MouseEvent e) {
    windowHight = e.getComponent().getHeight();
    windowWidth = e.getComponent().getWidth();
  }
}